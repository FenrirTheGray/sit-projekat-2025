package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@CssImport("./style/style-views.css")
@Route(value = "statistics/inventory-flow", layout = MasterHeaderNavLayout.class)
public class InventoryFlowView extends VerticalLayout {
    // atributi
    // SellersService - servis za promet

    // konstruktor
    public InventoryFlowView() {
        // naslov
        H1 naslov = new H1("Promet");
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
