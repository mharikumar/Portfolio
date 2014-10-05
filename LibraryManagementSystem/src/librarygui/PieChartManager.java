package librarygui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
/** 
 * class for creating the pie chart for admin options 
 * total items category wise and checkout and non checkout items
 * @param none
 */	

public class PieChartManager extends JPanel{
	int fictionbooks;
	int nonfictionbooks;
	int videos;
	int totalitems;
	int checkedout;
	
	PieChartManager(int fictionbooks, int nonfictionbooks, int videos, int checkedout) {
		this.fictionbooks = fictionbooks;
		this.nonfictionbooks = nonfictionbooks;
		this.videos = videos;
		this.checkedout = checkedout;
		this.totalitems = fictionbooks + nonfictionbooks + videos;
	}
	
	public void paintComponent ( Graphics g )
	  { 
		super.paintComponents(g);
				
		double percentagefictionbooks = 0.0;
		double percentagenonfictionbooks = 0.0;
		double percentagevideo = 0.0;
		int startangle = 0;
		int arcangle = 0;
		int x = 5;
		int y = 5;
		percentagefictionbooks = (double)fictionbooks/totalitems;
		arcangle = ( int ) Math.round( percentagefictionbooks * 360 );
		g.setColor(Color.BLUE);
	    g.fillArc( x, y, 100, 100, startangle, arcangle);
		
		startangle += arcangle;
		percentagenonfictionbooks = (double)nonfictionbooks/totalitems;
		int arcangle1 = ( int ) Math.round( percentagenonfictionbooks * 360 );
		g.setColor(Color.RED);
	    g.fillArc( x, y, 100, 100, startangle, arcangle1);
	    
	    startangle += arcangle1;
	    percentagevideo = (double)videos/totalitems;
	    int arcangle2 =  (int ) Math.round( percentagevideo * 360 );
	    g.setColor(Color.GREEN);
	    g.fillArc( x, y, 100, 100, startangle, arcangle2);
	    
	    //info for fictionbook
	    g.setColor( Color.BLUE );
        g.fillRect( 125, 100, 10, 10 );
        g.setColor( Color.black );
        g.drawString( "Fiction Books", 140, 110 );
        
        //info for non fictionbook
        g.setColor( Color.RED );
        g.fillRect( 125, 115, 10, 10 );
        g.setColor( Color.black );
        g.drawString( "Non Fiction Books", 140, 125 );
        
        //info for video
        g.setColor( Color.GREEN );
        g.fillRect( 125, 130, 10, 10 );
        g.setColor( Color.black );
        g.drawString( "Videos", 140, 140 );
        g.setColor(Color.black);
        g.drawLine(290, 0, 290, 400);
        
        
        double percentagecheckedoutitems = 0.0;
		double percentagenoncheckedoutitems = 0.0;
		int startanglecheckedout = 0;
		int arcanglecheckedout = 0;
		int xdim = 300;
		int ydim = 5;
		percentagecheckedoutitems = (double)checkedout/totalitems;
		arcanglecheckedout = ( int ) Math.round( percentagecheckedoutitems * 360 );
		
		g.setColor(Color.CYAN);
	    g.fillArc( xdim, ydim, 100, 100, startanglecheckedout, arcanglecheckedout);
	    
	    startanglecheckedout += arcanglecheckedout;
	    percentagenoncheckedoutitems = 1 - (double)checkedout/totalitems;
		int arcanglenoncheckedout = ( int ) Math.round( percentagenoncheckedoutitems * 360 );
		g.setColor(Color.MAGENTA);
	    g.fillArc( xdim, ydim , 100, 100, startanglecheckedout, arcanglenoncheckedout);
	    
	    g.setColor( Color.CYAN );
        g.fillRect( 400, 110, 10, 10 );
        g.setColor( Color.black );
        g.drawString( "Checkedout Items", 415, 120 );
        
        g.setColor( Color.MAGENTA );
        g.fillRect( 400, 125, 10, 10 );
        g.setColor( Color.black );
        g.drawString( "Non Checkedout Items", 415, 135 );

        
	    
    
	  }
	
	

}
