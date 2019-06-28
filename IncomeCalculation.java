package com.sapient.code;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.sapient.code.Currency.*;

public class IncomeCalculation {


    public static List<CSVInputData> convertCurrency(List<CSVInputData> list) {
        List<CSVInputData> updatedData=new ArrayList<>();
        for (CSVInputData data : list) {
            switch (data.getCurrency()) {
                case INR:
                    data.setAverageIncome(data.getAverageIncome() / 66f);
                    break;
                case GBP:
                    data.setAverageIncome(data.getAverageIncome() / 0.67f);
                    break;
                case SGD:
                    data.setAverageIncome(data.getAverageIncome() / 1.5f);
                    break;
                case HKD:
                    data.setAverageIncome(data.getAverageIncome() / 8f);
                    break;
                case USD:
                    data.setAverageIncome(data.getAverageIncome());
                    break;

            }
            updatedData.add(data);
        }
        return updatedData;

    }

   static Map<String, Average> averageIncome(List<CSVInputData> list) {
        Map<String, Average> countryMap = new HashMap<String, Average>();
        list.forEach(data -> {
            int count=0;
            if (data.getCountry().equals("")) {
                {
                    Average avg = countryMap.get(data.getCity() + "," + data.getGender())==null?new Average(0,0.0f):countryMap.get(data.getCity() + "," + data.getGender());
                    avg.setAvg(avg.getAvg() + avg.getAvg()+data.getAverageIncome());
                    avg.setCount(avg.getCount()+1);
                    countryMap.put(data.getCity() + "," + data.getGender(),  avg);
                }
            } else {
                if (countryMap.containsKey(data.getCountry() + "," + data.getGender())) {
                    Average avg = countryMap.get(data.getCountry() + "," + data.getGender())==null?new Average(0,0.0f):countryMap.get(data.getCountry() + "," + data.getGender());
                    avg.setAvg(avg.getAvg() +data.getAverageIncome());
                    avg.setCount(avg.getCount()+1);

                    countryMap.put(data.getCountry() + "," + data.getGender(), avg);
                } else {
                    countryMap.put(data.getCountry() + "," + data.getGender(), new Average(1,data.getAverageIncome()));
                }
            }

        });
        return countryMap;
    }
static void outputFile(String path){
    try {
        //reading data rowwise
     List<CSVInputData> data=   RowsFromInputFile.csvRows(path+"\\sampleInput.csv");
     data=convertCurrency(data);
     data.sort(Comparator.comparing(CSVInputData::getCountry));
     Map<String, Average> map=averageIncome(data);
        map.forEach((K,V)->V.setAvg(V.getAvg()/V.getCount()));
        Map<String, Average> map1= map.entrySet()
                .stream()
                .sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        System.out.println(map1);
        ReadWriteCsvFile.writeCSV(path+"\\sampleOutput.csv",map);

    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public static void main(String[] args) {
        outputFile("C:\\Users\\amit.dixit2\\Downloads\\Amit _Kumar_Gupta_Code2\\Amit _Kumar_Gupta_Code");
    }
}
