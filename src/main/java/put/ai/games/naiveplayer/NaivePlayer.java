package put.ai.games.naiveplayer;

import java.util.List;
import java.util.Random;
import put.ai.games.game.Board;
import put.ai.games.game.Move;
import put.ai.games.game.Player;

public class NaivePlayer extends Player {

	public static void main(String [] args){
		
	}
	
	private Random random=new Random(0xdeadbeef);

    @Override
    public String getName() {
        return "Hubert Kazmierczak 109705 Daniel Koza 109701";
    }
    
    @Override
    public Move nextMove(Board b) {    	
        List<Move> moves = b.getMovesFor(getColor());
        
        Move bestMove;
        
        bestMove = moves.get(random.nextInt(moves.size()));
        
        for(Move m : moves){
        	Board b2 = b.clone();
        	b2.doMove(m);
        	
        	Color c = getColor();
        	if(b2.getWinner(c)==c) {
        		bestMove = m;
        		break;
        	}
        }
        
		return bestMove;
   }


}