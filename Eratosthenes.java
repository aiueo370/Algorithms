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

public class Eratosthenes extends JPanel {
	
	private static int end;	// ここまで調べる
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		loadData();
		
		String text;
		int height = 50;
		int h = 1;
		int hLine = h;
		int rSum = 20;
		int f[] = new int[end+1];// 0～endまでの配列
		
		g.setColor(Color.RED);
		g.setFont(new Font(Font.SERIF,Font.BOLD,30));
		g.drawString("エラトステネスのふるい", 50,height);
		
		
		g.setColor(Color.blue);
		g.setFont(new Font(Font.SERIF,Font.PLAIN,20));
		height += 30;
		
		// あとは表示するだけ
		int n = 0; // 改行の制御用
		
		g.drawString("素数一覧", 50,height);
		
		//数字の表示
		g.setColor(Color.blue);
		g.setFont(new Font(Font.SERIF,Font.PLAIN,10));
		for(int i=0; i <= end; i++){ // 配列すべてをチェックする。
			
			text = i + ",";
			g.drawString(text, 50 + n * 30,height + 15*h);
			//System.out.printf("%4d,",i);
			n++;
			if(n >= rSum) { // 10個表示したら
				n = 0; // n を０に戻す
				h++;
			}
		}
		
		g.setColor(Color.black);
		n = 0;
		
		int last = (int)(Math.sqrt((double)end)); //候補の最後を計算しておく
		
		f[0] = f[1] = 1; // 0 と 1 は定義により素数ではない。
		for(int j = 0; j <= 1; j++){
			n = j % rSum;
			hLine = j / rSum;
			g.drawLine(50 + n * 30 , height + 15*hLine + 5, 50 + n * 30 + 10, height + 15*hLine + 15);
		}
		
		for(int i = 2; i <= last; i++){// 素数でない数をチェックする
			if(f[i] == 0){
				for(int j = i*2; j <= end; j += i){
					f[j]++;
					n = j % rSum;
					hLine = j / rSum;
					g.drawLine(50 + n * 30 , height + 15*hLine + 5, 50 + n * 30 + 10, height + 15*hLine + 15);
				}
			}
		}
		
		n = 0;
		
		g.setColor(Color.blue);
		g.setFont(new Font(Font.SERIF,Font.PLAIN,15));
		height += 30;
		
		g.drawString("素数：", 50,height + 15*h);
		
		height += 20;
		//素数の表示
		for(int i=0; i <= end; i++){ // 配列すべてをチェックする。
			if(f[i] == 0){
				text = i + ",";
				g.drawString(text, 50 + n * 30,height + 15*h);
				//System.out.printf("%4d,",i);
				n++;
				if(n >= rSum) { // 10個表示したら
					n = 0; // n を０に戻す
					h++;
				}
			}
		}
	}
	
	public static void loadData(){
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("Eratosthenes.csv")));
			String line = reader.readLine();
			end = Integer.valueOf(line);
		}
		catch(FileNotFoundException e){
			throw new RuntimeException(e);
		}
		catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		
		jf.getContentPane().add(new Eratosthenes());
		
		jf.setTitle("エラトステネスのふるい");
		
		jf.setSize(1000, 1000);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}