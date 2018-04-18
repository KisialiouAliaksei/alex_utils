package by.gsu.epamlab.model.beans;

import java.sql.Date;
import java.util.List;

/**
 * Created by alex on 13.06.2016.
 */
public class Premiere {
    private String name;
    private String genre;
    private Author author;
    private List<Date> dates;
    private String description;

    public Premiere() {}

    public Premiere(String name) {
        this.name = name;
    }

    public Premiere(Author author, String name, String genre,String description) {
        this.author = author;
        this.name = name;
        this.genre = genre;
        this.description = description;
    }


    public Premiere(Author author, String name, String genre, List<Date> dates, String description) {
        this(author, name, genre, description);
        this.dates = dates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Date> getDates() {
        return dates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Premiere premiere = (Premiere) o;

        return name.equals(premiere.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

}
