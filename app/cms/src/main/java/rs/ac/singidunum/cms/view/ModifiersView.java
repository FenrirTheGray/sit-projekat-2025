package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@CssImport("./style/style-views.css")
@Route(value = "modifiers", layout = MasterLayout.class)
public class ModifiersView extends VerticalLayout {
    // atributi

    // konstruktor
    public ModifiersView() {
        H1 naslov = new H1("Modifikatori");
        naslov.getStyle().set("color", "white");
        add(naslov);

        // TODO: dodavanje liste proizvoda
        add(new H2("Ovde će biti lista modifikatora iz ArangoDB-a"));

        setSizeFull();
        setAlignItems(Alignment.CENTER);
    }

}
