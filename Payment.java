package com.myfitness.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "payment")
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String paymentId;
    private String memberId;
    private double amount;
    private String paymentDate;

    public Payment(String paymentId, String memberId, double amount, String paymentDate) {
        this.paymentId = paymentId;
        this.memberId = memberId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public Payment() {}

    @XmlElement
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    @XmlElement
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    @XmlElement
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    @XmlElement
    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }
}
