package sitprojekat.view;

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

import sitprojekat.interfaces.HeaderAndNavBarInterface;
import sitprojekat.presenter.HeaderAndNavBarPresenter;

@CssImport("./style/style.css")
@StyleSheet("https://fonts.googleapis.com/css?family=Kaushan+Script")                       //import od googla za font 
public class HeaderAndNavBar extends AppLayout implements HeaderAndNavBarInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2259364331400139508L;
	
	private SideNav leftNav=new SideNav();
	private SideNavItem productsView=new SideNavItem("Pregled Proizvoda", "Products");
	private SideNavItem ordersView=new SideNavItem("Pregled Porudzbina", "Orders");
	private Span titleSpan=new Span("Ordering app");
	private Icon userIcon=VaadinIcon.USER.create();
	private Icon shoppingCartIcon=VaadinIcon.CART_O.create();
	private final HeaderAndNavBarPresenter presenter;
	
	public HeaderAndNavBar(HeaderAndNavBarPresenter presenter) {
		this.presenter=presenter;
		presenter.setView(this);
		
		
		createHeader();
		createNavBar();
		addClassName("greenBackground");
	}
	

	private void createNavBar() {
		
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
		
		titleSpan.addClassName("LogoText");
		
		userIcon.addClassName("userIcon");
		userIcon.addClickListener(e->presenter.userProfileScreen());
		
		shoppingCartIcon.addClassName("shoppingCartIcon");		
		shoppingCartIcon.addClickListener(e->presenter.shoppingCartScreen());
		
		HorizontalLayout iconContainer=new HorizontalLayout(userIcon,shoppingCartIcon);	
		HorizontalLayout naslovKontainer=new HorizontalLayout(titleSpan);
		HorizontalLayout header=new HorizontalLayout(naslovKontainer,iconContainer);
		
		header.addClassName("header");
		header.setDefaultVerticalComponentAlignment(Alignment.CENTER);
		header.setJustifyContentMode(JustifyContentMode.BETWEEN);
		 addToNavbar(header);
	}


	@Override
	public SideNavItem getProductsView() {
		return productsView;
	}

	@Override
	public SideNavItem getOrdersView() {
		return ordersView;
	}

}
