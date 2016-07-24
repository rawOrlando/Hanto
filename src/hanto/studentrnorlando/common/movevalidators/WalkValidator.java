/**
 * Walk Validator Code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.common.movevalidators;


import java.util.List;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.studentrnorlando.common.MoveValidator;
import hanto.studentrnorlando.common.board.IHantoGameBoard;

/**
 * Validate whether a walk move is valid or not
 * @author Orlando
 *
 */
public class WalkValidator implements MoveValidator {

	private final int distance;
	
	/**
	 * default constuctor
	 * @param distance how far away they can walk
	 */
	public WalkValidator(int distance)
	{
		this.distance = distance;
	}
	
	@Override
	public boolean validMove(HantoCoordinate from, HantoCoordinate to, IHantoGameBoard board) throws HantoException 
	{
		if(board.distanceBetween(from, to) > distance)
		{
			throw new HantoException("Invlaid Play Location: Can't walk that far away");
		}
		
		if(!isThereWalkPath(from, to, board))
		{
			throw new HantoException("Invlaid Play Location: No paths to spot");
		}
		
		
		List<HantoCoordinate> otherNieghbors = board.getNieghbors(to);
		
		int nieghbors = 0;
		for(HantoCoordinate nieghbor: board.getNieghbors(from))
		{
			if(otherNieghbors.contains(nieghbor))
			{
				nieghbors++;
			}
			if(nieghbors > 1)
			{
				throw new HantoException("Invlaid Play Location: Can't walk Through piece like that");
			}
		}
		
		
		//!!! should check for ontop of peice
		
		
		return true;
		
	}
	
	private boolean isThereWalkPath(HantoCoordinate from, HantoCoordinate to, IHantoGameBoard board)
	{
		return isThereWalkPath(from, to, board, 0);
		
	}
	
	private boolean isThereWalkPath(HantoCoordinate from, HantoCoordinate to, IHantoGameBoard board, int distance)
	{
		if(from.getX() == to.getX() && from.getY() == to.getY())
		{
			return true;
		}
		if(distance >= this.distance)
		{
			return false;
		}
		
		List<HantoCoordinate> neighbors = board.getNieghboringSpots(from);
		
		boolean otherStep = false;
		for(int j = 0; j < neighbors.size(); j++)
		{
			HantoCoordinate spot =  neighbors.get(j);
			
			if(board.isAdjacentToSomeOne(spot))
			{
				if(board.getPieceAt(spot) == null)
				{
					otherStep =  otherStep || isThereWalkPath(spot, to, board, distance +1);
				
				}
			}
		}
		
		return otherStep;
		
	}

}
