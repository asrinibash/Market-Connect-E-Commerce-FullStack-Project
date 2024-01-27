package com.cutm.marketconnect.service;

import com.cutm.marketconnect.exception.CartItemException;
import com.cutm.marketconnect.exception.UserException;
import com.cutm.marketconnect.modal.Cart;
import com.cutm.marketconnect.modal.CartItem;
import com.cutm.marketconnect.modal.Product;

public interface CartItemService {
	
	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId, Long id,CartItem cartItem) throws CartItemException, UserException;
	
	public CartItem isCartItemExist(Cart cart,Product product,String size, Long userId);
	
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
	
}
