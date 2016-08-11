package hanto.studentrno.epsilon;
/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

import static hanto.common.HantoPieceType.*;
import static hanto.common.MoveResult.*;
import static hanto.common.HantoPlayerColor.*;
import static org.junit.Assert.*;
import hanto.common.*;
import hanto.studentrnorlando.common.game.HantoBaseGame;
import hanto.studentrnorlando.factory.HantoGameFactory;

import org.junit.*;

/**
 * Test cases for gamma Hanto.
 * @version Sep 14, 2014
 */
public class EpsilonHantoMasterTest
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
		game = factory.makeHantoGame(HantoGameID.EPSILON_HANTO);
		redGame = factory.makeHantoGame(HantoGameID.EPSILON_HANTO, WHITE);
	}
	
	//Borrowed tests from Beta ~
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
	public void bluePlaceInitialDoveAtOrigin() throws HantoException
	{
		game.makeMove(DOVE, null, makeCoordinate(0, 0));
	}
	
	@Test(expected=HantoException.class) // 5
	public void bluePlaceInitialNotAtOrigin() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
	}
	
	@Test(expected=HantoException.class) // 5.1
	public void redPlaceInitialNotAtOrigin() throws HantoException
	{
		redGame.makeMove(SPARROW, null, makeCoordinate(0, 1));
	}
	
	@Test(expected=HantoException.class) // 5.2
	public void redPlaceInitialVeryMuchNotAtOrigin() throws HantoException
	{
		redGame.makeMove(SPARROW, null, makeCoordinate(10, 11));
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
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLACK, p.getColor());
		assertEquals(SPARROW, p.getType());
		
		p = game.getPieceAt(makeCoordinate(1, 0));
		assertEquals(WHITE, p.getColor());
		assertEquals(SPARROW, p.getType());
		
		p = game.getPieceAt(makeCoordinate(-1, 0));
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
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(3, 0));
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
		MoveResult mr = null;
		for(int turns = 1; turns <= 6; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(-1, 0)); break;
				case(3): game.makeMove(CRAB, null, makeCoordinate(-1, 1)); break;
				case(4): game.makeMove(HORSE, null, makeCoordinate(0, -1)); break;
				case(5): game.makeMove(SPARROW, null, makeCoordinate(1, -2)); break;
				case(6): game.makeMove(SPARROW, makeCoordinate(1, -2), makeCoordinate(1, -1)); break;
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0)); break;
				case(2): game.makeMove(CRAB, null, makeCoordinate(1, 1)); break;
				case(3): game.makeMove(HORSE, null, makeCoordinate(1, 2)); break;
				case(4): game.makeMove(SPARROW, null, makeCoordinate(2, -1)); break;
				case(5): game.makeMove(SPARROW, null, makeCoordinate(0, 2)); break;
				case(6): mr = game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(0, 1)); break;
			}
		}
		assertEquals(mr, MoveResult.WHITE_WINS);
	}
	
	@Test 		//19
	public void blueWins() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, makeCoordinate(-1, 1), makeCoordinate(0, 1));
		game.makeMove(HORSE, null, makeCoordinate(2, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(HORSE, null, makeCoordinate(2, 0));
		final MoveResult mr = game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(1, -1));
		
		assertEquals(mr, MoveResult.BLACK_WINS);
	}
	
	@Test	//20
	public void bothPlayersWinAtTheSameTimes() throws HantoException
	{
		MoveResult mr = null;
		for(int turns = 1; turns <= 6; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(-1, 0)); break;
				case(3): game.makeMove(CRAB, null, makeCoordinate(-1, 1)); break;
				case(4): game.makeMove(HORSE, null, makeCoordinate(0, -1)); break;
				case(5): game.makeMove(SPARROW, null, makeCoordinate(1, -2)); break;
				case(6): game.makeMove(SPARROW, makeCoordinate(1, -2), makeCoordinate(1, -1)); break;
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0)); break;
				case(2): game.makeMove(CRAB, null, makeCoordinate(1, 1)); break;
				case(3): game.makeMove(SPARROW, null, makeCoordinate(2, 0)); break;
				case(4): game.makeMove(HORSE, null, makeCoordinate(2, -1)); break;
				case(5): game.makeMove(SPARROW, null, makeCoordinate(0, 2)); break;
				case(6): mr = game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(0, 1)); break;
			}
		}
		assertEquals(mr, MoveResult.DRAW);
	}
	
	@Test (expected=HantoException.class)		//21
	public void redWinsAndStillPlays() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, 0));
		game.makeMove(CRAB, null, makeCoordinate(0, 1));
		game.makeMove(CRAB, null, makeCoordinate(0, -1));
		game.makeMove(CRAB, null, makeCoordinate(1, -1));
		game.makeMove(HORSE, null, makeCoordinate(-1, 1));
		
		game.makeMove(CRAB, null, makeCoordinate(2, 1));
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
	
	//Test added for gamma Hanto
	
	@Test	//25
	public void winOnLastMoveRed() throws HantoException 
	{	
		MoveResult mr = null;
		for(int turns = 1; turns <= 10; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(-1, 0)); break;
				case(3): game.makeMove(CRAB, null, makeCoordinate(-1, 1)); break;
				case(4): game.makeMove(HORSE, null, makeCoordinate(0, -1)); break;
				case(5): game.makeMove(SPARROW, null, makeCoordinate(-2, 1)); break;
				case(6): game.makeMove(SPARROW, makeCoordinate(-2, 1), makeCoordinate(-2, 0)); break;
				case(7): game.makeMove(SPARROW, makeCoordinate(-2, 0), makeCoordinate(-1, -1)); break;
				case(8): game.makeMove(SPARROW, makeCoordinate(-1, -1), makeCoordinate(0, -2)); break;
				case(9): game.makeMove(SPARROW, makeCoordinate(0, -2), makeCoordinate(1, -2)); break;
				case(10): mr = game.makeMove(SPARROW, makeCoordinate(1, -2), makeCoordinate(1, -1)); break;
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0)); break;
				case(2): game.makeMove(CRAB, null, makeCoordinate(2, 0)); break;
				case(3): game.makeMove(HORSE, null, makeCoordinate(1, 1)); break;
				case(4): game.makeMove(SPARROW, null, makeCoordinate(1, 2)); break;
				case(5):  game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(0, 2)); break;
				case(6): game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(1, 2)); break;
				case(7): game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(0, 2)); break;
				case(8): game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(1, 2)); break;
				case(9):  game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(0, 2)); break;
				case(10): mr = game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(0, 1)); break;
			}
		}
		assertEquals(MoveResult.WHITE_WINS, mr);
	}
	
	@Test //26
	public void butterflyWalksPieceMoves() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(1, -1));
		
		final HantoPiece p = game.getPieceAt(makeCoordinate(1, -1));
		assertEquals(BLACK, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test //27
	public void sparrowWalksOldPieceGone() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(1, -1));
		
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(p, null);
	}
	
	@Test (expected=HantoException.class) // 28
	public void sparrowWalksOnToOtherSparrow() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(1, 0));
	}
	
	@Test (expected=HantoException.class) // 29
	public void sparrowPlacedAway() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 0));
		
	}
	
	@Test (expected=HantoException.class) // 30
	public void sparrowWalksAway() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(-1, 0));
	}
	
	@Test(expected=HantoException.class)  //31
	public void BluePlaysNextToRed() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
	}
	
	@Test(expected=HantoException.class) //32
	public void pieceJumpsNotWalks() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(2, 0));
	}
	
	@Test(expected=HantoException.class) //33
	public void trystoMoveAPieceFirst() throws HantoException
	{
		game.makeMove(SPARROW, makeCoordinate(1,0), makeCoordinate(0, 0));
	}
	
	@Test(expected=HantoException.class) //34
	public void movesPieceThatIsNotThere() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 1), makeCoordinate(1, 1));
	}
	
	@Test(expected=HantoException.class) //35
	public void movesPieceThatIsOtherPlayers() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, makeCoordinate(1, 0), makeCoordinate(0, 1));
	}
	
	@Test(expected=HantoException.class) //36
	public void movesPieceThatIsOtherAnotheerType() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, 1));
	}
	
	//		   A
	//		 F   B
	//		 E   C
	// 		   D
	
	//For 2 cases
	
	@Test(expected=HantoException.class)	//37
	public void breakConnectionAtoD() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(0, 1));
	}
	
	@Test(expected=HantoException.class)	//38
	public void breakConnectionFtoD() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(0, 1));
	}
	
	@Test(expected=HantoException.class)	//39
	public void breakConnectionBtoD() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(-1, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(0, 1));
	}
	
	@Test	//40
	public void noBreakConnectionCtoD() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, 0));
		game.makeMove(BUTTERFLY, makeCoordinate(0, -1), makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(3, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(0, 1));
	}
	
	//3 cases
	
	@Test(expected=HantoException.class)	//41
	public void breakConnectionFAtoD() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(3, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(0, 1));
	}
	
	@Test(expected=HantoException.class)	//42
	public void breakConnectionFtoBtoD() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(3, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(0, 1));
	}
	
	@Test					//43
	public void noBreakConnectionBCD() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 1));
		game.makeMove(BUTTERFLY, makeCoordinate(0, -1), makeCoordinate(1, -1));
		game.makeMove(BUTTERFLY, makeCoordinate(1, 1), makeCoordinate(0, 1));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(-1, 1));
	}
	
	//case for 4
	
	@Test				//44
	public void noBreakConectionAFED()throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(BUTTERFLY, makeCoordinate(2, -1), makeCoordinate(1, -1));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(0, 1));
	}
	
	@Test(expected=HantoException.class)	//45
	public void breakConnectionFABtoD() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(3, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(4, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(0, 1));
	}
	
	@Test(expected=HantoException.class)	//46
	public void breakConnectionFAtoCD() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(BUTTERFLY, makeCoordinate(2, -1), makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(0, 1));
	}
	
	// case 5
	@Test(expected=HantoException.class)	//47
	public void canNotMoveSurrondedBy5BCDEF() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(BUTTERFLY, makeCoordinate(2, -1), makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(3, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(0, 1));
	}
	
	@Test(expected=HantoException.class)	//48
	public void canNotMoveSurrondedBy5ABCDE() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 2));
		game.makeMove(BUTTERFLY, makeCoordinate(2, -1), makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(1, 1), makeCoordinate(0, 1));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(-1, 1));
	}
	
	@Test(expected=HantoException.class)	//49
	public void canNotMoveSurrondedBy5FABCD() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 2));
		game.makeMove(BUTTERFLY, makeCoordinate(2, -1), makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, makeCoordinate(1, 1), makeCoordinate(0, 1));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(-1, 0));
	}
	
	@Test(expected=HantoException.class)	//50
	public void canNotMoveSurrondedBy5EFABC() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 2));
		game.makeMove(BUTTERFLY, makeCoordinate(2, -1), makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 2));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, -1));
		game.makeMove(SPARROW, makeCoordinate(1, 1), makeCoordinate(0, 1));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(0, -1));
	}
	
	@Test(expected=HantoException.class)	//51
	public void canNotMoveSurrondedBy5DEFAB() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-2, -1));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(1, -1));
	}
	
	@Test(expected=HantoException.class)	//52
	public void canNotMoveSurrondedBy5CDEFA() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 0));
		game.makeMove(BUTTERFLY, makeCoordinate(2, -1), makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-2, -1));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(0, -1));
	}
	
	
	@Test(expected=HantoException.class)	//51
	public void canNotMoveSurrondedBy6() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(BUTTERFLY, makeCoordinate(2, -1), makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(1, 1), makeCoordinate(0, 1));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(0, 1));
	}
	
	
	
	@Test (expected=HantoException.class)	//54
	public void playsToManyButterFlies() throws HantoException
	{
		for(int turns = 1; turns <= 11; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 0)); break;
				case(3): game.makeMove(SPARROW, null, makeCoordinate(-1, 1)); break;
				case(4): game.makeMove(SPARROW, null, makeCoordinate(0, -1)); break;
				case(5): game.makeMove(SPARROW, null, makeCoordinate(-2, 1)); break;
				case(6): game.makeMove(SPARROW, makeCoordinate(-2, 1), makeCoordinate(-2, 0)); break;
				case(7): game.makeMove(SPARROW, makeCoordinate(-2, 0), makeCoordinate(-1, -1)); break;
				case(8): game.makeMove(SPARROW, makeCoordinate(-1, -1), makeCoordinate(0, -2)); break;
				case(9): game.makeMove(SPARROW, makeCoordinate(0, -2), makeCoordinate(1, -2)); break;
				case(10): game.makeMove(SPARROW, makeCoordinate(1, -2), makeCoordinate(1, -1)); break;
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(2, 0)); break;
				case(3): game.makeMove(SPARROW, null, makeCoordinate(1, 1)); break;
				case(4): game.makeMove(SPARROW, null, makeCoordinate(1, 2)); break;
				case(5):  game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(0, 2)); break;
				case(6): game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(1, 2)); break;
				case(7): game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(0, 2)); break;
				case(8): game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(1, 2)); break;
				case(9):  game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(0, 2)); break;
				case(10): game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(1, 2)); break;
			}
		}
	}
	
	@Test (expected=HantoException.class)	//54
	public void playsToManySparrows() throws HantoException
	{
		for(int turns = 1; turns <= 11; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(-1, 0)); break;
				case(3): game.makeMove(SPARROW, null, makeCoordinate(-1, 1)); break;
				case(4): game.makeMove(SPARROW, null, makeCoordinate(0, -1)); break;
				case(5): game.makeMove(SPARROW, null, makeCoordinate(-2, 1)); break;
				case(6): game.makeMove(SPARROW, null, makeCoordinate(0, -2)); break;
				case(7): game.makeMove(SPARROW, null, makeCoordinate(0, -3)); break;
				case(8): game.makeMove(SPARROW, makeCoordinate(-1, -1), makeCoordinate(0, -2)); break;
				case(9): game.makeMove(SPARROW, makeCoordinate(0, -2), makeCoordinate(1, -2)); break;
				case(10): game.makeMove(SPARROW, makeCoordinate(1, -2), makeCoordinate(1, -1)); break;
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(2, 0)); break;
				case(3): game.makeMove(SPARROW, null, makeCoordinate(1, 1)); break;
				case(4): game.makeMove(SPARROW, null, makeCoordinate(1, 2)); break;
				case(5):  game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(0, 2)); break;
				case(6): game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(1, 2)); break;
				case(7): game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(0, 2)); break;
				case(8): game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(1, 2)); break;
				case(9):  game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(0, 2)); break;
				case(10): game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(1, 2)); break;
			}
		}
	}
	
	@Test (expected=HantoException.class)	//55
	public void cantMoveWithTripleLoop() throws HantoException
	{
		for(int turns = 1; turns <= 11; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(-1, 1)); break;
				case(3): game.makeMove(SPARROW, null, makeCoordinate(-1, 2)); break;
				case(4): game.makeMove(SPARROW, null, makeCoordinate(-1, 3)); break;
				case(5): game.makeMove(SPARROW, makeCoordinate(-1, 3), makeCoordinate(0, 2)); break;
				case(6): game.makeMove(SPARROW, null, makeCoordinate(-1, 0)); break;
				case(7): game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1)); break;
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(1, 1)); break;
				case(3): game.makeMove(SPARROW, null, makeCoordinate(2, -1)); break;
				case(4): game.makeMove(SPARROW, null, makeCoordinate(2, -2)); break;
				case(5): game.makeMove(SPARROW, null, makeCoordinate(1, -2)); break;
				case(6): game.makeMove(SPARROW, null, makeCoordinate(2, 0)); break;
				case(7): game.makeMove(BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(1, -1)); break;
			}
		}
	}
	
	@Test (expected=HantoException.class) //56
	public void movesBeforeButterFlyPlace() throws HantoException
	{
		for(int turns = 1; turns <= 2; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(SPARROW, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(SPARROW, makeCoordinate(0,0), makeCoordinate(0, 1)); break;
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0)); break;
			}
		}
	}
	
	@Test 							 //57
	public void redPlaysFirstPeice() throws HantoException
	{
		final MoveResult mr = redGame.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = redGame.getPieceAt(makeCoordinate(0, 0));
		assertEquals(WHITE, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	// ~ New tests for Delta Hanto
	@Test (expected=HantoException.class) 	//58
	public void playsTooManySparrows() throws HantoException
	{
		for(int turns = 1; turns <= 11; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(-1, 1)); break;
				case(3): game.makeMove(SPARROW, null, makeCoordinate(-1, 2)); break;
				case(4): game.makeMove(SPARROW, null, makeCoordinate(-1, 3)); break;
				case(5): game.makeMove(SPARROW, null, makeCoordinate(-2, 1)); break;
				case(6): game.makeMove(SPARROW, null, makeCoordinate(-2, 2)); break;
				case(7): game.makeMove(SPARROW, null, makeCoordinate(-2, 3)); break;
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(1, 1)); break;
				case(3): game.makeMove(SPARROW, null, makeCoordinate(2, -1)); break;
				case(4): game.makeMove(SPARROW, null, makeCoordinate(2, -2)); break;
				case(5): game.makeMove(SPARROW, null, makeCoordinate(1, -2)); break;
				case(6): game.makeMove(SPARROW, null, makeCoordinate(2, 0)); break;
				case(7): game.makeMove(BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(1, -1)); break;
			}
		}
	}
	
	@Test (expected=HantoException.class)				 	//61
	public void playerResignsAndKeepsPlayin() throws HantoException
	{
		MoveResult mr = game.makeMove(null, null, null);
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		
		assertEquals(mr, MoveResult.WHITE_WINS);
	}
	
	@Test (expected=HantoException.class) 	//62
	public void playsTooManyCrab() throws HantoException
	{
		for(int turns = 1; turns <= 11; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(CRAB, null, makeCoordinate(-1, 1)); break;
				case(3): game.makeMove(CRAB, null, makeCoordinate(-1, 2)); break;
				case(4): game.makeMove(CRAB, null, makeCoordinate(-1, 3)); break;
				case(5): game.makeMove(CRAB, null, makeCoordinate(-2, 1)); break;
				case(6): game.makeMove(CRAB, null, makeCoordinate(-2, 2)); break;
				case(7): game.makeMove(CRAB, null, makeCoordinate(-2, 3)); break;
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(1, 1)); break;
				case(3): game.makeMove(SPARROW, null, makeCoordinate(2, -1)); break;
				case(4): game.makeMove(SPARROW, null, makeCoordinate(2, -2)); break;
				case(5): game.makeMove(SPARROW, null, makeCoordinate(1, -2)); break;
				case(6): game.makeMove(CRAB, null, makeCoordinate(2, 0)); break;
				case(7): game.makeMove(BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(1, -1)); break;
			}
		}
	}
	
	@Test //(expected=HantoException.class) 	//63
	public void playsSomeCrabs() throws HantoException
	{
		for(int turns = 1; turns <= 11; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(CRAB, null, makeCoordinate(-1, 1)); break;
				case(3): game.makeMove(CRAB, null, makeCoordinate(-1, 2)); break;
				case(4): game.makeMove(SPARROW, null, makeCoordinate(-1, 3)); break;
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(1, 1)); break;
				case(3): game.makeMove(CRAB, null, makeCoordinate(2, -1)); break;
				case(4): game.makeMove(SPARROW, null, makeCoordinate(2, -2)); break;
			}
		}
			
	}
	
	@Test //(expected=HantoException.class) 	//64
	public void movesSparrow1() throws HantoException
	{
		for(int turns = 1; turns <= 11; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(-1, 1)); break;
				case(3): game.makeMove(SPARROW,  makeCoordinate(-1, 1), makeCoordinate(0, 1)); break;
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(1, 1)); break;
				case(3): game.makeMove(CRAB, null, makeCoordinate(2, -1)); break;
			}
		}
			
	} 
	
	@Test //(expected=HantoException.class) 	//65
	public void movesSparrow2() throws HantoException
	{
		for(int turns = 1; turns <= 11; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(-1, 1)); break;
				case(3): game.makeMove(SPARROW,  makeCoordinate(-1, 1), makeCoordinate(2, 0)); break;
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(1, 1)); break;
			}
		}
			
	} 
	
	@Test 					//66
	public void movesSparrowFlysFar() throws HantoException
	{
		for(int turns = 1; turns <= 11; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(CRAB, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 0)); break;
				case(3): game.makeMove(CRAB, null, makeCoordinate(-1, 1)); break;
				case(4): game.makeMove(SPARROW, null, makeCoordinate(-2, 1)); break;
				case(5): game.makeMove(CRAB, null, makeCoordinate(-3, 1)); break;
				case(6): game.makeMove(SPARROW, null, makeCoordinate(-1, 2)); break;
				case(7): game.makeMove(BUTTERFLY, makeCoordinate(-1, 0), makeCoordinate(-2, 0)); break;
				case(8): game.makeMove(SPARROW,  makeCoordinate(-1, 2), makeCoordinate(1, -1)); break;
			}
			switch(turns)
			{
				case(1): game.makeMove(SPARROW, null, makeCoordinate(1, 0)); break;
				case(2): game.makeMove(BUTTERFLY, null, makeCoordinate(2, -1)); break;
				case(3): game.makeMove(BUTTERFLY, makeCoordinate(2, -1), makeCoordinate(1, -1)); break;
				case(4): game.makeMove(BUTTERFLY, makeCoordinate(1, -1), makeCoordinate(0, -1)); break;
				case(5): game.makeMove(CRAB, null, makeCoordinate(2, -1)); break;
				case(6): game.makeMove(SPARROW, null, makeCoordinate(2, -2)); break;
				case(7): game.makeMove(CRAB, null, makeCoordinate(1, -2)); break;
			}
		}
			
	} 
	
	@Test (expected=HantoException.class)				//67
	public void movesSparrowToBreakTheBorad() throws HantoException
	{
		for(int turns = 1; turns <= 11; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(SPARROW, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 0)); break;
				case(3): game.makeMove(CRAB, null, makeCoordinate(-1, 1)); break;
				case(4): game.makeMove(SPARROW, null, makeCoordinate(-2, 1)); break;
				case(5): game.makeMove(SPARROW, null, makeCoordinate(-3, 1)); break;
				case(6): game.makeMove(CRAB, null, makeCoordinate(-4, 1)); break;
				case(7): game.makeMove(BUTTERFLY, makeCoordinate(-1, 0), makeCoordinate(-2, 0)); break;
				case(8): game.makeMove(SPARROW,  makeCoordinate(-3, 1), makeCoordinate(1, -1)); break;
			}
			switch(turns)
			{
				case(1): game.makeMove(SPARROW, null, makeCoordinate(1, 0)); break;
				case(2): game.makeMove(BUTTERFLY, null, makeCoordinate(2, -1)); break;
				case(3): game.makeMove(BUTTERFLY, makeCoordinate(2, -1), makeCoordinate(1, -1)); break;
				case(4): game.makeMove(BUTTERFLY, makeCoordinate(1, -1), makeCoordinate(0, -1)); break;
				case(5): game.makeMove(SPARROW, null, makeCoordinate(2, -1)); break;
				case(6): game.makeMove(SPARROW, null, makeCoordinate(2, -2)); break;
				case(7): game.makeMove(CRAB, null, makeCoordinate(1, -2)); break;
			}
		}
			
	} 
	
	@Test (expected=HantoException.class)				//68
	public void movesSparrowFliesOnTopOfPiece() throws HantoException
	{
		for(int turns = 1; turns <= 11; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(SPARROW, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 0)); break;
				case(3): game.makeMove(CRAB, null, makeCoordinate(-1, 1)); break;
				case(4): game.makeMove(SPARROW, null, makeCoordinate(-2, 1)); break;
				case(5): game.makeMove(SPARROW, null, makeCoordinate(-3, 1)); break;
				case(6): game.makeMove(CRAB, null, makeCoordinate(-4, 1)); break;
				case(7): game.makeMove(BUTTERFLY, makeCoordinate(-1, 0), makeCoordinate(-2, 0)); break;
				case(8): game.makeMove(SPARROW,  makeCoordinate(-3, 1), makeCoordinate(1, 0)); break;
			}
			switch(turns)
			{
				case(1): game.makeMove(SPARROW, null, makeCoordinate(1, 0)); break;
				case(2): game.makeMove(BUTTERFLY, null, makeCoordinate(2, -1)); break;
				case(3): game.makeMove(BUTTERFLY, makeCoordinate(2, -1), makeCoordinate(1, -1)); break;
				case(4): game.makeMove(BUTTERFLY, makeCoordinate(1, -1), makeCoordinate(0, -1)); break;
				case(5): game.makeMove(SPARROW, null, makeCoordinate(2, -1)); break;
				case(6): game.makeMove(SPARROW, null, makeCoordinate(2, -2)); break;
				case(7): game.makeMove(CRAB, null, makeCoordinate(1, -2)); break;
			}
		}
			
	} 
	
	
	@Test			//73
	public void horseLongJumps() throws HantoException
	{
		for(int turns = 1; turns <= 4; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(HORSE, null, makeCoordinate(0, -1)); break;
				case(3): game.makeMove(HORSE, makeCoordinate(0, -1), makeCoordinate(0, 3)); break;
				
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(0, 2)); break;
			}
		}
			
	}
	
	@Test (expected=HantoException.class)	 			//74
	public void movesButterFlywhereThereIsNone() throws HantoException
	{
		for(int turns = 1; turns <= 11; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(-1, 1)); break;
				
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1)); break;
				case(2): game.makeMove(HORSE, null, makeCoordinate(0, 2)); break;
			}
		}
			
	}
	
	@Test			//75
	public void horseShortJumps() throws HantoException
	{
		for(int turns = 1; turns <= 4; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(HORSE, null, makeCoordinate(-1, 0)); break;
				case(3): game.makeMove(HORSE, makeCoordinate(-1, 0), makeCoordinate(1, 0)); break;
				
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(0, 2)); break;
			}
		}
			
	}
	
	@Test			//75.1
	public void horseShortJumpsNorth() throws HantoException
	{
		for(int turns = 1; turns <= 4; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(HORSE, null, makeCoordinate(0, -1)); break;
				case(3): game.makeMove(HORSE, makeCoordinate(0, -1), makeCoordinate(0, 1)); break;
				
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 1)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(-2, 2)); break;
			}
		}
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 1));
		assertEquals(BLACK, p.getColor());
		assertEquals(HORSE, p.getType());
			
	}
	
	@Test			//75.2
	public void horseShortJumpsSouth() throws HantoException
	{
		for(int turns = 1; turns <= 4; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(HORSE, null, makeCoordinate(0, 1)); break;
				case(3): game.makeMove(HORSE, makeCoordinate(0, 1), makeCoordinate(0, -1)); break;
				
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(1, -1)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(2, -2)); break;
			}
		}
		
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, -1));
		assertEquals(BLACK, p.getColor());
		assertEquals(HORSE, p.getType());
			
	}
	
	@Test			//75.3
	public void horseShortJumpsSouthWest() throws HantoException
	{
		for(int turns = 1; turns <= 4; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(HORSE, null, makeCoordinate(1, 0)); break;
				case(3): game.makeMove(HORSE, makeCoordinate(1, 0), makeCoordinate(-1, 0)); break;
				
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(0, -2)); break;
			}
		}
		
		final HantoPiece p = game.getPieceAt(makeCoordinate(-1, 0));
		assertEquals(BLACK, p.getColor());
		assertEquals(HORSE, p.getType());
			
	}
	
	@Test			//75.4
	public void horseShortJumpsSouthEast() throws HantoException
	{
		for(int turns = 1; turns <= 4; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(HORSE, null, makeCoordinate(-1, 1)); break;
				case(3): game.makeMove(HORSE, makeCoordinate(-1, 1), makeCoordinate(1, -1)); break;
				
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(0, -2)); break;
			}
		}
		
		final HantoPiece p = game.getPieceAt(makeCoordinate(1, -1));
		assertEquals(BLACK, p.getColor());
		assertEquals(HORSE, p.getType());
			
	}
	
	@Test			//75.5
	public void horseShortJumpsNorthEast() throws HantoException
	{
		for(int turns = 1; turns <= 4; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(HORSE, null, makeCoordinate(-1, 0)); break;
				case(3): game.makeMove(HORSE, makeCoordinate(-1, 0), makeCoordinate(1, 0)); break;
				
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(0, 2)); break;
			}
		}
		
		final HantoPiece p = game.getPieceAt(makeCoordinate(1, 0));
		assertEquals(BLACK, p.getColor());
		assertEquals(HORSE, p.getType());
			
	}
	
	@Test			//75.6
	public void horseShortJumpsNorthWest() throws HantoException
	{
		for(int turns = 1; turns <= 4; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(HORSE, null, makeCoordinate(1, -1)); break;
				case(3): game.makeMove(HORSE, makeCoordinate(1, -1), makeCoordinate(-1, 1)); break;
				
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(0, 2)); break;
			}
		}
		
		final HantoPiece p = game.getPieceAt(makeCoordinate(-1, 1));
		assertEquals(BLACK, p.getColor());
		assertEquals(HORSE, p.getType());
			
	}
	
	@Test (expected=HantoException.class)	 //76
	public void horseAttemptsToWalksMoves() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(HORSE, null, makeCoordinate(0, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, 0));
		game.makeMove(HORSE, makeCoordinate(0, -1), makeCoordinate(1, -1));
		
		
		final HantoPiece p = game.getPieceAt(makeCoordinate(-1, 1));
		assertEquals(BLACK, p.getColor());
		assertEquals(HORSE, p.getType());
	}
	
	@Test (expected=HantoException.class)	 //76
	public void horseAttemptsToFlyMoves() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(HORSE, null, makeCoordinate(0, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, 0));
		game.makeMove(HORSE, makeCoordinate(0, -1), makeCoordinate(1, 1));
		

	}
	
	@Test	 //77
	public void crabWalksMoves() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(CRAB, null, makeCoordinate(1, 0));
		game.makeMove(CRAB, null, makeCoordinate(0, -1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(2, 0));
		game.makeMove(CRAB, makeCoordinate(0, -1), makeCoordinate(1, -1));
		
		
		final HantoPiece p = game.getPieceAt(makeCoordinate(1, -1));
		assertEquals(BLACK, p.getColor());
		assertEquals(CRAB, p.getType());
	}
	
	@Test (expected=HantoException.class)	 //76
	public void placesTooMAnyCrabs() throws HantoException
	{
		for(int turns = 1; turns <= 8; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(CRAB, null, makeCoordinate(-1, 0)); break;
				case(3): game.makeMove(CRAB, null, makeCoordinate(-2, 0)); break;
				case(4): game.makeMove(CRAB, null, makeCoordinate(-3, 0)); break;
				case(5): game.makeMove(CRAB, null, makeCoordinate(-4, 0)); break;
				case(6): game.makeMove(CRAB, null, makeCoordinate(-5, 0)); break;
				case(7): game.makeMove(CRAB, null, makeCoordinate(-6, 0)); break;
				case(8): game.makeMove(CRAB, null, makeCoordinate(-7, 0)); break;
				case(9): game.makeMove(CRAB, null, makeCoordinate(-8, 0)); break;
				
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1)); break;
				case(2): game.makeMove(CRAB, null, makeCoordinate(0, 2)); break;
				case(3): game.makeMove(CRAB, null, makeCoordinate(0, 3)); break;
				case(4): game.makeMove(CRAB, null, makeCoordinate(0, 4)); break;
				case(5): game.makeMove(CRAB, null, makeCoordinate(0, 5)); break;
				case(6): game.makeMove(CRAB, null, makeCoordinate(0, 6)); break;
				case(7): game.makeMove(CRAB, null, makeCoordinate(0, 7)); break;
				case(8): game.makeMove(CRAB, null, makeCoordinate(0, 8)); break;
			}
		}
	}
	
	@Test (expected=HantoException.class)	 //76
	public void placesTooManyHorses() throws HantoException
	{
		for(int turns = 1; turns <= 8; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(HORSE, null, makeCoordinate(-1, 0)); break;
				case(3): game.makeMove(HORSE, null, makeCoordinate(-2, 0)); break;
				case(4): game.makeMove(HORSE, null, makeCoordinate(-3, 0)); break;
				case(5): game.makeMove(HORSE, null, makeCoordinate(-4, 0)); break;
				case(6): game.makeMove(HORSE, null, makeCoordinate(-5, 0)); break;
				case(7): game.makeMove(HORSE, null, makeCoordinate(-6, 0)); break;
				case(8): game.makeMove(HORSE, null, makeCoordinate(-7, 0)); break;
				case(9): game.makeMove(HORSE, null, makeCoordinate(-8, 0)); break;
				
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1)); break;
				case(2): game.makeMove(HORSE, null, makeCoordinate(0, 2)); break;
				case(3): game.makeMove(HORSE, null, makeCoordinate(0, 3)); break;
				case(4): game.makeMove(HORSE, null, makeCoordinate(0, 4)); break;
				case(5): game.makeMove(HORSE, null, makeCoordinate(0, 5)); break;
				case(6): game.makeMove(HORSE, null, makeCoordinate(0, 6)); break;
				case(7): game.makeMove(HORSE, null, makeCoordinate(0, 7)); break;
				case(8): game.makeMove(HORSE, null, makeCoordinate(0, 8)); break;
			}
		}
	}
	
	@Test (expected=HantoException.class)	 //76
	public void sparrowFliesTooFar() throws HantoException
	{
		for(int turns = 1; turns <= 8; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(HORSE, null, makeCoordinate(-1, 0)); break;
				case(3): game.makeMove(HORSE, null, makeCoordinate(-2, 0)); break;
				case(4): game.makeMove(HORSE, null, makeCoordinate(-3, 0)); break;
				case(5): game.makeMove(SPARROW, null, makeCoordinate(-4, 0)); break;
				case(6): game.makeMove(SPARROW, makeCoordinate(-4, 0), makeCoordinate(0, 6)); break;
				
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1)); break;
				case(2): game.makeMove(HORSE, null, makeCoordinate(0, 2)); break;
				case(3): game.makeMove(HORSE, null, makeCoordinate(0, 3)); break;
				case(4): game.makeMove(HORSE, null, makeCoordinate(0, 4)); break;
				case(5): game.makeMove(HORSE, null, makeCoordinate(0, 5)); break;
			}
		}
	}
	
	
	@Test				//78
	public void resignsIsAcceptedLine() throws HantoException
	{
		MoveResult p = null;
		for(int turns = 1; turns <= 8; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(HORSE, null, makeCoordinate(-1, 0)); break;
				case(3): game.makeMove(HORSE, makeCoordinate(-1, 0), makeCoordinate(2, 0)); break;
				
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1)); break;
				case(2): game.makeMove(BUTTERFLY, makeCoordinate(0, 1), makeCoordinate(1, 0)); break;
				case(3): p = game.makeMove(null, null, null); break;
			}
		}
		
		assertEquals(p, MoveResult.BLACK_WINS);
		
	}
	
	@Test (expected=HantoPrematureResignationException.class)			//77
	public void resignsButHasPlacement() throws HantoException
	{
		for(int turns = 1; turns <= 8; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(SPARROW, null, makeCoordinate(-1, 0)); break;
				case(3): game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(1, 1)); break;
				
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0)); break;
				case(2): game.makeMove(BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(0, 1)); break;
				case(3): game.makeMove(null, null, null); break;
			}
		}
		
	}
	
	@Test (expected=HantoPrematureResignationException.class)			//78
	public void resignsButHasMove() throws HantoException
	{
		for(int turns = 1; turns <= 8; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(CRAB, null, makeCoordinate(0, 1)); break;
				case(3): game.makeMove(HORSE, null, makeCoordinate(-1, 1)); break;
				case(4): game.makeMove(HORSE, makeCoordinate(-1, 1), makeCoordinate(1, 1)); break;
				case(5): game.makeMove(SPARROW, null, makeCoordinate(-1, 1)); break;
				case(6): game.makeMove(SPARROW, makeCoordinate(-1, 1), makeCoordinate(1, -1)); break;
				
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1)); break;
				case(2): game.makeMove(BUTTERFLY, makeCoordinate(0, -1), makeCoordinate(1, -1)); break;
				case(3): game.makeMove(BUTTERFLY, makeCoordinate(1, -1), makeCoordinate(1, 0)); break;
				case(4): game.makeMove(BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(1, -1)); break;
				case(5): game.makeMove(BUTTERFLY, makeCoordinate(1, -1), makeCoordinate(1, 0)); break;
				case(6): game.makeMove(null, null, null); break;
			}
		}
		
	}
	
	@Test (expected=HantoPrematureResignationException.class)			//79
	public void resignsOnSecoundMove() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); 
		game.makeMove(null, null, null);
	}
	
	@Test (expected=HantoPrematureResignationException.class)			//80
	public void resignsOnFirstMove() throws HantoException
	{
		game.makeMove(null, null, null);
	}
	
	@Test (expected=HantoPrematureResignationException.class)			//81
	public void resignsButHasMoveandPlacement() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); 
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0)); 
		game.makeMove(CRAB, null, makeCoordinate(-1, 0)); 
		game.makeMove(null, null, null);
	}
	
	@Test 		//82
	public void resignsHasNoMovesofPieceButCanPlaceButOutofPieces() throws HantoException
	{
		
		for(int turns = 1; turns <= 15; turns++)
		{
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); break;
				case(2): game.makeMove(HORSE, null, makeCoordinate(-1, 0)); break;
				case(3): game.makeMove(HORSE, null, makeCoordinate(-2, 0)); break;
				case(4): game.makeMove(HORSE, null, makeCoordinate(-3, 0)); break;
				case(5): game.makeMove(HORSE, null, makeCoordinate(-4, 0)); break;
				case(6): game.makeMove(CRAB, null, makeCoordinate(-5, 0)); break;
				case(7): game.makeMove(CRAB, null, makeCoordinate(-6, 0)); break;
				case(8): game.makeMove(CRAB, null, makeCoordinate(-7, 0)); break;
				case(9): game.makeMove(CRAB, null, makeCoordinate(-8, 0)); break;
				case(10): game.makeMove(CRAB, null, makeCoordinate(-9, 0)); break;
				case(11): game.makeMove(CRAB, null, makeCoordinate(-10, 0)); break;
				case(12): game.makeMove(SPARROW, null, makeCoordinate(-11, 0)); break;
				case(13): game.makeMove(SPARROW, null, makeCoordinate(-12, 0)); break;
				case(14): game.makeMove(null, null, null); break;
				
			}
			switch(turns)
			{
				case(1): game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0)); break;
				case(2): game.makeMove(BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(1, -1)); break;
				case(3): game.makeMove(BUTTERFLY, makeCoordinate(1, -1), makeCoordinate(1, 0)); break;
				case(4): game.makeMove(BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(1, -1)); break;
				case(5): game.makeMove(BUTTERFLY, makeCoordinate(1, -1), makeCoordinate(1, 0)); break;
				case(6): game.makeMove(HORSE, null, makeCoordinate(2, 0)); break;
				case(7): game.makeMove(HORSE, null, makeCoordinate(3, 0)); break;
				case(8): game.makeMove(SPARROW, null, makeCoordinate(4, 0)); break;
				case(9): game.makeMove(HORSE, null, makeCoordinate(5, 0)); break;
				case(10): game.makeMove(CRAB, null, makeCoordinate(6, 0)); break;
				case(11): game.makeMove(CRAB, null, makeCoordinate(7, 0)); break;
				case(12): game.makeMove(HORSE, null, makeCoordinate(8, 0)); break;
				case(13): game.makeMove(HORSE, makeCoordinate(8, 0), makeCoordinate(-13, 0)); break;
			}
		}
		
	}
	
	
	
	
	
	// Helper methods
	private HantoCoordinate makeCoordinate(int x, int y)
	{
		return new TestHantoCoordinate(x, y);
	}
	
	

}
