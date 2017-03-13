// CS_205 Go Fish


import java.util.*;

/**
 * UserInterface class
 * Handles interaction between user and game
 * @author Sean Kates
 */
public class UserInterface
{
   /**
   print function that takes input and prints it to the command line
   @param textToPrint input from GameManager that is to be printed.
   */
   public void print( String textToPrint )
   {
      System.out.println( textToPrint );
   }
   
   /**
   prompt function that takes text and prints it, then user is prompted for a response.
   The function gets a string array of options to compare input to
   @param textToPrint input from GameManager that is to be printed.
   @param availableOptions user has to give input that is in this string array
   @return userInput input from the user
   */
   public String prompt( String textToPrint, LinkedList<String> availableOptions )
   {     
      // Print out the param text and ask for input
      Scanner keyboard = new Scanner( System.in );
      System.out.print( textToPrint );
      String userInput = keyboard.nextLine();
      
      // While loop to validate the input with approved options
      while (!availableOptions.contains( userInput ))
         {
            System.out.println( "Sorry, you must choose from one of the given options" );
            System.out.print( textToPrint );
            userInput = keyboard.nextLine();
         }
      return userInput;
   }
   
   /**
   Unit Testing of the user interface class
   */
//   public static void main(String[] args)
//   {
//      // Initializing a UserInterface class for testing
//      UserInterface uiDemo = new UserInterface();
//      
//      // Testing of the prompt function, printing out the input recieved from prompt
//      String [] com_type_options = {"simple","devious","advanced"};
//		String type_of_com = uiDemo.prompt("What type of computer do you wish to play against? (simple, advanced, or devious)",com_type_options);
//      System.out.println( "You have chosen: " + type_of_com );
//      
//      // Testing of the print function
//      uiDemo.print( "Hey look the print function works no way so sweet" );
//      
//   }
}