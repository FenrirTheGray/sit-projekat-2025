package sitprojekat.view;

import java.util.List;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import sitprojekat.model.Proizvod;

@Route(value = "PregledProizvoda",layout = HeaderINavBar.class)
public class PregledProizvoda extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6116769739933744188L;
	
	public PregledProizvoda() {
		//getStyle().set("background-color", "#204824");// za side bar #20281f
		
		TextField textboxFilter=new TextField();
		
		
		
		textboxFilter.setValue("pretraga");
		textboxFilter.setClearButtonVisible(true);
		textboxFilter.setSuffixComponent(VaadinIcon.SEARCH.create());
		textboxFilter.getStyle().set("background-color", "#ffffff");
		textboxFilter.getStyle().set("border-radius", "30px");
		textboxFilter.setWidth("350px");
		//textboxFilter.getStyle().set("margin", "10px");
		textboxFilter.getStyle().set("padding", "0px");
		
		HorizontalLayout filterKontainer=new HorizontalLayout(textboxFilter);
		filterKontainer.setWidthFull();
		filterKontainer.setJustifyContentMode(JustifyContentMode.END);
		
		Grid<Proizvod> tabelaProizvoda=new Grid<>(Proizvod.class);
		tabelaProizvoda.setColumns("id","naziv","opis","cena");
		
		
		List<Proizvod> proizvodi=List.of(new Proizvod(1, "naziv1", "opis1", 250.0),new Proizvod(2, "naizv2", "opis2", 140.99));
		tabelaProizvoda.setItems(proizvodi);
		tabelaProizvoda.getStyle().set("background", "transparent");
		
		add(filterKontainer,tabelaProizvoda);
	}
	


}
