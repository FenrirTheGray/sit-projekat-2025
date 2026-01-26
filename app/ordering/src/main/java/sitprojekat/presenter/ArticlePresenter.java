package sitprojekat.presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.vaadin.flow.spring.annotation.UIScope;

import sitprojekat.interfajsi.ArticleViewInterface;
import sitprojekat.model.Article;
import sitprojekat.model.Category;
import sitprojekat.model.Modifier;
import sitprojekat.model.ProductInCart;
import sitprojekat.model.Type;
import sitprojekat.service.ArticleService;
import sitprojekat.service.ProductInCartService;
import sitprojekat.view.ArticleView;

@Component
@UIScope  // svaki tab/korisnik dobija svoj presenter
public class ArticlePresenter {

	private ArticleViewInterface view;
    private final ArticleService service;
    private final ProductInCartService productInCartService;
    private Article article;
    
    public ArticlePresenter(ArticleService service,ProductInCartService productInCartService) {
        this.service = service;
        this.productInCartService=productInCartService;
    }
    
    public void findByID(String id) { // nalazi article i njegove modifiere
        this.article=service.findByID(id);
       
       if (article != null) {
    	   view.setArticleName(article.getName());
    	   view.setArticleDescription(article.getDescription());
           view.setPrice(article.getBasePrice());
           if (article.getModifiers() != null) { // ako ima mod
   			List<Modifier> articleSizesList = article.getModifiers().stream().filter(m -> m.getType().getName().equals("size")).toList(); // filter  size
   	            
   			List<Modifier> articleModifiersList = article.getModifiers().stream().filter(m -> m.getType().getName().equals("topping")).toList(); // filter topping

   			view.setArticleSizes(articleSizesList);
   			view.setArticleModifiers(articleModifiersList);
   	    }
       }
       else {                      
           view.setArticleName("Placeholder ako nije ucitan");   // ako ga nema placeholderi
           view.setArticleDescription("Placeholder ako nije ucitan");
           view.setPrice(0.0);
       }
    }
    public void orderAmountChange(Integer orderAmount) { // menja se vrednost u buttonu (cena proizvoda + cena modifiera) * kolicina koju narucuje 
    	
        if (article != null && orderAmount != null) {
        	Modifier sizeModifier=view.getArticleSizesRadioButton();
            Set<Modifier> toppingModifier=view.getArticleModifiersCheckBox();
            
            double sizeModifierPrice=0;
            double toppingModifierPrice=0;
            
            if(sizeModifier != null) {
            	sizeModifierPrice=sizeModifier.getPrice();
            }
            
            if(toppingModifier != null) {
            
            	for (Modifier modifier : toppingModifier) {
            		toppingModifierPrice+=modifier.getPrice();
            	}
            }
        	double newPrice = (article.getBasePrice()+sizeModifierPrice+toppingModifierPrice) * orderAmount;
            view.setPrice(newPrice);
        }
    }
    public void addToCart() {  // dodaje se u korp proizvod sa modifierima
        if (article != null) {
            int orderAmount = view.getOrderAmount();
            Modifier sizeModifier=view.getArticleSizesRadioButton();
            Set<Modifier> toppingModifier=view.getArticleModifiersCheckBox();
            double sizeModifierPrice=0;
            double toppingModifierPrice=0;
            
            if(sizeModifier != null) {
            	sizeModifierPrice=sizeModifier.getPrice();
            }
            
            if(toppingModifier != null) {
            
            	for (Modifier modifier : toppingModifier) {
            		toppingModifierPrice+=modifier.getPrice();
            	}
            }
            
            double totalPrice = (article.getBasePrice()+sizeModifierPrice+toppingModifierPrice) * orderAmount;

            ProductInCart productInCart = new ProductInCart(this.article, orderAmount,totalPrice,view.getArticleSizesRadioButton(),view.getArticleModifiersCheckBox());
            productInCartService.addProduct(productInCart);

            view.AddToCartNotif(article.getName() + " dodat u korpu");


        }
    }

	public Article findByID1(String sentArticleID) { // test metoda
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
		
		this.article= new Article("test1", "test1", "test1", 23, false,new Category("1","n1", "d1", false),modifierList);
		view.setArticleName(article.getName());
		view.setArticleDescription(article.getDescription());
		view.setPrice(article.getBasePrice());
		
		if (article.getModifiers() != null) {
			List<Modifier> articleSizesList = article.getModifiers().stream().filter(m -> m.getType().getName().equals("size")).toList();
	            
			List<Modifier> articleModifiersList = article.getModifiers().stream().filter(m -> m.getType().getName().equals("topping")).toList();

			view.setArticleSizes(articleSizesList);
			view.setArticleModifiers(articleModifiersList);
	    }
	
		return article;
		
		
		
	}

	public void setView(ArticleView view) {
		this.view=view;
		
	}
}
	