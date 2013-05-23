package sw.hax.tacos.ircbot.commands;

import org.pircbotx.User;
import sw.hax.tacos.ircbot.ICommand;
import sw.hax.tacos.ircbot.Rank;
import sw.hax.tacos.ircbot.TacoBot;

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
