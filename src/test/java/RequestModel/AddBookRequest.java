package RequestModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddBookRequest {
    String bookName;
    String isbn;
    String aisle;
    String authorName;


    @JsonProperty("name")
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }
    @JsonProperty("author")
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAisle() {
        return aisle;
    }

    public String getAuthorName() {
        return authorName;
    }
}


