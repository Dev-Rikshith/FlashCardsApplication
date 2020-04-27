package com.flashcardbuilder.java;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class FlashCardBuilder {
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
			System.out.println("Next Button Clicked");
			
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
			System.out.println("Save Button Clicked");
			
		}
		
	}
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new FlashCardBuilder();
				
			}
		});

	}

}
