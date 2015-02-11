import java.util.ArrayList;


/**
 * This class represents lines based in the form y = mx + c.
 * @author jessicaturner
 *
 */
public class Lines {

	private int x1 = 0;
	private int y1 = 0;
	private int x2 = 0;
	private int y2 = 0;
	private double gradient = 0; 
	private double c = 0;
	
	
	public Lines(int one, int two, int three, int four){
		x1 = one;
		y1 = two;
		x2 = three;
		y2 = four;
		gradient = (double) ((double) (y2 - y1) / (double) (x2 - x1));
		c =  y1 + (gradient * (x1 * -1));
	}
	
	public int getX1(){
		return x1;
	}
	
	public int getY1(){
		return y1;
	}
	
	public int getX2(){
		return x2;
	}
	
	public int getY2(){
		return y2;
	}
	
	public double getGradient(){
		return gradient;
	}
	
	public double getC(){
		return c;
	}
	
	public void getEquation(){
		System.out.println("y = " + gradient + "x + " + c);
	}
	
	/**
	 * Find the intersection of this line and the line provided. 
	 * @param l
	 * @return
	 */
	public Point getIntersection(Lines l){
		double dx = gradient + (l.getGradient() * -1);
		double dy = l.getC() + (-1 * c);
		double int_x = dy / dx;
		double int_y = int_x * gradient + c;
		Point p = new Point((int) Math.round(int_x), (int) Math.round(int_y));
		return p;
	}
	
	public double getYDiff(){
		return y2 - y1;
	}
	
	public double getXDiff(){
		return x2 - x1;
	}
	
}
