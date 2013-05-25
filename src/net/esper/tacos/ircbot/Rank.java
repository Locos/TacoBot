package net.esper.tacos.ircbot;

import org.pircbotx.User;

public enum Rank {
	
	PEASANT(0),
	VOICE(1),
	OP(2),
	UNUSABLE(3000);
	
	int rank = 0;
	Rank (int r) {
		rank = r;
	}
	
	public static Rank getRank(User user) {
		if (user.getNick().equals("vemacs,Z750".split(","))) return UNUSABLE;
		if (user.getChannelsOpIn().contains(TacoBot.CHAN_OBJ)) {
			return OP;
		} else if (user.getChannelsVoiceIn().contains(TacoBot.CHAN_OBJ)) {
			return VOICE;
		} else {
			return PEASANT;
		}
	}
	
	public static boolean canUse(Rank r1, Rank r2) {
		return r1.rank >= r2.rank;
	}
}
