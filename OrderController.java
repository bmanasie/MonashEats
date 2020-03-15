
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;

public class OrderController {

    private ArrayList<Coupon> coupons;

    Order order = new Order();

    public OrderController() {
        // create coupons with their code and value using coupon class
        coupons = new ArrayList<>();
        coupons.add(new Coupon("ABCD123", 5));
        coupons.add(new Coupon("DISC5", 5));
        coupons.add(new Coupon("DISC2", 2));
        coupons.add(new Coupon("DISC567", 3));

    }
    //Method will be called when Coupon option is selected 
    public boolean applyCoupon(String coupon) {

        for (int i = 0; i < coupons.size(); i++) {
            if (coupons.get(i).getCode().equals(coupon) && order.getPrice() == coupons.get(i).getValue()) {
                order.setPrice(0);
                return true;
            }

        }

        return false;

    }
    // Method will be called when Cash & Coupon option is selected
    public boolean applyCashCoupon(String coupon) {

        for (int i = 0; i < coupons.size(); i++) {
            if (coupons.get(i).getCode().equals(coupon) && order.getPrice() >= coupons.get(i).getValue()) {
                order.setPrice(order.getPrice() - coupons.get(i).getValue());
                return true;
            }

        }

        return false;

    }

    public double getTotalPrice() {

        return order.getPrice();

    }
    // Set the total price in the object of order class
    public void setTotalPrice(double price) {

        order.setPrice(price);

    }
    // Update the delivery addrees and phone number
    public void updateDeliveryInfo(ArrayList<String> info) {

        order.setAddress(info.get(0));
        order.setPhoneNumber(info.get(1));

    }
    // Method is called while placing the order, setting attribute values order class
    public void placeOrder(String paymentMethod, LinkedHashMap<Item, Integer> map) {
        int x = (int) ((Math.random()*((100000-1)+1))+1);
        order.setPaymentMethods(paymentMethod);
        order.setOrderId(x);
        order.setItems(map);

    }

    //View the receipt after placing the order 
    public String viewReceipt(String restaurantName, Customer cust) throws IOException {

        String path = "./Orders/";
        File file = new File(path + cust.getName() + ".txt");
        Calendar calendar = Calendar.getInstance();
        // Adding the 40 minutes in the current time
        calendar.add(Calendar.MINUTE, 40);
        //Get the current time
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Receipt receipt = new Receipt();
        receipt.setAddress(order.getAddress());
        receipt.setPaymentMethods(order.getPaymentMethods());
        receipt.setTime(dateFormat.format(date));
        receipt.setItem(order.getItems());
        receipt.setPrice((double)(Math.round(order.getPrice() * 100)) / 100);
        receipt.setDeliveryTime(dateFormat.format(calendar.getTime()));
        receipt.setRestaurantName(restaurantName);
        receipt.setCustomerName(cust.getName());
        receipt.setOrderId(order.getOrderId());

        //Creating a file or appending orders in the existing past orders for a particular customer
        if (file.exists()) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.newLine(); // Add new line

            writer.write(receipt.toString());
            writer.close();
        }
        else {
            PrintWriter writer = new PrintWriter(path + cust.getName() + ".txt", "UTF-8");
            writer.print(receipt.toString());
            writer.close();
        }

        return receipt.toString();
    }

}
