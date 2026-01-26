package sitprojekat.presenter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vaadin.flow.spring.annotation.UIScope;

import sitprojekat.interfajsi.ProductsViewInterface;
import sitprojekat.model.Article;
import sitprojekat.service.ArticleService;

@Component
@UIScope
public class ProductsPresenter {
	
	private ProductsViewInterface view;
	private ArticleService service;
	
	public ProductsPresenter(ArticleService service) {
		this.service = service;
		
	}
	public List<Article> getArticles(){
		return this.service.getArticles();
		
	}
	
	public void updateView(String filterText) {
		view.getProductsContainer().removeAll(); // brise sve sto ima
		
		List<Article>allArticles= view.getAllArticles();
		
		List<Article> filteredArticles = allArticles.stream()   // filter
	            .filter(article -> article.getName().toLowerCase().contains(filterText.toLowerCase()))
	            .toList();
		
		if (filteredArticles.isEmpty()) {
	    	view.noContentSpan("Nema proizvoda: " +filterText); // ako nema niceg sa tim nazivom
	    }
		else {
	    
			Map<String, List<Article>> articleCategoriesGrouped = filteredArticles.stream()
					.collect(Collectors.groupingBy(article->article.getCategory().getName())); // deli ih u kategorije

			articleCategoriesGrouped.forEach((categoryName,articleGrouped)->{
				view.getProductsContainer().add(view.createCategorySection(categoryName, articleGrouped));
			});
	   }
	}
	public void setView(ProductsViewInterface view) {
		this.view=view;
		
	}
	
	
	

}
	