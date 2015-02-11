/**
 * This code was created in reference to the Single-View Metrology: Algorithms and Applications by Antonio Criminisi. It performs all the height measuring
 * and stores important information in order to perform this calculation. 
 * Criminisi, A. (2002). Single-view metrology: Algorithms and applications. In Pattern Recognition (pp. 224-239). Springer Berlin Heidelberg.
 * @author jessicaturner
 *
 */
public class HeightMeasurement {
	
	private Vector vanishing_point = null;
	private Vector vanishing_line = null;
	private double alpha = 0;
	
	/*
	 * We must have a vanishing point and vanishing line in order to perform any calculations. 
	 */
	public HeightMeasurement(Vector v, Vector v_l){
		vanishing_point = v;
		vanishing_line = v_l;
	}
	
	public Vector getVanishingPoint(){
		return vanishing_point;
	}
	
	public Vector getVanishingLine(){
		return vanishing_line;
	}
	
	public double getAlpha(){
		return alpha;
	}
	
	/**
	 * This algorithm computes the metric factor as per step four of the Criminisi algorithm 2.
	 * @param ref_top the vector that represents the top coordinate of the reference object.
	 * @param ref_base the vector that represents the base coordinate of the reference object.
	 * @param ref_height the height of the reference object.
	 * @return alpha the metric scale factor. 
	 */
	
	public double calculateMetricFactor(Vector ref_top, Vector ref_base, double ref_height){
		double top_line = ref_base.crossProduct(ref_top).getAbsoluteValue();
		System.out.println("ABSOLUTE VALUE: " + top_line);
		double bot_line = ref_height * (vanishing_line.dotProduct(ref_base)) * (vanishing_point.crossProduct(ref_top).getAbsoluteValue());
		System.out.println("BOT LINE: " + bot_line);
		alpha = (top_line / bot_line) * -1.0;
		System.out.println("ALPHA: " + alpha);
		return alpha;
	}

	/**
	 * This algorithm computes the height of the object as per step five b of the Criminisi algorithm 2.
	 * @param top the vector that represents the top coordinate of the object we are measuring.
	 * @param bot the vector that represents the bottom coordinate of the object we are measuring.
	 * @return the height of the object. 
	 */
	
	public double calculateHeight(Vector top, Vector bot){
		double top_line = bot.crossProduct(top).getAbsoluteValue();
		System.out.println("HEIGHT TOP LINE: " + top_line);
		double bot_line = alpha * (vanishing_line.dotProduct(bot)) * (vanishing_point.crossProduct(top).getAbsoluteValue());
		System.out.println("BOTTOM LINE HEIGHT: " + bot_line);
		return (top_line / bot_line) * -1.0;
	}
}
