
import java.util.LinkedHashMap;


public class Receipt {
	private String time;
	private String restaurantName;
	private String customerName;
	private String address;
	private LinkedHashMap<Item, Integer> item;
	private double price;
	private int orderId;
	private String paymentMethods;
	private String deliveryTime;

	/**
	 * @return the restaurantName
	 */
	public String getRestaurantName() {
		return restaurantName;
	}

	/**
	 * @param restaurantName
	 *            the restaurantName to set
	 */
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	/**
	 * @return the deliveryTime
	 */
	public String getDeliveryTime() {
		return deliveryTime;
	}

	/**
	 * @param deliveryTime
	 *            the deliveryTime to set
	 */
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the item
	 */
	public LinkedHashMap<Item, Integer> getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(LinkedHashMap<Item, Integer> item) {
		this.item = item;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
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
	 * @param orderId
	 *            the orderId to set
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
	 * @param paymentMethods
	 *            the paymentMethods to set
	 */
	public void setPaymentMethods(String paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "Order Id- " + orderId + "," + "Customer Name- " + customerName + "," + "Restaurant Name- "
				+ restaurantName + "," + "Address- " + address + "," + "Order time- " + time + "," + "Delivery Time- "
				+ deliveryTime + "," + "Payment Method- "+ paymentMethods + ",Items Ordered- " + item.toString().replaceAll("=", "*").replaceAll(",", " ")
				+ ",Total Price- " + price;
	}

}
