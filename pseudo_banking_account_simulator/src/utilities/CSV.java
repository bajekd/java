
package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class CSV {
    //Function to read data from CSV and return it as a list
    public static List<String[]> read(String file) {
        List<String[]> data = new LinkedList<String[]>();
        String dataRow;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ( (dataRow = br.readLine()) != null) {
                String[] dataRecords = dataRow.split(",");
                data.add(dataRecords);
            }
        
    } catch (FileNotFoundException e) {
        System.out.println(" Couldn't find file!");
    } catch (IOException e) {
        System.out.println(" Couldn't read file!");
        e.printStackTrace();
    }
      return data;  
    }
}

