package com.example.demo.repo;

import com.example.demo.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepo extends JpaRepository<News, Long> {
    List<News> findAllByTitle(String title);
    List<News> findAllByOrderByCreationDateAsc();
    List<News> findAllByOrderByCreationDateDesc();
}
