package rs.ac.singidunum.servelogic.model;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Document("order")
public class Order extends AbstractArangoEntity {

    private Date createdAt;
    private OrderStatus status = OrderStatus.CREATED;

    @Ref
    private User user;

    private List<Choice> choices = new ArrayList<>();

    public Order(){super();}

    public Order(String id, String key, User user, Date createdAt, OrderStatus status, List<Choice> choices){
        super(id, key);
        this.user = user;
        this.createdAt = createdAt;
        this.status = status;
        this.choices = choices;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Choice> getChoices() {
        return choices;
    }
    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
    public void addChoices(Choice choices) {
        this.choices.add(choices);
    }
    public void removeChoices(Choice choices) {
        this.choices.remove(choices);
    }
    public void removeChoicesByIndex(int i) {
        try {
            this.choices.remove(i);
        } catch (Exception e) {}
    }

    public double getTotal(){
        double total = 0.0;

        for(var c : choices) total += c.getTotal();

        return total;
    }
}

