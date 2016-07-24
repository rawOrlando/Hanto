/**
 * Beta Hanto Game Board Code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.beta;



import java.util.HashMap;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.studentrnorlando.common.HantoCordinateImpl;
import hanto.studentrnorlando.common.HantoPieceImpl;
import hanto.studentrnorlando.common.board.HexHantoGameBoard;

/**
 * This represents a Game Board in a Beta Hanto game.
 * It keeps track of where there are piece and whether moves are ok with the board state
 * @author Orlando
 *
 */
public class BetaHantoGameBoard extends HexHantoGameBoard {


	
	public BetaHantoGameBoard()
	{
		super(new HashMap<HantoCoordinate, HantoPiece>(10), HantoGameID.BETA_HANTO);
	}	

	@Override
	public boolean checkValidPlayLocation(HantoCoordinate where) throws HantoException {
		if(getPieceAt(where) != null)
		{
			throw new HantoException("Invlaid Play Location: There is already a piece there.");
		}
		else if(!isAdjacentToSomeOne(where))
		{
			throw new HantoException("Invlaid Play Location: That is not adjacent to anyother piece.");
		}
		return true;
	}

	@Override
	public boolean checkValidPlayLocationForPiece(HantoCoordinate where, HantoPiece piece) throws HantoException {
		return checkValidPlayLocation(where);
	}

	

	@Override
	public void playPiece(HantoCoordinate where, HantoPieceImpl piece) {
		pieces.put(new HantoCordinateImpl(where), piece);
	}

	
	

}
