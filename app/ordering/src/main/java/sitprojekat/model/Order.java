package sitprojekat.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Order extends AbstractEntity {

	private UserAccount user;

	private LocalDate createdAtDate;
	private LocalTime createdAtTime;
	
	private LocalTime sentAtTime;
	private LocalTime recievedtedAtTime;
	private OrderStatus status = OrderStatus.ACCEPTED;	
	private List<OrderedProduct> orderedProducts;
	private double price;
	private String paymentType;

	public Order() {
	}

	public Order(String id,UserAccount user, LocalDate createdAtDate, LocalTime createdAtTime, LocalTime sentAtTime,
			LocalTime recievedtedAtTime, OrderStatus status, List<OrderedProduct> orderedProducts, double price,
			String paymentType) {
		super(id);
		this.user = user;
		this.createdAtDate = createdAtDate;
		this.createdAtTime = createdAtTime;
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



	public LocalDate getCreatedAtDate() {
		return createdAtDate;
	}



	public void setCreatedAtDate(LocalDate createdAtDate) {
		this.createdAtDate = createdAtDate;
	}



	public LocalTime getCreatedAtTime() {
		return createdAtTime;
	}



	public void setCreatedAtTime(LocalTime createdAtTime) {
		this.createdAtTime = createdAtTime;
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
