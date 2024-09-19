package com.example.bookhub.feedback;

import com.example.bookhub.book.Book;
import com.example.bookhub.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback extends BaseEntity {

    private Double rating;
    private String comment;
    
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
