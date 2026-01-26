package sitprojekat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.vaadin.flow.spring.annotation.VaadinSessionScope;

import sitprojekat.model.ProductInCart;

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
	        if ((productInCart.getArticle().getId().equals(product.getArticle().getId()) &&  // provera da nije isto izabrano
	        	Objects.equals(productInCart.getModifierSize(),product.getModifierSize())) &&  //Objects.equals lakse poredjenje
	        	Objects.equals(productInCart.getModifierToppings(),(product.getModifierToppings()))) {// ako vec postoji taj samo dodaje kolicinu
	            	int updatedAmount = productInCart.getNumberOrdered() + product.getNumberOrdered(); 
	            	productInCart.updateOrderedAmount(updatedAmount);
	            	return;// da ne ide dalje
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
