package hanto.studentrno.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.common.HexHantoDirection;

public class HantoPlayerColorTest {
	
	@Test // 1
	public void twoCollorsExist() 
	{
		assertTrue(HantoPlayerColor.values().length == 2);
	}
	
	@Test // 2
	public void StringToWHITE()
	{
		assertEquals(HantoPlayerColor.valueOf(HantoPlayerColor.WHITE.name()), HantoPlayerColor.WHITE);
	}
	
	@Test(expected=IllegalArgumentException.class)	 // 3
	public void StringToNonColor()
	{
		HantoPlayerColor.valueOf("dire");
	}

}
