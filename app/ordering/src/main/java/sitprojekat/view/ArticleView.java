package sitprojekat.view;

import java.util.List;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import sitprojekat.model.Article;
import sitprojekat.service.ArticleService;


@StyleSheet("/css/style.css")
@Route(value = "Article",layout = HeaderAndNavBar.class)
public class ArticleView extends HorizontalLayout implements HasUrlParameter<String>{

	private static final long serialVersionUID = -4405949928698782190L;
	private Article mainArticle;
	private H2 articleName = new H2();
	private Span articleDescription = new Span();
	private Span articlePrice = new Span();
	private Button addToCartButton = new Button();
	private IntegerField productCounter = new IntegerField();
	private ArticleService service = new ArticleService();

	@Override
	public void setParameter(BeforeEvent event, String sentArticleID) {
		this.mainArticle = service.findByID(sentArticleID);
		if (mainArticle != null) {
			this.articleName.setText(mainArticle.getName());
			this.articleDescription.setText(mainArticle.getDescription());
			this.articlePrice.setText(String.format("%.2f RSD", mainArticle.getBasePrice()));
			updateButtonValue();
		} else {
			this.articleName.setText("Artikal nije pronađen");
			this.articleDescription.setText("Molimo vas pokušajte ponovo");
			this.articlePrice.setText("0.00 RSD");
			this.addToCartButton.setEnabled(false);
			this.productCounter.setEnabled(false);
		}
	}

	public ArticleView() {
		setPadding(true);
		setSpacing(true);
		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);

		// Back button
		Icon backArrowIcon = VaadinIcon.ARROW_BACKWARD.create();
		Button buttonBack = new Button("Povratak", backArrowIcon);
		buttonBack.addClassName("btn-primary");
		buttonBack.addClickListener(e -> UI.getCurrent().navigate(ProductsView.class));

		// Article card (gold/tan background)
		VerticalLayout articleCard = new VerticalLayout();
		articleCard.addClassName("card");
		articleCard.setWidth("400px");
		articleCard.setAlignItems(Alignment.CENTER);
		articleCard.setPadding(true);

		// Article name
		articleName.addClassName("article-detail-title");
		articleName.getStyle().set("margin", "0");

		// Article description
		articleDescription.addClassName("article-detail-desc");
		articleDescription.getStyle().set("text-align", "center");

		// Article image
		Image articleImage = new Image("/images/image_burger.jpg", "slika proizvoda");
		articleImage.setWidth("300px");
		articleImage.setHeight("200px");
		articleImage.getStyle().set("border-radius", "8px");
		articleImage.getStyle().set("object-fit", "cover");

		// Price
		articlePrice.addClassName("article-detail-price");

		articleCard.add(articleName, articleDescription, articleImage, articlePrice);

		// Counter and add to cart
		productCounter.setValue(1);
		productCounter.setMin(1);
		productCounter.setMax(99);
		productCounter.setStepButtonsVisible(true);
		productCounter.addClassName("productCounter");
		productCounter.addValueChangeListener(e -> updateButtonValue());

		addToCartButton.addClassName("btn-success");
		addToCartButton.setWidth("100%");

		HorizontalLayout counterAndAddtoCartContainer = new HorizontalLayout();
		counterAndAddtoCartContainer.setWidthFull();
		counterAndAddtoCartContainer.setAlignItems(Alignment.CENTER);
		counterAndAddtoCartContainer.add(productCounter, addToCartButton);

		// Left side container
		VerticalLayout leftSideContainer = new VerticalLayout();
		leftSideContainer.setWidth("450px");
		leftSideContainer.setAlignItems(Alignment.START);
		leftSideContainer.add(buttonBack, articleCard, counterAndAddtoCartContainer);

		// Right side - Size selection
		H3 sizeChoice = new H3("Odabir Veličine");
		sizeChoice.addClassName("page-title");
		sizeChoice.getStyle().set("font-size", "18px");

		RadioButtonGroup<Article> radioArticleSize = new RadioButtonGroup<>();
		radioArticleSize.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
		radioArticleSize.addClassName("option-group");

		List<Article> sizeOptions = List.of(
			new Article("1", "Veliki", "opis1", 50.0, true),
			new Article("2", "Srednji", "opis2", 0.0, true),
			new Article("3", "Mali", "opis3", -30.0, true)
		);
		radioArticleSize.setItems(sizeOptions);
		radioArticleSize.setValue(sizeOptions.get(1)); // Default to medium

		radioArticleSize.setRenderer(new ComponentRenderer<>(article -> {
			VerticalLayout container = new VerticalLayout();
			container.setSpacing(false);
			container.setPadding(false);

			Span articleTitle = new Span(article.getName());
			articleTitle.getStyle().set("font-weight", "bold");
			articleTitle.getStyle().set("color", "#3F220F");

			String priceText = article.getBasePrice() >= 0
				? "+ " + String.format("%.0f", article.getBasePrice()) + " RSD"
				: "- " + String.format("%.0f", Math.abs(article.getBasePrice())) + " RSD";
			Span articlePriceSubText = new Span(priceText);
			articlePriceSubText.getStyle().set("font-size", "var(--lumo-font-size-s)");
			articlePriceSubText.getStyle().set("color", "gray");

			container.add(articleTitle, articlePriceSubText);
			return container;
		}));

		// Modifiers selection
		H3 modificationChoice = new H3("Odabir Priloga");
		modificationChoice.addClassName("page-title");
		modificationChoice.getStyle().set("font-size", "18px");

		CheckboxGroup<Article> checkBoxModifikator = new CheckboxGroup<>();
		checkBoxModifikator.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
		checkBoxModifikator.addClassName("option-group");

		List<Article> modifierOptions = List.of(
			new Article("1", "Kečap", "opis1", 20.0, true),
			new Article("2", "Majonez", "opis2", 20.0, true),
			new Article("3", "Senf", "opis3", 15.0, true),
			new Article("4", "Ljuta papričica", "opis4", 30.0, true)
		);
		checkBoxModifikator.setItems(modifierOptions);

		checkBoxModifikator.setRenderer(new ComponentRenderer<>(article -> {
			VerticalLayout container = new VerticalLayout();
			container.setSpacing(false);
			container.setPadding(false);

			Span articleTitle = new Span(article.getName());
			articleTitle.getStyle().set("font-weight", "bold");
			articleTitle.getStyle().set("color", "#3F220F");

			Span articlePriceSubText = new Span("+ " + String.format("%.0f", article.getBasePrice()) + " RSD");
			articlePriceSubText.getStyle().set("font-size", "var(--lumo-font-size-s)");
			articlePriceSubText.getStyle().set("color", "gray");

			container.add(articleTitle, articlePriceSubText);
			return container;
		}));

		// Right side container
		VerticalLayout rightSideContainer = new VerticalLayout();
		rightSideContainer.setWidth("350px");
		rightSideContainer.add(sizeChoice, radioArticleSize, modificationChoice, checkBoxModifikator);

		add(leftSideContainer, rightSideContainer);
	}

	private void updateButtonValue() {
		if (mainArticle != null) {
			double totalPrice = mainArticle.getBasePrice() * productCounter.getValue();
			addToCartButton.setText("Dodaj u korpu (" + String.format("%.2f", totalPrice) + " RSD)");
			addToCartButton.setIcon(VaadinIcon.CART_O.create());
		}
	}
}
