package com.lms.core;

import com.lms.domain.BookDetails;
import com.lms.domain.BookIssueDetails;
import com.lms.domain.UserDetails;
import com.lms.util.LMSUtil;

import java.util.*;

/**
 * 1. Ability to add books to the system.
 * 2. Ability to add users to the system.
 * 3. Ability to lend books to users.
 * 4. Ability to return books to the library.
 * 5. Ability to limit the number of books borrowed by user.
 * 6. Ability to search a book by title, author.
 * 7. Ability to search a user by name
 * *
 */
public class LMSImpl {

    /**
     * List of books
     */
    static List<BookDetails> books = new ArrayList<BookDetails>();
    /**
     * Look map for the books issued per student
     */
    static Map<Integer, ArrayList<BookIssueDetails>> bookIssueLookupMap = new HashMap<Integer, ArrayList<BookIssueDetails>>();
    static Map<Integer, UserDetails> userLookupMap = new HashMap<Integer, UserDetails>();

    public static void main(String[] args) {
        lms();
    }

    private static void lms() {
        System.out.println("Library Management System");
        System.out.println("Press 1 to add Book");
        System.out.println("Press 2 to issue a book");
        System.out.println("Press 3 to return a book");
        System.out.println("Press 4 to print the book details");
        System.out.println("Press 5 to print complete issue detais");
        System.out.println("Press 6 to add customer details");
        System.out.println("Press 7 to Print customer details");
        System.out.println("Press 8 to exit");
        Scanner c = new Scanner(System.in);
        int choice = c.nextInt();
        do {
            switch (choice) {
                case 1:
                    addBook();
                    lms();
                    break;
                case 2:
                    issueBook();
                    lms();
                    break;
                case 3:
                    returnBook();
                    lms();
                    break;
                case 4:
                    printBookDetails();
                    lms();
                    break;
                case 5:
                    printCompleteIssueDetails();
                    lms();
                    break;
                case 6:
                    addCustomer();
                    lms();
                    break;
                case 7:
                    printCustomerDetails();
                    lms();
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Invalid input");
                    lms();
                    break;
            }
            c = new Scanner(System.in);
            choice = c.nextInt();
        } while (choice > 0 && choice < 6);
    }

    private static void printCustomerDetails() {

        Collection<UserDetails> userDetails = userLookupMap.values();
        for (UserDetails cust : userDetails) {
            System.out.println(cust.getId() + "\t" + cust.getName());
        }
    }

    private static void addCustomer() {

        System.out.println("Please enter Customer name ");
        Scanner c = new Scanner(System.in);
        String name = c.nextLine();
        int id = LMSUtil.getNewStudentId();
        UserDetails cust = new UserDetails(name, id);
        userLookupMap.put(id, cust);

    }

    private static void printCompleteIssueDetails() {
        for (Map.Entry<Integer, ArrayList<BookIssueDetails>> entry : bookIssueLookupMap
                .entrySet()) {

            for (BookIssueDetails b : entry.getValue()) {
                System.out.println(entry.getKey() + "  " + b.getBookNumber()
                        + "  " + b.getIssueDate() + "  " + b.getReturnDate());
            }
        }
    }

    private static void printBookDetails() {
        for (BookDetails b : books) {
            System.out.println(b.getBookNumber() + "  " + b.getBookName() + "  "
                    + b.getCount() + "  " + b.getPrice());
        }
    }

    private static void returnBook() {
        System.out.println("Enter CustomerId ");
        Scanner c = new Scanner(System.in);
        int customerId = Integer.parseInt(c.nextLine());
        System.out.println("BookId");
        int bookId = Integer.parseInt(c.nextLine());
        List<BookIssueDetails> bd = bookIssueLookupMap.get(customerId);
        for (BookIssueDetails b : bd) {
            if (b.getBookNumber() == bookId) {
                Date issueDate = b.getIssueDate();
                Date returnDate = new Date();

                long diff = returnDate.getTime() - issueDate.getTime();

                long diffDays = diff / (24 * 60 * 60 * 1000);

                if (diffDays > 10) {
                    int fine = (int) (diffDays - 10);
                    fine = fine * 10;
                    System.out.println("Total Fine " + fine + " Rs.");
                }
                b.setReturnDate(returnDate);
            }
        }
    }


    private static void issueBook() {
        System.out.println("Enter Customer Id");
        Scanner c = new Scanner(System.in);
        int customerId = Integer.parseInt(c.nextLine());
        System.out.println("Enter Book Number");
        Scanner c1 = new Scanner(System.in);
        int bookNumber = c1.nextInt();
        BookIssueDetails newIssuedBook = new BookIssueDetails(bookNumber, customerId);

        ArrayList<BookIssueDetails> booksIssuedSofFar = bookIssueLookupMap.get(customerId);

        if (booksIssuedSofFar == null) {
            booksIssuedSofFar = new ArrayList<BookIssueDetails>();
            bookIssueLookupMap.put(customerId, booksIssuedSofFar);
        }

        if (booksIssuedSofFar.size() == 2) {
            System.out.println("We have already issued max(2) books to you. Sorry");
            lms();
        }


        booksIssuedSofFar.add(newIssuedBook);
    }

    private static void addBook() {
        System.out.println("Please enter book name ");
        Scanner c = new Scanner(System.in);
        String name = c.nextLine();
        System.out.println("Please enter author name");
        String author = c.nextLine();
        System.out.println("Please enter Price");
        Double price = c.nextDouble();
        BookDetails book = new BookDetails(LMSUtil.getNewBookId(), name, author, price);
        books.add(book);

    }

}