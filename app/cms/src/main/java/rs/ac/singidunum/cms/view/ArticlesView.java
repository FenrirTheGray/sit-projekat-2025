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
import rs.ac.singidunum.cms.dto.response.ArticleResponseDTO;
import rs.ac.singidunum.cms.presenter.ArticlesPresenter;

import java.util.List;

@Route(value = "products/articles", layout = MasterHeaderNavLayout.class)
@CssImport("./style/style-views.css")
public class ArticlesView extends VerticalLayout {

	private final ArticlesPresenter presenter;
	private TextField searchBar;
	private Button buttonAdd;
	private VerticalLayout containerAllArticles;
	private ProgressBar loadingIndicator;

	public ArticlesView(ArticlesPresenter presenter) {
		this.presenter = presenter;
		this.presenter.setView(this);

		H1 naslov = new H1("Artikli");
		naslov.addClassName("page-title");
		add(naslov);

		loadingIndicator = new ProgressBar();
		loadingIndicator.setIndeterminate(true);
		loadingIndicator.setVisible(false);
		add(loadingIndicator);

		createSearchBarAndAddButton();
		createArticlesContainer();

		setSizeFull();
		setAlignItems(Alignment.CENTER);

		setupListeners();

		this.presenter.onViewLoad();
	}

	private void setupListeners() {
		searchBar.addValueChangeListener(e ->
				presenter.onSearchChange(e.getValue()));
		buttonAdd.addClickListener(e ->
				presenter.onAddArticleClick());
	}

	private void createSearchBarAndAddButton() {
		searchBar = new TextField();
		searchBar.setPlaceholder("Pretraga");
		searchBar.setClearButtonVisible(true);
		searchBar.setPrefixComponent(VaadinIcon.SEARCH.create());
		searchBar.addClassName("view-search-bar");

		buttonAdd = new Button("Dodaj Artikal", VaadinIcon.PLUS.create());
		buttonAdd.addClassName("view-button-add");

		HorizontalLayout containerToolbar = new HorizontalLayout(searchBar, buttonAdd);
		containerToolbar.setWidthFull();
		containerToolbar.setJustifyContentMode(JustifyContentMode.BETWEEN);
		containerToolbar.setAlignItems(Alignment.CENTER);
		containerToolbar.addClassName("view-container-toolbar");

		add(containerToolbar);
	}

	private void createArticlesContainer() {
		containerAllArticles = new VerticalLayout();
		containerAllArticles.setWidthFull();
		containerAllArticles.setPadding(false);
		containerAllArticles.setSpacing(true);
		containerAllArticles.addClassName("view-container-all-items");

		add(containerAllArticles);
	}

	private HorizontalLayout createArticleCard(ArticleResponseDTO article) {
		HorizontalLayout articleCard = new HorizontalLayout();
		articleCard.setPadding(false);
		articleCard.setSpacing(false);
		articleCard.setAlignItems(Alignment.CENTER);
		articleCard.addClassName("item-card");

		Div imagePlaceholder = new Div();
		imagePlaceholder.addClassName("item-image");
		Icon articleIcon = VaadinIcon.PACKAGE.create();
		articleIcon.setSize("40px");
		articleIcon.addClassName("item-icon");
		imagePlaceholder.add(articleIcon);

		VerticalLayout infoContainer = new VerticalLayout();
		infoContainer.setSpacing(false);
		infoContainer.setPadding(false);
		infoContainer.setJustifyContentMode(JustifyContentMode.AROUND);
		infoContainer.addClassName("item-container-info");

		Span naziv = new Span(article.getName());
		naziv.addClassName("item-info");

		String formatiranaCena = String.format("%.2f RSD", article.getBasePrice());
		Span cena = new Span(formatiranaCena);
		cena.addClassName("item-info");

		Span opis = new Span(article.getDescription() != null ? article.getDescription() : "");
		opis.addClassName("item-info");

		Span statusBadge = new Span(article.isActive() ? "Aktivan" : "Neaktivan");
		statusBadge.addClassName("status-badge");
		statusBadge.addClassName(article.isActive() ? "status-badge--active" : "status-badge--inactive");

		infoContainer.add(naziv, cena, opis, statusBadge);

		VerticalLayout actionsContainer = new VerticalLayout();
		actionsContainer.setSpacing(true);
		actionsContainer.setPadding(false);
		actionsContainer.setAlignItems(Alignment.END);
		actionsContainer.addClassName("item-container-buttons");

		Icon iconEdit = VaadinIcon.EDIT.create();
		iconEdit.setSize("25px");
		Button buttonEdit = new Button(iconEdit);
		buttonEdit.addClassName("item-buttons-edit-and-delete");
		buttonEdit.addClickListener(e -> presenter.onEditArticleClick(article.getKey()));

		Icon iconDelete = VaadinIcon.TRASH.create();
		iconDelete.setSize("25px");
		Button buttonDelete = new Button(iconDelete);
		buttonDelete.addClassName("item-buttons-edit-and-delete");
		buttonDelete.addClickListener(e ->
				presenter.onDeleteArticleClick(article.getKey(), article.getName()));

		actionsContainer.add(buttonEdit, buttonDelete);

		articleCard.add(imagePlaceholder, infoContainer, actionsContainer);
		articleCard.expand(infoContainer);

		return articleCard;
	}

	public void setArticles(List<ArticleResponseDTO> articles) {
		containerAllArticles.removeAll();
		for (ArticleResponseDTO article : articles) {
			HorizontalLayout card = createArticleCard(article);
			containerAllArticles.add(card);
		}
	}

	public void clearArticles() {
		containerAllArticles.removeAll();
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
		containerAllArticles.removeAll();
		Span emptyMessage = new Span(message);
		emptyMessage.addClassName("empty-state-message");
		containerAllArticles.add(emptyMessage);
	}

	public void navigateToArticleEdit(String articleKey) {
		getUI().ifPresent(ui -> ui.navigate("products/articles/form/" + articleKey));
	}

	public void navigateToArticleCreate() {
		getUI().ifPresent(ui -> ui.navigate("products/articles/form"));
	}

	public void showDeleteConfirmation(String articleKey, String articleName) {
		ConfirmDialog dialog = new ConfirmDialog();
		dialog.setHeader("Potvrda brisanja");
		dialog.setText("Da li ste sigurni da želite da obrišete artikal: " + articleName + "?");
		dialog.setCancelable(true);
		dialog.setCancelText("Otkaži");
		dialog.setConfirmText("Obriši");
		dialog.setConfirmButtonTheme("error primary");
		dialog.addConfirmListener(e -> presenter.confirmDeleteArticle(articleKey));
		dialog.open();
	}
}
