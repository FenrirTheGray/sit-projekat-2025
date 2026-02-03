package sitprojekat.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import sitprojekat.interfaces.ComboViewInterface;
import sitprojekat.model.Article;
import sitprojekat.model.Combo;
import sitprojekat.model.Modifier;
import sitprojekat.presenter.ComboPresenter;
import sitprojekat.service.ComboService;

@CssImport("./style/style.css")
@Route(value = "Combo",layout = HeaderAndNavBar.class)
public class ComboView  extends HorizontalLayout implements HasUrlParameter<String>, ComboViewInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8994010294830377854L;

	private H2 comboName=new H2();
    private Span comboDescription = new Span();
	private Button addToCartButton = new Button();
    private IntegerField productCounter = new IntegerField();
    private final ComboPresenter presenter;
    
    Image articleImage=new Image("/images/image_burger.jpg","slika primer proizvoda");
    
    List<Article> mainChoiceList;
    List<Article> sideChoiceList;
    List<Article> sideDrinkList;
    
    List<Modifier> mainModifiersChoice;
    List<Modifier> sideModifierChoiceList;
    List<Modifier> drinkModifierChoiceList;
    
    private RadioButtonGroup<Article> mainRadioButtonGroup=new RadioButtonGroup<Article>();
    private CheckboxGroup<Modifier> mainModifierCheckBoxGroup=new CheckboxGroup<Modifier>();
    
    private RadioButtonGroup<Article> sideRadioButtonGroup=new RadioButtonGroup<Article>();
    private CheckboxGroup<Modifier> sideModifierCheckBoxGroup=new CheckboxGroup<Modifier>();
    
    private RadioButtonGroup<Article> drinkRadioButtonGroup=new RadioButtonGroup<Article>();
    private CheckboxGroup<Modifier> drinkModifierCheckBoxGroup=new CheckboxGroup<Modifier>();
    
    VerticalLayout comboChoiceContainer=new VerticalLayout();
	VerticalLayout comboModificationsContainer=new VerticalLayout();
	
	@Override
	public void setParameter(BeforeEvent event, String sentComboID) {
		presenter.findByID1(sentComboID);
	}
	public ComboView(ComboPresenter presenter) {
		this.presenter=presenter;
		this.presenter.setView(this);
		
		Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
		Button backButton=new Button("Povratak",backArrowIcon);
		backButton.addClassName("brownButton");
		comboName.addClassName("whiteText");
		
		comboDescription.addClassName("DescriptionSpan");
				
		articleImage.addClassName("articleImage");

		productCounter.addClassName("productCounter");
		productCounter.setValue(1);
		productCounter.setStepButtonsVisible(true);
		
		
		addToCartButton.addClassName("brownButton");
		addToCartButton.addClickListener(e->presenter.addToCart());
		
		productCounter.addValueChangeListener(e->presenter.orderAmountChange(productCounter.getValue()));
		
		
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
		
		
		
		

			
		comboChoiceContainer.setSpacing(false);


		comboModificationsContainer.setSpacing(false);
		
		HorizontalLayout rightSideContainer=new HorizontalLayout();
		
		rightSideContainer.add(comboChoiceContainer,comboModificationsContainer);
		
		rightSideContainer.setWidthFull();
		
		add(leftSideContainer,rightSideContainer);
		
	}


	public RadioButtonGroup<Article> createComboRadioButtons(String label,List<Article> articles) {
	    RadioButtonGroup<Article> comboRadioButtonGroup = new RadioButtonGroup<>();
	    comboRadioButtonGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL); //smer kako su poredjani
	    comboRadioButtonGroup.addClassName("choiceBackground");
	    comboRadioButtonGroup.setLabel(label);
	    comboRadioButtonGroup.setItems(articles);
	    comboRadioButtonGroup.setWidthFull();
	    comboRadioButtonGroup.setRenderer(createArticleRenderer());
	     return comboRadioButtonGroup;
	}

	public CheckboxGroup<Modifier> createComboCheckBox(String label,List<Modifier> mainModifiersChoice2) {
	    CheckboxGroup<Modifier> comboCheckboxGroup = new CheckboxGroup<>();
	    comboCheckboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL); //smer kako su poredjani
	    comboCheckboxGroup.addClassName("choiceBackground");
	    comboCheckboxGroup.setLabel(label);
	    comboCheckboxGroup.setItems(mainModifiersChoice2);
	    comboCheckboxGroup.setWidthFull();
	    comboCheckboxGroup.setRenderer(createModifierRenderer());
	    return comboCheckboxGroup;
	}
	public ComponentRenderer<VerticalLayout, Article> createArticleRenderer() {
	    return new ComponentRenderer<>(article ->  {  //za svaki modifikator se pravi
		    VerticalLayout container = new VerticalLayout(); 
		    container.setSpacing(false);
		    container.setPadding(false);      // jedno ispod drugog bez razmaka
		    Span articleTitleSpan = new Span(article.getName());   // naziv za opis i izgled 
		    articleTitleSpan.addClassName("choiceButton");

		    Span articlePriceSubTextSpan = new Span("opcija "+article.getDescription()); // ispod modifikator cena kao manji info
		    articlePriceSubTextSpan.addClassName("choiceButtonSubText");								// boja

		    container.add(articleTitleSpan, articlePriceSubTextSpan);
		    return container;                // dodaje u container i vraca pa ako ima jos pravi novi container
	    });
	}

	public ComponentRenderer<VerticalLayout, Modifier> createModifierRenderer() {
	    return new ComponentRenderer<>(modifier ->  {  //za svaki modifikator se pravi
		    VerticalLayout container = new VerticalLayout(); 
		    container.setSpacing(false);
		    container.setPadding(false);      // jedno ispod drugog bez razmaka
		    Span articleTitleSpan = new Span(modifier.getName());   // naziv za opis i izgled 
		    articleTitleSpan.addClassName("choiceButton");

		    Span articlePriceSubTextSpan = new Span("+ "+String.valueOf(modifier.getPrice()+" RSD")); // ispod modifikator cena kao manji info
		    articlePriceSubTextSpan.addClassName("choiceButtonSubText");								// boja

		    container.add(articleTitleSpan, articlePriceSubTextSpan);
		    return container;                // dodaje u container i vraca pa ako ima jos pravi novi container
	    });
	}
	@Override
	public void setComboName(String string) {
		this.comboName.setText(string);
		
	}
	@Override
	public void setComboDescription(String string) {
		this.comboDescription.setText(string);
		
	}
	@Override
	public Integer getProductCounter() {
		return this.productCounter.getValue();
	}
	
	public RadioButtonGroup<Article> getMainRadioButtonGroup() {
		return mainRadioButtonGroup;
	}
	@Override
	public void setMainRadioButtonGroup(RadioButtonGroup<Article> mainRadioButtonGroup) {
		this.mainRadioButtonGroup=mainRadioButtonGroup;
		
	}
	@Override
	public CheckboxGroup<Modifier> getMainModifierCheckBoxGroup() {
		return mainModifierCheckBoxGroup;
	}
	@Override
	public void setMainModifierCheckBoxGroup(CheckboxGroup<Modifier> mainModifierCheckBoxGroup) {
		this.mainModifierCheckBoxGroup=mainModifierCheckBoxGroup;
		
	}
	@Override
	public RadioButtonGroup<Article> getSideRadioButtonGroup() {
		return sideRadioButtonGroup;
	}
	@Override
	public void setSideRadioButtonGroup(RadioButtonGroup<Article> sideRadioButtonGroup) {
		this.sideRadioButtonGroup=sideRadioButtonGroup;
		
	}
	@Override
	public CheckboxGroup<Modifier> getSideModifierCheckBoxGroup() {
		return sideModifierCheckBoxGroup;
	}
	@Override
	public void setSideModifierCheckBoxGroup(CheckboxGroup<Modifier> sideModifierCheckBoxGroup) {
		this.sideModifierCheckBoxGroup=sideModifierCheckBoxGroup;
		
	}
	@Override
	public RadioButtonGroup<Article> getDrinkRadioButtonGroup() {
		return drinkRadioButtonGroup;
	}
	@Override
	public void setDrinkRadioButtonGroup(RadioButtonGroup<Article> drinkRadioButtonGroup) {
		this.drinkRadioButtonGroup=drinkRadioButtonGroup;
		
	}
	@Override
	public CheckboxGroup<Modifier> getDrinkModifierCheckBoxGroup() {
		return drinkModifierCheckBoxGroup;
	}
	@Override
	public void setDrinkModifierCheckBoxGroup(CheckboxGroup<Modifier> drinkModifierCheckBoxGroup) {
		this.drinkModifierCheckBoxGroup=drinkModifierCheckBoxGroup;
		
	}
	@Override
	public VerticalLayout getComboChoiceContainer() {
		return comboChoiceContainer;
	}
	@Override
	public void setComboChoiceContainer(VerticalLayout comboChoiceContainer) {
		this.comboChoiceContainer=comboChoiceContainer;
		
	}
	@Override
	public VerticalLayout getComboModificationsContainer() {
		return comboModificationsContainer;
	}
	@Override
	public void setComboModificationsContainer(VerticalLayout comboModificationsContainer) {
		this.comboModificationsContainer=comboModificationsContainer;
		
	}
	@Override
	public void setPrice(double basePrice) {
		this.addToCartButton.setText("Dodaj u korpu (" + basePrice + ") RSD");
		
	}
	@Override
	public void AddToCartNotif(String string) {
		Notification notification = Notification.show(string, 3000, Notification.Position.BOTTOM_START); // sta pise , koliko traje, pozicija
	    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);// koje je boje
		
	}
	@Override
	public List<Article> getSelectedArticles() {
		List<Article> articles=new ArrayList<Article>();
		
		 if (mainRadioButtonGroup.getValue() != null) {
			 articles.add(mainRadioButtonGroup.getValue());
		    }
		 if (sideRadioButtonGroup.getValue() != null) {
			 articles.add(sideRadioButtonGroup.getValue());
		    }
		 if (drinkRadioButtonGroup.getValue() != null) {
			 articles.add(drinkRadioButtonGroup.getValue());
		    }
		return articles;
	}
	@Override
	public Set<Modifier> getSelectedModifiers() {
		Set<Modifier> modifiers=new HashSet<Modifier>();
		

		modifiers.addAll(mainModifierCheckBoxGroup.getSelectedItems());
		modifiers.addAll(sideModifierCheckBoxGroup.getSelectedItems());
		modifiers.addAll(drinkModifierCheckBoxGroup.getSelectedItems());

		return modifiers;
	}
	@Override
	public Article getMainArticle() {
		Article articleMain=mainRadioButtonGroup.getValue();
//		 if (mainRadioButtonGroup.getValue() != null) {
//			 articles.add(mainRadioButtonGroup.getValue());
//		    }
		return articleMain;
	}
	@Override
	public Set<Modifier> getMainModifiers(){
		return mainModifierCheckBoxGroup.getSelectedItems();
	}
	@Override
	public Article getSideArticle() {
		Article articleSide=sideRadioButtonGroup.getValue();
//		 if (mainRadioButtonGroup.getValue() != null) {
//			 articles.add(mainRadioButtonGroup.getValue());
//		    }
		return articleSide;
	}
	@Override
	public Set<Modifier> getSideModifiers(){
		return sideModifierCheckBoxGroup.getSelectedItems();
	}
	@Override
	public Article getDrinkArticle() {
		Article articleDrink=drinkRadioButtonGroup.getValue();
//		 if (mainRadioButtonGroup.getValue() != null) {
//			 articles.add(mainRadioButtonGroup.getValue());
//		    }
		return articleDrink;
	}
	@Override
	public Set<Modifier> getDrinkModifiers(){
		return drinkModifierCheckBoxGroup.getSelectedItems();
	}
}
