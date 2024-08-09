package cart.shopping.shoppingcart.service.Impl;

import cart.shopping.shoppingcart.model.Basket;
import cart.shopping.shoppingcart.model.BasketProduct;
import cart.shopping.shoppingcart.model.Product;
import cart.shopping.shoppingcart.repository.BasketProductRepository;
import cart.shopping.shoppingcart.repository.BasketRepository;
import cart.shopping.shoppingcart.service.BasketService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class BasketServiceImpl implements BasketService {

    private final ModelMapper modelMapper;
    private final BasketRepository basketRepository;
    private final BasketProductRepository basketProductRepository;

    private Basket initBasket;

    public BasketServiceImpl(ModelMapper modelMapper, BasketRepository basketRepository, BasketProductRepository basketProductRepository) {
        this.modelMapper = modelMapper;
        this.basketRepository = basketRepository;
        this.basketProductRepository = basketProductRepository;
    }

    private Basket initBasket() {
        return basketRepository.findAll().stream().findFirst().orElseGet(() -> {
            Basket initBasket = new Basket();
            return basketRepository.saveAndFlush(initBasket);
        });
    }


    @Override
    public Basket addProductToBasket(UUID basketId, Product product, int quantity) {
        if (initBasket == null) {
            initBasket = initBasket();
        }

        Basket basket = basketRepository.findById(initBasket.getId()).orElse(initBasket);



        if(basket == null) {
            Basket basketCreate = new Basket();
            return this.basketRepository.saveAndFlush(basketCreate);
        }
            BasketProduct basketProduct = new BasketProduct();
            basketProduct.setProduct(product);
            basketProduct.setQuantity(quantity);
            basketProduct.setBasket(basket);

            basket.getProducts().add(basketProduct);
            basketProductRepository.save(basketProduct);
            basketRepository.save(basket);

            return basket;
        }


    }

