import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Blacks extends JButton implements ActionListener {

    // Initialise bval
    byte bval = 0;

    // --- Create button and attach ActionLister ---
        public Blacks() {
            // Set default background colour to lightGray and attach ActionListener
            setBackground(Color.lightGray);
            this.addActionListener(this);
        }

        // --- Action Performed for black JButtons ---
        public void actionPerformed(ActionEvent e) {
            // Increase value of bval every time button is clicked
            bval++;
            // % 2 means that value is either 1 or 2 as if bval increases to 2, sets bval to 0
            bval %= 2;
            // If bval is 0, set background colour to light grey, if bval is 1 set background to black
            switch (bval) {
                case 0:
                    setBackground(Color.lightGray);
                    break;
                case 1:
                    setBackground(Color.black);
                    break;
            }
        }
    }
