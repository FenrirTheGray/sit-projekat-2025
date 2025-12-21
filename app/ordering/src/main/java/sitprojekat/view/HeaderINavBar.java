package sitprojekat.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;

@StyleSheet("https://fonts.googleapis.com/css?family=Kaushan+Script")                       //import od googla za font 
public class HeaderINavBar extends AppLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2259364331400139508L;

	public HeaderINavBar() {
		napraviHeader();
		napraviNavBar();
		getStyle().set("background-color", "#204824");// za side bar #20281f
	}
	

	private void napraviNavBar() {
		SideNav leviNav=new SideNav();
		
		//SideNavItem pregledProizvoda=new SideNavItem("Pregled Proizvoda",PregledProizvoda.class); automatski bude plav ako je na toj stranici
		SideNavItem pregledProizvoda=new SideNavItem("Pregled Proizvoda");
		pregledProizvoda.getStyle().set("display", "flex");
		pregledProizvoda.getStyle().set("justify-content", "center"); 
		pregledProizvoda.getStyle().set("color", "white");
		pregledProizvoda.getStyle().set("background-color", "#3F220F");//nije aktivan             //aktivan #C9AB71 ima nacin za testiranje koji je aktiv treba namestiti lepo
		pregledProizvoda.getStyle().set("border-radius", "10px");
		pregledProizvoda.getStyle().set("margin", "10px");
		pregledProizvoda.getStyle().set("width", "230px");  //radi za svaki item nisam siguran zasto? 
		//pregledProizvoda.addClickListener(e->{UI.getCurrent().navigate("Korpa");}); mora sa get elemnt jer addclick nema
		pregledProizvoda.getElement().addEventListener("click", e->{UI.getCurrent().navigate("PregledProizvoda");});
		//pregledProizvoda.getStyle().set("cursor","pointer"); samo za spoljne strane oko teksta
		
		SideNavItem pregledPorudzbina=new SideNavItem("Pregled Porudzbina");
		pregledPorudzbina.getStyle().set("display", "flex");
		pregledPorudzbina.getStyle().set("justify-content", "center"); 
		pregledPorudzbina.getStyle().set("color", "white");
		pregledPorudzbina.getStyle().set("background-color", "#C9AB71");
		pregledPorudzbina.getStyle().set("border-radius", "10px");
		pregledPorudzbina.getStyle().set("margin", "10px");

		
		leviNav.addItem(pregledProizvoda,pregledPorudzbina);
		
//		leviNav.getStyle().set("background-color", "#20281f");// za side bar #20281f
		leviNav.getStyle().set("color", "white");
//		leviNav.setHeightFull();
//		leviNav.getStyle().set("align-items", "center");
		//leviNav.getItems().forEach(e->e.getStyle().set("cursor", "pointer")); daje svakom ali opet sredina nema
		
		VerticalLayout kontejnerZaLeviNav=new VerticalLayout(leviNav);
		kontejnerZaLeviNav.setSizeFull();
		kontejnerZaLeviNav.setJustifyContentMode(JustifyContentMode.CENTER);
		kontejnerZaLeviNav.setAlignItems(Alignment.CENTER);
		kontejnerZaLeviNav.setPadding(false);
		kontejnerZaLeviNav.setSpacing(true);
		
		kontejnerZaLeviNav.getStyle().set("background-color", "#20281f");
		
		addToDrawer(kontejnerZaLeviNav);
	}


	private void napraviHeader() {
		Span naslov=new Span("Ordering app");
		naslov.getStyle().set("color", "white");
		naslov.getStyle().set("font-family", "'Kaushan Script");
		naslov.getStyle().set("font-size", "32px");
		
		Icon userIkonica=VaadinIcon.USER.create();
		Icon korpaIkonica=VaadinIcon.CART_O.create();
		
		userIkonica.setColor("#8C6774");
		userIkonica.getStyle().set("background-color", "white");
		userIkonica.getStyle().set("border-radius", "5px");
		userIkonica.getStyle().set("cursor","pointer");
		
		userIkonica.addClickListener(e->{UI.getCurrent().navigate("KorisnikovProfil");});
		
		
		
		korpaIkonica.getStyle().set("margin-right", "10px");
		korpaIkonica.setColor("#74904C");
		korpaIkonica.getStyle().set("cursor","pointer");
		
		korpaIkonica.addClickListener(e->{UI.getCurrent().navigate("Korpa");});
		
		
		HorizontalLayout ikoniceKontainer=new HorizontalLayout(userIkonica,korpaIkonica);
		
		
		HorizontalLayout naslovKontainer=new HorizontalLayout(naslov);
		
		HorizontalLayout header=new HorizontalLayout(naslovKontainer,ikoniceKontainer);
		
		
		header.getStyle().set("background-color","#3F220F");                                      //#204824 pozadina
		header.setWidthFull();
		header.setHeight("60px");
		header.setDefaultVerticalComponentAlignment(Alignment.CENTER);
		header.setJustifyContentMode(JustifyContentMode.BETWEEN);
		addToNavbar(header);
	}
	
	
	
	
	
}
