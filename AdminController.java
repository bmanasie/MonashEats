

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class AdminController {

	private ArrayList<String> suburbList = new ArrayList<>();
	private HashMap<String, LinkedList<String>> suburbRestaurantMap = new HashMap<>();

	public AdminController() {
		// Create a suburblist
		suburbList.add("caufield");
		suburbList.add("carlton");
		suburbList.add("richmond");
		suburbList.add("frankston");
		suburbList.add("clayton");

	}

	public ArrayList<String> getSurburbList() throws IOException {

		return suburbList;

	}

	// Set the restaurants in each suburb
	public void setRestaurantinSuburb() throws IOException {
		LinkedList<String> restaurantList = new LinkedList<>();
		File folder = new File("./SuburbInfo");
		File[] fileNames = folder.listFiles();
		// Read each surburb file to get list of restaurants
		for (File file : fileNames) {
			restaurantList = new LinkedList<>();
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String strLine;
				// Read lines from the file, returns null when end of stream is reached
				while ((strLine = br.readLine()) != null) {
					restaurantList.add(strLine);
				}
			}
			suburbRestaurantMap.put(file.getName().replaceFirst("[.][^.]+$", ""), restaurantList);
		}

	}

	// Get the list of restaurants in the choose suburb
	public LinkedList<String> getRestaurantListinSuburb(String suburb) {
		return suburbRestaurantMap.get(suburb);

	}

}
