package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
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
import rs.ac.singidunum.cms.dto.response.ArticleResponseDTO;
import rs.ac.singidunum.cms.dto.response.CategoryResponseDTO;
import rs.ac.singidunum.cms.presenter.ArticleFormPresenter;
import rs.ac.singidunum.cms.service.CategoryService;

import java.util.List;

@Route(value = "products/articles/form/:key?", layout = MasterHeaderNavLayout.class)
@CssImport("./style/style-views.css")
public class ArticleFormView extends VerticalLayout implements BeforeEnterObserver {

	private final ArticleFormPresenter presenter;
	private final CategoryService categoryService;

	private H1 title;
	private TextField nameField;
	private TextArea descriptionField;
	private TextField imageUrlField;
	private NumberField basePriceField;
	private Checkbox activeCheckbox;
	private ComboBox<CategoryResponseDTO> categoryComboBox;
	private Button saveButton;
	private Button cancelButton;
	private ProgressBar loadingIndicator;

	public ArticleFormView(ArticleFormPresenter presenter, CategoryService categoryService) {
		this.presenter = presenter;
		this.categoryService = categoryService;
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
		String articleKey = event.getRouteParameters().get("key").orElse(null);

		if (articleKey != null && !articleKey.isEmpty()) {
			title.setText("Uredi Artikal");
		} else {
			title.setText("Novi Artikal");
		}

		presenter.onViewLoad(articleKey);
	}

	private void initializeForm() {
		title = new H1("Novi Artikal");
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

		nameField = new TextField("Naziv");
		nameField.setWidthFull();
		nameField.setRequired(true);
		nameField.addClassName("form-field");

		descriptionField = new TextArea("Opis");
		descriptionField.setWidthFull();
		descriptionField.setRequired(true);
		descriptionField.setHeight("150px");
		descriptionField.addClassName("form-field");

		imageUrlField = new TextField("URL slike");
		imageUrlField.setWidthFull();
		imageUrlField.setPlaceholder("https://example.com/image.jpg");
		imageUrlField.addClassName("form-field");

		basePriceField = new NumberField("Osnovna cena (RSD)");
		basePriceField.setWidthFull();
		basePriceField.setRequired(true);
		basePriceField.setMin(0);
		basePriceField.setStep(0.01);
		basePriceField.addClassName("form-field");

		activeCheckbox = new Checkbox("Aktivan");
		activeCheckbox.setValue(true);
		activeCheckbox.addClassName("form-checkbox");
		activeCheckbox.addValueChangeListener(event -> {
			activeCheckbox.setLabel(event.getValue() ? "Aktivan" : "Neaktivan");
		});

		categoryComboBox = new ComboBox<>("Kategorija");
		categoryComboBox.setWidthFull();
		categoryComboBox.setRequired(true);
		categoryComboBox.setItemLabelGenerator(CategoryResponseDTO::getName);
		categoryComboBox.addClassName("form-field");
		loadCategories();

		saveButton = new Button("Sačuvaj");
		saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		saveButton.addClassName("form-button-save");

		cancelButton = new Button("Otkaži");
		cancelButton.addClassName("form-button-cancel");

		HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
		buttonLayout.setPadding(true);
		buttonLayout.setSpacing(true);

		formContainer.add(
				nameField,
				descriptionField,
				imageUrlField,
				basePriceField,
				activeCheckbox,
				categoryComboBox,
				buttonLayout
		);

		add(formContainer);
	}

	private void loadCategories() {
		try {
			List<CategoryResponseDTO> categories = categoryService.findAll();
			categoryComboBox.setItems(categories);
		} catch (Exception e) {
			showError("Greška pri učitavanju kategorija: " + e.getMessage());
		}
	}

	private void setupListeners() {
		saveButton.addClickListener(e -> presenter.onSaveClick());
		cancelButton.addClickListener(e -> presenter.onCancelClick());
	}

	public void setArticleData(ArticleResponseDTO article) {
		nameField.setValue(article.getName() != null ? article.getName() : "");
		descriptionField.setValue(article.getDescription() != null ? article.getDescription() : "");
		imageUrlField.setValue(article.getImageUrl() != null ? article.getImageUrl() : "");
		basePriceField.setValue(article.getBasePrice());
		activeCheckbox.setValue(article.isActive());

		if (article.getCategory() != null) {
			categoryComboBox.setValue(article.getCategory());
		}
	}

	public void clearForm() {
		nameField.clear();
		descriptionField.clear();
		imageUrlField.clear();
		basePriceField.clear();
		activeCheckbox.setValue(true);
		categoryComboBox.clear();
		clearValidationErrors();
	}

	public String getArticleName() {
		return nameField.getValue();
	}

	public String getArticleDescription() {
		return descriptionField.getValue();
	}

	public String getArticleImageUrl() {
		return imageUrlField.getValue();
	}

	public Double getArticleBasePrice() {
		return basePriceField.getValue();
	}

	public Boolean getArticleActive() {
		return activeCheckbox.getValue();
	}

	public String getSelectedCategoryId() {
		CategoryResponseDTO selected = categoryComboBox.getValue();
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

	public void showDescriptionError(String message) {
		descriptionField.setInvalid(true);
		descriptionField.setErrorMessage(message);
	}

	public void showPriceError(String message) {
		basePriceField.setInvalid(true);
		basePriceField.setErrorMessage(message);
	}

	public void showCategoryError(String message) {
		categoryComboBox.setInvalid(true);
		categoryComboBox.setErrorMessage(message);
	}

	public void clearValidationErrors() {
		nameField.setInvalid(false);
		descriptionField.setInvalid(false);
		basePriceField.setInvalid(false);
		categoryComboBox.setInvalid(false);
	}

	public void navigateBackToArticles() {
		getUI().ifPresent(ui -> ui.navigate("products/articles"));
	}
}
