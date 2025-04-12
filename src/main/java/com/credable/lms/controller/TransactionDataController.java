package com.credable.lms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/transaction-data")
public class TransactionDataController {

    @GetMapping
    public List<Object> getTransactionData(@RequestParam String customerNumber) {
        // TODO: Replace with actual SOAP call to CORE Banking System
        // and data transformation
        return createMockTransactionData(customerNumber);
    }

    private List<Object> createMockTransactionData(String customerNumber) {
        // Replace with mock data as per the document
        List<Object> mockData = new ArrayList<>();
        if (customerNumber.equals("318411216")) {
            mockData.add(createMockTransaction1());
            mockData.add(createMockTransaction2());
        }
        return mockData;
    }

    private Object createMockTransaction1() {
        //returns mock transaction data object as specified in the document
        return new Object() {
            public String accountNumber = "332216783322167555621628";
            public Double alternativechanneltrnscrAmount = 87988.2441;
            public Double alternativechanneltrnsdebitAmount = 675.3423;
            public Long alternativechanneltrnsdebitNumber = 902403930L;
            public Long atmTransactionsNumber = 4812921L;
            public Double atmtransactionsAmount = 561.96661249;
            public Long bouncedChequesDebitNumber = 8L;
            public Long bouncedchequescreditNumber = 0L;
            public Double bouncedchequetransactionscrAmount = 748011.19;
            public Double bouncedchequetransactionsdrAmount = 43345.569028578;
            public Double chequeDebitTransactionsAmount = 4.6933076819151E8;
            public Long chequeDebitTransactionsNumber = 44L;
            public Long createdAt = 740243532000L;
            public Long createdDate = 1196266216000L;
            public Double credittransactionsAmount = 609.297663;
            public Double debitcardpostransactionsAmount = 21.134264;
            public Long debitcardpostransactionsNumber = 502L;
            public Double fincominglocaltransactioncrAmount = 0.0;
            public Long id = 2L;
            public Double incominginternationaltrncrAmount = 70.52733936;
            public Long incominginternationaltrncrNumber = 9L;
            public Long incominglocaltransactioncrNumber = 876L;
            public Long intrestAmount = 310118L;
            public Long lastTransactionDate = 1169339429000L;
            public Object lastTransactionType = null;
            public Long lastTransactionValue = 3L;
            public Double maxAtmTransactions = 6.0;
            public Double maxMonthlyBebitTransactions = 5.66201073E8;
            public Double maxalternativechanneltrnscr = 0.0;
            public Double maxalternativechanneltrnsdebit = 0.0;
            public Double maxbouncedchequetransactionscr = 0.0;
            public Double maxchequedebittransactions = 0.0;
            public Double maxdebitcardpostransactions = 5.18696078798654E15;
            public Double maxincominginternationaltrncr = 0.0;
            public Double maxincominglocaltransactioncr = 0.0;
            public Double maxmobilemoneycredittrn = 0.0;
            public Double maxmobilemoneydebittransaction = 0.0;
            public Double maxmonthlycredittransactions = 0.0;
            public Double maxoutgoinginttrndebit = 0.0;
            public Double maxoutgoinglocaltrndebit = 0.0;
            public Double maxoverthecounterwithdrawals = 959858.0;
            public Double minAtmTransactions = 0.0;
            public Double minMonthlyDebitTransactions = 0.0;
            public Double minalternativechanneltrnscr = 0.0;
            public Double minalternativechanneltrnsdebit = 0.0;
            public Double minbouncedchequetransactionscr = 0.0;
            public Double minchequedebittransactions = 0.0;
            public Double mindebitcardpostransactions = 4.539102239610779E15;
            public Double minincominginternationaltrncr = 0.0;
            public Double minincominglocaltransactioncr = 0.0;
            public Double minmobilemoneycredittrn = 0.0;
            public Double minmobilemoneydebittransaction = 524.0;
            public Double minmonthlycredittransactions = 0.0;
            public Double minoutgoinginttrndebit = 0.0;
            public Double minoutgoinglocaltrndebit = 0.0;
            public Double minoverthecounterwithdrawals = 5821338.0;
            public Double mobilemoneycredittransactionAmount = 0.0;
            public Long mobilemoneycredittransactionNumber = 946843L;
            public Double mobilemoneydebittransactionAmount = 0.0;
            public Long mobilemoneydebittransactionNumber = 5523407L;
            public Double monthlyBalance = 6.59722841E8;
            public Double monthlydebittransactionsAmount = 103262.90429936;
            public Double outgoinginttransactiondebitAmount = 5.473303560725E7;
            public Long outgoinginttrndebitNumber = 646L;
            public Double outgoinglocaltransactiondebitAmount = 565972.1236;
            public Long outgoinglocaltransactiondebitNumber = 2971L;
            public Double overdraftLimit = 0.0;
            public Double overthecounterwithdrawalsAmount = 332.0;
            public Long overthecounterwithdrawalsNumber = 87569L;
            public Double transactionValue = 1.0;
            public Long updatedAt = 773556430000L;
        };
    }

    private Object createMockTransaction2() {
        return new Object() {
            public String accountNumber = "332216783322167555621628";
            public Double alternativechanneltrnscrAmount = 27665.6889301;
            public Double alternativechanneltrnsdebitAmount = 2.9997265951905E7;
            public Long alternativechanneltrnsdebitNumber = 114L;
            public Long atmTransactionsNumber = 36934417L;
            public Double atmtransactionsAmount = 192538.94;
            public Long bouncedChequesDebitNumber = 535L;
            public Long bouncedchequescreditNumber = 0L;
            public Double bouncedchequetransactionscrAmount = 1.37;
            public Double bouncedchequetransactionsdrAmount = 2602.4;
            public Double chequeDebitTransactionsAmount = 2765.57;
            public Long chequeDebitTransactionsNumber = 6L;
            public Long createdAt = 1401263420000L;
            public Long createdDate = 1350538588000L;
            public Double credittransactionsAmount = 0.0;
            public Double debitcardpostransactionsAmount = 117347.063;
            public Long debitcardpostransactionsNumber = 931309756L;
            public Double fincominglocaltransactioncrAmount = 2552389.4;
            public Long id = 5L;
            public Double incominginternationaltrncrAmount = 76.160425;
            public Long incominginternationaltrncrNumber = 285700400L;
            public Long incominglocaltransactioncrNumber = 1L;
            public Long intrestAmount = 22L;
            public Long lastTransactionDate = 554704439000L;
            public Object lastTransactionType = null;
            public Long lastTransactionValue = 1L;
            public Double maxAtmTransactions = 0.0;
            public Double maxMonthlyBebitTransactions = 7.8272009E7;
            public Double maxalternativechanneltrnscr = 0.0;
            public Double maxalternativechanneltrnsdebit = 0.0;
            public Double maxbouncedchequetransactionscr = 0.0;
            public Double maxchequedebittransactions = 0.0;
            public Double maxdebitcardpostransactions = 5.468080253826023E15;
            public Double maxincominginternationaltrncr = 0.0;
            public Double maxincominglocaltransactioncr = 0.0;
            public Double maxmobilemoneycredittrn = 0.0;
            public Double maxmobilemoneydebittransaction = 0.0;
            public Double maxmonthlycredittransactions = 0.0;
            public Double maxoutgoinginttrndebit = 0.0;
            public Double maxoutgoinglocaltrndebit = 0.0;
            public Double maxoverthecounterwithdrawals = 6.09866462E8;
            public Double minAtmTransactions = 0.0;
            public Double minMonthlyDebitTransactions = 0.0;
            public Double minalternativechanneltrnscr = 0.0;
            public Double minalternativechanneltrnsdebit = 0.0;
            public Double minbouncedchequetransactionscr = 0.0;
            public Double minchequedebittransactions = 0.0;
            public Double mindebitcardpostransactions = 4.716295906413E12;
            public Double minincominginternationaltrncr = 0.0;
            public Double minincominglocaltransactioncr = 0.0;
            public Double minmobilemoneycredittrn = 0.0;
            public Double minmobilemoneydebittransaction = 0.0;
            public Double minmonthlycredittransactions = 29624.78;
            public Double minoutgoinginttrndebit = 0.0;
            public Double minoutgoinglocaltrndebit = 0.0;
            public Double minoverthecounterwithdrawals = 1.00927826E8;
            public Double mobilemoneycredittransactionAmount = 349693.8071922;
            public Long mobilemoneycredittransactionNumber = 4092L;
            public Double mobilemoneydebittransactionAmount = 1.87382823746E7;
            public Long mobilemoneydebittransactionNumber = 0L;
            public Double monthlyBalance = 2205.0;
            public Double monthlydebittransactionsAmount = 295.6677;
            public Double outgoinginttransactiondebitAmount = 9.561730814;
            public Long outgoinginttrndebitNumber = 0L;
            public Double outgoinglocaltransactiondebitAmount = 56.03;
            public Long outgoinglocaltransactiondebitNumber = 0L;
            public Double overdraftLimit = 7.0;
            public Double overthecounterwithdrawalsAmount = 3.72849038239E8;
            public Long overthecounterwithdrawalsNumber = 546382904L;
            public Double transactionValue = 51.0;
            public Long updatedAt = 687774305000L;
        };
    }
}
