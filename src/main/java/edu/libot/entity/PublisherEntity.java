package edu.libot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PublisherEntity {
    private Long id;
    private String name;
    private String city;
    private List<BookEntity> publishedBooks;

    @Override
    public String toString() {
        return "ИЗДАТЕЛЬСТВО: \n" +
                "название: " + name + "\n" +
                "город: " + city + "\n";
    }
}