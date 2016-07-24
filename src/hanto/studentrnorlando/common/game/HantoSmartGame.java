/**
 * Smart Game interface Code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.common.game;

import java.util.List;

import hanto.common.HantoGame;
import hanto.common.HantoPlayerColor;
import hanto.tournament.HantoMoveRecord;

/**
 * A game that can figure out possible moves
 * @author Orlando
 *
 */
public interface HantoSmartGame extends HantoGame {

	/**
	 * gets all the players options for a move
	 * @param player the player color
	 * @return all the options for moves
	 */
	 List<HantoMoveRecord> getAllPlayersOptions(HantoPlayerColor player);
	
	 /**
	  * grades how good a move is for the board
	  * @param move move that is made
	  * @param color the player that would make the move
	  * @return an arbirary number on how good a move is
	  */
	 int gradeMove(HantoMoveRecord move, HantoPlayerColor color);
}
