package hanto.studentrno.common.game;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPieceType.SPARROW;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentrnorlando.beta.BetaHantoPlayer;
import hanto.studentrnorlando.common.IHantoPlayer;
import hanto.studentrnorlando.common.board.IHantoGameBoard;
import hanto.studentrnorlando.common.game.HantoBaseGame;
import hanto.studentrnorlando.factory.HantoBoardFactory;

public class HantoBaseGameTest 
{
	
	static HantoBaseGame game;
	
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
	
	// Helper methods
	private HantoCoordinate makeCoordinate(int x, int y)
	{
		return new TestHantoCoordinate(x, y);
	}
	
	
	public HantoBaseGame makeGame(HantoPlayerColor color)
	{
		IHantoGameBoard board = HantoBoardFactory.getInstance().makeHantoBoard(HantoGameID.BETA_HANTO);
		IHantoPlayer currentPlayer = new BetaHantoPlayer(color);
		IHantoPlayer nextPlayer = new BetaHantoPlayer(HantoPlayerColor.getOtherColor(color));
		return new TestHantoBaseGame(board, currentPlayer, nextPlayer, 20);
	}
	
	@Test	//1
	public void intialazationOfHantoBaseGame()
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
	public void intialazationOfHantoBaseGameWithNull()
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
	public void getgameStatusOnTie() throws HantoException
	{
		HantoBaseGame game = makeGame(HantoPlayerColor.BLACK);
		
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(2, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		assertEquals(game.gameStatus(), MoveResult.DRAW);
	}
	
	@Test	//4
	public void getGameStatusPlayerMakeHimselfLose() throws HantoException
	{
		HantoBaseGame game = makeGame(HantoPlayerColor.BLACK);
		
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		assertEquals(game.gameStatus(), MoveResult.WHITE_WINS);
	}
	
	@Test	//5
	public void getGameStatusPlayerMakeHimselfLosRed() throws HantoException
	{
		HantoBaseGame game = makeGame(HantoPlayerColor.WHITE);
		
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		assertEquals(game.gameStatus(), MoveResult.BLACK_WINS);
	}
	
	@Test	//6
	public void getGameStatusBlueWins() throws HantoException
	{
		HantoBaseGame game = makeGame(HantoPlayerColor.WHITE);
		
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		assertEquals(game.gameStatus(), MoveResult.BLACK_WINS);
	}
	
	@Test	//7
	public void getGameStatusRedWins() throws HantoException
	{
		HantoBaseGame game = makeGame(HantoPlayerColor.BLACK);
		
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		assertEquals(game.gameStatus(), MoveResult.WHITE_WINS);
	}
	
	@Test (expected=HantoException.class)	//8
	public void DoesNotPlayButterFlyInThreTurns() throws HantoException
	{
		HantoBaseGame game = makeGame(HantoPlayerColor.BLACK);
		
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
	}
	
	@Test (expected=HantoException.class)	//9
	public void doesNotPlayeOnOrginOnFirstMove() throws HantoException
	{
		HantoBaseGame game = makeGame(HantoPlayerColor.BLACK);
		
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
	}
	
	@Test (expected=HantoException.class)	//9.1
	public void doesNotPlayeOnOrginXOnFirstMove() throws HantoException
	{
		HantoBaseGame game = makeGame(HantoPlayerColor.BLACK);
		
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
	}
	
	@Test (expected=HantoException.class)	//9.2
	public void doesNotPlayeOnOrginYOnFirstMove() throws HantoException
	{
		HantoBaseGame game = makeGame(HantoPlayerColor.BLACK);
		
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
	}
	
	@Test (expected=HantoException.class)	//10
	public void runsOutOfMovesAndContinuesToPlay() throws HantoException
	{
		IHantoGameBoard board = HantoBoardFactory.getInstance().makeHantoBoard(HantoGameID.BETA_HANTO);
		IHantoPlayer currentPlayer = new BetaHantoPlayer(HantoPlayerColor.BLACK);
		IHantoPlayer nextPlayer = new BetaHantoPlayer(HantoPlayerColor.WHITE);
		HantoBaseGame game = new TestHantoBaseGame(board, currentPlayer, nextPlayer, 4);
		
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		
		assertEquals(game.gameStatus(), MoveResult.DRAW);
	}
	
	@Test	//11
	public void drawWhenNoMoreMoves() throws HantoException
	{
		IHantoGameBoard board = HantoBoardFactory.getInstance().makeHantoBoard(HantoGameID.BETA_HANTO);
		IHantoPlayer currentPlayer = new BetaHantoPlayer(HantoPlayerColor.BLACK);
		IHantoPlayer nextPlayer = new BetaHantoPlayer(HantoPlayerColor.WHITE);
		HantoBaseGame game = new TestHantoBaseGame(board, currentPlayer, nextPlayer, 4);
		
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		
		assertEquals(game.gameStatus(), MoveResult.DRAW);
	}
	// !!! add more tests when posible.
	// I went back to write these tests

}
