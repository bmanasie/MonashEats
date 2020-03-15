
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 * Class for the admin to view ratings for restaurants and customers.
 *
 * @Yiwang29209409
 * 
 */
public class ViewRatingController
{
    // instance variables - replace the example below with your own
    

    /**
     * Constructor for objects of class ViewRatingController
     */
    public ViewRatingController()
    {
        // initialise instance variables
        
    }

    /**
     * view the restaurant rating
     *
     */
     public String[] viewResRating()throws IOException{
        File folder = new File("./Restaurants/");
        File[] fileNames = folder.listFiles();
        String[] view = new String[1000];
        for(int i = 0; i<1000; i++)
            view[i] = "";
        int number = 0;
        for(File file : fileNames)
        {
            try (BufferedReader br = new BufferedReader(new FileReader(file))){
                int index = 0;
                String line;
                while(index < 4)
                {
                    line = br.readLine();
                    if(index == 3)
                    {
                        String fileName = file.getName();
                        String name = fileName.substring(0,fileName.lastIndexOf("."));
                        
                        view[number] = name + " " + line;
                        number ++;
                    }
                    index ++;
                }
                br.close();
            }
        }
        return view;
    }
    /**
     * view the customer rating by order
     * 
     */
     public String[] viewCustOrder()throws IOException{
        File folder = new File("./Orders/");
        File[] fileNames = folder.listFiles();
        String[] view = new String[1000];
        for(int i = 0; i<1000; i++)
            view[i] = "";
        int number = 0;
        for(File file : fileNames)
        {
            try (BufferedReader br = new BufferedReader(new FileReader(file))){
                String line;
                line = br.readLine();
                if(line!=null)
                {
                    String fileName = file.getName();
                    String name = fileName.substring(0,fileName.lastIndexOf("."));
                    view[number] = name;
                    number ++;
                }
                while(line!=null)
                {
                    String[] list = new String [20];
                    list = line.split(",");
                    if(list.length>10){
                        view[number] = list[0];
                        number ++;
                        for(int i = 9; i< 13; i ++)
                        {
                            view[number] = list[i];
                            number ++;
                        }
                        view[number] = "  ";
                        number ++;
                }
                    line = br.readLine();
                }
                br.close();
            }
        }
        return view;
    }
    
}
