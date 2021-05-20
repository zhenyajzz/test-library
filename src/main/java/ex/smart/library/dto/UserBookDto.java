package ex.smart.library.dto;

import lombok.Data;

@Data
public class UserBookDto {

    private Long userId;

    private String username;

    private String bookName;

    private String category;
}
