package net.esper.tacos.ircbot.commands;

import java.util.Hashtable;
import java.util.jar.Attributes;

import javax.naming.directory.InitialDirContext;

import net.esper.tacos.ircbot.ICommand;
import net.esper.tacos.ircbot.Rank;
import net.esper.tacos.ircbot.TacoBot;
import net.esper.tacos.ircbot.utils.Utils;

import org.pircbotx.User;

public class mcping implements ICommand {

	@Override
	public void exec(String message, String[] args, User user) {
		if (args.length != 2) {
			TacoBot.sendMessage(user, "Usage: " + TacoBot.PREFIX
					+ "mcping [ip](:port)");
			return;
		}
		String arg1 = args[1];
		int arg2 = (arg1.split(":").length > 1 ? Integer.parseInt(arg1
				.split(":")[1]) : Integer.parseInt(getServerPort(arg1)));
		String res = Utils.getFormattedPing(
				(arg1.split(":").length > 1 ? arg1.split(":")[0] : arg1), arg2);
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

	private static String getServerPort(String par0Str) {
		try {
			Class.forName("com.sun.jndi.dns.DnsContextFactory");
			Hashtable var2 = new Hashtable();
			var2.put("java.naming.factory.initial",
					"com.sun.jndi.dns.DnsContextFactory");
			var2.put("java.naming.provider.url", "dns:");
			InitialDirContext var3 = new InitialDirContext(var2);
			javax.naming.directory.Attributes var4 = var3.getAttributes(
					"_minecraft._tcp." + par0Str, new String[] { "SRV" });
			String[] var5 = var4.get("srv").get().toString().split(" ", 4);
			return var5[2];
		} catch (Throwable var6) {
			return Integer.toString(25565);
		}
	}
}
