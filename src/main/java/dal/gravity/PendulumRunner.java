package dal.gravity;

import java.text.*;

/** 
 * compares the values of a simple pendulum using the harmonic motion equation
 * versus the Euler algorithm approximation
 */
public class PendulumRunner {


    public static void main (String [] args) {
        NumberFormat nf = NumberFormat.getInstance ();
        nf.setMaximumFractionDigits (3);

        double delta = (args.length == 0) ? .1 : Double.parseDouble (args[0]);
        double sLen = 10, pMass = 10, theta0 = Math.PI/30;

        // create earth gravity model and jupiter gravity model
        GravityModel earthGModel = new GravityConstant(GravityConstant.EARTH_GRAVITY);
        GravityModel jupiterGModel = new GravityConstant(GravityConstant.JUPITER_GRAVITY);
        
        RegularPendulum rp = new RegularPendulum (sLen, pMass, theta0, delta, earthGModel);
        SimplePendulum sp = new SimplePendulum (sLen, pMass, theta0, earthGModel);
        RegularPendulum rpCoarse = 
                new RegularPendulum (sLen, pMass, theta0, .1, earthGModel);

        // print out difference in displacement in 1 second intervals
        // for 20 seconds
        int iterations = (int) (1/delta);
        System.out.println ("analytical vs. numerical displacement (fine, coarse)");
        for (int second = 1; second <= 20; second++) {
        	
        	//change the gravity to Jupiter
        	if(second == 12){
        		rp.setGravity(jupiterGModel);
                sp.setGravity(jupiterGModel);
                rpCoarse.setGravity(jupiterGModel);
        	}
            for (int i = 0; i < iterations; i++) rp.step ();
            for (int i = 0; i < 10; i++) rpCoarse.step (); 
            if(second == 12)
            	System.out.println("\nFollowing are Jupiter's Gravity");
            System.out.println ("t=" + second + "s: \t" + 
                    nf.format (Math.toDegrees (sp.getTheta (second))) 
            + "\t" +
            nf.format (Math.toDegrees (rp.getLastTheta ()))
            + "\t" + 
            nf.format (Math.toDegrees (rpCoarse.getLastTheta ())));
        }
    }
}
