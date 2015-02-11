/**
 * This class represents [x,y,z] vectors.
 * @author jessicaturner
 *
 */
public class Vector {

	private double x = 0;
	private double y = 0;
	private double z = 0;
	boolean vertical = false;
	
	public Vector(double one, double two, double three){
		x = one;
		y = two;
		z = three;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public double getZ(){
		return z;
	}
	
	/**
	 * Given a vector calculate the cross product of these two vectors.
	 * [a,b,c] & [d,e,f]
	 * x = (b * f) - (c * e)
	 * y = (c * d) - (a * f)
	 * z = (a * e) - (b * d)
	 * return [x,y,z]
	 * @param v
	 * @return
	 */
	public Vector crossProduct(Vector v){
		double one = (y*v.getZ()) - (z*v.getY());
		double two = (z*v.getX()) - (x*v.getZ());
		double three = (x*v.getY()) - (y*v.getX());
		return new Vector(one, two, three);
	}
	
	/**
	 * Given a vector calculate the dot product of these two vectors.
	 * a . b = (ax * bx) + (ay * by) + (az * bz)
	 * @param v
	 * @return
	 */
	public double dotProduct(Vector v){
		return (x * v.getX()) + (y * v.getY()) + (z * v.getZ());
	}
	
	/**
	 * Get the absolute value of this vector.
	 * |a| = sqrt(x^2 + y^2 + z^2);
	 * @return
	 */
	public double getAbsoluteValue(){
		return Math.sqrt((x*x) + (y*y) + (z*z));
	}
	
	/**
	 * Multiple this vector by a given scale.
	 * @param scale
	 * @return
	 */
	public Vector scalarMultiplication(double scale){
		return new Vector((scale *x), (scale * y), (scale * z));
	}
	
	/**
	 * Subtract one vector from another.
	 * a = v + -k
	 * @param v
	 * @return
	 */
	public Vector subtract(Vector v){
		return new Vector((x - v.getX()), (y - v.getY()), (z - v.getZ()));
	}
	
	@Override
	public String toString(){
		if(vertical == false)
			return "[" + x + ", " + y + ", " + z + "]";
		else 
			return " _   _\n| " + x + " |\n| " + y + " |\n| " + z + " |\n -   -";
	}
	
	/**
	 * This represents whether a vector is a vertical or horizontal vector.
	 */
	public void transpose(){
		if(vertical == true)
				vertical = false;
		else
				vertical = true;
	}
}
