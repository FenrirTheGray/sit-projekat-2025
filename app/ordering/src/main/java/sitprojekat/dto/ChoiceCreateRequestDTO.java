package sitprojekat.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "className"
)
@JsonSubTypes({
        @JsonSubTypes.Type( value = ArticleChoiceCreateRequestDTO.class, name = "articleDTO"),
        @JsonSubTypes.Type( value = ComboChoiceCreateRequestDTO.class, name = "comboDTO")
})
public class ChoiceCreateRequestDTO {
    private int amount;
    private String className;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
