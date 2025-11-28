package com.example.dit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Entity class to store greenhouse gas emission record.
 * will hold data from both the XML and JSON.
 */
@Entity
public class Emission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;             // primary key in the table
    
    private String category;    // emission category code e.g. "1.A.1.a."
    
    private String gasUnits;    // text for gas 
    
    private double value;       // numeric emission value
    
    private String scenario;    // scenario 
    
    private int year;           // year of the data
    
    private String dataType;    // "projection" or "actual" (XML vs JSON)
    
    private String description; //  readable description of the category
    
    private boolean approved;   // hass record been approved as final
    
    private String approvedBy;  // username of the user who approved it

    public Emission() {
    }

    //  constructor 
    public Emission(String category, String gasUnits, double value,
                    String scenario, int year, String dataType, String description) {
        this.category = category;
        this.gasUnits = gasUnits;
        this.value = value;
        this.scenario = scenario;
        this.year = year;
        this.dataType = dataType;
        this.description = description;
        this.approved = false;       // default to not approved
        this.approvedBy = null;      // nobody yet
    }

    // --- getters and setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGasUnits() {
        return gasUnits;
    }

    public void setGasUnits(String gasUnits) {
        this.gasUnits = gasUnits;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}
