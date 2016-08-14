/**
 * Beta Hanto Game Board Interface Code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.common.board;


import java.util.List;
import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.studentrnorlando.common.HantoPieceImpl;

/**
 * The interface abstract of a Game board for the hanto game
 * Game board keeps track of the baord state 
 * Decides if It the board is ok with moves
 * @author Orlando
 *
 */
public interface IHantoGameBoard 
{
	
	/**
	 * gets the location
	 * @param where location to find the piece at
	 * @return the piece if any found at the location else null
	 */
	HantoPiece getPieceAt(HantoCoordinate where);
	
	/**
	 * whether the location is  adjacent to any other piece on the board
	 * @param where the location in question
	 * @return whether the location is  adjacent to any other piece on the board
	 */
	boolean isAdjacentToSomeOne(HantoCoordinate where);
	
	/**
	 *  tells whether that spot is surrronded
	 * @param where the spot in question
	 * @return whether it is surrounded
	 */
	boolean isSurronded(HantoCoordinate where);
	
	/**
	 * tells if the coordinate is next to the other coordinate on the board
	 * @param from first lcoation
	 * @param to secound location
	 * @return whether the locaitons are next to each other
	 */
	boolean nextTo(HantoCoordinate from, HantoCoordinate to);
	
	/**
	 * checks to see if it is a valid Play location
	 * @param where the location in question
	 * @throws HantoException 
	 * @return whether the play Lcoaiton is Valid
	 */
	boolean checkValidPlayLocation(HantoCoordinate where) throws HantoException;
	
	/**
	 * checks to see if it is a valid Play location
	 * @param where the location in question
	 * @param piece the piece being played 
	 * @return (true or error)
	 * @throws HantoException if play location is not valid
	 */
	boolean checkValidPlayLocationForPiece(HantoCoordinate where, HantoPiece piece) throws HantoException;
	
	/**
	 * gets all the nieghboring pieces at the coordinate
	 * @param where the place to look for nieghbors
	 * @return the nieghbors
	 */
	List<HantoCoordinate> getNieghbors(HantoCoordinate where);
	
	/**
	 * gets all the nieghboring spots at the coordinate
	 * @param where the place to look for nieghbors
	 * @return the nieghbors
	 */
	List<HantoCoordinate> getNieghboringSpots(HantoCoordinate where);
	
	
	/**
	 * plays a piece at the location
	 * @param where where the pieces is being played
	 * @param piece the piece being played
	 */
	void playPiece(HantoCoordinate where, HantoPieceImpl piece);
	
	/**
	 * get a printable board
	 * @return printable board
	 */
	String getPrintableBoard();
	
	/**
	 * tells the distance between to spots
	 * @param src locations
	 * @param dest secound locations
	 * @return distance in between 
	 */
	int distanceBetween(HantoCoordinate src, HantoCoordinate dest); // Delete and move to some where else !!!

	HantoGameID getGameID();
	
	Map<HantoCoordinate, HantoPiece> getPieces();
}
