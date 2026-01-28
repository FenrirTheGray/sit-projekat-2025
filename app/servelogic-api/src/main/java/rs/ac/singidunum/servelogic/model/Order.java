package rs.ac.singidunum.servelogic.model;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Document("order")
public class Order extends AbstractArangoEntity {

    private Date createdAt;
    private OrderStatus status = OrderStatus.ACCEPTED;

    @Transient
    private User user;

    private List<Product> products = new ArrayList<>();

    public Order(){super();}

    public Order(String id, String key, User user, Date createdAt, OrderStatus status, List<Product> products){
        super(id, key);
        this.user = user;
        this.createdAt = createdAt;
        this.status = status;
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public void addProducts(Product product) {
        this.products.add(product);
    }
    public void removeProducts(Product product) {
        this.products.remove(product);
    }
    public void removeProductsByIndex(int i) {
        try {
            this.products.remove(i);
        } catch (Exception e) {}
    }

    public double getTotal(){
        double total = 0.0;

        for(var p : products) total += p.calculatePrice();

        return total;
    }
}

