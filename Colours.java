import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Colours extends JButton implements ActionListener {

    byte val = 0;

    // --- Default constructor ---
    public Colours() {
        // Set default background to red and attach ActionLister
        setBackground(Color.red);
    this.addActionListener(this);
    }

    // --- Constructor that takes int argument ---
    public Colours(int i) {
        // Depending on colour set value to i and set background colour
        if(i == 0){
            setBackground(Color.red);
            val = 0;
        }
        else if(i == 1){
            setBackground(Color.blue);
            val = 1;
        }
        else if(i == 2){
            setBackground(Color.green);
            val = 2;
        }
        else if(i == 3){
            setBackground(Color.yellow);
            val = 3;
        }
        else if(i == 4){
            setBackground(Color.pink);
            val = 4;
        }
        else if(i == 5){
            setBackground(Color.magenta);
            val = 5;
        }
    }
    // --- Action Performed for colour JButtons ---
    public void actionPerformed(ActionEvent e)
    {
        // Increase value of val every time button is clicked
        val ++;
        // % 6 means that val is reset to 0 if val is 6
        val %= 6;
        /* If val is;
        0 = set background colour to red
        1 = set background colour to blue
        2 = set background colour to green
        3 = set background colour to yellow
        4 = set background colour to pink
        5 = set background colour to magenta */
        switch(val)
        {
            case 0:
                setBackground(Color.red);
                break;
            case 1:
                setBackground(Color.blue);
                break;
            case 2:
                setBackground(Color.green);
                break;
            case 3:
                setBackground(Color.yellow);
                break;
            case 4:
                setBackground(Color.pink);
                break;
            case 5:
                setBackground(Color.magenta);
                break;
        }
    }
}
