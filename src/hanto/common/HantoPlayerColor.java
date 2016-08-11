/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.common;

/**
 * This enumeration identifies the players in Hanto.
 * 
 * @version Jan 12, 2013
 */
public enum HantoPlayerColor
{
	BLACK, WHITE;
	
	/**
	 * gets the otherPlayers Color
	 * @param color the curent color
	 * @return the other players color
	 */
	public static HantoPlayerColor getOtherColor(HantoPlayerColor color)
	{
		if(color == HantoPlayerColor.WHITE)
		{
			return HantoPlayerColor.BLACK;
		}
		return HantoPlayerColor.WHITE;
	}
	
	public static String getPrintableName(HantoPlayerColor color)
	{
		if(color == HantoPlayerColor.WHITE)
		{
			return "White";
		}
		return "Black";
	}
}
