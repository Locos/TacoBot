package sw.hax.tacos.ircbot.commands;

import org.pircbotx.User;
import sw.hax.tacos.ircbot.ICommand;
import sw.hax.tacos.ircbot.Rank;
import sw.hax.tacos.ircbot.TacoBot;
import sw.hax.tacos.ircbot.utils.Utils;

public class annoy implements ICommand {

	@Override
	public void exec(String message, String[] args, User user) {
		if (args.length < 2) {
			TacoBot.sendMessage(user, "Usage: " + TacoBot.PREFIX + "annoy [nick] [message]");
			return;
		}
		if (args[1].equalsIgnoreCase(TacoBot.NICK) || args[1].equalsIgnoreCase(user.getNick())) return;
		TacoBot.sendMessage(TacoBot.bot.getUser(args[1]), Utils.compileMessage(2, args));
	}

	@Override
	public Rank getRank() {
		return Rank.OP;
	}

	@Override
	public String getNoAccessMessage() {
		return null;
	}
}
