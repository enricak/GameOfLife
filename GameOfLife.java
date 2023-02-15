package conwaygame;
import java.util.ArrayList;
/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many iterations/generations.
 *
 * Rules 
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.

 * @author Seth Kelley 
 * @author Maxwell Goldberg
 */
public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean  DEAD = false;

    private boolean[][] grid;    // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    /**
    * Default Constructor which creates a small 5x5 grid with five alive cells.
    * This variation does not exceed bounds and dies off after four iterations.
    */
    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
    }

    /**
    * Constructor used that will take in values to create a grid with a given number
    * of alive cells
    * @param file is the input file with the initial game pattern formatted as follows:
    * An integer representing the number of grid rows, say r
    * An integer representing the number of grid columns, say c
    * Number of r lines, each containing c true or false values (true denotes an ALIVE cell)
    */
    public GameOfLife (String file) {

        // WRITE YOUR CODE HERE
        StdIn.setFile(file);

        int r = StdIn.readInt();
        int c = StdIn.readInt();

        grid= new boolean [r][c];

        for(int i=0; i<r; i++){
            for(int x=0; x<c; x++){
                
                grid[i][x]= StdIn.readBoolean();
            }
        }
        for(int i=0; i<r; i++){
            for(int x=0; x<c; x++){
                
                if(grid[i][x]== ALIVE){
                    totalAliveCells++;
                }
            }
        }
    }

    /**
     * Returns grid
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid () {
        return grid;
    }
    
    /**
     * Returns totalAliveCells
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells () {
        return totalAliveCells;
    }

    /**
     * Returns the status of the cell at (row,col): ALIVE or DEAD
     * @param row row position of the cell
     * @param col column position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState (int row, int col) {

         if(grid[row][col]==false){
            return DEAD;
        }else{
            return ALIVE; // update this line, provided so that code compiles
        }

    
    }

    /**
     * Returns true if there are any alive cells in the grid
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive () {

        // WRITE YOUR CODE HERE
        int r = grid.length;
        int c = grid[0].length;

        for(int i = 0; i<r; i++){
            for(int x=0; x<c; x++){
                if (grid[i][x]==true){
                    return true;
                }
            }
        }
        return false; // update this line, provided so that code compiles
       
        
    }

    /**
     * Determines the number of alive cells around a given cell.
     * Each cell has 8 neighbor cells which are the cells that are 
     * horizontally, vertically, or diagonally adjacent.
     * 
     * @param col column position of the cell
     * @param row row position of the cell
     * @return neighboringCells, the number of alive cells (at most 8).
     */
    public int numOfAliveNeighbors (int row, int col) {

        // WRITE YOUR CODE HERE
        int countRow = 0;
        
        int countAlive = 0;
  

        for (int i = row - 1; countRow < 3; i++){
            if (i<0){
                i = grid.length-1;
            }
            if (i> grid.length -1){
                i = 0;
            }
            int countCol = 0; 
            for (int j = col-1; countCol<3; j++){
                if (j<0){
                    j=grid[i].length-1;
                }
                if (j>grid[i].length-1){
                    j=0;
                }
                if (grid[i][j] == DEAD || (i == row) && (j == col)){
                    countCol++;
                }
                else{
                    countAlive++;
                    countCol++;
                }
            }
            countRow++;
        }
       return countAlive;
    }

    /**
     * Creates a new grid with the next generation of the current grid using 
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
    public boolean[][] computeNewGrid () {

        // WRITE YOUR CODE HERE
        int r= grid.length;
        int c= grid[0].length;

        boolean[][] newGrid= new boolean[r][c];

        for(int x=0; x<r; x++){
            for(int y=0; y<c;y++){
                int count=0;
                count= numOfAliveNeighbors(x, y);
                
                if (grid[x][y]== ALIVE){
                    if(count==3 || count==2){
                        newGrid[x][y]= ALIVE;
                    } else{
                        newGrid[x][y]= DEAD;
                    }
                } else {
                    if(count==3){
                        newGrid[x][y]= ALIVE;
                    } else{
                      newGrid[x][y]= DEAD;
                    }
                }
            }
        }

      


        return newGrid;// update this line, provided so that code compiles
    }

    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid().
     * 
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration () {

        // WRITE YOUR CODE HERE  
        grid=computeNewGrid();

        int r2= grid.length;
        int c2= grid[0].length;

        totalAliveCells=0;
       

        for(int x=0; x<r2; x++){
            for(int y=0; y<c2;y++){

                if(grid[x][y]==ALIVE){
                    totalAliveCells++;
                }
            }
        }

        
          

    }

    /**
     * Updates the current grid with the grid computed after multiple (n) generations. 
     * @param n number of iterations that the grid will go through to compute a new grid
     */
    public void nextGeneration (int n) {

        // WRITE YOUR CODE HERE
        for(int number=0; number<n;number++){

            grid= computeNewGrid();
        }

        int r2= grid.length;
        int c2= grid[0].length;

        totalAliveCells=0;
        
        for(int x=0; x<r2; x++){
            for(int y=0; y<c2;y++){

                if(grid[x][y]==ALIVE){
                    totalAliveCells++;
                }
            }
        }     
    }

    /**
     * Determines the number of separate cell communities in the grid
     * @return the number of communities in the grid, communities can be formed from edges
     */
    public int numOfCommunities() {
       
       
        ArrayList<Integer> roots = new ArrayList<>();// WRITE YOUR CODE HERE
        WeightedQuickUnionUF comm = new WeightedQuickUnionUF(grid.length, grid[0].length);
       
        for (int row=0; row<grid[0].length; row++){
            for (int col=0; col<grid[0].length; col++){
              
                if(grid[row][col] == ALIVE){
                    int countRow = 0;
                   
                    for (int i = row-1; countRow <3; i++){
                        if (i<0){
                            i = grid.length-1;
                        }
                        if (i > grid.length-1){
                            i=0;
                        }
                        int countCol = 0;
                     
                        for (int j = col-1; countCol<3; j++){
                            if (j<0){
                                j = grid[i].length-1;
                            }
                            if (j> grid[i].length-1){
                                j = 0;
                            }
                            if (grid [i][j]==DEAD || ((i == row) && (j == col))){
                                countCol++;
                            }
                            else{
                                comm.union(i, j, row, col);
                                int root = (comm.find(i, j));
                                if (!roots.contains(root)){
                                    roots.add(root);
                                }
                                countCol++;
                            }
                        }
                        countRow++;
                    }
                }
            }
        }
        return roots.size(); // update this line, provided so that code compiles
    }
}