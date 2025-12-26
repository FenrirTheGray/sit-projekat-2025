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

import sitprojekat.model.Article;
import sitprojekat.service.ArticleService;


@CssImport("./style/style.css")
@Route(value = "Article",layout = HeaderAndNavBar.class)
public class ArticleView  extends HorizontalLayout implements HasUrlParameter<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4405949928698782190L;
	private Icon shoppingCartIcon=VaadinIcon.CART_O.create();;
	private Article mainArticle ;
	private H2 articleName=new H2();
    private Span articleDescription = new Span();
    private Button addToCartButton = new Button();
    private IntegerField productCounter = new IntegerField();
    private ArticleService service=new ArticleService();
    
	@Override
	public void setParameter(BeforeEvent event, String sentArticleID) {
		
		this.mainArticle=service.findByID(sentArticleID);
		if (mainArticle != null) {
	        this.articleName.setText(mainArticle.getName());
	        this.articleDescription.setText(mainArticle.getDescription());
	        updateButtonValue();
	    }else {
			this.articleName.setText("Placeholder ako nije dobro ucitan");
			this.articleDescription.setText("Placeholder ako nije dobro ucitan");
			this.addToCartButton.setDisableOnClick(true);
			this.productCounter.setEnabled(false);
			this.addToCartButton.setText("Dodaj u korpu () RSD");
		}
	}
	
	
	public ArticleView() {
		
		Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
		Button buttonBack=new Button("Povratak",backArrowIcon);
		//dugmeNazad.getStyle().set("background-color", "#3F220F");
		//dugmeNazad.getStyle().set("color", "#ffffff");
		buttonBack.addClassName("dugmeNazad");
		
		//H2 articleName=new H2("Naziv Artikla");
		//articleName.setText(mainArticle.getName());
		articleName.getStyle().set("color", "#ffffff");
		
		
//		Span articleDescription=new Span("asasfffffffffffffffffffsaasasfffffffffffffffffffsaasasfffffffffffffffffffsaasasff"
//				+ "asasfffffffffffffffffffsaasasfffffffffffffffffffsaasasfffffffffffffffffffsaasasfffffffffffffffffffsa"
//				+ "fffffffffffffffffsaasasfffffffffffffffffffsaasasfffffffffffffffffffsaasasfffffffffffffffffffsa"
//				+ "asasfffffffffffffffffffsaasasfffffffffffffffffffsaasasfffffffffffffffffffsaasasfffffffffffffffffffsaas"
//				+ "asfffffffffffffffffffsaasasfffffffffffffffffffsaasasfffffffffffffffffffsaasasfffffffffffffffffffsaasasfffffffffffffffffffsa"
//				+ "asasfffffffffffffffffffsaasasfffffffffffffffffffsaasasfffffffffffffffffffsaasasfffffffffffffffffffsaasasfffffffffffffffffffsaasasfffffffffffffffffffsa");
		//Span articleDescription=new Span(mainArticle.getDescription());
		articleDescription.getStyle().set("color", "#ffffff");
		articleDescription.getStyle().set("overflow-wrap","break-word");
		articleDescription.setWidth("350px");
		
		Image articleImage=new Image("/images/image_burger.jpg","slika primer proizvoda");
		articleImage.setWidth("400px");
		articleImage.setHeight("300px");
		//Image articleImage=new Image();
		
		productCounter.setValue(1);
		productCounter.setStepButtonsVisible(true);
		productCounter.addClassName("productCounter");	
		addToCartButton.getStyle().set("color", "#ffffff");
		addToCartButton.getStyle().set("background-color", "#3F220F");
		addToCartButton.setWidth("100%");
		
		productCounter.addValueChangeListener(e->{updateButtonValue();});
		
		
		VerticalLayout articleNameAndDescriptionAndImageContainer=new VerticalLayout();
		
		articleNameAndDescriptionAndImageContainer.add(articleName,articleDescription,articleImage);
		articleNameAndDescriptionAndImageContainer.setAlignItems(Alignment.CENTER);
		
		
		HorizontalLayout counterAndAddtoCartContainer=new HorizontalLayout();
		counterAndAddtoCartContainer.add(productCounter,addToCartButton);
		
		VerticalLayout leftSideContainer=new VerticalLayout();
		leftSideContainer.add(buttonBack,articleNameAndDescriptionAndImageContainer,counterAndAddtoCartContainer);
		
		
		H2 sizeChoice=new H2("Odabir Velicine");
		sizeChoice.getStyle().set("color", "#ffffff");
		
//		RadioButtonGroup<String> radioArticleSize = new RadioButtonGroup<>();
//		radioArticleSize.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
//		radioArticleSize.setLabel("Odabir Velicine");
//		radioArticleSize.setItems("Veliki", "Srednji", "Mali");
//		radioArticleSize.getStyle().set("background-color", "#ffffff");             // vec definisani nacin 
//		radioArticleSize.setWidth("100%");
//		radioArticleSize.setHeight("250px");
		
		RadioButtonGroup<Article> radioArticleSize = new RadioButtonGroup<>();
		radioArticleSize.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);       //smer kako su poredjani
		radioArticleSize.getStyle().set("background-color", "#ffffff");
		
		List<Article> listaArtikala=List.of(new Article("1", "Veliki", "opis1", 250.0, true),new Article("2", "Srednji", "opis2", 350.0, true)); // test za velicine
		
		radioArticleSize.setItems(listaArtikala); 
		radioArticleSize.setWidth("100%");
		radioArticleSize.setHeight("250px");
																			// nacin da od svakog uzme listu njegovih podatka samo test za sad
		radioArticleSize.setRenderer(new ComponentRenderer<>(article -> {  //za svaki artikal se pravi
		    VerticalLayout container = new VerticalLayout(); 
		    container.setSpacing(false);
		    container.setPadding(false);      // jedno ispod drugog bez razmaka
		    Span articleTitle = new Span("Naziv Artikla");   // naziv articla i izgled 
		    articleTitle.getStyle().set("font-weight", "bold");
		    articleTitle.getStyle().set("color", "#3F220F");

		    Span articlePriceSubText = new Span("+ "+String.valueOf(article.getBasePrice()+" RSD")); // ispod articla cena kao manji info
		    articlePriceSubText.getStyle().set("font-size", "var(--lumo-font-size-s)");              // manji font
		    articlePriceSubText.getStyle().set("color", "gray");									// boja

		    container.add(articleTitle, articlePriceSubText);
		    return container;                // dodaje u container i vraca pa ako ima jos pravi novi container
		}));
		
		
		
		
		H2 modificationChoice=new H2("Odabir Prilog");
		modificationChoice.getStyle().set("color", "#ffffff");
//		RadioButtonGroup<String> radioModifikator = new RadioButtonGroup<>();
//		radioModifikator.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
//		radioModifikator.setItems("Modifikator1", "Modifikator2", "Modifikator3");          // vec definisani nacin
//		radioModifikator.getStyle().set("background-color", "#ffffff");//#20281f
//		radioModifikator.setWidth("100%");
//		radioModifikator.setHeight("250px");
		
		
		CheckboxGroup<Article> checkBoxModifikator = new CheckboxGroup<>();   
		checkBoxModifikator.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);       //smer kako su poredjani
		checkBoxModifikator.getStyle().set("background-color", "#ffffff");
		
		List<Article> listaArtikala2=List.of(new Article("1", "modifikator1", "opis1", 250.0, true),new Article("2", "modifikator2", "opis2", 350.0, true)); // test kao modifikator
		
		checkBoxModifikator.setItems(listaArtikala2); 
		checkBoxModifikator.setWidth("100%");
		checkBoxModifikator.setHeight("250px");
		
		checkBoxModifikator.setRenderer(new ComponentRenderer<>(article -> {  //za svaki modifikator se pravi
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
		
		
		
		
		
		VerticalLayout rightSideContainer=new VerticalLayout();
		
		
		rightSideContainer.add(sizeChoice,radioArticleSize,modificationChoice,checkBoxModifikator);
		
		
		
		add(leftSideContainer,rightSideContainer);
		
	}

	private void updateButtonValue() { 		// menja se vrednost u buttonu cena * kolicina koju narucuje
        if (mainArticle != null) {
            double totalPrice = mainArticle.getBasePrice() * productCounter.getValue();
            addToCartButton.setText("Dodaj u korpu (" + totalPrice + ") RSD");
            addToCartButton.setIcon(VaadinIcon.CART_O.create());
        }
    }
	

	
	
}
