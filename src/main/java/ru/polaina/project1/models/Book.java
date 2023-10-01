package ru.polaina.project1.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person personId;

    @NotEmpty(message = "Title should not be empty")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Writer should not be empty")
    @Column(name = "writer")
    private String writer;

    @Column(name = "year_of_publishing")
    private int yearOfPublishing;

    @Column(name = "date_of_receiving")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateOfReceiving;

    @Transient
    private boolean isReturnTimeOverdue;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public Date getDateOfReceiving() {
        return dateOfReceiving;
    }

    public void setDateOfReceiving(Date dateOfReceiving) {
        this.dateOfReceiving = dateOfReceiving;
    }

    public boolean isReturnTimeOverdue() {
        Date currentTime = new Timestamp(System.currentTimeMillis());
        long differenceInMillis = currentTime.getTime() - dateOfReceiving.getTime();
        long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMillis);

        return differenceInDays > 10;
    }

    public void setReturnTimeOverdue(boolean returnTimeOverdue) {
        isReturnTimeOverdue = returnTimeOverdue;
    }
}
