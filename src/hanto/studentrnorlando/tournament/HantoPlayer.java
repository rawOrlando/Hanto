/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentrnorlando.tournament;

import java.util.List;

import hanto.common.*;
import hanto.studentrnorlando.common.HantoCordinateImpl;
import hanto.studentrnorlando.common.game.HantoSmartGame;
import hanto.studentrnorlando.factory.HantoGameFactory;
import hanto.tournament.*;

/**
 * Description
 * @version Oct 13, 2014
 */
public class HantoPlayer implements HantoGamePlayer
{

	protected HantoSmartGame game;
	HantoPlayerColor myColor;
	boolean moveFirst = true;
	protected int turn = 0;
	/*
	 * @see hanto.tournament.HantoGamePlayer#startGame(hanto.common.HantoGameID, hanto.common.HantoPlayerColor, boolean)
	 */
	@Override
	public void startGame(HantoGameID version, HantoPlayerColor myColor,
			boolean doIMoveFirst)
	{
		HantoPlayerColor firstColor;
		if(doIMoveFirst)
		{
			firstColor = myColor;
		}
		else
		{
			firstColor = HantoPlayerColor.getOtherColor(myColor);
		}
		game = HantoGameFactory.getInstance().makeSmartHantoGame(version, firstColor);
		this.myColor = myColor;
		moveFirst = doIMoveFirst;
	}

	/*
	 * @see hanto.tournament.HantoGamePlayer#makeMove(hanto.tournament.HantoMoveRecord)
	 */
	@Override
	public HantoMoveRecord makeMove(HantoMoveRecord opponentsMove)
	{
		//This code is not working, my board is not constentant for some reason.
		System.out.println(turn + "\n");
		if(turn == 0 && moveFirst)
		{
			try
			{
				game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(0,0));
			}
			catch(Exception e)
			{
				System.out.println("My code messed up some where");//Should  never Happen... !!!
				return null;
			}
			turn++;
			return new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(0,0));
		}
		else if(turn == 0)
		{
			try
			{
				game.makeMove(opponentsMove.getPiece(), opponentsMove.getFrom(), opponentsMove.getTo());
				game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(1,0));
			}
			catch(Exception e)
			{
				System.out.println("My code messed up some where");
				return null;
			}
			turn++;
			return new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(1,0));
		}
		//what to do if I start? !!!
		try
		{
			game.makeMove(opponentsMove.getPiece(), opponentsMove.getFrom(), opponentsMove.getTo());
		}
		catch(Exception e)
		{
			System.out.println("Thier move did not work My code messed up some where");
			return null;
		}
		List<HantoMoveRecord> options = game.getAllPlayersOptions(myColor);
		
		if(options.size() == 0)
		{
			System.out.println("Player says Hello \n");
			return new HantoMoveRecord(null, null, null);
		}
		turn++;
		
		return pickAndPlayBestMove(options);
		
	}
	
	/**
	 * picks the best move and plays it
	 * @param options
	 * @return the best move
	 */
	public HantoMoveRecord pickAndPlayBestMove(List<HantoMoveRecord> options)
	{

		HantoMoveRecord myMove =HantoMoveGrader.pickBestMove(options, game, myColor);
		try
		{
			game.makeMove(myMove.getPiece(), myMove.getFrom(), myMove.getTo());
		}
		catch(Exception e)
		{
			//Something wrong...
			System.out.println("this my code is bad");
		}
		return myMove;
		
	}

}
