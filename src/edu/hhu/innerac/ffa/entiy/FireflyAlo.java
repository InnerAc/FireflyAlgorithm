package edu.hhu.innerac.ffa.entiy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.hhu.innerac.ffa.template.Point;
import edu.hhu.innerac.ffa.template.PointUtil;

/**
 * @author innerac
 * the Firefly Algorithm of innerac <br>
 * now it is only a normal algorithm for study.<br>
 * 萤火虫基础算法，仅仅包含基础的方法，它是一个抽象类。
 */
public abstract class FireflyAlo {

	protected List<Firefly> fireflies;	//Firefly population 萤火虫的种群表示
	protected int popNum;					//the number of firefly population 萤火虫的种群数量
	protected int dim;					//the space dimension 萤火虫所处空间的维度
	protected int maxT;					//Maximum iteration number 萤火虫算法最大迭代次数

	protected double beta, alpha, gamma;	//Necessary value 一些必要的参数

	
	/**
	 * constructed function <br>
	 * 初始化，必要参数默认值如下：<br>
	 * default alpha = 0.02, beta is 1.0, game = 1.0<br>
	 * @param i_popNum	the number of firefly population 萤火虫种群数量
	 * @param i_dim		the space dimension 所处空间维度
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
	 * 初始化时设置所有的值和参数<br>
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

	/**
	 * 设置必要的参数，用于初始化时使用默认参数的情况<br>
	 * @param i_alpha	alpha参数
	 * @param i_beta	beta参数
	 * @param i_gamma	gamma参数
	 */
	public void setValue(double i_alpha, double i_beta, double i_gamma) {
		this.beta = i_beta;
		this.gamma = i_gamma;
		this.alpha = i_alpha;
	}

	/**
	 * Initial population <br>
	 * Initial the population by random of (st,en) <br>
	 * 初始化种群，随机初始化种群中萤火虫的位置。输入一个多维空间的范围，并且服从均匀分布。<br>
	 * @param st	the start point of space 不同维度的最低值
	 * @param en	the end point of space 不同维度的最高值
	 */
	public void init(double[] st, double[] en) {
		fireflies = new ArrayList<>();
		for (int i = 0; i < popNum; i++) {
			fireflies.add(new Firefly(dim, new Point(dim, st, en)));
		}
	}

	/**
	 * Light function <br>
	 * 亮度计算函数，在实例化时需要复写此方法，这是方法就是用来计算寻优的函数。<br>
	 * You must first over write the function<br>
	 * @param args	the point of firefly 函数方法的参数
	 * @return	the weight of firefly 函数返回的结果
	 */
	public abstract double f(double[] args);

	/**
	 * 计算萤火虫种群中每个萤火虫的亮度
	 */
	public void calcuLight() {
		for (Firefly firefly : fireflies) {
			firefly.setLight(f(firefly.getPoint().getDims()));
		}
	}

	/**
	 * calculated Euclidean distance<br>
	 * calculate the euclidean distance of two point<br>
	 * 计算两个节点的欧式距离，得到的结果作为公示里面的中间变量<br>
	 * @param a	the point of one 第一个点坐标
	 * @param b	the point of two 第二个点坐标
	 * @return	the distance between one and two 欧式距离
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
	 * calculated the attraction of firefly and set the target vector into firefly.<br>
	 * 便利萤火虫种群，计算出每个萤火虫的最大吸引度及移动向量
	 */
	public void calcuAttraction() {
		for (int i = 0; i < popNum; i++) {
			for (int j = 0; j < popNum; j++) {
				Firefly fireflyi = fireflies.get(i);
				Firefly fireflyj = fireflies.get(j);
				if (i != j && fireflyj.getLight() > fireflyi.getLight()) {	//当萤火虫j的亮度大于萤火虫i的亮度时
					double disij = calcuDistance(fireflyj.getPoint().getDims(), fireflyi.getPoint().getDims());
					double attraction = beta * Math.pow(Math.E, -gamma * disij * disij);	//计算萤火虫j对萤火虫i的吸引度
					if (fireflyi.getMaxAttraction() < attraction) {	//当此时的吸引度比萤火虫i此时的最大吸引度大时
						fireflyi.setMaxAttraction(attraction);
						Point vector = PointUtil.add(
								PointUtil.mul((PointUtil.sub(fireflyj.getPoint(), fireflyi.getPoint())), beta),
								PointUtil.mul(new Point(dim, 0, 1), alpha));	//计算移动方向向量
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
	 * 开始执行算法<br>
	 * input the max number of iteration and Moving range ,now start to iteration. <br>
	 * 输入最大迭代次数及萤火虫所处空间的范围。<br>
	 * @param i_maxT	Maximum iteration number 最大迭代次数
	 * @param st	the start point of space 空间最低值
	 * @param en	the end point of space 空间最高值
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
