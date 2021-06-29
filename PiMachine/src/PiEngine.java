import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;



import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class PiEngine extends GraphicsProgram{
	
	public void init() {
		setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT);
		setBackground(Color.BLACK);
		wall =new GRect(90,50,10,400);
		wall.setFilled(true);
		wall.setColor(Color.GRAY);
		add(wall);
		collisions=0;
		collisions_lbl= new GLabel("# Collisions (Digits of "+"\u03C0 )"+" :");
		collisions_lbl.setFont("Serif-BOLD-20");
		collisions_Clbl= new GLabel(""+collisions);
		collisions_Clbl.setFont("Serif-BOLD-20");
		collisions_lbl.setColor(Color.PINK);
		collisions_Clbl.setColor(Color.WHITE);
		add(collisions_lbl,400,50);
		add(collisions_Clbl,620,50);
		
		x_axis= new GLine(YX_OFFSET,YY_OFFSET,YX_OFFSET+1600,YY_OFFSET);
		y_axis= new GLine(XX_OFFSET,XY_OFFSET,XX_OFFSET,YY_OFFSET);
		x_axis.setColor(Color.WHITE);
		y_axis.setColor(Color.WHITE);
		add(x_axis);
		add(y_axis);
		l_block=new Block(XX_OFFSET+200,XY_OFFSET+400-50,50);
		l_block.setColor(Color.WHITE);
		
		r_block=new Block(XX_OFFSET+400,XY_OFFSET+400-50,50);
		r_block.setColor(Color.LIGHT_GRAY);
		
		l_block_lbl=new GLabel("kg");
		l_block_lbl.setColor(Color.BLACK);
		l_block_lbl.setFont("Serif-BOLD-15");
		r_block_lbl=new GLabel("kg");
		r_block_lbl.setColor(Color.BLACK);
		r_block_lbl.setFont("Serif-BOLD-15");
		
		spark=new Spark(Color.black.ORANGE,new Color(255,69,0));
		do_spark=false;

		clipURL= getClass().getResource("hitsound.wav");
		tickClip=Applet.newAudioClip(clipURL);
	}
	
	
	public void run() {
		for(int i=0;i<rswlist.length;i++) {  
			
		     double l_lbl_x=l_block.getWidth()/2 - l_block_lbl.getWidth()/2 ;
		     double l_lbl_y=l_block.getHeight()/2- l_block_lbl.getHeight()/2 ;
		     double r_lbl_x=r_block.getWidth()/2 - r_block_lbl.getWidth()/2 ;
		     double r_lbl_y=r_block.getHeight()/2- r_block_lbl.getHeight()/2 ;
			
			collisions=0;
			collisions_Clbl.setLabel(""+collisions);
			l_block.setLocation(XX_OFFSET+200,XY_OFFSET+400-l_block.getHeight());
			l_block_lbl.setLabel(l_block.getM()+" kg");
			
			r_block.setLocation(XX_OFFSET+600,XY_OFFSET+400-r_block.getHeight());
			r_block.setBlockSize(r_block.getHeight()+i*20);
			
			add(l_block);
			add(r_block);
			add(l_block_lbl,l_lbl_x+l_block.getX(),l_lbl_y+l_block.getY());
			add(r_block_lbl,r_lbl_x+r_block.getX(),r_lbl_y+r_block.getY());
			r_block.setM(rswlist[i]);
			r_block_lbl.setLabel(r_block.getM()+" kg");
		    l_block.setV(0.0);
		    r_block.setV(-0.0001);
		    add(spark);
		    new Thread(spark).start();

			pause(1500);
			
		
		    while(true){
		    	for(int l=0;l<LOOP_COUNT;l++) {

		         	l_block.Move();
			        r_block.Move();
			        l_block_lbl.setLocation(l_lbl_x+l_block.getX(),l_lbl_y+l_block.getY());
			        r_block_lbl.setLocation(r_lbl_x+r_block.getX(),r_lbl_y+r_block.getY());
			      //  etLocation((block1.getX()+10),(block1.getY()+30))
			        

			        
			        if(l_block.hitBlock(r_block)) {
			        	
				        spark.setLocation(l_block.getX()+l_block.getWidth(),l_block.getY());
				        do_spark=true;
				        pause(1);
			        	tickClip.play();
			      
			            collisions++;
			            collisions_Clbl.setLabel(""+collisions);
			            double v1=l_block.getV();
			            double v2=r_block.getV();
		         	    long m1=l_block.getM();
		            	long m2=r_block.getM();
			            l_block.setV(((m1-m2)*v1)/(m1+m2) + (2*m2*v2)/(m1+m2));
			            r_block.setV((2*m1*v1)/(m1+m2) + ((m2-m1)*v2)/(m1+m2));
		       	   }
		 	       if(l_block.hitWall(wall)) {
				        spark.setLocation(l_block.getX(),l_block.getY());
				        do_spark=true;
				        pause(1);
		 	    	    tickClip.play();
			            collisions++;
			            collisions_Clbl.setLabel(""+collisions);
				        l_block.setV(-l_block.getV());
				        
			       }
		 	       
			        
		     	}
			    pause(1);
	    	    if(( (l_block.getV()>=0 && r_block.getV()>0 && l_block.getV()<r_block.getV())) ){
	    		    break;
	    	    }
		  
		    
            }

		     
		     for(int n=0;n<=1000;n++) {
		        for(int m=0;m<=10000;m++) {
	            	l_block.Move();
		            r_block.Move();
			     //   r_block_lbl.setLocation(r_block.getX()+4*i+10,r_block.getY()+20+10*i);
			     ///   l_block_lbl.setLocation(l_block.getX()+10,l_block.getY()+20);
			        
			     //   r_block_lbl.setLocation(r_lbl_x,r_lbl_y);
			      //  etLocation((block1.getX()+10),(block1.getY()+30))
			    //  l_block_lbl.setLocation(l_lbl_x,l_lbl_y);
			        
			        l_block_lbl.setLocation(l_lbl_x+l_block.getX(),l_lbl_y+l_block.getY());
			        r_block_lbl.setLocation(r_lbl_x+r_block.getX(),r_lbl_y+r_block.getY());
		            
		        } 
		        pause(1);
		    }
		    
		  }
		
	}	
			
	
	private AudioClip tickClip;
	private long collisions;
	private static final int  LOOP_COUNT=100000;

	private long[] rswlist= {1,100,10000,1000000,100000000};
	private GLabel collisions_lbl,collisions_Clbl,l_block_lbl,r_block_lbl;
	private Block l_block,r_block;
	private GRect wall;
	private GLine x_axis,y_axis;
	private Spark spark;
	private URL clipURL;
	public static boolean do_spark;
	
	
	private static final int XX_OFFSET=100;
	private static final int XY_OFFSET=50;
	private static final int YX_OFFSET=100;
	private static final int YY_OFFSET=450;
	private static final int APPLICATION_WIDTH=1350;
	private static final int APPLICATION_HEIGHT=800;

}
