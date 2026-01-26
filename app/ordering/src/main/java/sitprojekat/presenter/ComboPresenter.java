package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import sitprojekat.interfaces.ComboViewInterface;
import sitprojekat.view.ComboView;

@Component
public class ComboPresenter {

	private ComboViewInterface view;
	
	public ComboPresenter() {
		
	}

	public void setView(ComboView view) {
		this.view=view;
		
	}
}
