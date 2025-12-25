package sitprojekat.view;

import java.util.List;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
	
	
	public ProductsView(ArticleService service) {
		//getStyle().set("background-color", "#204824");// za side bar #20281f
		
		this.service=service;
		
		TextField filterTextBox=new TextField();
		
		
		
		filterTextBox.setValue("pretraga");
		filterTextBox.setClearButtonVisible(true);
		filterTextBox.setSuffixComponent(VaadinIcon.SEARCH.create());
		filterTextBox.getStyle().set("background-color", "#ffffff");
		filterTextBox.getStyle().set("border-radius", "30px");
		filterTextBox.setWidth("350px");
		//textboxFilter.getStyle().set("margin", "10px");
		filterTextBox.getStyle().set("padding", "0px");
		
		HorizontalLayout filterContainer=new HorizontalLayout(filterTextBox);
		filterContainer.setWidthFull();
		filterContainer.setJustifyContentMode(JustifyContentMode.END);
		
		Grid<Article> articleTable=new Grid<>(Article.class);
		articleTable.setColumns("id","name","description","basePrice","active");

		
		
		//List<Article> articles=List.of(new Article("1", "naziv1", "opis1", 250.0, true));
		
		List<Article> articles=service.getArticles();
		articleTable.setItems(articles);
		
		articleTable.getStyle().set("background", "transparent");
		
		articleTable.addItemDoubleClickListener(e->{
			Article article=e.getItem();
		   // Notification.show("artikal ima : " +article.getId()+" "+article.getBasePrice());
			UI.getCurrent().navigate(ArticleView.class,article.getId());
			
		});
		
		add(filterContainer,articleTable);
	}
	


}
