package sitprojekat.interfajsi;

import java.util.List;
import java.util.Set;

import com.vaadin.flow.component.radiobutton.RadioButtonGroup;

import sitprojekat.model.Modifier;

public interface ArticleViewInterface {
	public void setArticleName(String name);
	public void setArticleDescription(String description);
	public void setPrice(double price);
	public int getOrderAmount();
	public void AddToCartNotif(String string);
	public void setArticleSizes(List<Modifier> articleSizes);
	public void setArticleModifiers(List<Modifier> articleModifiers);
	public Modifier getArticleSizesRadioButton();
	public Set<Modifier> getArticleModifiersCheckBox();
}
