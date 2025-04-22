package com.backend.E_commerce.ServiceImpl;

import com.backend.E_commerce.Entity.Cateogry;
import com.backend.E_commerce.Entity.Product;
import com.backend.E_commerce.Repository.CateogryReository;
import com.backend.E_commerce.Repository.ProductRepository;
import com.backend.E_commerce.request.CreateProductRequest;
import com.backend.E_commerce.Service.ProductService;
import com.backend.E_commerce.Service.UserService;
import com.backend.E_commerce.exception.ProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private UserService userService;
    private CateogryReository cateogryReository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserService userService,CateogryReository cateogryReository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.cateogryReository = cateogryReository;
    }
    @Override
    public Product createProduct(CreateProductRequest req) {
        Cateogry topLevel = cateogryReository.findByName(req.getTopLevelCateogry());
        if(topLevel == null){
                Cateogry topLevelCateogry = new Cateogry();
                topLevelCateogry.setName(req.getTopLevelCateogry());
                topLevelCateogry.setLevel(1);
                topLevel = cateogryReository.save(topLevelCateogry);
        }
        Cateogry secondLevel = cateogryReository.findByNameAndParent(req.getSecondLevelCateogry(),topLevel.getName());
        if(secondLevel == null){
            Cateogry secondLevelCateogry = new Cateogry();
            secondLevelCateogry.setName(req.getSecondLevelCateogry());
            secondLevelCateogry.setLevel(2);
            secondLevelCateogry.setParent_cateogry(topLevel);
            secondLevel = cateogryReository.save(secondLevelCateogry);
        }

        Cateogry thirdLevel = cateogryReository.findByNameAndParent(req.getThirdLevelCateogry(),secondLevel.getName());
        if(thirdLevel == null){
            Cateogry thirdLevelCateogry = new Cateogry();
            thirdLevelCateogry.setName(req.getThirdLevelCateogry());
            thirdLevelCateogry.setLevel(3);
            thirdLevelCateogry.setParent_cateogry(secondLevel);
            thirdLevel = cateogryReository.save(thirdLevelCateogry);
        }
        Product product = new Product();
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setDescription(req.getDesription());
        product.setDiscountedPrice(req.getDiscountedPrice());
        product.setDiscountPercent(req.getDiscountPercent());
        product.setImageUrl(req.getImageUrl());
        product.setBrand(req.getBrand());
        product.setSizes(req.getSize());
        product.setQuantity(req.getQuantity());
        product.setCateogry(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product  = findProductById(productId);
        product.getSizes().clear();
        productRepository.delete(product);
        return "Product Deleted SuccessFully";
    }

    @Override
    public Product updateProduct(Long productId, Product req) throws ProductException {
        Product product  = findProductById(productId);
        if(req.getQuantity()!=0){
            product.setQuantity(req.getQuantity());
        productRepository.save(product);
        }
        return null;
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent())
            return product.get();
        throw new ProductException("Product not found with id - "+id);
    }

    @Override
    public List<Product> findproductByCateogry(String cateogry) {
        return List.of();
    }

    @Override
    public Page<Product> getAllProduct(String cateogry, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNum, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNum,pageSize);

        Sort sort1 = null;
        if(sort.equalsIgnoreCase("price_high")){
             sort1 =Sort.by(Sort.Direction.DESC,"discountedPrice");}
        else{
             sort1 =Sort.by(Sort.Direction.ASC,"discountedPrice");
        }
        List<Product> products = productRepository.filterProducts(cateogry,minPrice,maxPrice,minDiscount,sort1);
//        if(!colors.isEmpty()){
//            products = products.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).
//                    collect(Collectors.toList());
//        }
//        if(stock!=null){
//           if(stock.equals("in_stock"))
//               products=products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
//           else if(stock.equals("out_of_stock"))
//               products=products.stream().filter(p-> p.getQuantity()<1).collect(Collectors.toList());
//        }
        int startIndex =(int)pageable.getOffset();
        int endIndex = Math.min(startIndex+pageable.getPageSize(),products.size());
        List<Product> pageContent = products.subList(startIndex,endIndex);
        Page<Product> filteredProducts = new PageImpl<>(pageContent,pageable,products.size());
        return filteredProducts;
    }
}
