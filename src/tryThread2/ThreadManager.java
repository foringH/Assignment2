package tryThread2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ThreadManager {
	public static String writeContent = "";
	private int numberOfFiles = 0;
	private String source = "";
	private String destination = "";
	private String result = "";
	
	private int numberOfThread = 0;
	private int numberOfSet = 0;
	private int fileNoInSet = 0;
	
	private ArrayList<ArrayList<File>> tempFileList=new ArrayList<ArrayList<File>>();
	
	private File fileDirectory;
	private File[] fileListOfDirectory;
	
	private ReadThread[] readThreads;
	private Thread[] threads;
	private WriteThread writeThread;
	private Thread mainThread;
	
	
	public String getWriteContent()
	{
		return writeContent;
		
	}
	
	public void setWriteContent(String line)
	{
		writeContent = writeContent + line;
		
	}
	
	public void initialize(String source,String destination,int numOfThread) throws IOException
	{
		this.source=source;
		this.destination = destination;
		System.out.println("destination in INIT"+destination);
		this.numberOfThread = numOfThread;
		this.numberOfSet = this.numberOfThread;
		
		this.fileDirectory = new File(source);
        this.fileListOfDirectory = this.fileDirectory.listFiles();
        this.numberOfFiles = fileListOfDirectory.length;
        System.out.println("printing n:" + numberOfFiles);
        
        if( this.fileListOfDirectory.length < this.numberOfThread )
        {
        	this.numberOfThread = this.fileListOfDirectory.length;
        	this.numberOfSet = this.numberOfThread;
        }
        
        this.fileNoInSet = (this.numberOfFiles/this.numberOfSet);
        
        writeThread=new WriteThread(this.destination);
		mainThread=new Thread(writeThread); 
        
		readThreads=new ReadThread[this.numberOfSet];
		threads=new Thread[this.numberOfSet];
				
	}
	

	public String readWithThread() throws InterruptedException, IOException
	{
		System.out.println("hello");
		
        System.out.println("printing n:" + numberOfFiles);
        
        String result = "";
        
        initialize(source,destination,numberOfThread);
        
        int index = 0;
		
    		for(int setNumber = 0; setNumber < numberOfSet; setNumber++)
    		{
    			
    			ArrayList<File> tempFiles = new ArrayList<File>(); 
    			
    			if(setNumber < numberOfSet-1)
    			{
    				for( int fileNumber = 0; fileNumber < fileNoInSet; fileNumber++ )
    				{
    					index = ( fileNoInSet * setNumber ) + fileNumber;
    					System.out.println("my set no:"+setNumber+" filrNo:"+index);
    					tempFiles.add(fileListOfDirectory[index]);

    				}
    				
    			}
    			else
    			{
    				index = index + 1;
    				
    				while(index < numberOfFiles)
    				{					
    					System.out.println("my set no:"+setNumber+" filrNo:"+index);
    					
    					tempFiles.add(fileListOfDirectory[index]);
    		        	    		        	
    		        	index++;
    					
    				}
    				
    			}
    			
    			tempFileList.add(tempFiles);
    		     			
    		}
    		
    		
    		for(int i=0;i<tempFileList.size();i++)
    		{
    			System.out.println("set is: "+i);
    			readThreads[i] = new ReadThread(tempFileList.get(i));
    			threads[i] = new Thread(readThreads[i]);
    			threads[i].start();
    			
    			for(File f: tempFileList.get(i))
    			{
    				System.out.println("file name is:"+f.getName());
    				
    			}
    			
    		}
    		    		
    		result = result + merge();
    		System.out.println("WRITECON" + result);
    		    		
    		return result;
	}
	
	public String merge() throws InterruptedException
	{
		String temp = "";
		for(int i=0;i<tempFileList.size();i++)
		{
			
			threads[i].join();
			temp=temp+readThreads[i].getResult();
			System.out.println("this is what i get"+temp);
		}
		writeContent=writeContent+temp;
		return temp;
	}
	
	public void write(String line)
	{
		String temp = "";
		
		temp = getWriteContent();
		System.out.println("the line is"+temp);
				
    	this.writeThread.setLine(temp);
    	System.out.println("I want to write"+writeContent);
    	
		this.mainThread.start();
		
	}
	
}