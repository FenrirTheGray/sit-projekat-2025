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
@CssImport("./style/style-master.css")
public class MasterHeaderNavLayout extends AppLayout implements AfterNavigationObserver {
    // atributi
    private List<SideNavItem> listaDugmica = new ArrayList<>(); // lista dugmića

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
        Span iconKorisnik = new Span("A"); // TODO: promeniti da ikonica bude prvo slovo imena Korisnika
        iconKorisnik.addClassName("master-user-icon");
        // kreiranje context menija (korisnički meni) za ikonicu slova
        ContextMenu userMenu = new ContextMenu();
        userMenu.setTarget(iconKorisnik); // povezivanje sa ikonicom
        userMenu.setOpenOnClick(true);
        // dodavanje događaja
        // događaj "Podešavanja"
        userMenu.addItem("Podešavanja", e -> {
            UI.getCurrent().navigate("user/profile");
        });
        // linija između opcija
        userMenu.add(new Hr());
        // događaj "Odjava"
        userMenu.addItem("Odjava", e -> {
            UI.getCurrent().navigate(""); // "" == default ruta
        });

        Span labelKorisnik = new Span("Korisnik");
        labelKorisnik.addClassName("master-user-label");

        Span nameKorisnik = new Span("Admin"); // TODO: ubaciti da se prikazuje pravo korisničko ime Korisnika
        nameKorisnik.addClassName("master-user-name");

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
        header.setHeight("70px");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        addToNavbar(header);
    }

    public void createSideNav() {
        // glavni kontejner
        VerticalLayout kontejnerNav = new VerticalLayout();

        // pomoćni kontejneri
        SideNav navProizvodi = new SideNav();
        navProizvodi.addClassName("master-nav");
        SideNav navStatistika = new SideNav();
        navProizvodi.addClassName("master-nav");
        SideNav navKorisnici = new SideNav();
        navProizvodi.addClassName("master-nav");

        // labele
        Span proizvodiLabel = new Span("Proizvodi");
        proizvodiLabel.addClassName("master-labels");
        Span stastikaLabel = new Span("Statistika");
        stastikaLabel.addClassName("master-labels");
        Span korisniciLabel = new Span("Korisnici");
        korisniciLabel.addClassName("master-labels");

        listaDugmica.clear();

        // buttoni: PROIZVODI
        SideNavItem artikliButton = kreirajStavku("Artikli", "products/articles");
        SideNavItem modifButton = kreirajStavku("Modifikatori", "products/modifiers");
        SideNavItem comboButton = kreirajStavku("Combo", "products/combos");
        SideNavItem kategorijeButton = kreirajStavku("Kategorije", "products/categories");

        navProizvodi.addItem(artikliButton, modifButton, comboButton, kategorijeButton);

        // buttoni: STATISTIKA
        SideNavItem prometButton = kreirajStavku("Promet", "statistics/inventory-flow");
        SideNavItem ucestalostButton = kreirajStavku("Učestalost", "statistics/frequency-products");
        SideNavItem najprodavanijiButton = kreirajStavku("Najprodavaniji", "statistics/best-sellers");

        navStatistika.addItem(prometButton, ucestalostButton, najprodavanijiButton);

        // buttoni: KORISNICI
        SideNavItem korisniciButton = kreirajStavku("Korisnici", "users/list");

        navKorisnici.addItem(korisniciButton);

        // dodavanje svega u Drawer
        kontejnerNav.add(proizvodiLabel, navProizvodi, stastikaLabel, navStatistika, korisniciLabel, navKorisnici);
        kontejnerNav.setSizeFull();
        kontejnerNav.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        kontejnerNav.setAlignItems(FlexComponent.Alignment.CENTER);
        kontejnerNav.addClassName("master-kontejner-nav");

        addToDrawer(kontejnerNav);
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

    // pomoćna metoda za navigaciju dugmića
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
            if (path.contains(labelLower.substring(0, Math.min(labelLower.length(), 4)))) { // fuzzy matching
                updateSelection(item);
                break;
            }
        }
    }
}
