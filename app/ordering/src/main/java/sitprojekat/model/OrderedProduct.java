package sitprojekat.model;

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
	
	
	
}
