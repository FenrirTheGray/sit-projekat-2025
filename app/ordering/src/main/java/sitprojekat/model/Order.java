package sitprojekat.model;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order extends AbstractEntity {

	private UserAccount user;

	
	private String createdAt;

	
	private LocalTime sentAtTime;
	private LocalTime recievedtedAtTime;
	private OrderStatus status;	
	@JsonProperty("choices")
	private List<OrderedProduct> orderedProducts;
	private double price;
	private String paymentType;

	public Order() {
	}

	public Order(String id,UserAccount user, String createdAt, LocalTime sentAtTime,
			LocalTime recievedtedAtTime, OrderStatus status, List<OrderedProduct> orderedProducts, double price,
			String paymentType) {
		super(id);
		this.user = user;
		this.sentAtTime = sentAtTime;
		this.recievedtedAtTime = recievedtedAtTime;
		this.status = status;
		this.orderedProducts = orderedProducts;
		this.price = price;
		this.paymentType = paymentType;
	}



	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}

	public List<OrderedProduct> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(List<OrderedProduct> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}






	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


	public LocalTime getSentAtTime() {
		return sentAtTime;
	}



	public void setSentAtTime(LocalTime sentAtTime) {
		this.sentAtTime = sentAtTime;
	}



	public LocalTime getRecievedtedAtTime() {
		return recievedtedAtTime;
	}



	public void setRecievedtedAtTime(LocalTime recievedtedAtTime) {
		this.recievedtedAtTime = recievedtedAtTime;
	}



	public OrderStatus getStatus() {
		return status;
	}



	public void setStatus(OrderStatus status) {
		this.status = status;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public String getPaymentType() {
		return paymentType;
	}



	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	

}
