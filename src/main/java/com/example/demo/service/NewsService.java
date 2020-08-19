package com.example.demo.service;

import com.example.demo.entity.News;
import com.example.demo.repo.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class NewsService {
    @Autowired
    NewsRepo newsRepo;

    public void addNews(String title, String description, String author){
        News news = new News(title, description, author);
        news.setCreationDate(new Timestamp(System.currentTimeMillis()));
        news.setPublished(false);
        newsRepo.save(news);
    }

    public List<News> listAll(){
        return newsRepo.findAll();
    }

    public News findById(Long id){
        return newsRepo.findById(id).get();
    }
    public void setPublished1(Long id){
        News news = findById(id);
        news.setPublished(true);
        newsRepo.save(news);
    }

    public void update(Long id, String title, String description, String author){
        News news = findById(id);
        news.setTitle(title);
        news.setDescription(description);
        news.setAuthor(author);
        news.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        newsRepo.save(news);
    }

    public List<News> findByFilter(String filter){
        return newsRepo.findAllByTitle(filter);
    }

    public void delete(Long id){
        newsRepo.delete(findById(id));
    }

    public List<News> sortByDate(String ascDesc){
        switch (ascDesc) {
            case "asc":
                return newsRepo.findAllByOrderByCreationDateAsc();
            case "desc":
                return newsRepo.findAllByOrderByCreationDateDesc();
            default: return null;
        }
    }

}
