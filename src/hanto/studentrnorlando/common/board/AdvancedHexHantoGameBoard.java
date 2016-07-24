/**
 * AdvanceHexHantoBoard Game Board Code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.common.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.common.HantoCordinateImpl;
import hanto.studentrnorlando.common.HantoPieceImpl;
import hanto.studentrnorlando.common.HantoPieceMoveableImpl;
import hanto.studentrnorlando.common.MoveValidator;
import hanto.studentrnorlando.factory.HantoPieceFactory;
import hanto.tournament.HantoMoveRecord;

/**
 * handless the game board for hanto
 * @author Orlando
 *
 */
public class AdvancedHexHantoGameBoard extends HexHantoGameBoard {
	
	/**
	 * default constuctor
	 * @param size the estimated size for the board
	 * @param gameId the games Id which is being played
	 */
	public AdvancedHexHantoGameBoard(int size, HantoGameID gameId)
	{
		super(new HashMap<HantoCoordinate, HantoPiece>(size), gameId);
	}
	
	/**
	 * moves pieces
	 * @param from location it was
	 * @param to location to move it to
	 * @param piece piece that is being moved
	 * @param checking whether this a checking or actually making the move 
	 * @throws HantoException if piece can not be moved
	 */
	protected void movePiece(HantoCoordinate from, HantoCoordinate to, HantoPieceImpl piece, boolean checking) throws HantoException
	{
		//Validate move
		HantoPieceMoveableImpl movablePiece = HantoPieceFactory.getInstance().makeHantoMovablePiece(gameId, piece.getType(), piece.getColor());
		
		MoveValidator validator = movablePiece.getValidator();
		
		validator.validMove(from, to, this);
		
		HantoPiece pickedUpPiece = pickUpPiece(from);
		
		//check out board
		if(!isBoardOkWithPieceMove(from))
		{
			if(checking)
			{
				playPiece(from, new HantoPieceImpl(pickedUpPiece.getColor(), pickedUpPiece.getType()));
			}
			throw new HantoException("Invlaid Move: That is breaks the board into multiple segments");
		}
		
		//Not There Piece
		if(pickedUpPiece.getColor() != piece.getColor())
		{
			if(checking)
			{
				playPiece(from, new HantoPieceImpl(pickedUpPiece.getColor(), pickedUpPiece.getType()));
			}
			throw new HantoException("Invlaid Move: That is not your piece");
		}
		if(pickedUpPiece.getType() != piece.getType())
		{
			if(checking)
			{
				playPiece(from, new HantoPieceImpl(pickedUpPiece.getColor(), pickedUpPiece.getType()));
			}
			throw new HantoException("Invlaid Move: That is not a " + piece.getType() + " piece.");
		}
		
		this.checkValidPlayLocation(to);
		
		if(checking)
		{
			playPiece(from, new HantoPieceImpl(pickedUpPiece.getColor(), pickedUpPiece.getType()));
		}
		else
		{
			playPiece(to, piece);
		}
		
	}
	
	/**
	 * moves pieces
	 * @param from location it was
	 * @param to location to move it to
	 * @param piece piece that is being moved
	 * @throws HantoException if piece can not be moved
	 */
	public void movePiece(HantoCoordinate from, HantoCoordinate to, HantoPieceImpl piece) throws HantoException
	{
		movePiece(from, to, piece, false);
	}
	
	/**
	 * picks up a pieces from the board
	 * @param where the location to pick up the piece from
	 * @return the piece
	 * @throws HantoException if the piece could not be picked up
	 */
	private HantoPiece pickUpPiece(HantoCoordinate where) throws HantoException
	{
		HantoPiece returnVal = this.getPieceAt(where);
		pieces.put(new HantoCordinateImpl(where), null);
		pieces.remove(where);

		if(returnVal == null)
		{
			throw new HantoException("Invlaid Move: There is no Piece There to Move.");
		}
		
		return returnVal;
	}
	
	/**
	 * plays a piece at the location
	 * @param where where the pieces is being played
	 * @param piece the piece being played
	 */
	public void playPiece(HantoCoordinate where, HantoPieceImpl piece)//should be Impl or not?
	{
		//!!! more checking for valid in play
		pieces.put(new HantoCordinateImpl(where), piece);
	}
	

	
	/**
	 * checks to see if it is a valid Play location
	 * @param where the location in question
	 * @throws HantoException 
	 */
	public boolean checkValidPlayLocation(HantoCoordinate where) throws HantoException
	{
		if(getPieceAt(where) != null)
		{
			throw new HantoException("Invlaid Play Location: There is already a piece there.");
		}
		else if(pieces.size() == 0)
		{
			//return true;
		}
		else if(!isAdjacentToSomeOne(where))
		{
			throw new HantoException("Invlaid Play Location: That is not adjacent to anyother piece.");
		}
		return true;
	}
	
	/**
	 * checks to see if it is a valid Play location
	 * @param where the location in question
	 * @param color rhw players Color
	 * @return true or error
	 * @throws HantoException if location is not valid
	 */
	public boolean checkValidPlaceLocation(HantoCoordinate where, HantoPlayerColor color) throws HantoException
	{
		checkValidPlayLocation(where);
		if(nextToColor(color, where))
		{
			throw new HantoException("Invlaid Play Location: That is adjacent to the other player's pieces");
		}
		return true;
	}
	
	/**
	 * Tells whether the places is next to a piece of color
	 * @param color the color of the piece to look for
	 * @param where the place to look for neighbors
	 * @return whether the places is next to a piece of color
	 */
	public boolean nextToColor(HantoPlayerColor color, HantoCoordinate where)
	{
		 for (HantoCoordinate cordinate : pieces.keySet()) 
		 {
			 	if(pieces.get(cordinate) != null && pieces.get(cordinate).getColor() == color)
			 	{
			        if(nextTo(where, cordinate))
			        {
			        	return true;
			        }
			 	}
		 }
		return false;
	}
	
	/**
	 * Tells is the board is ok with the peice being moved
	 * Meaning it checks to see if the board would be broken into multiple segments if the peice there would be moved
	 * @param where the place the piece is(was)
	 * @return whether the board is ok with the move
	 */
	protected boolean isBoardOkWithPieceMove(HantoCoordinate where)
	{
		List<HantoCoordinate> importantNeighbors = limtiToImporantNieghbors(getNieghbors(where));
		if(doesPathExistBetween(importantNeighbors))
		{
			return true;
		}
		return doesHaveIndirectPath(importantNeighbors);
	}
	
	/**
	 * tells if there is a indirect path between the palces
	 * @param places places to see if there is a indirect path between
	 * @return whether there is a path
	 */
	private boolean doesHaveIndirectPath(List<HantoCoordinate> places)
	{
		return hasPath(places);
	}
	
	/**
	 * if there is  is a path between the paces
	 * @param places to travel between
	 * @return wether there is a path
	 */
	private boolean hasPath(List<HantoCoordinate> places)
	{
		HantoCordinateImpl alphaPiece = new HantoCordinateImpl(places.remove(0)); 
		boolean pathes = true;
		for(HantoCoordinate where: places)
		{
			if(!hasPath(alphaPiece, where, new ArrayList<HantoCoordinate>()))
			{
				pathes = false;
			}
		}
		return pathes;
	}
	
	/**
	 * If ther is a path between alpha Piece and end Piece
	 * @param alphaPiece start location
	 * @param endPiece end location
	 * @param alreadyChecked locations already traveled
	 * @return whether there is a path between the two locations
	 */
	private boolean hasPath(HantoCoordinate alphaPiece, HantoCoordinate endPiece, List<HantoCoordinate> alreadyChecked )
	{
		//ArrayList<HantoCoordinate> returnList = new ArrayList<HantoCoordinate>();
		
		List<HantoCoordinate> neighbors = getNieghbors(alphaPiece);
		
		for(HantoCoordinate where: neighbors)
		{
			if(!alreadyChecked.contains(where))
			{
				alreadyChecked.add(where);
				if(endPiece.getX() == where.getX() && endPiece.getY() == where.getY())
				{
					return true;
				}
				if(hasPath(where, endPiece, alreadyChecked))
				{
					return true;
				}
			}
		}
		
		
		return false;
	}
	
	// !!! Think I will want these but for now don't need them
	/*
	private ArrayList<HantoCoordinate> makePath(ArrayList<HantoCoordinate> places)
	{
		HantoCordinateImpl alphaPiece = new HantoCordinateImpl(places.remove(0)); 
		return makePath(alphaPiece, places, new ArrayList<HantoCoordinate>());
	}
	
	//!!! does not work yet
	private ArrayList<HantoCoordinate> makePath(HantoCoordinate alphaPiece, ArrayList<HantoCoordinate> places, ArrayList<HantoCoordinate> alreadyChecked )
	{
		ArrayList<HantoCoordinate> returnList = new ArrayList<HantoCoordinate>();
		
		ArrayList<HantoCoordinate> neighbors = getNieghbors(alphaPiece);
		
		for(HantoCoordinate where: neighbors)
		{
			if(!alreadyChecked.contains(where))
			{
				alreadyChecked.add(where);
				if(places.contains(where))
				{
					places.remove(where);
					if(places.size() == 0)
					{
						return alreadyChecked;
					}
				}
				returnList.addAll(makePath(where, places, alreadyChecked));
			}
		}
		
		
		return returnList;
	}
	*/
	
	/**
	 * figures out if a path exist Between all the palces
	 * @param places places to connect ina path
	 * @return whether a path exists between the places
	 */
	private boolean doesPathExistBetween(List<HantoCoordinate> places)
	{
		if(places == null || places.size() == 0 ||places.size() == 1)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * limits the nieghbors to only disconnected nieghbors
	 * @param nieghbors the nieghbors to be limited
	 * @return only the disconnected nieghbors
	 */
	private List<HantoCoordinate> limtiToImporantNieghbors(List<HantoCoordinate> nieghbors)
	{
		if(nieghbors.size()==1)
		{
			return nieghbors;
		}
		
		List<HantoCoordinate> returnlist = new ArrayList<HantoCoordinate>();
		
		
		List<HantoCoordinate> alreadyChecked = new ArrayList<HantoCoordinate>();
		
		for(HantoCoordinate alpha : nieghbors)
		{
			boolean grabin = true;
			for(HantoCoordinate where : nieghbors)
			{
				if(nextTo(alpha, where) && !alreadyChecked.contains(where))
				{
					grabin = false;
				}
			}
			alreadyChecked.add(alpha);	
			if(grabin)
			{
				returnlist.add(alpha);
			}
		}
		
		return returnlist;
		
	}
	

	@Override
	public boolean checkValidPlayLocationForPiece(HantoCoordinate where, HantoPiece piece) throws HantoException {
		HantoPlayerColor otherPlayerColor = HantoPlayerColor.BLUE;
		switch(piece.getColor())
		{
			case RED : otherPlayerColor = HantoPlayerColor.BLUE; 
						break;
			case BLUE : otherPlayerColor = HantoPlayerColor.RED; 
						break;
				
		}
		return this.checkValidPlayLocation(where) && checkValidPlaceLocation(where, otherPlayerColor);
	}
	
	@Override
	public List<HantoMoveRecord> getAllPlayersOptions(HantoPlayerColor player)
	{
		//List<HantoMoveRecord> returnList = getAllPlayerMovementMoves(player);
		//returnList.addAll(getAllPlayerPlacementMoves(player));
		List<HantoMoveRecord> returnList = getAllPlayerPlacementMoves(player);
		return returnList;
	}
	
	@Override
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
				 			this.checkValidPlaceLocation(nieghbor, HantoPlayerColor.getOtherColor(player));
				 			returnList.add(new HantoMoveRecord(null, null, cordinate));
			 			}
			 			catch(HantoException e)
			 			{
			 				add= false;
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
	
	/**
	 * get all the player movement moves
	 * @param player the player to get the movement moves more
	 * @return list of all movement moves
	 */
	public List<HantoMoveRecord> getAllPlayerMovementMoves(HantoPlayerColor player)
	{
		List<HantoMoveRecord> returnList = new ArrayList<HantoMoveRecord>();
		for(HantoPieceImpl piece : getPlayersPieces(player))
 		{
			for(HantoCoordinate spot : getEdgeSpots())
	 		{
				boolean add = false;
				try
				{
					add = true;
					this.movePiece(piece.getCordinate(), spot, piece, true);
				}
				catch(HantoException e)
				{
					add = false;
				}
				if(add)
				{
					if(piece.getType() == null)
					{
						System.out.println("piece missing type...");
					}
					returnList.add(new HantoMoveRecord(piece.getType(), piece.getCordinate(), spot));
				}
	 		}
 		}
		return returnList;
	}
	
	/**
	 * gets the EdgeSpots
	 * @return All the open edge spots allong the baord
	 */
	protected List<HantoCoordinate> getEdgeSpots()
	{
		List<HantoCoordinate> returnList = new ArrayList<HantoCoordinate>();
		for (HantoCoordinate cordinate : pieces.keySet()) 
		{
			List<HantoCoordinate> list = this.getNieghboringSpots(cordinate);
	 		for(HantoCoordinate nieghbor : list)
	 		{
	 			if(getPieceAt(nieghbor) == null && !returnList.contains(nieghbor))
	 			{
	 				returnList.add(nieghbor);
	 			}
	 		}
		}
		return returnList;
	}
	
	/**
	 * gets all the Pieces a Player has on the board
	 * @param player player who's peice to look for
	 * @return all the Pieces a Player has on the board
	 */
	protected List<HantoPieceImpl> getPlayersPieces(HantoPlayerColor player)
	{
		List<HantoPieceImpl> returnList = new ArrayList<HantoPieceImpl>();
		for (HantoCoordinate cordinate : pieces.keySet()) 
		{
			if(getPieceAt(cordinate) != null && getPieceAt(cordinate).getColor() == player)
			{
				HantoPiece piece = getPieceAt(cordinate);
				returnList.add(new HantoPieceImpl(piece.getColor(), piece.getType(), cordinate));
			}
		}
		return returnList;
	}
	

}
