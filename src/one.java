import java.util.ArrayList;
import java.util.LinkedList;

public class one {
   public static void main(String args[]) {
      LinkedList list1 = new LinkedList();
      list1.add("JavaFX");
      list1.add("Hbase");
     
      LinkedList list2 = new LinkedList();
      list2.add("OpenCV");
      list2.add("Mahout");
     
      LinkedList list3 = new LinkedList();
      list3.add("WebGL");
      list3.add("CoffeeScript");
     
      ArrayList <LinkedList> aList = new ArrayList<LinkedList>();
      aList.add(list1);
      aList.add(list2);
      aList.add(list1);
      System.out.println(aList);
      System.out.println(aList.get(1));
      
   }
}