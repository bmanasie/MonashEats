import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
/**
 * @author Tianchang Ning
 * @author Manasie Bajpai 
 * @author Yi Wang 
 *
 */
public class MonashEats {
    static ArrayList<String> surburbList = new ArrayList<>();
    static AdminController admin = new AdminController();
    static CartController cart = new CartController();
    static UserController user = new UserController();
    static RestaurantController restaurantcontrol = new RestaurantController();
    static OrderController ordercontrol = new OrderController();
    static RateController rate = new RateController();
    static ViewRatingController viewRate = new ViewRatingController();
    static String restaurantName = null;
    static String suburbName = null;
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            while (true) {
                restaurantcontrol.createRestaurant();
                if(user.getUser() == null){
                    printOnScreen("Select the option to proceed");
                    printOnScreen("1. Login");
                    printOnScreen("2. Register");
                    printOnScreen("3. Search Restaurant");
                    printOnScreen("4. Exit Application");
                    int selection = getUserInputInt();
                    if (selection == 1) {
                        login();
                        continue;
                    }
                    if (selection == 2) {
                        register();
                        continue;
                    }
                    if (selection == 3) {
                        searchRestaurant();
                    }
                    if (selection == 4) {
                        break;
                    }
                }else{
                    boolean isCustomer = user.getUser().getClass().getName().equals("Customer");
                    printOnScreen("Select the option to proceed");
                    if(isCustomer)
                    {
                        printOnScreen("1. Search Restaurant");
                        printOnScreen("2. View Past Orders");
                        printOnScreen("3. Logout");
                    }else{
                        boolean isAdmin = user.getUser().getClass().getName().equals("Admin");
                        if(isAdmin){
                            viewRating();
                            logOut();
                            continue;
                        }
                        else
                            printOnScreen("1. Logout");
                    }
                    int selection = getUserInputInt();
                    if(isCustomer) {
                        if (selection == 1) {
                            searchRestaurant();
                        }
                        if (selection == 2) {
                            viewPastOrders();
                        }
                        if (selection == 3) {
                            logOut();
                            continue;
                        }
                    }else {
                        logOut();
                        continue;
                    }
                    boolean isAdmin = user.getUser().getClass().getName().equals("Admin");
                    if(isAdmin)
                        viewRating();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void logOut() {
        if(user.logOut()){
            printOnScreen("You have logged out.\nPlease Enter any key to back to the main menu.");
        }else{
            printOnScreen("You cannot logout since you haven't logged in.\nPlease Enter any key to go back to the main menu.");
        }
        @SuppressWarnings("resource")
        Scanner reader = new Scanner(System.in);
        reader.next();
    }

    // Search a restaurant by taking suburb input from Customer
    public static void searchRestaurant() throws IOException {
        surburbList = admin.getSurburbList();
        admin.setRestaurantinSuburb();

        while (true) {
            printOnScreen("Enter a suburb Name");
            printOnScreen("Select 0 to go back to main menu");
            suburbName = getUserInputString();
            if(suburbName.equals("0")){
                break;
            }
            if (surburbList.contains(suburbName.toLowerCase())) {
                selectRestaurant(suburbName.toLowerCase());
                break;

            } else {
                printOnScreen("Not a suburb name");

            }

        }
    }
    // View orders placed by the customer in the past
    public static void viewPastOrders() throws IOException {
        if(user.geCustomer() != null){
            String path = "./Orders/";
            File file = new File(path + user.geCustomer().getName() + ".txt");
            int index = 0;
            List<String> list = new ArrayList<>();
            if (file.exists()) {

                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String strLine;
                   
                    // Read lines from the file, returns null when end of stream is reached

                    while ((strLine = br.readLine()) != null) {
                        index++;
                        list = Arrays.asList(strLine.split(","));
                        printOnScreen(index + "");
                        for (int i = 0; i < list.size(); i++) {
                            printOnScreen(list.get(i));
                        }

                    }
                }

            }
            else{
                printOnScreen("No past orders");
            }
        }else{
            printOnScreen("Please Login First");
        }
    }
    // Select the restaurant by giving index from the given list of restaurants in the given suburb
    public static void selectRestaurant(String suburbName) throws IOException {
        LinkedList<String> restaurantList = admin.getRestaurantListinSuburb(suburbName);
        int actualSize = restaurantList.size();
        int restaurantIndex;

        while (true) {
            for (int i = 0; i < actualSize; i++) {

                printOnScreen(i + 1 + ": " + restaurantList.get(i));

            }
            printOnScreen("Select a restaurant by giving index");
            printOnScreen("Select 0 to go back");

            restaurantIndex = getUserInputInt();
            if (actualSize < restaurantIndex || restaurantIndex <= 0) {
                if(restaurantIndex < 0)
                    printOnScreen("Invalid Selection, please try again");
                else if(restaurantIndex == 0)
                {
                    searchRestaurant();
                    break;

                }
            } else {

                restaurantName = restaurantList.get(restaurantIndex - 1);
                displayRestaurant(restaurantName);
                break;
            }
        }

    }
    // Display the details of the selected restaurant by using restaurant controller
    public static void displayRestaurant(String restaurantName) throws IOException {

        printOnScreen(restaurantcontrol.getRestaurantDetails(restaurantName));
        if(user.getUser()!= null){
            addToCart(restaurantName);
        }
        else{
        printOnScreen("You have not logged in. Please select any key to go back to log in");
         Scanner reader = new Scanner(System.in);
        reader.next();
        }

    }

    //Add items to the cart from the selected restaurant by communicating with CartController
    public static void addToCart(String restaurantName) throws IOException {

        printOnScreen("Select items to be added to the cart by giving index");
        printOnScreen("Select 0 to go to the cart");
        printOnScreen("Select 100 to go to back and clear the cart");
        int itemIndex = getUserInputInt();
        while (true) {
            if (itemIndex == 0 && cart.displayCart().size()<=1) {

                printOnScreen("No items in the cart, add items");

            } else if (itemIndex == 0 || itemIndex==100) {
                break;
            }
            else{

                try {

                    cart.addItemToCart(restaurantcontrol.getItem(itemIndex - 1));

                } catch (Exception e) {
                    printOnScreen("Invalid input,please enter again");

                }
            }
            itemIndex = getUserInputInt();

        }
        if(itemIndex==100){
            cart.clearCart();
            selectRestaurant(suburbName.toLowerCase());
            return;
        }
        else{
            printOnScreen("Items in the cart-");
            displayCartItems(restaurantName);
        }

    }
    // Display items in the cart by using CartController
    public static void displayCartItems(String restaurantName) throws IOException {
        int quantity;
        int index;

        while (true) {
            printOnScreen(cart.displayCart());
            printOnScreen("Select one option");
            printOnScreen("1. Change Quantity");
            printOnScreen("2. Place Order");
            printOnScreen("3. Clear Cart");
            printOnScreen("4. Go back to restaurant menu");
            index = getUserInputInt();

            if (index == 1) {
                printOnScreen(cart.displayCart());
                printOnScreen("Select the item from the cart by giving the index");
                index = getUserInputInt();

                printOnScreen("Select the updated quantity");
                quantity = getUserInputInt();
                cart.updateQuantity(quantity, index - 1);

            } else if (index == 2) {
                ordercontrol.setTotalPrice(cart.calculateTotalPrice());
          
                if(placeOrder(restaurantName)==true){
                break;
                }

            } 
            else if(index ==3){
                cart.clearCart();
                displayRestaurant(restaurantName);
                break;
            }
            else if(index ==4){
                displayRestaurant(restaurantName);
                break;
            }
            else {
                printOnScreen("Invalid selection");
            }
        }

    }
    // Finalize the order after selecting the payment method and update delivery info
    public static boolean placeOrder(String restaurantName) throws IOException {
   
        String coupon;
        String message = "Type 1 to checkout or 2 to go back to cart";
        printOnScreen(cart.displayCart());
        while (true) {
            // Select payment method
            printOnScreen("Select the payment method-");
            printOnScreen("1. Cash");
            printOnScreen("2. Coupon");
            printOnScreen("3. Cash & Coupon");
            printOnScreen("4. Go back");
            int index = getUserInputInt();
            if (index == 1) {

                printOnScreen("Total amount to be paid by cash- $" + (Math.round(cart.calculateTotalPrice() * 100.0) / 100.0));

                while (true) {
                    printOnScreen(message);
                    index = getUserInputInt();
                    if (index == 1) {
                        //update delivery info
                        if(handlDeliveryInfo()==true){
                        ordercontrol.placeOrder("Cash", cart.getItems());
                        break;
                    }
                    }
                    else if(index ==2){
                        displayCartItems(restaurantName);
                        
                    }
                    else{
                        printOnScreen("Invalid selection");
                    }
                }
                break;
            } else if (index == 2) {

                printOnScreen("Enter Coupon Code-");
                coupon = getUserInputString();

                if (ordercontrol.applyCoupon(coupon)) {
                    printOnScreen("Coupon applied successfully");
                    printOnScreen("Total amount to be paid -$0");
                    printOnScreen(message);
                    index = getUserInputInt();
                    if (index == 1) {
                       if(handlDeliveryInfo()==true){
                        ordercontrol.placeOrder("Coupon", cart.getItems());
                        break;
                    }
                    }

                } else {

                    printOnScreen("Coupon cannot be applied for selected payment method");
                }

            } else if (index == 3) {

                printOnScreen("Enter Coupon Code-");
                coupon = getUserInputString();

                if (ordercontrol.applyCashCoupon(coupon)) {
                    printOnScreen("Coupon applied successfully");
                    printOnScreen("Total amount to be paid by cash- $" + ordercontrol.getTotalPrice());
                    printOnScreen(message);
                    index = getUserInputInt();
                    if (index == 1) {
                        if(handlDeliveryInfo()==true){
                        ordercontrol.placeOrder("Cash & Coupon", cart.getItems());
                        break;
                    }
                    }

                } else {

                    printOnScreen("Invalid selection");
                }

            } 

            else if (index==4){

                return false;
            }
            else {
                printOnScreen("Invalid selection");
            }
        }
        // Display the receipt of the order

        
        String order = ordercontrol.viewReceipt(restaurantName, user.geCustomer());
        ArrayList<String> list = new ArrayList<>(
                Arrays.asList(order.split(",")));
        printOnScreen(list);
        rateOrder(order);
        cart.clearCart();
        return true;
   
    }
    // Method to handle address and phone number information for delivery
    public static boolean handlDeliveryInfo() {

        String deliveryMessage = "Update delivery Info";
        String streetaddress;
        String suburbNameString;
        ArrayList<String> list = new ArrayList<>();
        printOnScreen(deliveryMessage);
        printOnScreen("Update Delivery Address");
        String regexStr = "^[0-9]{10}$";
        
        while (true) {
            
            printOnScreen("Enter Street Address");
            streetaddress = getUserInputString();
            printOnScreen("Enter suburb name");
            suburbNameString = getUserInputString();
            String input ;
            if (suburbNameString.equalsIgnoreCase(suburbName)) {
                list.add(streetaddress + " Suburb- " + suburbNameString);
                        printOnScreen("Enter Phone Number");
            input =getUserInputString();
            if(input.matches(regexStr)){
            list.add(input);

            break;
            }
            else {
                printOnScreen("Invalid phone number ");
               printOnScreen("Press 1 to enter again or 0 to go back");
               input = getUserInputString();
               if(input.equals("0")){
                return false;
                }
            }
              

            } else {
                printOnScreen("Delivery cannot be done in this surburb");
                printOnScreen("Press any key to enter again or 0 to go back");
                input =getUserInputString();
                 if(input.equals("0")){
                return false;
                }
            }

    }
                ordercontrol.updateDeliveryInfo(list);
        return true;

    }
    // Print on the screen of the user
    public static void printOnScreen(String msg) {
        System.out.println(msg);

    }

    public static void printOnScreen(ArrayList<String> list) {

        for (int i = 0; i < list.size(); i++) {
            printOnScreen(list.get(i));

        }

    }
    // Get string input from user
    public static String getUserInputString() {

        @SuppressWarnings("resource")
        Scanner reader = new Scanner(System.in);
        return reader.next();

    }
    // Get int input from user
    public static int getUserInputInt() {

        @SuppressWarnings("resource")

        Scanner reader = new Scanner(System.in);
        try {
            return reader.nextInt();
        } catch (java.util.InputMismatchException e) {
            return -1;
        }

    }

    //Get the type of Login
    public static void login() throws IOException {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        if(user.getUser() != null){
            printOnScreen("You already logged in as " + user.getUser().getClass().getName() + ": "+ user.getUser().getEmailAddress());
            printOnScreen("You need to logout first");
            printOnScreen("Please enter any key to back to the main menu");
            scanner.nextLine();
            return;
        }
        String input;
        while(true) {
            printOnScreen("Please the type of Log in: ");
            printOnScreen("1: Customer Login");
            printOnScreen("2: Owner Login");
            printOnScreen("3: Admin Login");
            printOnScreen("4: Return to the Main Menu");
            while (true) {
                input = scanner.nextLine();
                if (input.matches("[0-9]+") && Integer.parseInt(input) >= 1 && Integer.parseInt(input) <= 4) {
                    break;
                } else {
                    printOnScreen("Invalid Selection. Please enter number 1 to 4");
                    ;
                }
            }
            //Calling different function depending on user's input.
            switch (input) {
                case "1":
                userLogin("Customer");
                return;
                case "2":
                userLogin("Owner");
                return;

                case "3":
                userLogin("Admin");
                return;
                case "4":
                return;
            }
        }
    }

    //Get the type of Login
    public static void userLogin(String type) throws IOException {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        //Ask for user's Email.
        String emailAddress;
        while(true) {
            printOnScreen("Please enter your email: Enter 0 to go back");
            emailAddress = scanner.nextLine();
            if(emailAddress.equals("0")){
                return;
            }
            //Check if the email is valid.
            if(!emailAddress.matches("^[a-zA-Z0-9_!#$%&?*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
                printOnScreen("Email pattern is not valid");
            }else{
                break;
            }
        }
        printOnScreen("Please enter password:");
        String password = scanner.nextLine();

        boolean success = user.userLogin(type, emailAddress, password);
        if(success){
            printOnScreen("You are logged in as " + type + ": " + emailAddress);
            printOnScreen("Please enter any key to back to the main menu");
            scanner.nextLine();
        }else{
            //Email and password combination is invalid.
            printOnScreen("Invalid Username/Password");
            while (true) {
                //Ask user to select "try again" or "back to the main menu."
                printOnScreen("Please enter 1 to try again or 2 to back to the main menu");
                String input = scanner.nextLine();
                if (input.matches("[0-9]+") && (Integer.parseInt(input) == 1 || Integer.parseInt(input) == 2)) {
                    if(Integer.parseInt(input) == 1){
                        userLogin(type);
                        break;
                    }else{
                        return;
                    }
                }else{
                    printOnScreen("Invalid Input");
                }
            }
        }
    }

    private static void register() throws IOException {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String accountType;
        String password;
        String name;
        String confirmedPassword;
        String email;
        String userInfo = "";

        //Ask user the type of account to be registered. We supports two types: Customer and Owner.
        while(true){
            printOnScreen("Please enter the type of the account to register. 1 for Customer.  2 for Owner.  3 to back to the main menu");
            accountType = scanner.nextLine();
            if(accountType.equals("1")){
                accountType = "Customer";
                break;
            }else if (accountType.equals("2")){
                accountType = "Owner";
                break;
            }else if(accountType.equals("3")){
                return;
            }
            printOnScreen("Invalid Input");
        }

        /*Read the data file for account information. The account information is stored in the external file.
        As a result, the registered account is remained after restarting the program.
         */
        printOnScreen("Register " + accountType + " Account:");
        //Ask user to enter email.
        while(true){
            printOnScreen("Please enter your Email: Enter 0 to go back");
            email = scanner.nextLine();
            if(email.equals("0")){
                return;
            }
            boolean duplicated = user.checkAccountExists(accountType, email);
            if(!duplicated){
                //Regular expression to validate if the email follows the correct pattern.
                if(!email.matches("^[a-zA-Z0-9_!#$%&�*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
                    printOnScreen("Email pattern is not valid");
                    continue;
                }else{
                    break;
                }
            }else{
                printOnScreen("The Email already exists. Please enter another Email:");
                continue;
            }
        }
        //Ask user to enter password.
        while(true){
            printOnScreen("Please enter your password:");
            password = scanner.nextLine();

            printOnScreen("Please confirm your password:");
            confirmedPassword = scanner.nextLine();
            //check if the passered enter at the second time is the same as the first time.
            if(password.equals(confirmedPassword)){
                break;
            }
            printOnScreen("The password does not match. Please try again.");
        }
        //Collecting name for customer.
        if(accountType.equals("Customer")) {
            while(true) {
                printOnScreen("Please enter your name:");
                name = scanner.nextLine();
                if(!name.matches("[a-zA-Z]+")){
                    printOnScreen("name must be letters only");
                }else{
                    break;
                }
            }
            userInfo = "---" + name;
        }
        user.createAccount(accountType, email, password, userInfo);

    }
    // rate the order after place order
    public static void rateOrder(String order) throws IOException{
            System.out.println("Do you want to rate your order?(Y/N)");
            String input = getUserInputString();
            while(!input.equals("N")&&!input.equals("Y"))
            {
                System.out.println("Please enter Y or N:");
                input = getUserInputString();
            }
            if(input.equals("N")){
                System.out.println("Enjoy your meal!");
            }
            else{
                if(input.equals("Y")){
                    System.out.println("Please rate the restaurant(1 - 5):");
                    int res = getUserInputInt();
                    while(res>5 || res<1)
                    {
                        System.out.println("Please rate the restaurant(1 - 5):");
                        res = getUserInputInt();
                    }
                    System.out.println("Please rate the delievery(1 - 5):");
                    int del = getUserInputInt();
                    while(del<1 || del >5)
                    {
                        System.out.println("Please rate the delievery(1 - 5):");
                        del = getUserInputInt();
                    }
                    System.out.println("Please tell us whether your food is damaged(damaged/fine):");
                    String dam = getUserInputString();
                    while(!dam.equals("damaged") && !dam.equals("fine"))
                    {
                         System.out.println("Please tell us whether your food is damaged(damaged/fine):");
                         dam = getUserInputString();       
                    }
                    System.out.println("Please rate your food(verygood, good, normal, bad):");
                    String food = getUserInputString();
                    while(!food.equals("verygood") && !food.equals("good") && !food.equals("normal") && !food.equals("bad"))
                    {
                        System.out.println("Please rate your food(verygood, good, normal, bad):");
                        food = getUserInputString();
                    }
                    String rating = ",Restaurant Rating: "+ res + "," + "Delievery Rating:" + del + "," + "Food Damaged:" + dam + "," + "Food Rating:" + food;
                    
                    String path = "./Orders/";
                    File file = new File(path + user.geCustomer().getName() + ".txt");
                    rate.rate(file, rating, order);
                    String resPath = "./Restaurants/";
                    File resFile = new File(resPath + restaurantName + ".txt");
                    rate.update(resFile,res);
                    }
                    
            }
            
    }
    // get int number and validation
    public static int getInt() {
        Scanner reader = new Scanner(System.in);
        try {
            String input = reader.nextLine();
                while(!input.matches("[0-9]+") || Integer.parseInt(input) < 1 || Integer.parseInt(input) > 5) 
                {
                    System.out.println("Please enter number between 1 and 5.");
                    input = reader.nextLine();
                } 
            return Integer.parseInt(input);
        } catch (java.util.InputMismatchException e) {
            return -1;
        }

    }
    
    /**
     * view rating page
     *
     */
    public static void viewRating()
    {
        System.out.println("1.View restaurant ratings");
        System.out.println("2.View customer ratings by order");
        System.out.println("3.Log out");
        int option = getInt();
        while(option!=3){
        try{
            if(option == 1){
                String[] view = viewRate.viewResRating();
                for(int i = 0; i<view.length; i ++){
                    if(!view[i].equals(""))
                        System.out.println(view[i]);
            }
                System.out.println("");
            }
            if(option == 2){
                String[] view = viewRate.viewCustOrder();
                for(int i = 0; i<view.length; i ++){
                    if(!view[i].equals(""))
                        System.out.println(view[i]);
                }
            }
            System.out.println("1.View restaurant ratings");
            System.out.println("2.View customer ratings by order");
            System.out.println("3.Log out");
            option = getInt();
        }
        catch (IOException e) {
            e.printStackTrace();
        }}
    }
    
}
