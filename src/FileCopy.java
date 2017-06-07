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

	private File from;
	private String to;
	private float progress;
	private Schmitter schmitter;
	JProgressBar progBar;

	public FileCopy(File from, String to, Schmitter schmitter) {
		super();
		this.from = from;
		this.to = to;
		this.schmitter = schmitter;
		progBar = schmitter.getCp().addCopyProgresses(this);
		//this.execute();
		
	}
	
	public File getFrom() {
		return from;
	}
	
	private void GUIStopped(){
		progBar.setForeground(Color.RED);
	}
	
	private void GUIFinished(){
		progBar.setValue(100);
		progBar.setForeground(Color.GREEN);		
	}
	
	private boolean fileExist(){
		if(schmitter.getChckbxNewCheckBox().isSelected()){
			return false;
		};
		
		File folder = new File(to);
		File[] listOfFiles = folder.listFiles();
		for(int i = 0; i < listOfFiles.length;i++) {
			System.out.println(listOfFiles[i].getName());
			if(listOfFiles[i].getName().equals(from.getName())){
				System.out.println("Already exist!");
				GUIFinished();
				return true;
			}
		}
		return false;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		InputStream is = null;
		OutputStream os = null;			

		try {			
			if(fileExist()){
				return null;
			};
			
			is = new FileInputStream(from);
			os = new FileOutputStream(new File(to+'\\'+from.getName()));
			
			byte[] buffer = new byte[1024];

			long fileSize = from.length();
			long bytesWritten = 0;
			
			

			int length;
			int last = 0;
			while ((length = is.read(buffer)) > 0) {
				if(this.isCancelled()){		
					GUIStopped();
					break;
				}
				
				os.write(buffer, 0, length);
				bytesWritten += length;

				progress = bytesWritten * 100f / fileSize;	
				if(last < (int) progress){
					last = (int) progress;
					System.out.println(from.toPath() + ": " + (int) progress);
				}
				
				progBar.setValue((int) progress);
				
				if(progress == 100){
					GUIFinished();
				}
			}

			is.close();
			os.close();
			
			
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}		
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
