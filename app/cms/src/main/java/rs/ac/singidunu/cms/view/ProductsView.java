package rs.ac.singidunu.cms.view;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "products", layout = HeaderAndNavBar.class)

public class ProductsView extends VerticalLayout {

    public ProductsView() {
        add(new H2("Ovde će biti lista proizvoda iz ArangoDB-a"));

        // Ovde ćemo kasnije dodati Grid (tabelu)
        setSizeFull();
        setAlignItems(Alignment.CENTER);
    }

}
