package com.example.affablebeanui.controller;

import com.example.affablebeanui.data.*;
import com.example.affablebeanui.exception.DeliveryError;
import com.example.affablebeanui.exception.PaymentError;
import com.example.affablebeanui.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ui")
public class ProductController {
    private final ProductService productService;


    @GetMapping("/checkout/form")
    public String checkoutForm(Model model){
        model.addAttribute("userInfo",new UserInfo());
        return "checkout";
    }
    @PostMapping("/checkout/form")
    public String processCheckoutForm(@ModelAttribute("total")double total, UserInfo userInfo,
                                      BindingResult result){
        if(result.hasErrors()){
            return "checkout";
        }
        double deliveryCharge = userInfo.getLocation().getPrice();
        double amount = deliveryCharge + total;
        var accountRequest=new AccountRequest(userInfo.getCreditCardNumber(),
                amount);

        var response=productService.makePayment(accountRequest);
        if(response.getStatusCode().is2xxSuccessful()){
            List<ProductRequest> list=productService.getCartItems()
                    .stream()
                    .map(c -> new ProductRequest(c.getName(),c.getPrice(),
                            c.getQuantity()))
                    .collect(Collectors.toList());
            var request = new DeliveryRequest(
                    userInfo.getName(),
                    userInfo.getEmail(),
                    userInfo.getPhone(),
                    userInfo.getAddress(),
                    amount,
                    userInfo.getCreditCardNumber(),
                    list
            );
            var resp=productService.makeDelivery(request);
            if(resp.getStatusCode().is2xxSuccessful()){
                this.orderId = resp.getBody();
                productService.clearCart();
            }
            else{
                throw new DeliveryError();
            }

        }
        else{
            throw new PaymentError();
        }


        return "redirect:/ui/checkout/info";
    }

    private String orderId;
    @GetMapping("/checkout/info")
    public String showCheckoutInfo(Model model){
         model.addAttribute("orderId",orderId);
         return "checkoutInfo";
    }


    @PostMapping("/checkout")
    public String proceedToCheckOut(CartItem cartItem){

        productService.updateCartItem(cartItem);


        productService.getCartItems()
                .forEach(System.out::println);
        return "redirect:/ui/view-cart";
    }
    @GetMapping("/cart/remove")
    public String removeItemFromCart(@RequestParam("id")int id){
        productService.removeFromCart(id);
        return "redirect:/ui/view-cart";
    }

    @GetMapping("/clear-cart")
    public String clearCart(){
        productService.clearCart();
        return "redirect:/ui/view-cart";
    }

    @GetMapping("/view-cart")
    public String viewCart(Model model){
        model.addAttribute("cartItem",
                new CartItem());
        model.addAttribute("cartItemForQuantity",
                productService.getCartItems()
                        .stream().findFirst().orElse(new CartItem()));
        model.addAttribute("cartItems",
                productService.getCartItems());
        return "cartView";
    }
    @GetMapping("/products")
    public String listProducts(@RequestParam("id")int id, Model model){
        model.addAttribute("products",
                productService.findProductsByCategoryId(id));
        return "products";
    }
    @GetMapping("/add-cart")
    public String addToCart(@RequestParam("id")int id){
        ProductDto productDto=productService
                .findProductsByProductId(id);
        productService.addToCart(productDto);
        return "redirect:/ui/products?id="+ productDto.getCategory_id();
    }

    @GetMapping({"/home"})
    public String index(){
        return "index";
    }

    @ResponseBody
    @GetMapping("/test")
    public List<CategoryDto> test(){
        return productService.listAllCategory();
    }
    @ModelAttribute("cartSize")
    public int cartSize(){
        return productService.cartSize();
    }

   // @ModelAttribute("locationList")
    public List<String> locationList(){
        return Arrays.stream(Location.values())
                .map(l -> l.name())
                .collect(Collectors.toList());
    }

    @ModelAttribute("total")
    public String totalPrice(){
        double total= productService
                .getCartItems()
                .stream()
                .map( c -> c.getPrice() * c.getQuantity())
                .mapToDouble(Double::doubleValue)
                .sum();
        return String.format("%.2f",total);
    }


}












