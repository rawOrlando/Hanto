/**
 * SmartHantoGame Board code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.common.board;

import java.util.List;

import hanto.common.HantoPlayerColor;
import hanto.tournament.HantoMoveRecord;

/**
 * it is a game board that is smart for the AI
 * @author Orlando
 *
 */
public interface SmartHantoGameBoard extends IHantoGameBoard {
	
	/**
	 * gets a list of all the players Move and placement options
	 * @param player player to look for options for
	 * @return the list of all move options
	 */
	 List<HantoMoveRecord> getAllPlayersOptions(HantoPlayerColor player);
	
	/**
	 * get all the placement options for the player on the board
	 * @param player player to look for options
	 * @return the list of all placement options
	 */
	 List<HantoMoveRecord> getAllPlayerPlacementMoves(HantoPlayerColor player);

}
