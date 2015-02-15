import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.applet.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Floodfill extends Applet implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Color[] m_Colors =
		{
			Color.blue, Color.red, Color.green, Color.yellow,
			Color.gray, Color.magenta, Color.orange, Color.cyan
		};
		int m_nUpperLeftX = 10;
		int m_nUpperLeftY = 10;
		int m_nColorWidth = 50;
		int m_nColorHeight = 50;
		int m_nLowerRightX;
		int m_nLowerRightY;
		Color m_objSelectedColor = Color.blue;
		BufferedImage m_objShape;
		MediaTracker tracker = new MediaTracker(this);
		int m_nTestShapeX = 100;
		int m_nTestShapeY = 100;
		
		void DrawColors( Graphics canvas )
		{
			for( int i=0; i<m_Colors.length; i++ )
			{
				canvas.setColor( m_Colors[i] );
				canvas.fillRect(m_nUpperLeftX, m_nUpperLeftY + i * m_nColorHeight, 
		m_nColorWidth, m_nColorHeight );
				canvas.setColor( Color.black );
				canvas.drawRect(m_nUpperLeftX, m_nUpperLeftY + i * m_nColorHeight, 
		m_nColorWidth, m_nColorHeight );
				m_nLowerRightX = m_nUpperLeftX + m_nColorWidth;
				m_nLowerRightY = ( i + 1 ) * m_nColorHeight;
			}
			
			
			//current color selection
			canvas.setColor( m_objSelectedColor );
			canvas.fillRect(m_nUpperLeftX + m_nColorWidth + 5, m_nUpperLeftY, 
	m_nColorWidth, m_nColorHeight );
			canvas.setColor( Color.black );
			canvas.draw3DRect(m_nUpperLeftX + m_nColorWidth + 5, m_nUpperLeftY, 
	m_nColorWidth, m_nColorHeight,true );
			canvas.drawString("Selected", m_nUpperLeftX + m_nColorWidth + 3, m_nUpperLeftY + m_nColorHeight + 13);
		}
		
		
		public void init()
		{
			addMouseListener(this);
			setSize(1020,700);

		        try 
		        {
				m_objShape = ImageIO.read(Floodfill.class.getResourceAsStream("Untitled.png"));
			} 
		        catch (IOException e1) 
		        {
			}
			tracker.addImage(m_objShape, 100);
			try 
			{
				tracker.waitForAll();
			} 
			catch (InterruptedException e) 
			{
			}
				
		}
		
		void DrawTestShape( Graphics canvas )
		{
			canvas.drawImage(m_objShape, 100, 100, this);
		}
		
		public void paint( Graphics canvas )
		{
			DrawColors( canvas );
			DrawTestShape( canvas );
		}
		
		void SetPixel( int x, int y, Graphics canvas )
		{
			canvas.drawLine(x, y, x, y);
		}
		void SetPixel( int x, int y, int nColor )
		{
			m_objShape.setRGB(x, y, nColor);
		}
		
		public int GetPixel( int x, int y )
		{
			return( m_objShape.getRGB(x, y) );
		}
		
		public boolean CheckBounds(int x, int y)
		{
			if(x >= m_objShape.getWidth() - 1 || x < 0)
				return false;
			if(y >= m_objShape.getHeight() - 1 || y < 0)
				return false;
			return true;
		}
		
/*		Every time you call DoFloodFill, you iterate upwards 
 * 		until you find a boundary (not coloring any pixels). 

		Then you have two flags, left and right. 
		The left flag indicates that you've found a column to the left
		to fill and haven't found a boundary yet that blocks that column. 
		Same thing on the right.

		So now you're at the top of column you clicked on. 
		You check the left pixel, and if it's a pixel that's the color 
		you need to fill and the left flag isn't true, then add that pixel 
		to a stack (in my case an array since we can't use stacks) and set 
		the left flag to true. So now you've found another column to recurse on. 
		Do the same with the right. 
		
		If either pixel is a boundary, then set its respective flag to false, 
		indicating that there could be another column below that needs filling.

		Continue this process, moving downward one pixel at a time, 
		coloring them as you go, putting pixels on the stack(array) 
		that need to be recursed on. Stop when you reach a boundary. 
		Then do recursive calls on all the pixels in the stack.*/
		
		public void DoFloodFill(int x, int y, int n_initialColor)
		{
			//bounds
			int x_bound = m_objShape.getWidth();
			int y_bound = m_objShape.getHeight();
			
			if(GetPixel(x,y) == m_objSelectedColor.getRGB())
				return;
			
			//travel up till we reach a boundary
			while(y > 0)
			{
				if(GetPixel(x,y) != n_initialColor)
				{
					y++;
					break;
				}
				y--;
			}
			
			//left and right flags
			boolean left = false, right = false;
			//stores pixels to recurse on
			//would be much easier if I could use a real stack
			int[] x_co = new int[1000];
			int[] y_co = new int[1000];
			//painfully initialize it
			for(int i = 0; i < x_co.length; i++)
			{
				x_co[i] = -1;
				y_co[i] = -1;
			}
			
			//travel down until we reach a boundary, coloring pixels as we go
			//check for unfilled columns and boundary pixels on each side
			for(int i = 0; y < y_bound; i+=2, y++)
			{
				//check if we're on a boundary
				if(GetPixel(x,y) != n_initialColor)
					break;
				
				//color pixel
				SetPixel(x, y, m_objSelectedColor.getRGB());
				
				//check if left is valid pixel first
				if(x > 0 && x < x_bound )
				{
					//check if left is unfilled and if the left flag is not set
					//if so, add the pixel to the array and set left to true
					if(GetPixel(x-1, y) == n_initialColor && left == false)
					{
						x_co[i] = x-1;
						y_co[i] = y;
						left = true;
					}
					//check if left is boundary and left flag is set
					if(GetPixel(x-1, y) != n_initialColor && left == true)
						left = false;
				}
				//check if right is valid pixel
				if(x >= 0 && x < x_bound - 1)
				{
					//check if right is unfilled and if the left flag is not set
					//if so, add the pixel to the array and set left to true
					if(GetPixel(x+1, y) == n_initialColor && right == false)
					{
						x_co[i+1] = x+1;
						y_co[i+1] = y;
						right = true;
					}
					//check if right is boundary and left flag is set
					if(GetPixel(x+1, y) != n_initialColor && right == true)
						right = false;
				}
			}
	
			//recurse on pixels in array
			for(int i = 0; i < x_co.length; i++)
			{
				if(x_co[i] != -1 && y_co[i] != -1)
					DoFloodFill(x_co[i], y_co[i], n_initialColor);
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent ms) {
		
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseClicked(MouseEvent ms) {
			if( ms.getX() >= m_nUpperLeftX &&
					ms.getY() >= m_nUpperLeftY &&
					ms.getX() < m_nLowerRightX &&
					ms.getY() < m_nLowerRightY )
				{
					int nColorIndex = ( ms.getY() - m_nUpperLeftY ) / m_nColorHeight;
					if( nColorIndex >= 0 && nColorIndex <= 7 )
					{
						m_objSelectedColor = m_Colors[nColorIndex];
						repaint();
					}
				}
			else if( ms.getX() >= m_nTestShapeX &&
					ms.getY()>=m_nTestShapeY &&
					ms.getX() < m_nTestShapeX + m_objShape.getWidth() &&
					ms.getY() < m_nTestShapeY + m_objShape.getHeight())
			{
					DoFloodFill( ms.getX() - 100, ms.getY() - 100,
							     GetPixel(ms.getX() - 100, ms.getY() - 100));
					repaint();
			}
			
		}
		
		
}
