/**
 * HantoBaseSmartMovableGamed Game Board Code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.common.game;

import java.util.List;

import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.common.IHantoPlayer;
import hanto.studentrnorlando.common.board.AdvancedHexHantoGameBoard;
import hanto.studentrnorlando.common.board.IHantoGameBoard;
import hanto.studentrnorlando.common.board.SmartHantoGameBoard;
import hanto.tournament.HantoMoveRecord;

/**
 * A Hanto base game that is smart and can move
 * @author Orlando
 *
 */
public abstract class HantoBaseSmartMovableGame extends HantoBaseSmartGame {
	
	AdvancedHexHantoGameBoard moveableBoard;
	
	public HantoBaseSmartMovableGame(SmartHantoGameBoard board, IHantoPlayer currentPlayer, IHantoPlayer nextPlayer,
			int maxNumberofTurns)
	{
		super(board, currentPlayer, nextPlayer, maxNumberofTurns);
	}

	public List<HantoMoveRecord> getAllPlayersOptions(HantoPlayerColor player) 
	{
		if(currentPlayer.hasPlayedButterFly())
		{
			List<HantoMoveRecord> moveOptions = getBoard().getAllPlayerMovementMoves(player);
			moveOptions.addAll(super.getAllPlayersOptions(player));
			return moveOptions;
		}
		return super.getAllPlayersOptions(player);
	}
	
	//Does this work !!!, with overrid overloading ??
		@Override
		public AdvancedHexHantoGameBoard getBoard()
		{
			return moveableBoard;
		}
		
		@Override
		protected void setBoard(IHantoGameBoard board)
		{
			//check to see if it is a smart board
			if(board != null)
			{
				moveableBoard = (AdvancedHexHantoGameBoard) board;
			}
		}

}
