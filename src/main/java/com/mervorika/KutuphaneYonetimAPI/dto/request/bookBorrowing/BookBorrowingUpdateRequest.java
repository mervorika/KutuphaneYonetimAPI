package com.mervorika.KutuphaneYonetimAPI.dto.request.bookBorrowing;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingUpdateRequest {
    @Positive
    private int id;
    @NotNull
    private String borrowerName;
    @NotNull
    private LocalDate borrowingDate;
    @NotNull
    private LocalDate returnDate;
    @NotNull
    private int book_id;
}
