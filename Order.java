

import java.util.Date;
import java.util.LinkedHashMap;

public class Order {

	private Date time;
	private String status;
	private Date deliveryTime;
	private String phoneNumber;
	private String address;
	private LinkedHashMap<Item,Integer> items;
	private double price;
	private int orderId;
	private String paymentMethods;
	
	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}



	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}



	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}



	/**
	 * @return the deliveryTime
	 */
	public Date getDeliveryTime() {
		return deliveryTime;
	}



	/**
	 * @param deliveryTime the deliveryTime to set
	 */
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}



	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}



	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}



	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}







	/**
	 * @return the items
	 */
	public LinkedHashMap<Item, Integer> getItems() {
		return items;
	}



	/**
	 * @param items the items to set
	 */
	public void setItems(LinkedHashMap<Item, Integer> items) {
		this.items = items;
	}



	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}



	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}



	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}



	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



	/**
	 * @return the paymentMethods
	 */
	public String getPaymentMethods() {
		return paymentMethods;
	}



	/**
	 * @param paymentMethods the paymentMethods to set
	 */
	public void setPaymentMethods(String paymentMethods) {
		this.paymentMethods = paymentMethods;
	}


	
}
