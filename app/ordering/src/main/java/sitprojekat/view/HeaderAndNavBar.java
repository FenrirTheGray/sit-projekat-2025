package sitprojekat.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
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

@CssImport("./style/style.css")
@StyleSheet("https://fonts.googleapis.com/css?family=Kaushan+Script")                       //import od googla za font 
public class HeaderAndNavBar extends AppLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2259364331400139508L;

	public HeaderAndNavBar() {
		createHeader();
		createNavBar();
		addClassName("greenBackground");// za side bar #20281f
	}
	

	private void createNavBar() {
		SideNav leftNav=new SideNav();
		
		//SideNavItem pregledProizvoda=new SideNavItem("Pregled Proizvoda",PregledProizvoda.class); automatski bude plav ako je na toj stranici
		SideNavItem productsView=new SideNavItem("Pregled Proizvoda");
		productsView.addClassName("SideNavItemNotActive");
		//pregledProizvoda.addClickListener(e->{UI.getCurrent().navigate("Korpa");}); mora sa get elemnt jer addclick nema
		productsView.getElement().addEventListener("click", e->{UI.getCurrent().navigate("Products");}); //za rout
		//pregledProizvoda.getElement().addEventListener("click", e->{UI.getCurrent().getPage().setLocation("https://www.google.com");}); za url
		//pregledProizvoda.getElement().addEventListener("click", e->{UI.getCurrent().getPage().open("https://www.google.com");}); otvara novu stranicu
		//pregledProizvoda.getElement().addEventListener("click", e->{UI.getCurrent().navigate("https://www.google.com/");});
		//pregledProizvoda.getStyle().set("cursor","pointer"); samo za spoljne strane oko teksta
		
		SideNavItem ordersView=new SideNavItem("Pregled Porudzbina");
		ordersView.addClassName("SideNavItemActive");

		
		leftNav.addItem(productsView,ordersView);
		

		leftNav.addClassName("leftNav");

		
		VerticalLayout containerForLeftNav=new VerticalLayout(leftNav);
		containerForLeftNav.setSizeFull();
		containerForLeftNav.setJustifyContentMode(JustifyContentMode.CENTER);
		containerForLeftNav.setAlignItems(Alignment.CENTER);
		containerForLeftNav.setPadding(false);
		containerForLeftNav.setSpacing(true);
		
		containerForLeftNav.addClassName("choiceBackground");
		
		addToDrawer(containerForLeftNav);
	}


	private void createHeader() {
		Span titleSpan=new Span("Ordering app");
		titleSpan.addClassName("LogoText");
		
		Icon userIcon=VaadinIcon.USER.create();
		Icon shoppingCartIcon=VaadinIcon.CART_O.create();
		
		userIcon.addClassName("userIcon");
		
		userIcon.addClickListener(e->{UI.getCurrent().navigate("UserProfile");});
		
		shoppingCartIcon.addClassName("shoppingCartIcon");
		
		shoppingCartIcon.addClickListener(e->{UI.getCurrent().navigate("Cart");});
		
		
		HorizontalLayout iconContainer=new HorizontalLayout(userIcon,shoppingCartIcon);
		
		
		HorizontalLayout naslovKontainer=new HorizontalLayout(titleSpan);
		
		HorizontalLayout header=new HorizontalLayout(naslovKontainer,iconContainer);
		
		
		header.addClassName("header");
		//header.setWidthFull();
		header.setDefaultVerticalComponentAlignment(Alignment.CENTER);
		header.setJustifyContentMode(JustifyContentMode.BETWEEN);
		addToNavbar(header);
	}
	
	
	
	
	
}
