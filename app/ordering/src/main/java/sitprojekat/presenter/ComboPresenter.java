package sitprojekat.presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.spring.annotation.UIScope;

import sitprojekat.interfaces.ComboViewInterface;
import sitprojekat.model.Article;
import sitprojekat.model.Combo;
import sitprojekat.model.Modifier;
import sitprojekat.model.ProductInCart;
import sitprojekat.model.ProductInCartCombo;
import sitprojekat.model.Type;
import sitprojekat.service.ComboService;
import sitprojekat.service.ProductInCartService;

@Component
@UIScope  // svaki tab/korisnik dobija svoj presenter
public class ComboPresenter {

	private ComboViewInterface view;
    private final ComboService service;
    private final ProductInCartService productInCartService;
    private Combo combo;

    
    public ComboPresenter(ComboService service,ProductInCartService productInCartService) {
        this.service = service;
        this.productInCartService=productInCartService;
    }
    
    public void findByID(String id) { // nalazi article i njegove modifiere
        this.combo=this.findByID1(id);
       
       if (combo != null) {
    	   view.setComboName(combo.getName());
    	   view.setComboDescription(combo.getDescription());
           
           H2 comboChoiceH2=new H2("Kombo sadrzi moguce izbore");
   		comboChoiceH2.addClassName("whiteText");
   		
   		H2 modifierChoice=new H2("Ukljuceni modifikatori");
   		modifierChoice.addClassName("whiteText");

   		
   		
   		
   		CheckboxGroup<Modifier> checkBoxModifierMain = view.createComboCheckBox("Moguci izbor za main",combo.getMain().get(0).getModifiers());
   		checkBoxModifierMain.addValueChangeListener(e->orderAmountChange(view.getProductCounter()));
   		view.setMainModifierCheckBoxGroup(checkBoxModifierMain);
   		
   		
   		CheckboxGroup<Modifier> checkBoxModifierSide = view.createComboCheckBox("Moguci izbor za side",combo.getSide().get(0).getModifiers());
   		checkBoxModifierSide.addValueChangeListener(e->orderAmountChange(view.getProductCounter()));
   		view.setSideModifierCheckBoxGroup(checkBoxModifierSide);
   		
   		
   		CheckboxGroup<Modifier> checkBoxModifierDrink = view.createComboCheckBox("Moguci izbor za drink",combo.getDrink().get(0).getModifiers());   
   		checkBoxModifierDrink.setHeight("175px");
   		checkBoxModifierDrink.addValueChangeListener(e->orderAmountChange(view.getProductCounter()));
   		view.setDrinkModifierCheckBoxGroup(checkBoxModifierDrink);
   		
   		
   		
   		RadioButtonGroup<Article> comboMainRadio = view.createComboRadioButtons("Moguci Izbor za main",combo.getMain());
   		
   		
   		comboMainRadio.addValueChangeListener(e -> { //stavlja modifiere od izabranog articla
   		   
   		        view.getMainModifierCheckBoxGroup().setItems(e.getValue().getModifiers());
   		        orderAmountChange(view.getProductCounter());
   		    
   		});
   		
   		view.setMainRadioButtonGroup(comboMainRadio);
   		view.getMainRadioButtonGroup().setValue(combo.getMain().get(0));
   		
   		RadioButtonGroup<Article> radioComboSide = view.createComboRadioButtons("Moguci izbor za side", combo.getSide());
   	
   		radioComboSide.addValueChangeListener(e -> { //stavlja modifiere od izabranog articla

   		        view.getSideModifierCheckBoxGroup().setItems(e.getValue().getModifiers());
   		        orderAmountChange(view.getProductCounter());
   		        
   		});
   		
   		
   		view.setSideRadioButtonGroup(radioComboSide);
   		view.getSideRadioButtonGroup().setValue(combo.getSide().get(0));
   		
   		RadioButtonGroup<Article> radioComboDrink = view.createComboRadioButtons("Moguci izbor za drink", combo.getDrink());
   		radioComboDrink.setHeight("175px");
   		
   		view.setDrinkRadioButtonGroup(radioComboDrink);
   		view.getDrinkRadioButtonGroup().setValue(combo.getDrink().get(0));
   		
   		radioComboDrink.addValueChangeListener(e -> { //stavlja modifiere od izabranog 
   		   
   		        view.getDrinkModifierCheckBoxGroup().setItems(e.getValue().getModifiers());
   		        orderAmountChange(view.getProductCounter());
   		    
   		});
   		
   		
   	    view.getComboChoiceContainer().add(comboChoiceH2, comboMainRadio, radioComboSide, radioComboDrink);

   	    view.getComboModificationsContainer().add(modifierChoice, checkBoxModifierMain, checkBoxModifierSide, checkBoxModifierDrink);
   		
           
   	    orderAmountChange(view.getProductCounter());    
           
           
           
           
       }
       else {                      
           view.setComboName("Placeholder ako nije ucitan");   // ako ga nema placeholderi
           view.setComboDescription("Placeholder ako nije ucitan");
           view.setPrice(0.0);
       }
    }
    public void orderAmountChange(Integer orderAmount) { // menja se vrednost u buttonu (cena proizvoda + cena modifiera) * kolicina koju narucuje 
    	
        if (combo != null && orderAmount != null) {
        	List<Article> articles=view.getSelectedArticles();
            Set<Modifier> toppingModifier=view.getSelectedModifiers();
            
            double articlesPrice=0;
            double toppingModifierPrice=0;
            
            if(articles != null) {
            	for (Article a: articles) {
            		articlesPrice+=a.getBasePrice();
            	}
            }
            
            if(toppingModifier != null) {
            
            	for (Modifier modifier : toppingModifier) {
            		toppingModifierPrice+=modifier.getPrice();
            	}
            }
        	double newPrice = (combo.getBasePrice()+articlesPrice+toppingModifierPrice) * orderAmount;
            view.setPrice(newPrice);
        }
    }
    public void addToCart() {  // dodaje se u korp proizvod sa modifierima
        if (combo != null) {
            int orderAmount = view.getProductCounter();
            List<Article> articles=view.getSelectedArticles();
            Set<Modifier> toppingModifier=view.getSelectedModifiers();
            
            double articlesPrice=0;
            double toppingModifierPrice=0;
            
            if(articles != null) {
            	for (Article a: articles) {
            		articlesPrice+=a.getBasePrice();
            	}
            }
            
            if(toppingModifier != null) {
                
            	for (Modifier modifier : toppingModifier) {
            		toppingModifierPrice+=modifier.getPrice();
            	}
            }
            
            double totalPrice = (combo.getBasePrice()+articlesPrice+toppingModifierPrice) * orderAmount;

            ProductInCart productInCart = new ProductInCartCombo(this.combo, orderAmount,totalPrice,view.getSelectedModifiers(),articles.get(0),articles.get(1),articles.get(2));
            productInCartService.addProduct(productInCart);

            view.AddToCartNotif(combo.getName() + " dodat u korpu");


        }
    }

	public Combo findByID1(String sentArticleID) { // test metoda
		Type type1=new Type("1", "size", false);
		Type type2=new Type("2", "topping", false);

		
		Modifier mod1=new Modifier("1", "name1", "desc1",15.0, false, type1);
		Modifier mod2=new Modifier("2", "name2", "desc2",20.0, false, type1);
		Modifier mod3=new Modifier("3", "name3", "desc3",20.0, false, type1);
		
		Modifier mod4=new Modifier("4", "name4", "desc4",20.0, false, type2);
		Modifier mod5=new Modifier("5", "name5", "desc5",20.0, false, type2);
		Modifier mod6=new Modifier("6", "name6", "desc6",20.0, false, type2);
		
		List<Modifier> modifierList=new ArrayList<Modifier>();
		modifierList.add(mod1);
		modifierList.add(mod2);
		modifierList.add(mod3);
		modifierList.add(mod4);
		modifierList.add(mod5);
		modifierList.add(mod6);
		
		List<Article> mainChoiceList;
	    List<Article> sideChoiceList;
	    List<Article> sideDrinkList;
	    
	    List<Modifier> mainModifiersChoice;
	    List<Modifier> sideModifierChoiceList;
	    List<Modifier> drinkModifierChoiceList;
		
	    List<Modifier> mainModifiersChoice2;
	    List<Modifier> sideModifierChoiceList2;
	    List<Modifier> drinkModifierChoiceList2;
		
	    H2 comboChoiceH2=new H2("Kombo sadrzi moguce izbore");
		comboChoiceH2.addClassName("whiteText");
		
		H2 modifierChoice=new H2("Ukljuceni modifikatori");
		modifierChoice.addClassName("whiteText");

		mainModifiersChoice=List.of(new Modifier("1", "main1", "desc1",15.0, false, type1),new Modifier("2", "main2", "desc2",20.0, false, type1)); // test kao modifikator
		mainModifiersChoice2=List.of(new Modifier("3", "main3", "desc1",15.0, false, type1),new Modifier("4", "main4", "desc2",20.0, false, type1)); // test kao modifikator
		
		CheckboxGroup<Modifier> checkBoxModifierMain = view.createComboCheckBox("Moguci izbor za main",mainModifiersChoice);
		checkBoxModifierMain.addValueChangeListener(e->orderAmountChange(view.getProductCounter()));
		view.setMainModifierCheckBoxGroup(checkBoxModifierMain);
		
		sideModifierChoiceList=List.of(new Modifier("5", "side1 ", "desc1",15.0, false, type1),new Modifier("6", "side2", "desc2",20.0, false, type1)); // test kao modifikator
		sideModifierChoiceList2=List.of(new Modifier("7", "side3 ", "desc1",15.0, false, type1),new Modifier("8", "side4", "desc2",20.0, false, type1)); // test kao modifikator
		
		CheckboxGroup<Modifier> checkBoxModifierSide = view.createComboCheckBox("Moguci izbor za side",sideModifierChoiceList);
		checkBoxModifierSide.addValueChangeListener(e->orderAmountChange(view.getProductCounter()));
		view.setSideModifierCheckBoxGroup(checkBoxModifierSide);
		
		drinkModifierChoiceList=List.of(new Modifier("9", "drink1", "desc1",15.0, false, type1),new Modifier("10", "drink2", "desc2",20.0, false, type1)); // test kao modifikator
		drinkModifierChoiceList2=List.of(new Modifier("11", "drink3", "desc1",15.0, false, type1),new Modifier("12", "drink4", "desc2",20.0, false, type1)); // test kao modifikator
		
		CheckboxGroup<Modifier> checkBoxModifierDrink = view.createComboCheckBox("Moguci izbor za drink",drinkModifierChoiceList);   
		checkBoxModifierDrink.setHeight("175px");
		checkBoxModifierDrink.addValueChangeListener(e->orderAmountChange(view.getProductCounter()));
		view.setDrinkModifierCheckBoxGroup(checkBoxModifierDrink);
		// kraj modifiera
		
		
		
		
		mainChoiceList=List.of(new Article("1", "Veliki", "opis1", "slika1", 250.0, true, null, mainModifiersChoice),new Article("2", "Srednji", "opis2", "slika1", 350.0, true, null, mainModifiersChoice2)); // test za velicine
		RadioButtonGroup<Article> comboMainRadio = view.createComboRadioButtons("Moguci Izbor za main",mainChoiceList);
		
		
		comboMainRadio.addValueChangeListener(e -> { //stavlja modifiere od izabranog articla
		   
		        view.getMainModifierCheckBoxGroup().setItems(e.getValue().getModifiers());
		        orderAmountChange(view.getProductCounter());
		    
		});
		
		view.setMainRadioButtonGroup(comboMainRadio);
		view.getMainRadioButtonGroup().setValue(mainChoiceList.get(0));
		
		sideChoiceList=List.of(new Article("1", "Veliki", "opis1", "slika1", 250.0, true, null, sideModifierChoiceList),new Article("2", "Srednji", "opis2", "slika1", 350.0, true, null, sideModifierChoiceList2)); // test za velicine
		RadioButtonGroup<Article> radioComboSide = view.createComboRadioButtons("Moguci izbor za side", sideChoiceList);
	
		radioComboSide.addValueChangeListener(e -> { //stavlja modifiere od izabranog articla

		        view.getSideModifierCheckBoxGroup().setItems(e.getValue().getModifiers());
		        orderAmountChange(view.getProductCounter());
		        
		});
		
		
		view.setSideRadioButtonGroup(radioComboSide);
		view.getSideRadioButtonGroup().setValue(sideChoiceList.get(0));
		
		sideDrinkList=List.of(new Article("1", "Veliki", "opis1", "slika1", 250.0, true, null, drinkModifierChoiceList),new Article("2", "Srednji", "opis2", "slika1", 350.0, true, null, drinkModifierChoiceList2)); // test za velicine
		RadioButtonGroup<Article> radioComboDrink = view.createComboRadioButtons("Moguci izbor za drink", sideDrinkList);
		radioComboDrink.setHeight("175px");
		
		view.setDrinkRadioButtonGroup(radioComboDrink);
		view.getDrinkRadioButtonGroup().setValue(sideDrinkList.get(0));
		
		radioComboDrink.addValueChangeListener(e -> { //stavlja modifiere od izabranog 
		   
		        view.getDrinkModifierCheckBoxGroup().setItems(e.getValue().getModifiers());
		        orderAmountChange(view.getProductCounter());
		    
		});
		
		
	    view.getComboChoiceContainer().add(comboChoiceH2, comboMainRadio, radioComboSide, radioComboDrink);

	    view.getComboModificationsContainer().add(modifierChoice, checkBoxModifierMain, checkBoxModifierSide, checkBoxModifierDrink);
		
	    
	    
		
		this.combo=new Combo("1","1", "1", "1", 32, false, null, sideChoiceList, sideDrinkList, sideDrinkList);
		
		view.setComboName(combo.getName());
		view.setComboDescription(combo.getDescription());

		
		orderAmountChange(view.getProductCounter());
		
		return combo;
		
		
		
	}
	

	public void setView(ComboViewInterface view) {
		this.view=view;
		
	}
	
}
	