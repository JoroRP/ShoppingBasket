package cart.shopping.shoppingcart.repository;

import cart.shopping.shoppingcart.model.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketProductRepository extends JpaRepository<BasketProduct,Long> {
}
