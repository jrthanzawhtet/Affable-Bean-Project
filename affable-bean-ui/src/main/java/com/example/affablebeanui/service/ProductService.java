package com.example.affablebeanui.service;

import com.example.affablebeanui.config.DeliveryFeignClient;
import com.example.affablebeanui.config.PaymentFeignClient;
import com.example.affablebeanui.config.ProductFeignClient;
import com.example.affablebeanui.data.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CartBean cartBean;
    private final ProductFeignClient productFeignClient;
    private final PaymentFeignClient paymentFeignClient;
    private final DeliveryFeignClient deliveryFeignClient;

    public void removeFromCart(int id) {
        cartBean.removeItem(findById(id));
    }

    public ResponseEntity<String> makeDelivery(DeliveryRequest deliveryRequest){
        return deliveryFeignClient.makeDelivery(deliveryRequest);
    }

    private CartItem findById(int id){
        return getCartItems()
                .stream()
                .filter(c  -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        id + " Not Found."));
    }

    public Set<CartItem> getCartItems(){
        return cartBean.getCartItems();
    }
    public void addToCart(ProductDto productDto){
        cartBean.addToCart(toCartItem(productDto));
    }
    public ProductDto findProductsByProductId(int id){
        return listAllProduct()
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        id + " Not Found"
                ));
    }
    public int cartSize(){
        return cartBean.cartSize();
    }
    private CartItem toCartItem(ProductDto productDto){
        return new CartItem(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                0
        );
    }

    public List<ProductDto> listAllProduct(){
        return productFeignClient.findAllProducts();
    }

    public List<CategoryDto> listAllCategory(){
        return productFeignClient.findAllCategory();
    }

    public List<ProductDto> findProductsByCategoryId(int id){
        return listAllProduct()
                .stream()
                .filter(p -> p.getCategory_id() == id)
                .collect(Collectors.toList());
    }


    public void clearCart() {
        cartBean.clearCart();
    }

    public ResponseEntity makePayment(AccountRequest request){
        return paymentFeignClient.makePayment(request);
    }


    public void updateCartItem(CartItem cartItem) {
        cartBean.updateCartItem(cartItem);
    }
}
