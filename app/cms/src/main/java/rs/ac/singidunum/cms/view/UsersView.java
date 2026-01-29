package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "users/list", layout = MasterHeaderNavLayout.class)
@CssImport("./style/style-views.css")
public class UsersView extends VerticalLayout {
    // atributi
    // UsersService - servis za korisnike

    // konstruktor
    public UsersView() {
        // naslov
        H1 naslov = new H1("Korisnici");
        naslov.addClassName("page-title");
        add(naslov);

        // inicijalne metode
        // createSearchBarAndAddButton();
        // createUsersContainer();

        // TODO: dodavanje liste proizvoda


        setSizeFull();
        setAlignItems(Alignment.CENTER);
    }

    // metode
}
