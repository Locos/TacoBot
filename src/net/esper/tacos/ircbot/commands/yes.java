package net.esper.tacos.ircbot.commands;

import net.esper.tacos.ircbot.ICommand;
import net.esper.tacos.ircbot.Rank;
import net.esper.tacos.ircbot.TacoBot;

import org.pircbotx.Colors;
import org.pircbotx.User;

public class yes implements ICommand {
	
	@Override
	public void exec(String message, String[] args, User user) {
		if (voterally.voteUser != null && !voterally.agree.contains(user.getNick()) && !voterally.disagree.contains(user.getNick())) {
			voterally.agree.add(user.getNick());
			TacoBot.sendMessage(user.getNick() + " " + Colors.GREEN + "agrees" + Colors.removeColors(" with inviting " + voterally.voteUser + " to " + TacoBot.CHAN));
			voterally.voteProcess();
		}
	}
	
	@Override
	public Rank getRank() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getNoAccessMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
