package sitprojekat.view;

import java.util.List;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.Route;

import sitprojekat.interfajsi.CartViewInterface;
import sitprojekat.model.Article;
import sitprojekat.model.ProductInCart;
import sitprojekat.presenter.CartPresenter;

@CssImport("./style/style.css")
@Route(value = "Cart",layout = HeaderAndNavBar.class)
public class CartView extends VerticalLayout implements CartViewInterface{	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3732233131929022510L;

	
	private VerticalLayout orderedProductsContainer = new VerticalLayout();
    private H4 totalPriceH4 = new H4("Ukupna Cena: 0.0 RSD");
    private Button continueToOrderButton=new Button("Nastavi sa placanjem");
    private final CartPresenter presenter;
	
	
	public CartView(CartPresenter presenter) {
		this.presenter=presenter;
		presenter.setView(this);
		
		Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
		Button backButton=new Button("Povratak",backArrowIcon);
		backButton.addClassName("brownButton");
		backButton.addClickListener(e->{UI.getCurrent().getPage().getHistory().back();});
		
							
		continueToOrderButton.addClassName("continueToOrderButton");
		continueToOrderButton.addClickListener(e->{
			List<ProductInCart> products=presenter.getProducts();
			
			if(!products.isEmpty()) {
				UI.getCurrent().navigate("OrderCreation");
			}
			
		});		

		totalPriceH4.addClassName("whiteText");
		

		
		orderedProductsContainer.add(totalPriceH4,continueToOrderButton);
		orderedProductsContainer.setAlignItems(Alignment.CENTER);
		orderedProductsContainer.setJustifyContentMode(JustifyContentMode.CENTER);
		
		
		
		add(backButton,orderedProductsContainer);
		presenter.updateCart();
	}
	public VerticalLayout createOrderedProductContainer(ProductInCart productInCart) {
		
		Article article=productInCart.getArticle();
		
	    VerticalLayout productOrderedContainer = new VerticalLayout(); // glavni pravougaonik
	    productOrderedContainer.addClassName("productOrderedContainer");

	    HorizontalLayout topRowContainer = new HorizontalLayout();  // gornji red
	    topRowContainer.setWidthFull();
	    
	    Span titleSpan = new Span(article.getName());
	    titleSpan.addClassName("boldText");
	    
	    
	    IntegerField addToCountIntegerField = new IntegerField();
	    addToCountIntegerField.setValue(1);
	    addToCountIntegerField.setMin(1);
	    addToCountIntegerField.setStepButtonsVisible(true);;
	    addToCountIntegerField.addClassName("productCounter");
	    
	    Button removeOrderButton = new Button("Ukloni");
	    removeOrderButton.addClassName("greenButton");
	    removeOrderButton.addClickListener(e -> presenter.removeFromCart(productInCart,addToCountIntegerField.getValue()));

	    
	    topRowContainer.add(titleSpan, addToCountIntegerField,removeOrderButton);
	    topRowContainer.expand(titleSpan);

	    Span modifiersSpan = new Span(article.getDescription());   // srednji red
	    modifiersSpan.addClassName("whiteText");

	   
	    HorizontalLayout bottomRowContainer = new HorizontalLayout(); // donji red
	    bottomRowContainer.setWidthFull();
	    bottomRowContainer.setAlignItems(Alignment.CENTER);
	
	    Span totalCostSpan = new Span(productInCart.getTotalPrice() + " RSD");
        totalCostSpan.addClassName("whiteText");


	    Span orderAmountSumSpan = new Span("Kolicina: " + productInCart.getNumberOrdered());
        orderAmountSumSpan.addClassName("whiteText");
                

	    
	    Button detailsButton = new Button("Detalji");
	    detailsButton.addClassName("greenButton");
	    
	    detailsButton.addClickListener(e->{UI.getCurrent().navigate("Article/"+productInCart.getArticle().getId());});
	    bottomRowContainer.add(orderAmountSumSpan, totalCostSpan, detailsButton);
	    bottomRowContainer.setJustifyContentMode(JustifyContentMode.BETWEEN);

	    productOrderedContainer.add(topRowContainer, modifiersSpan, bottomRowContainer);
	    
	    return productOrderedContainer;
	}
	@Override
	public void showCartItems(List<ProductInCart> productsInCart) {
        orderedProductsContainer.removeAll(); // brise se staro
        
        for (ProductInCart productInCart : productsInCart) {// dodaje se sa novim
            orderedProductsContainer.add(createOrderedProductContainer(productInCart));
        }
        orderedProductsContainer.add(totalPriceH4,continueToOrderButton);
		orderedProductsContainer.setAlignItems(Alignment.CENTER);
		orderedProductsContainer.setJustifyContentMode(JustifyContentMode.CENTER);
		
	}
	@Override
	public void updateTotalPrice(double totalPrice) { // ukupna cena od svih proizvoda
		totalPriceH4.setText("Ukupna Cena: " + totalPrice + " RSD");
		
	}
}
