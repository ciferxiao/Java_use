package cifer.com.java_use.bean;

/**
 * Created by cifer
 * on 2018/4/17 14:03.
 */

public class Bookbean {
    private String bookname;
    private String writer;
    private String bookpicture;
    private String bookbreaf;
    private String bookid;

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }
    /*    public Bookbean(String bookname, String writer, String bookpicture, String bookbreaf) {
        this.bookname = bookname;
        this.writer = writer;
        this.bookpicture = bookpicture;
        this.bookbreaf = bookbreaf;
    }*/

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getBookpicture() {
        return bookpicture;
    }

    public void setBookpicture(String bookpicture) {
        this.bookpicture = bookpicture;
    }

    public String getBookbreaf() {
        return bookbreaf;
    }

    public void setBookbreaf(String bookbreaf) {
        this.bookbreaf = bookbreaf;
    }

    @Override
    public String toString() {
        return "Bookbean{" +
                "bookname='" + bookname + '\'' +
                ", writer='" + writer + '\'' +
                ", bookpicture='" + bookpicture + '\'' +
                ", bookbreaf='" + bookbreaf + '\'' +
                '}';
    }
}
