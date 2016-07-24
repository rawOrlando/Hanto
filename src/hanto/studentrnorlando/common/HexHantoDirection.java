/**
 * HexHantoDirect Game Board Code.
 * Deals with directions for a hexanglonal board
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.common;

import hanto.common.HantoCoordinate;
import hanto.studentrnorlando.common.board.HexHantoGameBoard;

public enum HexHantoDirection {
		NORTH(0, 1), 
		NORTHWEST(-1, 1), 
		NORTHEAST(1, 0), 
		SOUTH(0, -1), 
		SOUTHWEST(1, -1), 
		SOUTHEAST(-1, 0);
	
		private final int xOffSet;
		private final int yOffSet;
		
		
		private HexHantoDirection(int x, int y)
		{
			xOffSet = x;
			yOffSet = y;
		}
		
		/**
		 * tells whether the two cordinates are in a straight line for the given dierction
		 * @param from first place
		 * @param to secound place
		 * @return whether there is a straight line this direction
		 */
		public boolean inDirection(HantoCoordinate from, HantoCoordinate to)
		{
			int distance = HexHantoGameBoard.getDistanceBetween(from, to);
			if(distance*yOffSet == from.getY() - to.getY() &&
					distance*xOffSet == from.getX() - to.getX())
			{
				return true;
			}
			return false;
		}
		
		/**
		 tells whether the two cordinates are in a straight line for any direction
		 * @param from first place
		 * @param to secound place
		 * @return whether there is a straight line for any direction
		 */
		public static boolean inAStraightLine(HantoCoordinate from, HantoCoordinate to)
		{
			return NORTH.inDirection(from, to) || NORTHEAST.inDirection(from, to) || NORTHWEST.inDirection(from, to) ||
					SOUTH.inDirection(from, to) || SOUTHEAST.inDirection(from, to) || SOUTHWEST.inDirection(from, to);
		}
}
