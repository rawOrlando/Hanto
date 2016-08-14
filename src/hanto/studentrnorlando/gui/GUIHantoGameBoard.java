package hanto.studentrnorlando.gui;

import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.studentrnorlando.common.HantoPieceImpl;
import hanto.studentrnorlando.common.board.HexHantoGameBoard;
import hanto.studentrnorlando.common.board.IHantoGameBoard;

public class GUIHantoGameBoard extends HexHantoGameBoard{

	public GUIHantoGameBoard(Map<HantoCoordinate, HantoPiece> pieces, HantoGameID gameId) {
		super(pieces, gameId);
		// TODO Auto-generated constructor stub
	}

	public GUIHantoGameBoard(IHantoGameBoard board)
	{
		super(board.getPieces(), board.getGameID());
		
	}
	//Does not make moves on this board !!!
	@Override
	public boolean checkValidPlayLocation(HantoCoordinate where) throws HantoException {
		
		return false;
	}

	@Override
	public boolean checkValidPlayLocationForPiece(HantoCoordinate where, HantoPiece piece) throws HantoException {
		
		return false;
	}

	@Override
	public void playPiece(HantoCoordinate where, HantoPieceImpl piece) {
		
	}
	
	// !!! change to memoizations
	public int getXBoardLength()
	{
		int maxLeftLength = Integer.MAX_VALUE;
		int maxRightLength = Integer.MIN_VALUE;
		for(HantoCoordinate coordinate: pieces.keySet())
		{
			int currentPoint = getXPoint(coordinate);
			if(maxLeftLength > currentPoint)
			{
				maxLeftLength = currentPoint;
			}
			if(maxRightLength < currentPoint)
			{
				maxRightLength = currentPoint;
			}
		}
		
		return (maxRightLength - maxLeftLength);
	}
	// !!! change to memoization
	public int getXFartherestLeftPoint()
	{
		int maxLeftLength = Integer.MAX_VALUE;
		for(HantoCoordinate coordinate: pieces.keySet())
		{
			int currentPoint = getXPoint(coordinate);
			if(maxLeftLength > currentPoint)
			{
				maxLeftLength = currentPoint;
			}
		}
		return maxLeftLength;
	}
	
	public int getXPoint(HantoCoordinate coordinate)
	{
		return coordinate.getX() + (int)(Math.floor((double) (coordinate.getY() /2)));
	}
	
	public int getYBoardLength()
	{
		int maxHighestLength = Integer.MIN_VALUE; 
		int maxLowestLength = Integer.MAX_VALUE;
		for(HantoCoordinate coordinate: pieces.keySet())
		{
			int currentPoint = coordinate.getY() * -1;
			if(maxHighestLength < currentPoint)
			{
				maxHighestLength= currentPoint;
			}
			if(maxLowestLength > currentPoint)
			{
				maxLowestLength = currentPoint;
			}
		}
		
		return (maxHighestLength - maxLowestLength);
	}
	// !!! change to memoization
	public int getYHighestPoint()
	{
		int maxHighestLength = Integer.MIN_VALUE; 
		for(HantoCoordinate coordinate: pieces.keySet())
		{
			int currentPoint = coordinate.getY() * -1;
			if(maxHighestLength < currentPoint)
			{
				maxHighestLength = currentPoint;
			}
		}
		return maxHighestLength;
	}
	
	//Don't know if this is how it should be done !!!
	public Map<HantoCoordinate, HantoPiece> getMap()
	{
		return pieces;
	}

}
