package com.cutm.marketconnect.service;

import com.cutm.marketconnect.exception.ProductException;
import com.cutm.marketconnect.modal.Cart;
import com.cutm.marketconnect.modal.User;
import com.cutm.marketconnect.request.AddItemRequest;

public interface CartService {
	
	public Cart createCart(User user);
	
	public String addCartItem(Long userId,AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);

}
