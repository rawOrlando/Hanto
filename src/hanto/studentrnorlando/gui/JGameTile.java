package hanto.studentrnorlando.gui;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;

public class JGameTile extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int xOffset = 85;
	private int XYOffset = 44;
	private int yOffset = 73;
	
	private double xOffsetPoint = .85;
	private double XYOffsetPoint = .44;
	private double yOffsetPoint = .73;
	
	private int tileDemension = 100;
	
	private String defaultImage = "Option";
	
	private ArrayList<JButton> buttons = null;
	
	private HantoCoordinate coordinate;
	private HantoPiece piece;

	public JGameTile(HantoPiece piece, HantoCoordinate coordinate)
	{
		this.coordinate = coordinate;
		this.piece = piece;
		setImage();
	}
	
	private String getImageFilePath()
	{
		return "res/" + getImageName() + ".png";
	}
	
	protected String getImageName()
	{
		if(piece == null)
		{
			return defaultImage;
		}
		return piece.getType().getPrintableName() + HantoPlayerColor.getPrintableName(piece.getColor());
	}
	
	private void setImage()
	{
		String fileName = getImageFilePath();
		//Figure out the Image
		Image img = null;
		try {
			img = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block !!!
			//e.printStackTrace();
			//This should never happen... 
			return;
		}
		
		Image smallImg  = img.getScaledInstance(tileDemension, tileDemension, Image.SCALE_DEFAULT);
		setIcon(new ImageIcon(smallImg));
	}
	
	@Override
	public void setBounds(int x, int y, int hieght, int width)
	{
		changeBounds(Math.min(width, hieght));
		setImage();
		super.setBounds(x + getXPoint(), y + getYPoint(), tileDemension, tileDemension);
	}
	
	// DO math tofigure out if click is in hex( rectangle and triangles)
	// make this image button 
	//Lazy for now
	public List<JButton> getButtons()
	{
		if (buttons == null)
		{
			setupButtons();
		}
		return buttons;
	}
	
	private void setupButtons()
	{
		buttons = new ArrayList<JButton>();
		JButton button = new JButton();
		
		Rectangle rec = this.getBounds();
		double demension = .55;
		double demensionX = .87;
		double demensionY = .80;
		
		button.setBounds((int)(rec.getX() + (rec.getWidth()*demension/8)),
				(int)(rec.getY() + (rec.getHeight()*demension/2)),
				(int)(rec.getWidth()*demensionX),
				(int)(rec.getHeight()*demension));
		
		
		demension = .44;
		demensionX = .55;
		
		JButton button2 = new JButton();
		
		button2.setBounds((int)(rec.getX() + (rec.getWidth()*demension/2)),
				(int)(rec.getY() + (rec.getHeight()*demension/4)),
				(int)(rec.getWidth()*demensionX),
				(int)(rec.getHeight()*demensionY));
		
		buttons.add(button);
		buttons.add(button2);
	}
	
	public HantoCoordinate getCordinate()
	{
		return coordinate;
	}
	
	public HantoPiece getPiece()
	{
		return piece;
	}
	
	private void changeBounds(int width)
	{
		//xOffset = xOffset  * floor(width/tileDemension);
		tileDemension = width;
	}
	
	private int getXOffSet()
	{
		return (int) (xOffsetPoint * tileDemension);
	}
	
	private int getYOffSet()
	{
		return (int) (yOffsetPoint * tileDemension);
	}
	
	private int getXYOffSet()
	{
		return (int) (XYOffsetPoint * tileDemension);
	}
	
	public int getXPoint()
	{
		return coordinate.getX() *getXOffSet() + coordinate.getY() * getXYOffSet();
	}
	
	public int getYPoint()
	{
		return (- coordinate.getY() *getYOffSet());
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(other == null)
		{
			return false;
		}
		if(!other.getClass().equals(this.getClass()))
		{
			return false;
		}
		if(((JGameTile)other).getPiece() == null ^ piece == null)
		{
			return false;
		}
		if(!((JGameTile)other).getCordinate().equals(getCordinate()))
		{
			return false;
		}
		if(piece != null && !((JGameTile)other).getPiece().equals(piece))
		{
			return false;
		}
		return true;
	}
}
