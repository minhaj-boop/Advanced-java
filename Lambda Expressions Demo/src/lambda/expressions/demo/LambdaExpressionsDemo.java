/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lambda.expressions.demo;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author HP
 */
public class LambdaExpressionsDemo {

    class StudentComparatorByIdAsc implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {

            return (int) o1.getId() - (int) o2.getId();
        }
    }

    class StudentComparatorByIdDesc implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {

            return (int) o2.getId() - (int) o1.getId();
        }
    }

    class StudentComparatorByGpaAsc implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {

            return (int) (o1.getGpa() * 100 - o2.getGpa() * 100);
        }
    }

    public void integerArraySorting() {
        System.out.println("Integer Array");

        int array[] = {35, 4, 5, 67, 2, 11};

        System.out.println("Before Sorting:");
        System.out.println(Arrays.toString(array));

        Arrays.sort(array);
        System.out.println("After Sorting");
        System.out.println(Arrays.toString(array));
    }

    public void stringArraySorting() {
        System.out.println("\nString Array");

        String array[] = {"35", "4", "5", "67", "2", "11"};

        System.out.println("Before Sorting:");
        System.out.println(Arrays.toString(array));

        Arrays.sort(array);
        System.out.println("After Sorting");
        System.out.println(Arrays.toString(array));
    }

    public void studentArraySorting() {
        System.out.println("\nStudent Array");

        Student array[] = {
                new Student(1730400, "Minhaj", LocalDate.of(1998, Month.DECEMBER, 2), 3.4),
                new Student(1730232, "Don", LocalDate.of(1999, Month.AUGUST, 23), 3.9),
                new Student(1730777, "Trikkal", LocalDate.of(1990, Month.JANUARY, 7), 2.8),
                new Student(1620200, "Solomonco", LocalDate.of(1998, Month.MAY, 4), 3.1)
        };

        System.out.println("Before Sorting:");
        for (Student student : array) {
            System.out.println(student);
        }

        //Arrays.sort(array, new StudentComparatorByIdDesc());
        //Arrays.sort(array, new StudentComparatorByIdAsc());
        //Arrays.sort(array, new StudentComparatorByGpaAsc());

        //Anynomous Inner Class
        Arrays.sort(array, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (int) (o2.getGpa() * 100 - o1.getGpa() * 100);
            }
        });

        //Lambda Expressions(version 1)
        Arrays.sort(array, (Student o1, Student o2) -> {
                    return (int) (o2.getGpa() * 100 - o1.getGpa() * 100);
                }
        );

        //Lambda Expressions(version 2)
        Arrays.sort(array, (o1, o2) -> {
                    return (int) (o2.getGpa() * 100 - o1.getGpa() * 100);
                }
        );

        //Lambda Expressions(version 3)
        Arrays.sort(array, (o1, o2) -> (int) (o2.getGpa() * 100 - o1.getGpa() * 100)
        );

        //Lambda Expressions(version 4)
        Arrays.sort(array, Comparator.comparing(Student::getGpa).reversed());

        System.out.println("After Sorting:");
        for (Student student : array) {
            System.out.println(student);
        }

    }

    public LambdaExpressionsDemo() {
        integerArraySorting();
        stringArraySorting();
        studentArraySorting();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new LambdaExpressionsDemo();
    }
}
