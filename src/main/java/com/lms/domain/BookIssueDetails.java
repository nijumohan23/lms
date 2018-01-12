package com.lms.domain;

import java.util.Date;

public class BookIssueDetails {

    private String bookTitle;
    private String userName;
    private Date issueDate;
    private Date returnDate;

    public String getUserName() {
        return userName;
    }

    public BookIssueDetails(String bookTitle, String userName)
    {
        this.bookTitle =bookTitle;
        this.userName = userName;
        this.issueDate=new Date();
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

    public String getBookTitle() {
        return bookTitle;
    }
}