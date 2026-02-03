package rs.ac.singidunum.servelogic.dto.update;

import rs.ac.singidunum.servelogic.dto.create.ChoiceCreateRequestDTO;
import rs.ac.singidunum.servelogic.model.OrderStatus;

import java.util.Date;
import java.util.List;

public class OrderUpdateRequestDTO {
    private String key;
    private Date createdAt;
    private OrderStatus status;
    private List<ChoiceCreateRequestDTO> choices;

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
    public List<ChoiceCreateRequestDTO> getChoices() {
        return choices;
    }
    public void setChoices(List<ChoiceCreateRequestDTO> choices) {
        this.choices = choices;
    }
}
