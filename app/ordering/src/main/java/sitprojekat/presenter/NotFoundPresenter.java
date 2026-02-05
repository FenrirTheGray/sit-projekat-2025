package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;

import sitprojekat.view.NotFoundView;

@Component
public class NotFoundPresenter {
 
	private NotFoundView view;
	
	public NotFoundPresenter() {
    }
	
	public NotFoundPresenter(NotFoundView view) {
		this.view = view;
	}

	public void backClick() {
		UI.getCurrent().getPage().getHistory().back();
	}

	public void setView(NotFoundView view) {
		this.view=view;
		
	}
	
	
}
