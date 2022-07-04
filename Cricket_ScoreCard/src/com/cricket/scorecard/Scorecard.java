package com.cricket.scorecard;

import java.util.List;

public class Scorecard {
	
	private List<Over> overs;
	private int total;
	private int wickets;
	private int extras;
	
	
	public List<Over> getOvers() {
		return overs;
	}
	public void setOvers(List<Over> overs) {
		this.overs = overs;
	}
	public int getTotalScore() {
		return total;
	}
	public void setTotalScore(int totalScore) {
		this.total = totalScore;
	}
	public int getWickets() {
		return wickets;
	}
	public void setWickets(int wickets) {
		this.wickets = wickets;
	}
	public int getExtras() {
		return extras;
	}
	public void setExtras(int extras) {
		this.extras = extras;
	}
	@Override
	public String toString() {
		return "Scorecard [overs=" + overs + ", total=" + total + ", wickets=" + wickets + ", extras=" + extras + "]";
	}
	
}
