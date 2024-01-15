package com.example.affablebeanui.data;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Getter
public class CartBean {
    private Set<CartItem> cartItems=
            new HashSet<>();

    public void updateCartItem(CartItem cartItem) {
        int counter=0;
        for(CartItem cart:cartItems){
            cart.setQuantity(cartItem.getQuantityList()
                    .get(counter));
            counter++;
        }
    }
    public void addToCart(CartItem cartItem){
        this.cartItems.add(cartItem);
    }
    public int cartSize(){
        return cartItems.size();
    }
    public void clearCart(){
        cartItems.clear();
    }
    public void removeFromCart(CartItem cartItem){
        cartItems.remove(cartItem);
    }

    public void removeItem(CartItem item) {
        cartItems.remove(item);
    }


}
