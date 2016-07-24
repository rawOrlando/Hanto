/**
 * Delta Hanto Player Code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.delta;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.common.GeneralHantoPlayer;

/**
 * This Class Models the Player in a Beta Hanto Game
 * The PLayer will keep track of his piece and where his butterfly is.
 * @author Orlando
 *	March 28 2016
 */
public class DeltaHantoPlayer extends GeneralHantoPlayer
{	
	/**
	 * Constructor, sets up variable, and the color fo the player
	 * @param color Color of the Player
	 */
	public DeltaHantoPlayer(HantoPlayerColor color)
	{
		super(color, 9);
		bag.addPiece(HantoPieceType.BUTTERFLY, 1);
		bag.addPiece(HantoPieceType.SPARROW, 4);
		bag.addPiece(HantoPieceType.CRAB, 4);
	}
	
}
