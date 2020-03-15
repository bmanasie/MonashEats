
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Calss for customer to rate the order
 *
 * @author Yiwang 29208408
 * @author Tianchang Ning
 * 
 */

public class RateController
{
    // instance variables - replace the example below with your own
    

    /**
     * Constructor for objects of class RateController
     */
    public RateController()
    {
        
    }

    /**
     * Get string input
     *
     */
    public String getUserInputString() {
        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }
    /**
     * Get int input
     */
    public int getUserInputInt() {
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
     * Rate the order
     *
     * @param  file    the file to read
     * @param  order    the order information
     * @param  rating   the rating information
     */
    public void rate(File file, String rating, String order)throws IOException
    {
        if (file.exists()) {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
                        try {
                            String strLine = br.readLine();
                            while (strLine != null) {
                                if(strLine.equals(order)){
                                    bw.write(rating);
                                }
                                strLine = br.readLine();
                            }
                            br.close();
                            bw.close();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
    }
    // update the restaurant rating
    public void update(File file, int rate)throws IOException
    {
        if(file.exists())
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try{
                String strLine;
                String[] content = new String[20];
                int count = 0;
                while ((strLine = br.readLine()) != null) {
                    content[count] = strLine;
                    count ++;
                }
                double tem = (Double.parseDouble(content[3]) + Double.valueOf(rate))/2;
                tem = getResRating(file.getName() ,rate);
                content[3] = Double.toString(tem);
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                for(int i = 0;i<count;i ++){
                    bw.write(content[i]);
                    bw.newLine();
                }
                br.close();
                bw.close();
                }
            catch (Exception e) {
                 e.printStackTrace();
            }
        }
    }
    //Calculate the restaurant rating
    public  double getResRating(String restName, int rate)throws IOException{
        File folder = new File("./Orders/");
        File[] fileNames = folder.listFiles();
        HashMap<String,Integer> RestTotalRating = new HashMap<>();
        HashMap<String,Integer> RestTotalRatingSize = new HashMap<>();
        restName = restName.substring(0,restName.indexOf(".txt"));
        for(File file : fileNames)
        {
            try (BufferedReader br = new BufferedReader(new FileReader(file))){
                String line;
                line = br.readLine();
                while(line!=null)
                {
                    String[] list = new String [20];
                    list = line.split(",");
                    if(list.length <= 9){
                        line = br.readLine();
                        continue;
                    }
                    String RestaurantName = list[2].substring(list[2].indexOf('-') + 2);
                    int rating = 0;
                    try{
                        rating = Integer.valueOf(list[9].substring(19));
                    }catch(Exception e){

                    }
                    //Find all historical ratings
                    if(RestTotalRating.containsKey(RestaurantName)){
                        RestTotalRating.put(RestaurantName,RestTotalRating.get(RestaurantName) + rating);
                        RestTotalRatingSize.put(RestaurantName,RestTotalRatingSize.get(RestaurantName) + 1);
                    }else{
                        RestTotalRating.put(RestaurantName, rating);
                        RestTotalRatingSize.put(RestaurantName, 1);
                    }
                    line = br.readLine();
                }
            }
        }
        //Calculate new ratings using all historical ratings
        double rating = (double)RestTotalRating.get(restName) /RestTotalRatingSize.get(restName)* 100 / 100;
        rating = (double)(Math.round(rating * 100)) / 100;
        return rating;
    }
}
