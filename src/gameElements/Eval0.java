package gameElements;

public class Eval0 implements Eval{

	@Override
	public float getEval(Player player, Board board) {
		Player adversaire;
		if(player == board.getGame().getPlayer1()){
			adversaire = board.getGame().getPlayer0();
		}else{
			adversaire = board.getGame().getPlayer1();
		}

		return board.getScore(player) - board.getScore(adversaire);

		//EvalLambda evalLambda = new EvalLambda();

		//return E.getEval(player, board);
	}

}
