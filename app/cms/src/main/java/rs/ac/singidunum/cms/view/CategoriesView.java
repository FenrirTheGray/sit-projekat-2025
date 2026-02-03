package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
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
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import rs.ac.singidunum.cms.dto.response.CategoryResponseDTO;
import rs.ac.singidunum.cms.interfaces.CategoriesViewInterface;
import rs.ac.singidunum.cms.presenter.CategoriesPresenter;
import rs.ac.singidunum.cms.presenter.CategoryFormPresenter;

import java.util.List;

@Route(value = "products/categories", layout = MasterHeaderNavLayout.class)
@CssImport("./style/style-views.css")
public class CategoriesView extends VerticalLayout implements CategoriesViewInterface {

	private final CategoriesPresenter presenter;
	private final CategoryFormPresenter categoryFormPresenter;
	private CategoryFormDialog categoryFormDialog;
	private TextField searchBar;
	private Button buttonAdd;
	private VerticalLayout containerAllCategories;
	private ProgressBar loadingIndicator;

	public CategoriesView(CategoriesPresenter presenter, CategoryFormPresenter categoryFormPresenter) {
		this.presenter = presenter;
		this.categoryFormPresenter = categoryFormPresenter;
		this.presenter.setView(this);

		this.categoryFormDialog = new CategoryFormDialog(
			categoryFormPresenter,
			() -> presenter.loadCategories()
		);

		H1 naslov = new H1("Kategorije");
		naslov.addClassName("page-title");
		add(naslov);

		loadingIndicator = new ProgressBar();
		loadingIndicator.setIndeterminate(true);
		loadingIndicator.setVisible(false);
		add(loadingIndicator);

		createSearchBarAndAddButton();
		createCategoriesContainer();

		setSizeFull();
		setAlignItems(Alignment.CENTER);

		setupListeners();

		this.presenter.onViewLoad();
	}

	private void setupListeners() {
		searchBar.addValueChangeListener(e ->
				presenter.onSearchChange(e.getValue()));
		buttonAdd.addClickListener(e ->
				presenter.onAddCategoryClick());
	}

	private void createSearchBarAndAddButton() {
		searchBar = new TextField();
		searchBar.setPlaceholder("Pretraga");
		searchBar.setClearButtonVisible(true);
		searchBar.setPrefixComponent(VaadinIcon.SEARCH.create());
		searchBar.addClassName("view-search-bar");

		buttonAdd = new Button("Dodaj Kategoriju", VaadinIcon.PLUS.create());
		buttonAdd.addClassName("view-button-add");

		HorizontalLayout containerToolbar = new HorizontalLayout(searchBar, buttonAdd);
		containerToolbar.setWidthFull();
		containerToolbar.setJustifyContentMode(JustifyContentMode.BETWEEN);
		containerToolbar.setAlignItems(Alignment.CENTER);
		containerToolbar.addClassName("view-container-toolbar");

		add(containerToolbar);
	}

	private void createCategoriesContainer() {
		containerAllCategories = new VerticalLayout();
		containerAllCategories.setWidthFull();
		containerAllCategories.setPadding(false);
		containerAllCategories.setSpacing(true);
		containerAllCategories.addClassName("view-container-all-items");

		add(containerAllCategories);
	}

	private HorizontalLayout createCategoryCard(CategoryResponseDTO category) {
		HorizontalLayout categoryCard = new HorizontalLayout();
		categoryCard.setPadding(false);
		categoryCard.setSpacing(false);
		categoryCard.setAlignItems(Alignment.CENTER);
		categoryCard.addClassName("item-card");

		Div iconPlaceholder = new Div();
		iconPlaceholder.addClassName("item-image");
		Icon categoryIcon = VaadinIcon.FOLDER.create();
		categoryIcon.setSize("40px");
		categoryIcon.addClassName("item-icon");
		iconPlaceholder.add(categoryIcon);

		VerticalLayout infoContainer = new VerticalLayout();
		infoContainer.setSpacing(false);
		infoContainer.setPadding(false);
		infoContainer.setJustifyContentMode(JustifyContentMode.AROUND);
		infoContainer.addClassName("item-container-info");

		Span naziv = new Span(category.getName());
		naziv.addClassName("item-info");
		Span opis = new Span(category.getDescription());
		opis.addClassName("item-info");
		Span statusBadge = new Span(category.isActive() ? "Aktivna" : "Neaktivna");
		statusBadge.addClassName("status-badge");
		statusBadge.addClassName(category.isActive() ? "status-badge--active" : "status-badge--inactive");

		infoContainer.add(naziv, opis, statusBadge);

		VerticalLayout actionsContainer = new VerticalLayout();
		actionsContainer.setSpacing(true);
		actionsContainer.setPadding(false);
		actionsContainer.setAlignItems(Alignment.END);
		actionsContainer.addClassName("item-container-buttons");

		Icon iconEdit = VaadinIcon.EDIT.create();
		iconEdit.setSize("25px");
		Button buttonEdit = new Button(iconEdit);
		buttonEdit.addClassName("item-buttons-edit-and-delete");
		buttonEdit.addClickListener(e -> presenter.onEditCategoryClick(category.getId()));

		Icon iconDelete = VaadinIcon.TRASH.create();
		iconDelete.setSize("25px");
		Button buttonDelete = new Button(iconDelete);
		buttonDelete.addClassName("item-buttons-edit-and-delete");
		buttonDelete.addClickListener(e ->
				presenter.onDeleteCategoryClick(category.getId(), category.getName()));

		actionsContainer.add(buttonEdit, buttonDelete);

		categoryCard.add(iconPlaceholder, infoContainer, actionsContainer);
		categoryCard.expand(infoContainer);

		return categoryCard;
	}


	@Override
	public void setCategories(List<CategoryResponseDTO> categories) {
		containerAllCategories.removeAll();
		for (CategoryResponseDTO category : categories) {
			HorizontalLayout card = createCategoryCard(category);
			containerAllCategories.add(card);
		}
	}

	@Override
	public void clearCategories() {
		containerAllCategories.removeAll();
	}

	@Override
	public String getSearchText() {
		return searchBar.getValue();
	}

	@Override
	public void showLoading() {
		loadingIndicator.setVisible(true);
	}

	@Override
	public void hideLoading() {
		loadingIndicator.setVisible(false);
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
	public void showEmptyState(String message) {
		containerAllCategories.removeAll();
		Span emptyMessage = new Span(message);
		emptyMessage.addClassName("empty-state-message");
		containerAllCategories.add(emptyMessage);
	}

	@Override
	public void openCategoryEditDialog(String categoryId) {
		categoryFormDialog.openForEdit(categoryId);
	}

	@Override
	public void openCategoryCreateDialog() {
		categoryFormDialog.openForCreate();
	}

	@Override
	public void showDeleteConfirmation(String categoryId, String categoryName) {
		ConfirmDialog dialog = new ConfirmDialog();
		dialog.setHeader("Potvrda brisanja");
		dialog.setText("Da li ste sigurni da želite da obrišete kategoriju: " + categoryName + "?");
		dialog.setCancelable(true);
		dialog.setCancelText("Otkaži");
		dialog.setConfirmText("Obriši");
		dialog.setConfirmButtonTheme("error primary");
		dialog.addConfirmListener(e -> presenter.confirmDeleteCategory(categoryId));
		dialog.open();
	}
}
