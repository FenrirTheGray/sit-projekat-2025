package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@CssImport("./style/style-views.css")
@Route(value = "statistics/frequency-products", layout = MasterHeaderNavLayout.class)
public class FrequencyProductsView extends VerticalLayout {
    // atributi
    // SellersService - servis za promet

    // konstruktor
    public FrequencyProductsView() {
        // naslov
        H1 naslov = new H1("Učestalost");
        naslov.getStyle().set("color", "white");
        add(naslov);

        // inicijalne metode
        // createSearchBarAndAddButton();
        // createArticlesContainer();

        // TODO: dodavanje liste proizvoda


        setSizeFull();
        setAlignItems(Alignment.CENTER);
    }
}
