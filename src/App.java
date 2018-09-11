import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ActionListener {

    // Initialise new panels. An overall panel an then a panel for each section
    private JPanel panel = new JPanel();
    private JPanel north = new JPanel();
    private JPanel south = new JPanel();
    private JPanel east = new JPanel();
    private JPanel west = new JPanel();
    private JPanel centre = new JPanel();
    // Initialise new JButton for Confirm
    private JButton confirm = new JButton("Confirm");
    // Initialise new int for amount of times confirm has been clicked
    private int confcount = 0;
    // Initialise buttons for whites, blakcks, computer generated guesses and hidden combination
    private Colours cButtons[] = new Colours[4];
    private Colours buttons[] = new Colours[4];
    private Whites wbuttons[][] = new Whites[10][4];
    private Blacks bbuttons[][] = new Blacks[10][4];
    // Initialise whitecount and blackcount as well as new computer generated guess
    private int whitecount = 0;
    private int blackcount = 0;
    private String newGuess = "0000";

    public static void main(String[] args) {
        // Start new game
        new App();
    }

    // --- Create App ---
    public App()
    {
        // - Main Panel -

        // Set title of window to "Mastermind"
        super("Mastermind");
        // Set confcount to 0
        confcount = 0;
        // Create new knuth class, reset and create initial ArrayList of 1296 combinations
        new Knuth();
        Knuth.reset();
        Knuth.createS();
        // Set screen size, default close operation and disable screen resize
        setSize(540,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        // Set background colour of GUI to lightgray and split GUI into BorderLayout style
        panel.setBackground(Color.lightGray);
        panel.setLayout(new BorderLayout());

        // - Centre Panel -

        // Set centre panel to 10 rows and 1 column with a padding of 20 on the top, left, bottom and right hand side
        centre.setLayout(new GridLayout(10,1));
        centre.setBorder(BorderFactory.createEmptyBorder(20, 20,
                20, 20));
        // Add centre panel to main panel
        panel.add(centre, BorderLayout.CENTER);

        // - West Panel -

        // Set west panel to 10 rows and 4 columns with a padding of 20 on top, left, bottom and right hand side
        west.setLayout(new GridLayout(10, 4));
        west.setBorder(BorderFactory.createEmptyBorder(20, 20,
                20, 20));
        // Nested for loop to add black buttons in each column of each row and set them to visibility to false
        for (int i = 0; i < 10; i++){
            for(int j = 0; j <4; j++)
            {
                bbuttons[i][j] = new Blacks();
                west.add(bbuttons[i][j]).setVisible(false);
            }
        }
        // Add west panel to main panel
        panel.add(west, BorderLayout.WEST);

        // - East Panel -

        // Set east panel to 10 rows and 4 columns with a padding of 20 on top, left, bottom and right hand side
        east.setLayout(new GridLayout(10, 4));
        east.setBorder(BorderFactory.createEmptyBorder(20, 20,
                20, 20));
        // Nested for loop to add white buttons in each column of each row and set them to visibility to false
        for (int i = 0; i < 10; i++){
            for(int j =0; j<4; j++) {
                wbuttons[i][j] = new Whites();
                east.add(wbuttons[i][j]).setVisible(false);
            }
        }
        // Add east panel to main panel
        panel.add(east, BorderLayout.EAST);

        // - North Panel -

        // Set east panel to 1 rows with 3 columns and a padding of 20 on top
        north.setLayout(new GridLayout(1,3));
        north.setBorder(BorderFactory.createEmptyBorder(20, 0,
                0, 0));
        // Add new JLabels to north panel
        north.add(new JLabel("Blacks", JLabel.CENTER));
        north.add(new JLabel("Computers Guesses", JLabel.CENTER));
        north.add(new JLabel("Whites", JLabel.CENTER));
        // Add north panel to main panel
        panel.add(north, BorderLayout.NORTH);

        // - South Panel -

        // Set south panel to 1 rows with 3 columns and a padding of 20 on top, left, bottom and right
        south.setLayout(new GridLayout(1,3));
        south.setBorder(BorderFactory.createEmptyBorder(20, 20,
                20, 20));
        // Create new JPanel and set GridLayout to 1 row and 1 column with a padding of 40 on the right hand side
        JPanel inplabel = new JPanel(new GridLayout(1,1));
        inplabel.setBorder(BorderFactory.createEmptyBorder(0, 0,
                0, 40));
        // Add JLabel to previous JPanel and add to South panel
        JLabel input = new JLabel("Hidden Pegs:", JLabel.CENTER);
        inplabel.add(input);
        south.add(inplabel);

        // Create new JPanel and set GridLayout to 1 row and 4 columns
        JPanel hidden = new JPanel(new GridLayout(1,4));
        // Add 4 coloured buttons to JPanel
        for (int i = 0; i < 4; i++){
            buttons[i] = new Colours();
            hidden.add(buttons[i]);
        }
        // Add JPanel to South Panel
        south.add(hidden);

        // Create new JPanel and set GridLayout to 1 row and 1 column with a padding of 40 on the left hand side
        JPanel confbutton = new JPanel(new GridLayout(1,1));
        confbutton.setBorder(BorderFactory.createEmptyBorder(0, 40,
                0, 0));
        // Add ActionListener for Confirm Button
        confirm.addActionListener(this);
        // Set prefered size to 20 width and 40 height then add to JPanel
        confirm.setPreferredSize(new Dimension(20, 40));
        confbutton.add(confirm);
        // Add JPanel to South Panel
        south.add(confbutton);
        // Add South Panel to Main Panel
        panel.add(south, BorderLayout.SOUTH);

        // Add Main panel and set to visible
        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        // Find source of Action and set to src
        Object src = e.getSource();
        // If Confirm button was pressed proceed
        if (src.equals(confirm))
        {
            // If first iteration then disable hidden pegs
            if (confcount == 0) {
                buttons[0].setEnabled(false);
                buttons[1].setEnabled(false);
                buttons[2].setEnabled(false);
                buttons[3].setEnabled(false);
            }
            // Else if any other iteration proceed
            else if( confcount >= 1 && confcount <= 10)
            {
                // Set whitecount and blackcount to 0
                whitecount = 0;
                blackcount = 0;
                // For loop that will go through each white black button
                for(int i =0; i <4; i++) {
                    // Disable previous white and black buttons
                    wbuttons[confcount-1][i].setEnabled(false);
                    bbuttons[confcount-1][i].setEnabled(false);
                    // If previous white button(i) is equal to 1, add one to whitecount
                    if(wbuttons[confcount-1][i].wval == 1)
                    {
                        whitecount++;
                    }
                    // If previous black button(i) is equal to 1, add one to blackcount
                    if(bbuttons[confcount-1][i].bval == 1)
                    {
                        blackcount++;
                    }
                }
            }
            // If blackcount and whitecount is over 4 proceed
            if (blackcount+whitecount > 4)
            {
                // Loop through and re-enable previous white and black buttons
                for(int i =0; i <4; i++) {
                    wbuttons[confcount - 1][i].setEnabled(true);
                    bbuttons[confcount - 1][i].setEnabled(true);
                }
                // Display error message
                JOptionPane.showMessageDialog(this, "Incorrect amount of black and white pegs. Try again.");
            }
            // Else proceed
            else {
                // If blackcount is 4, display Computer Guessed Correctly message
                if (blackcount == 4) {
                    // Store answer as n
                    int n = JOptionPane.showConfirmDialog(this,
                            "The computer correctly guessed your hidden code. Play Again?", "Guessed!",
                            JOptionPane.YES_NO_OPTION);
                    // If no option was selected, exit game
                    if (n == JOptionPane.NO_OPTION)
                    {
                        System.exit(0);
                    }
                    // Else dispose and start again
                    else {
                        dispose();
                        new App();
                    }
                }
                // If blackcount is not 4 and on last guess proceed
                if (confcount == 10 && blackcount!= 4)
                {
                    // Store answer as n
                    int n = JOptionPane.showConfirmDialog(this,
                            "The computer got it wrong! Play again?", "Not Guessed!",
                            JOptionPane.YES_NO_OPTION);
                    // If no option was selected, exit game
                    if (n == JOptionPane.NO_OPTION)
                    {
                        System.exit(0);
                    }
                    // Else dispose and start again
                    else {
                        dispose();
                        new App();
                    }

                }
                // Else if any other iteration, proceed
                else if (confcount < 10) {
                    // Loop through next guess blacks and white and set them to visible
                    for (int i = 0; i < 4; i++) {
                        wbuttons[confcount][i].setVisible(true);
                        bbuttons[confcount][i].setVisible(true);
                    }
                    // Create new JPanel with of 1 row and 4 columns
                    JPanel input = new JPanel();
                    input.setLayout(new GridLayout(1, 4));
                    // If first iteration, set Knuth.first to false
                    if (confcount == 0)
                    {
                        Knuth.first = false;
                    }
                    // Generate new guess using Knuth class and store as newGuess
                    newGuess = Knuth.guess(newGuess, whitecount, blackcount);
                    // If newGuess is empty, the player has cheated as there are no valid combinations remaining
                    if(newGuess == "")
                    {
                        // Store answer as n
                        int n = JOptionPane.showConfirmDialog(this,
                                "Cheater! There are no other possible outcomes. Play Again?", "Cheater!",
                                JOptionPane.YES_NO_OPTION);
                        // If no option selected then exit game
                        if (n == JOptionPane.NO_OPTION)
                        {
                            System.exit(0);
                        }
                        // Else dispose and start again
                        else {
                            dispose();
                            new App();
                        }
                    }
                    // Else proceed
                    else {
                        // For debugging
                        //System.out.println(newGuess);

                        // For loop to take newGuess from Knuth class and display guess in GUI
                        for (int j = 0; j < 4; j++) {
                            // Pass each character of string to int
                            char c = newGuess.charAt(j);
                            int n = Character.getNumericValue(c);
                            // Add new colour button with value (colour) set with n
                            cButtons[j] = new Colours(n);
                            // Add to input JPanel and disable
                            input.add(cButtons[j]).setEnabled(false);
                            // For debugging
                            //System.out.println(c);
                        }
                        // Add input JPanel to centre JPanel
                        centre.add(input, BorderLayout.CENTER);
                        // For debugging
                        // System.out.println("it = " + confcount);
                        // Add 1 to confirm clicks count
                        confcount++;
                    }
                }
            }
        }
    }
}