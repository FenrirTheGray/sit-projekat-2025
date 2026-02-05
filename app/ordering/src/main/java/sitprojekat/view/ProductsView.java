package sitprojekat.view;

import java.util.List;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import sitprojekat.interfaces.ProductsViewInterface;
import sitprojekat.model.Article;
import sitprojekat.model.Combo;
import sitprojekat.model.Product;
import sitprojekat.presenter.ProductsPresenter;

@Route(value = "Products",layout = HeaderAndNavBar.class)
public class ProductsView extends VerticalLayout implements ProductsViewInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6116769739933744188L;
	
	private final ProductsPresenter	 presenter;
	private List<Article> allArticles;
	private List<Combo> allCombos;
	private TextField filterTextBox=new TextField();
	private final VerticalLayout productsContainer = new VerticalLayout();
	private Span noContentSpan=new Span("");
	
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
		this.allCombos =presenter.getCombos();	
        productsContainer.setWidthFull();
		
		
		add(filterContainer, productsContainer);
		if(allArticles!=null && !allArticles.isEmpty() && allCombos!=null && !allCombos.isEmpty()) {
			presenter.updateView("");
		}
		else {
			noContentSpan.setText("desila se greska");
			noContentSpan.addClassName("whiteText");
		}
		add(noContentSpan);
		
	}
	public VerticalLayout createProductContainer(Product product) {
		
	    VerticalLayout productContainer = new VerticalLayout();		 // glavni kontejner proizvoda
	    productContainer.addClassName("productContainer");			
	    productContainer.setPadding(true);
	    productContainer.setSpacing(true);
	    productContainer.setAlignItems(Alignment.CENTER);

	    
	    Image productImage = new Image("/images/image_burger.jpg", "Burger");      // slika proizvoda test
	    productImage.addClassName("productImage");

	    
	    H3 productNameH3 = new H3(product.getName()); //   naziv i opis za proizvod
	    productNameH3.addClassName("whiteText");
	    
	    Span productDescriptionSpan = new Span(product.getDescription()); 
	    productDescriptionSpan.addClassName("whiteText");

	    productContainer.add(productImage, productNameH3, productDescriptionSpan);
	    productContainer.addClassName("cursorPointer");;  //  double click pa ode na detalje i menja mis 
	    productContainer.addDoubleClickListener(e -> {
	    	if(product instanceof Article)
	        UI.getCurrent().navigate(ArticleView.class, product.getId());
	    	else {
	    		UI.getCurrent().navigate(ComboView.class, product.getId());
	    	}
	    });
	    
	    
	    return productContainer;
	}
	@Override
	public VerticalLayout createCategorySectionArticle(String title, List<Article> articles) {
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

    	noContentSpan.addClassName("whiteText");
        noContentSpan.setText(noContent);
		
	}
	@Override
	public List<Combo> getAllCombos() {
		return this.allCombos;
	}
	@Override
	public VerticalLayout createCategorySectionCombo(String title, List<Combo> combos) {
		H1 categoryH1 = new H1(title);
	    categoryH1.addClassName("categoryH1");  //naslov	    
	    categoryH1.setWidthFull();

	    VerticalLayout articleContainer = new VerticalLayout();            // svi u toj kategoriji 3 po jednom redu
	    articleContainer.addClassName("articleContainer");

	    for (Combo combo: combos) {
	        articleContainer.add(createProductContainer(combo));
	    }

	    VerticalLayout section = new VerticalLayout(categoryH1, articleContainer);
	    section.setPadding(false);
	    return section;
	}
}