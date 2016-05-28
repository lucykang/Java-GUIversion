
package GUI;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * Final Assignment (Group assignment)
 * @version 2016-04-20
 * @author Haeyeon Kang, Cindy Diaz
 */

public class TopPanel extends JPanel{
    
    //component
    private JLabel greetingLabel;
    
    public TopPanel(){
        //initialise component
        greetingLabel = new JLabel(" Lucindy Company Management System ");
        greetingLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        
        //add label to the panel
        add(greetingLabel);
        
        //add border presentation (lowered dock)
        setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
        
    }
}
