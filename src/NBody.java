/*
 * NBody Simulation - visually represent solar system using Newtonian Physics
 * Michael Gardiner
 * Lab Due = 2-26-16
 */

import java.io.FileInputStream;
import java.util.Scanner;

public class NBody {

	public static void main(String[] args) throws Exception{
		// read in planet information
		Scanner s = new Scanner(new FileInputStream("planets.txt"));
		// number of planets
		int N = s.nextInt();
		// radius of universe
		double R = s.nextDouble();
		StdDraw.setXscale(-R,R);
		StdDraw.setYscale(-R,R);
		
		// Gravitational Constant
		double G = 6.67e-11;
		// time step
		double dt = 25000; // time delta
		int t = 0;

		// data structures to store information related to the planets
		double[] x = new double[N];
		double[] y = new double[N];
		double[] vx = new double[N];
		double[] vy = new double[N];
		double[] m = new double[N];
		String[] img = new String[N];

		/* Step 1: Read in planet data from file and store in data structures */

		// Adding each value to it's assigned array:
		for (int i = 0; i < N; i++) {
			x[i] = s.nextDouble();
			y[i] = s.nextDouble();
			vx[i] = s.nextDouble();
			vy[i] = s.nextDouble();
			m[i] = s.nextDouble();
			img[i] = s.next();
		}

		while(true) {
			StdDraw.picture(0, 0, "img/starfield.jpg");



			for (int i = 0; i < N; i++) {
				double Fx = 0; double Fy = 0, ax, ay;
				for (int j = 0; j < N; j++) {
					double r;
					if (j != i) {
						r = Math.sqrt(Math.pow((x[i]-x[j]), 2) + Math.pow((y[i]-y[j]), 2));
						Fx += (G * m[i]*m[j] / Math.pow(r, 2)  * (x[j] - x[i])) /r ;
						Fy += (G * m[i]*m[j] / Math.pow(r, 2)  * (y[j] - y[i])) /r ;
					}

				}
				ax = Fx / m[i];
				ay = Fy / m[i];
				vx[i] += (dt * ax);
				vy[i] += (dt * ay);
				x[i] += (dt * vx[i]);
				y[i] += (dt * vy[i]);

				// Drawing the picture, along with the planets at their original and new positions
				StdDraw.picture(x[i], y[i], "img/" + img[i]);
				StdDraw.show();
			}
		}
	}

}
