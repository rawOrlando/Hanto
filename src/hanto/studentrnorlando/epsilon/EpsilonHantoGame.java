/**
 * Epsilon Hanto Game Code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.epsilon;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.studentrnorlando.common.HantoPieceImpl;
import hanto.studentrnorlando.common.game.HantoBaseSmartMovableGame;
import hanto.studentrnorlando.factory.HantoBoardFactory;

/**
 * Epsilon Hanto Game
 * @author Orlando
 *
 */
public class EpsilonHantoGame extends HantoBaseSmartMovableGame {

private boolean resigned;
	
	/**
	 * Contructs a GammaHantoGame
	 * @param movesFirst the color of the player Moveing first
	 */
	public EpsilonHantoGame(HantoPlayerColor movesFirst)
	{		
		super(HantoBoardFactory.getInstance().makeHantoBoard(HantoGameID.EPSILON_HANTO),
				new EpsilonHantoPlayer(movesFirst),
				new EpsilonHantoPlayer(HantoPlayerColor.getOtherColor(movesFirst)),
				Integer.MAX_VALUE);
		resigned = false;
	}	

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException
	{
		//end is handle else where 
		
		if(pieceType == null || (from == null && to == null))
		{
			resigned = true;
			MoveResult result = gameStatus();
			super.changeTurns();
			
			return result;
		}
		if(from == null)
		{
			return super.makeMove(pieceType, from, to);
		}
		
		canPlayPiece(pieceType, to, true);
		
		if(!currentPlayer.hasPlayedButterFly())
		{
			throw new HantoException("You have to places your butterfly before moving peices");
		}
		
		getBoard().movePiece(from, to,  new HantoPieceImpl(currentPlayer.getPlayerColor(), pieceType, to));
		
		currentPlayer.movePiece(pieceType, from, to);

		
		MoveResult result = gameStatus();
		super.changeTurns();
		
		return result;
	}
	

	@Override	
	public void canPlayPiece(HantoPieceType pieceType, HantoCoordinate where) throws HantoException
	{
		canPlayPiece(pieceType, where, false);
	}
	
	/**
	 * checks to see if the player has that pice left to play, and whether they can play there
	 * @param pieceType the piece attempting to be played
	 * @param where the position the piece is attempting to be played in
	 * @param moving whther it is a move or a place
	 * @throws HantoException thrown if is it not able to be played
	 */
	public void canPlayPiece(HantoPieceType pieceType, HantoCoordinate where, boolean moving) throws HantoException
	{	
		//Must place at origin on first turn
		if(turn == 1)
		{
			//does not need to check the origin done else !!!
			return;
		}
		
		if(turn < 3)
		{
			getBoard().checkValidPlayLocation(where);
		}
		else if(!moving)
		{
			super.canPlayPiece(pieceType, where);
			
		}
		else
		{
			getBoard().checkValidPlayLocation(where);
		}
		
	}
	
	@Override
	public MoveResult gameStatus() throws HantoPrematureResignationException
	{
		if(resigned)
		{
			if (turn <= 2)
			{
				throw new HantoPrematureResignationException();
			}
			else if(currentPlayer.hasPiece(null) && ((EpsilonHantoGameBoard)getBoard()).playerCanPlace(currentPlayer.getPlayerColor()))
			{
				throw new HantoPrematureResignationException();
			}
			else if(((EpsilonHantoGameBoard)getBoard()).playerCanMove(currentPlayer.getPlayerColor()))
			{
				throw new HantoPrematureResignationException();
			}
			if(currentPlayer.getPlayerColor() == HantoPlayerColor.BLACK)
			{
				return MoveResult.WHITE_WINS;
			}
			else if(currentPlayer.getPlayerColor() == HantoPlayerColor.WHITE)
			{
				return MoveResult.BLACK_WINS;
			}
		}
		return super.gameStatus();
	}
}
