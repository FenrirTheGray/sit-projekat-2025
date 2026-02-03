package sitprojekat.dto;



import java.util.List;

import sitprojekat.model.OrderStatus;

public class OrderCreateRequestDTO {
    private OrderStatus status;
    private List<ChoiceCreateRequestDTO> choices;

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