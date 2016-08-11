package hanto.studentrno.common.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.beta.BetaHantoPlayer;
import hanto.studentrnorlando.common.IHantoPlayer;
import hanto.studentrnorlando.common.board.IHantoGameBoard;
import hanto.studentrnorlando.common.game.HantoBaseGame;
import hanto.studentrnorlando.factory.HantoBoardFactory;

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
	
	// !!! add more tests when posible.
	// I went back to write these tests
}
