package com.lms.util;

import java.util.concurrent.atomic.AtomicInteger;

public class LMSUtil {


    public static final int TOTAL_BOOKS_ALLOWED_PER_CUSTOMER = 2;
    private static AtomicInteger bookId = new AtomicInteger(1);
    private static AtomicInteger studentId = new AtomicInteger(1);


    public static int getNewBookId() {
        return bookId.getAndIncrement();
    }

    public static int getNewStudentId() {
        return studentId.getAndIncrement();
    }
}