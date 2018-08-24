import java.util.*;
import java.io.*;

/**
 * MazeTester uses recursion to determine if a maze can be traversed.
 *
 * @author Lewis and Chase
 * @version 4.0
 */
public class MazeTester
{
    /**
     * Creates a new maze, prints its original form, attempts to
     * solve it, and prints out its final form along with coordinates of final path
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        try{
            Scanner scan = new Scanner(System.in);
            scan.useDelimiter("\\D");
            System.out.print("Enter the name of the file containing the maze: \n");
            String filename = scan.nextLine();

            System.out.print("Enter the x y index you \n want to start at (separated by a comma): \n");
            int startX = scan.nextInt();
            int startY = scan.nextInt();

            System.out.print("Enter the x y index you \n want to end at (separated by a comma): \n");
            int endX = scan.nextInt();
            int endY = scan.nextInt();

            System.out.println("Start position is: x: " + startX + " y: " + startY);
            System.out.println("End position is: x: " + endX + " y: " + endY);

            Maze labyrinth = new Maze(filename);

            System.out.println(labyrinth);

            MazeSolver solver = new MazeSolver(labyrinth);

            if (solver.traverse(startX,startY,endX,endY))
                System.out.println("The maze was successfully traversed!");
            else
                System.out.println("There is no possible path.");

            System.out.println(labyrinth);

        }
        catch(FileNotFoundException | InputMismatchException ex){
            System.out.println("Operation could not be completed.");
        }
        catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("Input variables are out of bounds of the maze.");
        }

    }
}