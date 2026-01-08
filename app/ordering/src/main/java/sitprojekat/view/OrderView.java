package sitprojekat.view;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
		backButton.addClassName("dugmeNazad");

		VerticalLayout mainContainer=new VerticalLayout();
		VerticalLayout orderInformationContainer=new VerticalLayout();
		orderInformationContainer.setWidth("850px");
		orderInformationContainer.getStyle().set("background-color", "#20281f");
		orderInformationContainer.getStyle().set("border-radius", "12px");
		orderInformationContainer.getStyle().set("padding", "15px");
		orderInformationContainer.getStyle().set("margin-bottom", "15px");
		orderInformationContainer.setSpacing(false);
		    
		Span titleSpan = new Span("Porudzbina 1");
		titleSpan.getStyle().set("font-weight", "bold");
		titleSpan.getStyle().set("font-size", "25px");
		titleSpan.getStyle().set("color", "#ffffff");
		titleSpan.getStyle().set("margin-bottom", "10px");

		Span orderDateSpan = new Span("Datum Porudzbine: 15.21.2025"); 
		orderDateSpan.getStyle().set("color", "#ffffff");
		
		Span orderTimeSpan = new Span("Vreme Porudzbine: 16:30");
		orderTimeSpan.getStyle().set("color", "#ffffff");
		orderTimeSpan.getStyle().set("margin-bottom", "10px");
		    
		Span orderTimeSentSpan = new Span("Porudzbina poslata: 17:03"); 
		orderTimeSentSpan.getStyle().set("color", "#ffffff");
		
		Span orderTimeDeliveredSpan = new Span("Vreme isporuke: 18:23");
		orderTimeDeliveredSpan.getStyle().set("color", "#ffffff");
		orderTimeDeliveredSpan.getStyle().set("margin-bottom", "10px");
		
		List<Article> listaArtikala=List.of(new Article("1", "Artical1", "modifikator1", 250.0, true),
				new Article("2", "Artical2", "modifikator2", 350.0, true),
				new Article("3", "Artical3", "modifikator3", 650.0, true)); // test za velicine
		
		orderInformationContainer.add(titleSpan,orderDateSpan,orderTimeSpan,orderTimeSentSpan,orderTimeDeliveredSpan);
		
		double totalSumDouble=0;
		for (int i = 0; i < listaArtikala.size(); i++) {
		    Article a = listaArtikala.get(i);
		    Span orderArticleSpan = new Span(a.getName() + " x2");
		    orderArticleSpan.getStyle().set("color", "#ffffff");
		    orderInformationContainer.add(orderArticleSpan);
		    totalSumDouble += a.getBasePrice();

		    if (i == listaArtikala.size() - 1) {
		    	orderArticleSpan.getStyle().set("margin-bottom", "10px");
		    }
		}
		
		
		Span orderStatusSpan=new Span("Status: U pripremi"); 
		orderStatusSpan.getStyle().set("color", "#ffffff");
		orderStatusSpan.getStyle().set("margin-bottom", "10px");
		
		Span orderTotalSumSpan=new Span("Cena:"+totalSumDouble);    
		orderTotalSumSpan.getStyle().set("color", "#ffffff");
		
		Span orderPaymentType=new Span("Nacin placanja: Kes");    
		orderPaymentType.getStyle().set("color", "#ffffff");
		orderPaymentType.getStyle().set("margin-bottom", "20px");
		
		orderInformationContainer.add(orderStatusSpan,orderTotalSumSpan,orderPaymentType);
		mainContainer.add(orderInformationContainer);
		mainContainer.setAlignItems(Alignment.CENTER);
		mainContainer.setJustifyContentMode(JustifyContentMode.CENTER);

		add(backButton,mainContainer);
	}
}
