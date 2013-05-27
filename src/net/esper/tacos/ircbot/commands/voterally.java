package net.esper.tacos.ircbot.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.esper.tacos.ircbot.ICommand;
import net.esper.tacos.ircbot.Rank;
import net.esper.tacos.ircbot.TacoBot;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;
import org.pircbotx.User;

public class voterally implements ICommand {

	public static String voteUser = null;
	public static boolean endRally = false;
	public static List<String> agree = Collections
			.synchronizedList(new ArrayList<String>());
	public static List<String> disagree = Collections
			.synchronizedList(new ArrayList<String>());

	private static List<User> getFilteredList() {
		String[] filteredUsers = TacoBot.getOps();
		List<User> users = new CopyOnWriteArrayList<User>(
				TacoBot.CHAN_OBJ.getUsers());
		for (String filtered : filteredUsers) {
			users.remove(filtered);
		}
		for (User user : users) {
			if (user.isAway() || user.getNick().contains("afk")
					|| user.isVerified()) {
				users.remove(user);
			}
		}
		return users;
	}

	private static boolean isBlacklisted(String user) {
		for (String g : TacoBot.blacklist) {
			if (g.equalsIgnoreCase(user)) {
				return true;
			}
		}
		return false;
	}

	public static void voteProcess() {
		if (endRally
				|| (agree.size() + disagree.size()) >= (getFilteredList()
						.size() / 2) + 1) {
			endRally = false;
			TacoBot.sendMessage("The vote rally is over! Users who "
					+ Colors.GREEN + "Agreed: " + agree + Colors.RED
					+ " and Disagreed: " + disagree);
			if (agree.size() > disagree.size()) {
				TacoBot.sendMessage(Colors.GREEN + "Majority vote wins! "
						+ voteUser + " will now be invited to " + TacoBot.CHAN);
				if (!voteUser.equalsIgnoreCase("cancel")
						&& !voteUser.startsWith("!")) {
					TacoBot.bot.sendInvite(voteUser, TacoBot.CHAN);
				}
			} else if (disagree.size() > agree.size()) {
				TacoBot.sendMessage(Colors.RED + "Majority vote wins! "
						+ voteUser + " will not be invited to " + TacoBot.CHAN);
			} else {
				TacoBot.sendMessage("http://www.youtube.com/watch?v=5NV6Rdv1a3I");
				TacoBot.sendMessage("Get lucky next time?");
			}
			voteUser = null;
			agree.clear();
			disagree.clear();
		}
	}

	@Override
	public void exec(String message, String[] args, User user) {
		if (args.length != 2) {
			TacoBot.sendMessage(user, "Usage: " + TacoBot.PREFIX
					+ "voterally [user]");
			return; // damn
		}
		if (args[1].equals("cancel") && voteUser == null) {
			TacoBot.sendMessage(user, "Usage: " + TacoBot.PREFIX
					+ "voterally [user]");
			return;
		}
		if (!args[1].startsWith("!")) {
			if (isBlacklisted(args[1])
					|| TacoBot.CHAN_OBJ.getUsers().contains(
							TacoBot.bot.getUser(args[1]))
					|| args[1].equalsIgnoreCase(TacoBot.NICK)
					|| args[1].equalsIgnoreCase(user.getNick())) {
				TacoBot.sendMessage("._.");
				if (isBlacklisted(args[1])) {
					user.sendMessage("(DO NOT TELL ANYBODY THIS!) That user is blacklisted!");
				}
				return;
			}
		}
		if (voteUser != null) {
			if (args[1].equalsIgnoreCase("cancel")) {
				voteUser = null;
				agree.clear();
				disagree.clear();
				TacoBot.sendMessage("The vote rally has been cancelled by "
						+ user.getNick());
			} else {
				TacoBot.sendMessage(user,
						"Sorry, a vote is already in process.  Type 'cancel' if you wish to cancel.");
			}
		} else {
			voteUser = args[1];
			agree.clear();
			disagree.clear();
			TacoBot.sendMessage("A vote rally for " + voteUser
					+ " has started! Type " + TacoBot.PREFIX
					+ "yes to invite or " + TacoBot.PREFIX
					+ "no to not invite to #tacos.");
		}
	}

	@Override
	public Rank getRank() {
		return Rank.VOICE;
	}

	@Override
	public String getNoAccessMessage() {
		return "Only ops can start vote rallies.";
	}

}
