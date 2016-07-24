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
import hanto.studentrnorlando.beta.BetaHantoPlayer;
import hanto.studentrnorlando.common.*;

import static org.junit.Assert.*;

import org.junit.*;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * Test cases for General Players.
 * @version Jun 07, 2016
 */
public class GeneralHantoPlayerMasterTest {
	
	private static GeneralHantoPlayer player, redPlayer;
	
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
		player = new BetaHantoPlayer(HantoPlayerColor.BLUE);
		redPlayer = new BetaHantoPlayer(HantoPlayerColor.RED);
	}
	
	@Test // 1
	public void createPlayerFine() 
	{
		assertEquals(player.hasPlayedButterFly(), false);
		assertNotEquals(player.getPieceOptions(), null);
		assertTrue(player.getNumberofPiece() > 0);
	}
	
	@Test // 2
	public void playerColorBlueWorks()
	{
		assertEquals(player.getPlayerColor(), HantoPlayerColor.BLUE);
	}
	
	@Test // 3
	public void playerColorRedWorks()
	{
		assertEquals(redPlayer.getPlayerColor(), HantoPlayerColor.RED);
	}
	
	@Test(expected=HantoException.class) //4
	public void playsAPeiceThePlayerHas() throws HantoException
	{
		player.playPiece(HantoPieceType.CRAB, new TestHantoCoordinate(0,0));
	}
	
	@Test //5
	public void playsAButterFlyAtOrigin() throws HantoException
	{
		player.playPiece(HantoPieceType.BUTTERFLY, new TestHantoCoordinate(0,0));
		assertEquals(player.getButterFlyLocation().getX(), 0);
		assertEquals(player.getButterFlyLocation().getY(), 0);
		assertTrue(player.hasPlayedButterFly());
	}
	
	@Test //6
	public void playsAButterFlyElseWhere() throws HantoException
	{
		player.playPiece(HantoPieceType.BUTTERFLY, new TestHantoCoordinate(3,5));
		assertEquals(player.getButterFlyLocation().getX(), 3);
		assertEquals(player.getButterFlyLocation().getY(), 5);
		assertTrue(player.hasPlayedButterFly());
	}
	
	@Test //7
	public void movesButterFly()throws HantoException
	{
		player.playPiece(HantoPieceType.BUTTERFLY, new TestHantoCoordinate(0,0));
		player.movePiece(HantoPieceType.BUTTERFLY, new TestHantoCoordinate(0,0), new TestHantoCoordinate(3,5));
		assertEquals(player.getButterFlyLocation().getX(), 3);
		assertEquals(player.getButterFlyLocation().getY(), 5);
	}
	
	@Test  (expected=HantoException.class) //8
	public void movesAMissingButterFly() throws HantoException
	{
		player.movePiece(HantoPieceType.BUTTERFLY, new TestHantoCoordinate(0,0), new TestHantoCoordinate(3,5));
	}
	
	@Test (expected=HantoException.class)//9
	public void movesButterFlyFromWrongSpot()throws HantoException
	{
		player.playPiece(HantoPieceType.BUTTERFLY, new TestHantoCoordinate(0,0));
		player.movePiece(HantoPieceType.BUTTERFLY, new TestHantoCoordinate(1,0), new TestHantoCoordinate(3,5));
	}
	
	@Test	// 10
	public void hasForBetaPlayer() 
	{
		assertTrue(player.hasPiece(HantoPieceType.BUTTERFLY));
		assertTrue(player.hasPiece(HantoPieceType.SPARROW));
		assertTrue(!player.hasPiece(HantoPieceType.CRAB));
		assertTrue(!player.hasPiece(HantoPieceType.HORSE));
	}
	
	@Test	// 11
	public void moveSparrow() throws HantoException
	{
		player.playPiece(HantoPieceType.SPARROW, new TestHantoCoordinate(0,0));
		player.movePiece(HantoPieceType.SPARROW, new TestHantoCoordinate(0,0), new TestHantoCoordinate(2,1));
	}
	
	@Test (expected=HantoException.class)	// 11
	public void moveAPieceTheyDontHave() throws HantoException
	{
		player.playPiece(HantoPieceType.CRANE, new TestHantoCoordinate(0,0));
		player.movePiece(HantoPieceType.CRANE, new TestHantoCoordinate(0,0), new TestHantoCoordinate(2,1));
	}
	
	

	
	
}

