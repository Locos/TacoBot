package net.esper.tacos.ircbot;

import org.pircbotx.User;

public interface ICommand {
	
	public void exec(String message, String[] args, User user);
	
	public Rank getRank();
	
	public String getNoAccessMessage();
}
