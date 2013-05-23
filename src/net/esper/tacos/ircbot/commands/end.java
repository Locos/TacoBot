package net.esper.tacos.ircbot.commands;

import net.esper.tacos.ircbot.ICommand;
import net.esper.tacos.ircbot.Rank;

import org.pircbotx.User;

public class end implements ICommand {
	
	@Override
	public void exec(String message, String[] args, User user) {
		if (voterally.voteUser != null) {
			voterally.endRally = true;
			voterally.voteProcess();
		}
	}
	
	@Override
	public Rank getRank() {
		// TODO Auto-generated method stub
		return Rank.UNUSABLE;
	}
	
	@Override
	public String getNoAccessMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
