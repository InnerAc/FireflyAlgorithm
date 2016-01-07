package edu.hhu.innerac.ffa.test;

import edu.hhu.innerac.ffa.entiy.extension.FireflyAloSelection;

public class FFAS extends FireflyAloSelection {

	public FFAS(int i_popNum, int i_dim) {
		super(i_popNum, i_dim);
	}

	@Override
	public double f(double[] args) {
		double y = -(args[0] + 1/args[0]) + 10;
		return y;
	}
	
	public static void main(String args[]){
		FFAS ffa = new FFAS(3, 1);
		double[] st = new double[1];
		double[] en = new double[1];
		st[0] = 0;
		en[0] = 10;
		ffa.start(100, st, en);
	}

}
