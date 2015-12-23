package edu.hhu.innerac.ffa.template;

public class PointUtil {

	/**
	 * One does not change the original value of the add operation of points.
	 * @param pointa	the first point
	 * @param pointb	the second point
	 * @return			the result
	 */
	public static Point add(Point pointa,Point pointb){
		Point point = pointa.clone();
		return point.add(pointb);
	}
	
	/**
	 * One does not change the original value of the subtract operation of points.
	 * @param pointa	the first point
	 * @param pointb	the second point
	 * @return			the result
	 */
	public static Point sub(Point pointa,Point pointb){
		Point point = pointa.clone();
		return point.sub(pointb);
	}
	
	/**
	 * the multiply operation between point and constant. 
	 * @param point
	 * @param constant
	 * @return
	 */
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
