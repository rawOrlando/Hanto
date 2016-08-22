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
	
	@Test //5
	public void doesNotEqualDifferentTypeValue()
	{
		assertTrue(!rec.equals(new HantoMoveRecord(HantoPieceType.SPARROW, new HantoCordinateImpl(0,0), new HantoCordinateImpl(0,0))));
	}
	
	@Test //5.1
	public void doesNotEqualDifferentFromValue()
	{
		assertTrue(!rec.equals(new HantoMoveRecord(HantoPieceType.BUTTERFLY, new HantoCordinateImpl(1,1), new HantoCordinateImpl(0,0))));
	}
	
	@Test //5.2
	public void doesNotEqualDifferentToValue()
	{
		assertTrue(!rec.equals(new HantoMoveRecord(HantoPieceType.BUTTERFLY, new HantoCordinateImpl(0,0), new HantoCordinateImpl(0,1))));
	}
	
	@Test //6
	public void nullEqualsNulls()
	{
		HantoMoveRecord testRecord = new HantoMoveRecord(null,null,null);
		assertTrue(testRecord.equals(new HantoMoveRecord(null,null,null)));
	}
	@Test //7
	public void nullEqualsNotEquals()
	{
		HantoMoveRecord testRecord = new HantoMoveRecord(null,null,null);
		assertTrue(!testRecord.equals(new HantoMoveRecord(HantoPieceType.BUTTERFLY, new HantoCordinateImpl(0,0), new HantoCordinateImpl(0,1))));
	}
	
	// Test toSting
	
	@Test //8
	public void toStringAllValues()
	{
		String s = rec.toString();
		assertEquals(s, "BUTTERFLY (0,0) (0,0)");
	}
	
	@Test //9
	public void toStringPlacementRecord()
	{
		HantoMoveRecord testRecord = new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCordinateImpl(0,1));
		String s = testRecord.toString();
		assertEquals(s, "BUTTERFLY null (0,1)");
	}
	
	//Test convertToString
	
	@Test // 10
	public void convertToStringFromGilberish()
	{
		assertEquals(null, HantoMoveRecord.convertFromString("What this does nto make sences"));
	}
	
	@Test // 11
	public void convertToStrignAndBAckGetsTheSame()
	{
		String s = rec.toString();
		assertEquals(HantoMoveRecord.convertFromString(s), rec);
	}
}
