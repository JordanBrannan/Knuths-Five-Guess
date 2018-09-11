import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Whites extends JButton implements ActionListener {

    // Initialise wval
    byte wval = 0;

        // --- Create button and attach ActionLister ---
        public Whites() {
            // Set default background colour to lightGray and attach ActionListener
            setBackground(Color.lightGray);
            this.addActionListener(this);
        }

        // --- Action Performed for white JButtons ---
        public void actionPerformed(ActionEvent e) {
            // Increase value of wval every time button is clicked
            wval++;
            // % 2 means that value is either 1 or 2 as if wval increases to 2, sets wval to 0
            wval %= 2;
            // If wval is 0, set background colour to light grey, if wval is 1 set background to white
            switch (wval) {
                case 0:
                    setBackground(Color.lightGray);
                    break;
                case 1:
                    setBackground(Color.white);
                    break;

            }
        }
    }
