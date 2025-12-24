package sitprojekat.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@CssImport("./style/style.css")
@Route(value = "Article",layout = HeaderAndNavBar.class)
public class ArticleView  extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4405949928698782190L;

	public ArticleView() {
		
		Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
		Button dugmeNazad=new Button("Povratak",backArrowIcon);
		//dugmeNazad.getStyle().set("background-color", "#3F220F");
		//dugmeNazad.getStyle().set("color", "#ffffff");
		dugmeNazad.addClassName("dugmeNazad");
		
		
		
		
		add(dugmeNazad);
	}

	
	
}
