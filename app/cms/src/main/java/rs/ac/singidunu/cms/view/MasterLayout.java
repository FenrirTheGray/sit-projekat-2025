package rs.ac.singidunu.cms.view;

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

import java.util.List;

@StyleSheet("https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap") //import od googla za font
@CssImport("./style/style.css") // import css-a
public class MasterLayout extends AppLayout {
	// AppLayout = komponenta za root layout, koja ima 3 dela: Content, Nav i Drawer (side menu)

	private static final long serialVersionUID = -2259364331400139508L;

	// konstruktor
	public MasterLayout() {
		getElement().setAttribute("theme", "dark");
		// Header i Nav su zajednički za sve prozore (view-ove)
		createHeader();
		createSideNav();
		// Content ("createContentBar()" ne postoji) = uređuje svaki prozor (view) za sebe, zato nema posebnu metodu
		addClassName("master-layout-bg");
	}

	// metode
	private void createSideNav() {
		// glavni kontejner (side nav)
		SideNav nav = new SideNav();

		// elementi (dugmići) navigacije
		SideNavItem artikli = new SideNavItem("Artikli");
		SideNavItem modifikatori = new SideNavItem("Modifikatori"); // TODO: Privremeno na istu klasu
		SideNavItem kombo = new SideNavItem("Kombo"); // TODO: Privremeno na istu klasu
		SideNavItem kategorije = new SideNavItem("Kategorije"); // TODO: Privremeno na istu klasu

		// stilovi za svaki element
		List.of(artikli, modifikatori, kombo, kategorije).forEach(item -> {
			item.getStyle().set("display", "flex");
			item.getStyle().set("justify-content", "center");
			item.getStyle().set("background-color", "#272727");
			item.getStyle().set("color", "#b3b3b3");
			item.getStyle().set("border-radius", "8px");
			item.getStyle().set("margin", "10px");
			item.getStyle().set("width", "230px");
			item.getStyle().set("cursor", "pointer");
		});

		// TODO: ubaciti funkcionalnost dugmića

		// ubacujemo stavke (elemente) u navigaciju
		nav.addItem(artikli, modifikatori, kombo, kategorije);

		// pakujemo sve u vertikalni layout
		VerticalLayout navWrapper = new VerticalLayout(nav);
		navWrapper.setSizeFull();
		navWrapper.setJustifyContentMode(JustifyContentMode.CENTER);
		navWrapper.setAlignItems(Alignment.CENTER);
		navWrapper.getStyle().set("background-color", "#202020"); // pozadina celog levog menija

		// fiksiranje nav kutije na levi slot layout-a
		addToDrawer(navWrapper);
	}

	private void createHeader() {
		// elementi za 1. kontejner => title kontejner
		// element: naslov
		Span title = new Span("CMS");
		title.addClassName("header-title");

		// stavljamo naslov u njegovu kutiju => title kontejner
		HorizontalLayout titleContainer = new HorizontalLayout(title);

		// elementi za 2. kontejner => icon kontejner
		// element: ikonica
		Span avatar = new Span("F"); // Kao na tvojoj slici
		avatar.addClassName("user-avatar");
		// element: tekst "Korisnik"
		Span labelKorisnik = new Span("Korisnik");
		labelKorisnik.addClassName("user-label-top");
		// element: korisničko ime
		Span labelIme = new Span("Admin"); // TODO: Kasnije će ovde ići ime iz baze
		labelIme.addClassName("user-label-name");

		// mini kutija (kontejner) za tekstove
		VerticalLayout userTextWrapper = new VerticalLayout(labelKorisnik, labelIme);
		userTextWrapper.setPadding(false);
		userTextWrapper.setSpacing(false);
		userTextWrapper.addClassName("user-text-wrapper");

		// stavljamo ikonicu i tekstove u njihovu kutiju => icon kontejner
		HorizontalLayout userProfileBox = new HorizontalLayout(avatar, userTextWrapper);
		userProfileBox.setAlignItems(Alignment.CENTER);
		userProfileBox.addClassName("user-profile-box");

		// userProfileBox.addClickListener(e -> UI.getCurrent().navigate("UserProfile"));

		// glavna kutija (kontejner) => header
		HorizontalLayout header = new HorizontalLayout(titleContainer, userProfileBox);

		// podešavanja glavne kutije
		header.addClassName("main-header");
		header.setWidthFull();
		header.setHeight("60px");
		header.setDefaultVerticalComponentAlignment(Alignment.CENTER);
		header.setJustifyContentMode(JustifyContentMode.BETWEEN);

		// fiksiranje header kutije na gornji slot layout-a
		addToNavbar(header);
	}
	
}
