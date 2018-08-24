import java.util.*;

/**
 * MazeSolver attempts to recursively traverse a Maze. The goal is to get from the
 * given starting position to the bottom right, following a path of 1's. Arbitrary
 * constants are used to represent locations in the maze that have been TRIED
 * and that are part of the solution PATH.
 *
 * @author Lewis and Chase
 * @version 4.0
 */
public class MazeSolver
{
    private Maze maze;
    private ArrayList<Position> paths =  new ArrayList<Position>();

    /**
     * Constructor for the MazeSolver class.
     */
    public MazeSolver(Maze maze)
    {
        this.maze = maze;
    }

    /**
     * Attempts to recursively traverse the maze. Inserts special
     * characters indicating locations that have been TRIED and that
     * eventually become part of the solution PATH.
     *
     * @param //row row index of current location
     * @param //column column index of current location
     * @return true if the maze has been solved
     */
    public boolean traverse(int startX, int startY,int endX, int endY)
    {
        if(startX > maze.getRows() || startY > maze.getColumns()){
            throw new ArrayIndexOutOfBoundsException();
        }
        if(endX > maze.getRows() || endY > maze.getColumns()){
            throw new ArrayIndexOutOfBoundsException();
        }
        boolean done = false;
        int row, column,x,y;
        Position pos = new Position();
        Deque<Position> stack = new LinkedList<Position>();
        stack.push(pos);

        while (!(done) && !stack.isEmpty())
        {
            pos = stack.pop();
            maze.tryPosition(pos.getx(),pos.gety());  // this cell has been tried
            if (pos.getx() == endX && pos.gety() == endY)
                done = true;  // the maze is solved
            else
            {
                push_new_pos(pos.getx() - 1,pos.gety(), stack);
                push_new_pos(pos.getx() + 1,pos.gety(), stack);
                push_new_pos(pos.getx(),pos.gety() - 1, stack);
                push_new_pos(pos.getx(),pos.gety() + 1, stack);
            }
        }

        checkPath(startX, startY, endX, endY, maze);

        // Print out coordinates of path from last to first
        ListIterator it = paths.listIterator();
        while(it.hasNext()){
            Position p = (Position)it.next();
            System.out.println("(" + p.getx() + ", " + p.gety() + ")");
        }
        return done;
    }

    /**
     * Push a new attempted move onto the stack
     * @param x represents x coordinate
     * @param y represents y coordinate
     * @param stack the working stack of moves within the grid
     * @return stack of moves within the grid
     */
    private void push_new_pos(int x, int y,
                              Deque<Position> stack)
    {
        Position npos = new Position();
        npos.setx(x);
        npos.sety(y);
        if (maze.validPosition(x,y)){
            stack.push(npos);
        }
    }

    /**
     * Check for the path from beginning to end w/o dead ends and mark
     * @param x represents x coordinate
     * @param y represents y coordinate
     * @param endX represents ending x coordinate
     * @param endY represents ending y coordinate
     * @param maze the working maze that will hold marked path
     * @return boolean true if path was found
     */
    public boolean checkPath(int x, int y, int endX, int endY, Maze maze){
        Position p = new Position();
        p.setx(x);
        p.sety(y);
        if(x == endX && y == endY){
            maze.markPath(x,y);
            paths.add(p);
            return true;
        }
        if(maze.grid[x][y] == 0 || maze.grid[x][y] == 1 || maze.grid[x][y] == 3){
            return false;
        }
        else{
            maze.markPath(x,y);
        }
        if(x+1 < maze.getRows()){
            if(checkPath(x+1, y, endX, endY, maze)){
                maze.markPath(x,y);
                paths.add(p);
                return true;
            }
        }
        if(x-1 >= 0){
            if(checkPath(x-1, y, endX, endY, maze)){
                maze.markPath(x,y);
                paths.add(p);
                return true;
            }
        }
        if(y+1 < maze.getColumns()){
            if(checkPath(x, y+1, endX, endY, maze)){
                maze.markPath(x,y);
                paths.add(p);
                return true;
            }
        }
        if(y-1 >= 0){
            if(checkPath(x, y-1, endX, endY, maze)){
                maze.markPath(x,y);
                paths.add(p);
                return true;
            }
        }
        return false;
    }
}