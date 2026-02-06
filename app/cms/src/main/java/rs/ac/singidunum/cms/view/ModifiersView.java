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
import rs.ac.singidunum.cms.dto.response.ModifierResponseDTO;
import rs.ac.singidunum.cms.presenter.ModifierTypePresenter;
import rs.ac.singidunum.cms.presenter.ModifiersPresenter;

import java.util.List;

@Route(value = "products/modifiers", layout = MasterHeaderNavLayout.class)
@CssImport("./style/style-views.css")
public class ModifiersView extends VerticalLayout {

	private final ModifiersPresenter presenter;
	private final ModifierTypePresenter modifierTypePresenter;
	private TextField searchBar;
	private Button buttonAdd;
	private Button buttonManageTypes;
	private VerticalLayout containerAllModifiers;
	private ProgressBar loadingIndicator;
	private ModifierTypeManagementDialog typeManagementDialog;

	public ModifiersView(ModifiersPresenter presenter, ModifierTypePresenter modifierTypePresenter) {
		this.presenter = presenter;
		this.modifierTypePresenter = modifierTypePresenter;
		this.presenter.setView(this);

		H1 naslov = new H1("Modifikatori");
		naslov.addClassName("page-title");
		add(naslov);

		loadingIndicator = new ProgressBar();
		loadingIndicator.setIndeterminate(true);
		loadingIndicator.setVisible(false);
		add(loadingIndicator);

		createSearchBarAndAddButton();
		createModifiersContainer();

		setSizeFull();
		setAlignItems(Alignment.CENTER);

		setupListeners();

		this.presenter.onViewLoad();
	}

	private void setupListeners() {
		searchBar.addValueChangeListener(e ->
				presenter.onSearchChange(e.getValue()));
		buttonAdd.addClickListener(e ->
				presenter.onAddModifierClick());
		buttonManageTypes.addClickListener(e ->
				presenter.onManageTypesClick());
	}

	private void createSearchBarAndAddButton() {
		searchBar = new TextField();
		searchBar.setPlaceholder("Pretraga");
		searchBar.setClearButtonVisible(true);
		searchBar.setPrefixComponent(VaadinIcon.SEARCH.create());
		searchBar.addClassName("view-search-bar");

		buttonManageTypes = new Button("Tipovi", VaadinIcon.COG.create());
		buttonManageTypes.addClassName("view-button-add");

		buttonAdd = new Button("Dodaj Modifikator", VaadinIcon.PLUS.create());
		buttonAdd.addClassName("view-button-add");

		HorizontalLayout buttonsLayout = new HorizontalLayout(buttonManageTypes, buttonAdd);
		buttonsLayout.setSpacing(true);

		HorizontalLayout containerToolbar = new HorizontalLayout(searchBar, buttonsLayout);
		containerToolbar.setWidthFull();
		containerToolbar.setJustifyContentMode(JustifyContentMode.BETWEEN);
		containerToolbar.setAlignItems(Alignment.CENTER);
		containerToolbar.addClassName("view-container-toolbar");

		add(containerToolbar);
	}

	private void createModifiersContainer() {
		containerAllModifiers = new VerticalLayout();
		containerAllModifiers.setWidthFull();
		containerAllModifiers.setPadding(false);
		containerAllModifiers.setSpacing(true);
		containerAllModifiers.addClassName("view-container-all-items");

		add(containerAllModifiers);
	}

	private HorizontalLayout createModifierCard(ModifierResponseDTO modifier) {
		HorizontalLayout modifierCard = new HorizontalLayout();
		modifierCard.setPadding(false);
		modifierCard.setSpacing(false);
		modifierCard.setAlignItems(Alignment.CENTER);
		modifierCard.addClassName("item-card");

		Div imagePlaceholder = new Div();
		imagePlaceholder.addClassName("item-image");
		Icon modifierIcon = VaadinIcon.SLIDERS.create();
		modifierIcon.setSize("40px");
		modifierIcon.addClassName("item-icon");
		imagePlaceholder.add(modifierIcon);

		VerticalLayout infoContainer = new VerticalLayout();
		infoContainer.setSpacing(false);
		infoContainer.setPadding(false);
		infoContainer.setJustifyContentMode(JustifyContentMode.AROUND);
		infoContainer.addClassName("item-container-info");

		Span naziv = new Span(modifier.getName());
		naziv.addClassName("item-info");

		String formatiranaCena = String.format("%.2f RSD", modifier.getPrice());
		Span cena = new Span(formatiranaCena);
		cena.addClassName("item-info");

		// Description (truncated)
		if (modifier.getDescription() != null && !modifier.getDescription().isEmpty()) {
			String desc = modifier.getDescription();
			if (desc.length() > 60) {
				desc = desc.substring(0, 57) + "...";
			}
			Span description = new Span(desc);
			description.addClassName("item-info");
			description.getStyle().set("color", "var(--cms-text-muted)");
			description.getStyle().set("font-size", "0.85em");
			infoContainer.add(naziv, cena, description);
		} else {
			infoContainer.add(naziv, cena);
		}

		HorizontalLayout badgesRow = new HorizontalLayout();
		badgesRow.setSpacing(true);
		badgesRow.setPadding(false);
		badgesRow.setAlignItems(Alignment.CENTER);
		badgesRow.getStyle().set("margin-top", "4px");

		if (modifier.getType() != null) {
			Span typeBadge = new Span(modifier.getType().getName().toUpperCase());
			typeBadge.addClassName("status-badge");
			typeBadge.getStyle().set("background-color", "var(--cms-surface-3)");
			typeBadge.getStyle().set("color", "var(--cms-text-secondary)");
			typeBadge.getStyle().set("font-size", "0.7rem");
			typeBadge.getStyle().set("padding", "2px 8px");
			badgesRow.add(typeBadge);
		}

		Span statusBadge = new Span(modifier.isActive() ? "AKTIVAN" : "NEAKTIVAN");
		statusBadge.addClassName("status-badge");
		statusBadge.addClassName(modifier.isActive() ? "status-badge--active" : "status-badge--inactive");
		statusBadge.getStyle().set("font-size", "0.7rem");
		statusBadge.getStyle().set("padding", "2px 8px");
		badgesRow.add(statusBadge);

		infoContainer.add(badgesRow);

		VerticalLayout actionsContainer = new VerticalLayout();
		actionsContainer.setSpacing(true);
		actionsContainer.setPadding(false);
		actionsContainer.setAlignItems(Alignment.END);
		actionsContainer.addClassName("item-container-buttons");

		Icon iconEdit = VaadinIcon.EDIT.create();
		iconEdit.setSize("25px");
		Button buttonEdit = new Button(iconEdit);
		buttonEdit.addClassName("item-buttons-edit-and-delete");
		buttonEdit.addClickListener(e -> presenter.onEditModifierClick(modifier.getKey()));

		Icon iconDelete = VaadinIcon.TRASH.create();
		iconDelete.setSize("25px");
		Button buttonDelete = new Button(iconDelete);
		buttonDelete.addClassName("item-buttons-edit-and-delete");
		buttonDelete.addClickListener(e ->
				presenter.onDeleteModifierClick(modifier.getKey(), modifier.getName()));

		actionsContainer.add(buttonEdit, buttonDelete);

		modifierCard.add(imagePlaceholder, infoContainer, actionsContainer);
		modifierCard.expand(infoContainer);

		return modifierCard;
	}

	public void setModifiers(List<ModifierResponseDTO> modifiers) {
		containerAllModifiers.removeAll();
		for (ModifierResponseDTO modifier : modifiers) {
			HorizontalLayout card = createModifierCard(modifier);
			containerAllModifiers.add(card);
		}
	}

	public void clearModifiers() {
		containerAllModifiers.removeAll();
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
		containerAllModifiers.removeAll();
		Span emptyMessage = new Span(message);
		emptyMessage.addClassName("empty-state-message");
		containerAllModifiers.add(emptyMessage);
	}

	public void navigateToModifierEdit(String modifierKey) {
		getUI().ifPresent(ui -> ui.navigate("products/modifiers/form/" + modifierKey));
	}

	public void navigateToModifierCreate() {
		getUI().ifPresent(ui -> ui.navigate("products/modifiers/form"));
	}

	public void showDeleteConfirmation(String modifierKey, String modifierName) {
		ConfirmDialog dialog = new ConfirmDialog();
		dialog.setHeader("Potvrda brisanja");
		dialog.setText("Da li ste sigurni da želite da obrišete modifikator: " + modifierName + "?");
		dialog.setCancelable(true);
		dialog.setCancelText("Otkaži");
		dialog.setConfirmText("Obriši");
		dialog.setConfirmButtonTheme("error primary");
		dialog.addConfirmListener(e -> presenter.confirmDeleteModifier(modifierKey));
		dialog.open();
	}

	public void openModifierTypeManagementDialog() {
		if (typeManagementDialog == null) {
			typeManagementDialog = new ModifierTypeManagementDialog(
					modifierTypePresenter,
					() -> presenter.onTypesDialogClosed()
			);
		}
		typeManagementDialog.openDialog();
	}
}
