package com.skilldistillery.blackjack;

import java.util.Scanner;

import com.skilldistillery.common.cards.Card;
import com.skilldistillery.common.cards.Deck;

public class BlackjackGame {
	public static void main(String[] args) {
		String nickname;
		Scanner scanner = new Scanner(System.in);
		Boolean playOn;
		String input;
		Card holdCard;

		System.out.println("Please insert your user name:");
		nickname = scanner.nextLine();

		System.out.println("Would you like to display the total of the cards? (y/n)");
		input = scanner.nextLine();
		System.out.println();
		if (input.equals("n")) {
			playOn = false;
		} else {
			playOn = true;
		}

		do {
			Boolean gameOver = false;
			System.out.println("\n" + "A new game has begun:" + "\n");

			Player player = new Player(nickname);
			Player dealer = new Player("Dealer");
			Deck deck = new Deck();

			// Shuffle deck
			deck.shuffle();

			player.giveCard(deck.draw());
			dealer.giveCard(deck.draw());
			player.giveCard(deck.draw());

			System.out.println(player.showHand(playOn));

			holdCard = deck.draw();
			System.out.println("\n" + "Dealer: " + "\n" + "Hidden" + "\n" + dealer.giveCard(holdCard));

			if (playOn) {
				System.out.println("(" + holdCard.getRank().getValue() + ")");
			}

			do {

				do {
					System.out.println("\n" + "Would you like one more card? (hit/stand)");
					input = scanner.nextLine();
				} while (!input.equalsIgnoreCase("hit") && !input.equalsIgnoreCase("stand"));

				if (input.equalsIgnoreCase("hit")) {
					player.giveCard(deck.draw());
					System.out.println("\n" + player.showHand(playOn));

					if (player.getSum() > 21) {
						gameOver = true;
						System.out.println("\n" + "You busted! Dealer wins.");
					}

					else if (player.getSum() == 21) {
						System.out.println("\n" + "Blackjack! You win!");
						gameOver = true;
					}
				}

				else if (input.equalsIgnoreCase("stand")) {
					System.out.println("\n" + nickname + " stands with " + player.getSum() + "\n");
				}
			} while (input.equalsIgnoreCase("hit") && !gameOver); // loops this part as long as input is "hit" and not a
																	// game over

			if (gameOver == false) {
				System.out.println(dealer.showHand(playOn));
			}

			while (dealer.getSum() < 17 && gameOver == false) {
				System.out.println(dealer.giveCard(deck.draw()));
				System.out.println(dealer.checkCheat(playOn));

				if (dealer.getSum() == 21) {
					System.out.println("\n" + "Blackjack! Dealer wins this time");
					gameOver = true;
				} else if (dealer.getSum() > 21) {
					System.out.println("\n" + "Dealer busted! You win!");
					gameOver = true;
				}
			}

			if (!gameOver) {
				System.out.println("\n" + "Dealer: " + "(" + dealer.getSum() + ")");
				System.out.println(nickname + " : " + "(" + player.getSum() + ")");

				if (dealer.getSum() > player.getSum()) {
					System.out.println("Sorry! Dealer wins");
				} else if (dealer.getSum() < player.getSum()) {
					System.out.println("Congratulations! You win!");
				} else {
					System.out.println("You tied!");
				}
			}

			System.out.println();
			System.out.println("Would you like to start a new game?  'y/n' :");
			do {
				input = scanner.nextLine();
			} while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));

		} while (input.equalsIgnoreCase("y"));

		scanner.close();
	}
}