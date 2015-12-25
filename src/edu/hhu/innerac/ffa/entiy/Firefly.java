package edu.hhu.innerac.ffa.entiy;

import edu.hhu.innerac.ffa.template.Point;

/**
 * @author innerac
 * The entiy of firefly
 */
public class Firefly implements Comparable{

	private Point point = null;
	private int dimension;
	private double Light;
	private double maxAttraction;
	private Point vector = null;
	
	/**
	 * Define the dimension of the space of the firefly <br>
	 * @param dim	the dimension
	 */
	public Firefly(int dim){
		this.dimension = dim;
	}
	/**
	 * Define the dimension of the space of the firefly and the coordinates in space<br>
	 * @param dim	the dimension
	 * @param i_point	the coordinates of firefly
	 */
	public Firefly(int dim,Point i_point){
		this.dimension = dim;
		this.point = i_point.clone();
	}
	
	/**
	 * Firefly move to the target direction.
	 */
	public void move(){
		if(maxAttraction > 0){
			this.point.add(vector);
			maxAttraction = 0;
		}
	}
	
	/**
	 * Gets a copy of the current position of the firefly <br>
	 * You can change your position at any position without having to worry about the original value.
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
		return vector.clone();
	}
	public void setVector(Point vector) {
		this.vector = vector.clone();
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
