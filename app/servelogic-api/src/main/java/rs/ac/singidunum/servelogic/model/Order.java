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
    @Ref
    private List<Product> products = new ArrayList<>();

    public Order(){super();}

    public Order(User user){
        super();
    }

    public double getTotal(){
        double total = 0.0;

        for(var p : products) total += p.calculatePrice();

        return total;
    }
}

