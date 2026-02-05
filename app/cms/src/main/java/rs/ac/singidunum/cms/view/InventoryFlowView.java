package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import rs.ac.singidunum.cms.presenter.InventoryFlowPresenter;
import software.xdev.vaadin.chartjs.ChartContainer;

import java.time.LocalDate;

@Route(value = "statistics/inventory-flow", layout = MasterHeaderNavLayout.class)
@CssImport("./style/style-views.css")
public class InventoryFlowView extends VerticalLayout {

    private InventoryFlowPresenter presenter;

    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public ChartContainer mainChart;

    public InventoryFlowView(InventoryFlowPresenter presenter) {

        this.presenter = presenter;
        this.presenter.setView(this);

        H1 naslov = new H1("Promet");
        naslov.addClassName("page-title");
        add(naslov);


        startDatePicker = new DatePicker("Početni datum");
        startDatePicker.setRequiredIndicatorVisible(true);
        startDatePicker.setMax(LocalDate.now());

        endDatePicker = new DatePicker("Krajnji datum");
        endDatePicker.setRequiredIndicatorVisible(true);
        endDatePicker.setMax(LocalDate.now());

        startDatePicker.addValueChangeListener(e -> {
            presenter.dateValueChanged();
        });

        HorizontalLayout dateLayout = new HorizontalLayout();
        dateLayout.add(startDatePicker);
        dateLayout.add(endDatePicker);

        add(dateLayout);

        mainChart = new ChartContainer();
        mainChart.addClassName("base-chart");
        add(mainChart);


        setSizeFull();
        setAlignItems(Alignment.CENTER);
    }
}
