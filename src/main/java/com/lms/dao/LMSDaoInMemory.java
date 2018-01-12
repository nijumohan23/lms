package com.lms.dao;

import com.lms.domain.BookDetails;
import com.lms.domain.BookIssueDetails;
import com.lms.domain.UserDetails;

import java.util.*;

/**
 * Created by Niju on 1/12/2018.
 */
public class LMSDaoInMemory implements LMSDao {


    private Map<String, BookDetails> bookDetailsByTitle = new HashMap<String, BookDetails>();
    private Map<String, List<BookDetails>> bookDetailsByAuthor = new HashMap<String, List<BookDetails>>();
    private Map<String,UserDetails> userList = new HashMap<String, UserDetails>();
    private Map<UserDetails,List<BookIssueDetails>> booksIssuedForUsers = new HashMap<UserDetails, List<BookIssueDetails>>();

    /**
     * Assemption is each book having unique name, But an author can publish multiple books
     *
     * @param book
     */
    public void saveBookDetails(BookDetails book) {

        String author = book.getAuthor();
        List<BookDetails> bookListByAuthor = bookDetailsByAuthor.get(author);
        if (bookListByAuthor == null) {

            bookListByAuthor = new ArrayList<BookDetails>();
            bookDetailsByAuthor.put(author, bookListByAuthor);
        }
        bookListByAuthor.add(book);
        bookDetailsByTitle.put(book.getTitle(), book);
    }

    public void saveUserDetails(UserDetails user) {
        userList.put(user.getName(),user);
    }

    public void lendBook(BookIssueDetails bookIssueDetails) {

        String userName = bookIssueDetails.getUserName();
        UserDetails user = getUserDetailsByName(userName);
        if (checkIfUserIsInvalid(user)) return;

        int maxBooksCanBeIssued = user.getMaxNumberOfBooks();

        List<BookIssueDetails> booksIssuedSofFar = booksIssuedForUsers.get(user);


        if (booksIssuedSofFar == null) {
            booksIssuedSofFar = new ArrayList<BookIssueDetails>();
            booksIssuedForUsers.put(user, booksIssuedSofFar);
        }

        if (booksIssuedSofFar.size() == maxBooksCanBeIssued) {
            String message = "We have already issued max books to you. Sorry";
            System.out.println(message);
//            throw new LMSException(message);
        }


        booksIssuedSofFar.add(bookIssueDetails);
    }

    public void returnBook(String userName, String bookTitle) {

        UserDetails user = getUserDetailsByName(userName);

        if (checkIfUserIsInvalid(user)) return;

        List<BookIssueDetails> bd = booksIssuedForUsers.get(user);
        for (BookIssueDetails issueDetails : bd) {
            if (issueDetails.getBookTitle() == bookTitle) {
//                Date issueDate = issueDetails.getIssueDate();
                Date returnDate = new Date();
                issueDetails.setReturnDate(returnDate);
            }
        }
    }

    private boolean checkIfUserIsInvalid(UserDetails user) {
        if(user==null)
        {
            System.out.println("Invalid user");
            return true;
        }
        return false;
    }

    public void limitNumberOfBooksAllowedForUser(String userName, int maxNumberOfBooksAllowed) {

        UserDetails user = userList.get(userName);
        if (checkIfUserIsInvalid(user)) return;

        user.setMaxNumberOfBooks(maxNumberOfBooksAllowed);
    }

    public BookDetails getBookDetailsByTitle(String bookTitle) {
        BookDetails bookDetails = bookDetailsByTitle.get(bookTitle);
        return bookDetails;
    }

    public List<BookDetails> getBookDetailsByAuthor(String author) {
        return bookDetailsByAuthor.get(author);
    }

    public UserDetails getUserDetailsByName(String userName) {
        UserDetails user = userList.get(userName);
        return user;
    }
}
