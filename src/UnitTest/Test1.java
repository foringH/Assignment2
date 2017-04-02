package UnitTest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import tryThread2.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class Test1 {
	public static FileMerger t; 
	public static String temp = "";
	
	@BeforeClass
	public static void TestInit() throws InterruptedException,IOException
	{
		
		System.out.println("Testing initialization");
		//t.initialize("D:/test", "D:/output2.txt", 5);
		
			t = new FileMerger("D:/test", "D:/outputTest.txt", 5);
			temp = t.readFiles();
			//System.out.println("createdFiles"+t.numberOfThread);
		
		
	}
	
	

	
	@Test
	public void failReadFile()
	{
		System.out.println("Fail test for Read file");
		FileMerger tt= new FileMerger("","D:/hello.txt",3);
		String g="";
		try{
			 g = tt.readFiles();
		}
		catch(Exception e)
		{
			System.out.println("error " + e);
			
		}
		
	}
	
	@Test
	public void testReadFile(){
		//fail("Not yet implemented");
		System.out.println("Testing Read with Thread");
		//t.readFiles();
		
		//System.out.println("Success test");
		assertEquals("\nfirst file123\nsecond file456\n3rd file789",t.readFiles());
		
	}
	
	
	@Test
	public void failWriteFile()
	{
		FileMerger tt = new FileMerger("D:/test","",3);
		
		
		try{
			tt.getWriteThread().write("hello");
			
		}catch(Exception e)
		{
			System.out.println("error  " + e);
			
		}
		
		
	}
	
	
	@Test
	public void testWriteFile()
	{
		
		System.out.println("Testing Write with Thread");
		
		t.writeFile("hello");

		assertEquals("hello",t.getWriteThread().getLine());
		
		
	}
	
	@Test
	public void testSetGetContent()
	{
		System.out.println("Testing set & get write Content");
		
		t.setWriteContent("hello");
		
		assertEquals("hello",t.getWriteContent());
		
	}
	
	@Test
	public void testWriteThreadSetGet()
	{
		System.out.println("Testing writeThread setLine & getLine");
		
		t.getWriteThread().setLine("hello");
		
		assertEquals("hello",t.getWriteThread().getLine());
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testReadThread()
	{
		System.out.println("Testing readThread");
		
		File fileDirectory = new File("D:/test");
        File[] fileListOfDirectory = fileDirectory.listFiles();
        
        ArrayList<File> fileList = new ArrayList<File>(Arrays.asList(fileListOfDirectory));
        
		ReadThread rr;
		rr = new ReadThread(fileList); // t.getReadThreads()[0];
		
		rr.run();		
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testWriteThread() 
	{
		System.out.println("Testing WriteThread");
		
		WriteThread w ;
		w = new WriteThread("D:/outputTest.txt");
		
		w.setLine("hello");
		
		w.run();		
		
		//assertEquals("hello",w.getLine());
	}
	
	

}
