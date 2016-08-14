package hanto.studentrnorlando.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;

public class HantoMove 
{
	HantoPiece piece;
	HantoCoordinate from, to;
	
	public HantoMove(HantoPiece piece, HantoCoordinate from, HantoCoordinate to)
	{
		this.piece = piece;
		this.to = to;
		this.from = from;
	}
	
	public HantoPiece getPiece()
	{
		return piece;
	}
	
	public HantoCoordinate getFrom()
	{
		return from;
	}
	
	public HantoCoordinate getTo()
	{
		return to;
	}
	
	

}
