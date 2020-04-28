package com.flashcardbuilder.java;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.swing.*;

public class FlashCardPlayer {
	//Instance Variables
	private JTextArea display;
	private JTextArea question;
	private FlashCards currentCard;
	private ArrayList<FlashCards> cardList;
	private Iterator<FlashCards> cardIterator;
	private JButton showAnswer;
	private int currentCardIndex;
	private boolean isShowAnswer;
	private JFrame frame;
	
	public FlashCardPlayer() {
		
		//Create the Frame and Panel
		frame =  new JFrame("Flash Card Player");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPane = new JPanel();
		
		//Font and JTextArea settings
		Font mFont = new Font("Helvetica Neue", Font.BOLD, 21);
		display = new JTextArea(10, 20);
		display.setFont(mFont);
		
		//Create Buttons
		showAnswer = new JButton("Show Answer");
		
		//Create ScrollBar for text areas
		JScrollPane dScrollPane = new JScrollPane(display);
		dScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		//Add components to the pane
		mainPane.add(dScrollPane);
		mainPane.add(showAnswer);
		
		//Initialize the menu settings
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem loadMenuItem = new JMenuItem("Load Cards");
		
		//Club all the components
		menu.add(loadMenuItem);
		menuBar.add(menu);
		
		//Initialize the frame
		frame.getContentPane().add(BorderLayout.CENTER, mainPane);
		frame.setJMenuBar(menuBar);
		frame.setSize(600, 500);
		frame.setVisible(true);
		
		
		//Action Listeners for the components
		showAnswer.addActionListener(new ShowAnswerListener());
		loadMenuItem.addActionListener(new LoadCardsListener());
		
	}
	
	public class ShowAnswerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//Checks for next question if none grays out the show answer button
			if(isShowAnswer) {
				display.setText(currentCard.getAnswer());
				showAnswer.setText("Next Question");
				isShowAnswer = false;
			}else {
				if(cardIterator.hasNext()) {
					showNextCard();
				}else {
					display.setText("That was the last Question");
					showAnswer.setEnabled(false);
				}
			}
		}
		
	}
	
	public class LoadCardsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//Chooses the a file and loads it into the frame
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(frame);
			loadFile(fileChooser.getSelectedFile());
		}

	}
	
	private void loadFile(File selectedFile) {
		
		//Reads the file from the local system or else throws exception
		cardList = new ArrayList<FlashCards>();
		try {			
			BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
			String line = null;
			while((line = reader.readLine()) != null) {
				makeCard(line);	
			}
		} catch (Exception e) {
			System.out.println("Could not read file");
			e.printStackTrace();
		}
		
		cardIterator = cardList.iterator();
		showNextCard();
	}
	
	private void makeCard(String line) {
		//Function to Create the cards and add them into the ArrayList
		StringTokenizer result = new StringTokenizer(line, "/");
		if(result.hasMoreTokens()) {
			FlashCards card = new FlashCards(result.nextToken(), result.nextToken());
			cardList.add(card);
		}
		
	}
	
	private void showNextCard() {
		//Displays the card details in the frame's text field
		currentCard = (FlashCards)cardIterator.next();
		display.setText(currentCard.getQuestion());
		showAnswer.setText("Show Answer");
		isShowAnswer = true;
	}



	public static void main(String[] args) {
		//invokeLater method updates or performs any task on Event dispatcher thread asynchronously.
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new FlashCardPlayer();
				
			}
		});
	}

}
