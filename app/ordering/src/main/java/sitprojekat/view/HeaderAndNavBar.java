package sitprojekat.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
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
public class HeaderAndNavBar extends AppLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2259364331400139508L;

	public HeaderAndNavBar() {
		createHeader();
		createNavBar();
		getStyle().set("background-color", "#204824");// za side bar #20281f
	}
	

	private void createNavBar() {
		SideNav leftNav=new SideNav();
		
		//SideNavItem pregledProizvoda=new SideNavItem("Pregled Proizvoda",PregledProizvoda.class); automatski bude plav ako je na toj stranici
		SideNavItem productsView=new SideNavItem("Pregled Proizvoda");
		productsView.getStyle().set("display", "flex");
		productsView.getStyle().set("justify-content", "center"); 
		productsView.getStyle().set("color", "white");
		productsView.getStyle().set("background-color", "#3F220F");//nije aktivan             //aktivan #C9AB71 ima nacin za testiranje koji je aktiv treba namestiti lepo
		productsView.getStyle().set("border-radius", "10px");
		productsView.getStyle().set("margin", "10px");
		productsView.getStyle().set("width", "230px");//radi za svaki item nisam siguran zasto? 
		//pregledProizvoda.setClassName("");
		//pregledProizvoda.addClickListener(e->{UI.getCurrent().navigate("Korpa");}); mora sa get elemnt jer addclick nema
		productsView.getElement().addEventListener("click", e->{UI.getCurrent().navigate("Products");}); //za rout
		//pregledProizvoda.getElement().addEventListener("click", e->{UI.getCurrent().getPage().setLocation("https://www.google.com");}); za url
		//pregledProizvoda.getElement().addEventListener("click", e->{UI.getCurrent().getPage().open("https://www.google.com");}); otvara novu stranicu
		//pregledProizvoda.getElement().addEventListener("click", e->{UI.getCurrent().navigate("https://www.google.com/");});
		//pregledProizvoda.getStyle().set("cursor","pointer"); samo za spoljne strane oko teksta
		
		SideNavItem ordersView=new SideNavItem("Pregled Porudzbina");
		ordersView.getStyle().set("display", "flex");
		ordersView.getStyle().set("justify-content", "center"); 
		ordersView.getStyle().set("color", "white");
		ordersView.getStyle().set("background-color", "#C9AB71");
		ordersView.getStyle().set("border-radius", "10px");
		ordersView.getStyle().set("margin", "10px");

		
		leftNav.addItem(productsView,ordersView);
		
//		leviNav.getStyle().set("background-color", "#20281f");// za side bar #20281f
		leftNav.getStyle().set("color", "white");
//		leviNav.setHeightFull();
//		leviNav.getStyle().set("align-items", "center");
		//leviNav.getItems().forEach(e->e.getStyle().set("cursor", "pointer")); daje svakom ali opet sredina nema
		
		VerticalLayout containerForLeftNav=new VerticalLayout(leftNav);
		containerForLeftNav.setSizeFull();
		containerForLeftNav.setJustifyContentMode(JustifyContentMode.CENTER);
		containerForLeftNav.setAlignItems(Alignment.CENTER);
		containerForLeftNav.setPadding(false);
		containerForLeftNav.setSpacing(true);
		
		containerForLeftNav.getStyle().set("background-color", "#20281f");
		
		addToDrawer(containerForLeftNav);
	}


	private void createHeader() {
		Span title=new Span("Ordering app");
		title.getStyle().set("color", "white");
		title.getStyle().set("font-family", "'Kaushan Script");
		title.getStyle().set("font-size", "32px");
		
		Icon userIcon=VaadinIcon.USER.create();
		Icon shoppingCartIcon=VaadinIcon.CART_O.create();
		
		userIcon.setColor("#8C6774");
		userIcon.getStyle().set("background-color", "white");
		userIcon.getStyle().set("border-radius", "5px");
		userIcon.getStyle().set("cursor","pointer");
		
		userIcon.addClickListener(e->{UI.getCurrent().navigate("UserProfile");});
		
		
		
		shoppingCartIcon.getStyle().set("margin-right", "10px");
		shoppingCartIcon.setColor("#74904C");
		shoppingCartIcon.getStyle().set("cursor","pointer");
		
		shoppingCartIcon.addClickListener(e->{UI.getCurrent().navigate("Cart");});
		
		
		HorizontalLayout iconContainer=new HorizontalLayout(userIcon,shoppingCartIcon);
		
		
		HorizontalLayout naslovKontainer=new HorizontalLayout(title);
		
		HorizontalLayout header=new HorizontalLayout(naslovKontainer,iconContainer);
		
		
		header.getStyle().set("background-color","#3F220F");                                      //#204824 pozadina
		header.setWidthFull();
		header.setHeight("60px");
		header.setDefaultVerticalComponentAlignment(Alignment.CENTER);
		header.setJustifyContentMode(JustifyContentMode.BETWEEN);
		addToNavbar(header);
	}
	
	
	
	
	
}
