import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Schmitter {

	private Schmitter schmitter = this;
	private JFrame frmSchmitterV;
	private JTextField fromPathTextField;
	private JTextField toPathTextField;
	private JProgressBar progressBar;
	private JButton btnStart;
	private JButton btnStop;
	private JFileChooser fc;
	
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
		frmSchmitterV = new JFrame();
		frmSchmitterV.setTitle("Schmitter - v2.0");
		frmSchmitterV.setBounds(100, 100, 390, 180);
		frmSchmitterV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSchmitterV.getContentPane().setLayout(null);
		
		JLabel fromLabel = new JLabel("From");
		fromLabel.setBounds(10, 16, 46, 14);
		frmSchmitterV.getContentPane().add(fromLabel);
		
		JLabel toLabel = new JLabel("To");
		toLabel.setBounds(10, 44, 46, 14);
		frmSchmitterV.getContentPane().add(toLabel);
		
		fromPathTextField = new JTextField();
		fromPathTextField.setBounds(60, 11, 270, 20);
		frmSchmitterV.getContentPane().add(fromPathTextField);
		fromPathTextField.setColumns(10);
		
		toPathTextField = new JTextField();
		toPathTextField.setColumns(10);
		toPathTextField.setBounds(60, 41, 270, 20);
		frmSchmitterV.getContentPane().add(toPathTextField);
		
		progressBar = new JProgressBar(0, 100);
		progressBar.setBounds(10, 71, 356, 30);
		frmSchmitterV.getContentPane().add(progressBar);
		
		btnStart = new JButton("Copy");		
		btnStart.setBounds(10, 111, 89, 23);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileCopy = new FileCopy(fromPathTextField.getText(), toPathTextField.getText(), schmitter);
				fileCopy.execute();
			}
		});
		frmSchmitterV.getContentPane().add(btnStart);
		
		btnStop = new JButton("Stop");		
		btnStop.setEnabled(false);
		btnStop.setBounds(277, 111, 89, 23);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileCopy.cancel(true);
			}
		});
		frmSchmitterV.getContentPane().add(btnStop);
		
		fc = new JFileChooser();
		
		JButton filePickerFrom = new JButton("...");
		filePickerFrom.setBounds(338, 11, 30, 20);
		filePickerFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc.setDialogTitle("Open");
				int status = fc.showOpenDialog(fc);
				if(status == fc.APPROVE_OPTION){
					fromPathTextField.setText(fc.getSelectedFile().getPath());
				}
			}
		});
		frmSchmitterV.getContentPane().add(filePickerFrom);
		
		JButton filePickerTo = new JButton("...");
		filePickerTo.setBounds(338, 41, 30, 20);
		filePickerTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc.setDialogTitle("Save to");
				int status = fc.showSaveDialog(fc);
				if(status == fc.APPROVE_OPTION){
					toPathTextField.setText(fc.getSelectedFile().getPath());
				}				
			}
		});
		frmSchmitterV.getContentPane().add(filePickerTo);

		
		
		
		frmSchmitterV.setVisible(true);
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
