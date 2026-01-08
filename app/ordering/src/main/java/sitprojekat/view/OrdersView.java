package sitprojekat.view;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import sitprojekat.model.Article;

@CssImport("./style/style.css")
@Route(value = "Orders",layout = HeaderAndNavBar.class)
public class OrdersView extends VerticalLayout{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7881133440689089779L;
	public OrdersView() {
					
		List<Article> listaArtikala=List.of(new Article("1", "Porudzbina1", "Datum Porudzbine :15.11.2025", 250.0, true),
				new Article("2", "Porudzbina2", "Datum Porudzbine :15.11.2025", 350.0, true),
				new Article("3", "Porudzbina3", "Datum Porudzbine :15.11.2025", 650.0, true)); // test za velicine
		
		VerticalLayout orderedProductsContainer=new VerticalLayout();
		
		Double totalSumDouble=0.0;
		for (Article a : listaArtikala) {
			orderedProductsContainer.add(createOrdersContainer(a));
			totalSumDouble+=a.getBasePrice();
		}
		


		orderedProductsContainer.setAlignItems(Alignment.CENTER);
		orderedProductsContainer.setJustifyContentMode(JustifyContentMode.CENTER);
		
		add(orderedProductsContainer);
	}
	public VerticalLayout createOrdersContainer(Article article) {
		
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

	    
	    topRowContainer.add(titleSpan);
	    topRowContainer.expand(titleSpan);

	    Span modifiersSpan = new Span(article.getDescription());   // srednji red
	    modifiersSpan.getStyle().set("color", "#ffffff").set("font-size", "0.9em").set("margin-bottom", "10px");

	   
	    HorizontalLayout bottomRowContainer = new HorizontalLayout(); // donji red
	    bottomRowContainer.setWidthFull();
	    bottomRowContainer.setAlignItems(Alignment.CENTER);

	    Span totalCostSpan = new Span(article.getBasePrice()+" umesto cene drugi datum koji sadrzi");
	    totalCostSpan.getStyle().set("color", "#ffffff");

	    
	    Button detailsButton = new Button("Detalji");
	    detailsButton.getStyle().set("color", "#ffffff");
	    detailsButton.getStyle().set("background-color", "#74914c");
	    
	    bottomRowContainer.add(totalCostSpan, detailsButton);
	    bottomRowContainer.setJustifyContentMode(JustifyContentMode.BETWEEN);

	    productOrderedContainer.add(topRowContainer, modifiersSpan, bottomRowContainer);
	    productOrderedContainer.getStyle().set("background-color", "#20281f");
	    
	    
	    return productOrderedContainer;
	}
}