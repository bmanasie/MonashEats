

import java.util.ArrayList;

public class Restaurant {
	
private	String description;
private String name;
private String address;
private String openingHours;
private String deals;
private double deliveryCost;
private ArrayList<Item> items;
private float rating;
private double id;
private String phoneNumber;



/**
 * @return the description
 */
public String getDescription() {
	return description;
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
 * @param description the description to set
 */
public void setDescription(String description) {
	this.description = description;
}
/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
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
 * @return the openingHours
 */
public String getOpeningHours() {
	return openingHours;
}
/**
 * @param openingHours the openingHours to set
 */
public void setOpeningHours(String openingHours) {
	this.openingHours = openingHours;
}
/**
 * @return the deals
 */
public String getDeals() {
	return deals;
}
/**
 * @param deals the deals to set
 */
public void setDeals(String deals) {
	this.deals = deals;
}
/**
 * @return the deliveryCost
 */
public double getDeliveryCost() {
	return deliveryCost;
}
/**
 * @param deliveryCost the deliveryCost to set
 */
public void setDeliveryCost(double deliveryCost) {
	this.deliveryCost = deliveryCost;
}
/**
 * @return the items
 */
public ArrayList<Item> getItems() {
	return items;
}
/**
 * @param items the items to set
 */
public void setItems(ArrayList<Item> items) {
	this.items = items;
}
/**
 * @return the rating
 */
public float getRating() {
	return rating;
}
/**
 * @param rating the rating to set
 */
public void setRating(float rating) {
	this.rating = rating;
}
/**
 * @return the id
 */
public double getId() {
	return id;
}
/**
 * @param id the id to set
 */
public void setId(double id) {
	this.id = id;
}
	
}
