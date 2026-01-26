package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;

import sitprojekat.interfaces.NotFoundViewInterface;
import sitprojekat.view.NotFoundView;

@Component
public class NotFoundPresenter {
 
	private NotFoundViewInterface view;
	
	public NotFoundPresenter() {
    }
	
	public NotFoundPresenter(NotFoundViewInterface view) {
		this.view = view;
	}

	public void backClick() {
		UI.getCurrent().getPage().getHistory().back();
	}

	public void setView(NotFoundViewInterface view) {
		this.view=view;
		
	}
	
	
}
