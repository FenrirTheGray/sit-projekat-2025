package sitprojekat.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "Main",layout = HeaderAndNavBar.class)
public class MainView extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6313156717813295316L;

	public MainView() {
		add(new H1("nesto nesto nesto nesto safasfafsa"));
	}

	
}
