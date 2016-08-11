/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentrno.beta;

import static hanto.common.HantoPieceType.*;
import static hanto.common.MoveResult.*;
import static hanto.common.HantoPlayerColor.*;
import static org.junit.Assert.*;
import hanto.common.*;
import hanto.studentrnorlando.factory.HantoGameFactory;

import org.junit.*;

/**
 * Test cases for Beta Hanto.
 * @version Sep 14, 2014
 */
public class BetaHantoMasterTest
{
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
	
	private static HantoGameFactory factory;
	private HantoGame game, redGame;
	
	@BeforeClass
	public static void initializeClass()
	{
		factory = HantoGameFactory.getInstance();
	}
	
	@Before
	public void setup()
	{
		// By default, blue moves first.
		game = factory.makeHantoGame(HantoGameID.BETA_HANTO);
		redGame = factory.makeHantoGame(HantoGameID.BETA_HANTO, WHITE);
	}
	
	@Test // 1
	public void noWinnerAfterFirstMove() throws HantoException
	{
		final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
	}
	
	@Test	// 2
	public void bluePlacesInitialButterflyAtOrigin() throws HantoException
	{
		final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLACK, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test	// 3
	public void bluePlacesInitialSparrowAtOrigin() throws HantoException
	{
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLACK, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test(expected=HantoException.class) // 4
	public void bluePlaceInitialHorseAtOrigin() throws HantoException
	{
		final MoveResult mr = game.makeMove(HORSE, null, makeCoordinate(0, 0));
	}
	
	@Test(expected=HantoException.class) // 5
	public void bluePlaceInitialNotAtOrigin() throws HantoException
	{
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(1, 0));
	}
	
	@Test 	// 6
	public void redPlacesButterFlyAt10() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		
		assertEquals(OK, mr);
		
		final HantoPiece redp = game.getPieceAt(makeCoordinate(1, 0));
		assertEquals(WHITE, redp.getColor());
		assertEquals(BUTTERFLY, redp.getType());
	}
	
	@Test 	// 7
	public void redPlacesSparrowAt1_1() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		
		assertEquals(OK, mr);
		
		final HantoPiece redp = game.getPieceAt(makeCoordinate(1, -1));
		assertEquals(WHITE, redp.getColor());
		assertEquals(SPARROW, redp.getType());
	}
	
	@Test(expected=HantoException.class)	// 8
	public void redPlacesOnTopOfBlue() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
	}
	
	@Test 	// 9
	public void redPlacesButterFlyAt10AllPieceAreOnBoard() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		
		assertEquals(OK, mr);
		final HantoPiece bluep = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLACK, bluep.getColor());
		assertEquals(BUTTERFLY, bluep.getType());
		
		final HantoPiece redp = game.getPieceAt(makeCoordinate(1, 0));
		assertEquals(WHITE, redp.getColor());
		assertEquals(BUTTERFLY, redp.getType());
	}
	
	@Test(expected=HantoException.class) // 10
	public void secoundMoveRedPlacesNotAdjacinet() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, 2));
	}
	
	@Test // 11
	public void placeThreeSparrowsInARow() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLACK, p.getColor());
		assertEquals(SPARROW, p.getType());
		
		p = game.getPieceAt(makeCoordinate(1, 0));
		assertEquals(WHITE, p.getColor());
		assertEquals(SPARROW, p.getType());
		
		p = game.getPieceAt(makeCoordinate(2, 0));
		assertEquals(BLACK, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test(expected=HantoException.class) // 12
	public void placeThreeButterFliesInARow() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, 0));
	}
	
	@Test(expected=HantoException.class) // 12.1 extra test to see where 11 is going worng
	public void placeFourButterFliesInARow() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(3, 0));
	}
	
	@Test(expected=HantoException.class) // 12.2 extra test to see where 11 is going worng
	public void placeFiveButterFliesInARow() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(3, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(4, 0));
	}
	
	@Test(expected=HantoException.class) // 13
	public void placeTenALLPieceAndMoreSparrows() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(3, 0));
		game.makeMove(SPARROW, null, makeCoordinate(4, 0));
		game.makeMove(SPARROW, null, makeCoordinate(5, 0));
		game.makeMove(SPARROW, null, makeCoordinate(6, 0));
		game.makeMove(SPARROW, null, makeCoordinate(7, 0));
		game.makeMove(SPARROW, null, makeCoordinate(8, 0));
		game.makeMove(SPARROW, null, makeCoordinate(9, 0));
		game.makeMove(SPARROW, null, makeCoordinate(10, 0));
		game.makeMove(SPARROW, null, makeCoordinate(11, 0));
		game.makeMove(SPARROW, null, makeCoordinate(12, 0));
		game.makeMove(SPARROW, null, makeCoordinate(13, 0));
	}
	
	@Test	// 14
	public void playButterFliesFirst() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(3, 0));
		game.makeMove(SPARROW, null, makeCoordinate(4, 0));
		game.makeMove(SPARROW, null, makeCoordinate(5, 0));
	}
	
	@Test(expected=HantoException.class)	//15
	public void redDoesNotPlayButterFlies() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(3, 0));
		game.makeMove(SPARROW, null, makeCoordinate(4, 0));
		game.makeMove(SPARROW, null, makeCoordinate(5, 0));
		game.makeMove(SPARROW, null, makeCoordinate(6, 0));
		game.makeMove(SPARROW, null, makeCoordinate(7, 0));
		game.makeMove(SPARROW, null, makeCoordinate(8, 0));
		game.makeMove(SPARROW, null, makeCoordinate(9, 0));
	}
	
	@Test	//16
	public void draw() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(3, 0));
		game.makeMove(SPARROW, null, makeCoordinate(4, 0));
		game.makeMove(SPARROW, null, makeCoordinate(5, 0));
		game.makeMove(SPARROW, null, makeCoordinate(6, 0));
		game.makeMove(SPARROW, null, makeCoordinate(7, 0));
		game.makeMove(SPARROW, null, makeCoordinate(8, 0));
		game.makeMove(SPARROW, null, makeCoordinate(9, 0));
		game.makeMove(SPARROW, null, makeCoordinate(10, 0));
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(11, 0));
		assertEquals(mr, MoveResult.DRAW);
	}
	
	@Test (expected=HantoException.class)	//17
	public void drawAndKeepPlaying() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(3, 0));
		game.makeMove(SPARROW, null, makeCoordinate(4, 0));
		game.makeMove(SPARROW, null, makeCoordinate(5, 0));
		game.makeMove(SPARROW, null, makeCoordinate(6, 0));
		game.makeMove(SPARROW, null, makeCoordinate(7, 0));
		game.makeMove(SPARROW, null, makeCoordinate(8, 0));
		game.makeMove(SPARROW, null, makeCoordinate(9, 0));
		game.makeMove(SPARROW, null, makeCoordinate(10, 0));
		game.makeMove(SPARROW, null, makeCoordinate(10, 0));
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(11, 0));
		assertEquals(mr, MoveResult.DRAW);
	}
	
	@Test 		//18
	public void redWins() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		assertEquals(mr, MoveResult.WHITE_WINS);
	}
	
	@Test 		//19
	public void blueWins() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(2, -1));
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		assertEquals(mr, MoveResult.BLACK_WINS);
	}
	
	@Test	//20
	public void bothPlayersWinAtTheSameTimes() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(2, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		assertEquals(mr, MoveResult.DRAW);
	}
	
	@Test (expected=HantoException.class)		//21
	public void redWinsAndStillPlays() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		
		game.makeMove(SPARROW, null, makeCoordinate(2, 1));
	}
	
	@Test (expected=HantoException.class)		//22
	public void blueWinsAndStillPlays() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(2, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		
		game.makeMove(SPARROW, null, makeCoordinate(2, 1));
	}
	
	@Test (expected=HantoException.class)	//23
	public void bothPlayersWinAtTheSameTimesAndKeepPlaying() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(2, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		
		game.makeMove(SPARROW, null, makeCoordinate(2, 1));
	}
	
	@Test	// 24
	public void redPlacesFirstAtOrigin() throws HantoException
	{
		final MoveResult mr = redGame.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = redGame.getPieceAt(makeCoordinate(0, 0));
		assertEquals(WHITE, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test(expected=HantoException.class)	// 25
	public void triestToMove() throws HantoException
	{
		redGame.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		redGame.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		redGame.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, 1));
		
	}
	
	// Helper methods
	private HantoCoordinate makeCoordinate(int x, int y)
	{
		return new TestHantoCoordinate(x, y);
	}
}
