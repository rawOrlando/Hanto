/**
 * Move Validator Code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.common;



import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.studentrnorlando.common.board.IHantoGameBoard;

/**
 * Validates whether a move is valid or not
 * @author Orlando
 *
 */
public interface MoveValidator 
{
	/**
	 * Whether the proposed move is valid 
	 * @param from where the piece starts
	 * @param to where the piece is moving to
	 * @param board the board it is moving on
	 * @return Whether the proposed move is valid (true or error)
	 * @throws HantoException if the move is not valid
	 */
	boolean validMove(HantoCoordinate from, HantoCoordinate to, IHantoGameBoard board) throws HantoException;

}
