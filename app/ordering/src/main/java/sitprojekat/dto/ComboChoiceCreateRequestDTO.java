package sitprojekat.dto;


import java.util.List;

public class ComboChoiceCreateRequestDTO extends ChoiceCreateRequestDTO {
    private String combo;
    private List<ArticleChoiceCreateRequestDTO> articleChoiceList;

    public String getCombo() {
        return combo;
    }

    public void setCombo(String combo) {
        this.combo = combo;
    }

    public List<ArticleChoiceCreateRequestDTO> getArticleChoiceList() {
        return articleChoiceList;
    }

    public void setArticleChoiceList(List<ArticleChoiceCreateRequestDTO> articleChoiceList) {
        this.articleChoiceList = articleChoiceList;
    }
}
