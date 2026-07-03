package com.librarymanagement.entity;

import java.time.LocalDate;

/**
 * Represents a physical copy of a book.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class BookCopy {

    private Integer copyId;
    private Book book;
    private String barcode;
    private String shelfLocation;
    private String status;
    private LocalDate purchaseDate;

    public BookCopy() {
    }

    public Integer getCopyId() {
        return copyId;
    }

    public void setCopyId(Integer copyId) {
        this.copyId = copyId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getShelfLocation() {
        return shelfLocation;
    }

    public void setShelfLocation(String shelfLocation) {
        this.shelfLocation = shelfLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return barcode;
    }
}