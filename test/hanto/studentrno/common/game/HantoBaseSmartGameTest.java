package hanto.studentrno.common.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.beta.BetaHantoGame;
import hanto.studentrnorlando.beta.BetaHantoPlayer;
import hanto.studentrnorlando.common.HantoCordinateImpl;
import hanto.studentrnorlando.common.IHantoPlayer;
import hanto.studentrnorlando.common.board.IHantoGameBoard;
import hanto.studentrnorlando.common.game.HantoBaseGame;
import hanto.studentrnorlando.factory.HantoBoardFactory;
import hanto.tournament.HantoMoveRecord;

public class HantoBaseSmartGameTest {

	@Test	//1
	public void intialazationOfHantoBaseSmartGame()
	{
		//IHantoPlayer currentPlayer = 
		IHantoGameBoard board = HantoBoardFactory.getInstance().makeHantoBoard(HantoGameID.BETA_HANTO);
		IHantoPlayer currentPlayer = new BetaHantoPlayer(HantoPlayerColor.BLACK);
		IHantoPlayer nextPlayer = new BetaHantoPlayer(HantoPlayerColor.WHITE);
		HantoBaseGame game = new TestHantoBaseGame(board, currentPlayer, nextPlayer, 20);
		assertEquals(board, game.getBoard());
		assertNotNull(game);
	}
	
	@Test	//2
	public void intialazationOfHantoBaseSmartGameWithNull()
	{
		//IHantoPlayer currentPlayer = 
		IHantoGameBoard board = null;
		IHantoPlayer currentPlayer = null;
		IHantoPlayer nextPlayer = null;
		HantoBaseGame game = new TestHantoBaseGame(board, currentPlayer, nextPlayer, 20);
		assertEquals(board, game.getBoard());
		assertNotNull(game);
	}
	
	@Test	//3
	public void getPlayerOptionsAtStartAllAtOrigin()
	{
		BetaHantoGame game = new BetaHantoGame(HantoPlayerColor.BLACK);
		List<HantoMoveRecord> list = game.getAllPlayersOptions();
		for(HantoMoveRecord record : list)
		{
			assertTrue((new HantoCordinateImpl(0,0)).equals(record.getTo()));
			assertNotNull(record.getPiece());
		}
		assertTrue(list.size() > 0);
		
	}
	
	@Test	//4
	public void onTurnTwoAllOptionsAreNotOnTheOrigin() throws HantoException
	{
		BetaHantoGame game = new BetaHantoGame(HantoPlayerColor.BLACK);
		game.makeMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(0,0)));
		List<HantoMoveRecord> list = game.getAllPlayersOptions();
		for(HantoMoveRecord record : list)
		{
			assertTrue(!(new HantoCordinateImpl(0,0)).equals(record.getTo()));
			assertNotNull(record.getPiece());
		}
		assertTrue(list.size() > 0);
	}
	
	@Test	//5
	public	void onTurnThreeGeneratesOptions() throws HantoException
	{
		BetaHantoGame game = new BetaHantoGame(HantoPlayerColor.BLACK);
		game.makeMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(0,0)));
		game.makeMove(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(1,0)));
		List<HantoMoveRecord> list = game.getAllPlayersOptions();
		for(HantoMoveRecord record : list)
		{
			assertTrue(!(new HantoCordinateImpl(0,0)).equals(record.getTo()));
			assertTrue(!(new HantoCordinateImpl(1,0)).equals(record.getTo()));
			assertNotNull(record.getPiece());
		}
		assertTrue(list.size() > 0);
	}
	// !!! add more tests when posible.
	// I went back to write these tests
}
