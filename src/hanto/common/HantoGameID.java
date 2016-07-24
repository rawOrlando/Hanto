/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.common;

import java.util.EnumSet;
import java.util.Set;

/**
 * This enumeration provides symbolic constants for each variation of Hanto. Its 
 * main purpose is to allow easy parameterization of the game creation in the
 * HantoGameFactory class.
 * @author gpollice
 * @version Jan 30, 2013
 */
public enum HantoGameID
{
	ALPHA_HANTO("Alpha"), 
	BETA_HANTO("Beta"), 
	GAMMA_HANTO("Gamma"), 
	DELTA_HANTO("Delta"), 
	EPSILON_HANTO("Epsilon");
	
	//THETA_HANTO, ZETA_HANTO, IOTA_HANTO;
	
	// Not need I think there si a better way keep temporarly !!!
	//public static HantoGameID[] allOptions = {ALPHA_HANTO, BETA_HANTO, GAMMA_HANTO, DELTA_HANTO, EPSILON_HANTO};
	private final String printableName;
	
	/**
	 * The constructor for each enumerable item sets up the state so that
	 *  the printable name are set up.
	 * 
	 * @param printableName the value returned from toString
	 */
	private HantoGameID(String printableName)
	{
		this.printableName = printableName;
	}
	
	/*
	@Override
	public static HantoGameID valueOf(String typeName)
	{
		Set<HantoGameID> allIDs = EnumSet.allOf(HantoGameID.class);
		for(HantoGameID id: allIDs)
		{
			if(id.toString().equals(typeName))
			{
				return id;
			}
		}
		return null;
	}
	*/
	
	@Override
	public String toString()
	{
		return printableName;
	}
}
