package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import rs.ac.singidunum.cms.dto.response.ArticleResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ArticleSelectionDialog extends Dialog {

	private final List<ArticleResponseDTO> allArticles;
	private final boolean multiSelect;
	private final Consumer<List<ArticleResponseDTO>> onSelect;

	private TextField searchField;
	private MultiSelectListBox<ArticleResponseDTO> articleListBox;
	private Button confirmButton;
	private Button cancelButton;

	public ArticleSelectionDialog(
			String title,
			List<ArticleResponseDTO> articles,
			boolean multiSelect,
			Consumer<List<ArticleResponseDTO>> onSelect) {
		this.allArticles = articles != null ? articles : new ArrayList<>();
		this.multiSelect = multiSelect;
		this.onSelect = onSelect;

		initializeDialog();
		initializeContent(title);
		setupListeners();
	}

	private void initializeDialog() {
		setModal(true);
		setDraggable(false);
		setResizable(false);
		setWidth("500px");
		setCloseOnEsc(true);
		setCloseOnOutsideClick(false);
		addClassName("article-selection-dialog");
	}

	private void initializeContent(String title) {
		VerticalLayout content = new VerticalLayout();
		content.setPadding(true);
		content.setSpacing(true);

		H2 dialogTitle = new H2(title);
		dialogTitle.addClassName("combo-dialog-title");

		searchField = new TextField();
		searchField.setPlaceholder("Pretraži artikle...");
		searchField.setWidthFull();
		searchField.setClearButtonVisible(true);
		searchField.setValueChangeMode(ValueChangeMode.EAGER);
		searchField.addClassName("form-field");

		articleListBox = new MultiSelectListBox<>();
		articleListBox.setHeight("300px");
		articleListBox.setWidthFull();
		articleListBox.setItems(allArticles);
		articleListBox.setRenderer(createArticleRenderer());
		articleListBox.addClassName("combo-selection-list");

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setWidthFull();
		buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
		buttonLayout.setSpacing(true);

		cancelButton = new Button("Otkaži");
		cancelButton.addClassName("cms-button-secondary");

		confirmButton = new Button("Potvrdi");
		confirmButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		if (!multiSelect) {
			confirmButton.setVisible(false);
		}

		buttonLayout.add(cancelButton, confirmButton);

		content.add(dialogTitle, searchField, articleListBox, buttonLayout);
		add(content);
	}

	private ComponentRenderer<HorizontalLayout, ArticleResponseDTO> createArticleRenderer() {
		return new ComponentRenderer<>(article -> {
			HorizontalLayout row = new HorizontalLayout();
			row.setSpacing(true);
			row.setAlignItems(FlexComponent.Alignment.CENTER);
			row.setPadding(true);
			row.setWidthFull();

			Span name = new Span(article.getName());
			name.addClassName("combo-article-row__name");

			Span price = new Span(String.format("%.2f RSD", article.getBasePrice()));
			price.addClassName("combo-article-row__price");

			row.add(name, price);
			return row;
		});
	}

	private void setupListeners() {
		searchField.addValueChangeListener(e -> filterArticles(e.getValue()));

		cancelButton.addClickListener(e -> close());

		confirmButton.addClickListener(e -> {
			Set<ArticleResponseDTO> selected = articleListBox.getSelectedItems();
			if (onSelect != null && !selected.isEmpty()) {
				onSelect.accept(new ArrayList<>(selected));
			}
			close();
		});

		if (!multiSelect) {
			articleListBox.addValueChangeListener(e -> {
				Set<ArticleResponseDTO> selected = e.getValue();
				if (!selected.isEmpty() && onSelect != null) {
					onSelect.accept(new ArrayList<>(selected));
					close();
				}
			});
		}
	}

	private void filterArticles(String searchText) {
		if (searchText == null || searchText.trim().isEmpty()) {
			articleListBox.setItems(allArticles);
		} else {
			String lowerSearch = searchText.toLowerCase().trim();
			List<ArticleResponseDTO> filtered = allArticles.stream()
					.filter(a -> a.getName().toLowerCase().contains(lowerSearch))
					.collect(Collectors.toList());
			articleListBox.setItems(filtered);
		}
	}

	public void setPreselectedArticles(List<ArticleResponseDTO> preselected) {
		if (preselected != null && !preselected.isEmpty()) {
			Set<ArticleResponseDTO> matching = allArticles.stream()
					.filter(a -> preselected.stream()
							.anyMatch(p -> p.getKey().equals(a.getKey())))
					.collect(Collectors.toSet());
			articleListBox.setValue(matching);
		}
	}
}
