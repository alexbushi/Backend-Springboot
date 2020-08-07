package com.example.interns.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "donors")
public class Donor {
    @Transient
    public static final String SEQUENCE_NAME = "donors_sequence";

    @Id
    private long id;

    @NotBlank
    private String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    @Indexed(unique = true)
    String email;

    @NotBlank
    String phoneNumber;

    @NotBlank
    String zipcode;

    @NotNull
    Boolean receiveEmails;

    @NotNull
    Boolean monthlyDonations;

    @NotNull
    Double donationAmount;

    @NotNull
    Date date;

    public Donor(String firstName, String lastName, String email, String phoneNumber, String zipcode,
            Boolean receiveEmails, Boolean monthlyDonations, Double donationAmount, Date date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.zipcode = zipcode;
        this.receiveEmails = receiveEmails;
        this.monthlyDonations = monthlyDonations;
        this.donationAmount = donationAmount;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Boolean getReceiveEmails() {
        return receiveEmails;
    }

    public String getZipcode() {
        return zipcode;
    }

    public Date getDate() {
        return date;
    }

    public Boolean getMonthlyDonations() {
        return monthlyDonations;
    }

    public Double getDonationAmount() {
        return donationAmount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirst(String firstName) {
        this.firstName = firstName;
    }

    public void setLast(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setReceiveEmails(Boolean receiveEmails) {
        this.receiveEmails = receiveEmails;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMonthlyDonations(Boolean monthlyDonations) {
        this.monthlyDonations = monthlyDonations;
    }

    public void setDonationAmount(Double donationAmount) {
        this.donationAmount = donationAmount;
    }

}