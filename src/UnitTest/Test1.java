package UnitTest;

import static org.junit.Assert.*;

import java.io.IOException;

import tryThread2.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class Test1 {
	private static final Class<IllegalArgumentException> expected = null;
	public static ThreadManager t; 
	@BeforeClass
	public static void init() throws IOException
	{
		System.out.println("Testing initialization");
		//t.initialize("D:/test", "D:/output2.txt", 5);
		 t = new ThreadManager("D:/test", "D:/output2.txt", 5);
				
	}
	
	@Test
	public void test1() throws InterruptedException, IOException {
		//fail("Not yet implemented");
		System.out.println("Testing Read with Thread");
		assertEquals("first file123second file4563rd file789",t.readWithThread());
	}
	
	@Test
	public void test2()
	{
		t.writeWithThread("hello");
		System.out.println("Testing Write with Thread");
		assertEquals("hello",t.getWriteThread().getLine());
		
	}
	
	@Test
	public void test3()
	{
		System.out.println("Testing set & get write Content");
		t.setWriteContent("hello");
		assertEquals("hello",t.getWriteContent());
		
	}
	
	@Test
	public void test4()
	{
		System.out.println("Testing writeThread setLine & getLine");
		
		t.getWriteThread().setLine("hello");
		
		assertEquals("hello",t.getWriteThread().getLine());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test5()
	{
		System.out.println("Testing readThread");
		ReadThread rr = t.getReadThreads()[0];
		
		rr.run();		
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void test6() throws IOException
	{
		System.out.println("Testing WriteThread");
		WriteThread w ;
		w = new WriteThread("D:/outputTest.txt");
		w.setLine("hello");
		
		w.run();		
		
		//assertEquals("hello",w.getLine());
	}
	
	

}
