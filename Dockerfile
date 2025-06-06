FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /workspace/app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make the Maven wrapper executable
RUN chmod +x ./mvnw

# Build all dependencies first (this layer will be cached)
RUN ./mvnw dependency:go-offline -B

# Copy the source code
COPY src src

# Build the application
RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Run stage
FROM eclipse-temurin:17-jre-alpine

# Upgrade libexpat to fix CVE-2024-8176
RUN apk update && apk upgrade libexpat

VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency

# Copy the application layers
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

#Set security related directives
USER nobody:nobody
# Set the entrypoint to run the application
ENTRYPOINT ["java","-cp","app:app/lib/*","com.credable.lms.LmsApplication"]
