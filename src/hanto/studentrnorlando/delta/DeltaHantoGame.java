/**
 * Delta Hanto Game Code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.delta;


import hanto.common.*;
import hanto.studentrnorlando.common.HantoPieceImpl;
import hanto.studentrnorlando.common.game.HantoBaseSmartMovableGame;
import hanto.studentrnorlando.factory.HantoBoardFactory;

/**
 * Runs a GammaHantoGame back end
 * @version Mar 16, 2016
 */
public class DeltaHantoGame extends HantoBaseSmartMovableGame
{
	private boolean resigned;
	
	/**
	 * Contructs a GammaHantoGame
	 * @param movesFirst the color of the player Moveing first
	 */
	public DeltaHantoGame(HantoPlayerColor movesFirst)
	{
		super(HantoBoardFactory.getInstance().makeHantoBoard(HantoGameID.DELTA_HANTO),
				new DeltaHantoPlayer(movesFirst),
				new DeltaHantoPlayer(HantoPlayerColor.getOtherColor(movesFirst)),
				20);
	}
	

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException
	{
		if(done)
		{
			throw new HantoException("Game Over! No moves can be made.");
		}
		if((from == null && to == null) || pieceType == null)
		{
			resigned = true;
			done = true;
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
		//Must place at origin on first tuen
		if(turn == 1)
		{
			//check to see the orgin... nto needed
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

