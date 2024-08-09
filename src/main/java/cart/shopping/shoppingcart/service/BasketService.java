package cart.shopping.shoppingcart.service;

import cart.shopping.shoppingcart.model.Basket;
import cart.shopping.shoppingcart.model.Product;

import java.util.UUID;

public interface BasketService {

    Basket getBasket();
    void addProductToBasket(UUID basketId, Long productId, int quantity);
    void removeProductFromBasket(UUID basketId, Long productId);
    Double calculateTotalPrice(UUID basketId);

}
