package rs.ac.singidunum.cms.presenter;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import rs.ac.singidunum.cms.view.BestSellersView;

@SpringComponent
@UIScope

public class BestSellersPresenter {

    private BestSellersView view;

    public BestSellersPresenter(){}

    public void setView(BestSellersView view){
        this.view = view;
    }

    public void dateValueChanged(){
        /*        this.view.mainChart.showChart(new BarChart(new BarData()
                .addLabels("A")
                .addDataset(new BarDataset()
                        .setBackgroundColor("#228811")
                        .setLabel("X")
                        .addData(1))
                .addDataset(new BarDataset()
                        .setBackgroundColor("#882211")
                        .setLabel("Y")
                        .addData(2)))
                .toJson());*/
    }
}
