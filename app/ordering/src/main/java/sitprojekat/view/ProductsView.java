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
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import sitprojekat.interfaces.ProductsViewInterface;
import sitprojekat.model.Article;
import sitprojekat.presenter.ProductsPresenter;
import sitprojekat.service.ArticleService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Route(value = "Products",layout = HeaderAndNavBar.class)
public class ProductsView extends VerticalLayout implements ProductsViewInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6116769739933744188L;
	
	private final ProductsPresenter	 presenter;
	private final List<Article> allArticles;
	private TextField filterTextBox=new TextField();
	private final VerticalLayout productsContainer = new VerticalLayout();
	
	public ProductsView(ProductsPresenter presenter) {			
		
		this.presenter=presenter;
		presenter.setView(this);
		
		
		Icon searchIcon=VaadinIcon.SEARCH.create();
		searchIcon.addClassName("cursorPointer");
		
		
		
		filterTextBox.setPlaceholder("pretraga");
		filterTextBox.addClassName("filterTextBox");
		filterTextBox.setClearButtonVisible(true);
		filterTextBox.setSuffixComponent(searchIcon);
		
		searchIcon.addClickListener(e -> {
		    presenter.updateView(filterTextBox.getValue());
		});
		
		HorizontalLayout filterContainer=new HorizontalLayout(filterTextBox);
		filterContainer.setWidthFull();
		filterContainer.setJustifyContentMode(JustifyContentMode.END);
		
		this.allArticles =presenter.getArticles();		
        productsContainer.setWidthFull();
		
		
		add(filterContainer, productsContainer);
		if(!allArticles.isEmpty()) {
			presenter.updateView("");
		}
		else {
			Span noProductsSpan=new Span("desila se greska ");
			noProductsSpan.addClassName("whiteText");
			add(noProductsSpan);
		}
		
	}
	public VerticalLayout createProductContainer(Article article) {
		
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
	public VerticalLayout createCategorySection(String title, List<Article> articles) {
	    H1 categoryH1 = new H1(title);
	    categoryH1.addClassName("categoryH1");  //naslov	    
	    categoryH1.setWidthFull();

	    VerticalLayout articleContainer = new VerticalLayout();            // svi u toj kategoriji 3 po jednom redu
	    articleContainer.addClassName("articleContainer");

	    for (Article article : articles) {
	        articleContainer.add(createProductContainer(article));
	    }

	    VerticalLayout section = new VerticalLayout(categoryH1, articleContainer);
	    section.setPadding(false);
	    return section;
	}
	@Override
	public String getFilterTextBox() {
		return this.filterTextBox.getValue();
		
	}
	@Override
	public void setFilterTextBox(String filter) {
		this.filterTextBox.setValue(filter);
		
	}
	@Override
	public List<Article>  getAllArticles() {
		return this.allArticles;
		
	}
	@Override
	public VerticalLayout getProductsContainer() {
		return this.productsContainer;
		
	}
	@Override
	public void noContentSpan(String noContent) {
		Span noContentSpan=new Span(noContent);
    	noContentSpan.addClassName("whiteText");
        add(noContentSpan);
        return ;
		
	}

	
	
	
}