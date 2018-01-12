package com.lms.domain;

public class BookDetails {

    private int bookNumber;
    private String bookName;
    private Double price;
    private int count;
    private String author;

    public BookDetails(int bookNumber, String name, String author) {
        this.bookNumber = bookNumber;
        this.bookName = name;
        this.author = author;
    }

    public BookDetails(int bookNumber, String name, String author, Double price) {
        this.bookNumber = bookNumber;
        this.bookName = name;
        this.price = price;
        this.author = author;
    }

    public int getCount() {
        return count;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public String getBookName() {
        return bookName;
    }

    public Double getPrice() {
        return price;
    }
}