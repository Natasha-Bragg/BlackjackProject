package com.skilldistillery.common.cards;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Dealing {
	public static void main(String[] args) {
	    Dealing d = new Dealing();
	    d.run();
	  }
	  
	  private void run() {
	    Deck deck = new Deck();
	    deck.shuffle();
	    
	    Scanner userInput = new Scanner(System.in);
	    System.out.print("How many cards would you like? ");
	    
	    try {
	      int numCards = userInput.nextInt();
	      if(numCards > deck.checkDeckSize()) {
	        System.out.println("Too many cards");
	      }
	      
	      List<Card> hand = new ArrayList<>(numCards);
	      int totalValue = 0;
	      for(int i = 0; i < numCards; i++) {
	        Card c = deck.dealCard();
	        totalValue += c.getValue();
	        hand.add(c);
	      }
	      printHandAndValue(hand, totalValue);
	    }
	    catch (InputMismatchException e) {
	      System.out.println("That is not a valid number of cards.");
	    }
	    finally {
	      userInput.close();
	    }
	  }
	  
	  private void printHandAndValue(List<Card> hand, int value) {
	    for (Card card : hand) {
	      System.out.println(card);
	    }
	    System.out.println("Total value: " + value);
	  }

	}
