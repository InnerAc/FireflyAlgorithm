package edu.hhu.innerac.ffa.template;

public class PointUtil {

	public static Point add(Point pointa,Point pointb){
		Point point = pointa.clone();
		return point.add(pointb);
	}
	
	public static Point sub(Point pointa,Point pointb){
		Point point = pointa.clone();
		return point.sub(pointb);
	}
	
	public static Point mul(Point point,double constant){
		Point newPoint = point.clone();
		int dim = point.getDimension();
		double[] dims = newPoint.getDims();
		for(int i=0;i<dim;i++){
			dims[i] *= constant;
		}
		return newPoint;
	}
}
