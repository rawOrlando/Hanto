package hanto.studentrnorlando.gui;

import hanto.common.HantoGameID;

public class GameContentPane extends ViewContainer{

	private static final long serialVersionUID = 1L;
	HantoGameID gameID;
	
	public GameContentPane(Screen screen, HantoGameID gameID) {
		//!!! figure out title
		super(screen, gameID.toString() + " Game");
		// TODO Auto-generated constructor stub !!!
	}

}
