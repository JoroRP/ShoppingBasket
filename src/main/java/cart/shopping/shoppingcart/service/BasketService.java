package cart.shopping.shoppingcart.service;

import cart.shopping.shoppingcart.model.Basket;
import cart.shopping.shoppingcart.model.Product;

import java.util.UUID;

public interface BasketService {

    Basket getBasket();
    Basket addProductToBasket(UUID basketId, Product product, int quantity);

}
