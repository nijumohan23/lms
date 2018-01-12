package com.lms.dao;

import com.lms.domain.BookDetails;
import com.lms.domain.BookIssueDetails;
import com.lms.domain.UserDetails;

import java.util.List;

public interface LMSDao {

    void saveBookDetails(BookDetails book);

    void saveUserDetails(UserDetails user);

    void lendBook(BookIssueDetails bookIssueDetails);

    void returnBook(String userName, String bookTitle);

    void limitNumberOfBooksAllowedForUser(String userName, int maxNumberOfBooksAllowed);

    BookDetails getBookDetailsByTitle(String bookTitle);

    List<BookDetails> getBookDetailsByAuthor(String author);

    UserDetails getUserDetailsByName(String userName);
}
