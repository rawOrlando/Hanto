package hanto.studentrno.common;

import static org.junit.Assert.*;

import org.junit.Test;

import hanto.common.HantoException;

public class HantoExceptionTest {
	
	@Test	//1
	public void defaultCosntuctorWorks()
	{
		HantoException exception = new HantoException("message");
		assertNotNull(exception);
		assertEquals(exception.getMessage(), "message");
	}
	
	@Test	//2
	public void cosntuctorThrowableWorks()
	{
		Throwable trow = new Throwable();
		HantoException exception = new HantoException("message", trow);
		assertNotNull(exception);
		assertEquals(exception.getMessage(), "message");
		assertEquals(exception.getCause(), trow);
	}

}
