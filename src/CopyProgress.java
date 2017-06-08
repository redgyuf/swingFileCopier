import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class CopyProgress {

	private JFrame frame;
	private JPanel p;
	private JScrollPane scrollPane;
	private GroupLayout gl_p;
	private int numOfCopies = 0;
	
	private List<JLabel> jlabels = new ArrayList<>();
	private List<JProgressBar> jprogbars = new ArrayList<>();
	private List<JButton> jbuttons = new ArrayList<>();
	private List<JSeparator> jseparators = new ArrayList<>();
 
	public CopyProgress() {
		initialize();
	}

	public JProgressBar addCopyProgresses(FileCopy copy) {
		JLabel lblNewLabel = new JLabel();
		jlabels.add(lblNewLabel);
		lblNewLabel.setBounds(10, 10+(numOfCopies*70), frame.getWidth()-65, 15);
		lblNewLabel.setText(copy.getFrom().getAbsolutePath());
		p.add(lblNewLabel);
		
		JProgressBar progressBar = new JProgressBar(0,100);
		jprogbars.add(progressBar);
		progressBar.setBounds(10, 35+(numOfCopies*70), frame.getWidth()-155, 25);
		progressBar.setStringPainted(true);
		progressBar.setForeground(Color.ORANGE);
		p.add(progressBar);
		
		JButton btnStop = new JButton("Stop");
		jbuttons.add(btnStop);
		btnStop.setBounds(frame.getWidth()-135, 35+(numOfCopies*70), 90, 25);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				copy.cancel(true);
				btnStop.setText("Stopped");
				btnStop.setEnabled(false);
			}
		});
		p.add(btnStop);
		
		JSeparator separator = new JSeparator();
		jseparators.add(separator);
		separator.setBounds(10, 70+(numOfCopies*70), frame.getWidth()-55, 2);
		p.add(separator);
		
		numOfCopies += 1;
		
		copy.execute();
		return progressBar;
	}

	private void initialize() {
		frame = new JFrame();
		frame.setSize(520, 200);
		frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);
		
		frame.addComponentListener(new ComponentAdapter() 
		{  
		        public void componentResized(ComponentEvent evt) {
		            Component c = (Component)evt.getSource();		            
	            	gl_p.setVerticalGroup(gl_p.createParallelGroup(Alignment.LEADING).addGap(0, numOfCopies*70, Short.MAX_VALUE));
		            
		            
		            int i = 0;
		            for (JLabel jlabel : jlabels) {
		            	jlabel.setBounds(10, 10+(i*70), frame.getWidth()-65, 14);
		            	i += 1;
					}
		            
		            i = 0;
		            for (JProgressBar jprogbar : jprogbars) {
		            	jprogbar.setBounds(10, 35+(i*70), frame.getWidth()-155, 23);
		            	i += 1;
					}
		            
		            i = 0;
		            for (JButton jButton : jbuttons) {
						jButton.setBounds(frame.getWidth()-135, 35+(i*70), 90, 23);
						i += 1;
					}
		            
		            i = 0;
		            for (JSeparator jSeparator : jseparators) {
						jSeparator.setBounds(10, 70+(i*70), frame.getWidth()-55, 2);
						i += 1;
					}
		            
		            
		            
		            
		        }
		});

		p = new JPanel();
		p.setSize(600, 400);
		
		
		scrollPane = new JScrollPane(p);

		gl_p = new GroupLayout(p);
		gl_p.setHorizontalGroup(gl_p.createParallelGroup(Alignment.LEADING).addGap(0, 282, Short.MAX_VALUE));
		gl_p.setVerticalGroup(gl_p.createParallelGroup(Alignment.LEADING).addGap(0, numOfCopies*70, Short.MAX_VALUE));
		p.setLayout(gl_p);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setVisible(true);

	}
}
