package hanto.studentrno.tournament;

import hanto.studentrnorlando.tournament.HantoPlayer;
import hanto.tournament.HantoMoveRecord;

public class TestableHantoPlayer extends HantoPlayer 
{
	public boolean playMove(HantoMoveRecord opponentsMove)
	{
		try
		{
			game.makeMove(opponentsMove.getPiece(), opponentsMove.getFrom(), opponentsMove.getTo());
		}
		catch(Exception e)
		{
			return false;
		}
		turn++;
		return true;
	}
	
	public String printBoard()
	{
		return game.getPrintableBoard();
	}

}
