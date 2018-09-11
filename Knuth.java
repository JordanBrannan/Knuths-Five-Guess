import java.util.ArrayList;

// ~~~ Clever strategy implemented based off Knuths Five Guess Algorithm ~~~
public class Knuth {

    // Initialise an Array of ArrayLists to store current combinations
    static ArrayList<String>[] s = (ArrayList<String>[]) new ArrayList[10];
    // Initialise iteration count
    static int itCount = 0;
    // Initialise first iteration to suggest correct first guess
    static Boolean first = false;

    // --- Resets all figures for play again feature ---
    public static void reset()
    {
        // Reset iteration count to 0
        itCount = 0;
        // Reset first iteration to false
        first = false;
        // Reset Array of ArrayLists
        s = (ArrayList<String>[]) new ArrayList[10];
    }

    // --- Create first ArrayList of all 1296 combinations ---
    public static void createS()
    {
        // Initialise first ArrayList
        s[0] = new ArrayList<>();
        // Four nested for loops to create the 1296 combinations
        for(int i = 0; i < 6; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                for(int k = 0; k < 6; k++)
                {
                    for (int l = 0; l < 6; l++)
                    {
                        // Add each combination to first ArrayList
                        s[itCount].add(String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l));
                    }
                }
            }
        }
        // For debugging
        // System.out.println(s[itCount].size());
        // System.out.println(s[itCount].get(20));
    }

    // --- Generate computer guess by removing combinations that couldn't work based on previous guess ---
    public static String guess(String input, int w, int b) {
        /* If statement for first guess of game is equal to false then set first boolean to true and return
        string "0011" */
        if (first == false) {
            first = true;
            // For debugging
            //System.out.println(s[itCount].size());
            return "0011";
        }
        // Else proceed with generating next guesses
        else {
            // Initialise new ArrayList
            s[itCount + 1] = new ArrayList<>();
            // Store previous guess as string n
            String n = input;
            // Initialise next guess as string g
            String g = "";
            // Initialise white value counter
            int wVal;
            // Initialise black value counter
            int bVal;
            // For loop to loop through all combinations in previous guesses associated ArrayList
            for (int i = 0; i < s[itCount].size(); i++)
            {
                /* Pass last guess and each combination in previous guesses ArrayList to generate amount of blacks
                and whites in common*/
                wVal = whites(n, s[itCount].get(i));
                bVal = blacks(n, s[itCount].get(i));
                /* If white value and black value are the same as the blacks and whites input from the GUI, add
                to next guesses ArrayList */
                if (wVal == w && bVal == b) {
                    s[itCount + 1].add(s[itCount].get(i));
                    // For debugging
                    //System.out.println(s[itCount].get(i));
                }
            }
            // Add 1 to iteration counter
            itCount++;
            //Initialise new White and Black value to -1
            int nWVal = -1;
            int nBVal = -1;
            // Initialise temp int to store white and black values
            int tNWVal;
            int tNBVal;
            // If the size is equal to one then set g as the only combination
            if (s[itCount].size() == 1) {
                g = s[itCount].get(0);
            }
            // Else proceed with next best guess generator
            else {
                /* If previous guess was first guess and returned either 0 whites or blacks or 1 white and 0 blacks
                then set g as "4455" */
                if (w == 0 && b == 0 && itCount ==1 || w == 1 && b == 0 && itCount == 1)
                {
                    g = "4455";
                }
                // Else proceeed with next best guess generator
                else {
                    // Nested for loops that iterates around new ArrayList and compares each combination with one another
                     for (int j = 0; j < s[itCount].size(); j++) {
                        for (int k = 0; k < s[itCount].size(); k++) {
                            // If two combinations are not the same then proceed
                             if (s[itCount].get(j) != s[itCount].get(k)) {
                                 // For Debugging
                                 // System.out.println(s[itCount].get(j) + "  | " + s[itCount].get(k));
                                 // Find whites and blacks amount between each combination and store temporarily
                                 tNWVal = whites(s[itCount].get(j), s[itCount].get(k));
                                 tNBVal = blacks(s[itCount].get(j), s[itCount].get(k));

                                 // If temp black value is greater than stored blacks value
                                if (tNBVal >= nBVal) {
                                    // Store temp black and white value in store black and white values
                                    nWVal = tNWVal;
                                    nBVal = tNBVal;
                                    // Set g as current combination
                                    g = s[itCount].get(k);
                                }
                                // Else, if temp white value is greater than stored value and blacks value is the same
                                else if (tNWVal > nWVal && tNBVal >= nBVal) {
                                    // Store temp black and white value in store black and white values
                                    nWVal = tNWVal;
                                    nBVal = tNBVal;
                                    // Set g as current combination
                                    g = s[itCount].get(k);
                                }
                            }
                        }
                    }
                    // For debugging
                    //System.out.println(s[itCount].size());
                    //System.out.println(count);
                    //System.out.println(g);

                    // Return g for new guess
                    return g;
                }
            }
            // Return g for new guess
            return g;
        }
    }

    // --- Calculate number of black pegs between two combinations ---
    static int blacks (String one, String two)
    {
        // Initialise black counter
        int countBlack = 0;
        // For loop to iterate through each character of string
        for (int i = 0; i < one.length(); i++)
        {
            // If character at i of string one equals string two, add 1 to black counter
            if (one.charAt(i) == two.charAt(i))
            {
                countBlack++;
            }
        }
        // Return black counter
        return countBlack;
    }

    // --- Calculate number of white pegs between two combinations ---
    static int whites (String one, String two)
    {
        // Initialise boolean to store whether match has been found
        boolean found;
        // Initialise arrays to store each int of string one and two
        int [] oneA = new int[one.length()];
        int [] twoA = new int[one.length()];
        // For loop to iterate through and store each int of strings in arrays
        for (int i = 0; i < one.length(); i++)
        {
            oneA[i] = one.charAt(i);
            twoA[i] = two.charAt(i);
        }
        // Initialise return value
        int val = 0;
        // For loop to store a number for each int in oneA and twoA that match to ensure each is different
        for (int i = 0; i < one.length(); i++)
            if (oneA[i] == twoA[i])
            {
                oneA[i] = 0-i-5;
                twoA[i] = 0-i-10;
            }
        // Nested for loop to count the amount of whites by comparing each int with one another
        for (int i = 0; i < one.length(); i++)
        {
            found = false;
            for (int j = 0; j < one.length() && !found; j++)
            {
                // If oneA[i] is found in twoA[i] then mark as found to stop comparing and add 1 to counter
                if (i != j && oneA[i] == twoA[j])
                {
                    val++;
                    oneA[i] = 0-i-5;
                    twoA[j] = 0-j-10;
                    found = true;
                }
            }

        }
        return val;
    }
}
