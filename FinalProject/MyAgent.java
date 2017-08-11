import java.util.Random;
import java.util.ArrayList;

public class MyAgent extends Agent
{
    Random r;
    String myName="";
   
    /**
     * Constructs a new agent, giving it the game and telling it whether it is Red or Yellow.
     * 
     * @param game The game the agent will be playing.
     * @param iAmRed True if the agent is Red, False if the agent is Yellow.
     */
    public MyAgent(Connect4Game game, boolean iAmRed)
    {
        super(game, iAmRed);
        r = new Random();
        
        if(iAmRed){
            myName="Fox";
        }
        else{
            myName="Chick";
        }
    }

    /**
     * The move method is run every time it is this agent's turn in the game. You may assume that
     * when move() is called, the game has at least one open slot for a token, and the game has not
     * already been won.
     * 
     * By the end of the move method, the agent should have placed one token into the game at some
     * point.
     * 
     * After the move() method is called, the game engine will check to make sure the move was
     * valid. A move might be invalid if:
     * - No token was place into the game.
     * - More than one token was placed into the game.
     * - A previous token was removed from the game.
     * - The color of a previous token was changed.
     * - There are empty spaces below where the token was placed.
     * 
     * If an invalid move is made, the game engine will announce it and the game will be ended.
     * 
     */
    public void move()
    {
            int columnTotal = myGame.getColumnCount();
            int bestMove = randomMove(); // If there is no better move, the agent would make a random move.
            int bestMoveOld=bestMove;
            int columnWin;
            int theyWin;
            int checkPotential;
            int checkLastRow;
            char color;
             int i=0;
            if(iAmRed){
                 color = 'Y';
             }
            else{
                    color = 'R';
             }
            while( i< columnTotal ){
        
               // Find the top empty slot in the column i
               // If the column is full, lowestEmptySlot will be -1
                if(getLowestEmptyIndex(myGame.getColumn(i)) == -1){
                    
                    i++;
                }
                else{
                    // if I can win, I do it first
                    columnWin = iCanWin (i);
                    theyWin = theyCanWin(i);
                    checkPotential = iCheckPotential(color);
                 
                    
                    if(columnWin > -1){
                        
                        //System.out.println("I can Win!");
                        bestMove = columnWin;
                        moveOnColumn(bestMove);
                        return;
                    }
                   
                    else if(theyWin > -1){
                        //then I check for dangerous combination
                         //System.out.println("Dangerous, the other can Win!");
                         bestMove = theyWin;
                         //System.out.println("Move on Column! "+bestMove);
                         moveOnColumn(bestMove);
                         return;
                    }
                    else if(theyWin == -1 && checkPotential > -1){
                         // System.out.println("Check, if the Potential is good!");
                        // only search for next potential dangerous possibilities
                          bestMove = checkPotential;
                          //System.out.println("Move on Column! "+bestMove);
                          moveOnColumn(bestMove);
                          return;
                         
                    } 
                    else{
                        bestMove=i;
                        moveOnColumn(bestMove);
                        return;
                    }
                   
                }
            }
            
            if(bestMove == bestMoveOld){
            }
            moveOnColumn(bestMove);
        
    }

    /**
     * Drops a token into a particular column so that it will fall to the bottom of the column.
     * If the column is already full, nothing will change.
     * 
     * @param columnNumber The column into which to drop the token.
     */
    public void moveOnColumn(int columnNumber)
    {
        int lowestEmptySlotIndex = getLowestEmptyIndex(myGame.getColumn(columnNumber));   // Find the top empty slot in the column
                                                                                                  // If the column is full, lowestEmptySlot will be -1
        if (lowestEmptySlotIndex > -1)  // if the column is not full
        {
            Connect4Slot lowestEmptySlot = myGame.getColumn(columnNumber).getSlot(lowestEmptySlotIndex);  // get the slot in this column at this index
            if (iAmRed) // If the current agent is the Red player...
            {
                lowestEmptySlot.addRed(); // Place a red token into the empty slot
            }
            else // If the current agent is the Yellow player (not the Red player)...
            {
                lowestEmptySlot.addYellow(); // Place a yellow token into the empty slot
            }
        }
    }

    /**
     * Returns the index of the top empty slot in a particular column.
     * 
     * @param column The column to check.
     * @return the index of the top empty slot in a particular column; -1 if the column is already full.
     */
    public int getLowestEmptyIndex(Connect4Column column) {
        int lowestEmptySlot = -1;
        for  (int i = 0; i < column.getRowCount(); i++)
        {
            if (!column.getSlot(i).getIsFilled())
            {
                lowestEmptySlot = i;
            }
        }
        return lowestEmptySlot;
    }

    /**
     * Returns a random valid move. If your agent doesn't know what to do, making a random move
     * can allow the game to go on anyway.
     * 
     * @return a random valid move.
     */
    public int randomMove()
    {
        int i = r.nextInt(myGame.getColumnCount());
        while (getLowestEmptyIndex(myGame.getColumn(i)) == -1)
        {
            i = r.nextInt(myGame.getColumnCount());
        }
        return i;
    }

    /**
     * Returns the column that would allow the agent to win.
     *
     * @return the column that would allow the agent to win.
     */
    public int iCanWin(int columnEmpty)
    {
        int column=-1;
        int horizontalWin;
        int diagonalLeftDownUpper;
        int diagonalLeftUpperDown;
        int threeInOneColumn;
        
       //tree slots with the same color inside of 1 column was found
       if (iAmRed) {
           
             threeInOneColumn = iThreeInOneColumn(columnEmpty, 'R');
             horizontalWin = iHorizontalWin('R');
             diagonalLeftDownUpper = iDiagonalLeftDownUpper('R');
             diagonalLeftUpperDown = iDiagonalLeftUpperDown('R');
              
             if ( horizontalWin > -1){
                   
                   column = horizontalWin;
                   return column;
               
                } 
   
            else if(threeInOneColumn > -1){
                
                column = threeInOneColumn;
               return column;
             }
           
             else if(  diagonalLeftDownUpper > -1){
                column = diagonalLeftDownUpper;
                }
                
             else if( diagonalLeftUpperDown > -1){
                column = diagonalLeftUpperDown;
                }
        }
        else {
                // I am Yellow
              threeInOneColumn = iThreeInOneColumn(columnEmpty, 'Y');
              horizontalWin = iHorizontalWin('Y');
              diagonalLeftUpperDown = iDiagonalLeftUpperDown('Y');
              diagonalLeftDownUpper = iDiagonalLeftDownUpper('Y');
              
                if ( horizontalWin > -1){
                   
                   column = horizontalWin;
                   return column;
               
                }
                else if (threeInOneColumn > -1) {
                 
                  column = threeInOneColumn;
                  return column;
                  
                }
             
                else if ( diagonalLeftUpperDown > -1){
                   
                    column = diagonalLeftUpperDown;
                    return column;
               
                }
                
                else if ( diagonalLeftDownUpper > -1){
                    
                    column= diagonalLeftDownUpper;
                    return column;
                }
        }
        
       return column;
    }
    
       /**
     * Returns the column that would give the agent potential to win.
     *
     * @return the column that would allow the agent to win.
     */
    public int iCheckPotential(char color)
    {
         int column = -1;   
         int horizontalPotential = iHorizontalPotential(color);
         
         if(horizontalPotential > -1){
             
           column = horizontalPotential;
         }
         
        return column;
       
    }
    
    
      /**
     * Returns the column where 3 red slots + 1 empty slot in one column available, that would allow the agent to win.
     * 
     * @return the column that would allow the agent to win.
     */
    public int iThreeInOneColumn(int columnEmpty, char color)
    {
        int column = -1;
        char[][] board = myGame.getBoardMatrix();
        
            for(int i=columnEmpty; i< myGame.getColumnCount();i++){
                
                    for(int row=0; row< myGame.getRowCount() - 3;row++){
                        
                             //for 3 red slots  + 1 empty in column
                             if(board[row][i] == 'B'){
                                  
                                      if(board[row+1][i] == color && board[row+2][i] == board[row+1][i]  && board[row+3][i] == board[row+1][i]){
       
                                          column = i;
                                         // System.out.println("Found: 3 "+ color +" In One Column: " + column);
                                          return column;
                                             
                                     }
                             }
              
                    }
        
        }
        return column;
     }
     
     /**
     * Returns the potential column where
     * 3 Red slots horizontal + 1 empty slot or
     * diagonal Left Upper to Down potential combination
     * in next column available, that would allow the agent to win.
     * @return the column that would allow the agent to win.
     */
    public int iHorizontalPotential(char color)
    {
         int column = -1;
         char[][] board = myGame.getBoardMatrix();
         ArrayList<Integer> setPotential = new ArrayList<Integer>();
         
         for(int i=0; i< myGame.getColumnCount() - 3;i++){
            
            for(int row=0; row< myGame.getRowCount();row++){
                
                if(board[row][i] == color){
                    //diagonal
                    if( row < myGame.getRowCount() - 3){
                            //last row
                             if(row == myGame.getRowCount() - 4){
                                 
                                 if(board[row + 1][i + 1] == color && board[row + 2][i + 2] == 'B' && board[row + 3][i + 2] != 'B' && board[row + 3][i + 3] == 'B'){
                                     
                                     column = i + 2;
                                     //System.out.println("Found potential: diagonal Left Upper 2 " + color +" left + 2 empty in LAST row! In column: " + column);
                                     setPotential.add(column);
                                    
                                    }
                                }
                                 //not last row
                              else {
                                    
                                   if(board[row + 1][i + 1] == color && board[row + 2][i + 2] == 'B' && board[row + 3][i + 2] !='B' && board[row + 3][i + 3] == 'B' && board[row + 4][i + 3] !='B'){
                                          column = i + 2;
                                          //System.out.println("Found potential: diagonal Left Upper 2 " + color + " left + 2 empty! In column: " + column);
                                           setPotential.add(column);
                                          
                                    }
                                }
                    }
                     //only for last row horizontal
                      if( row == myGame.getRowCount()-1 && board[row][i + 1] == 'B'){
                                 
                                  column = i + 1;
                                 // System.out.println("Fill LAST ROW first: Found 1 "  + color +" + 1 empty In column: " + column);
                                  setPotential.add(column);              
                            
                        }
                     
                      if( board[row][i + 1] == 'B' && board[row][i + 2] == 'B' && board[row][i + 3] == color){
                              // not last row horizontal
                           if(row < myGame.getRowCount()-1){
                               //slots in row under current row are filled
                               if(board[row + 1][i + 1] != 'B' && board[row + 1][i + 2] != 'B'){
                                  
                                  column = i + 1;
                                  //System.out.println("Found potential: 1 " + color +" left + 2 empty + 1 "+color+" right! In column: " + column);
                                  setPotential.add(column);
                                  
                                }
                            }
                            //last row horizontal
                            else if(row == myGame.getRowCount()-1){
                                 column = i + 1;
                                  //System.out.println("Found potential: 1 "  + color +" left + 2 empty + 1 "  + color +" right in LAST ROW! In column: " + column);
                                  setPotential.add(column);
                                 
                            }
                    
                    }
                    else if(board[row][i + 1] == color && board[row][i + 2] == 'B' && board[row][i + 3] == 'B'){
                               // not last row horizontal
                       if(row < myGame.getRowCount()-1){
                           //slots in row under current row are filled
                           if(board[row + 1][i + 2] != 'B' && board[row + 1][i + 3] != 'B'){
                              
                              column = i + 2;
                              //System.out.println("Found potential: 2 " + color +" left + 2 empty right! In column: " + column);
                              setPotential.add(column);
                              
                            }
                        }
                        //last row horizontal
                        else if(row == myGame.getRowCount()-1){
                             column = i + 2;
                             //System.out.println("Found potential: 2 "  + color +" left + 2 empty right in LAST ROW! In column: " + column);
                             setPotential.add(column);
                            
                        }
                    
                    }
                    
                }
                 else if(board[row][i] == 'B'){
                     //diagonal
                      if(row < myGame.getRowCount() - 3 ){
                          
                            if(board[row + 1][i] != 'B' && board[row + 1][i + 1] == 'B' && board[row + 2][i + 1] != 'B' && board[row + 2][i + 2] == color && board[row + 3][i + 3] == color){
                                    
                               column = i;
                               //System.out.println("Found potential: diagonal  Left Upper 2 empty + 2 " + color +" right! In column: " + column);
                               setPotential.add(column);
                               // return column;
                            }
                        
                    }
                     //not last row horizontal
                     if(row < myGame.getRowCount() - 1){
                         
                        if(board[row][i + 1] == 'B' && board[row+1][i] != 'B' && board[row+1][i + 1] != 'B' && board[row][i + 2] == color && board[row][i + 3] == color){
                             column = i;
                              //System.out.println("Found potential: 2 empty + 2 " + color +" right! In column: " + column);
                              setPotential.add(column);
                        }
                      }
                        //last row horizontal
                        else if(row == myGame.getRowCount()-1){
                            
                           if(board[row][i + 1] == 'B' && board[row][i + 2] == color && board[row][i + 3] == color){
                              column = i;
                              //System.out.println("Found potential: 2 empty + 2 " + color +" right! In column: " + column);
                              setPotential.add(column);
                        }
                        
                        }
                     
                    
                    }
            }
        }
    
        if( setPotential.size() > 0){
             
            column = getMiddlestColumn(setPotential);
            return column;
        }
        else{
            return -1;
        }
       
    }
    
     /**
     * Returns the column where 
     * 3 Red slots horizontal + 1 empty slot in next column available, that would allow the agent to win.
     * @return the column that would allow the agent to win.
     */
    public int iHorizontalWin(char color)
    {
         int column = -1;
         char[][] board = myGame.getBoardMatrix();
         ArrayList<Integer> setPotential = new ArrayList<Integer>();
         
         for(int i=0; i< myGame.getColumnCount(); i++){
            
            for(int row=0; row< myGame.getRowCount(); row++){
                
                if(board[row][i] == color && (i< myGame.getColumnCount() - 3)){
                 
                    if( board[row][i + 1] == color  && board[row][i + 2] == 'B' && board[row][i + 3] == color){
                        // not last row
                       if(row < myGame.getRowCount() - 1){
                           
                           if(board[row + 1][i + 2] != 'B'){
                              
                              column = i + 2;
                             // System.out.println("Found: 2 " + color +" left + 1 empty + 1 "+color+" right! In column: " + column);
                              setPotential.add(column);
                             
                            }
                        }
                        //last row
                        else if(row == myGame.getRowCount() - 1){
                             column = i + 2;
                             //System.out.println("Found: 2 "  + color +" left + 1 empty + 1 "  + color +" right in LAST ROW! In column: " + column);
                             setPotential.add(column);
                             
                        }
                    }
                    
                    else if(board[row][i + 1] == 'B' && board[row][i + 2] == color && board[row][i + 3] == color ){
                        
                        // not last row
                       if(row < myGame.getRowCount() - 1){
                           
                           if(board[row + 1][i + 1] != 'B'){
                              
                              column = i + 1;
                              //System.out.println("Found: 1 "  + color + " left + 1 empty + 2 "  + color +" right! In column: " + column);
                              setPotential.add(column);
                            
                            }
                        }
                        //last row
                        else if(row == myGame.getRowCount() - 1){
                            
                             column = i + 1;
                             //System.out.println("Found: 1 " + color + " left + 1 empty + 2 " + color +" right in LAST ROW! In column: " + column);
                             setPotential.add(column);
                             
                        }
                    }
                   
                    else if(board[row][i + 1] == color && board[row][i + 2] == color && board[row][i + 3] == 'B'){
                        
                        // not last row
                       if(row < myGame.getRowCount()-1){
                           
                           if(board[row + 1][i + 3] != 'B'){
                              
                              column = i + 3;
                              //System.out.println("Found: 3 " + color +" + 1 empty ! In column: " + column);
                              setPotential.add(column);
                              
                            }
                        }
                        //last row
                        else if(row == myGame.getRowCount() - 1){
                            
                             column = i + 3;
                              //System.out.println("Found: 3 " + color + "+ 1 empty in LAST ROW! In column: " + column);
                             setPotential.add(column);
                        
                        }
                    }
                }
                
                else if(board[row][i] == 'B' && i< myGame.getColumnCount() - 3){
                    
                  
                  if(board[row][i + 1] == color && board[row][i + 2] == color && board[row][i + 3] == color){  
                        // not last row
                       if( row < myGame.getRowCount() - 1 ){
                           
                           if( board[row + 1][i] != 'B'){
                              
                              column = i;
                              //System.out.println("Found: 1 empty + 3 " + color + " right! In column: " + column);
                              setPotential.add(column);
                              
                            }
                        }
                        //last row
                        else if(row == myGame.getRowCount() - 1){
                            
                              column = i;
                              //System.out.println("Found: 1 empty + 3 " + color +" right in LAST ROW! In column: " + column);
                              setPotential.add(column);
                            
                        }
                    }
                
                }
               
            }
        }
        
        if( setPotential.size() > 0){
             
            column = getMiddlestColumn(setPotential);
            return column;
        }
        else{
            return -1;
        }
     
    }
    
    
     /**
     * Returns the column where 3 Red slots on diagonal + 1 empty slot in next column available, that would allow the agent to win.
     * @return the column that would allow the agent to win.
     */
    public int iDiagonalLeftUpperDown(char color)
    {
            int column = -1;
            char[][] board = myGame.getBoardMatrix();
            ArrayList<Integer> setPotential = new ArrayList<Integer>();
        
            for (int i = 0; i < myGame.getColumnCount() - 3; i++)
            {
                for (int row = 0; row < myGame.getRowCount() - 3; row++)
                {
                    if( board[row][i] == color )
                       {
                          //from left upper down right
                             if(board[row + 1][i + 1] == color && board[row + 2][i + 2] == 'B' && board[row + 3][i + 2] != 'B' && board[row + 3][i + 3] == color){
                                       
                                  column = i + 2;
                                  //System.out.println("Found: diagonal Left Upper 2 " + color +" left + 1 empty + 1 " + color +" right! In column: " + column);
                                  setPotential.add(column);
                             
                                }                          
                               else if(board[row + 1][i + 1] =='B' && board[row + 2][i + 1] != 'B' && board[row + 2][i + 2] == color && board[row + 3][i + 3] == color){
                                       
                                  column = i + 1 ;
                                  //System.out.println("Found: diagonal Left Upper 1 " + color +" left + 1 empty + 2 " + color +" right! In column: " + column);
                                  setPotential.add(column);
                                  
                                }
                                //last row
                             if(row == myGame.getRowCount() - 4){
                                 
                                  if(board[row + 1][i + 1] == color && board[row + 2][i + 2] == color && board[row + 3][i + 3] == 'B'){
                                   
                                          column = i + 3;
                                          //System.out.println("Found: diagonal Left Upper 3 " + color +" left + 1 empty in LAST row! In column: " + column);
                                          setPotential.add(column);
                                         
                                  }
                                 
                                }
                                 //not last row
                              else {
                                    
                                  if(board[row + 1][i + 1] == color && board[row + 2][i + 2] == color && board[row + 3][i + 3] == 'B' && board[row + 4][i + 3] !='B'){
                                   
                                          column = i + 3;
                                          //System.out.println("Found: diagonal Left Upper 3 " + color + " left + 1 empty! In column: " + column);
                                          setPotential.add(column);
                                          
                                   }
                             
                                }
                             
                        }
                     else if( board[row][i] == 'B' )
                      {
                               
                         if( board[row + 1][i] != 'B' && board[row + 1][i + 1] == color && board[row + 2][i + 2] == color && board[row + 3][i + 3] == color ){
                                           
                                    column = i;
                                    //System.out.println("Found: diagonal Left Upper 1 empty + 3 " + color +" right! In column: " + column);
                                    setPotential.add(column);
                                      
                          }
                            
                        }
                            
                 }
                   
                }
          
             if( setPotential.size() > 0){
             
                column = getMiddlestColumn(setPotential);
                return column;
             }
             else{
                return -1;
             }
           
    }
    
    
     /**
     * Returns the column where 3 Red slots on diagonal + 1 empty slot in next column available, that would allow the agent to win.
     * @return the column that would allow the agent to win.
     */
    public int iDiagonalLeftDownUpper( char color)
    {
            int column = -1;
            char[][] board = myGame.getBoardMatrix();
            ArrayList<Integer> setPotential = new ArrayList<Integer>();
        
            for (int i = 3; i < myGame.getColumnCount(); i++)
            {
                for (int row = 0; row < myGame.getRowCount() - 3; row++)
                {
                    if(board[row][i] == color)
                    {
                        //from upper left to down right
                        //not last row
                        if (row + 4 < myGame.getRowCount()){
                            
                             if(board[row + 1][i - 1] == color && board[row + 2][i - 2] == color && board[row + 3][i - 3] =='B' && board[row + 4][i - 3] !='B' ){
                                    
                                column= i - 3;
                                //System.out.println("Found: diagonal Left Down 1 empty + 3 " + color +" right! In column: " + column);
                                setPotential.add(column);
                                
                            }
                          
                        }
                        //till Last row
                         else if (row == myGame.getRowCount() - 4){
                            
                             if(board[row + 1][i - 1] == color && board[row + 2][i - 2] == color && board[row + 3][i - 3] == 'B' ){
                                    
                                column= i - 3;
                                //System.out.println("Found: diagonal Left Down 1 empty + 3 " + color +" right in Last Row! In column: " + column);
                                setPotential.add(column);
                                
                            }
                         
                         }
                        
                            
                         if(board[row + 1][i - 1] == color && board[row + 2][i - 2] == 'B' && board[row + 3][i - 2] != 'B' && board[row + 3][i - 3] == color ){
                                    
                                      column = i - 2;
                                      //System.out.println("Found: diagonal Left Down 1 " + color +" + 1 empty + 2 " + color +" right! In column: " + column);
                                      setPotential.add(column);
                                     
                            }
                            
                         else if(board[row + 1][i - 1] == 'B' && board[row + 2][i - 1] != 'B' && board[row + 2][i - 2] == color && board[row + 3][i - 3] == color ){
                                    
                                      column = i - 1;
                                      //System.out.println("Found: diagonal Left Down 2 " + color +" + 1 empty + 1 " + color +" right! In column: " + column);
                                      setPotential.add(column);
                                     
                            }
                   
                     }
                     else if(board[row][i] == 'B'){
                        
                           if(board[row + 1][i] != 'B' && board[row + 1][i - 1] == color && board[row + 2][i - 2] == color && board[row + 3][i - 3] == color ){
                                    
                                      column= i;
                                      //System.out.println("Found: diagonal Left Down 3 " + color +" + 1 empty ! In column: " + column);
                                      setPotential.add(column);
                                     
                            }
                       
                      }
                   
                    }
                }
                
               if( setPotential.size() > 0){
                 
                    column = getMiddlestColumn(setPotential);
                    return column;
                 }
                 else{
                    return -1;
                 }  
     
    }
    /**
     * Returns the column that would allow the opponent to win.
     *
     * @return the column that would allow the opponent to win.
     */
    public int theyCanWin(int columnEmpty)
    {
        int dangerColumn = -1;
        int horizontalWin;
        int diagonalLeftDownUpper;
        int diagonalLeftUpperDown;
        int threeInOneColumn;
        ArrayList<Integer> dangerPotential = new ArrayList<Integer>();
        
          //Dangerous, the opponent can Win!
          if (iAmRed) {
           
              threeInOneColumn = iThreeInOneColumn(columnEmpty, 'Y');
              horizontalWin = iHorizontalWin('Y');
              diagonalLeftUpperDown= iDiagonalLeftUpperDown('Y');
              diagonalLeftDownUpper = iDiagonalLeftDownUpper('Y');
            
                if (threeInOneColumn > -1 ){
                 
                  dangerColumn = threeInOneColumn;
                  dangerPotential.add(dangerColumn);
               
                }
               else if ( horizontalWin > -1 ){
                   dangerColumn = horizontalWin;
                   dangerPotential.add(dangerColumn);
                }  
                else if( diagonalLeftUpperDown > -1 ){
                    
                   dangerColumn = diagonalLeftUpperDown;
                   dangerPotential.add(dangerColumn);
                }
                else if(diagonalLeftDownUpper > -1 ){
                    
                   dangerColumn = diagonalLeftDownUpper;
                   dangerPotential.add(dangerColumn);
                }
            
            }
            else {
                    // I am Yellow
                    threeInOneColumn=iThreeInOneColumn(columnEmpty, 'R');
                    horizontalWin=iHorizontalWin('R');
                    diagonalLeftDownUpper = iDiagonalLeftDownUpper('R');
                    diagonalLeftUpperDown= iDiagonalLeftUpperDown('R');
                
                if ( threeInOneColumn > -1 ){
                    
                     dangerColumn = threeInOneColumn;
                     dangerPotential.add(dangerColumn);
                    
                 }
                 else if ( horizontalWin > -1 ){
                     dangerColumn = horizontalWin;
                     dangerPotential.add(dangerColumn);
                 } 
                 
                 else if( diagonalLeftDownUpper > -1 ){
                     
                     dangerColumn =  diagonalLeftDownUpper;
                     dangerPotential.add(dangerColumn);
                    }
                  else if(diagonalLeftUpperDown > -1){
                    
                      dangerColumn = diagonalLeftUpperDown;
                      dangerPotential.add(dangerColumn);
                    }
                 
        }
        
        if( dangerPotential.size() == 0){
            return -1;
        }
        else{
            
            //find out the column that is near to middle of matrix
            dangerColumn = getMiddlestColumn(dangerPotential);
            return dangerColumn;
        }
       
    }
    
     /**
     * Returns the column that located so near as possible to center of the board.
     *
     * @return the middlest column
     */
    public int getMiddlestColumn( ArrayList<Integer> dangerSet)
    {
       
        int middlestColumn = dangerSet.get(0);
        
        if(dangerSet.size() > 1){
            
          for(int i = 1; i < dangerSet.size(); i++ ){
              
              if( Math.abs(myGame.getColumnCount()/2 - dangerSet.get(i) ) < Math.abs(myGame.getColumnCount()/2 - middlestColumn )) {
                
                  middlestColumn = dangerSet.get(i);
                }
          }
        }
        
        //  System.out.println("I am in ArrayList: Search for Middlest");
        return middlestColumn;
    }
    
    /**
     * Returns the name of this agent.
     *
     * @return the agent's name
     */
    public String getName()
    {
        return myName;
    }
}
