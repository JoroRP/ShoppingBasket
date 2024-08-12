package cart.shopping.shoppingcart.init;

import cart.shopping.shoppingcart.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {

    private final ProductService productService;

    public DataInit(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.productService.addProductsStart();
    }
}
