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

package hanto.studentrnorlando.gamma;


import hanto.common.*;
import hanto.studentrnorlando.common.HantoPieceImpl;
import hanto.studentrnorlando.common.game.HantoBaseSmartMovableGame;
import hanto.studentrnorlando.factory.HantoBoardFactory;

/**
 * Runs a GammaHantoGame back end
 * @version Mar 16, 2016
 */
public class GammaHantoGame extends HantoBaseSmartMovableGame
{

	
	/**
	 * Contructs a GammaHantoGame
	 * @param movesFirst the color of the player Moveing first
	 */
	public GammaHantoGame(HantoPlayerColor movesFirst)
	{
		super(HantoBoardFactory.getInstance().makeHantoBoard(HantoGameID.GAMMA_HANTO),
				new GammaHantoPlayer(movesFirst),
				new GammaHantoPlayer(HantoPlayerColor.getOtherColor(movesFirst)), 20);
	}
	

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException
	{
		if(done)
		{
			throw new HantoException("Game Over! No moves can be made.");
		}
		if(from == null)
		{
			return super.makeMove(pieceType, from, to);
		}
		
		canPlayPiece(pieceType, to, true);
		
		if(!currentPlayer.hasPlayedButterFly())
		{
			throw new HantoException("You have to places your butterfly before moving peices");
		}
		
		getBoard().movePiece(from, to,  new HantoPieceImpl(currentPlayer.getPlayerColor(), pieceType, to));
		
		currentPlayer.movePiece(pieceType, from, to);
		
		MoveResult result = gameStatus();
		super.changeTurns();
		
		return result;
	}
	

	@Override	
	public void canPlayPiece(HantoPieceType pieceType, HantoCoordinate where) throws HantoException
	{
		canPlayPiece(pieceType, where, false);
	}
	
	/**
	 * checks to see if the player has that pice left to play, and whether they can play there
	 * @param pieceType the piece attempting to be played
	 * @param where the position the piece is attempting to be played in
	 * @param moving whether it it a movement or a placement
	 * @throws HantoException thrown if is it not able to be played
	 */
	public void canPlayPiece(HantoPieceType pieceType, HantoCoordinate where, boolean moving) throws HantoException
	{	
		//Must place at origin on first tuen
		if(turn == 1)
		{
			//Not need to check orgin
			return;
		}
		
		if(turn < 3)
		{
			getBoard().checkValidPlayLocation(where);
		}
		else if(!moving)
		{
			super.canPlayPiece(pieceType, where);
			
		}
		else
		{
			getBoard().checkValidPlayLocation(where);
		}
		
	}
	

}
