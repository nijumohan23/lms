package com.lms.domain;

import java.util.Date;

public class BookIssueDetails {

    private int bookNumber;
    private int customerId;
    private Date issueDate;
    private Date returnDate;

    public BookIssueDetails(int bookNumber,int customerId)
    {
        this.bookNumber=bookNumber;
        this.customerId = customerId;
        this.issueDate=new Date();
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public void setBookNumer(int bookNumber) {
        this.bookNumber = bookNumber;
    }


    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

}