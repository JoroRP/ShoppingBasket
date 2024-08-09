package cart.shopping.shoppingcart.service.Impl;

import cart.shopping.shoppingcart.model.Basket;
import cart.shopping.shoppingcart.model.Product;
import cart.shopping.shoppingcart.repository.BasketRepository;
import cart.shopping.shoppingcart.repository.ProductRepository;
import cart.shopping.shoppingcart.service.BasketService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class BasketServiceImpl implements BasketService {

    private final ModelMapper modelMapper;
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;


    private Basket initBasket;

    @Autowired
    public BasketServiceImpl(ModelMapper modelMapper, BasketRepository basketRepository, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Basket getBasket() {
        return basketRepository.findAll().stream()
                .findFirst()
                .orElseGet(() -> basketRepository.save(new Basket()));
    }


    @Override
    public void addProductToBasket(UUID basketId, Long productId, int quantity) {

        Basket basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Basket not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<Product> existingProductOpt = basket.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst();

        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
        } else {
            product.setQuantity(quantity);
            basket.addProduct(product);
        }

        basketRepository.saveAndFlush(basket);
    }

    @Override
    public void removeProductFromBasket(UUID basketId, Long productId) {
        Basket basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Basket not found"));

        Optional<Product> existingProductOpt = basket.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst();

        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            basket.removeProduct(existingProduct);
        }

        basketRepository.save(basket);
    }

    @Override
    public Double calculateTotalPrice(UUID basketId) {
        Basket basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Basket not found"));

        return basket.getProducts().stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum();
    }


    }

