package hanto.studentrno.tournament;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoPieceType;
import hanto.studentrnorlando.common.HantoCordinateImpl;
import hanto.tournament.HantoMoveRecord;

public class HantoMoveRecordTest 
{
	HantoMoveRecord rec;
	
	@Before
	public void setUp()
	{
		rec = new HantoMoveRecord(HantoPieceType.BUTTERFLY, new HantoCordinateImpl(0,0), new HantoCordinateImpl(0,0));
	}
	
	@Test	// 1
	public void doesNotEqualNull()
	{
		//whats the problem 
		rec.getPiece();
		assertTrue(!rec.equals(null));
	}
	
	@Test	// 2
	public void doesNotEqualOtherThing()
	{
		assertTrue(!rec.equals(new HantoCordinateImpl(0,0)));
	}
	
	@Test //3.1
	public void doesNotEqualNullVersion()
	{
		assertTrue(!rec.equals(new HantoMoveRecord(null, null, null)));
	}
	
	@Test //3.2
	public void doesNotEqualNullVersionWithPiece()
	{
		assertTrue(!rec.equals(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, null)));
	}
	
	@Test //3.3
	public void doesNotEqualNullVersionWithFrom()
	{
		assertTrue(!rec.equals(new HantoMoveRecord(null, new HantoCordinateImpl(0,0), null)));
	}
	
	@Test //3.4
	public void doesNotEqualNullVersionWithTo()
	{
		assertTrue(!rec.equals(new HantoMoveRecord(null, null, new HantoCordinateImpl(0,0))));
	}
	
	@Test //3.5
	public void doesNotEqualNullVersionWithOutTo()
	{
		assertTrue(!rec.equals(new HantoMoveRecord(HantoPieceType.BUTTERFLY, new HantoCordinateImpl(0,0), null)));
	}
	
	@Test //4
	public void doesEqualSameInstance()
	{
		assertTrue(rec.equals(new HantoMoveRecord(HantoPieceType.BUTTERFLY, new HantoCordinateImpl(0,0), new HantoCordinateImpl(0,0))));
	}

}
