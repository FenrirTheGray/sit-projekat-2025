package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "categories", layout = MasterLayout.class)
public class CategoriesView extends VerticalLayout {
    // atributi

    // konstruktor
    public CategoriesView() {
        H1 naslov = new H1("Kategorije");
        naslov.getStyle().set("color", "white");
        add(naslov);

        // TODO: dodavanje liste proizvoda
        add(new H2("Ovde će biti lista kategorija iz ArangoDB-a"));

        setSizeFull();
        setAlignItems(Alignment.CENTER);
    }
}
