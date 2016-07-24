/**
 * Epsilon Hanto Game Board Code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.epsilon;

import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.common.board.AdvancedHexHantoGameBoard;

/**
 * Epsilon Hanto Game Board
 * @author Orlando
 *
 */
public class EpsilonHantoGameBoard extends AdvancedHexHantoGameBoard {
	
	/**
	 * default constuctor
	 */
	public EpsilonHantoGameBoard()
	{
		super(26, HantoGameID.EPSILON_HANTO);
	}
	
	/**
	 * player can place
	 * @param player player checking if can place
	 * @return if the player can place
	 */
	protected boolean playerCanPlace(HantoPlayerColor player)
	{	 	
		return getAllPlayerPlacementMoves(player).size() >0;
	}
	
	/**
	 * player can Move
	 * @param player player trying to male the move
	 * @return whthter the palyer can make the movement
	 */
	protected boolean playerCanMove(HantoPlayerColor player)
	{
		return getAllPlayerMovementMoves(player).size() > 0;
	}

}
