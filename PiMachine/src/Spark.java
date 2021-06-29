import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.graphics.GMath;
import acm.graphics.GOval;
import acm.graphics.GPoint;

public class Spark extends GCompound implements Runnable{
	
	public Spark(Color c1,Color c2) {
		
		
		for (int i=0;i<N;i++) {
			
			if(i%2==0) {
				GLine l1 = new GLine(8*GMath.cosDegrees(GMath.toDegrees(2*PI*i/N)),8*GMath.sinDegrees(GMath.toDegrees(2*PI*i/N)),12*GMath.cosDegrees(GMath.toDegrees(2*PI*i/N)),12*GMath.sinDegrees(GMath.toDegrees(2*PI*i/N)));
				l1.setColor(c2);
				add(l1);

				GLine l2 = new GLine(4*GMath.cosDegrees(GMath.toDegrees(2*PI*i/N)),4*GMath.sinDegrees(GMath.toDegrees(2*PI*i/N )),4*GMath.cosDegrees(GMath.toDegrees(2*PI*i/N+PI)),4*GMath.sinDegrees(GMath.toDegrees(2*PI*i/N+PI)));
				l2.setColor(c1);
				
				add(l2);
			}
			else {
				GLine l1 = new GLine(9*GMath.cosDegrees(GMath.toDegrees(2*PI*i/N)),9*GMath.sinDegrees(GMath.toDegrees(2*PI*i/N)),11*GMath.cosDegrees(GMath.toDegrees(2*PI*i/N)),11*GMath.sinDegrees(GMath.toDegrees(2*PI*i/N)));
				l1.setColor(c1);
				add(l1);
			}		
			
		}

		
	}
	
	

	public void run() {
		while(true) {
	    	if(PiEngine.do_spark) {
		       this.setVisible(true);
		       pause(30);
	    	    this.setVisible(false);
	    	    PiEngine.do_spark=false;
	    	}
	    	pause(10);
		}
	}
	

	private static final double PI=Math.PI;
	private static final int N=15;


}
