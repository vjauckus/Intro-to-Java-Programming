//Create a Polygon class. A polygon is a closed shape with lines joining the corner points.
//You will keep the points in an array list. Use object of java.awt.Point for the point.

//Polygon will have as an instance variable an ArrayList of Points to hold the points
//The constructor takes no parameters but initializes the instance variable.  The
//Polygon class also has the following methods:
//    add: adds a Point to the polygon
//    perimeter: returns the perimeter of the polygon
//    draw: draws the polygon by connecting consecutive points and then
//          connecting the last point to the first.
//
//No methods headers or javadoc is provided this time. You get to try your hand at writing
//a class almost from scratch

// Need help starting this question? In the lesson titled 
// "Starting points: Problem Set Questions", go to the
// problem titled "Problem Set 6 - Question 3" for some tips on 
// how to begin.

import java.util.ArrayList;
import java.awt.Point;
public class Polygon
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    
    private ArrayList<Point> points;

    /**
     * Konstruktor f?r Objekte der Klasse Polygon
     */
    public Polygon()
    {
        // Instanzvariable initialisieren
        points=new ArrayList<Point>();
    }
    public void add(Point point){
        points.add(point);
    }
    
    public double perimeter(){
        double perimeter=0;
        double dX=0;
        double dY=0;
        double site=0;
        double siteLast=0;
     
        if(points.size()==2){
          dX=(points.get(0).getX()-points.get(points.size()-1).getX());
              dY=(points.get(0).getY()-points.get(points.size()-1).getY());
            siteLast=Math.sqrt(Math.pow(dX,2)+Math.pow(dY,2));
            perimeter=perimeter+siteLast;
        }
        else if(points.size()>2){
              dX=(points.get(0).getX()-points.get(points.size()-1).getX());
              dY=(points.get(0).getY()-points.get(points.size()-1).getY());
              siteLast=Math.sqrt(Math.pow(dX,2)+Math.pow(dY,2));
           
              for(int i=0; i<points.size()-1;i++){
                 dX=(points.get(i).getX()-points.get(i+1).getX());
                 dY=(points.get(i).getY()-points.get(i+1).getY());
                 site=Math.sqrt(Math.pow(dX,2)+Math.pow(dY,2));
                 perimeter=perimeter+site;
                 System.out.println("perimeter:"+perimeter);
                
                }
         perimeter=perimeter+siteLast;
        }
      
        
        return perimeter;
    }
     public void draw(){
        for(int i=0; i<points.size()-1;i++){
            Line segment=new Line(points.get(i).getX(),points.get(i).getY(),points.get(i+1).getX(),points.get(i+1).getY());
            segment.draw();
            
        };
    
          Line segmentLast=new Line(points.get(0).getX(),points.get(0).getY(),points.get(points.size()-1).getX(),points.get(points.size()-1).getY());
           segmentLast.draw();
            
    }
}
