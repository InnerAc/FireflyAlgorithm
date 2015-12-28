package edu.hhu.innerac.ffa.entiy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.hhu.innerac.ffa.template.Point;
import edu.hhu.innerac.ffa.template.PointUtil;

/**
 * @author innerac
 * the Firefly Algorithm of innerac <br>
 * now it is only a normal algorithm for study.
 */
public abstract class FireflyAlo {

	protected List<Firefly> fireflies;	//Firefly population
	protected int popNum;					//the number of firefly population
	protected int dim;					//the space dimension
	protected int maxT;					//Maximum iteration number

	protected double beta, alpha, gamma;	//Necessary value

	
	/**
	 * constructed function <br>
	 * default alpha = 0.02, beta is 1.0, game = 1.0
	 * @param i_popNum	the number of firefly population
	 * @param i_dim		the space dimension
	 */
	public FireflyAlo(int i_popNum, int i_dim) {
		this.popNum = i_popNum;
		this.dim = i_dim;
		this.beta = 1.0;
		this.gamma = 1.0;
		this.alpha = 0.02;
	}

	/**
	 * constructed function <br>
	 * @param i_popNum	the number of firefly population
	 * @param i_dim		the space dimension
	 * @param i_alpha	the alpha value
	 * @param i_beta	the beta value
	 * @param i_gamma	the gamma value
	 */
	public FireflyAlo(int i_popNum, int i_dim, double i_alpha, double i_beta, double i_gamma) {
		this.popNum = i_popNum;
		this.dim = i_dim;
		setValue(i_alpha, i_beta, i_gamma);
	}

	public void setValue(double i_alpha, double i_beta, double i_gamma) {
		this.beta = i_beta;
		this.gamma = i_gamma;
		this.alpha = i_alpha;
	}

	/**
	 * Initial population <br>
	 * Initial the population by random of (st,en) 
	 * @param st	the start point of space
	 * @param en	the end point of space
	 */
	public void init(double[] st, double[] en) {
		fireflies = new ArrayList<>();
		for (int i = 0; i < popNum; i++) {
			fireflies.add(new Firefly(dim, new Point(dim, st, en)));
		}
	}

	/**
	 * Light function <br>
	 * You must first over write the function
	 * @param args	the point of firefly
	 * @return	the weight of firefly
	 */
	public abstract double f(double[] args);

	public void calcuLight() {
		for (Firefly firefly : fireflies) {
			firefly.setLight(f(firefly.getPoint().getDims()));
		}
	}

	/**
	 * calculated Euclidean distance<br>
	 * calculate the euclidean distance of two point
	 * @param a	the point of one
	 * @param b	the point of two
	 * @return	the distance between one and two
	 */
	public double calcuDistance(double[] a, double[] b) {
		double distance = 0;
		int n = a.length;
		for (int i = 0; i < n; i++) {
			distance += (a[i] - b[i]) * (a[i] - b[i]);
		}
		return Math.sqrt(distance);
	}

	/**
	 * calculated attraction of firefly<br>
	 * calculated the attraction of firefly and set the target vector into firefly.
	 */
	public void calcuAttraction() {
		for (int i = 0; i < popNum; i++) {
			for (int j = 0; j < popNum; j++) {
				Firefly fireflyi = fireflies.get(i);
				Firefly fireflyj = fireflies.get(j);
				if (i != j && fireflyj.getLight() > fireflyi.getLight()) {
					double disij = calcuDistance(fireflyj.getPoint().getDims(), fireflyi.getPoint().getDims());
					double attraction = beta * Math.pow(Math.E, -gamma * disij * disij);
					if (fireflyi.getMaxAttraction() < attraction) {
						fireflyi.setMaxAttraction(attraction);
						Point vector = PointUtil.add(
								PointUtil.mul((PointUtil.sub(fireflyj.getPoint(), fireflyi.getPoint())), beta),
								PointUtil.mul(new Point(dim, 0, 1), alpha));
						fireflyi.setVector(vector);
					}
				}
			}
		}
	}

	/**
	 * fireflies move to new point
	 */
	public void move() {
		for (Firefly firefly : fireflies) {
			firefly.move();
		}
	}
	
	public void myFuntion(){
		
	}
	
	@SuppressWarnings("unchecked")
	public void printFirflies() {
		Collections.sort(fireflies);
		for (Firefly firefly : fireflies) {
			System.out.println(firefly);
		}
	}

	/**
	 * start the iteration<br>
	 * input the max number of iteration and Moving range ,now start to iteration. 
	 * @param i_maxT	Maximum iteration number
	 * @param st	the start point of space
	 * @param en	the end point of space
	 */
	public void start(int i_maxT, double[] st, double[] en) {
		this.maxT = i_maxT;
		init(st, en);
		calcuLight();
		printFirflies();
		while (i_maxT-- > 0) {
			calcuAttraction();
			myFuntion();
			move();
			calcuLight();
		}
		System.out.println("------------------------------------");
		printFirflies();
	}
}
