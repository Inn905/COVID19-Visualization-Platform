package com.ncov.ncov2019.Controller;

import com.ncov.ncov2019.Repository.updates_ncRepository;
import com.ncov.ncov2019.Repository.wordcloudRepository;
import com.ncov.ncov2019.domain.updates_nc;
import com.ncov.ncov2019.domain.wordcloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wordcloud")
public class wordcloudController {
    @Autowired
    private wordcloudRepository repository;

    @CrossOrigin(origins = "*")
    @GetMapping("/word")
    public List<wordcloud> list(){
        return repository.findAll();
    }
}
