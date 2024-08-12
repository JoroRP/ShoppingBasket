package cart.shopping.shoppingcart.service.Impl;

import cart.shopping.shoppingcart.model.Basket;
import cart.shopping.shoppingcart.model.Product;
import cart.shopping.shoppingcart.repository.ProductRepository;
import cart.shopping.shoppingcart.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public void addProductsStart() {
        if (this.productRepository.count() == 0) {
            Product product1 = new Product();
            product1.setName("Water");
            product1.setDescription("You need this to stay hydrated");
            product1.setPrice(1.00);


            this.productRepository.saveAndFlush(product1);
        }
    }
}
