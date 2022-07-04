package com.cricket;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import com.cricket.player.Player;
import com.cricket.player.BattingStatus;
import com.cricket.team.Team;
import com.cricket.team.TeamStatus;
import com.cricket.scorecard.Over;
import com.cricket.scorecard.Scorecard;

public class Match {
	
	public static Scanner sc = new Scanner(System.in);
	public static int teamSize;
	public static int noOfOvers;
	public static boolean isAllWicketsDown;
	public static boolean isTargetChased = false;
	public static Team team1;
	public static Team team2;
	public static Team battingTeam;
	public static Team bowlingTeam;
	public static int currentBatting;
	public static int currentBowling;
	public static Set<String> validDeliveries = new HashSet<>();
	
	// set valid deliveries
	public static void setValidDeleveries()
	{
		validDeliveries.add("Wd");
		validDeliveries.add("Nb");
		validDeliveries.add("W");
		validDeliveries.add("0");
		validDeliveries.add("1");
		validDeliveries.add("2");
		validDeliveries.add("3");
		validDeliveries.add("4");
		validDeliveries.add("6");
	}
	
	// display final result
	public static void displayResult()
	{
		int team1_score = team1.getScorecard().getTotalScore();
		int team2_score = team2.getScorecard().getTotalScore();
		if(team1_score > team2_score)
		{
			System.out.println("Result: Team 1 won the match by " 
					+ (team1_score - team2_score) + " runs");
		}
		else
		{
			System.out.println("Result: Team 2 won the match by " 
					+ (teamSize - team2.getScorecard().getWickets() -1) + " wickets");
		}
	}
	
	// display Batting scorecard
	public static void displayBattingScoreCard()
	{
		String leftAlignFormat = "| %-15s | %-4d | %-4d | %-4d | %-4d | %.2f |%n";
		System.out.println("\nBatting Scorecard for Team " + currentBatting + ":");
		System.out.format("+-----------------+------+------+------+------+------------+%n");
		System.out.format("| Player Name     | Score|  4s  | 6s   | Balls| Strike Rate|%n");
		System.out.format("+-----------------+------+------+------+------+------------+%n");
		char c;
		for(Player p : battingTeam.getPlayers())
		{
			c=' ';
			if(battingTeam.getStriker().equals(p))
				c='*';
			System.out.format(leftAlignFormat, p.getName()+c, p.getScore(), p.getFours(), p.getSixes(), p.getBalls(), p.getStrikeRate());
		}
		System.out.format("+-----------------+------+------+------+------+------------+%n");
		System.out.println("Total: " 
				+ battingTeam.getScorecard().getTotalScore() + "/" + battingTeam.getScorecard().getWickets());
		System.out.println("Overs: " + getNoOfOvers());
		System.out.println("Extras: " + battingTeam.getScorecard().getExtras() + "\n");
	}
	
	// display Bowling scorecard
	public static void displayBowlingScoreCard()
	{
		String leftAlignFormat = "| %-15s | %-4s | %-4d | %-5d | %-4d | %-7d | %.2f |%n";
		System.out.println("Bowling Scorecard for Team " + currentBowling + ":");		
		System.out.format("+-----------------+------+------+-------+------+---------+------------+%n");
		System.out.format("| Player Name     | Overs| Runs |Wickets|Maiden|Dot Balls|   Economy  |%n");
		System.out.format("+-----------------+------+------+-------+------+---------+------------+%n");
		char c;
		for(Player p : bowlingTeam.getPlayers())
		{
			c=' ';
			if(bowlingTeam.getBowler().equals(p))
				c='*';
			System.out.format(leftAlignFormat, p.getName()+c, p.getOversBalled(), p.getRunsConceded(), 
					p.getWicketsTaken(),  p.getMaidenOvers(), p.getDotBalls(), p.getEconomy());
		}	
		System.out.format("+-----------------+------+------+-------+------+---------+------------+%n");
		System.out.println();
	}
	
	// update strike rate of player
	public static void updateStrikeRate(Player player)
	{
		double strikeRate=0;
		DecimalFormat df = new DecimalFormat("0.00");
		if(player.getScore()>0)
		{
			strikeRate = ((double) player.getScore())/player.getBalls() * 100;
		}
		strikeRate = Double.parseDouble(df.format(strikeRate));
		player.setStrikeRate(strikeRate);
	}
	
	// update economy of bowler
	public static void updateEconomy(Player bowler)
	{
		double economy=0.0;
		DecimalFormat df = new DecimalFormat("0.00");
		if(bowler.getRunsConceded()>0)
		{
			economy = ((double) bowler.getRunsConceded()/(bowler.getBallsBalled()/6.0));
		}
		economy = Double.parseDouble(df.format(economy));
		bowler.setEconomy(economy);
		int overs = bowler.getBallsBalled()/6;
		int carryOverBalls = bowler.getBallsBalled()%6;
		bowler.setOversBalled(overs + "." + carryOverBalls);
	}
	
	// update team Scorecard
	public static void updateScore(String ball)
	{
		if(ball.equalsIgnoreCase("Wd") || ball.equalsIgnoreCase("Nb"))
		{
			Scorecard scorecard = battingTeam.getScorecard();
			scorecard.setTotalScore(scorecard.getTotalScore()+1);
			scorecard.setExtras(scorecard.getExtras()+1);
			if(currentBatting == 2)
				checkTargetChased();
		}
		else if(ball.equalsIgnoreCase("W"))
		{
			Scorecard scorecard = battingTeam.getScorecard();			
			scorecard.setWickets(scorecard.getWickets()+1);			
		}
		else
		{
			Integer runs = Integer.parseInt(ball);
			Scorecard scorecard = battingTeam.getScorecard();			
			scorecard.setTotalScore(scorecard.getTotalScore() + runs);
		}
	}
	
	// update player score for batting and bowling
	public static void updatePlayerScore(String ball)
	{
		Player player = battingTeam.getStriker();
		Player bowler = bowlingTeam.getBowler();
		if(ball.equalsIgnoreCase("Wd") || ball.equalsIgnoreCase("Nb"))
		{
			bowler.setRunsConceded(bowler.getRunsConceded()+1);
			updateEconomy(bowler);
		}
		else if(ball.equalsIgnoreCase("W"))
		{
			player.setPlayerStatus(BattingStatus.OUT);
			player.setBalls(player.getBalls()+1);
			
			if(battingTeam.getBattingOrder().isEmpty())
			{
				isAllWicketsDown = true;
			}
			else
			{
				Player newPlayer = battingTeam.getBattingOrder().poll();
				newPlayer.setPlayerStatus(BattingStatus.BATTING);
				battingTeam.setStriker(newPlayer);
			}
			
			bowler.setBallsBalled(bowler.getBallsBalled()+1);
			bowler.setWicketsTaken(bowler.getWicketsTaken()+1);
			bowler.setDotBalls(bowler.getDotBalls()+1);
			updateEconomy(bowler);
		}
		else
		{
			Integer runs = Integer.parseInt(ball);
			player.setScore(player.getScore()+runs);
			player.setBalls(player.getBalls()+1);
			if(runs == 4)
				player.setFours(player.getFours()+1);
			if(runs == 6)
				player.setSixes(player.getSixes()+1);
			updateStrikeRate(player);
			if(currentBatting == 2)
				checkTargetChased();
			if(runs == 1 || runs == 3)
				changeStriker();
			
			bowler.setBallsBalled(bowler.getBallsBalled()+1);
			bowler.setRunsConceded(bowler.getRunsConceded()+runs);
			if(runs == 0)
				bowler.setDotBalls(bowler.getDotBalls()+1);
			updateEconomy(bowler);
		}
	}
	
	// check if target is chased
	public static void checkTargetChased()
	{
		if(team2.getScorecard().getTotalScore() > team1.getScorecard().getTotalScore())
			isTargetChased = true;	
	}
	
	// returns true for maiden over else returns false
	public static boolean checkMaidenOver(Over over)
	{
		boolean isMaiden = false;
		if(over.getBalls().size()==6)
		{
			for(String ball: over.getBalls())
			{
				if(ball.equals("W") || ball.equals("0"))
				{
					isMaiden = true;
				}
				else
				{
					isMaiden = false;
					break;
				}
			}
		}
		return isMaiden;
	}
	
	// change striker in case of 1, 3 or over change
	public static void changeStriker()
	{
		Player temp = battingTeam.getStriker();
		battingTeam.setStriker(battingTeam.getNonStriker());
		battingTeam.setNonStriker(temp);
	}
	
	// check if bowler is valid and returns bowler if valid else returns null
	public static Player validBowler(String bowler)
	{
		Player validBowler = null;
		for(Player p : bowlingTeam.getPlayers())
		{
			if(p.getName().equals(bowler))
			{
				validBowler = p;
				break;
			}					
			else
				continue;
		}
		return validBowler;
	}
	
	// get valid inputs for overs
	public static void getOvers()
	{
		List<Over> overs = new ArrayList<>();
		for(int i=1; i<=noOfOvers; i++)
		{
			System.out.println("Select bowler Name from team " + currentBowling + " :");
			String bowlerName = sc.next();
			Player bowler = validBowler(bowlerName);
			if(bowler == null)
			{
				i--;
				continue;
			}			
			bowlingTeam.setBowler(bowler);
			System.out.println("\nOver "+ i + ":");
			List<String> balls = new ArrayList<>();
			int ball=1;
			while(ball <= 6)
			{
				if(isAllWicketsDown || isTargetChased)
					break;
				String currentBall = sc.next();
				if(validDeliveries.contains(currentBall))
				{	
					if(currentBall.equalsIgnoreCase("Wd") || currentBall.equalsIgnoreCase("Nb"))
					{
						balls.add(currentBall);
						updateScore(currentBall);
						updatePlayerScore(currentBall);
					}
					else
					{
						balls.add(currentBall);
						updateScore(currentBall);
						updatePlayerScore(currentBall);
						ball++;
					}
				}
				else
				{
					System.out.print("Enter valid delivery: ");
				}
			}
			Over over = new Over(balls);
			if(checkMaidenOver(over))
			{
				bowler.setMaidenOvers(bowler.getMaidenOvers()+1);
			}
			overs.add(over);
			changeStriker();
			Scorecard scorecard = battingTeam.getScorecard();			
			scorecard.setOvers(overs);
			displayBattingScoreCard();
			displayBowlingScoreCard();
		}
	}
	
	// get valid team details and returns team
	public static Team getTeam()
	{
		Queue<Player> battingOrder = new LinkedList<>();
		List<Player> players = new ArrayList<>();
		for(int i=1; i<=teamSize; i++)
		{
			Player player;
			if(i==1)
			{
				player = new Player(sc.next(), BattingStatus.BATTING);
			}
			else if(i==2)
			{
				player = new Player(sc.next(), BattingStatus.BATTING);
			}
			else
			{
				player = new Player(sc.next(), BattingStatus.YETTOBAT);
				battingOrder.add(player);
			}
			players.add(player);
		}		
		Team team = new Team(players, battingOrder, players.get(0), players.get(1), TeamStatus.BATTING, new Scorecard());
		return team;
	}
	
	// returns no of overs bowled
	public static int getNoOfOvers()
	{
		if(battingTeam.getScorecard()!=null && battingTeam.getScorecard().getOvers()!=null)
			return battingTeam.getScorecard().getOvers().size();
		
		return 0;
	}
	
	public static void main(String[] args) throws Exception
	{	
		try 
		{
			setValidDeleveries();
			System.out.print("No. of players for each team: ");
			while (!sc.hasNextInt()) {
		        System.out.print("Please enter a valid Input: ");
		        sc.next();
		    }
			teamSize = sc.nextInt();
			System.out.print("No. of overs: ");
			while (!sc.hasNextInt()) {
				System.out.print("Please enter a valid Input: ");
		        sc.next();
		    }
			noOfOvers = sc.nextInt();
			System.out.println("Batting Order for team 1: ");
			team1 = getTeam();
			System.out.println("Batting Order for team 2: ");
			team2 = getTeam();
			isAllWicketsDown = false;
			battingTeam = team1;
			bowlingTeam = team2;
			currentBatting = 1;
			currentBowling = 2;
			getOvers();
			System.out.println("First Innings has ended | Second Innings begin");
			
			team1.setTeamStatus(TeamStatus.BOWLING);		
			team2.setTeamStatus(TeamStatus.BATTING);
			isAllWicketsDown = false;
			battingTeam = team2;
			bowlingTeam = team1;
			currentBatting = 2;	
			currentBowling = 1;
			getOvers();
			displayResult();
		}
		catch(Exception e)
		{
			throw new Exception("Please enter valid Input!"+e);
		}
	}
}
