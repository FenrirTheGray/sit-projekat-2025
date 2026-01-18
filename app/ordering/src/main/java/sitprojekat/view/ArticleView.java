package sitprojekat.view;

import java.util.ArrayList;
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

import sitprojekat.interfajsi.ArticleViewInterface;
import sitprojekat.model.Modifier;
import sitprojekat.presenter.ArticlePresenter;
import sitprojekat.service.ArticleService;
import sitprojekat.service.ProductInCartService;


@CssImport("./style/style.css")
@Route(value = "Article",layout = HeaderAndNavBar.class)
public class ArticleView  extends HorizontalLayout implements HasUrlParameter<String> ,ArticleViewInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4405949928698782190L;
	private H2 articleName=new H2();
    private Span articleDescription = new Span();
    private Button addToCartButton = new Button();
    private IntegerField productCounter = new IntegerField();
    private final ArticlePresenter presenter;
    private RadioButtonGroup<Modifier> articleSizeRadioButtonGroup;
    private CheckboxGroup<Modifier> articleModifierCheckBoxGroup;
    
	@Override
	public void setParameter(BeforeEvent event, String sentArticleID) {
		presenter.findByID(sentArticleID);
	}
	
	
	public ArticleView(ArticleService articleService,ProductInCartService cartService) {
		
		this.presenter=new ArticlePresenter(this, articleService,cartService);
		
		Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
		Button backButton=new Button("Povratak",backArrowIcon);
		backButton.addClassName("brownButton");
		
		articleName.addClassName("whiteText");
		

		//Span articleDescription=new Span(mainArticle.getDescription());
		articleDescription.addClassName("DescriptionSpan");
		
		Image articleImage=new Image("/images/image_burger.jpg","slika primer proizvoda");
		articleImage.addClassName("articleImage");

		
		productCounter.addClassName("productCounter");
		productCounter.setValue(1);
		productCounter.setStepButtonsVisible(true);
		
		
		addToCartButton.addClassName("brownButton");
		
		addToCartButton.addClickListener(e->{   // mora biti izabran size i kolicina veca od 0
			if(productCounter.getValue()>0) {
				presenter.addToCart();
		}
		});
		
		this.articleSizeRadioButtonGroup = createRadioButtonGroupForSize(new ArrayList<>());
        this.articleModifierCheckBoxGroup = createCheckboxGroupForModifiers(new ArrayList<>());
		
		productCounter.addValueChangeListener(e->presenter.orderAmountChange(e.getValue())); // menja se cena buttona sa kolicinom
		
		
		VerticalLayout articleNameAndDescriptionAndImageContainer=new VerticalLayout();
		
		articleNameAndDescriptionAndImageContainer.add(articleName,articleDescription,articleImage);
		articleNameAndDescriptionAndImageContainer.setAlignItems(Alignment.CENTER);
		
		
		HorizontalLayout counterAndAddtoCartContainer=new HorizontalLayout();
		counterAndAddtoCartContainer.add(productCounter,addToCartButton);
		
		VerticalLayout leftSideContainer=new VerticalLayout();
		leftSideContainer.add(backButton,articleNameAndDescriptionAndImageContainer,counterAndAddtoCartContainer);
		
		
		H2 sizeChoiceH2=new H2("Odabir Velicine");
		sizeChoiceH2.addClassName("whiteText");
		
		
		H2 modificationChoiceH2=new H2("Odabir Modifikator");
		modificationChoiceH2.addClassName("whiteText");
			
		
		VerticalLayout rightSideContainer=new VerticalLayout();	
		
		articleSizeRadioButtonGroup.addValueChangeListener(e->{ // kad se menja izabrani menja cenu
			presenter.orderAmountChange(getOrderAmount());
		});
		articleModifierCheckBoxGroup.addValueChangeListener(e->{ //  kad se menja izabrani menja cenu
			presenter.orderAmountChange(getOrderAmount());
		});
		
		
		rightSideContainer.add(sizeChoiceH2,articleSizeRadioButtonGroup,modificationChoiceH2,articleModifierCheckBoxGroup);
		
		add(leftSideContainer,rightSideContainer);
		
	}

	private RadioButtonGroup<Modifier> createRadioButtonGroupForSize(List<Modifier> modifierList){
		
		RadioButtonGroup<Modifier> articleSizeRadio = new RadioButtonGroup<>();
		articleSizeRadio.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);       //smer kako su poredjani
		articleSizeRadio.addClassName("choiceContainer");
				
		articleSizeRadio.setItems(modifierList); 
																			// nacin da od svakog uzme listu njegovih podatka samo test za sad
		articleSizeRadio.setRenderer(new ComponentRenderer<>(modifier -> {  //za svaki artikal se pravi
		    VerticalLayout container = new VerticalLayout(); 
		    container.setSpacing(false);
		    container.setPadding(false);      // jedno ispod drugog bez razmaka
		    Span articleTitleSpan = new Span(modifier.getName());   // naziv articla i izgled 
		    articleTitleSpan.addClassName("choiceButton");

		    Span articlePriceSubTextSpan = new Span("+ "+String.valueOf(modifier.getPrice()+" RSD")); // ispod articla cena kao manji info
		    articlePriceSubTextSpan.addClassName("choiceButtonSubText");

		    container.add(articleTitleSpan, articlePriceSubTextSpan);
		    return container;                // dodaje u container i vraca pa ako ima jos pravi novi container
		}));
		
		return articleSizeRadio;
		
	}
	private CheckboxGroup<Modifier> createCheckboxGroupForModifiers(List<Modifier> articlesList){
		
		CheckboxGroup<Modifier> modifierCheckBox = new CheckboxGroup<>();   
		modifierCheckBox.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);       //smer kako su poredjani
		modifierCheckBox.addClassName("choiceContainer");
	
		modifierCheckBox.setItems(articlesList); 

	
		modifierCheckBox.setRenderer(new ComponentRenderer<>(modifier -> {  //za svaki modifikator se pravi
			VerticalLayout container = new VerticalLayout(); 
			container.setSpacing(false);
			container.setPadding(false);      // jedno ispod drugog bez razmaka
			Span articleTitleSpan = new Span(modifier.getName());   // naziv za opis i izgled 
			 articleTitleSpan.addClassName("choiceButton");

			Span articlePriceSubTextSpan = new Span("+ "+String.valueOf(modifier.getPrice()+" RSD")); // ispod modifikator cena kao manji info
			articlePriceSubTextSpan.addClassName("choiceButtonSubText");
			container.add(articleTitleSpan, articlePriceSubTextSpan);
			return container;                // dodaje u container i vraca pa ako ima jos pravi novi container
		}));
		return modifierCheckBox;
		
		}

	@Override
	public void setArticleName(String name) { // stavlja naziv
		
		this.articleName.setText(name);
		
	}

	@Override
	public void setArticleDescription(String description) { // stavlja description
		
		this.articleDescription.setText(description);
		
	}

	@Override
	public void setPrice(double price) { // stavlja cenu zavisi od izabranog modifiera siza i kolicine

		this.addToCartButton.setText("Dodaj u korpu (" + price + ") RSD");
		
	}

	@Override
	public int getOrderAmount() {
		return productCounter.getValue();
	}


	@Override
	public void AddToCartNotif(String string) {    // notif da je dodat proizvod
		Notification notification = Notification.show(string, 3000, Notification.Position.BOTTOM_START); // sta pise , koliko traje, pozicija
	    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);// koje je boje
		
	}


	@Override
	public void setArticleSizes(List<Modifier> articleSizes) {  // stavlja size od tog articla
		articleSizeRadioButtonGroup.setItems(articleSizes);
	
	}


	@Override
	public void setArticleModifiers(List<Modifier> articleModifiers) { // stavlja modifiere od tog articla
		articleModifierCheckBoxGroup.setItems(articleModifiers);
		
	}


	public RadioButtonGroup<Modifier> getArticleSizeRadioButtonGroup() {
		return articleSizeRadioButtonGroup;
	}


	public void setArticleSizeRadioButtonGroup(RadioButtonGroup<Modifier> articleSizeRadioButtonGroup) {
		this.articleSizeRadioButtonGroup = articleSizeRadioButtonGroup;
	}


	public CheckboxGroup<Modifier> getArticleModifierCheckBoxGroup() {
		return articleModifierCheckBoxGroup;
	}


	public void setArticleModifierCheckBoxGroup(CheckboxGroup<Modifier> articleModifierCheckBoxGroup) {
		this.articleModifierCheckBoxGroup = articleModifierCheckBoxGroup;
	}


	@Override
	public double getArticleSizesRadioButton() {   // vraca cenu izabranog siza ako je neki izabran
	    if (this.articleSizeRadioButtonGroup.getValue() == null) {
	        return 0;
	    }
	    return this.articleSizeRadioButtonGroup.getValue().getPrice();
		
	}


	@Override
	public double getArticleModifiersCheckBox() {
		Set<Modifier> selectedItems=this.articleModifierCheckBoxGroup.getSelectedItems(); // vraca cenu za izabrane modifiere ako su izabrani
		double price=0;
		
		for (Modifier modifier : selectedItems) {
			price+=modifier.getPrice();
		}
		
		
		return price;
	}




	
	
}
