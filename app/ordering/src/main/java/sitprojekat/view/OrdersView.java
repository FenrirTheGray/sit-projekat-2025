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
					
		List<Article> listaArtikala=List.of(new Article("1", "Porudzbina1", "Datum Porudzbine :15.11.2025", 250.0, true, null, null),
				new Article("2", "Porudzbina2", "Datum Porudzbine :15.11.2025", 350.0, true, null, null),
				new Article("3", "Porudzbina3", "Datum Porudzbine :15.11.2025", 650.0, true, null, null)); // test za velicine
		
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
		
	    VerticalLayout orderContainer = new VerticalLayout(); // glavni pravougaonik
	    orderContainer.addClassName("orderContainer");
	    
	    orderContainer.setSpacing(false);

	    HorizontalLayout topRowContainer = new HorizontalLayout();  // gornji red
	    topRowContainer.setWidthFull();
	    
	    Span titleSpan = new Span(article.getName());
	    titleSpan.addClassName("boldText2");
    
	    topRowContainer.add(titleSpan);
	    topRowContainer.expand(titleSpan);

	    VerticalLayout dateTimeContainer = new VerticalLayout(); // spojeno datum i vreme u jedan
	    dateTimeContainer.setSpacing(false); 
	    dateTimeContainer.setPadding(false); 
	    
	    Span dateOrderedSpan = new Span(article.getDescription());   
	    dateOrderedSpan.addClassName("whiteText2");
	   
	    HorizontalLayout bottomRowContainer = new HorizontalLayout(); // donji red
	    bottomRowContainer.setWidthFull();
	    bottomRowContainer.setAlignItems(Alignment.CENTER);

	    Span timeOrderedSpan = new Span(article.getBasePrice()+" umesto cene drugi datum koji sadrzi");
	    timeOrderedSpan.addClassName("whiteText");
    
	    Button detailsButton = new Button("Detalji");
	    detailsButton.addClassName("greenButton");
	    
	    dateTimeContainer.add(dateOrderedSpan,timeOrderedSpan);
	    
	    bottomRowContainer.add(dateTimeContainer,detailsButton);
	    bottomRowContainer.setJustifyContentMode(JustifyContentMode.BETWEEN);

	    orderContainer.add(topRowContainer,bottomRowContainer);   
	    
	    return orderContainer;
	}
}