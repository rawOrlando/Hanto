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

public class HexHantoGameBoardTest {

	private static AdvancedHexHantoGameBoard hexBoard;
	
	
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
		hexBoard = new AdvancedHexHantoGameBoard(25, HantoGameID.BETA_HANTO);
	}
	
	@Test // 1
	public void getPieceWhenThereAreNone() 
	{
		assertEquals(hexBoard.getPieceAt(new TestHantoCoordinate(0,0)), null);
	}
	
	@Test // 2
	public void getPieceAtOrgin() 
	{
		hexBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertEquals(hexBoard.getPieceAt(new TestHantoCoordinate(0,0)).getType(), HantoPieceType.BUTTERFLY);
		assertEquals(hexBoard.getPieceAt(new TestHantoCoordinate(0,0)).getColor(), HantoPlayerColor.BLACK);
	}
	
	@Test //3
	public void getPieceFromNonOrginSpot()
	{
		hexBoard.playPiece(new TestHantoCoordinate(3,6), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.CRAB));
		assertEquals(hexBoard.getPieceAt(new TestHantoCoordinate(3,6)).getType(), HantoPieceType.CRAB);
		assertEquals(hexBoard.getPieceAt(new TestHantoCoordinate(3,6)).getColor(), HantoPlayerColor.BLACK);
	}
	
	// Test Next To
	@Test //4
	public void NextToNorth()
	{
		assertTrue(hexBoard.nextTo(new TestHantoCoordinate(0,0), new TestHantoCoordinate(1,0)));
	}
	
	@Test //5
	public void NextToNorthEast()
	{
		assertTrue(hexBoard.nextTo(new TestHantoCoordinate(0,0), new TestHantoCoordinate(1,-1)));
	}
	
	@Test //6
	public void NextToNorthWest()
	{
		assertTrue(hexBoard.nextTo(new TestHantoCoordinate(0,0), new TestHantoCoordinate(0,1)));
	}
	
	//Test isAdjacentToSomeOne
	@Test //7
	public void noOneNextToSpot()
	{
		hexBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(!hexBoard.isAdjacentToSomeOne((new TestHantoCoordinate(0,0))));
	}
	
	@Test //8
	public void someOneisNextToSpot()
	{
		hexBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(hexBoard.isAdjacentToSomeOne((new TestHantoCoordinate(1,0))));
	}
	
	@Test //9
	public void manynestTosomeOneisNextToSpot()
	{
		hexBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(1,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(hexBoard.isAdjacentToSomeOne((new TestHantoCoordinate(0,0))));
	}
	
	//test is Surronded
	
	@Test //10
	public void isNotSurronded()
	{
		hexBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(!hexBoard.isSurronded((new TestHantoCoordinate(1,0))));
	}
	
	@Test //11
	public void isSurronded()
	{
		hexBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(1,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(hexBoard.isSurronded((new TestHantoCoordinate(0,0))));
	}
	
	@Test //12
	public void isSurrondedPartly()
	{
		hexBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(!hexBoard.isSurronded((new TestHantoCoordinate(0,0))));
	}
	
	@Test //13
	public void SomeOneElseisSurrondedButNotThisSpot()
	{
		hexBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(1,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(!hexBoard.isSurronded((new TestHantoCoordinate(1,0))));
	}
	
	// Tests getsNeighbors()
	
	@Test //14
	public void getNieghborsWhenTheirAreNone()
	{
		assertTrue(hexBoard.getNieghbors(new TestHantoCoordinate(1,0)).size() == 0);
	}
	
	@Test //15
	public void getTheSingleNieghbor()
	{
		hexBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(hexBoard.getNieghbors(new TestHantoCoordinate(1,0)).size() == 1);
		assertTrue(hexBoard.getNieghbors(new TestHantoCoordinate(1,0)).get(0).getX() == 0);
		assertTrue(hexBoard.getNieghbors(new TestHantoCoordinate(1,0)).get(0).getY() == 0);
	}
	
	@Test //16
	public void getDoubleNieghbor()
	{
		hexBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(hexBoard.getNieghbors(new TestHantoCoordinate(0,0)).size() == 2);
	}
	
	@Test //17
	public void getTripleNieghbor()
	{
		hexBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(hexBoard.getNieghbors(new TestHantoCoordinate(0,0)).size() == 3);
	}
	
	@Test //18
	public void getQuadNieghbor()
	{
		hexBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(1,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(hexBoard.getNieghbors(new TestHantoCoordinate(0,0)).size() == 4);
	}
	
	@Test //19
	public void getPentaNieghbor()
	{
		hexBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(1,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(hexBoard.getNieghbors(new TestHantoCoordinate(0,0)).size() == 5);
	}
	
	@Test //20
	public void getHexaNieghbor()
	{
		hexBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(1,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(hexBoard.getNieghbors(new TestHantoCoordinate(0,0)).size() == 6);
	}
	
	
	
	/* figure out this later move pieces away and make sure get nieghbors works
	@Test //19
	public void getPentaNieghborOneMovedAway() throws HantoException
	{
		hexBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,0), new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,1), new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(1,-1), new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,-1), new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY));
		hexBoard.movePiece(new TestHantoCoordinate(1,0), new TestHantoCoordinate(1,1), new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY));
		assertTrue(hexBoard.getNieghbors(new TestHantoCoordinate(0,0)).size() == 4);
	}
	*/
	
	//Test getNieghbors Spots
	
	@Test //21
	public void getNieghborsSpotsWhenTheirAreNone()
	{
		assertTrue(hexBoard.getNieghboringSpots(new TestHantoCoordinate(1,0)).size() == 6);
	}
	
	@Test //22
	public void getTheSingleNieghborsSpots()
	{
		hexBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(hexBoard.getNieghboringSpots(new TestHantoCoordinate(1,0)).size() == 6);
	}
	
	@Test //23
	public void getDoubleNieghborsSpots()
	{
		hexBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(hexBoard.getNieghboringSpots(new TestHantoCoordinate(0,0)).size() == 6);
	}
	
	@Test //24
	public void getTripleNieghborsSpots()
	{
		hexBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(hexBoard.getNieghboringSpots(new TestHantoCoordinate(0,0)).size() == 6);
	}
	
	@Test //25
	public void getQuadNieghborsSpots()
	{
		hexBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(1,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(hexBoard.getNieghboringSpots(new TestHantoCoordinate(0,0)).size() == 6);
	}
	
	@Test //26
	public void getPentaNieghborsSpots()
	{
		hexBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(1,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(hexBoard.getNieghboringSpots(new TestHantoCoordinate(0,0)).size() == 6);
	}
	
	@Test //27
	public void getHexaNieghborsSpots()
	{
		hexBoard.playPiece(new TestHantoCoordinate(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(-1,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(1,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		hexBoard.playPiece(new TestHantoCoordinate(0,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertTrue(hexBoard.getNieghboringSpots(new TestHantoCoordinate(0,0)).size() == 6);
	}
	
	//Check to see if the right spots are there
	
	@Test //28
	public void getNieghborsSpotsRightsSpotsAboutOrigin()
	{
		List<TestHantoCoordinate> nieghbors = new ArrayList<TestHantoCoordinate>();
		for(HantoCoordinate cordinate :hexBoard.getNieghboringSpots(new TestHantoCoordinate(0,0)))
		{
			nieghbors.add(new TestHantoCoordinate(cordinate.getX(), cordinate.getY()));
		}
		assertTrue(nieghbors.contains(new TestHantoCoordinate(1, 0)));
		assertTrue(nieghbors.contains(new TestHantoCoordinate(1, -1)));
		assertTrue(nieghbors.contains(new TestHantoCoordinate(-1, 0)));
		assertTrue(nieghbors.contains(new TestHantoCoordinate(-1, 1)));
		assertTrue(nieghbors.contains(new TestHantoCoordinate(0, 1)));
		assertTrue(nieghbors.contains(new TestHantoCoordinate(0, -1)));
	}
	
	@Test //29
	public void getNieghborsSpotsRightsSpotsAbout33()
	{
		List<TestHantoCoordinate> nieghbors = new ArrayList<TestHantoCoordinate>();
		for(HantoCoordinate cordinate :hexBoard.getNieghboringSpots(new TestHantoCoordinate(3,3)))
		{
			nieghbors.add(new TestHantoCoordinate(cordinate.getX(), cordinate.getY()));
		}
		assertTrue(nieghbors.contains(new TestHantoCoordinate(4, 3)));
		assertTrue(nieghbors.contains(new TestHantoCoordinate(4, 2)));
		assertTrue(nieghbors.contains(new TestHantoCoordinate(2, 3)));
		assertTrue(nieghbors.contains(new TestHantoCoordinate(2, 4)));
		assertTrue(nieghbors.contains(new TestHantoCoordinate(3, 4)));
		assertTrue(nieghbors.contains(new TestHantoCoordinate(3, 2)));
	}
	
	//Test getDistance methods
	
	@Test //30
	public void DistanceBetweenSelf()
	{
		assertEquals(hexBoard.distanceBetween(new TestHantoCoordinate(3, 2), new TestHantoCoordinate(3, 2)), 0);
	}
	
	@Test //31
	public void DistanceOneAway()
	{
		assertEquals(hexBoard.distanceBetween(new TestHantoCoordinate(3, 3), new TestHantoCoordinate(3, 2)), 1);
	}
	
	@Test //32
	public void DistanceTwoAway()
	{
		assertEquals(hexBoard.distanceBetween(new TestHantoCoordinate(3, 4), new TestHantoCoordinate(3, 2)), 2);
	}
	
	@Test //33
	public void DistanceTwoAwayCurve()
	{
		assertEquals(hexBoard.distanceBetween(new TestHantoCoordinate(4, 3), new TestHantoCoordinate(3, 2)), 2);
	}
	
	//Test Print Board // add more
	
	@Test //34
	public void pirntNothingOnEmptyBoard()
	{
		assertEquals(hexBoard.getPrintableBoard(), "");
	}
	
	@Test //35
	public void pirntBoardOfOne()
	{
		hexBoard.playPiece(new TestHantoCoordinate(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertEquals(hexBoard.getPrintableBoard(), "(0, 0) Butterfly");
	}
	
}
