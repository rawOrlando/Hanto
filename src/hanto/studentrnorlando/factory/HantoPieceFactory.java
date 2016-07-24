/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
//Don't need file yet so its commented out

package hanto.studentrnorlando.factory;

import hanto.common.*;
import hanto.studentrnorlando.common.HantoPieceMoveableImpl;
import hanto.studentrnorlando.common.MoveValidator;
import hanto.studentrnorlando.common.movevalidators.FlyValidator;
import hanto.studentrnorlando.common.movevalidators.JumpValidator;
import hanto.studentrnorlando.common.movevalidators.WalkValidator;

/**
 * This is a singleton class that provides a factory to create an instance of any version
 * of a Hanto piece.
 * 
 * @author rnorlando
 * @version Feb 3, 2016
 */

public class HantoPieceFactory
{
	private static final HantoPieceFactory instance = new HantoPieceFactory();
	
	/**
	 * Default private descriptor.
	 */

	private HantoPieceFactory()
	{
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * @return the instance
	 */

	public static HantoPieceFactory getInstance()
	{
		return instance;
	}
	
	
	/**
	 * Factory method that returns the appropriately configured Hanto game.
	 * @param gameId the version desired.
	 * @param type piece type
	 * @param color player color
	 * @return the game instance
	 */
	public  HantoPieceMoveableImpl makeHantoMovablePiece(HantoGameID gameId, HantoPieceType type, HantoPlayerColor color) {
		HantoPieceMoveableImpl piece = null;
		MoveValidator validator = null;
		switch (gameId) {
			case GAMMA_HANTO:
				piece = new HantoPieceMoveableImpl(color,
						type,
						new WalkValidator(1));
				break;
			case DELTA_HANTO:
				validator = null;
				if(type == HantoPieceType.BUTTERFLY)
				{
					validator = new WalkValidator(1);	
				}
				else if(type == HantoPieceType.SPARROW)
				{
					validator = new FlyValidator(0);	
				}
				else if(type == HantoPieceType.CRAB)
				{
					validator = new WalkValidator(3);	
				}
				piece = new HantoPieceMoveableImpl(color,
						type,
						validator);
				break;
			case EPSILON_HANTO:
				validator = null;
				if(type == HantoPieceType.BUTTERFLY)
				{
					validator = new WalkValidator(1);	
				}
				else if(type == HantoPieceType.SPARROW)
				{
					validator = new FlyValidator(4);	
				}
				else if(type == HantoPieceType.CRAB)
				{
					validator = new WalkValidator(3);	
				}
				else if(type == HantoPieceType.HORSE)
				{
					validator = new JumpValidator();	
				}
				piece = new HantoPieceMoveableImpl(color,
						type,
						validator);
				break;
		}
		return piece;
	}
}

