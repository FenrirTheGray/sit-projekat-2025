package rs.ac.singidunum.servelogic.dto.response;

import rs.ac.singidunum.servelogic.dto.AbstractArangoDTO;
import rs.ac.singidunum.servelogic.model.Choice;
import rs.ac.singidunum.servelogic.model.OrderStatus;

import java.util.List;

public class OrderResponseDTO extends AbstractArangoDTO {
    private String createdAt;
    private OrderStatus status;
    private UserResponseDTO user;
    private List<Choice> choices;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
