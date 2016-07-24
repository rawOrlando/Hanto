/**
 * Hanto Player Code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.common;

import java.util.List;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * Is a Player In a Hanto game, 
 * keeps track of all the information a player should know about him self and his pieces
 * @author Orlando
 *
 */
public interface IHantoPlayer {

	/**
	 * whether the player has played a butterfly
	 * @return player has no butterfly
	 */
	boolean hasPlayedButterFly();
	
	/**
	 * gets the location where this Player's butterFly is
	 * @return location where the player's butterFly is
	 */
	HantoCoordinate getButterFlyLocation();
	
	/**
	 * tells whther or no this player has any of the type of piece left to play
	 * @param type type of piece in question
	 * @return whther the player ahs it
	 */
	boolean hasPiece(HantoPieceType type);
	
	/**
	 * Attempts to place the piece specified, by keeping track of player information on that piece
	 * @param type piece to be placed type
	 * @param where location of the piece to be place
	 * @throws HantoException thrown if the player does not have any more of the piece type
	 */
	void playPiece(HantoPieceType type, HantoCoordinate where) throws HantoException;
	
	/**
	 * Get the players Color
	 * @return Player's Color
	 */
	HantoPlayerColor getPlayerColor();
	
	/**
	 * gets the max Number of Piece the Player Has
	 * @return max Number of Piece
	 */
	int getNumberofPiece();
	
	/**
	 * gets the piece this player could play
	 * @return the piece this PlayerCouldPlay
	 */
	List<HantoPieceType> getPieceOptions();
	
	/**
	 * moves a one its piece
	 * @param type the type of piece it is moving
	 * @param from form where the piece is moving
	 * @param to to where the piece is moving
	 * @throws HantoException if the piece can not be moved
	 */
	void movePiece(HantoPieceType type, HantoCoordinate from, HantoCoordinate to) throws HantoException;
	
}
