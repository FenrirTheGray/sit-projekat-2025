package sitprojekat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vaadin.flow.spring.annotation.VaadinSessionScope;

import sitprojekat.model.ProductInCart;

@Service
@VaadinSessionScope
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
	        if ((productInCart.getArticle().getName().equals(product.getArticle().getName()) &&  // provera da nije isto izabrano
	        	productInCart.getModifierSizePrice()==product.getModifierSizePrice()) &&
	        	productInCart.getModifierToppings()==product.getModifierToppings()) {// ako vec postoji taj samo dodaje kolicinu
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
}
