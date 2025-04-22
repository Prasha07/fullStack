package com.backend.E_commerce.Service;

import com.backend.E_commerce.Entity.Product;
import com.backend.E_commerce.request.CreateProductRequest;
import com.backend.E_commerce.exception.ProductException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product createProduct(CreateProductRequest req);
    public String deleteProduct(Long productId) throws ProductException;
    public Product updateProduct(Long productId,Product product) throws ProductException;
    public Product findProductById(Long id) throws ProductException;
    public List<Product> findproductByCateogry(String cateogry);
    public Page<Product> getAllProduct(String cateogry,List<String> colors,List<String> sizes,Integer minPrice,Integer maxPrice,
        Integer minDiscount,String sort,String stock,Integer pageNum,Integer pageSize);



}
