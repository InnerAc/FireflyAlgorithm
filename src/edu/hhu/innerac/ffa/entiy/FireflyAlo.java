package edu.hhu.innerac.ffa.entiy;

import java.util.ArrayList;
import java.util.List;

import edu.hhu.innerac.ffa.template.Point;
import edu.hhu.innerac.ffa.template.PointUtil;

public abstract class FireflyAlo {

	private List<Firefly> fireflies;
	private int popNum;
	private int dim;
	private int maxT;
	
	private double beta,alpha,gama;
	
	public FireflyAlo(int i_popNum,int i_dim){
		this.popNum = i_popNum;
		this.dim = i_dim;
		this.beta = 1.0;
		this.gama = 1.0;
		this.alpha = 0.02;
	}
	
	public FireflyAlo(int i_popNum,int i_dim,double i_alpha,double i_beta,double i_gama){
		this.popNum = i_popNum;
		this.dim = i_dim;
		setValue(i_alpha, i_beta, i_gama);
	}
	
	public void setValue(double i_alpha,double i_beta,double i_gama){
		this.beta = i_beta;
		this.gama = i_gama;
		this.alpha = i_alpha;		
	}
	
	public void init(double st,double en){
		fireflies = new ArrayList<>();
		for(int i=0;i< popNum;i++){
			fireflies.add(new Firefly(dim,new Point(dim, st, en)));
		}
	}
	
	public abstract double f(double[] args);
	
	public void calcuLight(){
		for(Firefly firefly : fireflies){
			firefly.setLight(f(firefly.getPoint().getDims()));
		}
	}
	
	public double calcuDistance(double[]a, double[]b){
		double distance = 0;
		int n = a.length;
		for(int i=0;i<n;i++){
			distance += (a[i] - b[i])*(a[i] - b[i]);
		}
		return Math.sqrt(distance);
	}
	
	public void calcuAttraction(){
		for(int i=0;i<popNum;i++){
			for(int j=0;j<popNum;j++){
				Firefly fireflyi = fireflies.get(i);
				Firefly fireflyj = fireflies.get(j);
				if(i != j && fireflyj.getLight() > fireflyi.getLight()){
					double disij = calcuDistance(fireflyj.getPoint().getDims(), fireflyi.getPoint().getDims());
					double attraction = beta * Math.pow(Math.E, -gama*disij*disij);
					if(fireflyi.getMaxAttraction() < attraction){
						fireflyi.setMaxAttraction(attraction);
						Point vector = PointUtil.add(PointUtil.mul((PointUtil.sub(fireflyj.getPoint(), fireflyi.getPoint())), beta) , PointUtil.mul(new Point(dim, 0, 1), alpha));
						fireflyi.setVector(vector);
					}
				}
			}
		}
	}
	
	public void move(){
		for(Firefly firefly:fireflies){
			firefly.move();
		}
	}
	
	public void printFirflies(){
		for(Firefly firefly : fireflies){
			System.out.println(firefly);
		}
	}
	
	public void start(int i_maxT,double st,double en){
		this.maxT = i_maxT;
		init(st, en);
		calcuLight();
		printFirflies();
		while(i_maxT-- > 0){
			calcuAttraction();
			move();
			calcuLight();
		}
		System.out.println("------------------------------------");
		printFirflies();
	}
}
