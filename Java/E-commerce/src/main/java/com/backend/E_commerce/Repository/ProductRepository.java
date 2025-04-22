package com.backend.E_commerce.Repository;

import com.backend.E_commerce.Entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE " +
            "(:cateogry IS NULL OR p.cateogry = :cateogry) AND " +
            "(:minPrice IS NULL OR p.discountedPrice >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.discountedPrice <= :maxPrice) AND " +
            "(:minDiscount IS NULL OR p.discountPercent >= :minDiscount)")
    public List<Product> filterProducts(@Param("cateogry")String cateogry, @Param("minPrice")Integer minPrice,
                                        @Param("maxPrice")Integer maxPrice, @Param("minDiscount") Integer minDiscount, Sort sort);

   @Query("SELECT p FROM Product p where p.id = :id")
    Optional<Product> findById(@Param("id")Long id);
}
