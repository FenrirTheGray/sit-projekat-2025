package sitprojekat.interfajsi;

import java.util.List;

import sitprojekat.model.Modifier;

public interface ArticleViewInterface {
	void setArticleName(String name);
	void setArticleDescription(String description);
	void setPrice(double price);
	int getOrderAmount();
	void AddToCartNotif(String string);
	void setArticleSizes(List<Modifier> articleSizes);
    void setArticleModifiers(List<Modifier> articleModifiers);
    double getArticleSizesRadioButton();
    double getArticleModifiersCheckBox();
}
