package com.flashcardbuilder.java;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

public class FlashCardBuilder {
		//Instance Variables
		private JTextArea question;
		private JTextArea answer;
		private ArrayList<FlashCards> myCards = new ArrayList<>();
		private JFrame frame;
	
	
	public FlashCardBuilder(){
		//Initialize the frame
		frame = new JFrame("Flash Cards Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Create a JPanel and initialize it
		JPanel mainPane = new JPanel();
		

		//Create font and set it to question and answer JTextFields
		Font greatFont = new Font("Helvetica Neue", Font.BOLD, 21);
		question = new JTextArea(6, 20);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setFont(greatFont);
		
		answer = new JTextArea(6, 20);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(greatFont);
		
		//Create ScrollBar for question and answers text areas
		JScrollPane qJScrollPane = new JScrollPane(question);
		qJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		qJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JScrollPane aJScrollPane = new JScrollPane(answer);
		aJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		aJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		//Buttons and labels
		JButton nextButton = new JButton("Next Card");
		JLabel qJLabel = new JLabel("Question");
		JLabel aJLabel = new JLabel("Answer");
		
		//Initialize the menu bar and its items
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		
		//Club all the menu items
		menu.add(newMenuItem);
		menu.add(saveMenuItem);
		menuBar.add(menu);
		
		//Add Components to the pane
		mainPane.add(qJLabel);
		mainPane.add(qJScrollPane);
		mainPane.add(aJLabel);
		mainPane.add(aJScrollPane);
		mainPane.add(nextButton);
		
		//Initialize the frame
		frame.getContentPane().add(BorderLayout.CENTER, mainPane);
		frame.setSize(500, 450);
		frame.setVisible(true);
		frame.setJMenuBar(menuBar);
		
		//Action Listeners
		nextButton.addActionListener(new nextButtonListener());
		newMenuItem.addActionListener(new newMenuItemListener());
		saveMenuItem.addActionListener(new saveMenuItemListener());
		
	}
	
	public class nextButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//Data saved to ArrayList and cleared
			FlashCards card = new FlashCards(question.getText(), answer.getText());
			myCards.add(card);
			clearCard();
			
		}
	}
	
	
	public class newMenuItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("New Button Clicked");
		}
		
	}
	
	public class saveMenuItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//Add the card to ArrayList
			FlashCards card = new FlashCards(question.getText(), answer.getText());
			myCards.add(card);
			
			//Choose filename and write the ArrayList to the file
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(frame);
			saveFile(fileSave.getSelectedFile());
			
		}
		
	}
	
	private void clearCard() {
		//function clears text and focus on the question
		question.setText("");
		answer.setText("");
		question.requestFocus();
	}
	
	private void saveFile(File selectedFile) {
		//The file writing is error prone so we use try-catch block
		try {
			//Initialize a writer
			BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
			//Initialize a Iterator and write to the above selected file
			Iterator<FlashCards> cardIterator = myCards.iterator();
			while(cardIterator.hasNext()) {
				FlashCards card = (FlashCards)cardIterator.next();
				writer.write(card.getQuestion() + "/");
				writer.write(card.getAnswer() + "\n");
				
			}
			writer.close();
			
			
		} catch (Exception e) {
			//Print the error message and the StackTrace in case of any exception occurence 
			System.out.println("Could not write to file");
			e.printStackTrace();
		}
		
		
	}
	

	public static void main(String[] args) {
		//invokeLater method updates or performs any task on Event dispatcher thread asynchronously.
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new FlashCardBuilder();
				
			}
		});

	}

}
