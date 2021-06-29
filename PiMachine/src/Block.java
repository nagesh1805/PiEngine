import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Block extends GRect{
	
// constructors
	public Block(double xx, double yy,double dd, double vv, long mm) {
		super(xx,yy,dd,dd);
		v=vv;
		m=mm;
		this.setFilled(true);
		
	}
	
	public Block(double xx, double yy,double dd, double vv) {
		this(xx,yy,dd,vv,1); 
		
	}
	
	public Block(double xx, double yy,double dd) {
		this(xx,yy,dd,0.0,1);
	}
	
	
	public Block(long dd) {
		this(0,0,dd,0.0,1);
	}
	
	public void Move() {
		this.move(v, 0);
	}
	
	public void setV(double vv) {
		v=vv;
	}
	
	
	public void setM(long mm) {
		m=mm;
	}
	
	public double getV() {
		return v;
	}
	
	public long getM() {
		return m;
	}
	

	
	public void setBlockSize(double dd) {
		double sz=this.getHeight();
		if (dd >sz) {
		    this.setLocation(this.getX(), this.getY()-(dd-sz));
		}
		else {
			this.setLocation(this.getX(), this.getY()-(sz-dd));
		}
		this.setSize(dd, dd);
			
	}
	
	public boolean hitBlock(Block block) {
		
		return (( block.getX() <= this.getX()+this.getWidth()));
		
	}
	
	public boolean hitWall(GRect wall) {
	    return(this.getX()<=(wall.getX()+wall.getWidth())); 
	}
	
	
	
	
	
	private double v;    // block velocity
	private long m;      // block mass
	

}
