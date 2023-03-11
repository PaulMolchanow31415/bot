package edu.libot.entity;

import lombok.Data;

@Data
public class BookEntity {
    private Long id;
    private String title;
    private String section;
    private String origin;
    private String yearPub;
    private AuthorEntity author;
    private PublisherEntity publisher;

    @Override
    public String toString() {
        return "\nКНИГА: \n" +
                "название: " + title + "\n" +
                "раздел библиотеки: " + section + "\n" +
                "год издания: " + yearPub + "\n" +
                publisher.toString() +
                author.toString() +
                "происхождение: \n" + origin + "\n";
    }
}
