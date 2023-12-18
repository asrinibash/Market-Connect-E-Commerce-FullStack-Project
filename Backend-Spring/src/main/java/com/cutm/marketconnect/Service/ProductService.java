package com.cutm.marketconnect.Service;

import com.cutm.marketconnect.Exception.ProductException;
import com.cutm.marketconnect.Model.Product;
import com.cutm.marketconnect.Request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

     //for admin
     public Product createProduct(CreateProductRequest req);
     public String deleteProduct(Long productId) throws ProductException;

     public Product updateProduct(Long productId, Product req) throws ProductException;
     public List<Product> getAllProducts();

     //for User and admin both

     public Product findProductById(Long id)throws ProductException;
     public List<Product> findProductByCategory(String category);


     public List<Product> searchProduct(String query);

     public Page<Product> getAllProduct(String category,List<String> colour,List<String> sizes,
              Integer minPrice,Integer maxPrice,Integer minDiscount,String sort,String stock,
              Integer pageNumber,Integer pageSize);
}
