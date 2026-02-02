package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;

import java.util.ArrayList;
import java.util.List;

@StyleSheet("https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap")
@CssImport("./style/style-views.css")
@CssImport("./style/style-master.css")
public class MasterHeaderNavLayout extends AppLayout implements AfterNavigationObserver {
    // atributi
    private List<SideNavItem> listaDugmica = new ArrayList<>();

    // konstruktor
    public MasterHeaderNavLayout() {
        createHeader();
        createSideNav();
        setPrimarySection(Section.NAVBAR);
        addClassName("master-layout-bg");
    }

    // metode
    public void createHeader() {
        // naslov (CMS)
        Span title = new Span("CMS");
        title.addClassName("master-header-title");

        HorizontalLayout kontejnerTitle = new HorizontalLayout(title);

        // ikonica i tekst za korisnika
        Span iconKorisnik = new Span("A");
        iconKorisnik.addClassName("master-user-icon");

        ContextMenu userMenu = new ContextMenu();
        userMenu.setTarget(iconKorisnik);
        userMenu.setOpenOnClick(true);
        userMenu.addItem("Podešavanja", e -> {
            UI.getCurrent().navigate("user/profile");
        });
        userMenu.add(new Hr());
        userMenu.addItem("Odjava", e -> {
            UI.getCurrent().navigate("");
        });

        Span labelKorisnik = new Span("Korisnik");
        labelKorisnik.addClassName("master-user-label");

        Span nameKorisnik = new Span("Admin");
        nameKorisnik.addClassName("master-user-name");

        VerticalLayout kontejnerLabele = new VerticalLayout(labelKorisnik, nameKorisnik);
        kontejnerLabele.setPadding(false);
        kontejnerLabele.setSpacing(false);

        HorizontalLayout kontejnerKorisnik = new HorizontalLayout(iconKorisnik, kontejnerLabele);
        kontejnerKorisnik.setAlignItems(FlexComponent.Alignment.CENTER);
        kontejnerKorisnik.addClassName("master-kontejner-user");

        HorizontalLayout header = new HorizontalLayout(kontejnerTitle, kontejnerKorisnik);
        header.addClassName("master-header");
        header.setWidthFull();
        header.setHeight("70px");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        addToNavbar(header);
    }

    public void createSideNav() {
        VerticalLayout kontejnerNav = new VerticalLayout();

        SideNav navProizvodi = new SideNav();
        navProizvodi.addClassName("master-nav");
        SideNav navStatistika = new SideNav();
        navStatistika.addClassName("master-nav");
        SideNav navKorisnici = new SideNav();
        navKorisnici.addClassName("master-nav");

        Span proizvodiLabel = new Span("Proizvodi");
        proizvodiLabel.addClassName("master-labels");
        Span stastikaLabel = new Span("Statistika");
        stastikaLabel.addClassName("master-labels");
        Span korisniciLabel = new Span("Korisnici");
        korisniciLabel.addClassName("master-labels");

        listaDugmica.clear();

        SideNavItem artikliButton = kreirajStavku("Artikli", "products/articles");
        SideNavItem modifButton = kreirajStavku("Modifikatori", "products/modifiers");
        SideNavItem comboButton = kreirajStavku("Combo", "products/combos");
        SideNavItem kategorijeButton = kreirajStavku("Kategorije", "products/categories");

        navProizvodi.addItem(artikliButton, modifButton, comboButton, kategorijeButton);

        SideNavItem prometButton = kreirajStavku("Promet", "statistics/inventory-flow");
        SideNavItem ucestalostButton = kreirajStavku("Učestalost", "statistics/frequency-products");
        SideNavItem najprodavanijiButton = kreirajStavku("Najprodavaniji", "statistics/best-sellers");

        navStatistika.addItem(prometButton, ucestalostButton, najprodavanijiButton);

        SideNavItem korisniciButton = kreirajStavku("Korisnici", "users/list");

        navKorisnici.addItem(korisniciButton);

        kontejnerNav.add(proizvodiLabel, navProizvodi, stastikaLabel, navStatistika, korisniciLabel, navKorisnici);
        kontejnerNav.setSizeFull();
        kontejnerNav.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        kontejnerNav.setAlignItems(FlexComponent.Alignment.CENTER);
        kontejnerNav.addClassName("master-kontejner-nav");

        addToDrawer(kontejnerNav);
    }

    private void updateSelection(SideNavItem selectedItem) {
        listaDugmica.forEach(item -> {
            if (item == selectedItem) {
                item.setClassName("master-button-active");
            } else {
                item.setClassName("master-button-not-active");
            }
        });
    }

    private SideNavItem kreirajStavku(String label, String ruta) {
        SideNavItem item = new SideNavItem(label);
        item.getElement().addEventListener("click", e -> {
            UI.getCurrent().navigate(ruta);
            updateSelection(item);
        });
        listaDugmica.add(item);
        return item;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        String path = event.getLocation().getPath();

        if (path.contains("profile")) {
            updateSelection(null);
            return;
        }

        for (SideNavItem item : listaDugmica) {
            String labelLower = item.getLabel().toLowerCase();
            if (path.contains(labelLower.substring(0, Math.min(labelLower.length(), 4)))) {
                updateSelection(item);
                break;
            }
        }
    }
}
