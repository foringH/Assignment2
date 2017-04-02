package tryThread2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileMerger {
	
	public static String writeContent = "";
	private int numberOfFiles = 0;
	private String source = "";
	private String destination = "";
	private String result = "";
	
	//////////////
	public int numberOfThread = 0;  
	///////////
	private int numberOfSet = 0;
	private int fileNoInSet = 0;
	
	private ArrayList<ArrayList<File>> fileListArray=new ArrayList<ArrayList<File>>();
	
	private File fileDirectory;
	private File[] fileListOfDirectory;
	
	private ReadThread[] readThreads;
	private Thread[] threads;
	private WriteThread writeThread;
	private Thread mainThread;
	
	
	public FileMerger(String source,String destination, int threadNo) //throws IOException
	{
		this.source = source;
		this.destination = destination;
		
		this.numberOfThread = threadNo;
		this.numberOfSet = this.numberOfThread;
		
		
		//initialize(source,destination,threadNo);
	}
	
	public String getWriteContent()
	{
		return writeContent;
		
	}
	
	public void setWriteContent(String line)
	{
		writeContent = line; //writeContent + line;
		
	}
	
	public ReadThread[] getReadThreads()
	{
		return readThreads;
		
	}
	
	public WriteThread getWriteThread()
	{
		return writeThread;
		
	}
		
	private void initialize(String source,String destination,int numOfThread) throws IOException
	{
		/*
		this.source = source;
		this.destination = destination;
		
		this.numberOfThread = numOfThread;
		this.numberOfSet = this.numberOfThread;
		*/
		
		this.fileDirectory = new File(this.source);
        this.fileListOfDirectory = this.fileDirectory.listFiles();
        //System.out.println("printing n:" + numberOfFiles + "jkl" + fileListOfDirectory.length);
        this.numberOfFiles = fileListOfDirectory.length;
        //System.out.println("printing n:" + numberOfFiles);
        
        if( this.fileListOfDirectory.length < this.numberOfThread )
        {
        	this.numberOfThread = this.fileListOfDirectory.length;
        	this.numberOfSet = this.numberOfThread;
        }
        
        this.fileNoInSet = (this.numberOfFiles/this.numberOfSet);
        
        
        writeThread = new WriteThread(this.destination);
		mainThread = new Thread(writeThread); 
        
		readThreads = new ReadThread[this.numberOfSet];
		threads = new Thread[this.numberOfSet];
		
				
	}
	
	
	private void distributeFilesAmongThreads()
	{
		fileListArray = new ArrayList<ArrayList<File>>();
		int index = 0;
		
		for(int setNumber = 0; setNumber < numberOfSet; setNumber++)
		{
			
			ArrayList<File> tempFiles = new ArrayList<File>(); 
			
			if(setNumber < numberOfSet-1)
			{
				for( int fileNumber = 0; fileNumber < fileNoInSet; fileNumber++ )
				{
					index = ( fileNoInSet * setNumber ) + fileNumber;
					
					//System.out.println("my set no:"+setNumber+" filrNo:"+index);
					
					tempFiles.add(fileListOfDirectory[index]);

				}
				
			}
			else
			{
				index = index + 1;
				
				while(index < numberOfFiles)
				{					
					//System.out.println("my set no:"+setNumber+" filrNo:"+index);
					
					tempFiles.add(fileListOfDirectory[index]);
		        	    		        	
		        	index++;
					
				}
				
			}
			
			fileListArray.add(tempFiles);
		     			
		}

		
		
	}
	
	
	public String readFiles() //throws  IOException
	{
		String result = "";  
		
		//System.out.println("the no of THREAD IS::"+this.numberOfThread);
		
        try {
			initialize(this.source,this.destination,this.numberOfThread);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error in initializing");
			//e.printStackTrace();
		}
        finally{
        	if ( (this.source == null) || (this.destination == null) )
        	{
        		throw new NullPointerException();
        	}
        }
        
        distributeFilesAmongThreads();
        
        //System.out.println("SIZE OF FILELISTARRAY:"+fileListArray.size());
            		
    		for(int i = 0; i < fileListArray.size(); i++ )
    		{
    			/*
    			for(File f : tempFileList.get(i))
    			{
    				System.out.println("set is: "+i+f.getName());
    				
    			}
    			*/
    			//System.out.println("value of "+i);
    			readThreads[i] = new ReadThread(fileListArray.get(i));
    			threads[i] = new Thread(readThreads[i]);
    			threads[i].start();
    			    			
    		}
    		    		
    		try {
				result = result + merge();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("error in merging");
				//e.printStackTrace();
			}
    		
    		    		
    		return result;
	}
	
	private String merge() throws InterruptedException
	{
		String temp = "";
		for(int i=0;i<fileListArray.size();i++)
		{
			
			threads[i].join();
			temp = temp + "\n" + readThreads[i].getResult();
			//System.out.println("the temp is in loop" + i + " :::" + temp);

		}
		
		writeContent=writeContent+temp;
		//System.out.println("the temp is"+temp);
		
		return temp;
	}
	
	public void writeFile(String line)
	{
		String temp = "";
		
		//temp = getWriteContent();
		//System.out.println("the line is"+line);
				
    	this.writeThread.setLine(temp + line);
    	//System.out.println("I want to write"+writeContent);
    	
		this.mainThread.start();
		
	}
	
}
