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

public class Prime extends JPanel {
	private static int x;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		String text;
		int height = 100;
		
		g.setColor(Color.RED);
		g.setFont(new Font(Font.SERIF,Font.BOLD,30));
		g.drawString("素数を求める", 50,height);
		int maxCan = (int) Math.sqrt((double)x); // 約数の候補の最大値
		
		height += 30;
		
		text = "約数の候補の最大値 : " + maxCan;
		g.setColor(Color.RED);
		g.setFont(new Font(Font.SERIF,Font.PLAIN,20));
		g.drawString(text, 50,height);
		//System.out.printf("約数の候補の最大値 : %d\r\n",maxCan);
		
		int i; // 後で使うのでここで定義する
		for(i = 2; i <= maxCan; i++) {
			
			text = x + " mod " + i + " = " + x % i;
			g.setColor(Color.blue);
			g.setFont(new Font(Font.SERIF,Font.PLAIN,20));
			g.drawString(text, 50,height + 30*i);
			//System.out.printf("%d mod %d = %d\r\n", x,i, x % i);
			
			if(x % i == 0) break; // 割り切れたら素数では無い。
		}
		
		g.setColor(Color.blue);
		g.setFont(new Font(Font.SERIF,Font.PLAIN,30));
		if(i>maxCan){
			text = x + "は素数です"; // 最後までfor文を回った。
			g.drawString(text, 250,height + 30*i + 50);
		}
		else{
			text = x + "は素数ではありません";
			g.drawString(text, 250,height + 30*i + 50);
		}
	}
	
	 
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setTitle("素数を求める");
		
		loadData();
		
		jf.getContentPane().add(new Prime());
		
		jf.setSize(500, 800);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	
	public static void loadData(){
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("Prime.csv")));
			String line = reader.readLine();
			x = Integer.valueOf(line);
		}
		catch(FileNotFoundException e){
			throw new RuntimeException(e);
		}
		catch(IOException e){
			throw new RuntimeException(e);
		}
	}
}