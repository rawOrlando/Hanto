/**
 * Jump validator, validates jumps
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.common.movevalidators;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.studentrnorlando.common.HexHantoDirection;
import hanto.studentrnorlando.common.MoveValidator;
import hanto.studentrnorlando.common.board.IHantoGameBoard;

/**
 * Validates the Jumps
 * @author Orlando
 *
 */
public class JumpValidator implements MoveValidator {
	
	@Override
	public boolean validMove(HantoCoordinate from, HantoCoordinate to, IHantoGameBoard board) throws HantoException 
	{
		//!!! TODO  it works...
		
		
		//!!! move some where else ?
		//EpsilonHantoCordinate fromImpl = new EpsilonHantoCordinate(from);
		if(board.distanceBetween(from, to) < 2)
		{
			throw new HantoException("Invlaid Play Location: Not jumping over anything");
		}
		
		if(!(HexHantoDirection.inAStraightLine(from, to)))
		{
			throw new HantoException("Invlaid Play Location: No jump paths to spot");
		}
		
		return true;
		
	}

}
