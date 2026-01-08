package sitprojekat.view;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.Route;

import sitprojekat.model.Article;

@CssImport("./style/style.css")
@Route(value = "Cart",layout = HeaderAndNavBar.class)
public class CartView extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3732233131929022510L;

	
	public CartView() {
		
		Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
		Button backButton=new Button("Povratak",backArrowIcon);
		backButton.addClassName("dugmeNazad");
			
		
		
						
		Button continueToOrderButton=new Button("Nastavi sa placanjem");
		continueToOrderButton.setText("Kreiraj Porudzbinu");
		continueToOrderButton.getStyle().set("background-color", "#3F220F");
		continueToOrderButton.getStyle().set("color", "#ffffff");
		continueToOrderButton.getStyle().set("border-radius", "8px");
		continueToOrderButton.setWidth("850px");
				
		List<Article> listaArtikala=List.of(new Article("1", "Proizvod1", "modifikatori: modifikator1", 250.0, true),
				new Article("2", "Proizvod2", "modifikatori: modifikator1", 350.0, true),
				new Article("3", "Proizvod3", "modifikatori: modifikator1", 650.0, true)); // test za velicine
		
		VerticalLayout orderedProductsContainer=new VerticalLayout();
		
		Double totalSumDouble=0.0;
		for (Article a : listaArtikala) {
			orderedProductsContainer.add(createOrderedProductContainer(a));
			totalSumDouble+=a.getBasePrice();
		}
		
		
		H4 totalPriceH4=new H4("Ukupna Cena:   "+totalSumDouble+" RSD");
		totalPriceH4.getStyle().set("color", "#ffffff");
		
		orderedProductsContainer.add(totalPriceH4,continueToOrderButton);
		orderedProductsContainer.setAlignItems(Alignment.CENTER);
		orderedProductsContainer.setJustifyContentMode(JustifyContentMode.CENTER);
		
		add(backButton,orderedProductsContainer);
	}
	public VerticalLayout createOrderedProductContainer(Article article) {
		
	    VerticalLayout productOrderedContainer = new VerticalLayout(); // glavni pravougaonik
	    productOrderedContainer.setWidth("850px");
	    productOrderedContainer.getStyle().set("background-color", "#ffffff");
	    productOrderedContainer.getStyle().set("border-radius", "12px");
	    productOrderedContainer.getStyle().set("padding", "15px");
	    productOrderedContainer.getStyle().set("margin-bottom", "15px");
	    productOrderedContainer.setSpacing(false);

	    HorizontalLayout topRowContainer = new HorizontalLayout();  // gornji red
	    topRowContainer.setWidthFull();
	    
	    Span titleSpan = new Span(article.getName());
	    titleSpan.getStyle().set("font-weight", "bold").set("font-size", "1.2em").set("color", "#ffffff");
	    
	   
	    
	    IntegerField addToCountIntegerField = new IntegerField();
	    addToCountIntegerField.setValue(1);
	    addToCountIntegerField.setStepButtonsVisible(true);
	    addToCountIntegerField.setWidth("125px");
	    addToCountIntegerField.addClassName("productCounter");
	    //addToCountIntegerField.addThemeVariants(IntegerFieldVariant.LUMO_SMALL);
	    
	    Button removeOrderButton = new Button("Ukloni");
	    removeOrderButton.getStyle().set("color", "#ffffff");
	    removeOrderButton.getStyle().set("background-color", "#74914c");
	    
	    topRowContainer.add(titleSpan, addToCountIntegerField,removeOrderButton);
	    topRowContainer.expand(titleSpan);

	    Span modifiersSpan = new Span(article.getDescription());   // srednji red
	    modifiersSpan.getStyle().set("color", "#ffffff").set("font-size", "0.9em").set("margin-bottom", "10px");

	   
	    HorizontalLayout bottomRowContainer = new HorizontalLayout(); // donji red
	    bottomRowContainer.setWidthFull();
	    bottomRowContainer.setAlignItems(Alignment.CENTER);

	    Span totalCostSpan = new Span(article.getBasePrice() + " RSD");
	    totalCostSpan.getStyle().set("color", "#ffffff");

	    Span orderAmountSumSpan=new Span("Kolicina: 5");
	    orderAmountSumSpan.getStyle().set("color", "#ffffff");
	    
	    Button detailsButton = new Button("Detalji");
	    detailsButton.getStyle().set("color", "#ffffff");
	    detailsButton.getStyle().set("background-color", "#74914c");
	    
	    bottomRowContainer.add(orderAmountSumSpan, totalCostSpan, detailsButton);
	    bottomRowContainer.setJustifyContentMode(JustifyContentMode.BETWEEN);

	    productOrderedContainer.add(topRowContainer, modifiersSpan, bottomRowContainer);
	    productOrderedContainer.getStyle().set("background-color", "#20281f");
	    
	    
	    return productOrderedContainer;
	}
}
