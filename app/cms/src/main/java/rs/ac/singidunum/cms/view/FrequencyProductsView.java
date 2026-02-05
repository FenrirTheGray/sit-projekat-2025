package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import rs.ac.singidunum.cms.presenter.FrequencyProductsPresenter;

@Route(value = "statistics/frequency-products", layout = MasterHeaderNavLayout.class)
@CssImport("./style/style-views.css")
public class FrequencyProductsView extends VerticalLayout {

    private FrequencyProductsPresenter presenter;

    public FrequencyProductsView(FrequencyProductsPresenter presenter) {

        this.presenter = presenter;
        this.presenter.setView(this);

        H1 naslov = new H1("Učestalost");
        naslov.addClassName("page-title");
        add(naslov);



        setSizeFull();
        setAlignItems(Alignment.CENTER);
    }
}
