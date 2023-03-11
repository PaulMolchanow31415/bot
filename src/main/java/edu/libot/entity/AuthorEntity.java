package edu.libot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AuthorEntity {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private List<BookEntity> writtenBooks;

    @Override
    public String toString() {
        return "АВТОР: \n" +
                "имя: " + name + "\n" +
                "фамилия: " + surname + "\n" +
                "отчество: " + patronymic + "\n";
    }
}