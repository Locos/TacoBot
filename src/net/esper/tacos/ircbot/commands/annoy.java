package net.esper.tacos.ircbot.commands;

import net.esper.tacos.ircbot.ICommand;
import net.esper.tacos.ircbot.Rank;
import net.esper.tacos.ircbot.TacoBot;
import net.esper.tacos.ircbot.utils.Utils;

import org.pircbotx.User;

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
