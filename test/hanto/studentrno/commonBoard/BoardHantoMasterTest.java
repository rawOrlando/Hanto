/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentrno.commonBoard;
import hanto.studentrnorlando.common.board.*;
import org.junit.*;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;

/**
 * Test cases for Boards.
 * @version Jan 07, 2016
 */
public class BoardHantoMasterTest {


	/**
	 * Internal class for these test cases.
	 * @version Sep 13, 2014
	 */
	class TestHantoCoordinate implements HantoCoordinate
	{
		private final int x, y;
		
		public TestHantoCoordinate(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		/*
		 * @see hanto.common.HantoCoordinate#getX()
		 */
		@Override
		public int getX()
		{
			return x;
		}

		/*
		 * @see hanto.common.HantoCoordinate#getY()
		 */
		@Override
		public int getY()
		{
			return y;
		}

	}
	
	private static SmartHantoGameBoard smartBoard;
	private static AdvancedHexHantoGameBoard advanceBoard;
	private static HexHantoGameBoard hexGameBoard;
	
	@BeforeClass
	public static void initializeClass()
	{
		// don't nee to do any thing
	}


	
	
}

