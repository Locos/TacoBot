package net.esper.tacos.ircbot.utils.mcping;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

// Thanks jamietech!
public class MinecraftPing {
	public static MinecraftPingReply getOldPing(final String response,
			final String hostname, final int port) throws IOException {
		final String[] bits = response.split("\u00a7");
		if (bits.length != 3) {
			throw new IOException(
					"Bad message - The OLD ping was not specified properly.");
		}
		return new MinecraftPingReply(hostname, port, bits[0],
				Integer.valueOf(bits[2]), Integer.valueOf(bits[1]));
	}

	public static MinecraftPingReply getPing(final String hostname,
			final int port) throws IOException {
		Socket socket = new Socket();
		socket.connect(new InetSocketAddress(hostname, port), 3000);
		DataInputStream in = new DataInputStream(socket.getInputStream());
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		out.write(new byte[]{
				(byte) 0xFE,
				(byte) 0x01
			});
		if (in.read() != 255) {
			throw new IOException(
					"Bad message - An incorrect packet was received.");
		}
		final short bit = in.readShort();
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bit; ++i) {
			sb.append(in.readChar());
		}
		out.close();
		socket.close();

		final String[] bits = sb.toString().split("\0");
		if (bits.length != 6) {
			return getOldPing(sb.toString(), hostname, port);
		}
		return new MinecraftPingReply(hostname, port, bits[3], bits[1],
				bits[2], Integer.valueOf(bits[5]), Integer.valueOf(bits[4]));
	}
}