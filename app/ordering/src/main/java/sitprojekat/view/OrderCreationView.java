package sitprojekat.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import sitprojekat.interfajsi.OrderCreationViewInterface;
import sitprojekat.presenter.OrderCreationPresenter;

@Route(value = "OrderCreation", layout = HeaderAndNavBar.class)
public class OrderCreationView extends VerticalLayout implements OrderCreationViewInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8829602058718508863L;

	private String choice = "";
	private H2 titleH2 = new H2();
	private final OrderCreationPresenter presenter;

	public OrderCreationView(OrderCreationPresenter presenter) {
		this.presenter = presenter;
		presenter.setView(this);

		Icon backArrowIcon = VaadinIcon.ARROW_BACKWARD.create();
		Button backButton = new Button("Povratak", backArrowIcon);
		backButton.addClassName("brownButton");
		backButton.getStyle().set("cursor", "pointer");
		backButton.addClickListener(e -> presenter.backClick());
		
		presenter.setTotalPrice();
		titleH2.addClassName("whiteText");

		Icon homeIcon = VaadinIcon.HOME_O.create();
		homeIcon.addClassName("icon");
		TextField addressTextField = new TextField();
		addressTextField.setPrefixComponent(homeIcon);
		addressTextField.setPlaceholder("Adresa");
		addressTextField.addClassName("inputField");

		Icon phoneIcon = VaadinIcon.PHONE.create();
		phoneIcon.addClassName("icon");
		TextField telephoneTextField = new TextField();
		telephoneTextField.setPrefixComponent(phoneIcon);
		telephoneTextField.setPlaceholder("Broj telefona");
		telephoneTextField.addClassName("inputField");

		Span payingOptionSpan = new Span();
		payingOptionSpan.setText("Vrsta Placanja:");
		payingOptionSpan.addClassName("centeredText");

		Button cardChoiceButton = new Button();
		cardChoiceButton.setText("Kartica");
		cardChoiceButton.addClassName("brownButton");

		Button cashChoiceButton = new Button();
		cashChoiceButton.setText("Gotovina");
		cashChoiceButton.addClassName("brownButton");

		cardChoiceButton.addClickListener(e -> {
			choice = "card";
			cardChoiceButton.addClassName("greenButton");
			cardChoiceButton.removeClassName("brownButton");

			cashChoiceButton.addClassName("brownButton");
			cashChoiceButton.removeClassName("greenButton");
		});

		cashChoiceButton.addClickListener(e -> {
			choice = "cash";
			cashChoiceButton.addClassName("greenButton");
			cashChoiceButton.removeClassName("brownButton");

			cardChoiceButton.addClassName("brownButton");
			cardChoiceButton.removeClassName("greenButton");
		});

		VerticalLayout orderingContainer = new VerticalLayout();

		HorizontalLayout payingOptionChoiceContainer = new HorizontalLayout();
		payingOptionChoiceContainer.add(cardChoiceButton, cashChoiceButton);
		payingOptionChoiceContainer.setJustifyContentMode(JustifyContentMode.CENTER);
		payingOptionChoiceContainer.addClassName("payingOptionChoiceContainer");

		Button createOrderButton = new Button();
		createOrderButton.setText("Kreiraj Porudzbinu");
		createOrderButton.addClassName("createOrderButton");

		Span disclamerSpan = new Span();
		disclamerSpan.setText("Placanje karticom se obavlja na Third Party website-u");
		disclamerSpan.addClassName("centeredText");

		orderingContainer.add(addressTextField, telephoneTextField, payingOptionSpan, payingOptionChoiceContainer,
				createOrderButton, disclamerSpan);

		orderingContainer.addClassName("orderingContainer");
		orderingContainer.setPadding(true);
		orderingContainer.setAlignItems(Alignment.STRETCH);

		VerticalLayout orderContainer = new VerticalLayout();
		orderContainer.add(titleH2, orderingContainer);
		orderContainer.setAlignItems(Alignment.CENTER);
		orderContainer.setJustifyContentMode(JustifyContentMode.CENTER);

		add(backButton, orderContainer);	
	}

	@Override
	public void setTotalPrice(double totalPrice) {
		titleH2.setText("Ukupna Cena : " + totalPrice + " RSD");

	}
}
