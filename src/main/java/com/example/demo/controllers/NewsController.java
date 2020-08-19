package com.example.demo.controllers;

import com.example.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping("/")
    public String showNews(@RequestParam(required = false) String filter,
                           @RequestParam(required = false) String asc,
                           @RequestParam(required = false) String desc,
                           Model model){
        if(asc != null){
            model.addAttribute("news",newsService.sortByDate(asc));
        }else if(desc != null){
            model.addAttribute("news", newsService.sortByDate(desc));
        }else if(filter != null && !filter.equals("") ){
            model.addAttribute("news", newsService.findByFilter(filter));
        }else {
            model.addAttribute("news", newsService.listAll());
        }
        return "home";
    }

    @GetMapping("/edit/{id}")
    public String editShow(){

        return "edit";
    }

    @PostMapping("/edit/{id}")
        public String editNews(@PathVariable Long id,
                               @RequestParam String title,
                               @RequestParam String description,
                               @RequestParam String author){
        newsService.update(id, title, description, author);
        return "redirect:../";
    }

    @GetMapping("/{id}")
    public String setPublishedd(@PathVariable(required = false) Long id, Model model){
        if(id != null){
            newsService.setPublished1(id);
        }
        model.addAttribute("news", newsService.listAll());
        return "home";
    }

    @GetMapping("/new")
    public String showAdd(){
        return "new";
    }

    @PostMapping("/new")
    public String addNews(@RequestParam String title,
                          @RequestParam String description,
                          @RequestParam String author
                          ){
        newsService.addNews(title, description, author);
        return "redirect:";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        newsService.delete(id);
        return "redirect:../";
    }

}
