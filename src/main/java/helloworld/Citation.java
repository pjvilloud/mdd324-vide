package helloworld;

import java.util.Date;
import java.util.Objects;

public class Citation {

    private String quote;
    private String author;

    public Citation(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Citation citation = (Citation) o;
        return quote.equals(citation.quote) &&
                author.equals(citation.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quote, author);
    }

    @Override
    public String toString() {
        return "Citation{" +
                "quote='" + quote + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
