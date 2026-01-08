package sitprojekat.view;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
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

@CssImport("./style/style.css")
@Route(value = "Combo",layout = HeaderAndNavBar.class)
public class ComboView  extends HorizontalLayout implements HasUrlParameter<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8994010294830377854L;

	private Article mainCombo ;
	private H2 comboName=new H2();
    private Span comboDescription = new Span();
    private Button addToCartButton = new Button();
    private IntegerField productCounter = new IntegerField();
    private ArticleService service=new ArticleService();
	@Override
	public void setParameter(BeforeEvent event, String sentComboID) {
		
		this.mainCombo=service.findByID(sentComboID);
		if (mainCombo != null) {
	        this.comboName.setText(mainCombo.getName());
	        this.comboDescription.setText(mainCombo.getDescription());
	        updateButtonValue();
	    }else {
			this.comboName.setText("Placeholder ako nije dobro ucitan");
			this.comboDescription.setText("Placeholder ako nije dobro ucitan");
			this.addToCartButton.setDisableOnClick(true);
			this.productCounter.setEnabled(false);
			this.addToCartButton.setText("Dodaj u korpu () RSD");
			this.addToCartButton.setIcon(VaadinIcon.CART_O.create());
		}
	}
	public ComboView() {
		Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
		Button backButton=new Button("Povratak",backArrowIcon);
		backButton.addClassName("dugmeNazad");
		comboName.getStyle().set("color", "#ffffff");
		
		
		comboDescription.getStyle().set("color", "#ffffff");
		comboDescription.getStyle().set("overflow-wrap","break-word");
		comboDescription.setWidth("350px");
		
		Image articleImage=new Image("/images/image_burger.jpg","slika primer proizvoda");
		articleImage.setWidth("400px");
		articleImage.setHeight("300px");
		//Image articleImage=new Image();
		
		productCounter.addClassName("productCounter");
		productCounter.setValue(1);
		productCounter.setStepButtonsVisible(true);
		productCounter.getStyle().set("background-color", "#ffffff");//3F220F
		productCounter.getStyle().set("color", "#ffffff");
		
		
		
		//Icon shoppingCartIcon=VaadinIcon.CART_O.create();
		addToCartButton.getStyle().set("color", "#ffffff");
		addToCartButton.getStyle().set("background-color", "#3F220F");
		addToCartButton.setWidth("65%");
		
		productCounter.addValueChangeListener(e->{updateButtonValue();});
		
		
		VerticalLayout comboNameAndDescriptionAndImageContainer=new VerticalLayout();
		
		comboNameAndDescriptionAndImageContainer.add(comboName,comboDescription,articleImage);
		comboNameAndDescriptionAndImageContainer.setAlignItems(Alignment.CENTER);
		
		
		HorizontalLayout counterAndAddtoCartContainer=new HorizontalLayout();
		counterAndAddtoCartContainer.add(productCounter,addToCartButton);
		
		VerticalLayout leftSideContainer=new VerticalLayout();
		leftSideContainer.add(backButton,comboNameAndDescriptionAndImageContainer,counterAndAddtoCartContainer);
		leftSideContainer.setWidth("75%");
		
		H4 comboChoiceH4=new H4("Kombo sadrzi moguce izbore");
		comboChoiceH4.getStyle().set("color", "#ffffff");
		
		
		RadioButtonGroup<Article> radioComboMain = new RadioButtonGroup<>();
		radioComboMain.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);       //smer kako su poredjani
		radioComboMain.getStyle().set("background-color", "#ffffff");
		
		List<Article> listaArtikala=List.of(new Article("1", "Veliki", "opis1", 250.0, true),new Article("2", "Srednji", "opis2", 350.0, true)); // test za velicine
		
		radioComboMain.setItems(listaArtikala); 
		radioComboMain.setWidthFull();
		//radioComboMain.setHeight("250px");
		radioComboMain.setLabel("Moguci izbor za main");
																			// nacin da od svakog uzme listu njegovih podatka samo test za sad
		radioComboMain.setRenderer(new ComponentRenderer<>(article -> {  //za svaki artikal se pravi
		    VerticalLayout container = new VerticalLayout(); 
		    container.setSpacing(false);
		    container.setPadding(false);      // jedno ispod drugog bez razmaka
		    Span articleTitle = new Span("Naziv Comboa");   // naziv articla i izgled 
		    articleTitle.getStyle().set("font-weight", "bold");
		    articleTitle.getStyle().set("color", "#3F220F");

		    Span articlePriceSubText = new Span("+ "+String.valueOf(article.getBasePrice()+" RSD")); // ispod articla cena kao manji info
		    articlePriceSubText.getStyle().set("font-size", "var(--lumo-font-size-s)");              // manji font
		    articlePriceSubText.getStyle().set("color", "gray");									// boja

		    container.add(articleTitle, articlePriceSubText);
		    return container;                // dodaje u container i vraca pa ako ima jos pravi novi container
		}));
		
		
		RadioButtonGroup<Article> radioComboSide = new RadioButtonGroup<>();
		radioComboSide.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);       //smer kako su poredjani
		radioComboSide.getStyle().set("background-color", "#ffffff");
		
		//Span sideChoiceSpan=new Span("Moguci izbor za side");
		
		List<Article> sideChoiceList=List.of(new Article("1", "Veliki", "opis1", 250.0, true),new Article("2", "Srednji", "opis2", 350.0, true)); // test za velicine
		
		radioComboSide.setItems(sideChoiceList); 
		radioComboSide.setWidthFull();
		//radioComboSide.setHeight("250px");
		radioComboSide.setLabel("Moguci izbor za side");
		
		radioComboSide.setRenderer(new ComponentRenderer<>(article -> {  //za svaki artikal se pravi
		    VerticalLayout container = new VerticalLayout(); 
		    container.setSpacing(false);
		    container.setPadding(false);      // jedno ispod drugog bez razmaka
		    Span articleTitle = new Span("Naziv Comboa");   // naziv articla i izgled 
		    articleTitle.getStyle().set("font-weight", "bold");
		    articleTitle.getStyle().set("color", "#3F220F");

		    Span articlePriceSubText = new Span("+ "+String.valueOf(article.getBasePrice()+" RSD")); // ispod articla cena kao manji info
		    articlePriceSubText.getStyle().set("font-size", "var(--lumo-font-size-s)");              // manji font
		    articlePriceSubText.getStyle().set("color", "gray");									// boja

		    container.add(articleTitle, articlePriceSubText);
		    return container;                // dodaje u container i vraca pa ako ima jos pravi novi container
		}));
		
		
		RadioButtonGroup<Article> radioComboDrink = new RadioButtonGroup<>();
		radioComboDrink.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);       //smer kako su poredjani
		radioComboDrink.getStyle().set("background-color", "#ffffff");
		
		//Span sideChoiceSpan=new Span("Moguci izbor za side");
		
		List<Article> sideDrinkList=List.of(new Article("1", "Veliki", "opis1", 250.0, true),new Article("2", "Srednji", "opis2", 350.0, true)); // test za velicine
		
		radioComboDrink.setItems(sideDrinkList); 
		radioComboDrink.setWidthFull();
		radioComboDrink.setHeight("175px");
		radioComboDrink.setLabel("Moguci izbor za drink");
		
		radioComboDrink.setRenderer(new ComponentRenderer<>(article -> {  //za svaki artikal se pravi
		    VerticalLayout container = new VerticalLayout(); 
		    container.setSpacing(false);
		    container.setPadding(false);      // jedno ispod drugog bez razmaka
		    Span articleTitle = new Span("Naziv Comboa");   // naziv articla i izgled 
		    articleTitle.getStyle().set("font-weight", "bold");
		    articleTitle.getStyle().set("color", "#3F220F");

		    Span articlePriceSubText = new Span("+ "+String.valueOf(article.getBasePrice()+" RSD")); // ispod articla cena kao manji info
		    articlePriceSubText.getStyle().set("font-size", "var(--lumo-font-size-s)");              // manji font
		    articlePriceSubText.getStyle().set("color", "gray");									// boja

		    container.add(articleTitle, articlePriceSubText);
		    return container;                // dodaje u container i vraca pa ako ima jos pravi novi container
		}));
		
		
		
		
		
		H4 modificationChoice=new H4("Ukljuceni modifikatori");
		modificationChoice.getStyle().set("color", "#ffffff");

		
		
		CheckboxGroup<Article> checkBoxModifikatorMain = new CheckboxGroup<>();   
		checkBoxModifikatorMain.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);       //smer kako su poredjani
		checkBoxModifikatorMain.getStyle().set("background-color", "#ffffff");
		
		List<Article> listaArtikala2=List.of(new Article("1", "modifikator1", "opis1", 250.0, true),new Article("2", "modifikator2", "opis2", 350.0, true)); // test kao modifikator
		
		checkBoxModifikatorMain.setItems(listaArtikala2); 
		checkBoxModifikatorMain.setWidthFull();
		//checkBoxModifikatorMain.setHeight("250px");
		checkBoxModifikatorMain.setLabel("Moguci izbor za main");
		
		checkBoxModifikatorMain.setRenderer(new ComponentRenderer<>(article -> {  //za svaki modifikator se pravi
		    VerticalLayout container = new VerticalLayout(); 
		    container.setSpacing(false);
		    container.setPadding(false);      // jedno ispod drugog bez razmaka
		    Span articleTitle = new Span(article.getName());   // naziv za opis i izgled 
		    articleTitle.getStyle().set("font-weight", "bold");
		    articleTitle.getStyle().set("color", "#3F220F");

		    Span articlePriceSubText = new Span("+ "+String.valueOf(article.getBasePrice()+" RSD")); // ispod modifikator cena kao manji info
		    articlePriceSubText.getStyle().set("font-size", "var(--lumo-font-size-s)");              // manji font
		    articlePriceSubText.getStyle().set("color", "gray");									// boja

		    container.add(articleTitle, articlePriceSubText);
		    return container;                // dodaje u container i vraca pa ako ima jos pravi novi container
		}));
		
		CheckboxGroup<Article> checkBoxModifikatorSide = new CheckboxGroup<>();   
		checkBoxModifikatorSide.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);       //smer kako su poredjani
		checkBoxModifikatorSide.getStyle().set("background-color", "#ffffff");
		
		List<Article> sideModifierChoiceList=List.of(new Article("1", "modifikator1", "opis1", 250.0, true),new Article("2", "modifikator2", "opis2", 350.0, true)); // test kao modifikator
		
		checkBoxModifikatorSide.setItems(sideModifierChoiceList); 
		checkBoxModifikatorSide.setWidthFull();
		//checkBoxModifikatorSide.setHeight("250px");
		checkBoxModifikatorSide.setLabel("Moguci izbor za side");
		
		checkBoxModifikatorSide.setRenderer(new ComponentRenderer<>(article -> {  //za svaki modifikator se pravi
		    VerticalLayout container = new VerticalLayout(); 
		    container.setSpacing(false);
		    container.setPadding(false);      // jedno ispod drugog bez razmaka
		    Span articleTitle = new Span(article.getName());   // naziv za opis i izgled 
		    articleTitle.getStyle().set("font-weight", "bold");
		    articleTitle.getStyle().set("color", "#3F220F");

		    Span articlePriceSubText = new Span("+ "+String.valueOf(article.getBasePrice()+" RSD")); // ispod modifikator cena kao manji info
		    articlePriceSubText.getStyle().set("font-size", "var(--lumo-font-size-s)");              // manji font
		    articlePriceSubText.getStyle().set("color", "gray");									// boja

		    container.add(articleTitle, articlePriceSubText);
		    return container;                // dodaje u container i vraca pa ako ima jos pravi novi container
		}));
		
		CheckboxGroup<Article> checkBoxModifikatorDrink = new CheckboxGroup<>();   
		checkBoxModifikatorDrink.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);       //smer kako su poredjani
		checkBoxModifikatorDrink.getStyle().set("background-color", "#ffffff");
		
		List<Article> drinkModifierChoiceList=List.of(new Article("1", "modifikator1", "opis1", 250.0, true),new Article("2", "modifikator2", "opis2", 350.0, true)); // test kao modifikator
		
		checkBoxModifikatorDrink.setItems(drinkModifierChoiceList); 
		checkBoxModifikatorDrink.setWidthFull();
		checkBoxModifikatorDrink.setHeight("175px");
		checkBoxModifikatorDrink.setLabel("Moguci izbor za side");
		
		checkBoxModifikatorDrink.setRenderer(new ComponentRenderer<>(article -> {  //za svaki modifikator se pravi
		    VerticalLayout container = new VerticalLayout(); 
		    container.setSpacing(false);
		    container.setPadding(false);      // jedno ispod drugog bez razmaka
		    Span articleTitle = new Span(article.getName());   // naziv za opis i izgled 
		    articleTitle.getStyle().set("font-weight", "bold");
		    articleTitle.getStyle().set("color", "#3F220F");

		    Span articlePriceSubText = new Span("+ "+String.valueOf(article.getBasePrice()+" RSD")); // ispod modifikator cena kao manji info
		    articlePriceSubText.getStyle().set("font-size", "var(--lumo-font-size-s)");              // manji font
		    articlePriceSubText.getStyle().set("color", "gray");									// boja

		    container.add(articleTitle, articlePriceSubText);
		    return container;                // dodaje u container i vraca pa ako ima jos pravi novi container
		}));
		
		
		
		
		VerticalLayout comboChoiceContainer=new VerticalLayout();
		comboChoiceContainer.add(comboChoiceH4,radioComboMain,radioComboSide,radioComboDrink);		
		comboChoiceContainer.setSpacing(false);

		VerticalLayout comboModificationsContainer=new VerticalLayout();
		comboModificationsContainer.add(modificationChoice,checkBoxModifikatorMain,checkBoxModifikatorSide,checkBoxModifikatorDrink);
		comboModificationsContainer.setSpacing(false);
		
		HorizontalLayout rightSideContainer=new HorizontalLayout();
		
		rightSideContainer.add(comboChoiceContainer,comboModificationsContainer);
		
		rightSideContainer.setWidthFull();
		
		add(leftSideContainer,rightSideContainer);
		
	}

	private void updateButtonValue() { 		// menja se vrednost u buttonu cena * kolicina koju narucuje
        if (mainCombo != null) {
            double totalPrice = mainCombo.getBasePrice() * productCounter.getValue();
            addToCartButton.setText("Dodaj u korpu (" + totalPrice + ") RSD");
        }
    }
}
