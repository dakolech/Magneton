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
    private int INFTY = 1000000000;
    private int WIN = 1000;
    private Move bestMove;
    private int maxDepth;

    @Override
    public String getName() {
        return "Hubert Kazmierczak 109705 Daniel Koza 109701";
    }

    @Override
    public Move nextMove(Board b) {
    	int count = countFigures(b);
    	if( count < 20)maxDepth = 4;
    	else if( count < 22)maxDepth = 6;
    	else maxDepth = 10;
    	bestMove = null;
    	AlphaBeta(b, 0, -INFTY, INFTY);
    	if(bestMove==null){
    		System.out.println("NIE ZNALEZIONO RUCHU!!! ");
    		return b.getMovesFor(getColor()).get(0);
    	}
    	return bestMove;
    }
    
    private int AlphaBeta(Board b, int depth, int alpha, int beta){
    	Color c = getColor();
    	if(depth % 2 == 1) c = getOpponent(c);
    	
    	Color winner = b.getWinner(c);
    	if( (winner != null && winner != Color.EMPTY) || depth==maxDepth)return(heuristic(b,depth));
    	
    	List<Move> moves = b.getMovesFor(c);
        Board b2;
        for(Move m : moves){
        	b2 = b.clone();
        	b2.doMove(m);
        	int val = -AlphaBeta(b2,depth+1,-beta,-alpha);
        	if (val > alpha){
        		alpha = val;
        		if(depth == 0){
        			System.out.println("BEST: " + alpha);
        			this.bestMove = m;
        		}
        	}
        	if (alpha >= beta) return beta;
        }
        return alpha;
    }
    
    private int heuristic(Board b, int depth){
    	Color c = getColor();
    	if(depth % 2 == 1)c = getOpponent(c);
    	
    	if(b.getWinner(c)==c)return WIN;
    	if(b.getWinner(c)==getOpponent(c))return -WIN;
    	return 0;
    	
    }
    	
	private int countFigures(Board b){
		int counter = 0;
		for(int i=0; i<b.getSize(); i++){
			for(int j=0; j<b.getSize(); j++){
				if(b.getState(i, j) != Color.EMPTY)counter++;
			}
		}
		return counter;
	}
}