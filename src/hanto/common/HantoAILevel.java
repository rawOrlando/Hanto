/**
 * This files was developed for Learing purposes: writen by Ryan Orlando
 */
package hanto.common;

import java.util.EnumSet;
import java.util.Set;

public enum HantoAILevel 
{
	EASY("Easy"), 
	MEDIUM("Medium"), 
	HARD("Hard");
	
	
	private final String printableName;
	
	/**
	 * The constructor for each enumerable item sets up the state so that
	 * the printable name are set up.
	 * 
	 * @param printableName the value returned from toString
	 */
	private HantoAILevel (String printableName)
	{
		this.printableName = printableName;
	}
	
	@Override
	public String toString()
	{
		return printableName;
	}

}
