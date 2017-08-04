// What should an Assignment do?
// How do you want to use it for this program?
// You can put any code related to the Assignment class you
// want in here.

public class Assignment
{
    
    private String description;
    private int priority;
    public Assignment(int priority, String description){
        
        this.priority=priority;
        this.description=description;
    }
    //gives the name of photo
    public String getDescription(){
        return description;
    
    }
    
    public int getPriority(){
        return priority;
    
    }
    
}
