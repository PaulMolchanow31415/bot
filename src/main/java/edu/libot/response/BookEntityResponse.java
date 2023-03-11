package edu.libot.response;

import edu.libot.entity.BookEntity;
import lombok.Data;

@Data
public class BookEntityResponse extends BaseResponse {
    private BookEntity data;

    public BookEntityResponse(boolean success, String message, BookEntity data) {
        super(success, message);
        this.data = data;
    }
}
