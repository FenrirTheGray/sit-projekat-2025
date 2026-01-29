package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@CssImport("./style/style-views.css")
@Route(value = "user/profile", layout = MasterHeaderNavLayout.class)
public class ProfileView extends VerticalLayout {
    // atributi
    //

    // konstruktor
    public ProfileView() {
        // naslov
        H1 naslov = new H1("Profil");
        naslov.getStyle().set("color", "white");
        add(naslov);

        // TODO: inicijalne metode
        // createSearchBarAndAddButton();
        // createProfileContainer();

        // TODO: dodavanje liste proizvoda

        // TODO: PRIVREMENO
        H1 ispis = new H1("Forma za korisnika");
        ispis.getStyle().set("color", "white");
        add(ispis);


        setSizeFull();
        setAlignItems(Alignment.CENTER);
    }
}
