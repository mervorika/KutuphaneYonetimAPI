package com.mervorika.KutuphaneYonetimAPI.dto.request.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveRequest {

    private String name;
    private int stock;
    private int publicationYear;
    private int author_Id;
    private int publisherId;

}
