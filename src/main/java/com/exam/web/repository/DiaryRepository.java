package com.exam.web.repository;

import com.exam.web.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary,Long> {
    Optional<Diary> findByTitle(String url);
    @Query("SELECT c from Diary c Where c.title LIKE CONCAT('%', :query,'%')")
    List<Diary> searchDiaries(String query);
}
