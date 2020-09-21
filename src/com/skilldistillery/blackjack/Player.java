package com.skilldistillery.blackjack;

import java.util.ArrayList;

import com.skilldistillery.common.cards.Card;
import com.skilldistillery.common.cards.Rank;

public class Player {
	private final ArrayList<Card> hand;
	private final String name;

	public Player(String name) {
		this.name = name;
		hand = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public String giveCard(Card card) {
		hand.add(card);
		return card.toString();
	}

	public int getSum() {
		int numOfAces = 0;
		int handSum = 0;
		for (Card card : hand) {
			if (card.getRank() == Rank.ACE) {
				numOfAces++;
			}
			handSum += card.getRank().getValue();
		}

		// Checking Aces
		if (handSum > 21 && numOfAces == 1) {
			handSum -= 10;
		} else if (handSum > 21 && numOfAces > 1) {
			handSum -= 10 * (numOfAces - 1);
		}
		return handSum;
	}

	public String showHand(Boolean helpPlayer) {
		String playerHand = "";
		for (Card card : hand) {
			playerHand += "\n" + card.toString();
		}
		if (helpPlayer == true) {
			playerHand += "\n" + ("(" + this.getSum() + ")");
		}
		return this.name + ": " + playerHand;
	}

	public String checkCheat(Boolean checkSum) {
		String sum = "";
		if (checkSum == true) {
			sum = ("(" + this.getSum() + ")");
		}
		return sum;
	}

}