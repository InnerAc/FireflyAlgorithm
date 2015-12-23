package edu.hhu.innerac.ffa.entiy;

import edu.hhu.innerac.ffa.template.Point;

public class Firefly {

	private Point point = null;
	private int dimension;
	private double Light;
	private double maxAttraction;
	private Point vector = null;
	
	public Firefly(int dim){
		this.dimension = dim;
	}
	public Firefly(int dim,Point i_point){
		this.dimension = dim;
		this.point = i_point.clone();
	}
	
	public void move(){
		if(maxAttraction > 0){
			this.point.add(vector);
			maxAttraction = 0;
		}
	}
	
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
}
