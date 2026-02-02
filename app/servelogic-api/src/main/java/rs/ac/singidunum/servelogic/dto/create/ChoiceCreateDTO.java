package rs.ac.singidunum.servelogic.dto.create;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "className"
)
@JsonSubTypes({
        @JsonSubTypes.Type( value = ArticleChoiceCreateDTO.class, name = "articleDTO"),
        @JsonSubTypes.Type( value = ComboChoiceCreateDTO.class, name = "comboDTO")
})
public class ChoiceCreateDTO {
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
