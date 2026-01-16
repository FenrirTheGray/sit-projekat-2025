package rs.ac.singidunu.cms.view;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "articles", layout = MasterLayout.class)

public class ArticlesView extends VerticalLayout {

    public ArticlesView() {
        add(new H2("Ovde će biti lista proizvoda iz ArangoDB-a"));

        // Ovde ćemo kasnije dodati Grid (tabelu)
        setSizeFull();
        setAlignItems(Alignment.CENTER);
    }

}
