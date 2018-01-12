package com.lms.util;

import java.util.concurrent.atomic.AtomicInteger;

public class LMSUtil {

    private static AtomicInteger bookId = new AtomicInteger(1);
    private static AtomicInteger studentId = new AtomicInteger(1);


    public static int getNewBookId() {
        return bookId.getAndIncrement();
    }

    public static int getNewStudentId() {
        return studentId.getAndIncrement();
    }
}