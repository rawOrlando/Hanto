/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package hanto.studentrnorlando.beta;



import hanto.common.*;
import hanto.studentrnorlando.common.game.HantoBaseSmartGame;
import hanto.studentrnorlando.factory.HantoBoardFactory;

/**
 * Runs a BetaHantoGame back end
 * @version Mar 16, 2016
 */
public class BetaHantoGame extends HantoBaseSmartGame
{
	
	/**
	 * Contructs a BetaHantoGame
	 * @param movesFirst the color of the player Moving first
	 */
	public BetaHantoGame(HantoPlayerColor movesFirst)
	{
		super(HantoBoardFactory.getInstance().makeHantoBoard(HantoGameID.BETA_HANTO),
				new BetaHantoPlayer(movesFirst),
				new BetaHantoPlayer(HantoPlayerColor.getOtherColor(movesFirst)), 12);
	}
	

}
