/**
 * FLy Validator Code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.common.movevalidators;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.studentrnorlando.common.MoveValidator;
import hanto.studentrnorlando.common.board.IHantoGameBoard;

/**
 * Validate whther a Fly move is valid or not
 * @author Orlando
 *
 */
public class FlyValidator implements MoveValidator {
	
	private final int distance;
	
	/**
	 * default constuctor
	 * @param distance how far away they can Fly, 0 if infiinte
	 */
	public FlyValidator(int distance)
	{
		this.distance = distance;
	}
	
	@Override
	public boolean validMove(HantoCoordinate from, HantoCoordinate to, IHantoGameBoard board) throws HantoException 
	{
		//!!! TODO  it works...
		if(distance > 0)
		{
			if(board.distanceBetween(from, to) > distance)
			{
				throw new HantoException("Invlaid Play Location: Can't fly that far away");
			}
		}
		
		return true;
		
	}

}
