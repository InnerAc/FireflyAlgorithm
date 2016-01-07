package edu.hhu.innerac.ffa.template;

import java.util.Arrays;

/**
 * @author innerac
 * The Point class <br>
 * 坐标类，用来方便坐标的表示<br>
 * This class is in order to facilitate the representation and computation of coordinates in multidimensional space.<br>
 * Now ,it has add and subtract,and generate random coordinates.
 */
public class Point {
	private int dimension;
	private double[] dims;
	
	/**
	 * Create a point in {dim} dimension.
	 * @param dim	the dimension
	 */
	public Point(int dim){
		this.dimension = dim;
		dims = new double[dimension];
	}
	/**
	 * Create a point in {dim} dimension and set the coordinates.
	 * @param dim	dimension
	 * @param dimst	coordinates of {dim} dimension
	 */
	public Point(int dim,double dimst[]){
		dims = dimst.clone();
		this.dimension = dim;
	}
	
	/**
	 * Create a point in {dim} dimension and make the random coordinates.
	 * @param dim	dimension
	 * @param rst	the coordinate lower limits
	 * @param ren	the coordinate upper bounds
	 */
	public Point(int dim,double[] rst,double[] ren){
		this.dimension = dim;
		dims = new double[dimension];
		for(int i=0;i< dim;i++){
			dims[i] = rst[i] + (ren[i] - rst[i])*Math.random();
		}
	}
	
	/**
	 * Create a point in {dim} dimension and make the random coordinates from rst to ren in a cube.
	 * @param dim	dimension
	 * @param rst	the coordinates lower limit
	 * @param ren	the coordinates upper bound
	 */
	public Point(int dim,double rst,double ren){
		this.dimension = dim;
		dims = new double[dimension];
		for(int i=0;i< dim;i++){
			dims[i] = rst + (ren - rst)*Math.random();
		}
	}
	
	/**
	 * Create a point in {dim} dimension and make the random coordinates from rst to ren in a cube.<br>
	 * and the type is Int.
	 * @param dim	dimension
	 * @param rst	the coordinates lower limit
	 * @param ren	the coordinates upper bound
	 */
	public Point(int dim,double rst,double ren,String type){
		this.dimension = dim;
		dims = new double[dimension];
		if(type.matches("[iI][nN][tT]"))
		for(int i=0;i< dim;i++){
			dims[i] = (int)(rst + (ren - rst)*Math.random() + 0.5);
		}
	}

	public int getDimension() {
		return dimension;
	}
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	public double[] getDims() {
		return dims;
	}
	public void setDims(double[] dims) {
		this.dims = dims.clone();
	}
	
	/**
	 * This point add a new point.<br>
	 * It will change its own.
	 * @param point	the new point
	 * @return	itself
	 */
	public Point add(Point point){
		if(this.dimension == point.getDimension()){
			for(int i=0;i<this.dimension;i++){
				dims[i] += point.dims[i];
			}
		}
		return this;
	}
	
	/**
	 * This point subtract a new point.<br>
	 * It will change its own.
	 * @param point	the new point
	 * @return	itself
	 */
	public Point sub(Point point){
		if(this.dimension == point.getDimension()){
			for(int i=0;i<this.dimension;i++){
				dims[i] -= point.dims[i];
			}
		}
		return this;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Point clone(){
		Point clonePoint = new Point(dimension,dims.clone());
		return clonePoint;
	}
	@Override
	public String toString() {
		return "Point [dimension=" + dimension + ", dims=" + Arrays.toString(dims) + "]";
	}
}
