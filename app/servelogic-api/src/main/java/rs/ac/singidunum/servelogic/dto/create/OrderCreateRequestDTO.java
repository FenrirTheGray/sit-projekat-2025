package rs.ac.singidunum.servelogic.dto.create;

import rs.ac.singidunum.servelogic.model.OrderStatus;

import java.util.List;

public class OrderCreateRequestDTO {
    private OrderStatus status;
    private List<ChoiceCreateDTO> choices;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<ChoiceCreateDTO> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceCreateDTO> choices) {
        this.choices = choices;
    }
}
