import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Schmitter {

	private Schmitter schmitter = this;
	private JFrame frame;
	private JTextField fromPathTextField;
	private JTextField toPathTextField;
	private JProgressBar progressBar;
	private JButton btnStart;
	private JButton btnStop;
	
	private FileCopy fileCopy;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					new Schmitter();
			}
		});
	}

	public Schmitter(){
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Schmitter - v1.0");
		frame.setBounds(100, 100, 450, 210);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel fromLabel = new JLabel("From");
		fromLabel.setBounds(30, 30, 46, 14);
		frame.getContentPane().add(fromLabel);
		
		JLabel toLabel = new JLabel("To");
		toLabel.setBounds(30, 58, 46, 14);
		frame.getContentPane().add(toLabel);
		
		fromPathTextField = new JTextField();
		fromPathTextField.setBounds(80, 27, 300, 20);
		frame.getContentPane().add(fromPathTextField);
		fromPathTextField.setColumns(10);
		
		toPathTextField = new JTextField();
		toPathTextField.setColumns(10);
		toPathTextField.setBounds(80, 55, 300, 20);
		frame.getContentPane().add(toPathTextField);
		
		progressBar = new JProgressBar(0, 100);
		progressBar.setBounds(30, 95, 356, 30);
		frame.getContentPane().add(progressBar);
		
		btnStart = new JButton("Start");		
		btnStart.setBounds(30, 136, 89, 23);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileCopy = new FileCopy(fromPathTextField.getText(), toPathTextField.getText(), schmitter);
				fileCopy.execute();
			}
		});
		frame.getContentPane().add(btnStart);
		
		btnStop = new JButton("Stop");		
		btnStop.setEnabled(false);
		btnStop.setBounds(297, 136, 89, 23);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileCopy.cancel(true);
			}
		});
		frame.getContentPane().add(btnStop);
		
		frame.setVisible(true);
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
	public JTextField getFromPathTextField() {
		return fromPathTextField;
	}

	public JTextField getToPathTextField() {
		return toPathTextField;
	}

	public JButton getBtnStart() {
		return btnStart;
	}
	
	public JButton getBtnStop() {
		return btnStop;
	}
	
}
