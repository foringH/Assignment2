package tryThread2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadThread implements Runnable {
	
	//private int id=99999;
	private String result = "";
	//private File[] fileList;
	private ArrayList<File> fileList = new ArrayList<File>();
	
	ReadThread(ArrayList<File> files)
	{
		this.fileList = files;
		
	}
	
	public String getResult()
	{
		
		return result;
	}
	
	public void run()
	{
		//System.out.println("i'm reading"+id);
		
		try {
			
			read(this.fileList);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String read(ArrayList<File> files) throws IOException
	{
		for(File file: files)
		{
			if(file.isFile())
			 {
	             FileReader fileReader= new FileReader(file);
	             BufferedReader reader=new BufferedReader(fileReader);
	             
	             String line = "";
	                         
	             while ((line = reader.readLine()) != null) {
	            	 
	                System.out.println("printing line"+line);
	                 result=result+line;
	                 System.out.println("rsult::::::"+result);
	                 
	             }
	             
	             reader.close();
	             fileReader.close();
	          
	         }
			
		}
		 
		
		return result;
	}


}
