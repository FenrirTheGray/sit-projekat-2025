package rs.ac.singidunum.servelogic.dto.create;

import java.util.List;

public class ArticleChoiceCreateDTO extends ChoiceCreateDTO{
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
