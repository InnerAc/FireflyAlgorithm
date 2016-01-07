package edu.hhu.innerac.ffa.entiy;

import edu.hhu.innerac.ffa.template.Point;

/**
 * @author innerac
 * The entiy of firefly
 * 萤火虫实体类
 */
@SuppressWarnings("rawtypes")
public class Firefly implements Comparable{

	private Point point = null;		//萤火虫所处的位置（坐标）
	private int dimension;			//萤火虫所处空间的维度
	private double Light;			//萤火虫的亮度
	private double maxAttraction;	//最大吸引度，每次移动后置零
	private Point vector = null;	//萤火虫方向向量，移动时的的向量，移动后置空
	
	/**
	 * Define the dimension of the space of the firefly <br>
	 * 定义萤火虫所处空间的维度<br>
	 * @param dim	the dimension	萤火虫的维度
	 */
	public Firefly(int dim){
		this.dimension = dim;
	}
	/**
	 * Define the dimension of the space of the firefly and the coordinates in space<br>
	 * 定义萤火虫所处空间的维度，并且指定萤火虫在空间中所处的位置<br>
	 * @param dim	the dimension
	 * @param i_point	the coordinates of firefly
	 */
	public Firefly(int dim,Point i_point){
		this.dimension = dim;
		this.point = i_point.clone();
	}
	
	/**
	 * Firefly move to the target direction.<br>
	 * 萤火虫按照方向向量移动，移动完成后清空方向数据。<br>
	 */
	public void move(){
		if(maxAttraction > 0){
			this.point.add(vector);
			maxAttraction = 0;
		}
	}
	
	/**
	 * Gets a copy of the current position of the firefly <br>
	 * You can change your position at any position without having to worry about the original value.<br>
	 * 获取萤火虫当前位置的一个拷贝。<br>
	 * @return the position of firefly
	 */
	public Point getPoint() {
		return point.clone();
	}
	public void setPoint(Point point) {
		this.point = point.clone();
	}
	public int getDimension() {
		return dimension;
	}
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	public double getLight() {
		return Light;
	}
	public void setLight(double light) {
		Light = light;
	}
	public double getMaxAttraction() {
		return maxAttraction;
	}
	@Override
	public String toString() {
		return "Firefly [ "+point + ", Light=" + Light + "]";
	}
	public void setMaxAttraction(double maxAttraction) {
		this.maxAttraction = maxAttraction;
	}
	public Point getVector() {
		if (vector == null)
			return null;
		return vector.clone();
	}
	public void setVector(Point vector) {
		this.vector = vector.clone();
	}
	
	public Firefly clone(){
		Firefly firefly = new Firefly(dimension, point);
		firefly.setLight(Light);
		if(vector != null){
			firefly.setMaxAttraction(maxAttraction);
			firefly.setVector(vector);
		}
		return firefly;
	}
	@Override
	public int compareTo(Object o) {
		if(o == null){
			return 0;
		}
		double tmp = this.Light - ((Firefly)(o)).getLight();
		if(tmp < 0)
			return 1;
		if(tmp > 0)
			return -1;
		return 0;
	}
}
