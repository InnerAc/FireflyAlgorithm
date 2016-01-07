package edu.hhu.innerac.ffa.entiy.extension;

import java.util.Collections;

import edu.hhu.innerac.ffa.entiy.Firefly;
import edu.hhu.innerac.ffa.entiy.FireflyAlo;
import edu.hhu.innerac.ffa.template.Point;

/**
 * @author innerac
 * 对萤火虫种群中最优值进行随机扰动的萤火虫算法
 */
public abstract class FireflyAloDisturbance extends FireflyAlo {


	protected boolean isDisturbance = false;
	
	public FireflyAloDisturbance(int i_popNum, int i_dim) {
		super(i_popNum, i_dim);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * set random disturbance
	 */
	public void setRandomDisturbance(){
		this.isDisturbance = true;
	}
	
	public void unsetRandomDisturbance(){
		this.isDisturbance = false;
	}
	
	@SuppressWarnings("unchecked")
	public void randomDisturbance(){
		if(this.isDisturbance){
			Collections.sort(fireflies);
			Firefly firefly = fireflies.get(0);
			firefly.setMaxAttraction(1.0);
			Point vector = new Point(dim, 0, alpha);
			firefly.setVector(vector);
		}
	}
	
	@Override
	public void myFuntion(){
		randomDisturbance();
	}

}
