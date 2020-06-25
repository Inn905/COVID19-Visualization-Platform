package com.ncov.ncov2019.Controller;

import com.ncov.ncov2019.Repository.News_Repository;
import com.ncov.ncov2019.domain.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
public class News_Controller {
    @Autowired
    private News_Repository repository;

    @CrossOrigin(origins = "*")
    @GetMapping("/content")
    public List<News> list(){return repository.findAll();}

}
