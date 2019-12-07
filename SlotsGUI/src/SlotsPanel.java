import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;

public class SlotsPanel extends JPanel{
	private JButton spin;
	private JButton clear;
	private JButton restart;
	private JLabel walletLabel;
	private JLabel spinLabel;
	private JLabel betLabel;
	private JLabel rules1;
	private JLabel rules2;
	private JLabel rules3;
	private JLabel rules4;
	private JLabel rules5;
	private JLabel rules6;
	private JLabel slot1;
	private JLabel slot2;
	private JLabel slot3;
	private JLabel background;
	private JLabel rulesLabel;
	private JTextField wallet;
	private JTextField spins;
	private JTextField bet;
	private JTextArea historyLog;
	private JScrollPane scrollable;
	private Font myFont = new Font("TimesRoman", Font.ITALIC, 25);
	private Font slotFont = new Font("Serif", Font.ITALIC, 350);
	private int winnings;
	private int game = 0;
	private int money;
	private int betAmount;
	private int spinsLeft;
	Random rand = new Random();
	Timer timer;

	//adding gui components to the panel
	SlotsPanel(){
		this.setLayout(null);
		spin = new JButton("Spin");
		clear = new JButton("Clear");
		restart = new JButton("Restart");
		walletLabel = new JLabel("Wallet: ");
		spinLabel = new JLabel("Spins: ");
		wallet = new JTextField("2500");
		spins = new JTextField("10");
		betLabel = new JLabel("Bet Amount: ");
		bet = new JTextField("");
		historyLog = new JTextArea("");
		rules1 = new JLabel("- Max bet: 5000");
		rules2 = new JLabel("- Min bet: 100");
		rules3 = new JLabel("- Game over when wallet is less "
				+ "than 100 or spins is 0.");
		rules4 = new JLabel("- If 2 in a row, you win bet * 2.");
		rules5 = new JLabel("- If 3 in a row, you win bet * 5.");
		rules6 = new JLabel("- If 3 7's in a row, you win bet * 10.");
		rulesLabel = new JLabel("Rules:");
		slot1 = new JLabel("7");
		slot2 = new JLabel("7");
		slot3 = new JLabel("7");
		background = new JLabel("");		

		//buttons
		spin.setBounds(880, 820, 65, 35);
		clear.setBounds(970, 820, 65, 35);
		restart.setBounds(740, 820, 90, 35);
		spin.setOpaque(false);
		spin.setContentAreaFilled(false);
		spin.setBorderPainted(false);
		clear.setOpaque(false);
		clear.setContentAreaFilled(false);
		clear.setBorderPainted(false);
		restart.setOpaque(false);
		restart.setContentAreaFilled(false);
		restart.setBorderPainted(false);
		
		//counter
		walletLabel.setBounds(860, 0, 95, 30);
		walletLabel.setFont(myFont);
		wallet.setBounds(950, 5, 95, 25);
		wallet.setFont(myFont);
		wallet.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		spinLabel.setBounds(860, 40, 95, 30);
		spinLabel.setFont(myFont);
		spins.setBounds(950, 45, 95, 25);
		spins.setFont(myFont);
		spins.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		betLabel.setBounds(740, 780, 155, 40);
		betLabel.setFont(myFont);
		bet.setBounds(880, 790, 160, 30);
		bet.setFont(myFont);
		bet.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		//log
		historyLog.setFont(myFont);
		historyLog.setOpaque(false);
		scrollable = new JScrollPane(historyLog);
		scrollable.setBounds(159, 634, 470, 210);
		historyLog.setLineWrap(true);
		historyLog.setWrapStyleWord(true);
		scrollable.setBorder(null);
		scrollable.getViewport().setOpaque(false);
		scrollable.setOpaque(false);
		
		//rules
		rulesLabel.setFont(myFont);
		rulesLabel.setBounds(860, 620, 130, 35);
		rules1.setBounds(740, 650, 100, 20);
		rules2.setBounds(740, 670, 100, 20);
		rules3.setBounds(740, 690, 350, 20);
		rules4.setBounds(740, 710, 350, 20);
		rules5.setBounds(740, 730, 350, 20);
		rules6.setBounds(740, 750, 350, 20);
		
		//slots
		slot1.setBounds(200, 200, 200, 300);
		slot1.setFont(slotFont);
		slot2.setBounds(500, 200, 200, 300);
		slot2.setFont(slotFont);
		slot3.setBounds(800, 200, 200, 300);
		slot3.setFont(slotFont);

		//visuals
		Image img = new ImageIcon(this.getClass().getResource("img/pic.jpg")).getImage();
		background.setIcon(new ImageIcon(img));
		background.setBounds(0, 0, 1200, 900);
		Image butImg = new ImageIcon(this.getClass().getResource("img/but.png")).getImage();
		Image butImg2 = new ImageIcon(this.getClass().getResource("img/but2.png")).getImage();
		spin.setIcon(new ImageIcon(butImg));
		clear.setIcon(new ImageIcon(butImg));
		restart.setIcon(new ImageIcon(butImg2));
		spin.setHorizontalTextPosition(AbstractButton.CENTER);
		clear.setHorizontalTextPosition(AbstractButton.CENTER);
		restart.setHorizontalTextPosition(AbstractButton.CENTER);
		
		add(spin);
		add(clear);
		add(restart);
		add(walletLabel);
		add(wallet);
		add(spins);
		add(spinLabel);
		add(bet);
		add(betLabel);	
		add(scrollable);
		add(rulesLabel);
		add(rules1);
		add(rules2);
		add(rules3);
		add(rules4);
		add(rules5);
		add(rules6);
		add(slot1);
		add(slot2);
		add(slot3);		
		add(background);

		spins.setEnabled(false);
		spins.setDisabledTextColor(Color.black);
		wallet.setEnabled(false);
		wallet.setDisabledTextColor(Color.black);
		historyLog.setEnabled(false);
		historyLog.setDisabledTextColor(Color.black);	
		
		
		play p = new play();
		clear.addActionListener(p);
		spin.addActionListener(p);
		bet.addActionListener(p);
		wallet.addActionListener(p);
		restart.addActionListener(p);
		spin.setDefaultCapable(true);
		
		keys k = new keys();
		bet.addKeyListener(k);	
	}
	
	private class play implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//if clear is clicked set the bet amount to 0
			if(e.getActionCommand().equals("Clear")) {
				bet.setText("0");
			}
			//if restart is clicked game restarts
			if(e.getActionCommand().equals("Restart")) {
				game = 0;
				money = 2500;
				spinsLeft = 10;
				historyLog.setText("");
				wallet.setText(Integer.toString(money));
				spins.setText(Integer.toString(spinsLeft));
				spin.setEnabled(true);
			}
			//if spin gets clicked
			if(e.getActionCommand().equals("Spin")) {
				spin.setEnabled(false);			//disables the spin button
				money = Integer.parseInt(wallet.getText());
				spinsLeft = Integer.parseInt(spins.getText());
					
				//if user didn't input a bet amount
				if(bet.getText().equals("") || bet.getText().equals("0")){
					historyLog.setText(historyLog.getText() +
							"Please input a bet amount.\n");
					spin.setEnabled(true);	//enables the spin button
				}
				//if user did enter a bet amount
				else{
					betAmount = Integer.parseInt(bet.getText());
					//if bet is greater than 100 or less than 5000
					if(betAmount >= 100 && betAmount <= 5000) {
						//as long as they have enough money and spins
						if((money - betAmount) >= 0 && spinsLeft != 0) {
							money -= betAmount;
							spinsLeft--;
							timeClass tc = new timeClass(20);
							timer = new Timer(50, tc);
							timer.start();	
							wallet.setText(Integer.toString(money));
							spins.setText(Integer.toString(spinsLeft));
						}
						//if they dont have enough money, enter bet again
						else if((money - betAmount) < 100){
							historyLog.setText(historyLog.getText() +
									"You don't have that much money!\n");
							spin.setEnabled(true);
						}
					}
					//if bet is greater than 5000 or less than 100
					else {
						historyLog.setText(historyLog.getText() +
								"Bet amount cannot be more than 5000 and"
								+ " cannot be less than 100.\n");
						spin.setEnabled(true);
					}
				}	
			}
		}
	}	
	//listens the key clicks
	private class keys implements KeyListener{
		
		@Override
		public void keyTyped(KeyEvent e) {
			char c= e.getKeyChar();
			if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || 
				c == KeyEvent.VK_DELETE)){
				//if the input is not a number, dont show up
					e.consume();
				}
			if(bet.getText().length() > 3) {
				//if the input length is greater than 4, dont show up
				e.consume();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {	
			if (e.getKeyCode()==KeyEvent.VK_ENTER) {
				//enter button becomes the spin button too
	               spin.doClick();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		};			
	}
	//random number animation
	private class timeClass implements ActionListener {
		int count;
		
		private timeClass(int count) {
			this.count = count;
		}
		
		public void actionPerformed(ActionEvent e) {
			count--;			
			if(count >= 1) {
				//display random numbers for a second
				rand = new Random();
				String randInt = Integer.toString(rand.nextInt(9));
				slot1.setText(randInt);
				randInt = Integer.toString(rand.nextInt(9));
				slot2.setText(randInt);
				randInt = Integer.toString(rand.nextInt(9));
				slot3.setText(randInt);	
			}
			else {
				timer.stop();
				game++;
				historyLog.setText(historyLog.getText() + "\t--- GAME " + 
					game + " ---\n" + "Your numbers are: " + 
					slot1.getText() + ", " + slot2.getText() + ", " + 
					slot3.getText() + "\n");
				int slot1Num = Integer.parseInt(slot1.getText());
				int slot2Num = Integer.parseInt(slot2.getText());
				int slot3Num = Integer.parseInt(slot3.getText());
				spin.setEnabled(true);
				
				//checks if they won
				if(checkSlots(slot1Num, slot2Num, slot3Num, money, betAmount)) {
					if(slot1Num == 7 && slot2Num == 7 && slot3Num == 7) {
						historyLog.setText(historyLog.getText() +
								"You won the jackpot of: $"+ winnings +"!\n"
								+"Your bet was: $" + betAmount +"\n");
						money += winnings;
					}
					else {
						historyLog.setText(historyLog.getText() +
							"You won: $"+ winnings +"!\n"
							+"Your bet was: $" + betAmount +"\n");
							money += winnings;						
					}
				}
				else {
					historyLog.setText(historyLog.getText() +
							"Sorry, you lost this round.\n");
				}
				//if no spin or money left, game over
				if(spinsLeft == 0 || money < 100) {
					if(spinsLeft == 0 && money < 100) {
						historyLog.setText(historyLog.getText() +
							"You have run out of money and spins! \nGame Over!\n");
							spin.setEnabled(false);
					}
					else if(spinsLeft == 0) {
						historyLog.setText(historyLog.getText() +
						"You have run out of spins! \nGame Over!\n");
						spin.setEnabled(false);
					}
					else if(money < 100) {
						historyLog.setText(historyLog.getText() +
						"You have run out of money! \nGame Over!\n");
						spin.setEnabled(false);
					}			
				}	
				wallet.setText(Integer.toString(money));	
				spins.setText(Integer.toString(spinsLeft));			
			}		
		}
	}
	
	private boolean checkSlots(int slot1Num, int slot2Num, int slot3Num, int wallet, int betAmount) {
		//if 7 7 7, winnings = bet * 10
		if(slot1Num == 7 && slot2Num == 7 && slot3Num == 7) {
			winnings = betAmount * 10;
			wallet += winnings;			
			return true;
		}
		else if(slot1Num == slot2Num && slot2Num == slot3Num) {
			winnings = betAmount * 5;
			wallet += winnings;			
			return true;
		}
		else if(slot1Num == slot2Num || slot2Num == slot3Num) {
			winnings = betAmount * 2;
			wallet += winnings;			
			return true;
		}
		else {
			return false;
		}			
	}
}