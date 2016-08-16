package hanto.studentrno.common.board;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.beta.BetaHantoGame;
import hanto.studentrnorlando.beta.BetaHantoGameBoard;
import hanto.studentrnorlando.common.HantoCordinateImpl;
import hanto.studentrnorlando.common.HantoPieceImpl;
import hanto.tournament.HantoMoveRecord;

public class SmartHantoGameBoardTest 
{
	
	
	@Test
	public void betaReturnsOriginOptionsAtStart()
	{
		BetaHantoGameBoard board =  new BetaHantoGameBoard();
		List<HantoMoveRecord> list =  board.getAllPlayerPlacementMoves(HantoPlayerColor.BLACK);
		for(HantoMoveRecord record : list)
		{
			assertTrue((new HantoCordinateImpl(0,0)).equals(record.getTo()));
		}
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void betaReturnSixOptionsOnSecoundTurn()
	{
		BetaHantoGameBoard board =  new BetaHantoGameBoard();
		board.playPiece(new HantoCordinateImpl(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		List<HantoMoveRecord> list =  board.getAllPlayerPlacementMoves(HantoPlayerColor.WHITE);
		for(HantoMoveRecord record : list)
		{
			//assertTrue((new HantoCordinateImpl(0,0)).equals(record.getTo()));
		}
		assertEquals(list.size(),6);
	}
	
	@Test	//5
	public	void onTurnThreeGeneratesOptions() throws HantoException
	{
		BetaHantoGameBoard board =  new BetaHantoGameBoard();
		board.playPiece(new HantoCordinateImpl(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		board.playPiece(new HantoCordinateImpl(1,0), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
		List<HantoMoveRecord> list = board.getAllPlayersOptions(HantoPlayerColor.BLACK);
		for(HantoMoveRecord record : list)
		{
			assertTrue(!(new HantoCordinateImpl(0,0)).equals(record.getTo()));
			assertTrue(!(new HantoCordinateImpl(1,0)).equals(record.getTo()));
		}
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void betaReturnNineOptionsOnSecoundTurn()
	{
		BetaHantoGameBoard board =  new BetaHantoGameBoard();
		board.playPiece(new HantoCordinateImpl(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		board.playPiece(new HantoCordinateImpl(1,0), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
		List<HantoMoveRecord> list =  board.getAllPlayerPlacementMoves(HantoPlayerColor.BLACK);
		for(HantoMoveRecord record : list)
		{
			//assertTrue((new HantoCordinateImpl(0,0)).equals(record.getTo()));
		}
		assertEquals(list.size(),10);
	}

}
