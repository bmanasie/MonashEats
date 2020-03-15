

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class RestaurantController {
    ArrayList<Restaurant> restaurantList = new ArrayList<>();
    ArrayList<String> restaurantDetails = new ArrayList<>();
    LinkedList<Item> items = new LinkedList<>();
   

    // Create a restaurant object by reading file corresponding each restaurant, defining the attributes
    public void createRestaurant() throws IOException {

        Restaurant objRestaurant = new Restaurant();
        restaurantDetails = new ArrayList<>();
        restaurantList = new ArrayList<>();
        File folder = new File("./Restaurants");
        ArrayList<Item> items = new ArrayList<>();
        File[] fileNames = folder.listFiles();
        String name = null;
        for (File file : fileNames) {
            objRestaurant = new Restaurant();
            name = file.getName().replaceFirst("[.][^.]+$", "");
            objRestaurant.setName(name);

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String strLine;
                int count = 0;
                int index = 1;
                items = new ArrayList<>();
                // Read lines from the file, returns null when end of stream is reached

                while ((strLine = br.readLine()) != null) {

                    if (count >= 5) {
                        items.add(new Item(strLine.split(",")[0], Float.parseFloat(strLine.split(",")[1])));
                    } else {
                        if (count == 0) {

                            objRestaurant.setAddress(strLine);
                        } else if (count == 1) {

                            objRestaurant.setPhoneNumber(strLine);
                        } else if (count == 2) {

                            objRestaurant.setDescription(strLine);
                        } else if (count == 3) {

                            objRestaurant.setRating(Float.valueOf(strLine));
                        } else if (count == 4) {

                            objRestaurant.setOpeningHours(strLine);
                        }
                    }
                    count++;
                }

                objRestaurant.setId(index);
                index++;
            }
            objRestaurant.setItems(items);
            restaurantList.add(objRestaurant);
        }

    }
    // Get the details of the restaurant based on the selection

    public ArrayList<String> getRestaurantDetails(String restaurnatName) {
        Restaurant obj;
        String name;
        Item item;
        restaurantDetails = new ArrayList<>();
        items = new LinkedList<>();
        for (int i = 0; i < restaurantList.size(); i++) {
            obj = restaurantList.get(i);
            name = obj.getName();

            if (name.equals(restaurnatName.toLowerCase())) {


                restaurantDetails.add(obj.getAddress());
                restaurantDetails.add(obj.getPhoneNumber());
                restaurantDetails.add(obj.getDescription());
                restaurantDetails.add("" + obj.getRating() + "");
                restaurantDetails.add(obj.getOpeningHours());
                // Get details of the items in the menu using item class
                for (int y = 0; y < obj.getItems().size(); y++) {
                    item = obj.getItems().get(y);
                    restaurantDetails.add(y + 1 + ": " + item.getName() + " " + item.getPrice());
                    items.add(item);

                }

            }

        }

        return restaurantDetails;
    }

// Get each item from the list of items based on index
	public Item getItem(int index) {
		return items.get(index);
		
	}
}
