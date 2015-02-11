import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;


public class MainClass {
	
	//The threshold for detecting the edges in the image
	static int THRESHOLD = 620;
	//The threshold for detecting if this accumulator cell is a line or not
	static int LINE_THRESHOLD = 57;
	//List for storing all the lines detected in this image
	static ArrayList<Lines> lines = new ArrayList<Lines>();
	//List for storing all the intersecting points of those lines
	static ArrayList<Point> points = new ArrayList<Point>();
	
	//Different reference heights depending on image loaded
	//Reference height of the object in the image
	//paper straight as ref
	//private static double height_ref = 14.5;
	//paper bottom ref / dist top
	private static double height_ref = 14.0;
	//paper distort (skew) ref
//	private static double height_ref = 9.5;
	//paper left ref 
	//private static double height_ref = 11.2;
	//paper right ref
	//private static double height_ref = 10.1;
	//paper top ref / dist bot
	//private static double height_ref = 14.8;
	
	//Coordinates used for different images that are loaded in order to measure heights.
	//Actual height = 29.7cm
	//paper straight as ref
	/*static private Vector top_ref = new Vector(996, 840, 0);
	static private Vector bot_ref = new Vector(996, 1808, 0);
	static private Vector top = new Vector(952, 310, 0);
	static private Vector bot = new Vector(952, 2324, 0);*/
	//HEIGHT = 30.98 cms //thresh 300 //line thresh 300
	
	//paper bot ref
	/*static private Vector top_ref = new Vector(950,784,0);
	static private Vector bot_ref = new Vector(950,1471,0);
	static private Vector top = new Vector(940,400,0);
	static private Vector bot = new Vector(940,1834,0);*/
	//Height = 29.67 cm //thresh 300 //line thresh 300
	
	//paper distort ref
	/*static private Vector top_ref = new Vector(958,752,0);
	static private Vector bot_ref = new Vector(958,1248,0);
	static private Vector top = new Vector(873,402,0);
	static private Vector bot = new Vector(873,1852,0);*/
	//Height = 22.06 cm //thresh 320 //line thresh 150
	
	//paper left ref 
	/*static private Vector top_ref = new Vector(946,914,0);
	static private Vector bot_ref = new Vector(946,1478,0);
	static private Vector top = new Vector(972,386,0);
	static private Vector bot = new Vector(972,1992,0);*/
	//Height = 35.12 cm //thresh 550 //line thresh 145
	
	//paper right ref
	 /*static private Vector top_ref = new Vector(976,895,0);
	 static private Vector bot_ref = new Vector(976,1429,0);
	 static private Vector top = new Vector(1054,387,0);
	 static private Vector bot = new Vector(1054,1983,0);*/
	 //Height = 29.14 cm //threshold 200 //line thresh 300
	
	//paper top ref
	/*static private Vector top_ref = new Vector(952,565,0);
	static private Vector bot_ref = new Vector(952,1377,0);
	static private Vector top = new Vector(946,141,0);
	static private Vector bot = new Vector(946,1804,0);*/
	//Height = 29.12 //threshold 300 //line thresh 250
	
	//Actual height = 164.0 cm
	//dist bot
	/*static private Vector top_ref = new Vector(882, 1017, 0);
	static private Vector bot_ref = new Vector(882, 1140, 0);
	static private Vector top = new Vector(892, 802, 0);
	static private Vector bot = new Vector(892, 2161, 0);*/
	//Height = 167.012 // threshold 700 //line thresh 75
	
	//dist left
	/*static private Vector top_ref = new Vector(903, 942, 0);
	static private Vector bot_ref = new Vector(903, 1027, 0);
	static private Vector top = new Vector(909, 684, 0);
	static private Vector bot = new Vector(909, 2100, 0);*/
	//Height = 166.58 //threshold 550 //line thresh 90
	
	//dist right
	/*static private Vector top_ref = new Vector(933, 1034, 0);
	static private Vector bot_ref = new Vector(933, 1122, 0);
	static private Vector top = new Vector(925, 773, 0);
	static private Vector bot = new Vector(925, 2114, 0);*/
	//Height = 163.25 //threshold 650 //line thresh 80
	 
	//dist skew
	/*static private Vector top_ref = new Vector(930, 1032, 0);
	static private Vector bot_ref = new Vector(930, 1111, 0);
	static private Vector top = new Vector(933, 812, 0);
	static private Vector bot = new Vector(933, 2174, 0);*/
	//Height = 169.21 //threshold 800 //line thresh 52
	
	//dist straight
	/*static private Vector top_ref = new Vector(881, 1059, 0);
	static private Vector bot_ref = new Vector(881, 1185, 0);
	static private Vector top = new Vector(892, 839, 0);
	static private Vector bot = new Vector(892, 2254, 0);*/
	//Height = 161.41 //threshold 600 //line thresh 55
	
	//dist top
	static private Vector top_ref = new Vector(873, 972, 0);
	static private Vector bot_ref = new Vector(873, 1101, 0);
	static private Vector top = new Vector(879, 742, 0);
	static private Vector bot = new Vector(879, 2185, 0);
	//Height = 164.70 //threshold 550 //line thresh 60
	
	public static void main(String [] args){
		//start time of program
		long start = System.currentTimeMillis();
		
		//Different test images
		//BufferedImage image = loadImage("paper_straight_as_ref.jpg");
		//BufferedImage image = loadImage("paper_bot_ref.jpg");
		//BufferedImage image = loadImage("paper_distort_ref.jpg");
		//BufferedImage image = loadImage("paper_left_ref.jpg");
		//BufferedImage image = loadImage("paper_right_ref.jpg");
		//BufferedImage image = loadImage("paper_top_ref.jpg");
		
		//BufferedImage image = loadImage("dist_bot.jpg");
		//BufferedImage image = loadImage("dist_left.jpg");
		//BufferedImage image = loadImage("dist_right.jpg");
		//BufferedImage image = loadImage("dist_skew.jpg");
		//BufferedImage image = loadImage("dist_straight.jpg");
		BufferedImage image = loadImage("dist_top.jpg");
		
		//edge detect and line detect
		cannyEdgeDetect(image, THRESHOLD);
		
		System.out.println("Edge detection finished");
		//Lists to store horizontal and vertical lines
		ArrayList<Lines> horizontal = new ArrayList<Lines>();
		ArrayList<Lines> vertical = new ArrayList<Lines>();
		
		//Sort all the lines into either horizontal or vertical
		for(int i = 0; i < lines.size(); i++){
			Lines line = lines.get(i);
			//Get the x coordinate difference
			double dx = line.getX2() - line.getX1();
			//Get the y coordinate difference
			double dy = line.getY2() - line.getY1();
			/*if the line gradient is 0 then the line is horizontal, if the difference
			 * in the x coord is bigger than y coord then it's horizontal. Otherwise it is a
			 * vertical line.
			 */
			if(line.getGradient()==0|| dx > dy){
				horizontal.add(line);
			} else {
				vertical.add(line);
			}
		}
		
		System.out.println("HORIZONTAL: " + horizontal.size() + " VERTICAL: " + vertical.size());
		
		//Find the horizontal vanishing point and the vertical vanishing point
		Point hp = getVanishingPoint(horizontal);
		Point vp = getVanishingPoint(vertical);
		
		/* If both are null then there are no vanishing points detected in the image - this means
		 * there is not enough information in the image to find the vanishing points. Increase edge detection
		 * threshold and lower line threshold to see if this improves information found in image.
		 */
		if(vp==null&&hp==null){
			System.err.println("No vanishing points.");
			System.exit(0);
		}
		
		/*
		 * No vertical vanishing point means that there is a horizontal vanishing point but no vertical. 
		 * It is possible that there are not enough vertical lines in the image in order to detect this point, or that there is no
		 * skew in this direction. 
		 */
		if(vp==null){
			System.err.println("No vertical vanishing point.");
			System.exit(0);
		}
		
		/*
		 * No horizontal vanishing point means that the is a vertical vanishing point but no horizontal.
		 * It is possible that there are not enough horizontal lines in the image in order to detect this point, or that
		 * there is not enough skew in this direction.
		 */
		if(hp==null){
			System.err.println("No horizontal vanishing point.");
			System.exit(0);
		}
		
		//Vertical vanishing point and horizontal vanishing point
		Vector vvp = new Vector(vp.getX(), vp.getY(), 0);
		Vector hvp = new Vector(hp.getX(), hp.getY(), 0);
		//Actual vanishing point of all lines detected in image
		Vector vanishing_point = vvp.crossProduct(hvp);
		//The vanishing line
		Vector vanishing_line = new Vector((hvp.getX()-vvp.getX()), (hvp.getY()-vvp.getY()), (hvp.getZ()-vvp.getZ()));
		System.out.println("VANISHING LINE: " + vanishing_line.toString());
		//The measurement object 
		HeightMeasurement measurer = new HeightMeasurement(vanishing_point, vanishing_line);
		//Calculate the metric factor
		measurer.calculateMetricFactor(top_ref, bot_ref, height_ref);
		//Get a measurement of the object
		double height = measurer.calculateHeight(top, bot);
		//Print out the height
		System.out.println("HEIGHT = " + height);
		//Get the end time
		long end = System.currentTimeMillis();
		//Find out how long it took to calculate the height
		System.out.println("TIME: " + ((end - start) / 1000.0));
		
	}

	/*
	 * Load the image based on the file name into a BufferedImage object. 
	 */
	//checked
	public static BufferedImage loadImage(String filename){
		BufferedImage tmp = null;
		try {
			tmp = ImageIO.read(new File(filename));
		} catch(IOException e) {
			System.err.println(e.getMessage());
		}
		return tmp;
	}
	
	/*
	 * Save the image to the current file directory.
	 */
	//checked
	public static void saveImage(String newFileName, int [] pixels, int width, int height){
		try {
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			image.setRGB(0, 0, width, height, pixels, 0, width);
			File file = new File(newFileName + ".jpg");
			ImageIO.write(image, "jpg", file);
		} catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}
	
	/*Ma, X., Li, B., Zhang, Y., & Yan, M. (2012). The canny edge detection and its improvement. 
	In Artificial Intelligence and Computational Intelligence (pp. 50-58). Springer Berlin Heidelberg.*/

	static double GAUSSIAN_SUM = 159.0;
	
	public static void cannyEdgeDetect(BufferedImage image, int threshold){
		
		int height = image.getHeight();
		int width = image.getWidth();
		//turn image into array that can be manipulated
		int [] oldPixels = image.getRGB(0, 0, width, height, null, 0, width);
		int length = oldPixels.length;
		//Remove alpha channel, only want to deal with RGB colours
		for(int i: oldPixels){
			i &= 0x00FFFFFF;
		}
		//new pixel array to store results in
		int [] newPixels = new int[length];
		//origPixels array used to manipulate original image with new information from detected lines
		int [] origPixels = oldPixels.clone();
		
		//For all the pixels calculate the position indexes of pixels to be manipulated
		for(int y = 0; y < height; y++){
			
			int y_minus_two = wrap(y-2, height) * width;
			int y_minus_one = wrap(y-1, height) * width;
			int y_plus_one = wrap(y+1, height) * width;
			int y_plus_two = wrap(y+2, height) * width;
			int y_ = y * width;
			
			for(int x = 0; x < width; x++){
				
				int x_minus_two = wrap(x-2, width);
				int x_minus_one = wrap(x-1, width);
				int x_plus_one = wrap(x+1, width);
				int x_plus_two = wrap(x+2, width);
				
				int a = y_minus_two + x_minus_two;
				int b = y_minus_two + x_minus_one;
				int c = y_minus_two + x;
				int d = y_minus_two + x_plus_one;
				int e = y_minus_two + x_plus_two;
				
				int f = y_minus_one + x_minus_two;
				int g = y_minus_one + x_minus_one;
				int h = y_minus_one + x;
				int i = y_minus_one + x_plus_one;
				int j = y_minus_one + x_plus_two;
				
				int k = y_ + x_minus_two;
				int l = y_ + x_minus_one;
				int m = y_ + x;
				int n = y_ + x_plus_one;
				int o = y_ + x_plus_two;
				
				int p = y_plus_one + x_minus_two;
				int q = y_plus_one + x_minus_one;
				int r = y_plus_one + x;
				int s = y_plus_one + x_plus_one;
				int t = y_plus_one + x_plus_two;
				
				int u = y_plus_two + x_minus_two;
				int v = y_plus_two + x_minus_one;
				int w = y_plus_two + x;
				int z = y_plus_two + x_plus_one;
				int za = y_plus_two + x_plus_two;
				
				//Apply gaussian blur to each pixel colour
				int red = clamp(((red(oldPixels[a]) * 2 
						+ red(oldPixels[b]) * 4 
						+ red(oldPixels[c]) * 5 
						+ red(oldPixels[d]) * 4
						+ red(oldPixels[e]) * 2
						+ red(oldPixels[f]) * 4
						+ red(oldPixels[g]) * 9
						+ red(oldPixels[h]) * 12
						+ red(oldPixels[i]) * 9
						+ red(oldPixels[j]) * 4
						+ red(oldPixels[k]) * 5
						+ red(oldPixels[l]) * 12
						+ red(oldPixels[m]) * 15
						+ red(oldPixels[n]) * 12
						+ red(oldPixels[o]) * 5
						+ red(oldPixels[p]) * 4
						+ red(oldPixels[q]) * 9 
						+ red(oldPixels[r]) * 12
						+ red(oldPixels[s]) * 9
						+ red(oldPixels[t]) * 4
						+ red(oldPixels[u]) * 2
						+ red(oldPixels[v]) * 4
						+ red(oldPixels[w]) * 5
						+ red(oldPixels[z]) * 4
						+ red(oldPixels[za]) * 2) / GAUSSIAN_SUM));
				
				int green = clamp(((green(oldPixels[a]) * 2 
						+ green(oldPixels[b]) * 4 
						+ green(oldPixels[c]) * 5 
						+ green(oldPixels[d]) * 4
						+ green(oldPixels[e]) * 2
						+ green(oldPixels[f]) * 4
						+ green(oldPixels[g]) * 9
						+ green(oldPixels[h]) * 12
						+ green(oldPixels[i]) * 9
						+ green(oldPixels[j]) * 4
						+ green(oldPixels[k]) * 5
						+ green(oldPixels[l]) * 12
						+ green(oldPixels[m]) * 15
						+ green(oldPixels[n]) * 12
						+ green(oldPixels[o]) * 5
						+ green(oldPixels[p]) * 4
						+ green(oldPixels[q]) * 9 
						+ green(oldPixels[r]) * 12
						+ green(oldPixels[s]) * 9
						+ green(oldPixels[t]) * 4
						+ green(oldPixels[u]) * 2
						+ green(oldPixels[v]) * 4
						+ green(oldPixels[w]) * 5
						+ green(oldPixels[z]) * 4
						+ green(oldPixels[za]) * 2) / GAUSSIAN_SUM));
				
				int blue = clamp(((blue(oldPixels[a]) * 2 
						+ blue(oldPixels[b]) * 4 
						+ blue(oldPixels[c]) * 5 
						+ blue(oldPixels[d]) * 4
						+ blue(oldPixels[e]) * 2
						+ blue(oldPixels[f]) * 4
						+ blue(oldPixels[g]) * 9
						+ blue(oldPixels[h]) * 12
						+ blue(oldPixels[i]) * 9
						+ blue(oldPixels[j]) * 4
						+ blue(oldPixels[k]) * 5
						+ blue(oldPixels[l]) * 12
						+ blue(oldPixels[m]) * 15
						+ blue(oldPixels[n]) * 12
						+ blue(oldPixels[o]) * 5
						+ blue(oldPixels[p]) * 4
						+ blue(oldPixels[q]) * 9 
						+ blue(oldPixels[r]) * 12
						+ blue(oldPixels[s]) * 9
						+ blue(oldPixels[t]) * 4
						+ blue(oldPixels[u]) * 2
						+ blue(oldPixels[v]) * 4
						+ blue(oldPixels[w]) * 5
						+ blue(oldPixels[z]) * 4
						+ blue(oldPixels[za]) * 2) / GAUSSIAN_SUM));
				
				//Create new pixel and store in results array
				newPixels[y * width + x] += createPixel(red, green, blue);
			}
		}
		
		//Swap arrays, newPixels now becomes oldPixels, so we can over write newPixels with new results
		System.arraycopy(newPixels, 0, oldPixels, 0, newPixels.length);
		
		//For each pixel calculate the indexes of the pixels we want to manipulate
		for (int y = 0; y < height; y++) {
			
			int y_plus_one = wrap(y+1, height);
			int y_minus_one = wrap(y-1, height);
			int y_plus_oneCoord = y_plus_one * width;
			int y_minus_oneCoord = y_minus_one * width;
			int y_ = y * width;
			
			for (int x = 0; x < width; x++) {
				
				int x_plus_one = wrap(x+1, width);
				int x_minus_one = wrap(x-1, width);
				
				int a = y_minus_oneCoord + x_minus_one;
				int b = y_minus_oneCoord + x;
				int c = y_minus_oneCoord + x_plus_one;
				int d = y_ + x_minus_one;
				int e = y_ + x;
				int f = y_ + x_plus_one;
				int g = y_plus_oneCoord + x_minus_one;
				int h = y_plus_oneCoord + x;
				int i = y_plus_oneCoord + x_plus_one;
				
				//Apply sobel edge detect to each pixel individually by colour
				int redVertical = red(oldPixels[a]) * -1 
								+ red(oldPixels[d]) * -2
								+ red(oldPixels[g]) * -1
								+ red(oldPixels[c])
								+ red(oldPixels[f]) * 2
								+ red(oldPixels[i]);
				
				int greenVertical = green(oldPixels[a]) * -1 
								+ green(oldPixels[d]) * -2
								+ green(oldPixels[g]) * -1
								+ green(oldPixels[c])
								+ green(oldPixels[f]) * 2
								+ green(oldPixels[i]);
				
				int blueVertical = blue(oldPixels[a]) * -1 
								+ blue(oldPixels[d]) * -2
								+ blue(oldPixels[g]) * -1
								+ blue(oldPixels[c])
								+ blue(oldPixels[f]) * 2
								+ blue(oldPixels[i]);
				
				int redHorizontal = red(oldPixels[a])
								+ red(oldPixels[b]) * 2
								+ red(oldPixels[c])
								+ red(oldPixels[g]) * -1
								+ red(oldPixels[h]) * -2
								+ red(oldPixels[i]) * -1;
				
				int greenHorizontal = green(oldPixels[a])
								+ green(oldPixels[b]) * 2
								+ green(oldPixels[c])
								+ green(oldPixels[g]) * -1
								+ green(oldPixels[h]) * -2
								+ green(oldPixels[i]) * -1;
				
				int blueHorizontal = blue(oldPixels[a])
								+ blue(oldPixels[b]) * 2
								+ blue(oldPixels[c])
								+ blue(oldPixels[g]) * -1
								+ blue(oldPixels[h]) * -2
								+ blue(oldPixels[i]) * -1;
				
				//Get the gradients of each pixel
				int verticalGradient = Math.abs(redVertical) + Math.abs(greenVertical) + Math.abs(blueVertical);
				int horizontalGradient = Math.abs(redHorizontal) + Math.abs(greenHorizontal) + Math.abs(blueHorizontal);
				int totalGradient = verticalGradient + horizontalGradient;
				//If it's above a certain threshold it is an edge, otherwise it's not
				if (totalGradient >= threshold) {
					newPixels[e] = 0xFFFFFF; 
					origPixels[e] = 0xFFFFFF;
				} else {
					newPixels[e] = 0x000000;
				}
			}
		}

		//Apply hough transform to the edge detected image
		int [] linePixels = houghTransform(newPixels, threshold, width, height);
		//Save the new image
		saveImage("new_photo", newPixels, width, height);
	}
	
	/*
	 * This method wraps the indexes of pixels to the edge of the image so that we
	 * don't try and access a pixel that doesn't exist within the image.
	 */
	public static int wrap(int pos, int size){
		if(pos < 0){
			pos = 0;
		} else if (pos >= size){
			pos = size-1;
		}
		return pos;
	}
	
	/*
	 * We only want colours between 1-255, this method clamps any colours that are negative to 0
	 * and any larger than 255 to 255 itself. It does not modify anything within this range.
	 */
	public static int clamp(double value){
		int result = (int) (value + 0.5);
		if(result<=0){
			return 0;
		} else if(result > 255){
			return 255;
		} else {
			return result;
		}
	}
	
	/*
	 * Pull out the red colour from the given pixel.
	 */
	public static int red(int pixel) {
		return (pixel >> (2 * 8)) & ((1 << 8) -1);
	}
	
	/*
	 * Pull out the green colour from the given pixel.
	 */
	public static int green(int pixel) {
		return (pixel >> (1 * 8)) & ((1 << 8) -1);
	}
	
	/*
	 * Pull out the blue colour from the given pixel.
	 */
	public static int blue(int pixel) {
		return (pixel >> (0 * 8)) & ((1 << 8) -1);
	}
	
	/*
	 * Create a new pixel given the red, green and blue values.
	 */
	public final static int createPixel(int redValue, int greenValue, int blueValue) {
		assert 0 <= redValue && redValue <= ((1 << 8) -1);
		assert 0 <= greenValue && greenValue <= ((1 << 8) -1);
		assert 0 <= blueValue && blueValue <= ((1 << 8) -1);
		return (redValue << (2 * 8)) + (greenValue << 8) + blueValue;
	}
	
	/**
	 * Perform the hough transform on the sobel edge detect pixels in order to detect straight lines.
	 * Hough Transformation - C++ Implementation. (3 May, 2013). Bruno Keymolen. Retrieved from
	 * http://www.keymolen.com/2013/05/hough-transformation-c-implementation.html on 19 January, 2015. 
	 * @param pixels are the image pixels with edge detection.
	 * @param threshold the threshold at which to detect edges.
	 * @param width of the image.
	 * @param height of the image.
	 */
	//checked
	public static int [] houghTransform(int [] pixels, int threshold, int width, int height){
		
		//Create a new image to draw results into
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		image.setRGB(0, 0, width, height, pixels, 0, width);
		Graphics g = image.createGraphics();
		g.setColor(Color.RED);
		//Set a theta size of 180
		int theta_size = 180;
		//Compute the p size for the accumulator array, should be + or - this array size
		int p_size = (int) (Math.sqrt((width*width) + (height*height)));
		//Accumulator array stores all ps found for a certain edge
		int actual_p_size = p_size*2;
		int [] accumulator = new int[actual_p_size * theta_size];
		double centre_x = width / 2;
		double centre_y = height / 2;
		double deg_to_rads = Math.PI / 180;
		//Loop through the array of pixels
		for(int y = 0; y < height; y++){
			for(int x=0; x < width; x++){
				//If this pixel is an edge pixel (above the threshold)
				if(pixels[y * width + x] >= threshold){
					//For all theta compute the parametric equation of a straight line p
					for(int theta=0; theta < theta_size; theta++){
						double p = (x - centre_x) * Math.cos(theta * deg_to_rads) + (y - centre_y) * Math.sin(theta * deg_to_rads);
						//Get index for accumulator array (p_size is +or-)
						int index = (int) (Math.round(p + p_size) * 180);
						//Increment the accumulator array, the cell will store the number of pixels on the line at position
						// (p, theta)
						accumulator[index + theta]++;
						
					}
				}
			}
		}
		
		//For all results of p found
		for(int p=0; p < actual_p_size; p++){
			//For all theta values
			for(int theta=0; theta < theta_size; theta++){
				//If the accumulator cell is greater than the given threshold, this indicates a straight line
				if(accumulator[p * theta_size + theta] >= (LINE_THRESHOLD)){
					//Now find the local maximum
					int max = accumulator[p * theta_size + theta];
					//For the values near the given max, check to see if they are larger, if this is true the new value is the local maximum
					for(int ly=-4; ly<=4; ly++){
						for(int lx=-4; lx<=4; lx++){
							if((ly+p>=0 && ly+p<actual_p_size) && (lx+theta>=0 && lx+theta < theta_size)){
								if(accumulator[((p+ly)*theta_size)+(theta+lx)] > max){
									max = accumulator[((p+ly)*theta_size)+(theta+lx)];
									ly = lx = 5;
								}
							}
						}
					}
					
					//Get the indexes of the values back in order to draw lines (reverse the formula used to calculate the p values).
					int x1, y1, x2, y2;
					x1 = y1 = x2 = y2 = 0;
					if(theta >= 45 && theta <= 135){
						x1 = 0;
						y1 = (int) ((((p - p_size) - (x1 - (width/2)) * Math.cos(theta * deg_to_rads)) / Math.sin(theta * deg_to_rads)) + (height /2));
						x2 = width;
						y2 = (int) (((((p - p_size) - (x2 - (width/2)) * Math.cos(theta * deg_to_rads))) / Math.sin(theta * deg_to_rads)) + (height /2));
					} else {
						y1 = 0; 
						x1 = (int) (((p-p_size) - ((y1 - (height/2)) * Math.sin(theta * deg_to_rads))) / Math.cos(theta * deg_to_rads) + (width /2));
						y2 = height;
						x2 = (int) (((p-p_size) - ((y2-(height/2)) * Math.sin(theta * deg_to_rads))) / Math.cos(theta * deg_to_rads) + (width /2));
					}
					//Draw a line between these two points
					g.drawLine(x2, y2, x1, y1);
					//Create a new line, if it doesn't already exist add it to the lines array list
					Lines l = new Lines(x1, y1, x2, y2);
					boolean found = false;
					for(Lines li: lines){
						if((li.getX1()==x1)&&(li.getX2()==x2)&&(li.getY1()==y1)&&(li.getY2()==y2)){
							found = true;
							break;
						}
					}
					if(found==false){
						lines.add(l);
					}
				}
			}
		}
		
		//Calculate the vanishing point of all the lines
		Point p = getVanishingPoint(lines);
		//If vanishing point can be calculated, then draw the point on the image
		if(p!=null){
			g.setColor(Color.YELLOW);
			g.fillOval(p.getX()-5, p.getY()-5, 10, 10);
			g.drawLine(0, p.getY(), width, p.getY());
		}
		g.dispose();
		//save the new image
		int [] newPixels = image.getRGB(0, 0, width, height, null, 0, width);
		saveImage("lines", newPixels, width, height);
		//return the results
		return newPixels;
		
	}
	
	/**
	 * Given a set of straight lines in the image this method calculates the vanishing point. In order to
	 * calculate the vanishing point it looks for intersections between all the lines and finds the point
	 * that has the most intersections. This is the vanishing point.
	 * @param the_lines a set of straight lines within the given image.
	 * @return the vanishing point. 
	 */
	public static Point getVanishingPoint(ArrayList<Lines> the_lines){
		
		//Create a temporary array list to store all the different intersection points
		ArrayList<Point> the_points = new ArrayList<Point>();
		
		//If there are no straight lines in the image simply return null, meaning no vanishing point could be calculated
		if(the_lines.size() <= 0){
			return null;
		}
		
		//For a given line, loop through all other lines and calculate the intersection point
		for(int i = 0; i < the_lines.size(); i++){
			for(int j = i+1; j < the_lines.size(); j++){
				Point intersection = the_lines.get(i).getIntersection(the_lines.get(j));
				if(intersection.getX() <= 0 || intersection.getY() <= 0){
					//do nothing
				} else {
					//if this point has been found then increment the number of intersections
					boolean found = false;
					for(Point p: the_points){
						if((p.getX()==intersection.getX()) && (p.getY()==intersection.getY())){
							p.increment();
							found = true;
							break;
						}
					}
					//otherwise add to the list with one increment.
					if(found==false){
						intersection.increment();
						the_points.add(intersection);
					}
				}
			}
		}
		
		//If no straight line intersections are found then return null
		if(the_points.size() <= 0){
			return null;
		}
		
		//Sort the points to find the one with the most intersections
		Collections.sort(the_points);
		
		//Return this as the vanishing point
		System.out.println("Vanishing point = " + the_points.get(0).toString());
		return the_points.get(0);
	}
}
