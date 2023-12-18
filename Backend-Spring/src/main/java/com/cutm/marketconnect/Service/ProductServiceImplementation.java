package com.cutm.marketconnect.Service;

import com.cutm.marketconnect.Exception.ProductException;
import com.cutm.marketconnect.Model.Category;
import com.cutm.marketconnect.Model.Product;
import com.cutm.marketconnect.Repository.CategoryRepository;
import com.cutm.marketconnect.Repository.ProductRepository;
import com.cutm.marketconnect.Request.CreateProductRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService {

    private ProductRepository productRepository;

    private UserService userService;
    private CategoryRepository categoryRepository;

    ProductServiceImplementation(ProductRepository productRepository,UserService userService,CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.userService=userService;
        this.categoryRepository=categoryRepository;
    }
    @Override
    public Product createProduct(CreateProductRequest req) {

        Category topLevel=categoryRepository.findByName(req.getTopLevelCategory());
        if(topLevel==null){
          Category topLevelCategory=new Category();
            topLevelCategory.setName(req.getTopLevelCategory());
            topLevelCategory.setLevel(1);

            topLevel=categoryRepository.save(topLevelCategory);
        }

        Category secondLevel=categoryRepository.findByNameAndParent(req.getSecondLevelCategory(),topLevel.getName());
        if(secondLevel==null) {

            Category secondLavelCategory=new Category();
            secondLavelCategory.setName(req.getSecondLevelCategory());
            secondLavelCategory.setParentCategory(topLevel);
            secondLavelCategory.setLevel(2);

            secondLevel= categoryRepository.save(secondLavelCategory);
        }

        Category thirdLevel=categoryRepository.findByNameAndParent(req.getThirdLevelCategory(),secondLevel.getName());
        if(thirdLevel==null) {

            Category thirdLavelCategory=new Category();
            thirdLavelCategory.setName(req.getThirdLevelCategory());
            thirdLavelCategory.setParentCategory(secondLevel);
            thirdLavelCategory.setLevel(3);

            thirdLevel=categoryRepository.save(thirdLavelCategory);
        }

        Product product=new Product();
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setDescription(req.getDescription());
        product.setDiscountedPrice(req.getDiscountedPrice());
        product.setDiscountedPercent(req.getDiscountPercent());
        product.setImage(req.getImageUrl());
        product.setBrand(req.getBrand());
        product.setPrice(req.getPrice());
        product.setSizes(req.getSize());
        product.setQuantity(req.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product=findProductById(productId);
        product.getSizes().clear();
        productRepository.delete(product);
        return "Product deleted Successfully";
    }

    @Override
    public Product updateProduct(Long productId, Product req) throws ProductException {
        Product product=findProductById(productId);
        if(req.getQuantity()!=0){
            product.setQuantity(req.getQuantity());
        }
        return productRepository.save(product);
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @Override
    public Product findProductById(Long id) throws ProductException {
        Optional<Product> opt=productRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new ProductException("Product not fount by id"+id);
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        System.out.println("category --- "+category);
        List<Product> products = productRepository.findByCategory(category);
        return products;
    }

    @Override
    public List<Product> searchProduct(String query) {
        List<Product> products=productRepository.searchProduct(query);
        return products;
    }


    @Override
    public Page<Product> getAllProduct(String category, List<String> colour, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {


        Pageable pageable= PageRequest.of(pageNumber,pageSize);

        List<Product> products=productRepository.filterProducts(category,minPrice,maxPrice,minDiscount,sort);

        if(!colour.isEmpty()){
            products=products.stream().filter(p -> colour.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor())))
                    .collect(Collectors.toList());
        }
        if(stock!=null){
            if(stock.equals("in_stock")){
                products=products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
            } else if (stock.equals("out_stocks")) {
                products=products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
            }
            int startIndex=(int)pageable.getOffset();
            int endIndex=Math.min(startIndex+pageable.getPageSize(),products.size());

            List<Product> pageContent=products.subList(startIndex,endIndex);

            Page<Product> filteredProducts=new PageImpl<>(pageContent,pageable,products.size());
            return filteredProducts;
        }

        return null;
    }
}
