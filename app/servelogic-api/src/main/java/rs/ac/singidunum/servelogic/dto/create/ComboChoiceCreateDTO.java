package rs.ac.singidunum.servelogic.dto.create;

import java.util.List;

public class ComboChoiceCreateDTO extends ChoiceCreateDTO{
    private String combo;
    private List<ArticleChoiceCreateDTO> articleChoiceList;

    public String getCombo() {
        return combo;
    }

    public void setCombo(String combo) {
        this.combo = combo;
    }

    public List<ArticleChoiceCreateDTO> getArticleChoiceList() {
        return articleChoiceList;
    }

    public void setArticleChoiceList(List<ArticleChoiceCreateDTO> articleChoiceList) {
        this.articleChoiceList = articleChoiceList;
    }
}
