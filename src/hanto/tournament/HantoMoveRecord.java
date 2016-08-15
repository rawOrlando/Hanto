/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.tournament;

import hanto.common.*;
import hanto.studentrnorlando.common.HantoCordinateImpl;

/**
 * This class is a data structure that records the move by a
 * player in a Hanto game. It is used for tournament play with AI players.
 * 
 * @author gpollice
 * @version Feb 20, 2013
 */
public class HantoMoveRecord
{
	private final HantoPieceType piece;
	private final HantoCoordinate from;
	private final HantoCoordinate to;
	
	/**
	 * Constructor. If the parameters are null, then this indicates a <em>resign</em>
	 * move.
	 * @param piece the Hanto piece type that moved
	 * @param from the source hex
	 * @param to the source hex
	 */
	public HantoMoveRecord(HantoPieceType piece, HantoCoordinate from, HantoCoordinate to)
	{
		this.piece = piece;
		this.from = from;
		this.to = to;
	}

	/**
	 * @return the piece
	 */
	public HantoPieceType getPiece()
	{
		return piece;
	}

	/**
	 * @return the from
	 */
	public HantoCoordinate getFrom()
	{
		return from;
	}

	/**
	 * @return the to
	 */
	public HantoCoordinate getTo()
	{
		return to;
	}
	
	@Override
	public String toString()
	{
		String reValue =  this.piece.name() + " ";
		if(from == null)
		{
			reValue = reValue + "null ";
		}
		else
		{
			reValue = reValue + from.toString() + " ";
		}
		reValue = reValue + to.toString();
		return reValue;
	}
	
	public static HantoMoveRecord convertFromString(String s)
	{
		String[] splited = s.split(" ");
		HantoMoveRecord reValue = null;
		HantoPieceType pieceType = null;
		HantoCoordinate from = null;
		HantoCoordinate to = null;
		try
		{
			pieceType = HantoPieceType.valueOf(splited[0]);
			from = HantoCordinateImpl.convertFromString(splited[1]);
			to = HantoCordinateImpl.convertFromString(splited[2]);
		}
		catch(Exception e)
		{
			e.printStackTrace(System.out);
			System.out.println("Caught");
		}
		
		if(to != null && pieceType != null)
		{
			reValue = new HantoMoveRecord(pieceType, from, to);
		}
		return reValue;
	}
}
