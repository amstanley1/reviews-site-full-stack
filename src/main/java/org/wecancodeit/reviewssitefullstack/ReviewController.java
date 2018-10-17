package org.wecancodeit.reviewssitefullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ReviewController {
	
	@Resource
	CategoryRepository categoryRepo;
	
	@Resource
	ReviewRepository reviewRepo;
	
	@RequestMapping("/category")
	public String findOneCategory(@RequestParam(value="id")long id, Model model) throws CategoryNotFoundException {
		Optional<Category> category = categoryRepo.findById(id);
		
		if(category.isPresent()) {
			model.addAttribute("category", category.get());
			return "category";
		}
		throw new CategoryNotFoundException();
	}
	
	@RequestMapping("/show-categories")
	public String findAllCategories(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		return "categories";
	}
	
	@RequestMapping("/review")
	public String findOneReview(@RequestParam(value="id")long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);
		if(review.isPresent()) {
			model.addAttribute("review", review.get());
			model.addAttribute("category", review.get().getCategory());
			
			return "review";
		}
		throw new ReviewNotFoundException();
		
	}

	@RequestMapping("/show-reviews")
	public String findAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return "reviews";
	}
	
	

}
