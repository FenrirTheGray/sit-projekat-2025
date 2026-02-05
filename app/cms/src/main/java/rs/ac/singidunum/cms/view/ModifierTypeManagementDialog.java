package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextField;
import rs.ac.singidunum.cms.dto.response.ModifierTypeResponseDTO;
import rs.ac.singidunum.cms.presenter.ModifierTypePresenter;

import java.util.List;

public class ModifierTypeManagementDialog extends Dialog {

	private final ModifierTypePresenter presenter;
	private final Runnable onCloseCallback;

	private VerticalLayout typeListContainer;
	private HorizontalLayout toolbar;
	private TextField searchField;
	private Button addTypeButton;

	private VerticalLayout formContainer;
	private H2 formTitle;
	private TextField nameField;
	private Checkbox activeCheckbox;
	private Button saveButton;
	private Button cancelFormButton;

	private ProgressBar loadingIndicator;
	private Span emptyStateLabel;

	public ModifierTypeManagementDialog(ModifierTypePresenter presenter, Runnable onCloseCallback) {
		this.presenter = presenter;
		this.onCloseCallback = onCloseCallback;
		this.presenter.setView(this);

		initializeDialog();
		initializeContent();
		setupListeners();
	}

	public void openDialog() {
		hideForm();
		presenter.onViewLoad();
		open();
	}

	private void initializeDialog() {
		setModal(true);
		setDraggable(false);
		setResizable(false);
		setWidth("700px");
		setMaxHeight("80vh");
		setCloseOnEsc(true);
		setCloseOnOutsideClick(false);
		addClassName("modifier-type-dialog");
	}

	private void initializeContent() {
		VerticalLayout dialogContent = new VerticalLayout();
		dialogContent.setPadding(true);
		dialogContent.setSpacing(true);
		dialogContent.setWidthFull();
		dialogContent.addClassName("cms-dialog-content");

		H2 title = new H2("Upravljanje Tipovima Modifikatora");
		title.addClassName("cms-dialog-title");

		loadingIndicator = new ProgressBar();
		loadingIndicator.setIndeterminate(true);
		loadingIndicator.setVisible(false);
		loadingIndicator.setWidthFull();

		HorizontalLayout toolbar = createToolbar();
		typeListContainer = new VerticalLayout();
		typeListContainer.setPadding(false);
		typeListContainer.setSpacing(false);
		typeListContainer.addClassName("type-list-container");
		typeListContainer.getStyle().set("overflow-y", "auto");
		typeListContainer.getStyle().set("max-height", "400px");

		emptyStateLabel = new Span("Nema tipova modifikatora");
		emptyStateLabel.addClassName("empty-state-label");
		emptyStateLabel.setVisible(false);

		formContainer = createFormSection();
		formContainer.setVisible(false);

		Button closeButton = new Button("Zatvori", e -> {
			close();
			if (onCloseCallback != null) {
				onCloseCallback.run();
			}
		});
		closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		closeButton.getStyle().set("background-color", "var(--cms-surface-2)");
		closeButton.getStyle().set("color", "var(--cms-text)");
		closeButton.getStyle().set("border", "1px solid var(--cms-surface-3)");

		HorizontalLayout footer = new HorizontalLayout(closeButton);
		footer.setWidthFull();
		footer.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
		footer.getStyle().set("margin-top", "16px");

		dialogContent.add(title, loadingIndicator, toolbar, typeListContainer, emptyStateLabel, formContainer, footer);

		add(dialogContent);
	}

	private HorizontalLayout createToolbar() {
		toolbar = new HorizontalLayout();
		toolbar.setWidthFull();
		toolbar.setAlignItems(FlexComponent.Alignment.CENTER);

		searchField = new TextField();
		searchField.setPlaceholder("Pretraži tipove...");
		searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
		searchField.addClassName("view-search-bar");
		searchField.setWidth("300px");

		addTypeButton = new Button("Dodaj Tip", new Icon(VaadinIcon.PLUS));
		addTypeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		addTypeButton.addClassName("view-button-add");

		toolbar.add(searchField, addTypeButton);
		toolbar.setFlexGrow(1, searchField);

		return toolbar;
	}

	private VerticalLayout createFormSection() {
		VerticalLayout form = new VerticalLayout();
		form.setPadding(true);
		form.setSpacing(true);
		form.addClassName("type-form-section");
		form.getStyle().set("background-color", "var(--cms-surface-2)");
		form.getStyle().set("border", "1px solid var(--cms-surface-3)");
		form.getStyle().set("border-radius", "8px");
		form.getStyle().set("margin-top", "16px");

		formTitle = new H2("Novi Tip");
		formTitle.addClassName("form-section-title");

		nameField = new TextField("Naziv");
		nameField.setWidthFull();
		nameField.setRequired(true);
		nameField.addClassName("cms-form-field");

		activeCheckbox = new Checkbox("Aktivan");
		activeCheckbox.setValue(true);
		activeCheckbox.addClassName("cms-checkbox");

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setWidthFull();
		buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
		buttonLayout.setSpacing(true);

		cancelFormButton = new Button("Otkaži");
		cancelFormButton.addClassName("cms-button-secondary");

		saveButton = new Button("Sačuvaj");
		saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		buttonLayout.add(cancelFormButton, saveButton);

		form.add(formTitle, nameField, activeCheckbox, buttonLayout);

		return form;
	}

	private void setupListeners() {
		searchField.addValueChangeListener(e -> presenter.onSearchChange(e.getValue()));
		addTypeButton.addClickListener(e -> presenter.onAddTypeClick());
		saveButton.addClickListener(e -> presenter.onSaveClick());
		cancelFormButton.addClickListener(e -> presenter.onCancelFormClick());
	}

	public void setTypes(List<ModifierTypeResponseDTO> types) {
		typeListContainer.removeAll();
		emptyStateLabel.setVisible(false);

		for (ModifierTypeResponseDTO type : types) {
			HorizontalLayout card = createTypeCard(type);
			typeListContainer.add(card);
		}
	}

	private HorizontalLayout createTypeCard(ModifierTypeResponseDTO type) {
		HorizontalLayout card = new HorizontalLayout();
		card.setWidthFull();
		card.setPadding(true);
		card.setAlignItems(FlexComponent.Alignment.CENTER);
		card.addClassName("item-card");
		card.getStyle().set("background-color", "var(--cms-surface)");
		card.getStyle().set("border", "1px solid var(--cms-surface-3)");
		card.getStyle().set("border-radius", "8px");
		card.getStyle().set("margin-bottom", "8px");
		card.getStyle().set("padding", "16px");

		HorizontalLayout info = new HorizontalLayout();
		info.setPadding(false);
		info.setSpacing(true);
		info.setAlignItems(FlexComponent.Alignment.CENTER);

		Span nameSpan = new Span(type.getName().toUpperCase());
		nameSpan.getStyle().set("font-weight", "600");
		nameSpan.getStyle().set("font-size", "1rem");
		nameSpan.getStyle().set("color", "var(--cms-text)");

		Span statusBadge = new Span(type.isActive() ? "AKTIVAN" : "NEAKTIVAN");
		statusBadge.addClassName("status-badge");
		statusBadge.addClassName(type.isActive() ? "status-badge--active" : "status-badge--inactive");
		statusBadge.getStyle().set("font-size", "0.7rem");
		statusBadge.getStyle().set("padding", "2px 8px");

		info.add(nameSpan, statusBadge);

		HorizontalLayout actions = new HorizontalLayout();
		actions.setSpacing(true);

		Button editButton = new Button(new Icon(VaadinIcon.EDIT));
		editButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		editButton.addClassName("item-buttons-edit-and-delete");
		editButton.addClickListener(e -> presenter.onEditTypeClick(type));

		Button deleteButton = new Button(new Icon(VaadinIcon.TRASH));
		deleteButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
		deleteButton.addClassName("item-buttons-edit-and-delete");
		deleteButton.addClickListener(e -> presenter.onDeleteTypeClick(type.getId(), type.getName()));

		actions.add(editButton, deleteButton);

		card.add(info, actions);
		card.setFlexGrow(1, info);

		return card;
	}

	public void showFormForCreate() {
		formTitle.setText("Novi Tip");
		nameField.clear();
		activeCheckbox.setValue(true);
		clearValidationErrors();
		// Hide list, show form
		toolbar.setVisible(false);
		typeListContainer.setVisible(false);
		emptyStateLabel.setVisible(false);
		formContainer.setVisible(true);
	}

	public void showFormForEdit(ModifierTypeResponseDTO type) {
		formTitle.setText("Izmeni Tip");
		nameField.setValue(type.getName());
		activeCheckbox.setValue(type.isActive());
		clearValidationErrors();
		// Hide list, show form
		toolbar.setVisible(false);
		typeListContainer.setVisible(false);
		emptyStateLabel.setVisible(false);
		formContainer.setVisible(true);
	}

	public void hideForm() {
		formContainer.setVisible(false);
		nameField.clear();
		activeCheckbox.setValue(true);
		clearValidationErrors();
		// Show list again
		toolbar.setVisible(true);
		typeListContainer.setVisible(true);
	}

	public void showEmptyState(String message) {
		typeListContainer.removeAll();
		emptyStateLabel.setText(message);
		emptyStateLabel.setVisible(true);
	}

	public void showDeleteConfirmation(String typeId, String typeName) {
		ConfirmDialog dialog = new ConfirmDialog();
		dialog.setHeader("Potvrda brisanja");
		dialog.setText("Da li ste sigurni da želite da obrišete tip \"" + typeName + "\"?");
		dialog.setCancelable(true);
		dialog.setCancelText("Otkaži");
		dialog.setConfirmText("Obriši");
		dialog.setConfirmButtonTheme("error primary");
		dialog.addConfirmListener(e -> presenter.confirmDeleteType(typeId));
		dialog.open();
	}

	public String getTypeName() {
		return nameField.getValue();
	}

	public Boolean getTypeActive() {
		return activeCheckbox.getValue();
	}

	public void showLoading() {
		loadingIndicator.setVisible(true);
		saveButton.setEnabled(false);
		addTypeButton.setEnabled(false);
	}

	public void hideLoading() {
		loadingIndicator.setVisible(false);
		saveButton.setEnabled(true);
		addTypeButton.setEnabled(true);
	}

	public void showError(String message) {
		Notification notification = Notification.show(message, 5000, Notification.Position.BOTTOM_START);
		notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
	}

	public void showSuccess(String message) {
		Notification notification = Notification.show(message, 3000, Notification.Position.BOTTOM_START);
		notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
	}

	public void showNameError(String message) {
		nameField.setInvalid(true);
		nameField.setErrorMessage(message);
	}

	public void clearValidationErrors() {
		nameField.setInvalid(false);
	}
}
