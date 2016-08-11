package hanto.studentrno.gui;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentrnorlando.gui.JGameTile;

public class JGameTileFalseImage extends JGameTile {

	public JGameTileFalseImage(HantoPiece piece, HantoCoordinate coordinate) {
		super(piece, coordinate);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected String getImageName()
	{
		return "Name That Wont Work    NO Chance";
	}

}
