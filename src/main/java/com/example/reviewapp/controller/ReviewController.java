package com.example.reviewapp.controller;

import com.example.reviewapp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/add")
    public String add() {
        return "add";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable String id) {
        return "edit";
    }

    @GetMapping("/{id}")
    public String review(@PathVariable String id) {
        return "review";
    }

    @GetMapping()
    public String reviews() {
        return "reviews";
    }

}
