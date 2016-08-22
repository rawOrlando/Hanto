package hanto.studentrno.common.board;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.beta.BetaHantoGame;
import hanto.studentrnorlando.beta.BetaHantoGameBoard;
import hanto.studentrnorlando.common.HantoCordinateImpl;
import hanto.studentrnorlando.common.HantoPieceImpl;
import hanto.studentrnorlando.common.board.SmartHantoGameBoard;
import hanto.studentrnorlando.factory.HantoBoardFactory;
import hanto.tournament.HantoMoveRecord;

public class SmartHantoGameBoardTest 
{
	
	@Test
	public void betaReturnsOriginOptionsAtStart()
	{
		BetaHantoGameBoard board =  new BetaHantoGameBoard();
		List<HantoMoveRecord> list =  board.getAllPlayersOptions(HantoPlayerColor.BLACK);
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
		List<HantoMoveRecord> list =  board.getAllPlayersOptions(HantoPlayerColor.WHITE);
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
		List<HantoMoveRecord> list =  board.getAllPlayersOptions(HantoPlayerColor.BLACK);
		for(HantoMoveRecord record : list)
		{
			//assertTrue((new HantoCordinateImpl(0,0)).equals(record.getTo()));
		}
		assertEquals(8, list.size());
	}
	
	@Test
	public void gammaReturnsOriginOptionsAtStart()
	{
		SmartHantoGameBoard board =  HantoBoardFactory.getInstance().makeHantoBoard(HantoGameID.GAMMA_HANTO);
		List<HantoMoveRecord> list =  board.getAllPlayersOptions(HantoPlayerColor.BLACK);
		for(HantoMoveRecord record : list)
		{
			assertTrue((new HantoCordinateImpl(0,0)).equals(record.getTo()));
		}
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void gammaReturnSixOptionsOnSecoundTurn()
	{
		SmartHantoGameBoard board =  HantoBoardFactory.getInstance().makeHantoBoard(HantoGameID.GAMMA_HANTO);
		board.playPiece(new HantoCordinateImpl(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		List<HantoMoveRecord> list =  board.getAllPlayersOptions(HantoPlayerColor.WHITE);
		for(HantoMoveRecord record : list)
		{
			//assertTrue((new HantoCordinateImpl(0,0)).equals(record.getTo()));
		}
		assertEquals(list.size(),6);
	}
	
	@Test
	public void gammaPlacementOptionsOnSecoundTurn()
	{
		SmartHantoGameBoard board =  HantoBoardFactory.getInstance().makeHantoBoard(HantoGameID.GAMMA_HANTO);
		board.playPiece(new HantoCordinateImpl(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		board.playPiece(new HantoCordinateImpl(1,0), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
		List<HantoMoveRecord> list =  board.getAllPlayerPlacementMoves(HantoPlayerColor.BLACK);
		
		
		assertEquals(list.size(), 3);
	}
	
	@Test
	public void gammaReturnNineOptionsOnSecoundTurn()
	{
		SmartHantoGameBoard board =  HantoBoardFactory.getInstance().makeHantoBoard(HantoGameID.GAMMA_HANTO);
		board.playPiece(new HantoCordinateImpl(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		board.playPiece(new HantoCordinateImpl(1,0), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
		List<HantoMoveRecord> list =  board.getAllPlayersOptions(HantoPlayerColor.BLACK);
		List<HantoMoveRecord> placementList = new ArrayList<HantoMoveRecord>();
		List<HantoMoveRecord> playList = new ArrayList<HantoMoveRecord>();
		for(HantoMoveRecord record : list)
		{
			if(record.getPiece() != null)
			{
				playList.add(record);
			}
			else
			{
				placementList.add(record);
			}
		}
		assertEquals(placementList.size(), 3);
		assertEquals(playList.size(), 2);
		assertEquals(list.size(), 5);
	}
	
	@Test
	public void gammaReturnOptionsOnSecoundTurnWhite()
	{
		SmartHantoGameBoard board =  HantoBoardFactory.getInstance().makeHantoBoard(HantoGameID.GAMMA_HANTO);
		board.playPiece(new HantoCordinateImpl(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		board.playPiece(new HantoCordinateImpl(1,0), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
		List<HantoMoveRecord> list =  board.getAllPlayersOptions(HantoPlayerColor.WHITE);
		List<HantoMoveRecord> placementList = new ArrayList<HantoMoveRecord>();
		List<HantoMoveRecord> playList = new ArrayList<HantoMoveRecord>();
		for(HantoMoveRecord record : list)
		{
			if(record.getPiece() != null)
			{
				playList.add(record);
			}
			else
			{
				placementList.add(record);
			}
		}
		assertEquals(placementList.size(), 3);
		assertEquals(playList.size(), 2);
		assertEquals(list.size(), 5);
	}
	
	@Test
	public void gammaReturnOptionsOnThirdTurnClusteredAsWhite()
	{
		SmartHantoGameBoard board =  HantoBoardFactory.getInstance().makeHantoBoard(HantoGameID.GAMMA_HANTO);
		board.playPiece(new HantoCordinateImpl(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		board.playPiece(new HantoCordinateImpl(1,0), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
		board.playPiece(new HantoCordinateImpl(1,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		List<HantoMoveRecord> list =  board.getAllPlayersOptions(HantoPlayerColor.WHITE);
		List<HantoMoveRecord> placementList = new ArrayList<HantoMoveRecord>();
		List<HantoMoveRecord> playList = new ArrayList<HantoMoveRecord>();
		for(HantoMoveRecord record : list)
		{
			if(record.getPiece() != null)
			{
				playList.add(record);
			}
			else
			{
				placementList.add(record);
			}
		}
		assertEquals(placementList.size(), 2);
		assertEquals(playList.size(), 2);
		assertEquals(list.size(), 4);
	}
	
	@Test
	public void gammaReturnOptionsOnThirdTurnNonClusteredAsWhite()
	{
		SmartHantoGameBoard board =  HantoBoardFactory.getInstance().makeHantoBoard(HantoGameID.GAMMA_HANTO);
		board.playPiece(new HantoCordinateImpl(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		board.playPiece(new HantoCordinateImpl(1,0), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
		board.playPiece(new HantoCordinateImpl(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		List<HantoMoveRecord> list =  board.getAllPlayersOptions(HantoPlayerColor.WHITE);
		List<HantoMoveRecord> placementList = new ArrayList<HantoMoveRecord>();
		List<HantoMoveRecord> playList = new ArrayList<HantoMoveRecord>();
		for(HantoMoveRecord record : list)
		{
			if(record.getPiece() != null)
			{
				playList.add(record);
			}
			else
			{
				placementList.add(record);
			}
		}
		assertEquals(placementList.size(), 3);
		assertEquals(playList.size(), 2);
		assertEquals(list.size(), 5);
	}
	
	@Test
	public void gammaReturnOptionsOnThirdTurnClusteredAsBlack()
	{
		SmartHantoGameBoard board =  HantoBoardFactory.getInstance().makeHantoBoard(HantoGameID.GAMMA_HANTO);
		board.playPiece(new HantoCordinateImpl(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		board.playPiece(new HantoCordinateImpl(1,0), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
		board.playPiece(new HantoCordinateImpl(1,-1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		List<HantoMoveRecord> list =  board.getAllPlayersOptions(HantoPlayerColor.BLACK);
		List<HantoMoveRecord> placementList = new ArrayList<HantoMoveRecord>();
		List<HantoMoveRecord> playList = new ArrayList<HantoMoveRecord>();
		for(HantoMoveRecord record : list)
		{
			if(record.getPiece() != null)
			{
				playList.add(record);
			}
			else
			{
				placementList.add(record);
			}
		}
		assertEquals(placementList.size(), 6);
		assertEquals(playList.size(), 4);
		assertEquals(list.size(), 10);
	}
	
	@Test
	public void gammaReturnOptionsOnThirdTurnNonClusteredAsBLACK()
	{
		SmartHantoGameBoard board =  HantoBoardFactory.getInstance().makeHantoBoard(HantoGameID.GAMMA_HANTO);
		board.playPiece(new HantoCordinateImpl(0,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		board.playPiece(new HantoCordinateImpl(1,0), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
		board.playPiece(new HantoCordinateImpl(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		List<HantoMoveRecord> list =  board.getAllPlayersOptions(HantoPlayerColor.BLACK);
		List<HantoMoveRecord> placementList = new ArrayList<HantoMoveRecord>();
		List<HantoMoveRecord> playList = new ArrayList<HantoMoveRecord>();
		for(HantoMoveRecord record : list)
		{
			if(record.getPiece() != null)
			{
				playList.add(record);
			}
			else
			{
				placementList.add(record);
			}
		}
		assertEquals(placementList.size(), 5);
		assertEquals(playList.size(), 2);
		assertEquals(list.size(), 7);
	}

}
