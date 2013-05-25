package net.esper.tacos.ircbot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.UtilSSLSocketFactory;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.yaml.snakeyaml.Yaml;

@SuppressWarnings("rawtypes")
public class TacoBot extends ListenerAdapter implements Listener {

	public static String CHAN = "#tacos";
	public static String NICK = "TacoBot";
	public static Channel CHAN_OBJ;
	public static final String PREFIX = ".";
	public static final PircBotX bot = new PircBotX();

	public static void main(String[] args) throws Exception {
		// Setup
		CommandProcessor.init();
		bot.setName(NICK);
		// bot.setVerbose(true);
		bot.setVersion("TacoBot v1.2 - For #tacos only!");
		bot.getListenerManager().addListener(new TacoBot());

		// Now connect the magic
		try {
			InputStream input = new FileInputStream(new File("config.yml"));
		    Yaml yaml = new Yaml();
		    Map config = (Map) yaml.load(input);
			boolean ssl = false;

			String server = (String) config.get("server");
			int port = Integer.parseInt((String) config.get("port"));
			String password = (String) config.get("password");
			String channel = (String) config.get("channel");
			ssl = (Boolean) config.get("ssl");

			if (ssl) {
				// TODO: Figure out Java Keystore to only trust bouncer
				// certificate.
				bot.connect(server, port, password,
						new UtilSSLSocketFactory().trustAllCertificates());
			} else {
				bot.connect(server, port, password);
			}
			CHAN = channel;
			CHAN_OBJ = bot.getChannel(channel);
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Connected to Spigot Tacos!");
		}
	}

	private static List<String> msgs = new CopyOnWriteArrayList<String>();

	public static void addMsg(String msg) {
		msgs.add(msg);
		if (msgs.size() == 6) {
			msgs.remove(0);
		}
	}

	public static String[] getMsgs() {
		return msgs.toArray(new String[0]);
	}

	public static void sendMessage(User user, String message) {
		sendMessage(user.getNick(), message);
	}

	public static void sendMessage(String user, String message) {
		sendMessage("(" + user + ") " + message);
	}

	public static void sendMessage(String message) {
		bot.sendMessage(CHAN, message);
	}

	@Override
	public void onMessage(MessageEvent event) throws Exception {
		if (!event.getChannel().getName().equals(CHAN)) {
			return;
		}
		addMsg("<" + event.getUser().getNick() + "> " + event.getMessage());
		CommandProcessor.process(event);
	}

	@Override
	public void onPrivateMessage(PrivateMessageEvent event) {
		if (!event.getUser().getChannelsOpIn().contains(CHAN_OBJ)) {
			event.respond("Sorry, but this bot does not support private messaging capabilities.");
			return;
		}

		CommandProcessor.process(event);
	}
}
