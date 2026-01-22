package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@CssImport("./style/style-views.css")
@Route(value = "articles", layout = MasterLayout.class)
public class ArticlesView extends VerticalLayout {
    // atributi
    // ArticlesService - servis za artikle

    // konstruktor
    public ArticlesView() {
        // naslov
        H1 naslov = new H1("Artikli");
        naslov.getStyle().set("color", "white");
        add(naslov);

        // inicijalne metode
        createSearchBarAndAddButton();
        createArticlesContainer();

        // TODO: dodavanje liste proizvoda


        setSizeFull();
        setAlignItems(Alignment.CENTER);
    }

    private void createSearchBarAndAddButton() {
        // search bar
        TextField searchBar = new TextField();
        searchBar.setPlaceholder("Pretraga");
        searchBar.addClassName("articles-search-bar");
        searchBar.setClearButtonVisible(true);
        searchBar.setPrefixComponent(VaadinIcon.SEARCH.create());

        // button add
        Button buttonAdd = new Button("Dodaj Artikal", VaadinIcon.PLUS.create());
        buttonAdd.addClassName("articles-button-add");

        HorizontalLayout containerToolbar = new HorizontalLayout(searchBar, buttonAdd);
        containerToolbar.addClassName("articles-container-toolbar");
        containerToolbar.setWidthFull();
        containerToolbar.setJustifyContentMode(JustifyContentMode.BETWEEN);
        containerToolbar.setAlignItems(Alignment.CENTER);

        add(containerToolbar);
    }

    private void createArticlesContainer() {
        // glavni kontejner za sve artikle
        VerticalLayout containerAllArticles = new VerticalLayout();
        containerAllArticles.addClassName("articles-container-all-items"); // omogućavamo skrolovanje
        containerAllArticles.setWidthFull();
        containerAllArticles.setPadding(false);
        containerAllArticles.setSpacing(true);

        // GENERISANJE DUMMY PODATAKA ZA ITEME (ARTIKLE)
        for (int i = 1; i <= 50; i++) {
            // Pozivamo pomoćnu metodu da nam napravi jednu karticu
            double generisanaCena = 1000.0 + (i * 50.0);
            HorizontalLayout card = createArticleRow("Artikal broj " + i, generisanaCena, "Opis za nasumični artikal " + i);
            containerAllArticles.add(card);
        }

        add(containerAllArticles);
    }

    private HorizontalLayout createArticleRow(String nazivItema, double cenaItema, String opisItema) {
        // kontejner za svaki artikal pojedinačno (kartica artikla)
        HorizontalLayout articleCard = new HorizontalLayout();
        articleCard.addClassName("article-card");
        // articleCard.setWidthFull(); // prebačen u css -> width: 95%
        articleCard.setPadding(false);
        articleCard.setSpacing(false);
        articleCard.setAlignItems(Alignment.CENTER);

        // element kartice - slika
        Div imagePlaceholder = new Div();
        imagePlaceholder.addClassName("article-image");

        // element kartice - podaci
        VerticalLayout infoContainer = new VerticalLayout();
        infoContainer.addClassName("article-container-info");
        infoContainer.setSpacing(false);
        infoContainer.setPadding(false);
        infoContainer.setJustifyContentMode(JustifyContentMode.AROUND); // jednaki razmaci u visini

        Span naziv = new Span(nazivItema);
        naziv.addClassName("article-info");
        String formatiranaCena = String.format("%.2f", cenaItema);
        Span cena = new Span(formatiranaCena + " RSD");
        cena.addClassName("article-info");
        Span opis = new Span(opisItema);
        opis.addClassName("article-info");

        infoContainer.add(naziv, cena, opis);

        // element kartice - buttons (edit, delete)
        VerticalLayout actionsContainer = new VerticalLayout();
        actionsContainer.addClassName("article-container-buttons");
        actionsContainer.setSpacing(true);
        actionsContainer.setPadding(false);
        actionsContainer.setAlignItems(Alignment.END);

        Icon iconEdit = VaadinIcon.EDIT.create();
        iconEdit.setSize("25px");
        Button buttonEdit = new Button(iconEdit);
        buttonEdit.addClassName("article-buttons-edit-and-delete");
        Icon iconDelete = VaadinIcon.TRASH.create();
        iconDelete.setSize("25px");
        Button buttonDelete = new Button(iconDelete);
        buttonDelete.addClassName("article-buttons-edit-and-delete");

        actionsContainer.add(buttonEdit, buttonDelete);

        // dodavanje svega u karticu
        articleCard.add(imagePlaceholder, infoContainer, actionsContainer);
        articleCard.expand(infoContainer); // guramo ikonice (dugmiće)

        return articleCard; // vraćamo napravljenu karticu (card) za svaki item (artikal)
    }

}
