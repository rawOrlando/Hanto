/**
 * Generalized Hanto PLayer Code
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.common;

import java.util.List;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * genrealiezed hanto player
 * @author Orlando
 *
 */
public abstract class GeneralHantoPlayer implements IHantoPlayer {

	//Change to A Piece BAG
	protected HantoPieceBag bag;
	protected HantoCoordinate butterFlycoordinate = null;
	private final int NUMBEROFPIECE;
	private final HantoPlayerColor playerColor;
	
	/**
	 * default constuctor
	 * @param color of player
	 * @param numberofPieces max nubmer of piece he will have
	 */
	public GeneralHantoPlayer(HantoPlayerColor color, int numberofPieces)
	{
		bag = new HantoPieceBag();
		NUMBEROFPIECE = numberofPieces;
		playerColor = color;
	}
	
	@Override
	public int getNumberofPiece() {
		
		return NUMBEROFPIECE;
	}
	
	/**
	 * Get the players Color
	 * @return Player's Color
	 */
	public HantoPlayerColor getPlayerColor()
	{
		return playerColor;
	}
	
	
	@Override
	public boolean hasPlayedButterFly() {
		return !bag.has(HantoPieceType.BUTTERFLY);
	}

	@Override
	public HantoCoordinate getButterFlyLocation() {
		return butterFlycoordinate;
	}

	@Override
	public boolean hasPiece(HantoPieceType type) {
		return bag.has(type);
	}

	@Override
	public void playPiece(HantoPieceType type, HantoCoordinate where) throws HantoException {
		if(!bag.grab(type))
		{
			throw new HantoException("No more " + type.getPrintableName() + " pieces");
		}
		if(type == HantoPieceType.BUTTERFLY)
		{
			butterFlycoordinate = where;
		}
		
	}

	@Override
	public void movePiece(HantoPieceType type, HantoCoordinate from, HantoCoordinate to) throws HantoException {
		if(type == HantoPieceType.BUTTERFLY)
		{
			if(butterFlycoordinate == null)
			{
				throw new HantoException("You have not played a butterfly Yet");
			}
			else if(from.getX() != butterFlycoordinate.getX() || from.getY() != butterFlycoordinate.getY())
			{
				throw new HantoException("That is not where the butterFly is");
			}
			butterFlycoordinate = to;
		}
		
		
	}
	
	@Override
	public List<HantoPieceType> getPieceOptions()
	{
		return bag.getPieceLeft();
	}

}
