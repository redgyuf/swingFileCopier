import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class Schmitter {

	private Schmitter schmitter = this;
	private JFrame SchmitterFrame;
	private JTextField fromPathTextField;
	private JTextField toPathTextField;
	private JButton btnStart;
	JCheckBox chckbxNewCheckBox;
	private JFileChooser fc;

	private CopyProgress cp;

	private File[] files;

	private FileCopy fileCopy;
	private List<FileCopy> fileCopies = new ArrayList();
	private JButton btnStopAll;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Schmitter();
			}
		});
	}

	public Schmitter() {
		initialize();
	}

	private void initialize() {
		SchmitterFrame = new JFrame();
		SchmitterFrame.setResizable(false);
		SchmitterFrame.setTitle("Schmitter - v3.2");
		SchmitterFrame.setBounds(100, 100, 410, 160);
		SchmitterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SchmitterFrame.getContentPane().setLayout(null);

		JLabel fromLabel = new JLabel("From");
		fromLabel.setBounds(10, 16, 46, 14);
		SchmitterFrame.getContentPane().add(fromLabel);

		JLabel toLabel = new JLabel("To");
		toLabel.setBounds(10, 44, 46, 14);
		SchmitterFrame.getContentPane().add(toLabel);

		fromPathTextField = new JTextField();
		fromPathTextField.setEditable(false);
		fromPathTextField.setBounds(60, 11, 270, 20);
		SchmitterFrame.getContentPane().add(fromPathTextField);
		fromPathTextField.setColumns(10);

		toPathTextField = new JTextField();
		toPathTextField.setEditable(false);
		toPathTextField.setColumns(10);
		toPathTextField.setBounds(60, 41, 270, 20);
		SchmitterFrame.getContentPane().add(toPathTextField);

		btnStart = new JButton("Copy");
		btnStart.setBounds(279, 72, 89, 23);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cp = new CopyProgress();
				String to = toPathTextField.getText();
				System.out.println(files.length);

				for (int i = 0; i < files.length; i++) {
					
					fileCopies.add(new FileCopy(files[i], to, schmitter));
				}
				
				btnStopAll.setVisible(true);

			}
		});
		SchmitterFrame.getContentPane().add(btnStart);

		fc = new JFileChooser();

		JButton filePickerFrom = new JButton("...");
		filePickerFrom.setBounds(338, 11, 30, 20);
		filePickerFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc.setDialogTitle("Open");
				fc.setMultiSelectionEnabled(true);

				int status = fc.showOpenDialog(fc);
				files = fc.getSelectedFiles();
				if (status == fc.APPROVE_OPTION) {
					fromPathTextField.setText(files[0].getPath());
				}
			}
		});
		SchmitterFrame.getContentPane().add(filePickerFrom);

		JButton filePickerTo = new JButton("...");
		filePickerTo.setBounds(338, 41, 30, 20);
		filePickerTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc.setDialogTitle("Save to");
				fc.setFileSelectionMode(fc.DIRECTORIES_ONLY);
				int status = fc.showSaveDialog(fc);
				if (status == fc.APPROVE_OPTION) {
					toPathTextField.setText(fc.getSelectedFile().getPath());
				}
			}
		});
		SchmitterFrame.getContentPane().add(filePickerTo);

		chckbxNewCheckBox = new JCheckBox("Override?");
		chckbxNewCheckBox.setBounds(184, 72, 89, 23);
		SchmitterFrame.getContentPane().add(chckbxNewCheckBox);

		btnStopAll = new JButton("Stop All");
		btnStopAll.setBounds(10, 72, 89, 23);
		btnStopAll.setVisible(false);
		btnStopAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (FileCopy fileCopy : fileCopies) {
					fileCopy.cancel(true);
				}
			}
		});
		

		SchmitterFrame.getContentPane().add(btnStopAll);

		SchmitterFrame.setVisible(true);
	}

	public JButton getBtnStopAll() {
		return btnStopAll;
	}

	public JCheckBox getChckbxNewCheckBox() {
		return chckbxNewCheckBox;
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

	public CopyProgress getCp() {
		return cp;
	}

}
