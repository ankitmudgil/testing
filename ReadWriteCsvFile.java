package com.sapient.code;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadWriteCsvFile {
    static BufferedReader readCSV(String path) {
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader(path));
            fileReader.readLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileReader;
    }

    static boolean writeCSV(String path, Map<String, Average> countryMap) {
        File fileout = new File(path);
       // Map<String, Float> countryMap = new HashMap<String, Float>();
        try {
            FileOutputStream outfile = new FileOutputStream(fileout);
            //DataOutputStream ds = new DataOutputStream(outfile);
            String msg = "City/Country Gender Average Income(in USD)";
            FileWriter fw = new FileWriter(path);
            fw.append(msg);
            fw.append("\n");
            for (Map.Entry<String, Average> ms : countryMap.entrySet()) {

                String key = ms.getKey();
                Average value = ms.getValue();
                String countryGen[] = key.split(",");
                String recard = countryGen[0] + " " + countryGen[1] + " " + value.getAvg();
                fw.append(recard);
                fw.append("\n");
            }
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
