package sitprojekat.interfaces;

import java.util.List;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import sitprojekat.model.Article;
import sitprojekat.model.Combo;

public interface ProductsViewInterface {
	public String getFilterTextBox();
	public void setFilterTextBox(String filter);
	public List<Article> getAllArticles();
	public VerticalLayout getProductsContainer();
	public VerticalLayout createCategorySectionArticle(String title, List<Article> articles);
	public VerticalLayout createCategorySectionCombo(String title, List<Combo> combos);
	public void noContentSpan(String noContent);
	public List<Combo> getAllCombos();
}
