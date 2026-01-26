package sitprojekat.view;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
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

import sitprojekat.interfajsi.ComboViewInterface;
import sitprojekat.model.Article;
import sitprojekat.presenter.ComboPresenter;
import sitprojekat.service.ArticleService;

@CssImport("./style/style.css")
@Route(value = "Combo",layout = HeaderAndNavBar.class)
public class ComboView  extends HorizontalLayout implements HasUrlParameter<String>, ComboViewInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8994010294830377854L;

	private Article mainCombo ;
	private H2 comboName=new H2();
    private Span comboDescription = new Span();
    private Button addToCartButton = new Button();
    private IntegerField productCounter = new IntegerField();
    private final ArticleService service=new ArticleService();
    private final ComboPresenter presenter;
	@Override
	public void setParameter(BeforeEvent event, String sentComboID) {
		
		this.mainCombo=service.findByID(sentComboID);
		if (mainCombo != null) {
	        this.comboName.setText(mainCombo.getName());
	        this.comboDescription.setText(mainCombo.getDescription());
	        updateButtonValue();
	        productCounter.setMin(1);
	    }else {
			this.comboName.setText("Placeholder ako nije dobro ucitan");
			this.comboDescription.setText("Placeholder ako nije dobro ucitan");
			this.addToCartButton.setEnabled(false);
			this.productCounter.setEnabled(false);
			this.addToCartButton.setText("Dodaj u korpu () RSD");
			this.addToCartButton.setIcon(VaadinIcon.CART_O.create());
		}
	}
	public ComboView(ComboPresenter presenter) {
		this.presenter=presenter;
		this.presenter.setView(this);
		
		Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
		Button backButton=new Button("Povratak",backArrowIcon);
		backButton.addClassName("brownButton");
		comboName.addClassName("whiteText");
		
		
		comboDescription.addClassName("DescriptionSpan");
		
		Image articleImage=new Image("/images/image_burger.jpg","slika primer proizvoda");
		articleImage.addClassName("articleImage");
		//Image articleImage=new Image();
		
		productCounter.addClassName("productCounter");
		productCounter.setValue(1);
		productCounter.setStepButtonsVisible(true);
		
		
		addToCartButton.addClassName("brownButton");
		
		productCounter.addValueChangeListener(e->{updateButtonValue();});
		
		
		VerticalLayout comboNameAndDescriptionAndImageContainer=new VerticalLayout();
		
		comboNameAndDescriptionAndImageContainer.add(comboName,comboDescription,articleImage);
		comboNameAndDescriptionAndImageContainer.setAlignItems(Alignment.CENTER);
		
		
		HorizontalLayout counterAndAddtoCartContainer=new HorizontalLayout();
		counterAndAddtoCartContainer.add(productCounter,addToCartButton);
		
		VerticalLayout leftSideContainer=new VerticalLayout();
		leftSideContainer.add(backButton,comboNameAndDescriptionAndImageContainer,counterAndAddtoCartContainer);
		leftSideContainer.setWidth("75%");
		
		H2 comboChoiceH2=new H2("Kombo sadrzi moguce izbore");
		comboChoiceH2.addClassName("whiteText");
		
		
		//List<Article> mainChoiceList=List.of(new Article("1", "Veliki", "opis1", 250.0, true),new Article("2", "Srednji", "opis2", 350.0, true)); // test za velicine
		List<Article> mainChoiceList=List.of(new Article("1", "Veliki", "opis1", 250.0, true, null, null),new Article("2", "Srednji", "opis2", 350.0, true, null, null)); // test za velicine
		RadioButtonGroup<Article> comboMainRadio = createComboRadioButtons("Moguci Izbor za main",mainChoiceList);
		
		List<Article> sideChoiceList=List.of(new Article("1", "Veliki", "opis1", 250.0, true, null, null),new Article("2", "Srednji", "opis2", 350.0, true, null, null)); // test za velicine
		RadioButtonGroup<Article> radioComboSide = createComboRadioButtons("Moguci izbor za side", sideChoiceList);
	
		List<Article> sideDrinkList=List.of(new Article("1", "Veliki", "opis1", 250.0, true, null, null),new Article("2", "Srednji", "opis2", 350.0, true, null, null)); // test za velicine
		RadioButtonGroup<Article> radioComboDrink = createComboRadioButtons("Moguci izbor za drink", sideDrinkList);
		radioComboDrink.setHeight("175px");
		
		H2 modificationChoice=new H2("Ukljuceni modifikatori");
		modificationChoice.addClassName("whiteText");

		List<Article> mainModifiersChoice=List.of(new Article("1", "modifikator1", "opis1", 250.0, true, null, null),new Article("2", "modifikator2", "opis2", 350.0, true, null, null)); // test kao modifikator
		CheckboxGroup<Article> checkBoxModifierMain = createComboCheckBox("Moguci izbor za main",mainModifiersChoice);
		
		List<Article> sideModifierChoiceList=List.of(new Article("1", "modifikator1", "opis1", 250.0, true, null, null),new Article("2", "modifikator2", "opis2", 350.0, true, null, null)); // test kao modifikator
		CheckboxGroup<Article> checkBoxModifierSide = createComboCheckBox("Moguci izbor za side",sideModifierChoiceList);
		
		List<Article> drinkModifierChoiceList=List.of(new Article("1", "modifikator1", "opis1", 250.0, true, null, null),new Article("2", "modifikator2", "opis2", 350.0, true, null, null)); // test kao modifikator
		CheckboxGroup<Article> checkBoxModifierDrink = createComboCheckBox("Moguci izbor za drink",drinkModifierChoiceList);   
		checkBoxModifierDrink.setHeight("175px");
			
		VerticalLayout comboChoiceContainer=new VerticalLayout();
		comboChoiceContainer.add(comboChoiceH2,comboMainRadio,radioComboSide,radioComboDrink);		
		comboChoiceContainer.setSpacing(false);

		VerticalLayout comboModificationsContainer=new VerticalLayout();
		comboModificationsContainer.add(modificationChoice,checkBoxModifierMain,checkBoxModifierSide,checkBoxModifierDrink);
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
	private RadioButtonGroup<Article> createComboRadioButtons(String label,List<Article> articles) {
	    RadioButtonGroup<Article> comboRadioButtonGroup = new RadioButtonGroup<>();
	    comboRadioButtonGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL); //smer kako su poredjani
	    comboRadioButtonGroup.addClassName("choiceBackground");
	    comboRadioButtonGroup.setLabel(label);
	    comboRadioButtonGroup.setItems(articles);
	    comboRadioButtonGroup.setWidthFull();
	    comboRadioButtonGroup.setRenderer(createArticleRenderer());
	    return comboRadioButtonGroup;
	}

	private CheckboxGroup<Article> createComboCheckBox(String label,List<Article> articles) {
	    CheckboxGroup<Article> comboCheckboxGroup = new CheckboxGroup<>();
	    comboCheckboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL); //smer kako su poredjani
	    comboCheckboxGroup.addClassName("choiceBackground");
	    comboCheckboxGroup.setLabel(label);
	    comboCheckboxGroup.setItems(articles);
	    comboCheckboxGroup.setWidthFull();
	    comboCheckboxGroup.setRenderer(createArticleRenderer());
	    return comboCheckboxGroup;
	}

	private ComponentRenderer<VerticalLayout, Article> createArticleRenderer() {
	    return new ComponentRenderer<>(article ->  {  //za svaki modifikator se pravi
		    VerticalLayout container = new VerticalLayout(); 
		    container.setSpacing(false);
		    container.setPadding(false);      // jedno ispod drugog bez razmaka
		    Span articleTitleSpan = new Span(article.getName());   // naziv za opis i izgled 
		    articleTitleSpan.addClassName("choiceButton");

		    Span articlePriceSubTextSpan = new Span("+ "+String.valueOf(article.getBasePrice()+" RSD")); // ispod modifikator cena kao manji info
		    articlePriceSubTextSpan.addClassName("choiceButtonSubText");								// boja

		    container.add(articleTitleSpan, articlePriceSubTextSpan);
		    return container;                // dodaje u container i vraca pa ako ima jos pravi novi container
	    });
	}
}
