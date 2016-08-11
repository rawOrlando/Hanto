package hanto.studentrno.tournament;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPieceType.CRAB;
import static hanto.common.HantoPieceType.HORSE;
import static hanto.common.HantoPieceType.SPARROW;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.common.HantoCordinateImpl;
import hanto.studentrnorlando.tournament.HantoPlayer;
import hanto.tournament.HantoMoveRecord;

public class HantoTournamentMasterTest {
	HantoPlayer player;
	TestableHantoPlayer testPlayer;
	
	@Before
	public void setUp()
	{
		player = new HantoPlayer();
		testPlayer = new TestableHantoPlayer();
	}
	/*
	@Test // 1
	public void createsHantoPlayer()
	{
		HantoPlayer player = new HantoPlayer();
	}
	
	@Test // 2
	public void startsBetaGame()
	{
		player.startGame(HantoGameID.BETA_HANTO, HantoPlayerColor.BLACK, true);
	}
	
	@Test // 3
	public void startsGammaGame()
	{
		player.startGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLACK, true);
	}
	
	@Test // 4
	public void startsDeltaGame()
	{
		player.startGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.BLACK, true);
	}
	
	@Test // 5
	public void startsEpsilonGame()
	{
		player.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLACK, true);
	}
	
	@Test // 6
	public void startsRedGame()
	{
		player.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.WHITE, true);
	}
	
	@Test // 7
	public void startsMovesSecoundGame()
	{
		player.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.WHITE, false);
	}
	
	@Test // 6
	public void firstMoveAtOrigin()
	{
		player.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLACK, true);
		HantoMoveRecord rec = player.makeMove(null);
		assertTrue(rec.getTo().getY() == 0 && rec.getTo().getX() == 0);
		
	}
	
	@Test // 6
	public void secoundPlaceNextOrigin()
	{
		player.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLACK, false);
		HantoMoveRecord rec = player.makeMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(0,0)));
		assertTrue(rec.getTo().getY() < 2 && rec.getTo().getX() < 2 &&
				rec.getTo().getY() > -2 && rec.getTo().getX() > -2
				);
		
	}
	
	@Test // 6.1
	public void secoundPlaceNextOriginBeta()
	{
		player.startGame(HantoGameID.BETA_HANTO, HantoPlayerColor.BLACK, false);
		HantoMoveRecord rec = player.makeMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(0,0)));
		assertTrue(rec.getTo().getY() < 2 && rec.getTo().getX() < 2 &&
				rec.getTo().getY() > -2 && rec.getTo().getX() > -2
				);
		
	}
	
	@Test // 6.2
	public void secoundPlaceAfterAMissPlay()
	{
		player.startGame(HantoGameID.BETA_HANTO, HantoPlayerColor.BLACK, false);
		HantoMoveRecord rec = player.makeMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(3,-43)));
		assertTrue(rec == null);
		
	}
	
	@Test // 7
	public void thirdMoveExists()
	{
		player.startGame(HantoGameID.BETA_HANTO, HantoPlayerColor.BLACK, true);
		player.makeMove(null);
		HantoMoveRecord rec = player.makeMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(1,0)));
		assertTrue(rec != null);
		
	}
	
	@Test // 7.1
	public void thirdMoveIsToSomePlace()
	{
		player.startGame(HantoGameID.BETA_HANTO, HantoPlayerColor.BLACK, true);
		player.makeMove(null);
		HantoMoveRecord rec = player.makeMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(1,0)));
		assertTrue(rec.getTo() != null);
		
	}
	
	@Test // 7.2
	public void thirdMovehasAPiece()
	{
		player.startGame(HantoGameID.BETA_HANTO, HantoPlayerColor.BLACK, true);
		player.makeMove(null);
		HantoMoveRecord rec = player.makeMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(1,0)));
		System.out.println("BETA:" +rec.getPiece() + rec.getTo().getX() +rec.getTo().getY());
		assertTrue(rec.getPiece() != null);
		
	}
	*/
	@Test // 7.2
	public void thirdMoveEpslion()
	{
		testPlayer.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLACK, true);
		testPlayer.makeMove(null);
		System.out.println(testPlayer.printBoard());
		HantoMoveRecord rec = testPlayer.makeMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(1,0)));
		System.out.println(testPlayer.printBoard());
		System.out.println("EPSLION:" +rec.getPiece() + rec.getTo().getX() +rec.getTo().getY());
		System.out.println(testPlayer.printBoard());
		assertTrue(rec != null);
		assertTrue(rec.getPiece() != null);
		assertTrue(rec.getTo() != null);
		
	}
	

	@Test // 7.3
	public void thirdWithNoButterFlies()
	{
		testPlayer.startGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLACK, true);
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(0,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(1,0))));
		HantoMoveRecord rec = testPlayer.makeMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(-1,0)));
		assertTrue(rec != null);
		assertTrue(rec.getPiece() != null);
		assertTrue(rec.getTo() != null);
		
	}
	
	@Test // 7.3
	public void thirdMoveInvalid()
	{
		testPlayer.startGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLACK, true);
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(0,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(1,0))));
		HantoMoveRecord rec = testPlayer.makeMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, null));
		assertTrue(rec == null);
	}
	
	
	@Test // 8
	public void BetaResignsWhenOutOfPieces()
	{
		testPlayer.startGame(HantoGameID.BETA_HANTO, HantoPlayerColor.BLACK, true);
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(0,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(1,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(-1,0))));
		
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(2, 0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(-2,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(3,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(-3,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(4,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(-4,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(5,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(-5,0))));
		
		HantoMoveRecord rec = testPlayer.makeMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(-6,0)));

		assertTrue(rec != null);
		assertTrue(rec.getPiece() == null);
		assertTrue(rec.getTo() == null);
		assertTrue(rec.getFrom() == null);
	}
	@Test // 9
	public void willMakeAMoveWhenNoPieceLeftGAMMA()
	{
		testPlayer.startGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLACK, true);
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(0,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(1,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(-1,0))));
		
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, new HantoCordinateImpl(1,0), new HantoCordinateImpl(1,-1))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(-2,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, new HantoCordinateImpl(1,-1), new HantoCordinateImpl(1,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(-3,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, new HantoCordinateImpl(1,0), new HantoCordinateImpl(1,-1))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(-4,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, new HantoCordinateImpl(1,-1), new HantoCordinateImpl(1,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(-5,0))));
		
		
		HantoMoveRecord rec = testPlayer.makeMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, new HantoCordinateImpl(1,0), new HantoCordinateImpl(1,-1)));

		System.out.println(rec);
		assertTrue(rec != null);
		System.out.println(rec.getPiece());
		System.out.println(rec.getTo() + ", " + rec.getFrom() + ", "  + rec.getPiece() );
		assertTrue(rec.getPiece() != null);
		assertTrue(rec.getTo() != null);
		assertTrue(rec.getFrom() != null);
	}
	
	@Test // 9.2
	public void willMakeAMoveWhenNoPieceLeftDELTA()
	{
		testPlayer.startGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.BLACK, true);
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(0,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(1,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(-1,0))));
		
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, new HantoCordinateImpl(1,0), new HantoCordinateImpl(1,-1))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(-2,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, new HantoCordinateImpl(1,-1), new HantoCordinateImpl(1,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(-3,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, new HantoCordinateImpl(1,0), new HantoCordinateImpl(1,-1))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(-4,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, new HantoCordinateImpl(1,-1), new HantoCordinateImpl(1,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.CRAB, null, new HantoCordinateImpl(-5,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, new HantoCordinateImpl(1,0), new HantoCordinateImpl(1,-1))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.CRAB, null, new HantoCordinateImpl(-6,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, new HantoCordinateImpl(1,-1), new HantoCordinateImpl(1,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.CRAB, null, new HantoCordinateImpl(-7,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, new HantoCordinateImpl(1,0), new HantoCordinateImpl(1,-1))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.CRAB, null, new HantoCordinateImpl(-8,0))));
		
		
		
		HantoMoveRecord rec = testPlayer.makeMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(2,-1)));

		System.out.println(rec);
		assertTrue(rec != null);
		System.out.println(rec.getPiece());
		System.out.println(rec.getTo() + ", " + rec.getFrom() + ", "  + rec.getPiece() );
		assertTrue(rec.getPiece() != null);
		assertTrue(rec.getTo() != null);
		assertTrue(rec.getFrom() != null);
	}
	
	@Test
	public void getInvlaidMoveandGivesUp()
	{
		testPlayer.startGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.BLACK, true);
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(0,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(1,0))));
		assertTrue(testPlayer.playMove(new HantoMoveRecord(HantoPieceType.SPARROW, null, new HantoCordinateImpl(-1,0))));
		
		HantoMoveRecord rec = testPlayer.makeMove(new HantoMoveRecord(HantoPieceType.HORSE, null, new HantoCordinateImpl(2,-1)));
		assertTrue(rec == null);
	}
	
	@Test
	public void resignsOutofmovesandPlacement()
	{
		HantoMoveRecord rec = null;
		testPlayer.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLACK, true);
		for(int turns = 1; turns <= 15; turns++)
		{
			switch(turns)
			{
				case(1): assertTrue(testPlayer.playMove(new HantoMoveRecord(BUTTERFLY, null, makeCoordinate(0, 0)))); break;
				case(2): assertTrue(testPlayer.playMove(new HantoMoveRecord(HORSE, null, makeCoordinate(-1, 0)))); break;
				case(3): assertTrue(testPlayer.playMove(new HantoMoveRecord(HORSE, null, makeCoordinate(-2, 0)))); break;
				case(4): assertTrue(testPlayer.playMove(new HantoMoveRecord(HORSE, null, makeCoordinate(-3, 0)))); break;
				case(5): assertTrue(testPlayer.playMove(new HantoMoveRecord(HORSE, null, makeCoordinate(-4, 0)))); break;
				case(6): assertTrue(testPlayer.playMove(new HantoMoveRecord(CRAB, null, makeCoordinate(-5, 0)))); break;
				case(7): assertTrue(testPlayer.playMove(new HantoMoveRecord(CRAB, null, makeCoordinate(-6, 0)))); break;
				case(8): assertTrue(testPlayer.playMove(new HantoMoveRecord(CRAB, null, makeCoordinate(-7, 0)))); break;
				case(9): assertTrue(testPlayer.playMove(new HantoMoveRecord(CRAB, null, makeCoordinate(-8, 0)))); break;
				case(10): assertTrue(testPlayer.playMove(new HantoMoveRecord(CRAB, null, makeCoordinate(-9, 0)))); break;
				case(11): assertTrue(testPlayer.playMove(new HantoMoveRecord(CRAB, null, makeCoordinate(-10, 0)))); break;
				case(12): assertTrue(testPlayer.playMove(new HantoMoveRecord(SPARROW, null, makeCoordinate(-11, 0)))); break;
				case(13): assertTrue(testPlayer.playMove(new HantoMoveRecord(SPARROW, null, makeCoordinate(-12, 0)))); break;
				
			}
			switch(turns)
			{
				case(1): assertTrue(testPlayer.playMove(new HantoMoveRecord(BUTTERFLY, null, makeCoordinate(1, 0)))); break;
				case(2): assertTrue(testPlayer.playMove(new HantoMoveRecord(BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(1, -1)))); break;
				case(3): assertTrue(testPlayer.playMove(new HantoMoveRecord(BUTTERFLY, makeCoordinate(1, -1), makeCoordinate(1, 0)))); break;
				case(4): assertTrue(testPlayer.playMove(new HantoMoveRecord(BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(1, -1)))); break;
				case(5): assertTrue(testPlayer.playMove(new HantoMoveRecord(BUTTERFLY, makeCoordinate(1, -1), makeCoordinate(1, 0)))); break;
				case(6): assertTrue(testPlayer.playMove(new HantoMoveRecord(HORSE, null, makeCoordinate(2, 0)))); break;
				case(7): assertTrue(testPlayer.playMove(new HantoMoveRecord(HORSE, null, makeCoordinate(3, 0)))); break;
				case(8): assertTrue(testPlayer.playMove(new HantoMoveRecord(SPARROW, null, makeCoordinate(4, 0)))); break;
				case(9): assertTrue(testPlayer.playMove(new HantoMoveRecord(HORSE, null, makeCoordinate(5, 0)))); break;
				case(10): assertTrue(testPlayer.playMove(new HantoMoveRecord(CRAB, null, makeCoordinate(6, 0)))); break;
				case(11): assertTrue(testPlayer.playMove(new HantoMoveRecord(CRAB, null, makeCoordinate(7, 0)))); break;
				case(12): assertTrue(testPlayer.playMove(new HantoMoveRecord(HORSE, null, makeCoordinate(8, 0)))); break;
				case(13): rec = testPlayer.makeMove(new HantoMoveRecord(HORSE, makeCoordinate(8, 0), makeCoordinate(-13, 0))); break;
			}
		}
		
		assertTrue(rec != null);
		assertTrue(rec.getPiece() == null);
		assertTrue(rec.getTo() == null);
		assertTrue(rec.getFrom() == null);
	}
	
	
	// Helper methods
		private HantoCoordinate makeCoordinate(int x, int y)
		{
			return new HantoCordinateImpl(x, y);
		}
}
