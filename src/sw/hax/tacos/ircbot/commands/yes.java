package sw.hax.tacos.ircbot.commands;

import org.pircbotx.Colors;
import org.pircbotx.User;
import sw.hax.tacos.ircbot.ICommand;
import sw.hax.tacos.ircbot.Rank;
import sw.hax.tacos.ircbot.TacoBot;

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
