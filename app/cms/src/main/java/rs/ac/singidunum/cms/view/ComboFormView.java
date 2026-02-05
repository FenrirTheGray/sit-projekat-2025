package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
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
import rs.ac.singidunum.cms.dto.response.ComboResponseDTO;
import rs.ac.singidunum.cms.presenter.ComboFormPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = "products/combos/form/:key?", layout = MasterHeaderNavLayout.class)
@CssImport("./style/style-views.css")
public class ComboFormView extends VerticalLayout implements BeforeEnterObserver {

	private final ComboFormPresenter presenter;

	private H1 title;
	private TextField nameField;
	private TextArea descriptionField;
	private TextField imageUrlField;
	private NumberField basePriceField;
	private Checkbox activeCheckbox;

	private ArticleResponseDTO selectedMainArticle;
	private List<ArticleResponseDTO> selectedSides = new ArrayList<>();
	private List<ArticleResponseDTO> selectedDrinks = new ArrayList<>();

	private Button addMainButton;
	private Button addSideButton;
	private Button addDrinkButton;

	private VerticalLayout mainArticleDisplay;
	private VerticalLayout sidesDisplay;
	private VerticalLayout drinksDisplay;

	private VerticalLayout mainSection;
	private VerticalLayout sideSection;
	private VerticalLayout drinkSection;
	private VerticalLayout sideDrinkWrapper;

	private Span mainSelectionError;
	private Span selectionError;

	private Button saveButton;
	private Button cancelButton;
	private ProgressBar loadingIndicator;

	private List<ArticleResponseDTO> allArticles = new ArrayList<>();

	public ComboFormView(ComboFormPresenter presenter) {
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
		String comboKey = event.getRouteParameters().get("key").orElse(null);

		if (comboKey != null && !comboKey.isEmpty()) {
			title.setText("Uredi Combo");
		} else {
			title.setText("Novi Combo");
		}

		presenter.onViewLoad(comboKey);
	}

	private void initializeForm() {
		title = new H1("Novi Combo");
		title.addClassName("page-title");
		add(title);

		loadingIndicator = new ProgressBar();
		loadingIndicator.setIndeterminate(true);
		loadingIndicator.setVisible(false);
		add(loadingIndicator);

		HorizontalLayout formContainer = new HorizontalLayout();
		formContainer.setWidth("1200px");
		formContainer.setPadding(true);
		formContainer.setSpacing(true);
		formContainer.addClassName("form-container");
		formContainer.addClassName("combo-form-container");

		VerticalLayout leftColumn = new VerticalLayout();
		leftColumn.setWidth("50%");
		leftColumn.setPadding(false);
		leftColumn.setSpacing(true);

		H3 basicInfoTitle = new H3("Osnovne informacije");
		basicInfoTitle.addClassName("combo-section-title");

		nameField = new TextField("Naziv");
		nameField.setWidthFull();
		nameField.setRequired(true);
		nameField.addClassName("form-field");

		descriptionField = new TextArea("Opis");
		descriptionField.setWidthFull();
		descriptionField.setHeight("100px");
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

		saveButton = new Button("Sačuvaj");
		saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		saveButton.addClassName("form-button-save");
		saveButton.setEnabled(false);

		cancelButton = new Button("Otkaži");
		cancelButton.addClassName("form-button-cancel");

		HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
		buttonLayout.setPadding(false);
		buttonLayout.setSpacing(true);
		buttonLayout.addClassName("combo-button-layout");

		leftColumn.add(
				basicInfoTitle,
				nameField,
				descriptionField,
				imageUrlField,
				basePriceField,
				activeCheckbox,
				buttonLayout
		);

		VerticalLayout rightColumn = new VerticalLayout();
		rightColumn.setWidth("50%");
		rightColumn.setPadding(false);
		rightColumn.setSpacing(true);

		H3 selectionsTitle = new H3("Izbor artikala");
		selectionsTitle.addClassName("combo-section-title");

		selectionError = new Span();
		selectionError.addClassName("combo-error-text");
		selectionError.setVisible(false);

		VerticalLayout selectionsContainer = new VerticalLayout();
		selectionsContainer.setWidthFull();
		selectionsContainer.setPadding(false);
		selectionsContainer.setSpacing(true);

		mainSection = createSelectionSection(
				"Glavni artikal",
				"Izaberite glavni artikal"
		);
		addMainButton = new Button("+ Dodaj");
		addMainButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		addMainButton.addClassName("combo-add-button");
		mainArticleDisplay = new VerticalLayout();
		mainArticleDisplay.setPadding(false);
		mainArticleDisplay.setSpacing(false);
		mainSelectionError = new Span();
		mainSelectionError.addClassName("combo-error-text");
		mainSelectionError.setVisible(false);
		mainSection.add(addMainButton, mainArticleDisplay, mainSelectionError);

		Hr separator1 = new Hr();
		separator1.addClassName("combo-separator");

		sideSection = createSelectionSection(
				"Prilozi",
				"Izaberite artikle za side izbor"
		);
		addSideButton = new Button("+ Dodaj");
		addSideButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		addSideButton.addClassName("combo-add-button");
		sidesDisplay = new VerticalLayout();
		sidesDisplay.setPadding(false);
		sidesDisplay.setSpacing(false);
		sideSection.add(addSideButton, sidesDisplay);

		Hr separator2 = new Hr();
		separator2.addClassName("combo-separator");

		drinkSection = createSelectionSection(
				"Pića",
				"Izaberite artikle za drink izbor"
		);
		addDrinkButton = new Button("+ Dodaj");
		addDrinkButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		addDrinkButton.addClassName("combo-add-button");
		drinksDisplay = new VerticalLayout();
		drinksDisplay.setPadding(false);
		drinksDisplay.setSpacing(false);
		drinkSection.add(addDrinkButton, drinksDisplay);

		sideDrinkWrapper = new VerticalLayout();
		sideDrinkWrapper.setPadding(false);
		sideDrinkWrapper.setSpacing(false);
		sideDrinkWrapper.setWidthFull();
		sideDrinkWrapper.add(sideSection, separator2, drinkSection);

		selectionsContainer.add(mainSection, separator1, sideDrinkWrapper, selectionError);

		rightColumn.add(
				selectionsTitle,
				selectionsContainer
		);

		formContainer.add(leftColumn, rightColumn);
		add(formContainer);
	}

	private VerticalLayout createSelectionSection(String title, String description) {
		VerticalLayout section = new VerticalLayout();
		section.setPadding(false);
		section.setSpacing(false);
		section.setWidthFull();

		H3 sectionTitle = new H3(title);
		sectionTitle.addClassName("combo-section-title--small");

		Span sectionDesc = new Span(description);
		sectionDesc.addClassName("combo-section-description");

		section.add(sectionTitle, sectionDesc);
		return section;
	}

	private HorizontalLayout createSelectedArticleItem(ArticleResponseDTO article, Runnable onRemove) {
		HorizontalLayout item = new HorizontalLayout();
		item.setWidthFull();
		item.setAlignItems(Alignment.CENTER);
		item.setPadding(true);
		item.addClassName("combo-selected-item");

		VerticalLayout info = new VerticalLayout();
		info.setPadding(false);
		info.setSpacing(false);
		info.setFlexGrow(1);

		Span name = new Span(article.getName());
		name.addClassName("combo-selected-item__name");

		Span price = new Span(String.format("%.2f RSD", article.getBasePrice()));
		price.addClassName("combo-selected-item__price");

		info.add(name, price);

		Button removeButton = new Button(new Icon(VaadinIcon.CLOSE_SMALL));
		removeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_SMALL);
		removeButton.addClassName("combo-selected-item__remove");
		removeButton.addClickListener(e -> onRemove.run());

		item.add(info, removeButton);
		item.setFlexGrow(1, info);

		return item;
	}

	private void setupListeners() {
		saveButton.addClickListener(e -> presenter.onSaveClick());
		cancelButton.addClickListener(e -> presenter.onCancelClick());

		addMainButton.addClickListener(e -> openMainArticleDialog());
		addSideButton.addClickListener(e -> openSideArticleDialog());
		addDrinkButton.addClickListener(e -> openDrinkArticleDialog());
	}

	private void openMainArticleDialog() {
		ArticleSelectionDialog dialog = new ArticleSelectionDialog(
				"Izaberi Glavni Artikal",
				allArticles,
				false,
				selected -> {
					if (!selected.isEmpty()) {
						selectedMainArticle = selected.get(0);
						updateMainArticleDisplay();
						presenter.validateAndUpdateSaveButtonState();
					}
				}
		);
		dialog.open();
	}

	private void openSideArticleDialog() {
		ArticleSelectionDialog dialog = new ArticleSelectionDialog(
				"Izaberi Prilog",
				allArticles,
				false,
				selected -> {
					if (!selected.isEmpty()) {
						ArticleResponseDTO article = selected.get(0);
						if (selectedSides.stream().noneMatch(s -> s.getKey().equals(article.getKey()))) {
							selectedSides.add(article);
							updateSidesDisplay();
							presenter.validateAndUpdateSaveButtonState();
						}
					}
				}
		);
		dialog.open();
	}

	private void openDrinkArticleDialog() {
		ArticleSelectionDialog dialog = new ArticleSelectionDialog(
				"Izaberi Piće",
				allArticles,
				false,
				selected -> {
					if (!selected.isEmpty()) {
						ArticleResponseDTO article = selected.get(0);
						if (selectedDrinks.stream().noneMatch(d -> d.getKey().equals(article.getKey()))) {
							selectedDrinks.add(article);
							updateDrinksDisplay();
							presenter.validateAndUpdateSaveButtonState();
						}
					}
				}
		);
		dialog.open();
	}

	private void updateMainArticleDisplay() {
		mainArticleDisplay.removeAll();
		if (selectedMainArticle != null) {
			addMainButton.setEnabled(false);
			mainArticleDisplay.add(createSelectedArticleItem(selectedMainArticle, () -> {
				selectedMainArticle = null;
				updateMainArticleDisplay();
				presenter.validateAndUpdateSaveButtonState();
			}));
		} else {
			addMainButton.setEnabled(true);
		}
	}

	private void updateSidesDisplay() {
		sidesDisplay.removeAll();
		for (ArticleResponseDTO article : selectedSides) {
			sidesDisplay.add(createSelectedArticleItem(article, () -> {
				selectedSides.removeIf(s -> s.getKey().equals(article.getKey()));
				updateSidesDisplay();
				presenter.validateAndUpdateSaveButtonState();
			}));
		}
	}

	private void updateDrinksDisplay() {
		drinksDisplay.removeAll();
		for (ArticleResponseDTO article : selectedDrinks) {
			drinksDisplay.add(createSelectedArticleItem(article, () -> {
				selectedDrinks.removeIf(d -> d.getKey().equals(article.getKey()));
				updateDrinksDisplay();
				presenter.validateAndUpdateSaveButtonState();
			}));
		}
	}

	public void setAvailableArticles(List<ArticleResponseDTO> articles) {
		this.allArticles = articles != null ? articles : new ArrayList<>();
	}

	public void setComboData(ComboResponseDTO combo) {
		nameField.setValue(combo.getName() != null ? combo.getName() : "");
		descriptionField.setValue(combo.getDescription() != null ? combo.getDescription() : "");
		imageUrlField.setValue(combo.getImageUrl() != null ? combo.getImageUrl() : "");
		basePriceField.setValue(combo.getBasePrice());
		activeCheckbox.setValue(combo.isActive());

		if (combo.getMainSelection() != null && !combo.getMainSelection().isEmpty()) {
			ArticleResponseDTO mainArticle = combo.getMainSelection().get(0);
			selectedMainArticle = allArticles.stream()
					.filter(a -> a.getKey().equals(mainArticle.getKey()))
					.findFirst()
					.orElse(mainArticle);
			updateMainArticleDisplay();
		}

		if (combo.getSideSelection() != null) {
			selectedSides = allArticles.stream()
					.filter(a -> combo.getSideSelection().stream()
							.anyMatch(s -> s.getKey().equals(a.getKey())))
					.collect(Collectors.toList());
			updateSidesDisplay();
		}

		if (combo.getDrinkSelection() != null) {
			selectedDrinks = allArticles.stream()
					.filter(a -> combo.getDrinkSelection().stream()
							.anyMatch(d -> d.getKey().equals(a.getKey())))
					.collect(Collectors.toList());
			updateDrinksDisplay();
		}
	}

	public void clearForm() {
		nameField.clear();
		descriptionField.clear();
		imageUrlField.clear();
		basePriceField.clear();
		activeCheckbox.setValue(true);
		selectedMainArticle = null;
		selectedSides.clear();
		selectedDrinks.clear();
		updateMainArticleDisplay();
		updateSidesDisplay();
		updateDrinksDisplay();
		clearValidationErrors();
	}

	public String getComboName() {
		return nameField.getValue();
	}

	public String getComboDescription() {
		return descriptionField.getValue();
	}

	public String getComboImageUrl() {
		return imageUrlField.getValue();
	}

	public Double getComboBasePrice() {
		return basePriceField.getValue();
	}

	public Boolean getComboActive() {
		return activeCheckbox.getValue();
	}

	public String getSelectedMainArticleKey() {
		return selectedMainArticle != null ? selectedMainArticle.getKey() : null;
	}

	public List<String> getSelectedSideArticleKeys() {
		return selectedSides.stream()
				.map(ArticleResponseDTO::getKey)
				.collect(Collectors.toList());
	}

	public List<String> getSelectedDrinkArticleKeys() {
		return selectedDrinks.stream()
				.map(ArticleResponseDTO::getKey)
				.collect(Collectors.toList());
	}

	public void showLoading() {
		loadingIndicator.setVisible(true);
		saveButton.setEnabled(false);
	}

	public void hideLoading() {
		loadingIndicator.setVisible(false);
	}

	public void setSaveButtonEnabled(boolean enabled) {
		saveButton.setEnabled(enabled);
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
		basePriceField.setInvalid(true);
		basePriceField.setErrorMessage(message);
	}

	public void showMainSelectionError(String message) {
		mainSelectionError.setText(message);
		mainSelectionError.setVisible(true);
		mainSection.addClassName("combo-section--error");
	}

	public void showSelectionError(String message) {
		selectionError.setText(message);
		selectionError.setVisible(true);
		sideDrinkWrapper.addClassName("combo-section--error");
	}

	public void clearSelectionErrors() {
		mainSelectionError.setVisible(false);
		selectionError.setVisible(false);
		mainSection.removeClassName("combo-section--error");
		sideDrinkWrapper.removeClassName("combo-section--error");
	}

	public void clearValidationErrors() {
		nameField.setInvalid(false);
		basePriceField.setInvalid(false);
		clearSelectionErrors();
	}

	public void navigateBackToCombos() {
		getUI().ifPresent(ui -> ui.navigate("products/combos"));
	}
}
