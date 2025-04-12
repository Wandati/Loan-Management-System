package com.credable.lms.dto;

public class ScoringResponse {
    private int score;
    private double limitAmount;
    private String exclusion;
    private String exclusionReason;

    public int getScore() {
        return score;
    }

    public double getLimitAmount() {
        return limitAmount;
    }

    public String getExclusion() {
        return exclusion;
    }

    public String getExclusionReason() {
        return exclusionReason;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLimitAmount(double limitAmount) {
        this.limitAmount = limitAmount;
    }

    public void setExclusion(String exclusion) {
        this.exclusion = exclusion;
    }

    public void setExclusionReason(String exclusionReason) {
        this.exclusionReason = exclusionReason;
    }
}
