package sitprojekat.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@CssImport("./style/style.css")
@Route(value = "404NotFound",layout = HeaderAndNavBar.class)
public class NotFoundView extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3125964915820738388L;


	public NotFoundView() {
		
		Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
		Button backButton=new Button("Povratak",backArrowIcon);
		backButton.addClassName("brownButton");
		
		
		H1 titleH1=new H1("404 NOT FOUND");
		titleH1.addClassName("NOTFOUND");
		
		
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);
		setSizeFull();
		add(titleH1,backButton);
	}
}
