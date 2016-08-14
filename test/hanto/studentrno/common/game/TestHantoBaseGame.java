package hanto.studentrno.common.game;

import java.util.List;

import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.common.IHantoPlayer;
import hanto.studentrnorlando.common.board.IHantoGameBoard;
import hanto.studentrnorlando.common.game.HantoBaseGame;
import hanto.tournament.HantoMoveRecord;

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

	@Override
	public List<HantoMoveRecord> getAllPlayersOptions(HantoPlayerColor player) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
