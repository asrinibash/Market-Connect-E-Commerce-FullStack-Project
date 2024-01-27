package com.cutm.marketconnect.service;

import com.cutm.marketconnect.exception.ProductException;
import com.cutm.marketconnect.modal.Rating;
import com.cutm.marketconnect.modal.User;
import com.cutm.marketconnect.request.RatingRequest;

import java.util.List;

public interface RatingServices {
	
	public Rating createRating(RatingRequest req,User user) throws ProductException;
	
	public List<Rating> getProductsRating(Long productId);

}
