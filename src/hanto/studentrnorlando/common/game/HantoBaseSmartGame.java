/**
 * hanto Smart game base class Game Board Code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.common.game;

import java.util.ArrayList;
import java.util.List;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.common.HantoCordinateImpl;
import hanto.studentrnorlando.common.HantoMove;
import hanto.studentrnorlando.common.IHantoPlayer;
import hanto.studentrnorlando.common.board.IHantoGameBoard;
import hanto.studentrnorlando.common.board.SmartHantoGameBoard;
import hanto.tournament.HantoMoveRecord;

/**
 * contains shared code for all smart games, can be overided
 * @author Orlando
 *
 */
public abstract class HantoBaseSmartGame extends HantoBaseGame implements HantoSmartGame {

	SmartHantoGameBoard smartBoard;
	
	/**
	 * constuctor
	 * @param board the board for the game
	 * @param currentPlayer the current player
	 * @param nextPlayer the next player
	 * @param maxNumberofTurns max number of turns
	 */
	public HantoBaseSmartGame(SmartHantoGameBoard board, IHantoPlayer currentPlayer, IHantoPlayer nextPlayer, int maxNumberofTurns)
	{
		super(board, currentPlayer, nextPlayer, maxNumberofTurns);
	}
	
	@Override
	public List<HantoMoveRecord> getAllPlayersOptions(HantoPlayerColor player) {
		List<HantoMoveRecord> moveOptions = new ArrayList<HantoMoveRecord>();
		System.out.print("Base Action: ");
		
		List<HantoMoveRecord> gatheredOptions = getBoard().getAllPlayersOptions(player);
		System.out.println("Base Game report: " + gatheredOptions.size());
	
		for(HantoMoveRecord record: gatheredOptions)
		{
			if(record.getPiece() == null && record.getFrom() == null )
			{
				if(record.getTo() != null)
				{
					System.out.println("Smart Game has a movement move" );
					HantoCoordinate destination = record.getTo();
					//get all piece a player can place
					for(HantoPieceType option: currentPlayer.getPieceOptions())
					{
						boolean add = true;
						try 
						{
							this.canPlayPiece(option, destination);
						} 
						catch (HantoException e) 
						{
							add = false;
						}
						if(add) moveOptions.add(new HantoMoveRecord(option, null, destination));
					}
					
				}
				else
				{
					System.out.println("Smart Game has a movement move" );
					moveOptions.add(record);
				}
			}
		}
	
		return moveOptions;
	}
	
	//Does this work !!!, with overrid overloading ??
	@Override
	public SmartHantoGameBoard getBoard()
	{
		return smartBoard;
	}
	
	@Override
	protected void setBoard(IHantoGameBoard board)
	{
		//check to see if it is a smart board
		if(board != null)
		{
			smartBoard = (SmartHantoGameBoard) board;
		}
	}
	
	@Override
	public int gradeMove(HantoMoveRecord move, HantoPlayerColor color)
	{
		//splapstick fix up !!!
		if(move.getPiece() == HantoPieceType.BUTTERFLY)
		{
			if(move.getFrom() != null)
			{
				List<HantoCoordinate> oldnieghbors = getBoard().getNieghbors(move.getFrom());
				List<HantoCoordinate> newnieghbors = getBoard().getNieghbors(move.getFrom());
				return 12 + ((oldnieghbors.size() - newnieghbors.size())^2) *10;
			}
			else
			{
				return 90;
			}
		}
		
		HantoCoordinate ButterFlyLocation = getOtherPlayersButterFlyLocation(color);
		if(ButterFlyLocation == null)
		{
			return 90;
		}
		return (109 - getBoard().distanceBetween(move.getTo(), ButterFlyLocation)*10);
	}
	
	/**
	 * gets the othe Players ButterFly Location
	 * @param color other players color
	 * @return other players butterfly location
	 */
	private HantoCoordinate getOtherPlayersButterFlyLocation(HantoPlayerColor color)
	{
		if(currentPlayer.getPlayerColor() == color)
		{
			return nextPlayer.getButterFlyLocation();
		}
		else
		{
			return currentPlayer.getButterFlyLocation();
		}
	}
}
