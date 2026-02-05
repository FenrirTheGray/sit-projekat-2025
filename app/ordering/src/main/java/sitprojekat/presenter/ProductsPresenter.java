package sitprojekat.presenter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vaadin.flow.spring.annotation.UIScope;

import sitprojekat.interfaces.ProductsViewInterface;
import sitprojekat.model.Article;
import sitprojekat.model.Combo;
import sitprojekat.service.ArticleService;
import sitprojekat.service.ComboService;

@Component
@UIScope
public class ProductsPresenter {
	
	private ProductsViewInterface view;
	private ArticleService articleService;
	private ComboService comboService;
	
	public ProductsPresenter(ArticleService articleService, ComboService comboService) {
		this.articleService = articleService;
		this.comboService = comboService;
	}

	public List<Article> getArticles(){
		return this.articleService.getArticles();
		
	}
	
	public List<Combo> getCombos(){
		return this.comboService.getCombos();
		
	}
	
	public void updateView(String filterText) {
		view.getProductsContainer().removeAll(); // brise sve sto ima
		view.noContentSpan("");
		List<Article>allArticles= view.getAllArticles();
		
		List<Article> filteredArticles = allArticles.stream()   // filter
	            .filter(article -> article.getName().toLowerCase().contains(filterText.toLowerCase()))
	            .toList();
		
		List<Combo>allCombos= view.getAllCombos();
		
		List<Combo> filteredCombos = allCombos.stream()   // filter
	            .filter(combo -> combo.getName().toLowerCase().contains(filterText.toLowerCase()))
	            .toList();
		
		
		if (!filteredArticles.isEmpty()) {
	    
			Map<String, List<Article>> articleCategoriesGrouped = filteredArticles.stream()
					.collect(Collectors.groupingBy(article->{
						
						if(article.getCategory() != null) {
						
						return article.getCategory().getName();
						}
						else {
							return "nema kategoriju";
						}
						
					})); // deli ih u kategorije

			articleCategoriesGrouped.forEach((categoryName,articleGrouped)->{
				view.getProductsContainer().add(view.createCategorySectionArticle(categoryName, articleGrouped));
				
			});
	   }
		if(!filteredCombos.isEmpty()) {
		view.getProductsContainer().add(view.createCategorySectionCombo("Combo",filteredCombos));
		}
		
		if(filteredArticles.isEmpty() && filteredCombos.isEmpty()) {
	    	view.noContentSpan("Nema proizvoda: " +filterText); // ako nema niceg sa tim nazivom
		}
	}
	public void setView(ProductsViewInterface view) {
		this.view=view;
		
	}
	
	
	

}
	