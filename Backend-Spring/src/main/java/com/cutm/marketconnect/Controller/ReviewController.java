package com.cutm.marketconnect.controller;

import com.cutm.marketconnect.exception.ProductException;
import com.cutm.marketconnect.exception.UserException;
import com.cutm.marketconnect.modal.Review;
import com.cutm.marketconnect.modal.User;
import com.cutm.marketconnect.request.ReviewRequest;
import com.cutm.marketconnect.service.ReviewService;
import com.cutm.marketconnect.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
	
	private ReviewService reviewService;
	private UserService userService;
	
	public ReviewController(ReviewService reviewService,UserService userService) {
		this.reviewService=reviewService;
		this.userService=userService;

	}
	@PostMapping("/create")
	public ResponseEntity<Review> createReviewHandler(@RequestBody ReviewRequest req,@RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		User user=userService.findUserProfileByJwt(jwt);
		System.out.println("product id "+req.getProductId()+" - "+req.getReview());
		Review review=reviewService.createReview(req, user);
		System.out.println("product review "+req.getReview());
		return new ResponseEntity<Review>(review,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>> getProductsReviewHandler(@PathVariable Long productId){
		List<Review>reviews=reviewService.getAllReview(productId);
		return new ResponseEntity<List<Review>>(reviews,HttpStatus.OK);
	}

}
