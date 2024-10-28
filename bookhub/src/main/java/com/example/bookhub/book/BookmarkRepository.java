package com.example.bookhub.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findAllByUserId(String userId);

    boolean existsByBookIdAndUserId(Long bookId, String userId);
}
