package net.esper.tacos.ircbot.commands;

import net.esper.tacos.ircbot.ICommand;
import net.esper.tacos.ircbot.Rank;
import net.esper.tacos.ircbot.TacoBot;
import net.esper.tacos.ircbot.utils.Utils;

import org.pircbotx.User;

//Oh boy do I have plans for this...

public class toywithec implements ICommand {
	
	private static String[] trollmessages = new String[] { 
		"Here's a taco...:::NOT!", 
		"I wonder if EncryptedCurse:::even has a GF!", 
		"What if EncryptedCurse:::was....GONE FOREVER!", 
		"Hey, can you solve the following math equation...:::Are  (the Euler–Mascheroni constant), π+e, π-e, πe, π/e, πe, π√2, ππ, eπ2, ln π, 2e, ee, Catalan's constant or Khinchin's constant rational, algebraic irrational, or transcendental? What is the irrationality measure of each of these numbers?", 
		"Who do you like to sleep with...:::That guy over there, named nobody?", 
		"What if joehot200 and EncryptedCurse:::got married and had sex together?", 
		"What if EncryptedCurse:::was black!", 
		"What if vemacs understood the fact that,:::EncryptedCurse lived in Boulder, CO", 
		"I heard SuperSpyTX was going to play a private DJ set for you.:::IN YOUR DREAMS!LALALALALALALLA",
		"I'm sorry to say this but..:::You have HIV, and are about to get AIDS and die very soon."
		};
	
	@Override
	public void exec(String message, String[] args, User user) {
		User ec = TacoBot.bot.getUser("EncryptedCurse");
		if (!ec.getChannels().contains(TacoBot.CHAN_OBJ) && !user.getChannelsOpIn().contains(TacoBot.CHAN_OBJ)) {
			TacoBot.sendMessage(user, "Sorry, EncryptedCurse is not around to be toyed with.");
			return;
		}
		// Let's troll him :D
		String rand = trollmessages[Utils.getRandom(trollmessages.length)];
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
