package sitprojekat.view;

import java.util.List;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import sitprojekat.model.Article;
import sitprojekat.service.ArticleService;



@StyleSheet("/css/style.css")
@Route(value = "",layout = HeaderAndNavBar.class)
@RouteAlias(value = "Products",layout = HeaderAndNavBar.class)
public class ProductsView extends VerticalLayout{

	/**
	 *
	 */
	private static final long serialVersionUID = 6116769739933744188L;

	private final ArticleService service;
	private Grid<Article> articleTable;
	private List<Article> allArticles;


	public ProductsView(ArticleService service) {
		this.service=service;

		TextField filterTextBox=new TextField();
		filterTextBox.setPlaceholder("Pretraga proizvoda...");
		filterTextBox.setClearButtonVisible(true);
		filterTextBox.setPrefixComponent(VaadinIcon.SEARCH.create());
		filterTextBox.setWidth("350px");
		filterTextBox.addClassName("search-filter");
		filterTextBox.setValueChangeMode(ValueChangeMode.LAZY);
		filterTextBox.addValueChangeListener(e -> filterArticles(e.getValue()));

		HorizontalLayout filterContainer=new HorizontalLayout(filterTextBox);
		filterContainer.setWidthFull();
		filterContainer.setJustifyContentMode(JustifyContentMode.END);
		filterContainer.getStyle().set("padding", "16px 0");

		articleTable=new Grid<>(Article.class, false);
		articleTable.addClassName("article-grid");
		articleTable.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

		// Configure columns with better headers
		articleTable.addColumn(Article::getId)
			.setHeader("Id")
			.setSortable(true)
			.setWidth("80px")
			.setFlexGrow(0);

		articleTable.addColumn(Article::getName)
			.setHeader("Naziv")
			.setSortable(true)
			.setAutoWidth(true);

		articleTable.addColumn(Article::getDescription)
			.setHeader("Opis")
			.setSortable(true)
			.setAutoWidth(true);

		articleTable.addColumn(article -> String.format("%.2f RSD", article.getBasePrice()))
			.setHeader("Cena")
			.setSortable(true)
			.setWidth("120px")
			.setFlexGrow(0);

		articleTable.addColumn(new ComponentRenderer<>(article -> {
			Span badge = new Span(article.isActive() ? "Aktivan" : "Neaktivan");
			badge.addClassName("status-badge");
			badge.addClassName(article.isActive() ? "status-active" : "status-inactive");
			return badge;
		}))
			.setHeader("Status")
			.setWidth("100px")
			.setFlexGrow(0);

		allArticles = service.getArticles();
		articleTable.setItems(allArticles);

		articleTable.addItemDoubleClickListener(e->{
			Article article=e.getItem();
			UI.getCurrent().navigate(ArticleView.class,article.getId());
		});

		add(filterContainer,articleTable);
	}

	private void filterArticles(String filterText) {
		if (filterText == null || filterText.isEmpty()) {
			articleTable.setItems(allArticles);
		} else {
			String lowerFilter = filterText.toLowerCase();
			articleTable.setItems(allArticles.stream()
				.filter(article ->
					article.getName().toLowerCase().contains(lowerFilter) ||
					article.getDescription().toLowerCase().contains(lowerFilter))
				.toList());
		}
	}


}
