/**
 * HanotPieceBagt Game Board Code.
 * Will be used for Players, might
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.common;

import java.util.ArrayList;
import java.util.List;

import hanto.common.HantoPieceType;

/**
 * represents a bag of piece left
 * @author Orlando
 *
 */
public class HantoPieceBag {

	List<BaggedHantoPiece> pieces = new ArrayList<BaggedHantoPiece>();
	
	/**
	 * adds apiece to the bag if it is not already int he bag
	 * @param piece piece type
	 * @param amount amount of the piece
	 */
	public void addPiece(HantoPieceType piece, int amount)
	{
		if(!has(piece))
		{
			pieces.add(new BaggedHantoPiece(piece, amount));
		}
		else
		{
			for(BaggedHantoPiece baggedPiece: pieces)
			{
				if(baggedPiece.getPieceType() == piece)
				{
					pieces.remove(baggedPiece);
					pieces.add(new BaggedHantoPiece(piece, amount + baggedPiece.amount));
					return;
				}
			}
		}
	}
	
	/**
	 * whether is has the piece of piece type
	 * @param piece the type of piece to look for
	 * @return whether it has the piece
	 */
	public boolean has(HantoPieceType piece)
	{
		for(BaggedHantoPiece baggedPiece: pieces)
		{
			if(baggedPiece.getPieceType() == piece && baggedPiece.has())
			{
				return true;
			}
			else if(piece == null && baggedPiece.has())
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * grabs a piece out of the bag
	 * @param piece the type of piece to grab
	 * @return if grab was sucessfull
	 */
	public boolean grab(HantoPieceType piece)
	{
		for(BaggedHantoPiece baggedPiece: pieces)
		{
			if(baggedPiece.getPieceType() == piece && baggedPiece.has())
			{
				baggedPiece.take();
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * get a list of all the Pieces left
	 * @return a list of the tpye of piece left in the bag
	 */
	public List<HantoPieceType> getPieceLeft()
	{
		List<HantoPieceType> returnList = new ArrayList<HantoPieceType>();
		for(BaggedHantoPiece baggedPiece: pieces)
		{
			if(baggedPiece.has())
			{
				returnList.add(baggedPiece.getPieceType());
			}
		}
		return returnList;
	}
	
	/**
	 * Represents a type of piece in the bag
	 * @author Orlando
	 *
	 */
	private class BaggedHantoPiece
	{
		private HantoPieceType piece;
		private int amount;
		
		/**
		 * Constuctor
		 * @param type type of piece
		 * @param amount amount of the piece
		 */
		private BaggedHantoPiece(HantoPieceType type, int amount)
		{
			piece = type;
			this.amount = amount;
		}
		
		/**
		 * get this piece type
		 * @return the piece type
		 */
		public HantoPieceType getPieceType()
		{
			return piece;
		}
		
		/**
		 * whether it has a pieces left
		 * @return whether it has a pieces left
		 */
		public boolean has()
		{
			return amount > 0;
		}
		
		/**
		 * take a piece out
		 */
		public void take()
		{
			amount--;
		}
	}
	
}
