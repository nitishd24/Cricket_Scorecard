*** Run Match.java as Java Application and provide Input to test ***

com.cricket.player:
	Player.java: Stores all player details.
	BattingStatus.java: Enum for batting status Batting/Out/YetToBat.

com.cricket.scorecard:
	Over.java: Stores over details.
	Scorecard.java: Stores scorecard details like totalScore, wickets, extras, overs.

com.cricket.team: 
	Team.java: Store team details like players, battingOrder, striker, nonStriker, scorecard.
	TeamStatus.java: Enum for Team status Batting/Bowling.

com.cricket.Match:
	setValidDeleveries(): set valid deliveries.
	displayResult(): display final result.
	displayBattingScoreCard(): display Batting scorecard.
	displayBowlingScoreCard(): display Bowling scorecard.
	updateStrikeRate(): update strike rate of player.
	updateEconomy(): update economy of bowler.
	updateScore(): update Team Scorecard.
	updatePlayerScore(): update player score for batting and bowling.
	changeStriker(): change striker in case of 1, 3 or over change.
	checkTargetChased(): check if target is chased.
	checkMaidenOver(): returns true for maiden over else returns false.
	validBowler(): check if bowler is valid and returns bowler if valid else returns null.
	getOvers(): get valid inputs for overs.
	getTeam(): get valid team details and returns team instance.
	getNoOfOvers(): returns no of overs bowled.
	
	
