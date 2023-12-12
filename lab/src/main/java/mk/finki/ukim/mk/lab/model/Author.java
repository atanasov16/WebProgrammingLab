package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = AuthorFullnameConverter.class)
    private AuthorFullname authorFullname;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDate dateOfBirth;
    private String name;
    private String surname;
    private String biography;
    public Author(){}
    public Author(Long id, AuthorFullname authorFullname, String biography, LocalDate date) {
        this.id = id;
        this.authorFullname= authorFullname;
        this.biography = biography;
        this.dateOfBirth = date;
    }

    public Author(Long id, String name, String surname) {
        this.id = id;
        this.authorFullname = new AuthorFullname(name, surname);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
