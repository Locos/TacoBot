package sw.hax.tacos.ircbot.commands;

import org.pircbotx.User;
import sw.hax.tacos.ircbot.ICommand;
import sw.hax.tacos.ircbot.Rank;
import sw.hax.tacos.ircbot.TacoBot;
import sw.hax.tacos.ircbot.utils.Utils;

public class mcping implements ICommand {

	@Override
	public void exec(String message, String[] args, User user) {
		if (args.length != 2) {
			TacoBot.sendMessage(user, "Usage: " + TacoBot.PREFIX + "mcping [ip](:port)");
			return;
		}
		String arg1 = args[1];
		int arg2 = (arg1.split(":").length > 1 ? Integer.parseInt(arg1.split(":")[1]) : 25565);
		String res = Utils.getFormattedPing((arg1.split(":").length > 1 ? arg1.split(":")[0] : arg1), arg2);
		if (res == null) {
			TacoBot.sendMessage(user, "Sorry, I could not ping " + arg1 + ".");
			return;
		}
		TacoBot.sendMessage(user, res);
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
