/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author HP
 */
public class Lab3Task {

    public Lab3Task() {
        //attempt1();
        attempt2();
    }

    public void attempt1() {
        try {
            RandomAccessFile input = new RandomAccessFile("C:\\Users\\HP\\Desktop\\Tomal sirs AJ\\Lab3Task\\numbers.txt", "r");
            RandomAccessFile output = new RandomAccessFile("C:\\Users\\HP\\Desktop\\Tomal sirs AJ\\Lab3Task\\distinct_sorted.txt", "rw");

            output.setLength(0);

            String line;

            List<Integer> numberList = new ArrayList<>();

            while ((line = input.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line);
                    numberList.add(number);
                    //System.out.printf("[%s] %d\n", line, number);
                } catch (NumberFormatException nfe) {
                    System.err.printf("Not a number: [%s]\n", line);
                }
//                Optional<Integer> optionalNumber = null;
//                try{
//                    optionalNumber = Optional.of(Integer.parseInt(line));
//            
//                }catch(NumberFormatException nfe){
//                    optionalNumber = Optional.empty();
//                    System.err.printf("Not a number: [%s]\n", line);
//                }
//                if(optionalNumber.isPresent())
//                    System.out.printf("%d\n", optionalNumber.get());
            }

            Collections.sort(numberList);

            List<Integer> distinctList = new ArrayList<>();
            for (int i = 0; i < numberList.size() - 1; i++)
                if (!numberList.get(i).equals(numberList.get(i + 1)))
                    distinctList.add(numberList.get(i));

            distinctList.add(numberList.get(numberList.size() - 1));

            for (Integer number : distinctList)
                output.writeBytes(number + "\n");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lab3Task.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lab3Task.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void attempt2() {
        try {
            List<Integer> distinctList = Files
                    .lines(Paths.get("C:\\Users\\HP\\Desktop\\Tomal sirs AJ\\Lab3Task\\numbers.txt"))
                    //.mapToInt(string -> Integer.parseInt(string))
                    .map(string -> Integer.parseInt(string))
                    .filter(number -> number > 100)
                    .sorted()
                    .distinct()
                    .collect(Collectors.toList());
            //.forEach(string -> System.out.println(string));

            distinctList.forEach(string -> System.out.println(string));
        } catch (IOException ex) {
            Logger.getLogger(Lab3Task.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Lab3Task();
    }

}
