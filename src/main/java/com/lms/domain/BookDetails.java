package com.lms.domain;

public class BookDetails {

    private int bookNumber;
    private String title;
    private Double price;
    private int count;
    private String author;

    public String getAuthor() {
        return author;
    }

    public BookDetails(int bookNumber, String name, String author) {
        this.bookNumber = bookNumber;
        this.title = name;
        this.author = author;
    }

    public BookDetails(int bookNumber, String name, String author, Double price) {
        this.bookNumber = bookNumber;
        this.title = name;
        this.price = price;
        this.author = author;
    }

    public int getCount() {
        return count;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }
}