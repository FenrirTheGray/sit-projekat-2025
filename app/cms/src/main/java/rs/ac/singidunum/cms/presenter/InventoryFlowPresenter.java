package rs.ac.singidunum.cms.presenter;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import rs.ac.singidunum.cms.view.InventoryFlowView;
import software.xdev.chartjs.model.charts.BarChart;
import software.xdev.chartjs.model.charts.LineChart;
import software.xdev.chartjs.model.data.BarData;
import software.xdev.chartjs.model.data.LineData;
import software.xdev.chartjs.model.dataset.BarDataset;
import software.xdev.chartjs.model.dataset.LineDataset;
import software.xdev.chartjs.model.options.LineOptions;

import java.time.LocalDate;

@SpringComponent
@UIScope
public class InventoryFlowPresenter {

    private InventoryFlowView view;

    public InventoryFlowPresenter(){

    }

    public void setView(InventoryFlowView view) {
        this.view = view;
    }

    public void dateValueChanged(){

        LocalDate start = this.view.startDatePicker.getValue();
        LocalDate end = this.view.endDatePicker.getValue();

        LineChart lineChart = new LineChart(
            new LineData()
                    .addLabel("Test")
                    .addDataset(new LineDataset()
                            .setLabel("A")
                            .setBackgroundColor("#ff0000")
                            .setBorderColor("#ff0000")
                            .setLineTension(0.2f)
                            .setData(55, 58, 60, 54)
                    )
                    .addLabel("First")
                    .addLabel("Second")
                    .addLabel("Third")
                    .addLabel("Fourth")
        ).setOptions(new LineOptions()
                .setResponsive(true)
                .setMaintainAspectRatio(false));


        this.view.mainChart.showChart(lineChart.toJson());
    }
}
