package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;

import java.util.List;

@StyleSheet("https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap") //import od googla za font
@CssImport("./style/style-views.css") // import css-a
public class MasterLayout extends AppLayout {
	// atributi
	private List<SideNavItem> listaDugmica; // lista dugmića

	// konstruktor
	public MasterLayout() {
		createHeader();
		createSideNav();
		addClassName("master-layout-bg");
	}

	// metode
	public void createSideNav() {
		SideNav nav = new SideNav();

		SideNavItem artikliButton = new SideNavItem("Artikli");
		SideNavItem modifikatoriButton = new SideNavItem("Modifikatori");
		SideNavItem comboButton = new SideNavItem("Combo");
		SideNavItem kategorijeButton = new SideNavItem("Kategorije");

		// inicijalizacija liste dugmića
		listaDugmica = List.of(artikliButton, modifikatoriButton, comboButton, kategorijeButton);

		// početni izgled dugmića
		// listaDugmica.forEach(item -> item.setClassName("master-button-not-active")); // dupliranje
		updateSelection(artikliButton);

		artikliButton.getElement().addEventListener("click", e -> {
			UI.getCurrent().navigate("articles");
			updateSelection(artikliButton);
		});

		modifikatoriButton.getElement().addEventListener("click", e -> {
			UI.getCurrent().navigate("modifiers");
			updateSelection(modifikatoriButton);
		});

		comboButton.getElement().addEventListener("click", e -> {
			UI.getCurrent().navigate("combos");
			updateSelection(comboButton);
		});

		kategorijeButton.getElement().addEventListener("click", e -> {
			UI.getCurrent().navigate("categories");
			updateSelection(kategorijeButton);
		});

		nav.addItem(artikliButton, modifikatoriButton, comboButton, kategorijeButton);
		nav.addClassName("master-nav");

		VerticalLayout kontejnerNav = new VerticalLayout(nav);
		kontejnerNav.setSizeFull();
		kontejnerNav.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		kontejnerNav.setAlignItems(FlexComponent.Alignment.CENTER);
		kontejnerNav.setPadding(false);
		kontejnerNav.setSpacing(true);
		kontejnerNav.addClassName("master-kontejner-nav");

		addToDrawer(kontejnerNav);
	}

	public void createHeader() {
		Span title = new Span("CMS");
		title.addClassName("master-header-title");

		HorizontalLayout kontejnerTitle = new HorizontalLayout(title);

		Span iconKorisnik = new Span("A");
		iconKorisnik.addClassName("master-user-icon");

		Span labelKorisnik = new Span("Korisnik");
		labelKorisnik.addClassName("master-user-label");

		Span nameKorisnik = new Span("Admin"); // TODO: ubaciti da se prikazuje pravo korisničko ime Korisnika
		nameKorisnik.addClassName("master-user-name");

		// događaji
		// TODO: dodati događaj za kliktanje na ikonicu Korisnika

		VerticalLayout kontejnerLabele = new VerticalLayout(labelKorisnik, nameKorisnik);
		kontejnerLabele.setPadding(false);
		kontejnerLabele.setSpacing(false);
		// kontejnerLabele.addClassName("master-kontejner-labels");

		HorizontalLayout kontejnerKorisnik = new HorizontalLayout(iconKorisnik, kontejnerLabele);
		kontejnerKorisnik.setAlignItems(FlexComponent.Alignment.CENTER);
		kontejnerKorisnik.addClassName("master-kontejner-user");

		HorizontalLayout header = new HorizontalLayout(kontejnerTitle, kontejnerKorisnik);
		header.addClassName("master-header");
		header.setWidthFull();
		header.setHeight("60px");
		header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
		header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

		addToNavbar(header);
	}

	// pomoćna metoda za postavljanje "aktivnih" i "neaktivnih" dugmića
	private void updateSelection(SideNavItem selectedItem) {
		listaDugmica.forEach(item -> {
			if (item == selectedItem) {
				item.setClassName("master-button-active");
			} else {
				item.setClassName("master-button-not-active");
			}
		});
	}

}
