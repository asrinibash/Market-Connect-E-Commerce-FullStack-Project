package com.cutm.marketconnect.Model;


import jakarta.persistence.Column;

public class PaymentInformation {
    @Column(name = "cardholder_name")
    private String cardHolderName;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "expirationDate")
    private String expirationDate;
    @Column(name = "cvv")
    private String cvv;
}
