
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Tianchang Ning
 *
 */
public class UserController {

    protected User user;


    public User getUser() {
        return user;
    }
    
    public Customer geCustomer() {
        return (Customer) user;
    }
    
    public Admin getAdmin() {
        return (Admin) user;
    }
    
    //Method to handle user Login
    public boolean userLogin(String type, String emailAddress, String password) throws IOException {
        String fileName = "./Accounts/" + type + "Accounts";
        String data = "";
                /*Read the data file for account information. The account information is stored in the externa ltxt file.
                As a result, the registered account is remained after restarting the program.
                */
        Path file = Paths.get(fileName);
        if (Files.exists(file)) {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
        }
        String[] accounts = data.split("\n");
        List<String> accountsList = Arrays.asList(accounts);

        //Check if the email and password combination is valid.
        if(accountsList.contains(emailAddress + "&password:" + password)){
            switch(type){
                //Email and password combination is valid. Customer log in is successful. Create the customer object.
                case "Customer":
                    String[] CustomerInfo = accountsList.get(accountsList.indexOf(emailAddress + "&password:" + password) + 1).split("---");
                    String name = CustomerInfo[1];
                    user = new Customer(emailAddress, password, name, "", "");
                    return true;

                //Email and password combination is valid. Owner log in is successful. Create the Owner object.
                case "Owner":
                    user = new Owner(emailAddress, password, "");
                    return true;

                //Email and password combination is valid. Admin log in is successful. Create the admin object.
                case "Admin":
                    user = new Admin(emailAddress, password);
                    return true;
            }
        }
        // Email and password combination is invalid.
        return false;
    }
    
    //Method to check if the account has already exist.
    public boolean checkAccountExists(String accountType, String email) throws IOException {
        String data = "";
        String fileName = "./Accounts/" +accountType + "Accounts";
        Path file = Paths.get(fileName);
        if (Files.exists(file)) {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
        }
        String[] accounts = data.split("\n");
        //Check if the email exists
        for(String s: accounts){
            if(s.split("&password")[0].equals(email)){
                return true;
            }
        }
        return false;
    }

    //Create new account by wrtting the account information into data file.
    public void createAccount(String accountType, String email, String password, String userInfo) throws IOException {
        //Read the existing data file and append the new user account to it.
        String data = "";
        String fileName = "./Accounts/" +accountType + "Accounts";
        Path file = Paths.get(fileName);
        if (Files.exists(file)) {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
        }
        //Creating the account. Store the new account information to the data file.
        FileWriter writer = new FileWriter(fileName);
        writer.write(data + (data.equals("")? "" : "\n") + email + "&password:" + password + "\n" + userInfo);
        writer.close();
        System.out.println(accountType + " account " + email +" has been successfully registered");
        System.out.println("Enter any key to back to the main menu");
    }
    
    //Log out the user
    public boolean logOut() {
        //User can logout only if he has logged in.
        boolean success = (user != null);
        user = null;
        return success;
    }
}
