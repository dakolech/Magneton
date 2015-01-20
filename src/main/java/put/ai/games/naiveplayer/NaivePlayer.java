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
        return "Bartosz Kostaniak 109713 Kamil Szczepanski 109790";
    }
    
    @Override
    public Move nextMove(Board b) {    	
        List<Move> moves = b.getMovesFor(getColor());
        
        Move playerMove;               
        
        playerMove = checkIfOpponentLoses(b, moves);
        
        playerMove = generateWinMove(b, moves, playerMove);
        
		return playerMove;
   }
    
   private Move checkIfOpponentLoses(Board b, List<Move> moves) {
	   Move playerMove;
	   Color c = getColor();
	   int randInteger = random.nextInt(moves.size());  
	   
       playerMove = moves.get(randInteger);   
       
       boolean opponentLoses = false; 
	   
	   while (!opponentLoses) {
	       	Board boardClone = b.clone();
	       	boardClone.doMove(playerMove);
	       	
	       	if(boardClone.getWinner(c)!=getOpponent(c)) {
	       		opponentLoses = true;
	       	}	       	
	       	
	       	moves.remove(randInteger);
	       	randInteger = random.nextInt(moves.size());
	       	playerMove = moves.get(randInteger);
       }
	   
	   return playerMove;
   }
    
   private Move generateWinMove(Board b, List<Move> moves, Move generatedMove) {
		Move winMove = generatedMove;
		Color c = getColor();
		
		for(Move m : moves){
		   	Board boardClone = b.clone();
		   	boardClone.doMove(m);        	
		
		   	if(boardClone.getWinner(c)==c) {
		   		winMove = m;
		   		break;
		   	}		
		}
		
	    return winMove;
   }


}