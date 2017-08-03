import java.util.ArrayList;
public class ArrayMethodsTester
{
    public static void main(String[] args)
    {
        //set up
        String actual="anna";
        String maxim="maxim";
            System.out.println("Vergleich with compareTo: "+maxim.compareTo(actual));
            
            System.out.println("----------------------");
        
        
        String[] animals = {"cat", "ape", "dog", "horse", "zebra"};
        ArrayMethods zoo = new ArrayMethods(animals);
        
        //test replaceWithLargerNeighbor
        zoo.replaceWithLargerNeighbor();
        System.out.println(zoo.toString());
        System.out.println("Expected: [cat, dog, horse, zebra, zebra]");
        //System.out.println("Sorted:" + zoo.isSorted());
          //  System.out.println("Not Sorted");
        
        //test count duplicates;
        String[] animals2 = {"cat", "cat", "dog", "horse", "zebra", "zebra"};
        zoo = new ArrayMethods(animals2);
        //System.out.println(zoo.countDuplicates());
        //System.out.println("Expected: 2");
         System.out.println(zoo.toString());
        //System.out.println("Sorted:" + zoo.isSorted());
          //  System.out.println("Sorted");
       
        String[] animals3 = {"dog", "cat", "cat", "dog", "horse", "zebra", "zebra"};
        zoo = new ArrayMethods(animals3);
        System.out.println(zoo.countDuplicates());
        System.out.println("Expected: 3"); 
        // System.out.println(zoo.toString());
        //System.out.println("Sorted:" + zoo.isSorted());
           // System.out.println("Not Sorted");
        
        String[] animals4 = {"ape", "dog", "xantus",  "zebra", "cat", "yak"};
        zoo = new ArrayMethods(animals4);
        System.out.println(zoo.toString());
                System.out.println("----------------------");
        zoo.xyzToFront();
   
        System.out.println("Expected: [xantus, zebra, yak, ape, dog, cat]");
         System.out.println(zoo.toString());
        //System.out.println("Sorted:" + zoo.isSorted());
          //  System.out.println("Not Sorted");
        
    }
}
