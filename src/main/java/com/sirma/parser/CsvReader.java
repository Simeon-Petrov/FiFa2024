package com.sirma.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public List<String> readLine(String filePath) {
        List<String> lines = new ArrayList<>();

       try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
           br.readLine();

           String line;
           while ((line = br.readLine()) != null) {
               if (!line.trim().isEmpty()) {
                   lines.add(line);
               }
           }
       }catch (IOException e) {
           System.out.println("Error reading file: " + filePath);
       }
       return lines;
    }
}
