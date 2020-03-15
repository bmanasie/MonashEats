

import java.util.ArrayList;

public class Customer extends User {
    private String name;
    private String address;
    private String phoneNumber;
    private ArrayList<Order> pastOrders;
    
    public Customer(String emailAddress, String password, String name, String address, String phoneNumber) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }    
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

	/**
	 * @return the pastOrders
	 */
	public ArrayList<Order> getPastOrders() {
		return pastOrders;
	}

	/**
	 * @param pastOrders the pastOrders to set
	 */
	public void setPastOrders(ArrayList<Order> pastOrders) {
		this.pastOrders = pastOrders;
	}

}
