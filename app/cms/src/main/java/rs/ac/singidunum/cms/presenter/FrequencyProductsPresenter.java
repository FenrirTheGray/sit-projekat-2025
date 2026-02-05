package rs.ac.singidunum.cms.presenter;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import rs.ac.singidunum.cms.view.FrequencyProductsView;

@SpringComponent
@UIScope
public class FrequencyProductsPresenter {
    private FrequencyProductsView view;

    public FrequencyProductsPresenter(){}

    public void setView(FrequencyProductsView view){
        this.view = view;
    }

    public void dateValueChanged(){

    }
}
