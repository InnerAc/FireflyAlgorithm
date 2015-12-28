package edu.hhu.innerac.ffa.test;

import org.junit.Test;

import edu.hhu.innerac.ffa.entiy.Firefly;
import edu.hhu.innerac.ffa.template.Point;
import edu.hhu.innerac.ffa.template.PointUtil;

public class PoinTest {

	@Test
	public void pointTest(){
		Point point1 = new Point(2);
		Point point2 = new Point(2);
		double[] x = new double[2];
		x[0] = 1;
		x[1] = 2;
		point1.setDims(x);
		x[0] = 3;
		x[1] = 4;
		point2.setDims(x);
		
		System.out.println(point1.toString());
		System.out.println(point2.toString());
		
//		point1.add(point2);
		PointUtil.sub(point1, point2);
		
		System.out.println(point1.toString());
	}
	
	@Test
	public void fireflyTest(){
		Point point1 = new Point(2);
		double[] x = new double[2];
		x[0] = 1;
		x[1] = 2;
		point1.setDims(x);
		
		Firefly firefly = new Firefly(2,point1);
		System.out.println(firefly.getPoint());
		x[0] = 3;
		x[1] = 4;
		point1.setDims(x);
		int a = firefly.getDimension();
		a = 3;
		System.out.println(firefly.getDimension());
		System.out.println(firefly.getPoint());
	}
	
	@Test
	public void pointRandTest(){
		Point point = new Point(10,0,10,"Int");
		System.out.println(point);
		point = PointUtil.mul(point, 2);
		System.out.println(point);
	}
	
	@Test
	public void randomTest(){

		Point vector = new Point(2, 0, 0.02);
		System.out.println(vector);
	}
}
