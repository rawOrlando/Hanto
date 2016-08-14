/**
 * Hanto Game Board Code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.common.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.common.HantoCordinateImpl;
import hanto.tournament.HantoMoveRecord;

/**
 * keeps the defautl functioanlity for a Hanto Game BOard
 * keeping track of the board state
 * @author Orlando
 *
 */
public abstract class HexHantoGameBoard implements SmartHantoGameBoard {
	
	protected Map<HantoCoordinate, HantoPiece> pieces;
	protected HantoGameID gameId;
	
	/**
	 * constuctor
	 * @param pieces the maped spots to pieces
	 * @param gameId the games id
	 */
	public HexHantoGameBoard(Map<HantoCoordinate, HantoPiece> pieces, HantoGameID gameId)
	{
		this.pieces = pieces;
		this.gameId = gameId;
	}
	

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where)
	{
		return pieces.get(new HantoCordinateImpl(where));
	}
	
	@Override
	public boolean nextTo(HantoCoordinate from, HantoCoordinate to) {
		if((from.getX()-1 == to.getX() && from.getY() == to.getY()) ||
	        	(from.getX()+1 == to.getX() && from.getY() == to.getY()) ||
	        	(from.getX()-1 == to.getX() && from.getY()+1 == to.getY()) ||
	        	(from.getX() == to.getX()  && from.getY()+1 == to.getY()) ||
	        	(from.getX()+1 == to.getX() && from.getY()-1 == to.getY()) ||
	        	(from.getX() == to.getX()  && from.getY()-1 == to.getY()))	
	        {
	        	return true;
	        }
		return false;
	}
	
	@Override
	public boolean isAdjacentToSomeOne(HantoCoordinate where) {
		for (HantoCoordinate cordinate : pieces.keySet()) 
		 {
			if(nextTo(cordinate, where))
			{
		        return true;
		    }
		 }
		return false;
	}

	@Override
	public boolean isSurronded(HantoCoordinate where) {
		int numPieces = 0;
		for (HantoCoordinate cordinate : pieces.keySet()) 
		 {
			if(nextTo(cordinate, where))
			{
		        numPieces++;
		    }
		 }
		return numPieces > 5;
	}
	
	/**
	 * gets all the nieghboring pieces at the coordinate
	 * @param where the place to look for nieghbors
	 * @return the nieghbors
	 */
	public List<HantoCoordinate> getNieghbors(HantoCoordinate where)
	{
		List<HantoCoordinate> returnlist = new ArrayList<HantoCoordinate>();
		
		HantoCordinateImpl neighbor = new HantoCordinateImpl(new HantoCordinateImpl(where.getX()-1,  where.getY()));
		if(this.getPieceAt(neighbor) != null)
		{
			returnlist.add(neighbor);
		}
		
		neighbor = new HantoCordinateImpl(new HantoCordinateImpl(where.getX()-1,  where.getY()+1));
		if(this.getPieceAt(neighbor) != null)
		{
			returnlist.add(neighbor);
		}
		
		neighbor = new HantoCordinateImpl(new HantoCordinateImpl(where.getX(),  where.getY()+1));
		if(this.getPieceAt(neighbor) != null)
		{
			returnlist.add(neighbor);
		}
		
		neighbor = new HantoCordinateImpl(new HantoCordinateImpl(where.getX()+1,  where.getY()));
		if(this.getPieceAt(neighbor) != null)
		{
			returnlist.add(neighbor);
		}
		
		neighbor = new HantoCordinateImpl(new HantoCordinateImpl(where.getX()+1,  where.getY()-1));
		if(this.getPieceAt(neighbor) != null)
		{
			returnlist.add(neighbor);
		}
		
		neighbor = new HantoCordinateImpl(new HantoCordinateImpl(where.getX(),  where.getY()-1));
		if(this.getPieceAt(neighbor) != null)
		{
			returnlist.add(neighbor);
		}
		
		return returnlist;
	}
	
	/**
	 * gets the distance between two points
	 * @param src start point
	 * @param dest end point
	 * @return the distance between two points
	 */
	static public int getDistanceBetween(HantoCoordinate src, HantoCoordinate dest) {
		int x1 = src.getX();
		int x2 = dest.getX();
		int y1 = src.getY();
		int y2 = dest.getY();
		int z1 = ((-1 * x1) - y1);
		int z2 = ((-1 * x2) - y2);
		return Math.max(Math.abs(x2 - x1), Math.max(Math.abs(y2 - y1), Math.abs(z2 - z1)));
	}
	
	@Override
	public int distanceBetween(HantoCoordinate src, HantoCoordinate dest) {
		return getDistanceBetween(src, dest);
	}
	
	public List<HantoCoordinate> getNieghboringSpots(HantoCoordinate where)
	{
		List<HantoCoordinate> returnlist = new ArrayList<HantoCoordinate>();
		
		HantoCoordinate neighbor = (new HantoCordinateImpl(where.getX()-1,  where.getY()));
		returnlist.add(neighbor);
		
		
		neighbor = new HantoCordinateImpl(where.getX()-1,  where.getY()+1);
		returnlist.add(neighbor);
		
		
		neighbor = (new HantoCordinateImpl(where.getX(),  where.getY()+1));
		returnlist.add(neighbor);
		
		
		neighbor = (new HantoCordinateImpl(where.getX()+1,  where.getY()));
		returnlist.add(neighbor);
		
		
		neighbor = (new HantoCordinateImpl(where.getX()+1,  where.getY()-1));
		returnlist.add(neighbor);
		
		
		neighbor = (new HantoCordinateImpl(where.getX(),  where.getY()-1));
		returnlist.add(neighbor);
		
		return returnlist;
	}
	
	public List<HantoMoveRecord> getAllPlayersOptions(HantoPlayerColor player)
	{
		return getAllPlayerPlacementMoves(player);
	}
	
	public List<HantoMoveRecord> getAllPlayerPlacementMoves(HantoPlayerColor player)
	{
		List<HantoMoveRecord> returnList = new ArrayList<HantoMoveRecord>();
		for (HantoCoordinate cordinate : pieces.keySet()) 
		 {
			 	if(pieces.get(cordinate) != null && pieces.get(cordinate).getColor() == player)
			 	{
			 		List<HantoCoordinate> list = this.getNieghboringSpots(cordinate);
			 		for(HantoCoordinate nieghbor : list)
			 		{
			 			boolean add = false;
			 			try
			 			{
			 				add = true;
				 			this.checkValidPlayLocation(nieghbor);

			 			}
			 			catch(HantoException e)
			 			{
			 				add = false;
			 			}
			 			if(add)
			 			{
			 				returnList.add(new HantoMoveRecord(null, null, cordinate));
			 			}
			 		}
			 		
			 	}
		 }
		
			 	
		return returnList;
	}
	
	public String getPrintableBoard()
	{
		String s = "";
		for (HantoCoordinate cordinate : pieces.keySet()) 
		{
			HantoPiece piece = this.getPieceAt(cordinate);
			if(piece != null)
			{
				s = s + "(" + cordinate.getX() + ", " + cordinate.getY() + ") " + piece.getType().getPrintableName(); 
			}
		}
		return s;
	}
	
	public HantoGameID getGameID()
	{
		return this.gameId;
	}
	
	public Map<HantoCoordinate, HantoPiece> getPieces()
	{
		return this.pieces;
	}

}
