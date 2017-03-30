package gameElements;

/**
 * Created by simon on 30/03/17.
 */
public class EvalLambda implements Eval{

    private double lambda;

    public EvalLambda(){
        super();
        lambda = 0.5;
    }

    @Override
    public float getEval(Player player, Board board) {
        return (float) (lambda*new Eval0().getEval(player, board)+(1-lambda)*new Eval1().getEval(player, board));
    }

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public void addLambda(double lambda){
        this.lambda += lambda;
    }
}
