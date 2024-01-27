package com.cutm.marketconnect.service;

import com.cutm.marketconnect.exception.ProductException;
import com.cutm.marketconnect.modal.Review;
import com.cutm.marketconnect.modal.User;
import com.cutm.marketconnect.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

	public Review createReview(ReviewRequest req,User user) throws ProductException;
	
	public List<Review> getAllReview(Long productId);
	
	
}
