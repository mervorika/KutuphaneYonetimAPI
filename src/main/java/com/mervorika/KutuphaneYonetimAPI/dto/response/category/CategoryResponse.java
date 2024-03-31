package com.mervorika.KutuphaneYonetimAPI.dto.response.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.mervorika.KutuphaneYonetimAPI.entities.Book;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private int id;
    private String name;
    private String description;
    private List<Book> bookList;
}
