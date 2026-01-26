package sitprojekat.interfaces;

import java.util.List;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import sitprojekat.model.Article;

public interface ProductsViewInterface {
	public String getFilterTextBox();
	public void setFilterTextBox(String filter);
	public List<Article> getAllArticles();
	public VerticalLayout getProductsContainer();
	public VerticalLayout createCategorySection(String title, List<Article> articles);
	public void noContentSpan(String noContent);
}
