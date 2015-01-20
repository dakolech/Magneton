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
        Color c = getColor();
        
        Move bestMove;
        int rand = random.nextInt(moves.size());
        
        bestMove = moves.get(rand);
        
        boolean opponentWins = true;
        
        while (opponentWins) {
        	Board b1 = b.clone();
        	b1.doMove(bestMove);
        	if(b1.getWinner(c)!=getOpponent(c)) {
        		opponentWins = false;
        	}
        	moves.remove(rand);
        	rand = random.nextInt(moves.size());
        	bestMove = moves.get(rand);
        }
        
        for(Move m : moves){
        	Board b2 = b.clone();
        	b2.doMove(m);        	
        	
        	if(b2.getWinner(c)==c) {
        		bestMove = m;
        		break;
        	}
        }
        
		return bestMove;
   }


}