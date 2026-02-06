package sitprojekat.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import sitprojekat.presenter.NotFoundPresenter;


@CssImport("./style/style.css")
@Route(value = "404NotFound",layout = HeaderAndNavBar.class)
public class NotFoundView extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3125964915820738388L;
	
	private Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
	private Button backButton=new Button("Povratak",backArrowIcon);
	private NotFoundPresenter presenter;
	public NotFoundView(NotFoundPresenter presenter) {
		this.presenter=presenter;
		presenter.setView(this);
		
		
		backButton.addClassName("brownButton");
		backButton.addClickListener(e->presenter.backClick());
		
		H1 titleH1=new H1("404 NOT FOUND");
		titleH1.addClassName("NOTFOUND");
		
		
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);
		setSizeFull();
		add(titleH1,backButton);
	}

	




	
	
}
