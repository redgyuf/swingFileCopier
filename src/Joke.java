import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Joke {
	
	public Joke(){
		initialize();
	}

	private void initialize(){
		JFrame f = new JFrame();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        ImageIcon image = new ImageIcon(this.getClass().getResource("joke.jpg")); 

        JLabel lbl = new JLabel(image); 

        f.getContentPane().add(lbl);

        f.setSize(image.getIconWidth(), image.getIconHeight());

        int x = (screenSize.width - f.getSize().width)/2;
        int y = (screenSize.height - f.getSize().height)/2;

        f.setLocation(x, y);
        f.setVisible(true);
	}
}
