// Optional
// Does your design use FinishedPhoto objects?
// If so, fill in this class with your code.

public class FinishedPhoto
{
    private Photographer photograph;
    private Assignment assignment;
    
    public  FinishedPhoto(Photographer photograph,Assignment assignment){
        
        this.photograph=photograph;
        this.assignment=assignment;
        
    
    }
    
    public Assignment getAssignment(){
        
        return assignment;
    }
    
      public Photographer getPhotograph(){
        
        return photograph;
    }
}
