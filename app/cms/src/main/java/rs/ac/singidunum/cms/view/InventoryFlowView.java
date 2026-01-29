package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "statistics/inventory-flow", layout = MasterHeaderNavLayout.class)
@CssImport("./style/style-views.css")
public class InventoryFlowView extends VerticalLayout {
    // atributi
    // SellersService - servis za promet

    // konstruktor
    public InventoryFlowView() {
        // naslov
        H1 naslov = new H1("Promet");
        naslov.addClassName("page-title");
        add(naslov);

        // inicijalne metode
        // createSearchBarAndAddButton();
        // createArticlesContainer();

        // TODO: dodavanje liste proizvoda


        setSizeFull();
        setAlignItems(Alignment.CENTER);
    }
}
