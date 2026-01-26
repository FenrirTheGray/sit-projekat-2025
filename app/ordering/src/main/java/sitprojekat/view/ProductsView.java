package sitprojekat.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import sitprojekat.model.Article;
import sitprojekat.service.ArticleService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Route(value = "Products",layout = HeaderAndNavBar.class)
public class ProductsView extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6116769739933744188L;
	
	private final ArticleService service;
	private final List<Article> allArticles;
	private final VerticalLayout productsContainer = new VerticalLayout();
	
	public ProductsView(ArticleService service) {		
		
		this.service=service;
		
		Icon searchIcon=VaadinIcon.SEARCH.create();
		searchIcon.addClassName("cursorPointer");
		
		TextField filterTextBox=new TextField();
		
		filterTextBox.setPlaceholder("pretraga");
		filterTextBox.addClassName("filterTextBox");
		filterTextBox.setClearButtonVisible(true);
		filterTextBox.setSuffixComponent(searchIcon);
		
		searchIcon.addClickListener(e -> {
		    updateView(filterTextBox.getValue());
		});
		
		HorizontalLayout filterContainer=new HorizontalLayout(filterTextBox);
		filterContainer.setWidthFull();
		filterContainer.setJustifyContentMode(JustifyContentMode.END);

		this.allArticles = service.getArticles();
        productsContainer.setWidthFull();
		
		
		add(filterContainer, productsContainer);
		if(!allArticles.isEmpty()) {
			updateView("");
		}
		else {
			Span noProductsSpan=new Span("desila se greska ");
			noProductsSpan.addClassName("whiteText");
			add(noProductsSpan);
		}
		
	}
	private VerticalLayout createProductContainer(Article article) {
		
	    VerticalLayout productContainer = new VerticalLayout();		 // glavni kontejner proizvoda
	    productContainer.addClassName("productContainer");			
	    productContainer.setPadding(true);
	    productContainer.setSpacing(true);
	    productContainer.setAlignItems(Alignment.CENTER);

	    
	    Image productImage = new Image("/images/image_burger.jpg", "Burger");      // slika proizvoda test
	    productImage.addClassName("productImage");

	    
	    H3 productNameH3 = new H3(article.getName()); //   naziv i opis za proizvod
	    productNameH3.addClassName("whiteText");
	    
	    Span productDescriptionSpan = new Span(article.getDescription()); 
	    productDescriptionSpan.addClassName("whiteText");

	    productContainer.add(productImage, productNameH3, productDescriptionSpan);
	    productContainer.addClassName("cursorPointer");;  //  double click pa ode na detalje i menja mis 
	    productContainer.addDoubleClickListener(e -> {
	        UI.getCurrent().navigate(ArticleView.class, article.getId());
	    });
	    
	    
	    return productContainer;
	}
	private VerticalLayout createCategorySection(String title, List<Article> articles) {
	    H1 categoryH1 = new H1(title);
	    categoryH1.addClassName("categoryH1");  //naslov	    
	    categoryH1.setWidthFull();

	    FlexLayout articleContainer = new FlexLayout();            // svi u toj kategoriji 3 po jednom redu
	    articleContainer.addClassName("articleContainer");

	    for (Article article : articles) {
	        articleContainer.add(createProductContainer(article));
	    }

	    VerticalLayout section = new VerticalLayout(categoryH1, articleContainer);
	    section.setPadding(false);
	    return section;
	}
	private void updateView(String filterText) {
		productsContainer.removeAll(); // brise sve sto ima

			List<Article> filteredArticles = allArticles.stream()   // filter
					.filter(article -> article.getName().toLowerCase().contains(filterText.toLowerCase()))
					.toList();

			if (filteredArticles.isEmpty()) {
				Span noContentSpan=new Span("Nema proizvoda: " +filterText); 	  // ako nema niceg sa tim nazivom
				noContentSpan.addClassName("whiteText");
				productsContainer.add(noContentSpan);
				return ;
			}

			Map<String, List<Article>> articleCategoriesGrouped = filteredArticles.stream()
					.collect(Collectors.groupingBy(article->article.getCategory().getName()));

			articleCategoriesGrouped.forEach((categoryName,articleGrouped)->{
	    	productsContainer.add(createCategorySection(categoryName, articleGrouped));
	    });
	}
}