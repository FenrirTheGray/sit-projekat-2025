package rs.ac.singidunum.servelogic.dto.response;

import rs.ac.singidunum.servelogic.model.OrderStatus;

import java.util.Date;
import java.util.List;

public class OrderResponseDTO {
    private String key;
    private Date createdAt;
    private OrderStatus status;
    private UserResponseDTO user;
    private List<ChoiceResponseDTO> choices;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    public List<ChoiceResponseDTO> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceResponseDTO> choices) {
        this.choices = choices;
    }
}
