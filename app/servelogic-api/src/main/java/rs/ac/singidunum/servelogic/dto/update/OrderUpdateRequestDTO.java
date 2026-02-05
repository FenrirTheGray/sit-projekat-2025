package rs.ac.singidunum.servelogic.dto.update;

import rs.ac.singidunum.servelogic.dto.AbstractArangoDTO;
import rs.ac.singidunum.servelogic.dto.create.ChoiceCreateRequestDTO;
import rs.ac.singidunum.servelogic.model.OrderStatus;
import java.util.Date;
import java.util.List;

public class OrderUpdateRequestDTO extends AbstractArangoDTO {
    private Date createdAt;
    private OrderStatus status;
    private List<ChoiceCreateRequestDTO> choices;

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
