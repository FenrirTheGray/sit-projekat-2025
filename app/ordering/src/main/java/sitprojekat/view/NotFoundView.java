package sitprojekat.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.router.RouteNotFoundError;

import jakarta.servlet.http.HttpServletResponse;

public class NotFoundView extends RouteNotFoundError {

	private static final long serialVersionUID = 1L;

	@Override
	public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<NotFoundException> parameter) {
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		layout.setJustifyContentMode(JustifyContentMode.CENTER);
		layout.setAlignItems(Alignment.CENTER);
		layout.getStyle().set("background-color", "#204824");

		H1 title = new H1("404 Not Found");
		title.getStyle().set("color", "#C9AB71");
		title.getStyle().set("font-size", "48px");

		Span message = new Span("Stranica koju tražite ne postoji.");
		message.getStyle().set("color", "#ffffff");
		message.getStyle().set("font-size", "20px");

		Button backButton = new Button("Povratak", VaadinIcon.ARROW_LEFT.create());
		backButton.getStyle().set("background-color", "#3F220F");
		backButton.getStyle().set("color", "#ffffff");
		backButton.getStyle().set("margin-top", "20px");
		backButton.addClickListener(e -> UI.getCurrent().navigate(""));

		layout.add(title, message, backButton);
		getElement().appendChild(layout.getElement());

		return HttpServletResponse.SC_NOT_FOUND;
	}
}
