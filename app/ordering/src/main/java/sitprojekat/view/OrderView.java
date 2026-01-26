package sitprojekat.view;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import sitprojekat.interfaces.OrderViewInterface;
import sitprojekat.model.Article;
import sitprojekat.presenter.OrderPresenter;

@CssImport("./style/style.css")
@Route(value = "Order", layout = HeaderAndNavBar.class)
public class OrderView extends VerticalLayout implements OrderViewInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = -354889220122711558L;

	private Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
	private Button backButton=new Button("Povratak",backArrowIcon);
	private Span titleSpan = new Span();
	private Span orderDateSpan = new Span(); 
	private Span orderTimeSpan = new Span();
	private Span orderTimeSentSpan = new Span(); 
	private Span orderTimeDeliveredSpan = new Span();
	private Span orderStatusSpan=new Span(); 
	private Span orderTotalSumSpan=new Span();
	private Span orderPaymentType=new Span();   
	private final OrderPresenter presenter;
	
	public OrderView(OrderPresenter presenter) {
		this.presenter=presenter;
		presenter.setView(this);
		
		backButton.addClassName("brownButton");
		backButton.addClickListener(e->presenter.backClick());
		
		VerticalLayout mainContainer=new VerticalLayout();
		VerticalLayout orderInformationContainer=new VerticalLayout();
		orderInformationContainer.addClassName("orderContainer");
		orderInformationContainer.setSpacing(false);
		    
		titleSpan.setText("Porudzbina 1");
		titleSpan.addClassName("boldText2");

		orderDateSpan.setText("Datum Porudzbine: 15.21.2025"); 
		orderDateSpan.addClassName("whiteText");
		
		orderTimeSpan.setText("Vreme Porudzbine: 16:30");
		orderTimeSpan.addClassName("whiteText3");
		    
		orderTimeDeliveredSpan.setText("Porudzbina poslata: 17:03");
		orderTimeDeliveredSpan.addClassName("whiteText");
		
		orderTimeDeliveredSpan.setText("Vreme isporuke: 18:23");
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
		
		
		orderStatusSpan.setText("Status: U pripremi");
		orderStatusSpan.addClassName("whiteText3");
		
		orderTotalSumSpan.setText("Cena:"+totalSumDouble);    
		orderTotalSumSpan.addClassName("whiteText");
		
		orderPaymentType.setText("Nacin placanja: Kes");
		orderPaymentType.addClassName("whiteText4");
		
		orderInformationContainer.add(orderStatusSpan,orderTotalSumSpan,orderPaymentType);
		mainContainer.add(orderInformationContainer);
		mainContainer.setAlignItems(Alignment.CENTER);
		mainContainer.setJustifyContentMode(JustifyContentMode.CENTER);

		add(backButton,mainContainer);
	}

	@Override
	public void setTitleSpan(String title) {
		// TODO Auto-generated method stub presenter.getOrder.Title i service mozda 
		
	}

	@Override
	public void setOrderDateSpan(String orderDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOrderTimeSpan(String orderTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOrderTimeSentSpan(String timeSpent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOrderTimeDeliveredSpan(String orderTimeDelivered) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOrderStatusSpan(String orderStatus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOrderTotalSumSpan(String orderTotalSum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOrderPaymentType(String orderPaymentType) {
		// TODO Auto-generated method stub
		
	}
}
