package cart.shopping.shoppingcart.service;

import cart.shopping.shoppingcart.model.Basket;
import cart.shopping.shoppingcart.model.BasketProduct;
import cart.shopping.shoppingcart.model.Product;

import java.util.UUID;

public interface BasketService {

    Basket addProductToBasket(UUID basketId, Product product, int quantity);

}
