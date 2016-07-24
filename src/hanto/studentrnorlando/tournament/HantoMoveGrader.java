/**
 * Hanto Move grader code Code.
 * Author: Ryan Orlando
 */
package hanto.studentrnorlando.tournament;

import java.util.List;

import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.common.game.HantoSmartGame;
import hanto.tournament.HantoMoveRecord;

/**
 * grades moves
 * @author Orlando
 *
 */
public class HantoMoveGrader {

	/**
	 * picks the best move
	 * @param options options of moves to pick from
	 * @param game the game the move is beign played on
	 * @param color color of the player playing
	 * @return the best moves out of the options
	 */
	public static HantoMoveRecord pickBestMove(List<HantoMoveRecord> options, HantoSmartGame game,  HantoPlayerColor color)
	{
		int maxGrade = Integer.MIN_VALUE;
		HantoMoveRecord bestMove = null;
		for(HantoMoveRecord move: options)
		{
			int currentGrade = game.gradeMove(move, color);
			if(currentGrade > maxGrade)
			{
				maxGrade = currentGrade;
				bestMove = move;
			}

		}
		return bestMove;
	}
}
