


import java.util.LinkedHashMap;

public class Cart {

	private LinkedHashMap<Item,Integer> items = new LinkedHashMap<>();
	private float totalPrice;
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
	 * @return the totalPrice
	 */
	public float getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	


}
