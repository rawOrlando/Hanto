package hanto.studentrno.gui;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.common.HantoCordinateImpl;
import hanto.studentrnorlando.common.HantoPieceImpl;
import hanto.studentrnorlando.gui.GUIHantoGameBoard;

public class GUIHantoGameBoardTest 
{
	
	@Test	//1
	public void playPieceDoesNothing()
	{
		GUIHantoGameBoard board = new GUIHantoGameBoard(null, HantoGameID.BETA_HANTO);
		Map<HantoCoordinate, HantoPiece> map = board.getMap();
		board.playPiece(new HantoCordinateImpl(1,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		assertEquals(map, board.getMap());
	}
	
	@Test	//2
	public void checkValidPlayLocationAlwaysFalse() throws HantoException
	{
		GUIHantoGameBoard board = new GUIHantoGameBoard(null, HantoGameID.BETA_HANTO);
		assertTrue(!board.checkValidPlayLocation(new HantoCordinateImpl(1,1)));
		assertTrue(!board.checkValidPlayLocation(new HantoCordinateImpl(-2,0)));
		assertTrue(!board.checkValidPlayLocation(new HantoCordinateImpl(0,71)));
	}
	
	@Test	//3
	public void checkValidPlayLocationForPieceAlwaysFlase() throws HantoException
	{
		GUIHantoGameBoard board = new GUIHantoGameBoard(null, HantoGameID.BETA_HANTO);
		assertTrue(!board.checkValidPlayLocationForPiece(new HantoCordinateImpl(1,2), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY)));
		assertTrue(!board.checkValidPlayLocationForPiece(new HantoCordinateImpl(-2,-5), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.SPARROW)));
		assertTrue(!board.checkValidPlayLocationForPiece(new HantoCordinateImpl(0,7), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.CRAB)));
	}

}
