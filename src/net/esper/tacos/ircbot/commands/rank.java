package net.esper.tacos.ircbot.commands;

import net.esper.tacos.ircbot.ICommand;
import net.esper.tacos.ircbot.Rank;
import net.esper.tacos.ircbot.TacoBot;

import org.pircbotx.User;

public class rank implements ICommand {
	
	@Override
	public void exec(String message, String[] args, User user) {
		TacoBot.sendMessage(user, "Your rank is: " + Rank.getRank(user).name());
	}
	
	@Override
	public Rank getRank() {
		return null;
	}
	
	@Override
	public String getNoAccessMessage() {
		return null;
	}
}
