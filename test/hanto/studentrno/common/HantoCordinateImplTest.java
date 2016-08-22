package hanto.studentrno.common;

import static org.junit.Assert.*;

import org.junit.Test;

import hanto.studentrnorlando.common.HantoCordinateImpl;

public class HantoCordinateImplTest 
{
	
	@Test	//1
	public void intializesValues()
	{
		HantoCordinateImpl cordinate = new HantoCordinateImpl(2, 3);
		assertNotNull(cordinate);
		assertEquals(cordinate.getX(), 2);
		assertEquals(cordinate.getY(), 3);
	}
	
	@Test	//2
	public void intializesValuesDifferentValues()
	{
		HantoCordinateImpl cordinate = new HantoCordinateImpl(0, 0);
		assertNotNull(cordinate);
		assertEquals(cordinate.getX(), 0);
		assertEquals(cordinate.getY(), 0);
	}
	
	@Test 	//3
	public void doesnotEqualOtherThings()
	{
		HantoCordinateImpl cordinate = new HantoCordinateImpl(0, 0);
		assertTrue(!cordinate.equals("HI"));
	}
	
	@Test //4
	public void equalsWithSameCordiantes()
	{
		HantoCordinateImpl cordinate = new HantoCordinateImpl(0, 0);
		assertTrue(cordinate.equals(new HantoCordinateImpl(0, 0)));
	}
	
	@Test //5
	public void doesNotequalsWithDiferentCordiantesX()
	{
		HantoCordinateImpl cordinate = new HantoCordinateImpl(0, 0);
		assertTrue(!cordinate.equals(new HantoCordinateImpl(1, 0)));
	}
	
	@Test //6
	public void doesNotequalsWithDiferentCordiantesY()
	{
		HantoCordinateImpl cordinate = new HantoCordinateImpl(0, 0);
		assertTrue(!cordinate.equals(new HantoCordinateImpl(0, -1)));
	}
	
	@Test //7
	public void doesNotequalsWithDiferentCordiantes()
	{
		HantoCordinateImpl cordinate = new HantoCordinateImpl(0, 0);
		assertTrue(!cordinate.equals(new HantoCordinateImpl(8, 9)));
	}
	
	//convertFrom String
	@Test //8
	public void convertFromStringAndBack()
	{
		HantoCordinateImpl cordinate = new HantoCordinateImpl(0, 0);
		String s = cordinate.toString();
		assertEquals(cordinate, HantoCordinateImpl.convertFromString(s));
	}
	
	@Test //9
	public void convertFromStringNull()
	{
		assertEquals(null, HantoCordinateImpl.convertFromString("null"));
	}
	
	@Test //10
	public void convertFromStringGiberish()
	{
		assertEquals(null, HantoCordinateImpl.convertFromString("giber ish I said some words"));
	}

}
