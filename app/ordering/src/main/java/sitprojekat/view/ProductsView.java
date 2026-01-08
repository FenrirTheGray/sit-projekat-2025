package sitprojekat.view;

import java.util.List;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import sitprojekat.model.Article;
import sitprojekat.service.ArticleService;



@Route(value = "Products",layout = HeaderAndNavBar.class)
public class ProductsView extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6116769739933744188L;
	
	private final ArticleService service;
	private final List<Article> allArticles;
	private final VerticalLayout scrollContent = new VerticalLayout();
	
	public ProductsView(ArticleService service) {
		//getStyle().set("background-color", "#204824");// za side bar #20281f
		
		
		this.service=service;
		
		
		
		Icon searchIcon=VaadinIcon.SEARCH.create();
		searchIcon.getStyle().set("cursor", "pointer");
		
		TextField filterTextBox=new TextField();
		
		filterTextBox.setPlaceholder("pretraga");
		filterTextBox.setClearButtonVisible(true);
		filterTextBox.setSuffixComponent(searchIcon);
		filterTextBox.getStyle().set("background-color", "#ffffff");
		filterTextBox.getStyle().set("border-radius", "30px");
		filterTextBox.setWidth("350px");
		//textboxFilter.getStyle().set("margin", "10px");
		filterTextBox.getStyle().set("padding", "0px");
		
		searchIcon.addClickListener(e -> {
		    updateView(filterTextBox.getValue());
		});
		
		HorizontalLayout filterContainer=new HorizontalLayout(filterTextBox);
		filterContainer.setWidthFull();
		filterContainer.setJustifyContentMode(JustifyContentMode.END);
		
//		Grid<Article> articleTable=new Grid<>(Article.class);
//		articleTable.setColumns("id","name","description","basePrice","active");
		
		
		List<Article> articles=List.of(new Article("1", "naziv1", "opis1", 250.0, true),
				new Article("2", "naziv2", "opis2", 650.0, true),
				new Article("3", "naziv3", "opis3", 350.0, true),
				new Article("4", "naziv4", "opis4", 150.0, true),
				new Article("5", "naziv5", "opis5", 250.0, true),
				new Article("6", "naziv6", "opis6", 650.0, true),
				new Article("7", "naziv7", "opis7", 350.0, true),
				new Article("8", "naziv8", "opis8", 150.0, true));
		
		
		this.allArticles =articles;
		//List<Article> articles=service.getArticles();
//		articleTable.setItems(articles);
//		
//		articleTable.getStyle().set("background", "transparent");
//		
//		articleTable.addItemDoubleClickListener(e->{
//			Article article=e.getItem();
//		   // Notification.show("artikal ima : " +article.getId()+" "+article.getBasePrice());
//			UI.getCurrent().navigate(ArticleView.class,article.getId());
//			
//		});
		
		
//		H1 category1H1 = new H1("Kategorija 1");
//		category1H1.getStyle().set("text-align", "center");
//		category1H1.getStyle().set("color", "#ffffff");
//		category1H1.setWidthFull();
//
//		FlexLayout articleContainer1 = new FlexLayout();
//		articleContainer1.getStyle().set("display", "grid");
//		articleContainer1.getStyle().set("grid-template-columns", "repeat(3, 1fr)"); 
//		articleContainer1.getStyle().set("gap", "30px"); 
//		articleContainer1.getStyle().set("justify-content", "center");
//
//		//List<Article> articles = service.getArticles();
//		for (Article article : articles) {
//			articleContainer1.add(createProductContainer(article));
//		}
		
		
//		VerticalLayout scrollContent = new VerticalLayout();
		
        scrollContent.setWidthFull();
		
		Scroller scrolScroller = new Scroller(scrollContent);
		scrolScroller.setWidthFull();
		scrolScroller.setHeightFull();
		
		
		add(filterContainer, scrolScroller);
		updateView("");
		
	}
	private VerticalLayout createProductContainer(Article article) {
		
	    VerticalLayout productContainer = new VerticalLayout();		 // glavni kontejner proizvoda
	    productContainer.setWidth("300px");				
	    productContainer.setPadding(true);
	    productContainer.setSpacing(true);
	    productContainer.getStyle().set("border", "2px solid");
	    productContainer.getStyle().set("border-color", "#3F220F");
	    productContainer.getStyle().set("border-radius", "10px");
	    productContainer.setAlignItems(Alignment.CENTER);

	    
	    Image productImage = new Image("/images/image_burger.jpg", "Burger");      // slika proizvoda test
	    productImage.setWidth("100px");
	    productImage.setHeight("100px");

	    
	    H3 productNameH3 = new H3(article.getName()); //   naziv i opis za proizvod
	    productNameH3.getStyle().set("color", "#ffffff");
	    
	    Span productDescriptionSpan = new Span(article.getDescription()); 
	    productDescriptionSpan.getStyle().set("color", "#ffffff");

	    productContainer.add(productImage, productNameH3, productDescriptionSpan);
	    productContainer.getStyle().set("cursor", "pointer");  //  double click pa ode na detalje i menja mis 
	    productContainer.addDoubleClickListener(e -> {
	        UI.getCurrent().navigate(ArticleView.class, article.getId());
	    });
	    
	    
	    return productContainer;
	}
	private VerticalLayout createCategorySection(String title, List<Article> articles) {
	    H1 categoryH1 = new H1(title);
	    categoryH1.getStyle().set("text-align", "center");  //naslov
	    categoryH1.getStyle().set("color", "#ffffff");
	    categoryH1.setWidthFull();

	    FlexLayout articleContainer = new FlexLayout();            // svi u toj kategoriji 3 po jednom redu
	    articleContainer.getStyle().set("display", "grid"); 
	    articleContainer.getStyle().set("grid-template-columns", "repeat(3, 1fr)"); 
	    articleContainer.getStyle().set("gap", "120px"); 
	    articleContainer.getStyle().set("margin-left", "auto");
	    articleContainer.getStyle().set("margin-right", "auto");

	    for (Article article : articles) {
	        articleContainer.add(createProductContainer(article));
	    }

	    VerticalLayout section = new VerticalLayout(categoryH1, articleContainer);
	    section.setPadding(false);
	    return section;
	}
	private void updateView(String filterText) {
		scrollContent.removeAll(); // brise sve sto ima

		//
		if (filterText.trim().isEmpty()) {
			scrollContent.add(createCategorySection("Kategorija 1", allArticles),createCategorySection("Kategorija 2", allArticles)); // 
	    }
		else {
		
	    List<Article> filteredArticles = allArticles.stream()   // filtrer liste sve kao mala slova
	            .filter(article -> article.getName().toLowerCase().contains(filterText.toLowerCase()))
	            .toList();
	    
	    if (filteredArticles.isEmpty()) {
	    	Span nocontentSpan=new Span("Nema proizvoda: " +filterText); 	  // ako nema niceg sa tim nazivom
	    	nocontentSpan.getStyle().set("color", "#ffffff");
	        scrollContent.add(nocontentSpan);
	    } else {
	        scrollContent.add(createCategorySection("Rezultati pretrage", filteredArticles)); // pravi se nov sa filtriranim
	    }
		}
	}
}
