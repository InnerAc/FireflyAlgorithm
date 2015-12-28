package edu.hhu.innerac.ffa.test;

import edu.hhu.innerac.ffa.entiy.extension.FireflyAloDisturbance;

public class FFAD extends FireflyAloDisturbance {

	public FFAD(int i_popNum, int i_dim) {
		super(i_popNum, i_dim);
	}

	@Override
	public double f(double[] args) {
		double y = -(args[0] + 1/args[0]) + 10;
		return y;
	}

	public static void main(String args[]){
		FFAD ffa = new FFAD(3, 1);
		double[] st = new double[1];
		double[] en = new double[1];
		st[0] = 0;
		en[0] = 10;
		ffa.setRandomDisturbance();
		ffa.start(10, st, en);
	}
}
