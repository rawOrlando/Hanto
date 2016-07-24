/**
 * Hanto mobale peice code Code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.common;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * A hanto Piece that can move
 * @author Orlando
 *
 */
public class HantoPieceMoveableImpl extends HantoPieceImpl {
	
	protected MoveValidator validator;
	
	/**
	 * Constucts
	 * @param color The color
	 * @param type the type of piece this is
	 * @param validator the move validator for the move
	 */
	public HantoPieceMoveableImpl(HantoPlayerColor color, HantoPieceType type, MoveValidator validator) {
		super(color, type);
		this.validator = validator;
	}
	
	/**
	 * get the validator for this move
	 * @return the validator for this move
	 */
	public MoveValidator getValidator()
	{
		return validator;
	}

	
	

}
