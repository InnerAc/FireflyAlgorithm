package edu.hhu.innerac.ffa.template;

import java.util.Arrays;

public class Point {
	private int dimension;
	private double[] dims;
	
	public Point(int dim){
		this.dimension = dim;
		dims = new double[dimension];
	}
	public Point(int dim,double dimst[]){
		dims = dimst.clone();
		this.dimension = dim;
	}
	
	public Point(int dim,double rst,double ren){
		this.dimension = dim;
		dims = new double[dimension];
		for(int i=0;i< dim;i++){
			dims[i] = rst + (ren - rst)*Math.random();
		}
	}
	
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
	
	public Point add(Point point){
		if(this.dimension == point.getDimension()){
			for(int i=0;i<this.dimension;i++){
				dims[i] += point.dims[i];
			}
		}
		return this;
	}
	
	public Point sub(Point point){
		if(this.dimension == point.getDimension()){
			for(int i=0;i<this.dimension;i++){
				dims[i] -= point.dims[i];
			}
		}
		return this;
	}
	
	public Point clone(){
		Point clonePoint = new Point(dimension,dims.clone());
		return clonePoint;
	}
	@Override
	public String toString() {
		return "Point [dimension=" + dimension + ", dims=" + Arrays.toString(dims) + "]";
	}
}
