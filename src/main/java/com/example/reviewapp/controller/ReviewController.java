package com.example.reviewapp.controller;
import com.example.reviewapp.dto.ReactionRequest;
import com.example.reviewapp.dto.ReviewRequest;
import com.example.reviewapp.exception.ReviewNotFoundException;
import com.example.reviewapp.model.Review;
import com.example.reviewapp.security.SecurityUser;
import com.example.reviewapp.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

@PostMapping("/like/{id}")
@PreAuthorize("hasAnyAuthority('USER', 'ADMINISTRATOR', 'MODERATOR')")
public String likeItem(@PathVariable UUID id, @ModelAttribute ReactionRequest reactionRequest) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
    long userId = securityUser.getUserId();
    reactionRequest.setUserId(userId);
    reactionRequest.setReviewId(id);
    reviewService.like(reactionRequest);
    return "redirect:/reviews";
}


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('USER')")
    public String add(@Valid @ModelAttribute ReviewRequest reviewRequest, BindingResult result, Model model) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        long userId = securityUser.getUserId();
        reviewRequest.setUserId(userId);
        if(result.hasErrors()){
            System.out.println(result.getFieldError("message"));
            return "add";
        }else{
            reviewService.save(reviewRequest);
            model.addAttribute("reviews", reviewService.findAll());
            model.addAttribute("success", "Review added with success");
            return "redirect:/reviews";
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
    public String edit(@PathVariable UUID id, @ModelAttribute ReviewRequest reviewRequest, @ModelAttribute("review") Review review, Model model) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        long userId = securityUser.getUserId();
        reviewRequest.setUserId(userId);
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

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public String deleteItem(@PathVariable UUID id, Model model) {
        try {
            reviewService.delete(id);
            model.addAttribute("success", "Review deleted with success");
        } catch (ReviewNotFoundException e) {
            model.addAttribute("error", "Review not found");
        }
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
