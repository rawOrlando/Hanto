/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentrno.common.board;
import hanto.studentrnorlando.common.*;
import hanto.studentrnorlando.common.board.AdvancedHexHantoGameBoard;
import hanto.studentrnorlando.common.board.HexHantoGameBoard;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.*;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentrno.common.board.HexHantoGameBoardTest.TestHantoCoordinate;

public class AdvanceHexHantoGameBoardTest {

	private static AdvancedHexHantoGameBoard advanceBoard;
	
	
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
		
		@Override
		public boolean equals(Object o)
		{
			if(o.getClass().equals(this.getClass()))
			{
				return equals((TestHantoCoordinate)o);
			}
			
			return false;
		}
		
		public boolean equals(TestHantoCoordinate another)
		{
			if(another.getX() == this.getX() && another.getY() == this.getY())
			{
				return true;
			}
			return false;
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
		advanceBoard = new AdvancedHexHantoGameBoard(25, HantoGameID.DELTA_HANTO);
	}
	
	//Test Play Piece
	@Test //1
	public void playButterFly()
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(advanceBoard.getPieceAt(new TestHantoCoordinate(0,0)) != null);
	}
	
	@Test //2
	public void playSecoundMove()
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		advanceBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(advanceBoard.getPieceAt(new TestHantoCoordinate(1,0)) != null);
	}
	
	@Test //3
	public void playWhereThereIsAPiece()
	{
		//!!! what should hapen when you play ontop of a board
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(advanceBoard.getPieceAt(new TestHantoCoordinate(1,0)) == null);
		assertTrue(advanceBoard.getPieceAt(new TestHantoCoordinate(0,0)) != null);
		assertTrue(advanceBoard.getNieghbors(new TestHantoCoordinate(1,0)).size() == 1);
	}
	
	//Test checkValidPlayLocation
	@Test //4
	public void checkValidPlayLocationWhenNoPieceOnBoard() throws HantoException
	{
		assertTrue(advanceBoard.checkValidPlayLocation(new TestHantoCoordinate(0,0)));
	}
	
	@Test //5
	public void checkValidPlayLocationWhenOnoPieceOnBoard() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(advanceBoard.checkValidPlayLocation(new TestHantoCoordinate(1,0)));
	}
	@Test (expected=HantoException.class) //6
	public void checkValidPlayLocationPieceToFarAway() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		advanceBoard.checkValidPlayLocation(new TestHantoCoordinate(2,0));
	}
	
	@Test (expected=HantoException.class) //7
	public void checkValidPlayLocationPieceOnTopOfOtherPieces() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		advanceBoard.checkValidPlayLocation(new TestHantoCoordinate(0,0));
	}
	
	//Check Valid Place Location
	@Test //8
	public void checkValidPlaceLocationWhenNoPieceOnBoard() throws HantoException
	{
		assertTrue(advanceBoard.checkValidPlaceLocation(new TestHantoCoordinate(0,0), HantoPlayerColor.BLACK));
	}
	
	@Test //9
	public void checkValidPlaceLocationWhenOnoPieceOnBoard() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(advanceBoard.checkValidPlaceLocation(new TestHantoCoordinate(1,0), HantoPlayerColor.WHITE));
	}
	
	@Test (expected=HantoException.class)//10
	public void checkValidPlaceLocationWhenOnoPieceOnBoardOfSameColor() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(advanceBoard.checkValidPlaceLocation(new TestHantoCoordinate(1,0), HantoPlayerColor.BLACK));
	}
	
	@Test (expected=HantoException.class) //11
	public void checkValidPlaceLocationPieceToFarAway() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(advanceBoard.checkValidPlaceLocation(new TestHantoCoordinate(2,0), HantoPlayerColor.WHITE));
	}
	
	@Test (expected=HantoException.class) //12
	public void checkValidPlaceLocationPieceOnTopOfOtherPieces() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(advanceBoard.checkValidPlaceLocation(new TestHantoCoordinate(0,0), HantoPlayerColor.WHITE));
	}
	
	@Test // 13 
	public void checkValidPlaceLocationPieceBetweenTwoPieces() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		advanceBoard.playPiece(new TestHantoCoordinate(2,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(advanceBoard.checkValidPlaceLocation(new TestHantoCoordinate(1,0), HantoPlayerColor.WHITE));
	}
	@Test // 14 
	public void checkValidPlaceLocationPieceNextToTwoPieces() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		advanceBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(advanceBoard.checkValidPlaceLocation(new TestHantoCoordinate(1,-1), HantoPlayerColor.WHITE));
	}
	
	@Test (expected=HantoException.class)// 15 
	public void checkValidPlaceLocationPieceBetweenTwoPiecesWithInvalidColor() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		advanceBoard.playPiece(new TestHantoCoordinate(2,0), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
		assertTrue(advanceBoard.checkValidPlaceLocation(new TestHantoCoordinate(1,0), HantoPlayerColor.WHITE));
	}
	@Test (expected=HantoException.class)// 16 
	public void checkValidPlaceLocationPieceNextToTwoPiecesWithInvalidColor() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		advanceBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
		assertTrue(advanceBoard.checkValidPlaceLocation(new TestHantoCoordinate(1,-1), HantoPlayerColor.BLACK));
	}
	
	@Test (expected=HantoException.class)// 17 
	public void checkValidPlaceLocationPieceWithToTwoPiecesWithInvalidColor() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		advanceBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
		assertTrue(advanceBoard.checkValidPlaceLocation(new TestHantoCoordinate(-1,0), HantoPlayerColor.BLACK));
	}
	
	@Test // 18
	public void checkValidPlaceLocationPieceWithToTwoPieces() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		advanceBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
		assertTrue(advanceBoard.checkValidPlaceLocation(new TestHantoCoordinate(2,0), HantoPlayerColor.BLACK));
	}
	
	//Test move Piece
	@Test //19
	public void moveAPieceOneSpace() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		advanceBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
		advanceBoard.movePiece(new TestHantoCoordinate(0,0), new TestHantoCoordinate(1,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
	}
	
	@Test (expected=HantoException.class)//20
	public void moveAPieceOneSpaceWrongPieceType() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		advanceBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
		advanceBoard.movePiece(new TestHantoCoordinate(0,0), new TestHantoCoordinate(1,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.SPARROW));
	}
	
	@Test (expected=HantoException.class)//21
	public void moveAPieceOneSpaceWrongColor() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		advanceBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
		advanceBoard.movePiece(new TestHantoCoordinate(0,0), new TestHantoCoordinate(1,-1), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
	}
	
	@Test (expected=HantoException.class)//22
	public void moveAPieceButNoPieceThrere() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		advanceBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
		advanceBoard.movePiece(new TestHantoCoordinate(0,-1), new TestHantoCoordinate(1,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
	}
	
	//Test isBoardOkWithMove
	
	/*
	@Test //23
	public void checkMoveisOkWithBoardAPieceOneSpace() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY));
		advanceBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY));
		assertTrue(advanceBoard.isBoardOkWithPieceMove(new TestHantoCoordinate(0,0)));
	}
	
	@Test //24
	public void checkMoveisOkwithBoardBreakLine() throws HantoException
	{
		advanceBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY));
		advanceBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY));
		advanceBoard.playPiece(new TestHantoCoordinate(2,0), new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY));
		assertTrue(!advanceBoard.isBoardOkWithPieceMove(new TestHantoCoordinate(1,0)));
	}
	*/
	
	
}
