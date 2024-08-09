package cart.shopping.shoppingcart.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Basket")
@Transactional
public class Basket {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @Column(name = "total_amount")
    private Double totalPrice;

    public Basket() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.setBasket(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setBasket(null);
    }
}
