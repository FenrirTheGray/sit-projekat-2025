package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import rs.ac.singidunum.cms.dto.response.CategoryResponseDTO;
import rs.ac.singidunum.cms.presenter.CategoryFormPresenter;

public class CategoryFormDialog extends Dialog {

	private final CategoryFormPresenter presenter;
	private final Runnable onSuccessCallback;

	private H2 title;
	private TextField nameField;
	private TextArea descriptionField;
	private Checkbox activeCheckbox;
	private Button saveButton;
	private Button cancelButton;
	private ProgressBar loadingIndicator;

	public CategoryFormDialog(CategoryFormPresenter presenter, Runnable onSuccessCallback) {
		this.presenter = presenter;
		this.onSuccessCallback = onSuccessCallback;
		this.presenter.setView(this);

		initializeDialog();
		initializeForm();
		setupListeners();
	}

	public void openForCreate() {
		title.setText("Kreiraj Kategoriju");
		clearForm();
		presenter.onViewLoad(null);
		open();
	}

	public void openForEdit(String categoryId) {
		title.setText("Izmeni Kategoriju");
		presenter.onViewLoad(categoryId);
		open();
	}

	private void initializeDialog() {
		setModal(true);
		setDraggable(false);
		setResizable(false);
		setWidth("650px");
		setCloseOnEsc(true);
		setCloseOnOutsideClick(false);
		addClassName("category-form-dialog");
	}

	private void initializeForm() {
		VerticalLayout dialogContent = new VerticalLayout();
		dialogContent.setPadding(true);
		dialogContent.setSpacing(true);
		dialogContent.addClassName("cms-dialog-content");

		title = new H2("Kreiraj Kategoriju");
		title.addClassName("cms-dialog-title");

		loadingIndicator = new ProgressBar();
		loadingIndicator.setIndeterminate(true);
		loadingIndicator.setVisible(false);
		loadingIndicator.setWidthFull();

		nameField = new TextField("Naziv");
		nameField.setWidthFull();
		nameField.setRequired(true);
		nameField.addClassName("cms-form-field");

		descriptionField = new TextArea("Opis");
		descriptionField.setWidthFull();
		descriptionField.setRequired(true);
		descriptionField.setHeight("120px");
		descriptionField.addClassName("cms-form-field");

		activeCheckbox = new Checkbox("Aktivna");
		activeCheckbox.setValue(true);
		activeCheckbox.addClassName("cms-checkbox");

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setWidthFull();
		buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
		buttonLayout.setSpacing(true);

		cancelButton = new Button("Otkaži");
		cancelButton.addClassName("cms-button-secondary");

		saveButton = new Button("Sačuvaj");
		saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		buttonLayout.add(cancelButton, saveButton);

		dialogContent.add(title, loadingIndicator, nameField,
						 descriptionField, activeCheckbox, buttonLayout);

		add(dialogContent);
	}

	private void setupListeners() {
		saveButton.addClickListener(e -> presenter.onSaveClick());
		cancelButton.addClickListener(e -> {
			close();
			clearForm();
		});
	}

	public void setCategoryData(CategoryResponseDTO category) {
		nameField.setValue(category.getName());
		descriptionField.setValue(category.getDescription());
		activeCheckbox.setValue(category.isActive());
	}

	public void clearForm() {
		nameField.clear();
		descriptionField.clear();
		activeCheckbox.setValue(true);
		clearValidationErrors();
	}

	public String getCategoryName() {
		return nameField.getValue();
	}

	public String getCategoryDescription() {
		return descriptionField.getValue();
	}

	public Boolean getCategoryActive() {
		return activeCheckbox.getValue();
	}

	public void showLoading() {
		loadingIndicator.setVisible(true);
		saveButton.setEnabled(false);
	}

	public void hideLoading() {
		loadingIndicator.setVisible(false);
		saveButton.setEnabled(true);
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

	public void showDescriptionError(String message) {
		descriptionField.setInvalid(true);
		descriptionField.setErrorMessage(message);
	}

	public void clearValidationErrors() {
		nameField.setInvalid(false);
		descriptionField.setInvalid(false);
	}

	public void navigateBackToCategories() {
		close();
		clearForm();
		if (onSuccessCallback != null) {
			onSuccessCallback.run();
		}
	}
}
