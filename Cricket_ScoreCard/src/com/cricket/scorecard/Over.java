package com.cricket.scorecard;

import java.util.List;

public class Over {
	List<String> balls;

	public Over(List<String> balls) {
		super();
		this.balls = balls;
	}
	public List<String> getBalls() {
		return balls;
	}
	public void setBalls(List<String> balls) {
		this.balls = balls;
	}
	@Override
	public String toString() {
		return "Over [balls=" + balls + "]";
	}
	
}
