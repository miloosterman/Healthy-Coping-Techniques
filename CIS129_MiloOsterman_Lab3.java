/*******************************************************************************************************************
  * CIS129_MiloOsterman_Lab3.java
  * Author: Milo Osterman
  * CIS 129 - Programming and Problem Solving I
  * Pima Community College
  ******************************************************************************************************************
  * This program is a very simplified, non-persistent database
  * that will record whether a patient has been prescribed a technique or not
  ******************************************************************************************************************/

public class CIS129_MiloOsterman_Lab3 {

    // Constant declaration

    static int SENTINEL_VALUE = 0;
    static int MENU_VALS = 4;
    static String[][] TECHNIQUES = { { "Meditation", "to quiet the busy mind" },
   { "Yoga", "to connect with your physical form" }, { "Acupuncture", "a pain and stress reliever" },
   { "Journaling", "to improve awareness, mindfulness, and reflection" },
   { "Hiking", "a cholesterol-lowering activity" }, { "Reiki", "to focus on your inner energy" },
   { "Positive Self-Talk", "to boost confidence and self-worth" },
   { "Therapeutic Massage", "to address the aches and stiffness" },
   { "Western Herbal Therapy", "soothing teas, balms, and ointments" },
   { "Reflexology", "to release pressure points" },
   { "Breathing Exercises", "to slow down and oxygenate the body" }, { "Laughter", "an antidepressant" } };
    static int TOTAL_TECHNIQUES = TECHNIQUES.length;
    static boolean NAME_ENTERED;

    // Main
    public static void main(String[] args) {

        boolean[] techniquePrescribed = new boolean[TOTAL_TECHNIQUES];
        displayWelcome(null);
        userChoice(techniquePrescribed);

    }

    // Displays the welcome and menu items. If the doctor's name has been entered,
    // use it in message
    public static void displayWelcome(String userName) {

        System.out.println("**************************************************");
        System.err.println("Welcome to the Healthy Coping Techniques Program");
        System.out.println("**************************************************");
        if (userName != null) {
            System.out.println("Menu Options for Dr. " + userName + ":");
        } else {
            System.out.println("Menu Options:");

        }
        System.out.println("1. Sign in as the doctor");
        System.out.println("2. Display the Prescription History");
        System.out.println("3. Prescribe Technique");
        System.out.println("4. Reset Prescription History");
        System.out.println("5. Exit the program");

    }

    // gets the user's menu choice and call function for that menu selection. Checks
    // for valid menu selection
    public static void userChoice(boolean[] techniquePrescribed) {

        int userInput = IR.getInteger("Please enter a menu option: ");
        String userName = null;

        while (userInput != 5) {
            if (userInput <= MENU_VALS && userInput > 0) {
                switch (userInput) {

                    case 1:
                        userName = getSignIn();
                        break;

                    case 2:
                        displayHistory(techniquePrescribed);
                        break;

                    case 3:
                        prescribeTechnique(techniquePrescribed);
                        break;

                    case 4:
                        resetHistory(techniquePrescribed);
                        break;

                }
            } else {
                System.out.println("Invalid entry. Try again");
            }
            displayWelcome(userName);
            userInput = IR.getInteger("Please enter a menu option: ");
        }
        System.out.println("Signing out Dr. " + userName + ".");
        System.out.println("----- Program Terminating -----");
    }

    // Gets user input for sign in string, sets NAME_ENTERED to true
    public static String getSignIn() {

        String userName;

        userName = IR.getString("What is your name, doctor?").trim();
        NAME_ENTERED = true;

        return userName;

    }

    // Validates that user has signed in by checking if NAME_ENTERED is false and
    // gives appropriate error message
    public static boolean validateSignIn() {

        if (NAME_ENTERED == false) {
            System.out.println("Invalid entry. You must sign in before accessing data.");
            System.out.println("Please sign in or exit the program.");

            return false;
        }

        return true;

    }

    // Displays perscription history by going over parallel techniquePrescribed
    // array. If index is true, add message
    public static void displayHistory(boolean[] techniquePrescribed) {

        String techniqueString;
        int maxString = getMaxString();
        int bufferSpace;

        if (validateSignIn()) {
          
          System.out.println("*********************************************");
          System.err.println("      Patient Coping Techniques History      ");
          System.out.println("*********************************************");
          

            for (int i = 0; i < TECHNIQUES.length; i++) {
                techniqueString = "Technique #" + (i + 1) + ": " + TECHNIQUES[i][0] + " ";
                bufferSpace = (maxString * 2) - techniqueString.length();
                System.out.print(techniqueString);
                printSpaces(bufferSpace);
                System.out.print(TECHNIQUES[i][1]);

                if (techniquePrescribed[i]) {
                    System.err.print(" -already prescribed");
                }
                System.out.println();

            }
        }

    }
    
    //Prints spaces between technique & description based on the highest string length of technique.
    public static void printSpaces(int bufferSpace){
      
      for (int i = 0; i <= bufferSpace; i++){
        
        System.out.print(" ");
          
      }
      
    }
      
    
    //Gets the highest length string element out of an array.
    public static int getMaxString(){
      
      int maxStringLength = 0;
      
      for (int i=0; i < TECHNIQUES.length; i++){
        
        if (maxStringLength < TECHNIQUES[i][0].length()){
          
          maxStringLength = TECHNIQUES[i][0].length();
          
        }
      
      }
      
      return maxStringLength;
    
    }

    // valiates user has signed in, gets user input for which technique to
    // prescribe, validates that the doctor hasn't alraedy prescribed it. If they
    // have, message is displayed. Validates that user enters a technique from the
    // list. If pass all checks, set the techniquePrescribed array at the entered
    // index as true
    public static void prescribeTechnique(boolean[] techniquePrescribed) {

        int userInput;

        if (validateSignIn()) {

            userInput = IR.getInteger("Please enter the technique number you wish to prescribe (0 for menu).");

            if (userInput == SENTINEL_VALUE) {

                return;

            } else if (userInput > techniquePrescribed.length || userInput < 0) {

                System.out.println("Invalid entry. Try again.");
                prescribeTechnique(techniquePrescribed);
            } else if (techniquePrescribed[userInput - 1] == true) {

                System.out.print("You have already prescribed " + TECHNIQUES[userInput - 1][0] + ", ");
                System.out.println(TECHNIQUES[userInput - 1][1] + ".");
                prescribeTechnique(techniquePrescribed);


            } else if (userInput < 0 && userInput > techniquePrescribed.length) {

                System.out.println("Invalid entry. Try again.");

                prescribeTechnique(techniquePrescribed);

            } else {

                techniquePrescribed[userInput - 1] = true;
                System.out.print("You have prescribed " + TECHNIQUES[userInput - 1][0] + ", ");
                System.out.println(TECHNIQUES[userInput - 1][1] + ".");

            }
        }

    }

    // validates sign in, sets all techniquePrescribed elements to false
    private static void resetHistory(boolean[] techniquePrescribed) {

        if (validateSignIn()) {

            if (IR.getYorN("Are you sure you want to reset the prescription history? (Y/N)")) {
                for (int i = 0; i < techniquePrescribed.length; i++) {
                    techniquePrescribed[i] = false;
                }

                System.out.println("The prescription history has been reset.");

            }

        }

    }

}
