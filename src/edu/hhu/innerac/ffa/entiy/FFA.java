package edu.hhu.innerac.ffa.entiy;

public class FFA extends FireflyAlo {

	public FFA(int i_popNum, int i_dim) {
		super(i_popNum, i_dim);
	}

	@Override
	public double f(double[] args) {
		double y = -(args[0] + 1/args[0]) + 10;
		return y;
	}
	
	public static void main(String args[]){
		FFA ffa = new FFA(10, 1);
		double[] st = new double[1];
		double[] en = new double[1];
		st[0] = 0;
		en[0] = 10;
		ffa.start(10, st, en);
	}

}
