/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentrno.common;
import hanto.studentrnorlando.common.*;

import static org.junit.Assert.*;

import org.junit.*;

import hanto.common.HantoPieceType;

/**
 * Test cases for Bag.
 * @version Jun 07, 2016
 */
public class HantoPieceBagTest {
	
	private static HantoPieceBag bag;
	
	@BeforeClass
	public static void initializeClass()
	{
		// don't nee to do any thing
		//player = new GeneralHantoPlayer(HantoPlayerColor.BLUE, 10);
	}
	
	@Before
	public void setup()
	{
		// By default, blue moves first.
		bag = new HantoPieceBag();
	}
	
	@Test // 1
	public void grabWithNothingInBag_ButterFly() 
	{
		assertTrue(!bag.grab(HantoPieceType.BUTTERFLY));	
	}
	
	@Test // 2
	public void grabWithNothingInBag_Sparrow() 
	{
		assertTrue(!bag.grab(HantoPieceType.SPARROW));	
	}
	
	@Test // 3
	public void grabWithNothingInBag_Crab() 
	{
		assertTrue(!bag.grab(HantoPieceType.CRAB));	
	}
	
	@Test // 4
	public void grabWithNothingInBag_Crane() 
	{
		assertTrue(!bag.grab(HantoPieceType.CRANE));	
	}
	
	@Test // 5
	public void grabWithNothingInBag_Horse() 
	{
		assertTrue(!bag.grab(HantoPieceType.HORSE));	
	}
	
	@Test // 6
	public void hasFailsWithNothingInBag() 
	{
		assertTrue(!bag.has(HantoPieceType.BUTTERFLY));	
	}
	
	@Test // 7
	public void hasWithPieceInBag() 
	{
		bag.addPiece(HantoPieceType.BUTTERFLY, 1);
		assertTrue(bag.has(HantoPieceType.BUTTERFLY));	
	}
	
	@Test // 8
	public void grabWithPieceInBag() 
	{
		bag.addPiece(HantoPieceType.BUTTERFLY, 1);
		assertTrue(bag.grab(HantoPieceType.BUTTERFLY));	
	}
	
	@Test // 9
	public void hasFailsWithPieceInBag() 
	{
		bag.addPiece(HantoPieceType.BUTTERFLY, 1);
		assertTrue(!bag.has(HantoPieceType.CRAB));	
	}
	
	@Test // 10
	public void grabFailsWithPieceInBag() 
	{
		bag.addPiece(HantoPieceType.BUTTERFLY, 1);
		assertTrue(!bag.grab(HantoPieceType.CRAB));	
	}
	
	@Test // 11
	public void hasAnyWithPieceInBag() 
	{
		bag.addPiece(HantoPieceType.BUTTERFLY, 1);
		assertTrue(bag.has(null));	
	}
	
	@Test // 12
	public void hasAnyWithNothingInBag() 
	{
		assertTrue(!bag.grab(null));	
	}
	
	@Test // 13
	public void getPieceLeftWithNothingInBag() 
	{
		assertTrue(bag.getPieceLeft().size() == 0);	
	}
	
	@Test // 14
	public void getPieceLeftWithNothingTwoButterFliesInBag() 
	{
		bag.addPiece(HantoPieceType.BUTTERFLY, 2);
		assertTrue(bag.getPieceLeft().size() == 1);	
	}
	@Test // 15
	public void getPieceLeftWithNothingTwoThingInBag() 
	{
		bag.addPiece(HantoPieceType.BUTTERFLY, 1);
		bag.addPiece(HantoPieceType.CRAB, 4);
		assertTrue(bag.getPieceLeft().size() == 2);	
	}
	
	@Test // 16
	public void grabRemovesFromBag() 
	{
		bag.addPiece(HantoPieceType.BUTTERFLY, 1);
		bag.addPiece(HantoPieceType.CRAB, 4);
		bag.grab(HantoPieceType.BUTTERFLY);
		assertTrue(!bag.has(HantoPieceType.BUTTERFLY));	
		assertTrue(bag.has(HantoPieceType.CRAB));
		assertTrue(bag.getPieceLeft().size() == 1);	
	}
	
	@Test // 16
	public void getPieceLeftWhenNoneAreLeft() 
	{
		bag.addPiece(HantoPieceType.BUTTERFLY, 1);
		bag.grab(HantoPieceType.BUTTERFLY);
		assertTrue(bag.getPieceLeft().size() == 0);	
	}
	
	@Test //17
	public void addPieceAlreadyAdded()
	{
		bag.addPiece(HantoPieceType.BUTTERFLY, 1);
		bag.addPiece(HantoPieceType.BUTTERFLY, 1);
		assertTrue(bag.getPieceLeft().size() == 1);	
		bag.grab(HantoPieceType.BUTTERFLY);
		assertTrue(bag.getPieceLeft().size() == 1);	
	}
	
	
	
	
}
