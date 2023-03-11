package edu.libot.response;

import edu.libot.entity.BookEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookListResponse extends BaseResponse {
    private Iterable<BookEntity> data;

    public BookListResponse(Iterable<BookEntity> data) {
        super(true, "Список книг");
        this.data = data;
    }

    @Override
    public String toString() {
        return "Книги из библиотеки\n" + data;
    }
}
