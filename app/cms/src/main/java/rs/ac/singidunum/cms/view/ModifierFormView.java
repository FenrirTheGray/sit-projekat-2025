package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import rs.ac.singidunum.cms.dto.response.ModifierResponseDTO;
import rs.ac.singidunum.cms.dto.response.ModifierTypeResponseDTO;
import rs.ac.singidunum.cms.presenter.ModifierFormPresenter;

import java.util.List;

@Route(value = "products/modifiers/form/:key?", layout = MasterHeaderNavLayout.class)
@CssImport("./style/style-views.css")
public class ModifierFormView extends VerticalLayout implements BeforeEnterObserver {

	private final ModifierFormPresenter presenter;

	private H1 title;
	private TextField nameField;
	private TextArea descriptionField;
	private NumberField priceField;
	private ComboBox<ModifierTypeResponseDTO> typeComboBox;
	private Checkbox activeCheckbox;
	private Button saveButton;
	private Button cancelButton;
	private ProgressBar loadingIndicator;

	public ModifierFormView(ModifierFormPresenter presenter) {
		this.presenter = presenter;
		this.presenter.setView(this);

		initializeForm();
		setupListeners();

		setSizeFull();
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.START);
		setPadding(true);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		String modifierKey = event.getRouteParameters().get("key").orElse(null);

		if (modifierKey != null && !modifierKey.isEmpty()) {
			title.setText("Uredi Modifikator");
		} else {
			title.setText("Novi Modifikator");
		}

		presenter.onViewLoad(modifierKey);
	}

	private void initializeForm() {
		title = new H1("Novi Modifikator");
		title.addClassName("page-title");
		add(title);

		loadingIndicator = new ProgressBar();
		loadingIndicator.setIndeterminate(true);
		loadingIndicator.setVisible(false);
		add(loadingIndicator);

		VerticalLayout formContainer = new VerticalLayout();
		formContainer.setWidth("600px");
		formContainer.setPadding(true);
		formContainer.setSpacing(true);
		formContainer.addClassName("form-container");

		H3 basicInfoTitle = new H3("Informacije o modifikatoru");
		basicInfoTitle.addClassName("combo-section-title");

		nameField = new TextField("Naziv");
		nameField.setWidthFull();
		nameField.setRequired(true);
		nameField.addClassName("form-field");

		descriptionField = new TextArea("Opis");
		descriptionField.setWidthFull();
		descriptionField.setHeight("100px");
		descriptionField.addClassName("form-field");

		priceField = new NumberField("Cena (RSD)");
		priceField.setWidthFull();
		priceField.setRequired(true);
		priceField.setMin(0);
		priceField.setStep(0.01);
		priceField.setValue(0.0);
		priceField.addClassName("form-field");

		typeComboBox = new ComboBox<>("Tip modifikatora");
		typeComboBox.setWidthFull();
		typeComboBox.setRequired(true);
		typeComboBox.setItemLabelGenerator(ModifierTypeResponseDTO::getName);
		typeComboBox.addClassName("form-field");

		activeCheckbox = new Checkbox("Aktivan");
		activeCheckbox.setValue(true);
		activeCheckbox.addClassName("form-checkbox");

		saveButton = new Button("Sačuvaj");
		saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		saveButton.addClassName("form-button-save");

		cancelButton = new Button("Otkaži");
		cancelButton.addClassName("form-button-cancel");

		HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
		buttonLayout.setPadding(false);
		buttonLayout.setSpacing(true);
		buttonLayout.addClassName("combo-button-layout");

		formContainer.add(
				basicInfoTitle,
				nameField,
				descriptionField,
				priceField,
				typeComboBox,
				activeCheckbox,
				buttonLayout
		);

		add(formContainer);
	}

	private void setupListeners() {
		saveButton.addClickListener(e -> presenter.onSaveClick());
		cancelButton.addClickListener(e -> presenter.onCancelClick());
	}

	public void setAvailableTypes(List<ModifierTypeResponseDTO> types) {
		typeComboBox.setItems(types);
	}

	public void setModifierData(ModifierResponseDTO modifier) {
		nameField.setValue(modifier.getName() != null ? modifier.getName() : "");
		descriptionField.setValue(modifier.getDescription() != null ? modifier.getDescription() : "");
		priceField.setValue(modifier.getPrice());
		activeCheckbox.setValue(modifier.isActive());

		if (modifier.getType() != null) {
			typeComboBox.getListDataView().getItems()
					.filter(t -> t.getId().equals(modifier.getType().getId()))
					.findFirst()
					.ifPresent(typeComboBox::setValue);
		}
	}

	public void clearForm() {
		nameField.clear();
		descriptionField.clear();
		priceField.setValue(0.0);
		typeComboBox.clear();
		activeCheckbox.setValue(true);
		clearValidationErrors();
	}

	public String getModifierName() {
		return nameField.getValue();
	}

	public String getModifierDescription() {
		return descriptionField.getValue();
	}

	public Double getModifierPrice() {
		return priceField.getValue();
	}

	public Boolean getModifierActive() {
		return activeCheckbox.getValue();
	}

	public String getSelectedTypeId() {
		ModifierTypeResponseDTO selected = typeComboBox.getValue();
		return selected != null ? selected.getId() : null;
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

	public void showPriceError(String message) {
		priceField.setInvalid(true);
		priceField.setErrorMessage(message);
	}

	public void showTypeError(String message) {
		typeComboBox.setInvalid(true);
		typeComboBox.setErrorMessage(message);
	}

	public void clearValidationErrors() {
		nameField.setInvalid(false);
		priceField.setInvalid(false);
		typeComboBox.setInvalid(false);
	}

	public void navigateBackToModifiers() {
		getUI().ifPresent(ui -> ui.navigate("products/modifiers"));
	}
}
