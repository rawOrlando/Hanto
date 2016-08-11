/**
 * Hanto Game Code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.common.game;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.HantoGame;
import hanto.common.MoveResult;
import hanto.studentrnorlando.common.HantoPieceImpl;
import hanto.studentrnorlando.common.IHantoPlayer;
import hanto.studentrnorlando.common.board.IHantoGameBoard;

/**
 * reresents a Hanto Game
 * Containts all the defualt functioanlity of a Hanto Game
 * @author Orlando
 *
 */
public abstract class HantoBaseGame implements HantoGame{

	public static final int TURNSTILLBUTTERFLY = 4;
	public static int NUMBEROFPLAYERS = 2;
	private int MAXNUMBEROFTURNS;
	protected int turn;
	protected boolean done = false;
	protected IHantoPlayer currentPlayer;
	protected IHantoPlayer nextPlayer;	

	protected IHantoGameBoard board;
	
	/**
	 * meant to be called before anything else in a game
	 * sets up the variables in the abstract class
	 * @param board the current board
	 * @param currentPlayer the current Player
	 * @param nextPlayer the next Player
	 * @param maxNumberofTurns number of turns
	 */
	public HantoBaseGame (IHantoGameBoard board, IHantoPlayer currentPlayer, IHantoPlayer nextPlayer, int maxNumberofTurns)
	{
		setBoard(board);
		turn = 1;
		this.currentPlayer = currentPlayer;
		this.nextPlayer = nextPlayer;
		MAXNUMBEROFTURNS = maxNumberofTurns;
		
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException
	{
		//Game Over
		if(done)
		{
			throw new HantoException("Game Over! No moves can be made.");
		}
		//Must place at origin on first turn
		if(turn == 1)
		{
			if(!(to.getX() == 0 && to.getY() == 0))
			{
				throw new HantoException("Did not place on the origin on the first turn.");
			}
		}
		else
		{
			canPlayPiece(pieceType, to);
		}
		
		currentPlayer.playPiece(pieceType, to);
		
		getBoard().playPiece(to, new HantoPieceImpl(currentPlayer.getPlayerColor(), pieceType, to));
		
		final MoveResult result = gameStatus();
		changeTurns();
		
		return result;
	}

	/*
	 * @see hanto.common.HantoGame#getPieceAt(hanto.common.HantoCoordinate)
	 */
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where)
	{
		return getBoard().getPieceAt(where);
	}
	
	/**
	 * gets the game Status
	 * @throws HantoPrematureResignationException if the playe trys to resign to soon
	 * @return Whether the game is OK, a Draw, or one of the players has won.
	 */
	public MoveResult gameStatus() throws HantoPrematureResignationException
	{
		if(currentPlayer.hasPlayedButterFly() && nextPlayer.hasPlayedButterFly())
		{
			final boolean currentWin = getBoard().isSurronded(currentPlayer.getButterFlyLocation());
			final boolean nextWin = getBoard().isSurronded(nextPlayer.getButterFlyLocation());
			if(currentWin && nextWin)
			{
				done = true;
				return MoveResult.DRAW;
			}
			if((currentWin && currentPlayer.getPlayerColor() == HantoPlayerColor.WHITE) ||
					(nextWin && nextPlayer.getPlayerColor() == HantoPlayerColor.WHITE))
			{
				done = true;
				return MoveResult.BLACK_WINS;
			}
			else if(currentWin || // verbose && currentPlayer.getPlayerColor() == HantoPlayerColor.BLACK) ||
					nextWin) //&& nextPlayer.getPlayerColor() == HantoPlayerColor.BLACK))
			{
				done = true;
				return MoveResult.WHITE_WINS;
			}
		}
		if(turn >= MAXNUMBEROFTURNS)
		{
			done = true;
			return MoveResult.DRAW;
		}
		return MoveResult.OK;
	}
	
	
	/**
	 * checks to see if the player has that pice left to play, and whether they can play there
	 * @param pieceType the piece attempting to be played
	 * @param where the position the piece is attempting to be played in
	 * @throws HantoException thrown if is it not able to be played
	 */
	public void canPlayPiece(HantoPieceType pieceType, HantoCoordinate where) throws HantoException
	{	
		//check to see if butterFly is Played
		if(turn > NUMBEROFPLAYERS*(TURNSTILLBUTTERFLY - 1) 
				&& !currentPlayer.hasPlayedButterFly())
		{
			throw new HantoException("Need To Play ButterFly");
		}
		
		getBoard().checkValidPlayLocationForPiece(where, new HantoPieceImpl(currentPlayer.getPlayerColor(), pieceType));
		
		
	}
	
	/**
	 * change Turns
	 */
	protected void changeTurns()
	{
		turn++;
		
		IHantoPlayer temp = currentPlayer;
		currentPlayer = nextPlayer;
		nextPlayer = temp;
	}
	
	public IHantoGameBoard getBoard()
	{
		return board;
	}
	
	/**
	 * sets the game board
	 * @param board the new game baord
	 */
	protected void setBoard(IHantoGameBoard board)
	{
		if(board != null)
		{
			this.board = board;
		}
	}

	/*
	 * @see hanto.common.HantoGame#getPrintableBoard()
	 */
	@Override
	public String getPrintableBoard()
	{
		return getBoard().getPrintableBoard();
	}
}