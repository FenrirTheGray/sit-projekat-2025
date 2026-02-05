package sitprojekat.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderedProduct extends AbstractEntity {

	
	private int amount;
	private Product orderedProduct;
	
	public OrderedProduct() {}
	
	public OrderedProduct(String id,int amount, Product orderedProduct) {
		super(id);
		this.amount = amount;
		this.orderedProduct = orderedProduct;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Product getOrderedProduct() {
		return orderedProduct;
	}
	public void setOrderedProduct(Product orderedProduct) {
		this.orderedProduct = orderedProduct;
	}
	@JsonProperty("article")
    public void setOrderedProductArticle(Article article) { // kako bi jackson znao da li da stavi article ili porduct
        this.orderedProduct = article;
    }
	@JsonProperty("combo")
    public void setOrderedProductCombo(Combo combo) {
        this.orderedProduct = combo;
    }
	
	
}
