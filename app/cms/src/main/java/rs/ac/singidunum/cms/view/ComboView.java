package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import rs.ac.singidunum.cms.dto.response.ComboResponseDTO;
import rs.ac.singidunum.cms.presenter.CombosPresenter;

import java.util.List;

@Route(value = "products/combos", layout = MasterHeaderNavLayout.class)
@CssImport("./style/style-views.css")
public class ComboView extends VerticalLayout {

	private final CombosPresenter presenter;
	private TextField searchBar;
	private Button buttonAdd;
	private VerticalLayout containerAllCombos;
	private ProgressBar loadingIndicator;

	public ComboView(CombosPresenter presenter) {
		this.presenter = presenter;
		this.presenter.setView(this);

		H1 naslov = new H1("Combo");
		naslov.addClassName("page-title");
		add(naslov);

		loadingIndicator = new ProgressBar();
		loadingIndicator.setIndeterminate(true);
		loadingIndicator.setVisible(false);
		add(loadingIndicator);

		createSearchBarAndAddButton();
		createCombosContainer();

		setSizeFull();
		setAlignItems(Alignment.CENTER);

		setupListeners();

		this.presenter.onViewLoad();
	}

	private void setupListeners() {
		searchBar.addValueChangeListener(e ->
				presenter.onSearchChange(e.getValue()));
		buttonAdd.addClickListener(e ->
				presenter.onAddComboClick());
	}

	private void createSearchBarAndAddButton() {
		searchBar = new TextField();
		searchBar.setPlaceholder("Pretraga");
		searchBar.setClearButtonVisible(true);
		searchBar.setPrefixComponent(VaadinIcon.SEARCH.create());
		searchBar.addClassName("view-search-bar");

		buttonAdd = new Button("Dodaj Combo", VaadinIcon.PLUS.create());
		buttonAdd.addClassName("view-button-add");

		HorizontalLayout containerToolbar = new HorizontalLayout(searchBar, buttonAdd);
		containerToolbar.setWidthFull();
		containerToolbar.setJustifyContentMode(JustifyContentMode.BETWEEN);
		containerToolbar.setAlignItems(Alignment.CENTER);
		containerToolbar.addClassName("view-container-toolbar");

		add(containerToolbar);
	}

	private void createCombosContainer() {
		containerAllCombos = new VerticalLayout();
		containerAllCombos.setWidthFull();
		containerAllCombos.setPadding(false);
		containerAllCombos.setSpacing(true);
		containerAllCombos.addClassName("view-container-all-items");

		add(containerAllCombos);
	}

	private HorizontalLayout createComboCard(ComboResponseDTO combo) {
		HorizontalLayout comboCard = new HorizontalLayout();
		comboCard.setPadding(false);
		comboCard.setSpacing(false);
		comboCard.setAlignItems(Alignment.CENTER);
		comboCard.addClassName("item-card");

		Div imagePlaceholder = new Div();
		imagePlaceholder.addClassName("item-image");
		Icon comboIcon = VaadinIcon.GRID_BIG.create();
		comboIcon.setSize("40px");
		comboIcon.addClassName("item-icon");
		imagePlaceholder.add(comboIcon);

		VerticalLayout infoContainer = new VerticalLayout();
		infoContainer.setSpacing(false);
		infoContainer.setPadding(false);
		infoContainer.setJustifyContentMode(JustifyContentMode.AROUND);
		infoContainer.addClassName("item-container-info");

		Span naziv = new Span(combo.getName());
		naziv.addClassName("item-info");

		String formatiranaCena = String.format("%.2f RSD", combo.getBasePrice());
		Span cena = new Span(formatiranaCena);
		cena.addClassName("item-info");

		String selectionInfo = buildSelectionSummary(combo);
		Span selections = new Span(selectionInfo);
		selections.addClassName("item-info");

		Span statusBadge = new Span(combo.isActive() ? "Aktivan" : "Neaktivan");
		statusBadge.addClassName("status-badge");
		statusBadge.addClassName(combo.isActive() ? "status-badge--active" : "status-badge--inactive");

		infoContainer.add(naziv, cena, selections, statusBadge);

		VerticalLayout actionsContainer = new VerticalLayout();
		actionsContainer.setSpacing(true);
		actionsContainer.setPadding(false);
		actionsContainer.setAlignItems(Alignment.END);
		actionsContainer.addClassName("item-container-buttons");

		Icon iconEdit = VaadinIcon.EDIT.create();
		iconEdit.setSize("25px");
		Button buttonEdit = new Button(iconEdit);
		buttonEdit.addClassName("item-buttons-edit-and-delete");
		buttonEdit.addClickListener(e -> presenter.onEditComboClick(combo.getKey()));

		Icon iconDelete = VaadinIcon.TRASH.create();
		iconDelete.setSize("25px");
		Button buttonDelete = new Button(iconDelete);
		buttonDelete.addClassName("item-buttons-edit-and-delete");
		buttonDelete.addClickListener(e ->
				presenter.onDeleteComboClick(combo.getKey(), combo.getName()));

		actionsContainer.add(buttonEdit, buttonDelete);

		comboCard.add(imagePlaceholder, infoContainer, actionsContainer);
		comboCard.expand(infoContainer);

		return comboCard;
	}

	private String buildSelectionSummary(ComboResponseDTO combo) {
		int mainCount = combo.getMainSelection() != null ? combo.getMainSelection().size() : 0;
		int sideCount = combo.getSideSelection() != null ? combo.getSideSelection().size() : 0;
		int drinkCount = combo.getDrinkSelection() != null ? combo.getDrinkSelection().size() : 0;
		return String.format("Main: %d, Prilozi: %d, Pića: %d", mainCount, sideCount, drinkCount);
	}

	public void setCombos(List<ComboResponseDTO> combos) {
		containerAllCombos.removeAll();
		for (ComboResponseDTO combo : combos) {
			HorizontalLayout card = createComboCard(combo);
			containerAllCombos.add(card);
		}
	}

	public void clearCombos() {
		containerAllCombos.removeAll();
	}

	public String getSearchText() {
		return searchBar.getValue();
	}

	public void showLoading() {
		loadingIndicator.setVisible(true);
	}

	public void hideLoading() {
		loadingIndicator.setVisible(false);
	}

	public void showError(String message) {
		Notification notification = Notification.show(message, 5000, Notification.Position.BOTTOM_START);
		notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
	}

	public void showSuccess(String message) {
		Notification notification = Notification.show(message, 3000, Notification.Position.BOTTOM_START);
		notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
	}

	public void showEmptyState(String message) {
		containerAllCombos.removeAll();
		Span emptyMessage = new Span(message);
		emptyMessage.addClassName("empty-state-message");
		containerAllCombos.add(emptyMessage);
	}

	public void navigateToComboEdit(String comboKey) {
		getUI().ifPresent(ui -> ui.navigate("products/combos/form/" + comboKey));
	}

	public void navigateToComboCreate() {
		getUI().ifPresent(ui -> ui.navigate("products/combos/form"));
	}

	public void showDeleteConfirmation(String comboKey, String comboName) {
		ConfirmDialog dialog = new ConfirmDialog();
		dialog.setHeader("Potvrda brisanja");
		dialog.setText("Da li ste sigurni da želite da obrišete combo: " + comboName + "?");
		dialog.setCancelable(true);
		dialog.setCancelText("Otkaži");
		dialog.setConfirmText("Obriši");
		dialog.setConfirmButtonTheme("error primary");
		dialog.addConfirmListener(e -> presenter.confirmDeleteCombo(comboKey));
		dialog.open();
	}
}
