package basics.practice;

import java.util.ArrayList;
import java.util.List;

/*
List Operations using Java8 Features
 */
public class ListOperation {
    public static void main(String args[]) {
        findRecord();
    }
    /*
    Let you have two list, you need to check how many records exists in other list
     */
    static void findRecord(){
        List<String> strList=new ArrayList<>();
        strList.add("A");
        strList.add("B");
        strList.add("C");
        strList.add("D");
        strList.add("E");
        strList.add("F");
        strList.add("G");
        strList.add("H");
        strList.add("I");
        strList.add("J");


        List<String> strList2=new ArrayList<>();
        strList2.add("A");
        strList2.add("B");
        strList2.add("C1");
        strList2.add("D2");
        strList2.add("E2");
        strList2.add("F2");
        strList2.add("G2");
        strList2.add("H2");
        strList2.add("I2");
        strList2.add("J2");

        strList2.forEach(System.out::println);

    }
}
