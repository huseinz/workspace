import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.imageio.ImageIO;


public class EightQueens extends Applet implements MouseListener,
												   MouseMotionListener,
												   Runnable,
												   ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Image m_imgQueen;
	MediaTracker tracker = new MediaTracker(this);
	
	static final int NUMROWS = 8;
	static final int NUMCOLS = 8;
	static final int SQUAREWIDTH = 50;
	static final int SQUAREHEIGHT = 50;
	static final int BOARDLEFT = 50;
	static final int BOARDTOP = 50;
	int m_nBoard[][] = new int[8][8];
	boolean m_bClash = false;
	
	Button m_objButton = new Button("Solve");
	boolean m_bSolving = false;

	private String m_strStatus = "EightQueens";

	private int nCol;

	private int nRow;

	private Thread m_objThread;

	private Image m_objOffscreen;

	private boolean clearboard = false;
	
	
	public void init()
	{
		addMouseListener(this);
		setSize(1020, 700);
		

		add(m_objButton);
		m_objButton.setEnabled(true);
		m_objButton.addActionListener(this);
		
		m_objOffscreen = createImage(this.getSize().width,this.getSize().height);;
		try
		{
			m_imgQueen = ImageIO.read(EightQueens.class.getResourceAsStream("queen.png"));
		}
		catch (IOException el)
		{			
		}
		tracker.addImage(m_imgQueen, 100);
		try
		{
			tracker.waitForAll();
		}
		catch (InterruptedException e)
		{
		}
	}

	public void paint (Graphics canvas)
	{
		m_bClash = false;
		DrawSquares( canvas );
		canvas.setColor(Color.RED);
		CheckColumns( canvas );
		CheckRows( canvas );
		CheckDiagonal1( canvas );
		CheckDiagonal2( canvas );
		canvas.setColor(Color.BLUE);
		canvas.drawString(m_strStatus , 
						  BOARDLEFT, BOARDTOP + SQUAREHEIGHT * 8 + 20);
	}
	
	void DrawSquares( Graphics canvas )
	{
		canvas.setColor(Color.BLACK);
		for( int nRow=0; nRow<NUMROWS; nRow++ )
		{
			for( int nCol=0; nCol<NUMCOLS; nCol++ )
			{
				canvas.drawRect( BOARDLEFT + nCol * SQUAREWIDTH,
					BOARDTOP + nRow * SQUAREHEIGHT, SQUAREWIDTH, SQUAREHEIGHT );
					
				if( m_nBoard[nRow][nCol] != 0 )
				{
					canvas.drawImage( m_imgQueen,
						BOARDLEFT + nCol * SQUAREWIDTH + 8, BOARDTOP + nRow * SQUAREHEIGHT + 6, null );
				}
			}
		}
	}
		
	void CheckColumns( Graphics canvas )
	{
		// Check all columns
		for(  int nCol=0; nCol<NUMCOLS; nCol++ )
		{
			int nColCount = 0;
			for( int nRow=0; nRow<NUMROWS; nRow++ )
			{
				if( m_nBoard[nRow][nCol] != 0 )
				{
					nColCount++;
				}
			}
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
					BOARDTOP + ( SQUAREHEIGHT / 2 ),	
					BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
					BOARDTOP + SQUAREHEIGHT * 7 + ( SQUAREHEIGHT / 2 ) );
					
				m_bClash = true;
			}
		}
	}

	void CheckRows( Graphics canvas )
	{
		for(  int nRow=0; nRow<NUMROWS; nRow++ )
		{
			int nRowCount = 0;
			for( int nCol=0; nCol<NUMCOLS; nCol++ )
			{
				if( m_nBoard[nRow][nCol] != 0 )
				{
					nRowCount++;
				}
			}
			if( nRowCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + ( SQUAREWIDTH / 2 ),
					BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
					BOARDLEFT + 7 * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
					BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				m_bClash = true;
			}
		}
	}
		
	void CheckDiagonal1( Graphics canvas )
	{
		// Check diagonal 1
		
		for( int nRow=NUMROWS-1; nRow>=0; nRow-- )
		{
			int nCol = 0;
				
			int nThisRow = nRow;
			int nThisCol = nCol;

			int nColCount = 0;
				
			while( nThisCol < NUMCOLS &&
				nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol++;
				nThisRow++;
			}
				
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol - 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				m_bClash = true;
			}
		}

		for( int nCol=1; nCol<NUMCOLS; nCol++)
		{
			int nRow = 0;
			
			int nThisRow = nRow;
			int nThisCol = nCol;

			int nColCount = 0;
				
			while( nThisCol < NUMCOLS &&
				nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol++;
				nThisRow++;
			}
				
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol - 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				m_bClash = true;
			}
		}
	}
		
	void CheckDiagonal2( Graphics canvas )
	{
		// Check diagonal 2
			
		for( int nRow=NUMROWS-1; nRow>=0; nRow-- )
		{
			int nCol = NUMCOLS - 1;
				
			int nThisRow = nRow;
			int nThisCol = nCol;

			int nColCount = 0;
				
			while( nThisCol >= 0 &&
				nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol--;
				nThisRow++;
			}
				
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol + 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				m_bClash = true;
			}
		}

		for( int nCol=NUMCOLS-1; nCol>=0; nCol--)
		{
			int nRow = 0;
			
			int nThisRow = nRow;
			int nThisCol = nCol;

			int nColCount = 0;
				
			while( nThisCol >= 0 &&
				nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol--;
				nThisRow++;
			}
				
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol + 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				m_bClash = true;
			}
				
		}
	}


	@Override
	public void mousePressed(MouseEvent ms) {
		//clear board if it needs to
		if(clearboard){
			clearBoard();
			repaint();
			clearboard = false;
		}
		
		//calculate which row and column mouse is
		
		int x = ms.getX() - BOARDLEFT;
		int y = ms.getY() - BOARDTOP;
		
		if( nCol >= 0 && nRow >= 0 && nCol < NUMCOLS && nRow < NUMROWS && !m_bSolving)
		{
			x /= 50;
			y /= 50;
			m_nBoard[y][x] ^= 1;
			repaint();
		}
	}

	void clearBoard(){
		for(int i = 0; i < NUMROWS; i++)
			for(int j = 0; j < NUMCOLS; j++)
				m_nBoard[i][j] = 0;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		m_objThread = new Thread(this);
		m_bSolving = true;
		m_objButton.setEnabled(false);
		m_objThread.start();
	}

	@Override
	public void run() {

		try
		{
			//while(true)
			//{
				
				if(m_bSolving)
				{
					SolveIt(0);
				}
				m_bSolving = false;
				m_strStatus = "Solved. Click to clear board.";
				repaint();
				Thread.sleep(1000);
				m_objButton.setEnabled(true);
				clearboard = true;
			//}
		}
		catch(Exception e)
		{
		}
		
	}
	
	public boolean isSafe(int row, int col)
	{
		m_strStatus = "Checking if square is safe...";
		m_nBoard[row][col] = 1;
		repaint();
		Delay(10);
		if(m_bClash)
		{
			m_strStatus = "CLASH!!";
			repaint();
			m_nBoard[row][col] = 0;
			return false;
		}
		
		m_nBoard[row][col] = 0;
		return true;
	}
	//do the magic
	public boolean SolveIt(int i) {
		if(i >= NUMCOLS)
			return true;
		
		for(int j = 0; j < NUMROWS; j++)
		{
			Delay(5);
			if(isSafe(j, i)){
				m_strStatus = "Square is safe...for now";
				m_nBoard[j][i] = 1;
				
				repaint();
				if(SolveIt(i+1) == true)
					return true;
				m_nBoard[j][i] = 0;
			}
			
		}
		return false;
	}
	
	void Delay(int ms)
	{
		try
		{
			Thread.sleep(ms);
		}
		catch(InterruptedException e)
		{
			
		}
	}
	
	public void repaint()
	{
		Graphics g = m_objOffscreen.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 1020,700);
		paint(g);
		getGraphics().drawImage(m_objOffscreen, 0, 0, this);
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
