package com.myfitness.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "membershipplan")
@Entity
public class MembershipPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String planId;
    private String planName;
    private double price;

    public MembershipPlan(String planId, String planName, double price) {
        this.planId = planId;
        this.planName = planName;
        this.price = price;
    }

    public MembershipPlan() {}

    @XmlElement
    public String getPlanId() { return planId; }
    public void setPlanId(String planId) { this.planId = planId; }

    @XmlElement
    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }

    @XmlElement
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}

