package hanto.studentrnorlando.common.game;

import java.util.List;

import hanto.common.HantoGame;
import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.common.IHantoPlayer;
import hanto.studentrnorlando.common.board.IHantoGameBoard;
import hanto.tournament.HantoMoveRecord;

public interface HantoModelGame extends HantoGame 
{
	
	IHantoGameBoard getBoard();
	
	IHantoPlayer getPlayer();
	
	List<HantoMoveRecord> getAllPlayersOptions();
	
	List<HantoMoveRecord> getAllPlayersOptions(HantoPlayerColor player);

}
