
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class CartController {

    Cart obj = new Cart();
    LinkedHashMap<Item, Integer> map = new LinkedHashMap<>();
    ArrayList<Item> items = new ArrayList<Item>();

    // Add item to the cart with initial quanity being 1
    public void addItemToCart(Item item) {
        if(map.containsKey(item)) {
            
            map.put(item, map.get(item)+1);
            
        }
        else {
            map.put(item, 1);
    }
        items.add(item);
        obj.setItems(map);
    }

    // Method to display cart
    public ArrayList<String> displayCart() {
        ArrayList<String> list = new ArrayList<>();
         int index =1;
         
        for (Map.Entry<Item, Integer> entry : map.entrySet()) {
            Item item = entry.getKey();
            int quantity = entry.getValue();
            list.add(index+". "+item.getName() + " " + item.getPrice() + " " + quantity);
            index++;
        }
        list.add("" + (float) (Math.round(calculateTotalPrice() * 100.0) / 100.0));
        return list;
    }

    // Get the items in the cart with their quantity
    public LinkedHashMap<Item, Integer> getItems() {

        return map;
    }

    // Calculate total price of the items in the cart based on their quantity
    public double calculateTotalPrice() {
        float totalPrice = 0;
        float individualPrice;
        int quantity = 0;
        for (Map.Entry<Item, Integer> entry : map.entrySet()) {

            Item item = entry.getKey();
            quantity = entry.getValue();
            individualPrice = item.getPrice() * quantity;
            totalPrice = totalPrice + individualPrice;
        }
        totalPrice = (float) (Math.round(totalPrice * 100.0) / 100.0);;
        // Set the total price in the attribute of cart class
        obj.setTotalPrice(totalPrice);
        return totalPrice;
    }

    // Update the quantity of items in the cart, remove item from cart if the
    // quantity is 0
    public void updateQuantity(int updatedQuantity, int index) {
    
        if (updatedQuantity == 0) {
            // remove the item from the cart if the quantity is 0
            map.remove(items.get(index));

        } else if (updatedQuantity > 0) {
            // update the quantity of the item in the cart
            map.put(items.get(index), updatedQuantity);
        }
        obj.setItems(map);

    }

    // Remove all the items from cart or clear the cart
    public void clearCart() {
        items.clear();
        map.clear();
    }
}
