package sitprojekat.view;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import sitprojekat.model.Article;

@CssImport("./style/style.css")
@Route(value = "Order", layout = HeaderAndNavBar.class)
public class OrderView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -354889220122711558L;

	public OrderView() {
		Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
		Button backButton=new Button("Povratak",backArrowIcon);
		backButton.addClassName("brownButton");

		VerticalLayout mainContainer=new VerticalLayout();
		VerticalLayout orderInformationContainer=new VerticalLayout();
		orderInformationContainer.addClassName("orderContainer");
		orderInformationContainer.setSpacing(false);
		    
		Span titleSpan = new Span("Porudzbina 1");
		titleSpan.addClassName("boldText2");

		Span orderDateSpan = new Span("Datum Porudzbine: 15.21.2025"); 
		orderDateSpan.addClassName("whiteText");
		
		Span orderTimeSpan = new Span("Vreme Porudzbine: 16:30");
		orderTimeSpan.addClassName("whiteText3");
		    
		Span orderTimeSentSpan = new Span("Porudzbina poslata: 17:03"); 
		orderTimeSentSpan.addClassName("whiteText");
		
		Span orderTimeDeliveredSpan = new Span("Vreme isporuke: 18:23");
		orderTimeDeliveredSpan.addClassName("whiteText3");
		
		List<Article> listaArtikala=List.of(new Article("1", "Artical1", "modifikator1", 250.0, true, null, null),
				new Article("2", "Artical2", "modifikator2", 350.0, true, null, null),
				new Article("3", "Artical3", "modifikator3", 650.0, true, null, null)); // test za velicine
		
		orderInformationContainer.add(titleSpan,orderDateSpan,orderTimeSpan,orderTimeSentSpan,orderTimeDeliveredSpan);
		
		double totalSumDouble=0;
		for (int i = 0; i < listaArtikala.size(); i++) {
		    Article a = listaArtikala.get(i);
		    Span orderArticleSpan = new Span(a.getName() + " x2");
		    orderArticleSpan.addClassName("whiteText");
		    orderInformationContainer.add(orderArticleSpan);
		    totalSumDouble += a.getBasePrice();

		    if (i == listaArtikala.size() - 1) {
		    	orderArticleSpan.addClassName("whiteText3");
		    }
		}
		
		
		Span orderStatusSpan=new Span("Status: U pripremi"); 
		orderStatusSpan.addClassName("whiteText3");
		
		Span orderTotalSumSpan=new Span("Cena:"+totalSumDouble);    
		orderTotalSumSpan.addClassName("whiteText");
		
		Span orderPaymentType=new Span("Nacin placanja: Kes");    
		orderPaymentType.addClassName("whiteText4");
		
		orderInformationContainer.add(orderStatusSpan,orderTotalSumSpan,orderPaymentType);
		mainContainer.add(orderInformationContainer);
		mainContainer.setAlignItems(Alignment.CENTER);
		mainContainer.setJustifyContentMode(JustifyContentMode.CENTER);

		add(backButton,mainContainer);
	}
}
