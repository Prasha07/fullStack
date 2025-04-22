package com.backend.E_commerce.request;

import com.backend.E_commerce.Entity.Size;

import java.util.HashSet;
import java.util.Set;


public class CreateProductRequest {
   private  String title;
   private  String description;
   private Integer price;
   private Integer discountedPrice;
   private Integer discountPercent;
   private Integer quantity;
   private String brand ;
   private String color ;
   private Set<Size> size = new HashSet<>();
   private String imageUrl;
   private String topLevelCateogry;
   private String secondLevelCateogry;
   private String thirdLevelCateogry;

   public CreateProductRequest(){

   }

   public CreateProductRequest(String title, String description, Integer price, Integer discountedPrice, Integer discountPercent, Integer quantity, String brand, String color, Set<Size> size, String imageUrl, String topLevelCateogry, String secondLevelCateogry, String thirdLevelCateogry) {
      this.title = title;
      this.description = description;
      this.price = price;
      this.discountedPrice = discountedPrice;
      this.discountPercent = discountPercent;
      this.quantity = quantity;
      this.brand = brand;
      this.color = color;
      this.size = size;
      this.imageUrl = imageUrl;
      this.topLevelCateogry = topLevelCateogry;
      this.secondLevelCateogry = secondLevelCateogry;
      this.thirdLevelCateogry = thirdLevelCateogry;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDesription() {
      return description;
   }

   public void setDesription(String description) {
      this.description = description;
   }

   public Integer getPrice() {
      return price;
   }

   public void setPrice(Integer price) {
      this.price = price;
   }

   public Integer getDiscountedPrice() {
      return discountedPrice;
   }

   public void setDiscountedPrice(Integer discountedPrice) {
      this.discountedPrice = discountedPrice;
   }

   public Integer getDiscountPercent() {
      return discountPercent;
   }

   public void setDiscountPercent(Integer discountPercent) {
      this.discountPercent = discountPercent;
   }

   public Integer getQuantity() {
      return quantity;
   }

   public void setQuantity(Integer quantity) {
      this.quantity = quantity;
   }

   public String getBrand() {
      return brand;
   }

   public void setBrand(String brand) {
      this.brand = brand;
   }

   public String getColor() {
      return color;
   }

   public void setColor(String color) {
      this.color = color;
   }

   public Set<Size> getSize() {
      return size;
   }

   public void setSize(Set<Size> size) {
      this.size = size;
   }

   public String getImageUrl() {
      return imageUrl;
   }

   public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
   }

   public String getTopLevelCateogry() {
      return topLevelCateogry;
   }

   public void setTopLevelCateogry(String topLevelCateogry) {
      this.topLevelCateogry = topLevelCateogry;
   }

   public String getSecondLevelCateogry() {
      return secondLevelCateogry;
   }

   public void setSecondLevelCateogry(String secondLevelCateogry) {
      this.secondLevelCateogry = secondLevelCateogry;
   }

   public String getThirdLevelCateogry() {
      return thirdLevelCateogry;
   }

   public void setThirdLevelCateogry(String thirdLevelCateogry) {
      this.thirdLevelCateogry = thirdLevelCateogry;
   }
}
