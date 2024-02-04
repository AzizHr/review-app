package com.example.reviewapp.controller;

import com.example.reviewapp.dto.ReviewRequest;
import com.example.reviewapp.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

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


    @PostMapping("/add")
    public String save(@Valid @ModelAttribute ReviewRequest reviewRequest, BindingResult result) throws Exception {
        if(result.hasErrors()){
            System.out.println(result.getFieldError("message"));
            return "add";
        }else{
            reviewService.save(reviewRequest);
            return "reviews";
        }
    }
}
