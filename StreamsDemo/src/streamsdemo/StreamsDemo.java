/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streamsdemo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author HP
 */
public class StreamsDemo {

    public StreamsDemo() {
        List<Integer> integerList = new ArrayList<>();
        /*integerList.add(10);
        integerList.add(50);
        integerList.add(20);
        integerList.add(30);
        integerList.add(60);
        integerList.add(70);
        integerList.add(40);*/

        int sum = integerList.stream().mapToInt(num -> num.intValue()).sum();
        System.out.println("Sum = " + sum);


        Optional<Integer> min = integerList.stream().min(Comparator.comparing(Integer::intValue));
        System.out.println("min = " + min.get());
        //    for (Integer number : integerList)
        //        System.out.println(number);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new StreamsDemo();
    }

}
