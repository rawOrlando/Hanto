/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentrno.common;
import hanto.common.HantoPieceType;
import hanto.common.HantoPrematureResignationException;
import hanto.studentrnorlando.common.*;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * Test cases for HexHanto Directions.
 * @version Jun 07, 2016
 */
public class HexHantoDirectionTest {
	
	//!!! mabye no test is needed
	
	@BeforeClass
	public static void initializeClass()
	{
		// don't nee to do any thing
		//player = new GeneralHantoPlayer(HantoPlayerColor.BLUE, 10);
	}
	
	@Before
	public void setup()
	{
		// By default, blue moves first.
		//bag = new HantoPieceBag();
	}
	
	@Test // 1
	public void sixDirectionExist() 
	{
		assertTrue(HexHantoDirection.values().length == 6);
	}
	
	@Test // 2
	public void StrignToNorth()
	{
		assertEquals(HexHantoDirection.valueOf("NORTH"), HexHantoDirection.NORTH);
	}
	
	@Test(expected=IllegalArgumentException.class)	 // 3
	public void StringToNonDirection()
	{
		HexHantoDirection.valueOf("dire");
	}

}
