package hanto.studentrno.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import hanto.common.HantoGameID;

public class HantogameIDTest 
{
	@Test // 1
	public void fiveGameTypesExistExist() 
	{
		assertTrue(HantoGameID.values().length == 5);
	}
	
	@Test // 2
	public void StringToAlpha()
	{
		//HantoGameID.ALPHA.
		assertEquals(HantoGameID.valueOf("ALPHA_HANTO"), HantoGameID.ALPHA_HANTO);
	}
	
	@Test(expected=IllegalArgumentException.class)	 // 3
	public void StringToNonDirection()
	{
		HantoGameID.valueOf("dire");
	}
	
	@Test	//4
	public void getPrintableName()
	{
		assertEquals(HantoGameID.ALPHA_HANTO.toString(), "Alpha");
		assertEquals(HantoGameID.BETA_HANTO.toString(), "Beta");
		assertEquals(HantoGameID.GAMMA_HANTO.toString(), "Gamma");
		assertEquals(HantoGameID.DELTA_HANTO.toString(), "Delta");
		assertEquals(HantoGameID.EPSILON_HANTO.toString(), "Epsilon");
	}

}
