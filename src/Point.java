import java.util.ArrayList;

/**
 * The point class is used to store (x,y) cartesian coordinate points and the number
 * of times that straight lines intersect at this point. 
 * @author jessicaturner
 *
 */

public class Point implements Comparable {
	
	private int x = 0;
	private int y = 0;
	private int count = 0;
	private ArrayList<Lines> support_set = null;
	
	public Point(int one, int two){
		x = one;
		y = two;
		support_set = new ArrayList<Lines>();
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getCount(){
		return count;
	}
	
	public void increment(){
		count++;
	}

	public String toString(){
		return ("(" + x + ", " + y + ")");
	}
	
	@Override
	public int compareTo(Object o) {
		Point p = (Point) o;
		int other_count = p.getCount();
		if(other_count < count){
			return -1;
		} else if(other_count == count){
			return 0; 
		} else {
			return 1;
		}
	}
	
	public int getSupportSize(){
		return support_set.size();
	}
	
	public void addSupport(Lines l){
		support_set.add(l);
	}
	
	public ArrayList<Lines> getSupportSet(){
		return support_set;
	}
	
	public double getDistance(Lines l){
		double A = l.getXDiff();
		double B = l.getYDiff();
		double gradient = (A / B) * -1.0;
		double c =  y + (gradient * (x * -1));
		double dx = gradient + (l.getGradient() * -1);
		double dy = l.getC() + (-1 * c);
		double int_x = dy / dx;
		double int_y = int_x * gradient + c;
		Point p = new Point((int) Math.round(int_x), (int) Math.round(int_y));
		double new_dx = p.x - x;
		double new_dy = p.y - y;
		double dist = Math.sqrt((new_dx * new_dx) + (new_dy * new_dy));
		return dist;
	}
	

}
