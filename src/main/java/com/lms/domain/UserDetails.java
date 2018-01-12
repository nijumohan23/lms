package com.lms.domain;

public class UserDetails {

    private int id;
    private String name;
    /**
     * Default number of books which a user can lend, we can override using setter
     */
    private int maxNumberOfBooks = 2;

    public int getMaxNumberOfBooks() {
        return maxNumberOfBooks;
    }

    public UserDetails(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setMaxNumberOfBooks(int maxNumberOfBooks) {
        this.maxNumberOfBooks = maxNumberOfBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDetails that = (UserDetails) o;

        if (getId() != that.getId()) return false;
        if (getMaxNumberOfBooks() != that.getMaxNumberOfBooks()) return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getMaxNumberOfBooks();
        return result;
    }
}