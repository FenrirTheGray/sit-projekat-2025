package sitprojekat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.vaadin.flow.spring.annotation.VaadinSessionScope;

import sitprojekat.model.Article;
import sitprojekat.model.Combo;
import sitprojekat.model.ProductInCart;
import sitprojekat.model.ProductInCartArticle;
import sitprojekat.model.ProductInCartCombo;

@Service
@VaadinSessionScope // traje samo za tu sesiju
public class ProductInCartService {
	
	private List<ProductInCart> productsInCart=new ArrayList<>(); 

	public ProductInCartService() {
    }
	
	public ProductInCartService(List<ProductInCart> products) {
		this.productsInCart = products;
	}


	public List<ProductInCart> getProducts() {
		return productsInCart;
	}

	public void setProducts(List<ProductInCart> products) {
		
		this.productsInCart = products;
	}

	public void addProduct(ProductInCart product) {  // dodaje article u korpu 
		for (ProductInCart productInCart : productsInCart) { 
			
			if(productInCart.getProduct() instanceof Article && product.getProduct() instanceof Article) {
				ProductInCartArticle productInCartArticle= (ProductInCartArticle) productInCart;
				ProductInCartArticle productInCartArticleNew= (ProductInCartArticle) product;
				
	        if ((productInCartArticle.getProduct().getId().equals(productInCartArticleNew.getProduct().getId()) &&  // provera da nije isto izabrano
	        	Objects.equals(productInCartArticle.getModifierSize(),(productInCartArticleNew.getModifierSize())) &&  //Objects.equals lakse poredjenje
	        	Objects.equals(productInCartArticle.getModifierToppings(),(productInCartArticleNew.getModifierToppings())))) {// ako vec postoji taj samo dodaje kolicinu
	            	int updatedAmount = productInCartArticle.getNumberOrdered() + productInCartArticleNew.getNumberOrdered(); 
	            	productInCart.updateOrderedAmount(updatedAmount);
	            	return;// da ne ide dalje
	        }
			}else if(productInCart.getProduct() instanceof Combo && product.getProduct() instanceof Combo){
				ProductInCartCombo productInCartCombo= (ProductInCartCombo) productInCart;
				ProductInCartCombo productInCartComboNew= (ProductInCartCombo) product;
				
				if (productInCartCombo.getProduct().getId().equals(productInCartComboNew.getProduct().getId()) &&  // provera da nije isto izabrano  proizvod i id
			        	Objects.equals(productInCartCombo.getMain().getModifierToppings(),productInCartComboNew.getMain().getModifierToppings()) &&  //Objects.equals lakse poredjenje
						Objects.equals(productInCartCombo.getSide().getModifierToppings(),productInCartComboNew.getSide().getModifierToppings()) && 
						Objects.equals(productInCartCombo.getDrink().getModifierToppings(),productInCartComboNew.getDrink().getModifierToppings()) && 
						Objects.equals(productInCartCombo.getMain().getProduct().getId(),productInCartComboNew.getMain().getProduct().getId()) &&  
						Objects.equals(productInCartCombo.getSide().getProduct().getId(),productInCartComboNew.getSide().getProduct().getId()) && 
						Objects.equals(productInCartCombo.getDrink().getProduct().getId(),productInCartComboNew.getDrink().getProduct().getId())) {// ako vec postoji taj samo dodaje kolicinu
			            	int updatedAmount = productInCartCombo.getNumberOrdered() + productInCartComboNew.getNumberOrdered(); 
			            	productInCartCombo.updateOrderedAmount(updatedAmount);
			            	System.out.println(productInCartComboNew.getProduct().getName()+" "+updatedAmount);
			            	return;// da ne ide dalje
				}
			}
		}
		productsInCart.add(product);
	}
	public void removeProduct(ProductInCart product) {
	    productsInCart.remove(product);
	}
	public double getTotalPrice() {
		double totalPrice=0;
		for (ProductInCart productInCart : productsInCart) {
			totalPrice+=productInCart.getTotalPrice();
		}
		
		return totalPrice;
	}
}
