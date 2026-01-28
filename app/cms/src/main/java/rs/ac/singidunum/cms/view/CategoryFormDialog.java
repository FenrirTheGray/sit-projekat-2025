package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
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
import rs.ac.singidunum.cms.interfaces.CategoryFormViewInterface;
import rs.ac.singidunum.cms.presenter.CategoryFormPresenter;

@CssImport("./style/style-views.css")
public class CategoryFormDialog extends Dialog implements CategoryFormViewInterface {

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

		addOpenedChangeListener(e -> {
			if (e.isOpened()) {
				getElement().executeJs(
					"const overlay = this.$.overlay.$.overlay;" +
					"overlay.style.backgroundColor = '#202020';" +
					"overlay.style.color = 'white';" +
					"overlay.style.border = '1px solid #333333';" +
					"overlay.style.borderRadius = '10px';"
				);
			}
		});
	}

	private void initializeForm() {
		VerticalLayout dialogContent = new VerticalLayout();
		dialogContent.setPadding(true);
		dialogContent.setSpacing(true);
		dialogContent.getStyle()
			.set("background-color", "#202020")
			.set("color", "white");

		title = new H2("Kreiraj Kategoriju");
		title.getStyle()
			.set("color", "white")
			.set("margin-top", "0")
			.set("margin-bottom", "10px");

		loadingIndicator = new ProgressBar();
		loadingIndicator.setIndeterminate(true);
		loadingIndicator.setVisible(false);
		loadingIndicator.setWidthFull();

		nameField = new TextField("Naziv");
		nameField.setWidthFull();
		nameField.setRequired(true);
		styleFormField(nameField);

		descriptionField = new TextArea("Opis");
		descriptionField.setWidthFull();
		descriptionField.setRequired(true);
		descriptionField.setHeight("120px");
		styleFormField(descriptionField);

		activeCheckbox = new Checkbox("Aktivna");
		activeCheckbox.setValue(true);
		activeCheckbox.getStyle()
			.set("color", "white")
			.set("--vaadin-checkbox-label-color", "#ffffff");

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setWidthFull();
		buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
		buttonLayout.setSpacing(true);

		cancelButton = new Button("Otkaži");
		cancelButton.getStyle()
			.set("background-color", "#333333")
			.set("color", "white");

		saveButton = new Button("Sačuvaj");
		saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		buttonLayout.add(cancelButton, saveButton);

		dialogContent.add(title, loadingIndicator, nameField,
						 descriptionField, activeCheckbox, buttonLayout);

		add(dialogContent);
	}

	private void styleFormField(HasStyle field) {
		field.getStyle()
			.set("--vaadin-input-field-background", "#333333")
			.set("--vaadin-input-field-border-color", "#555555")
			.set("--vaadin-input-field-value-color", "#ffffff")
			.set("--vaadin-input-field-label-color", "#b3b3b3")
			.set("color", "white");
	}

	private void setupListeners() {
		saveButton.addClickListener(e -> presenter.onSaveClick());
		cancelButton.addClickListener(e -> {
			close();
			clearForm();
		});
	}

	@Override
	public void setCategoryData(CategoryResponseDTO category) {
		nameField.setValue(category.getName());
		descriptionField.setValue(category.getDescription());
		activeCheckbox.setValue(category.isActive());
	}

	@Override
	public void clearForm() {
		nameField.clear();
		descriptionField.clear();
		activeCheckbox.setValue(true);
		clearValidationErrors();
	}

	@Override
	public String getCategoryName() {
		return nameField.getValue();
	}

	@Override
	public String getCategoryDescription() {
		return descriptionField.getValue();
	}

	@Override
	public Boolean getCategoryActive() {
		return activeCheckbox.getValue();
	}

	@Override
	public void showLoading() {
		loadingIndicator.setVisible(true);
		saveButton.setEnabled(false);
	}

	@Override
	public void hideLoading() {
		loadingIndicator.setVisible(false);
		saveButton.setEnabled(true);
	}

	@Override
	public void showError(String message) {
		Notification notification = Notification.show(message, 5000, Notification.Position.BOTTOM_START);
		notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
	}

	@Override
	public void showSuccess(String message) {
		Notification notification = Notification.show(message, 3000, Notification.Position.BOTTOM_START);
		notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
	}

	@Override
	public void showNameError(String message) {
		nameField.setInvalid(true);
		nameField.setErrorMessage(message);
	}

	@Override
	public void showDescriptionError(String message) {
		descriptionField.setInvalid(true);
		descriptionField.setErrorMessage(message);
	}

	@Override
	public void clearValidationErrors() {
		nameField.setInvalid(false);
		descriptionField.setInvalid(false);
	}

	@Override
	public void navigateBackToCategories() {
		close();
		clearForm();
		if (onSuccessCallback != null) {
			onSuccessCallback.run();
		}
	}
}
