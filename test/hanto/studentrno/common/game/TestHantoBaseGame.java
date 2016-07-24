package hanto.studentrno.common.game;

import hanto.studentrnorlando.common.IHantoPlayer;
import hanto.studentrnorlando.common.board.IHantoGameBoard;
import hanto.studentrnorlando.common.game.HantoBaseGame;

/**
 * created so I can Test all HantoBaseGame stuff
 * @author Orlando
 *
 */
public class TestHantoBaseGame extends HantoBaseGame {

	public TestHantoBaseGame(IHantoGameBoard board, IHantoPlayer currentPlayer, IHantoPlayer nextPlayer,
			int maxNumberofTurns) {
		super(board, currentPlayer, nextPlayer, maxNumberofTurns);
	}
	

}
