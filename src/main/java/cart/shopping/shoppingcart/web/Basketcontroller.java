package cart.shopping.shoppingcart.web;

import cart.shopping.shoppingcart.service.BasketService;
import cart.shopping.shoppingcart.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basket")
public class Basketcontroller {

    private final BasketService basketService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public Basketcontroller(BasketService basketService, ProductService productService, ModelMapper modelMapper) {
        this.basketService = basketService;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/basket")
    public String viewBasket() {

        return "Basket";
    }
}
