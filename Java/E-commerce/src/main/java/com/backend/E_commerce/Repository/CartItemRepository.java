package com.backend.E_commerce.Repository;

import com.backend.E_commerce.Entity.Cart;
import com.backend.E_commerce.Entity.CartItem;
import com.backend.E_commerce.Entity.Product;
import com.backend.E_commerce.Entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    @Query("SELECT c FROM CartItem c WHERE c.cart=:cart and c.product =: product and c.size = :size and c.userId =:userId")
    public CartItem isCartItemExist(@Param("cart")Cart cart, @Param("product")Product product, @Param("size")String size,@Param("userId")Long userId);

}
