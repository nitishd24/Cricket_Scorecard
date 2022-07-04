package com.cricket.player;

public class Player {
	
	private String name;
	private int score;
	private int fours;
	private int sixes;
	private int balls;
	private BattingStatus battingStatus;
	private double strikeRate;
	
	private String oversBalled;
	private int ballsBalled;
	private int runsConceded;
	private int wicketsTaken;
	private int maidenOvers;
	private int dotBalls;
	private double economy;
	
	public Player(String name, BattingStatus battingStatus) {
		super();
		this.name = name;
		this.battingStatus = battingStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getFours() {
		return fours;
	}
	public void setFours(int fours) {
		this.fours = fours;
	}
	public int getSixes() {
		return sixes;
	}
	public void setSixes(int sixes) {
		this.sixes = sixes;
	}
	public int getBalls() {
		return balls;
	}
	public void setBalls(int balls) {
		this.balls = balls;
	}
	public BattingStatus getPlayerStatus() {
		return battingStatus;
	}
	public void setPlayerStatus(BattingStatus battingStatus) {
		this.battingStatus = battingStatus;
	}
	public double getStrikeRate() {
		return strikeRate;
	}
	public void setStrikeRate(double strikeRate) {
		this.strikeRate = strikeRate;
	}
	public String getOversBalled() {
		return oversBalled;
	}
	public void setOversBalled(String oversBalled) {
		this.oversBalled = oversBalled;
	}
	public int getBallsBalled() {
		return ballsBalled;
	}
	public void setBallsBalled(int ballsBalled) {
		this.ballsBalled = ballsBalled;
	}
	public int getRunsConceded() {
		return runsConceded;
	}
	public void setRunsConceded(int runsConceded) {
		this.runsConceded = runsConceded;
	}
	public int getWicketsTaken() {
		return wicketsTaken;
	}
	public void setWicketsTaken(int wicketsTaken) {
		this.wicketsTaken = wicketsTaken;
	}
	public int getMaidenOvers() {
		return maidenOvers;
	}
	public void setMaidenOvers(int maidenOvers) {
		this.maidenOvers = maidenOvers;
	}
	public int getDotBalls() {
		return dotBalls;
	}
	public void setDotBalls(int dotBalls) {
		this.dotBalls = dotBalls;
	}
	public double getEconomy() {
		return economy;
	}
	public void setEconomy(double economy) {
		this.economy = economy;
	}
	@Override
	public String toString() {
		return "Player [name=" + name + ", score=" + score + ", fours=" + fours + ", sixes=" + sixes + ", balls="
				+ balls + ", battingStatus=" + battingStatus + ", strikeRate=" + strikeRate + ", oversBalled="
				+ oversBalled + ", ballsBalled=" + ballsBalled + ", runsConceded=" + runsConceded + ", wicketsTaken="
				+ wicketsTaken + ", maidenOvers=" + maidenOvers + ", dotBalls=" + dotBalls + ", economy=" + economy
				+ "]";
	}
		
}
