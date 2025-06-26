import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
 
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
 
public class Euclidean extends JPanel {
	
	private static int xOri;
	private static int yOri;
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		String text;
		int height = 100;
		int h = 1;//高さ行目
		
		loadData();
		
		int x = xOri;
		int y = yOri;
		
		
		g.setColor(Color.RED);
		g.setFont(new Font(Font.SERIF,Font.BOLD,30));
		g.drawString("ユークリッドの互除法", 50,height);
		
		g.setColor(Color.blue);
		g.setFont(new Font(Font.SERIF,Font.PLAIN,20));
		height += 10;
		
		if(y > x) { int tmp=x; x = y; y = tmp;}// 常にx>yにしておく
		int r = x % y; // 余りを求める
		
		text = text = x + " mod " + y + " = " + r;
		g.setColor(Color.blue);
		g.setFont(new Font(Font.SERIF,Font.PLAIN,20));
		g.drawString(text, 50,height + 30*h);
		
		while(r>0) { // 余りが０ならばyがGDCである。
			h++;
			x = y; // x に yを
			y= r; // y に余りを入れ
			r = x % y; // 次の余りを求めて繰り返す
			text = text = x + " mod " + y + " = " + r;
			g.drawString(text, 50,height + 30*h);
		}
		text = "GDC(" + xOri + "," + yOri + ") = " + x;
		g.setFont(new Font(Font.SERIF,Font.PLAIN,30));
		g.drawString(text, 200, height + 30*h + 50);
	}
	
	 
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		
		jf.getContentPane().add(new Euclidean());
		
		jf.setTitle("ユークリッドの互除法");
		
		jf.setSize(500, 1000);
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jf.setVisible(true);
	}
	
	public static void loadData(){
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("Euclidiean.csv")));
				String line = reader.readLine();
				String[] data = line.split(",");
				xOri = Integer.valueOf(data[0]);
				yOri = Integer.valueOf(data[1]);
			}
		catch(FileNotFoundException e){
			throw new RuntimeException(e);
		}
		catch(IOException e){
			throw new RuntimeException(e);
		}
	}
}