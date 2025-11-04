package com.myfitness.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "member") // converted to xml or json by resteasy
@Entity // jpa entity 
public class Member {

    @Id //primary key for member table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String memberId;
    private String name;
    private String phone;
    private String address;
    private String fitnessGoal;

    @ManyToOne
    private String membershipPlan;

    // Unidirectional one-to-many: Member -> Payment 
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // cascade if the one thing changes in the member it updates related fies
    @JoinColumn(name = "member_id", nullable = false)
    private List<Payment> payments = new ArrayList<>();

    public Member() {}

    public Member(String memberId, String name, String phone, String address, String fitnessGoal, String membershipPlan) {
        this.memberId = memberId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.fitnessGoal = fitnessGoal;
        this.membershipPlan = membershipPlan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFitnessGoal() {
        return fitnessGoal;
    }

    public void setFitnessGoal(String fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

    public String getMembershipPlan() {
        return membershipPlan;
    }

    public void setMembershipPlan(String membershipPlan) {
        this.membershipPlan = membershipPlan;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = (payments != null) ? payments : new ArrayList<>();
    }
}
