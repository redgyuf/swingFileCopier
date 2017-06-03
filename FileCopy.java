import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class FileCopy extends SwingWorker<Integer, Integer> {

	private String from;
	private String to;
	private float progress;

	private Schmitter schmitter;

	public FileCopy(String from, String to, Schmitter schmitter) {
		super();
		this.from = from;
		this.to = to;
		this.schmitter = schmitter;
	}
	
	private void GUIModifyStartCopy(){
		schmitter.getFromPathTextField().setEnabled(false);
		schmitter.getToPathTextField().setEnabled(false);
		schmitter.getBtnStart().setEnabled(false);
		schmitter.getBtnStop().setEnabled(true);		
		schmitter.getProgressBar().setValue(0);
		schmitter.getProgressBar().setString(null);
		schmitter.getProgressBar().setStringPainted(true);
		schmitter.getProgressBar().setForeground(Color.orange);
		schmitter.getProgressBar().setIndeterminate(false);
	}
	
	private void GUIModifyFinishedCopy(){
		schmitter.getFromPathTextField().setEnabled(true);
		schmitter.getToPathTextField().setEnabled(true);
		schmitter.getBtnStart().setEnabled(true);
		schmitter.getBtnStop().setEnabled(false);
		
		if(this.isCancelled()){
			schmitter.getProgressBar().setForeground(Color.RED);
			schmitter.getProgressBar().setString("File transfer stopped!");
		}else{
			schmitter.getProgressBar().setForeground(Color.GREEN);
			schmitter.getProgressBar().setString("File transfer completed!");
		}
		
	}

	@Override
	protected Integer doInBackground() throws Exception {
		InputStream is = null;
		OutputStream os = null;
		JProgressBar progBar = schmitter.getProgressBar();
		
		GUIModifyStartCopy();			

		try {
			is = new FileInputStream(new File(from));
			os = new FileOutputStream(new File(to));
			byte[] buffer = new byte[1024];

			long fileSize = new File(from).length();
			long bytesWritten = 0;

			int length;
			while ((length = is.read(buffer)) > 0) {
				if(this.isCancelled()){
					
					break;
				}
				
				os.write(buffer, 0, length);
				bytesWritten += length;

				progress = bytesWritten * 100f / fileSize;
				progBar.setValue((int) progress);
			}

			is.close();
			os.close();
			
			GUIModifyFinishedCopy();
			
		}catch(FileNotFoundException e){
			GUIModifyFinishedCopy();
			schmitter.getProgressBar().setForeground(Color.RED);
			schmitter.getProgressBar().setIndeterminate(true);
			progBar.setString("File not found!");
		}		
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
