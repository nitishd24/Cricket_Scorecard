package com.cricket.team;

import java.util.List;
import java.util.Queue;

import com.cricket.player.Player;
import com.cricket.scorecard.Scorecard;

public class Team {
	private List<Player> players;
	private Queue<Player> battingOrder;
	private Player striker;
	private Player nonStriker;
	private Player bowler;
	private TeamStatus teamStatus;	
	private Scorecard scorecard;
	
	public Team(List<Player> players, Queue<Player> battingOrder, Player striker, Player nonStriker,
			TeamStatus teamStatus, Scorecard scorecard) {
		super();
		this.players = players;
		this.battingOrder = battingOrder;
		this.striker = striker;
		this.nonStriker = nonStriker;
		this.teamStatus = teamStatus;
		this.scorecard = scorecard;
	}

	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public Queue<Player> getBattingOrder() {
		return battingOrder;
	}
	public void setBattingOrder(Queue<Player> battingOrder) {
		this.battingOrder = battingOrder;
	}
	public Player getStriker() {
		return striker;
	}
	public void setStriker(Player striker) {
		this.striker = striker;
	}
	public Player getNonStriker() {
		return nonStriker;
	}
	public void setNonStriker(Player nonStriker) {
		this.nonStriker = nonStriker;
	}
	public Player getBowler() {
		return bowler;
	}
	public void setBowler(Player bowler) {
		this.bowler = bowler;
	}
	public TeamStatus getTeamStatus() {
		return teamStatus;
	}
	public void setTeamStatus(TeamStatus teamStatus) {
		this.teamStatus = teamStatus;
	}
	public Scorecard getScorecard() {
		return scorecard;
	}
	public void setScorecard(Scorecard scorecard) {
		this.scorecard = scorecard;
	}
	@Override
	public String toString() {
		return "Team [players=" + players + ", battingOrder=" + battingOrder + ", striker=" + striker + ", nonStriker="
				+ nonStriker + ", bowler=" + bowler + ", teamStatus=" + teamStatus + ", scorecard=" + scorecard + "]";
	}
	
}
