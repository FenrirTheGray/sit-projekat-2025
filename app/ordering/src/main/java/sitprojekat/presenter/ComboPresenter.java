package sitprojekat.presenter;

import sitprojekat.interfajsi.ComboViewInterface;
import sitprojekat.view.ComboView;

public class ComboPresenter {

	private ComboViewInterface view;
	
	public ComboPresenter() {
		
	}

	public void setView(ComboView view) {
		this.view=view;
		
	}
}
