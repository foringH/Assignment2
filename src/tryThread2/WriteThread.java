package tryThread2;


import java.io.FileWriter;
import java.io.IOException;

public class WriteThread implements Runnable{
//	private int id=888888;
	private String fileName = "";
	//private String destination = "D:/output5.txt";
	private FileWriter writer; // = new FileWriter(destination);
	private String line = "";
	
	public WriteThread(String fileName)
	{
		this.fileName = fileName;
		//createFile();
		//System.out.println("file name"+fileName);
		//writer = new FileWriter(this.fileName);
		
	}
	
	private void createFile() //throws IOException
	{
		try {
			writer = new FileWriter(this.fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("error in creation of file");
		}
		
	}
	
	public void setLine(String line)
	{
		this.line = line;
		
	}
	
	public String getLine()
	{
		return line;
		
	}
	
	public void run()
	{
		//System.out.println("i'm writing thread"+id);
		
		//try {
			
			write(line);
			
	//	}catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		try {
			
			close();
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void write(String line) //throws IOException 
	{
		//destination=des;
		//writer = new FileWriter(destination);
		//System.out.println("i'm writing thread in write"+line);
		try {
			createFile();
			writer.write(line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void close() throws IOException
	{		
		writer.close();
	}

}

