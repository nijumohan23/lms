package com.lms.core;

import com.lms.dao.LMSDao;
import com.lms.dao.LMSDaoInMemory;
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
public class LMSMain {

    /**
     *  /**
     *  Currently return new instance due to interest of time, but we can make this singleton as it is stateless
     *
     */
    private LMSDao lmsDao = new LMSDaoInMemory();

    public static void main(String[] args) {
        new LMSMain().lms();
    }

    private void lms() {
        System.out.println("Library Management System");
        System.out.println("Press 1 to Add Book");
        System.out.println("Press 2 to User to system");
        System.out.println("Press 3 to Lend or issue a book");
        System.out.println("Press 4 to Return books ");
        System.out.println("Press 5 to Limit number of books a user can lend");
        System.out.println("Press 6 to Print book details by title");
        System.out.println("Press 7 to Print book details by author");
        System.out.println("Press 8 to Print user details by name");
        System.out.println("Press 9 to Exit");
        Scanner c = new Scanner(System.in);
        int choice = c.nextInt();
        do {
            switch (choice) {
                case 1:
                    addBook();
                    lms();
                    break;
                case 2:
                    addUser();
                    lms();
                    break;
                case 3:
                    issueBook();
                    lms();
                    break;
                case 4:
                    returnBook();
                    lms();
                    break;
                case 5:
                    limitNumberOfBooksForUser();
                    lms();
                    break;
                case 6:
                    printBookDetailsByTitle();
                    lms();
                    break;
                case 7:
                    printBookDetailsByAuthor();
                    lms();
                    break;
                case 8:
                    printUserDetailsByName();
                    lms();
                    break;
                case 9:
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

    private void printUserDetailsByName() {

        System.out.println("Please enter User Name ");
        Scanner c = new Scanner(System.in);
        String userName = c.nextLine();
        UserDetails user = lmsDao.getUserDetailsByName(userName);
        if(user==null)
        {
            System.out.println("Invalid user");
            return;
        }
        System.out.println("User Name: "+user.getName()+"\tUser Id: "+user.getId()+"\tMaximum books can be issued: "+user.getMaxNumberOfBooks());
    }

    private void printBookDetailsByAuthor() {
        System.out.println("Please enter Author Name ");
        Scanner c = new Scanner(System.in);
        String author = c.nextLine();
        List<BookDetails> bookDetailsList = lmsDao.getBookDetailsByAuthor(author);
        if(bookDetailsList==null)
        {
            System.out.println("Author name is not valid");
            return;
        }
        for (BookDetails book: bookDetailsList) {
            System.out.println("Book Name: "+book.getTitle()+"\tAuthor: "+book.getAuthor()+"\tPrice: "+book.getPrice());
        }
    }

    private void printBookDetailsByTitle() {
        System.out.println("Please enter Book Title ");
        Scanner c = new Scanner(System.in);
        String bookTitle = c.nextLine();
        BookDetails bookDetails = lmsDao.getBookDetailsByTitle(bookTitle);
        if(bookDetails==null)
        {
            System.out.println("Invalid book title");
            return;
        }
        System.out.println("Book Name: "+bookDetails.getTitle()+"\tAuthor: "+bookDetails.getAuthor()+"\tPrice: "+bookDetails.getPrice());
    }

    private void limitNumberOfBooksForUser() {
        System.out.println("Please enter User name ");
        Scanner c = new Scanner(System.in);
        String userName = c.nextLine();
        System.out.println("Please enter the maximum numbers which can be issued for customer");
        int maxNumberOfBooksAllowed = Integer.parseInt(c.nextLine());
        lmsDao.limitNumberOfBooksAllowedForUser(userName,maxNumberOfBooksAllowed);
    }


    private  void addUser() {

        System.out.println("Please enter Customer name ");
        Scanner c = new Scanner(System.in);
        String name = c.nextLine();
        int id = LMSUtil.getNewStudentId();
        UserDetails user = new UserDetails(name, id);
        lmsDao.saveUserDetails(user);

    }



    private  void returnBook() {
        System.out.println("Enter Customer Name ");
        Scanner c = new Scanner(System.in);
        String userName = c.nextLine();
        System.out.println("Book Title");
        String bookTitle = c.nextLine();

        lmsDao.returnBook(userName,bookTitle);


    }


    private void issueBook() {
        System.out.println("Enter User Name");
        Scanner c = new Scanner(System.in);
        String userName = c.nextLine();
        System.out.println("Enter Book Title");
        Scanner c1 = new Scanner(System.in);
        String bookTitle = c1.nextLine();
        BookIssueDetails bookIssueDetails = new BookIssueDetails(bookTitle, userName);

        lmsDao.lendBook(bookIssueDetails);
    }

    private void addBook() {
        System.out.println("Please enter the book title ");
        Scanner c = new Scanner(System.in);
        String name = c.nextLine();
        System.out.println("Please enter author name");
        String author = c.nextLine();
        System.out.println("Please enter Price");
        Double price = c.nextDouble();
        BookDetails book = new BookDetails(LMSUtil.getNewBookId(), name, author, price);
        lmsDao.saveBookDetails(book);
    }
}