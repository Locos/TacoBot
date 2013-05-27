package net.esper.tacos.ircbot.commands;

import net.esper.tacos.ircbot.ICommand;
import net.esper.tacos.ircbot.Rank;
import net.esper.tacos.ircbot.TacoBot;
import net.esper.tacos.ircbot.utils.Utils;

import org.pircbotx.User;

//Oh boy do I have plans for this...

public class toywithec implements ICommand {
		
	@Override
	public void exec(String message, String[] args, User user) {
		User ec = TacoBot.bot.getUser("EncryptedCurse");
		if (!ec.getChannels().contains(TacoBot.CHAN_OBJ) && !user.getChannelsOpIn().contains(TacoBot.CHAN_OBJ)) {
			TacoBot.sendMessage(user, "Sorry, EncryptedCurse is not around to be toyed with.");
			return;
		}
		// Let's troll him :D
		String rand = TacoBot.trollmessages.get(Utils.getRandom(TacoBot.trollmessages.size()));
		if (rand.contains(":::")) {
			String[] spl = rand.split(":::");
			TacoBot.sendMessage("EncryptedCurse", spl[0]);
			try {
				Thread.sleep(Utils.getRandom(5000));
			} catch (InterruptedException e) {}
			TacoBot.sendMessage("EncryptedCurse", spl[1]);
		} else {
			TacoBot.sendMessage("EncryptedCurse", rand);
		}
	}
	
	@Override
	public Rank getRank() {
		return Rank.PEASANT;
	}
	
	@Override
	public String getNoAccessMessage() {
		//return "Sorry, only Voiced+ can play around with this feature.";
		return null;
	}
	
}
