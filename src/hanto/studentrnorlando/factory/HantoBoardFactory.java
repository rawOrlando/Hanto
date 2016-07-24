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
import hanto.studentrnorlando.beta.BetaHantoGameBoard;
import hanto.studentrnorlando.common.board.AdvancedHexHantoGameBoard;
import hanto.studentrnorlando.common.board.SmartHantoGameBoard;
import hanto.studentrnorlando.epsilon.EpsilonHantoGameBoard;

/**
 * This is a singleton class that provides a factory to create an instance of any version
 * of a Hanto piece.
 * 
 * @author rnorlando
 * @version Feb 3, 2016
 */

public class HantoBoardFactory
{
	private static final HantoBoardFactory instance = new HantoBoardFactory();
	
	/**
	 * Default private descriptor.
	 */

	private HantoBoardFactory()
	{
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * @return the instance
	 */

	public static HantoBoardFactory getInstance()
	{
		return instance;
	}
	
	
	/**
	 * Factory method that returns the appropriately configured Hanto game.
	 * @param gameId the version desired.
	 * @return the game instance
	 */

	public SmartHantoGameBoard makeHantoBoard(HantoGameID gameId) {
		SmartHantoGameBoard board = null;
		switch (gameId) {
			case BETA_HANTO:
				board = new BetaHantoGameBoard();
				break;
			case GAMMA_HANTO:
				board = new AdvancedHexHantoGameBoard(20, HantoGameID.GAMMA_HANTO);
				break;
			case DELTA_HANTO:
				board = new AdvancedHexHantoGameBoard(20, HantoGameID.DELTA_HANTO);
				break;
			case EPSILON_HANTO:
				board = new EpsilonHantoGameBoard();
				break;
		}
		return board;
	}
	
}
