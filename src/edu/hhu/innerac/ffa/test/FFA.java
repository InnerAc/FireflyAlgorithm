package edu.hhu.innerac.ffa.test;

import edu.hhu.innerac.ffa.entiy.FireflyAlo;

public class FFA extends FireflyAlo {

	public FFA(int i_popNum, int i_dim) {
		super(i_popNum, i_dim);
	}

	
	public double g(double[] args) {
		double y = -(args[0] + 1/args[0]) + 10;
		return y;
	}
	
	@Override
	public double f(double[] args){
		double son = Math.sin(Math.sqrt((args[0]* args[0] + args[1]*args[1])));
		son = son * son - 0.5;
		double mot = 1 + 0.001 * (args[0]* args[0] + args[1]*args[1]);
		mot = mot * mot;
		return son / mot - 0.5;
	}
	
	public static void main(String args[]){
		FFA ffa = new FFA(100, 2);
		double[] st = new double[2];
		double[] en = new double[2];
		st[0] = st[1] = -100;
		en[0] = en[1] = 100;
		ffa.start(1, st, en);
	}

}
