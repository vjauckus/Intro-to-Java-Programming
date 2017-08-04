// TO-DO
// Fill in the methods in the manager class so
// that when the simulation runs, and simulates the
// story, the assignments are assigned to photographers
// in the correct order, and printed out with photographers
// signatures below them in the order in which they are completed.
import java.util.ArrayList;

public class Manager
{
    // YOUR CODE HERE
    // What does the manager need to remember to do his/her job?
    private ArrayList <Photographer> photographes;
    private ArrayList <Assignment> assignments;
    private ArrayList <FinishedPhoto> finishedPhotos;

    public Manager()
    {
        // YOUR CODE HERE
        // How do you need to initialize the instance variables?
        photographes= new ArrayList<Photographer>();
        assignments=new ArrayList<Assignment>();
        finishedPhotos=new ArrayList <FinishedPhoto>();
    }

    /**
     * Called when it's time to hire a new photographer.
     */
    public void hire(String photographer)
    {
        // YOUR CODE HERE
        // How will you keep track of the photographers you have hired?
        Photographer photograph=new Photographer();
        photograph.saveName(photographer);
        photographes.add(photograph);
    }

    /**
     * Called when it's time for the daily meeting where
     * the highest priority assignments are given to photographers.
     * Each photographer can take one assignment. The highest priority
     * assignment should be given out first and the highest priority
     * assignment should go to the photographer who was hired first.
     */
    public void giveOutAssignments()
    {
        // YOUR CODE HERE
        // Where did you store the photographers and unfinished assignments?
        // Assign the highest priority assignment first to the
        // photographer who was hired first, then the next highest priority
        // assignment to the next photographer.
        for(Photographer photograph: photographes){
            
            //System.out.println("photograph: "+ photograph.getName());
            int maxPriority;
           if( assignments.size()>0){
                Assignment assignmentNext=assignments.get(0);
                 maxPriority=assignments.get(0).getPriority();
                 for(int i=1; i < assignments.size(); i++ ){
                    if(assignments.get(i).getPriority()>maxPriority){
                        
                        maxPriority=assignments.get(i).getPriority();
                        assignmentNext=assignments.get(i);
                    };     
                
                }
                
                 // System.out.println("Auftrag: "+assignmentNext.getDescription()+", priority: "+maxPriority);
                  FinishedPhoto finished=new FinishedPhoto(photograph,assignmentNext);
                  //Save ready Orders
                  finishedPhotos.add(finished);
                  //remove from assignments
                  assignments.remove(assignmentNext);
            }
            else{
                // System.out.println("Keine Aufträge! ");
                break;
            }
            
            
          
            
            
        }
    }

    /**
     * A Customer came up with a new assignment. The manager should
     * add it to the to-do list so that next time it's time to give
     * out assignments, the new assignment will be a possibility.
     */
    public void newAssignment(int priority, String description)
    {
        // YOUR CODE HERE
        // How will you keep track of the unfinished assignments?
        Assignment assignment=new Assignment(priority,description);
        assignments.add(assignment);
    }

    /**
     * It's the end of the story for now. Time to look at all the
     * work the company has done.
     */
    public void checkPortfolio()
    {
        // YOUR CODE HERE
        // You may need to display all the finished work when
        // this method is called, or you may have been displaying
        // the photos as you went. If you have already displayed
        // the photos, there is no need to do anything here.
        int rightMostX=0;
        int rightMostY=0;
        
        for(FinishedPhoto order: finishedPhotos){
           
            Photographer photograph=order.getPhotograph();
            String picture=photograph.takePicture(order.getAssignment().getDescription());
            //Use of Picture Class
            Picture pictureDraw = new Picture(picture);
            //Move in X - Direction
            pictureDraw.translate(rightMostX,0);
            //Draw a photo
            pictureDraw.draw();
            
               //Get Y for a photograph Name
            rightMostY=pictureDraw.getMaxY();
             //Text greeting = new Text(x, y, "Hello");  
            Text photoText=new Text(rightMostX,rightMostY,photograph.getName());
            //Show the Name of photographer under photo
            photoText.draw();
              /** Gets the leftmost x-position of the shape.
                * @return the leftmost x-position for next picture
                  * **/
            rightMostX=pictureDraw.getMaxX();
           
            //System.out.println("Photograph: "+photograph.getName()+", priority: "+order.getAssignment().getPriority()+", Descr: "+order.getAssignment().getDescription()+", photo: "+picture);
        
        }
    }
}
