package com.example.reviewapp.controller;

import com.example.reviewapp.dto.ReviewRequest;
import com.example.reviewapp.exception.ReviewNotFoundException;
import com.example.reviewapp.model.Review;
import com.example.reviewapp.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('USER')")
    public String addForm(Model model) {
        model.addAttribute("review", new ReviewRequest());
        return "add";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('USER')")
    public String add(@Valid @ModelAttribute ReviewRequest reviewRequest, BindingResult result, Model model) throws Exception {
        reviewRequest.setUserId(407);
        if(result.hasErrors()){
            System.out.println(result.getFieldError("message"));
            return "add";
        }else{
            reviewService.save(reviewRequest);
            model.addAttribute("reviews", reviewService.findAll());
            model.addAttribute("success", "Review added with success");
            return "reviews";
        }
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMINISTRATOR', 'MODERATOR')")
    public String editForm(@PathVariable UUID id, Model model) throws Exception {
        Review review = reviewService.findById(id);
        model.addAttribute("review", review);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMINISTRATOR', 'MODERATOR')")
    public String edit(@PathVariable UUID id, @ModelAttribute ReviewRequest reviewRequest, Model model) throws Exception {
        reviewRequest.setId(id);
        reviewService.update(reviewRequest);
        model.addAttribute("success", "Review updated with success");
        return "redirect:/reviews";
    }

    @PostMapping("/report/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public String report(@PathVariable UUID id, Model model) throws Exception {
        reviewService.report(id);
        model.addAttribute("success", "Review reported with success");
        return "redirect:/reviews";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public String deleteItem(@PathVariable UUID id, Model model) throws ReviewNotFoundException {
        reviewService.delete(id);
        model.addAttribute("success", "Review deleted with success");
        return "redirect:/reviews";
    }


    @GetMapping()
    public String reviews(Model model) {
        model.addAttribute("reviews", reviewService.findAll());
        return "reviews";
    }

    @GetMapping("/reported")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public String reportedReviews(Model model) {
        model.addAttribute("reviews", reviewService.findAllReportedReviews());
        return "reported-reviews";
    }

}
