package sw.hax.tacos.ircbot.commands;

import org.pircbotx.User;
import sw.hax.tacos.ircbot.ICommand;
import sw.hax.tacos.ircbot.Rank;

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
