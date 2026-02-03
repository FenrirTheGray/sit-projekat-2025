package sitprojekat.dto;

import java.util.List;

public class ArticleChoiceCreateRequestDTO extends ChoiceCreateRequestDTO {
    private String article;
    private List<String> modifierList;

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public List<String> getModifierList() {
        return modifierList;
    }

    public void setModifierList(List<String> modifierList) {
        this.modifierList = modifierList;
    }
}