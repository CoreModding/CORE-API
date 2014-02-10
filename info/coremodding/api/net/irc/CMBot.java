package info.coremodding.api.net.irc;

import info.coremodding.api.net.http.DownloadUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

/**
 * @author James
 * The bot
 */
public class CMBot extends PircBot {
	
	private List<String> channels;
	
	String _version;
	
	/**
	 * @param srvr The server
	 * @param _channel The channel
	 */
	public CMBot(String srvr, String _channel){
		List<String> channelsx = new ArrayList<>();
		channelsx.add(_channel);
		this.setVerbose(true);
		this.channels = channelsx;
		this.setName("CM_BOT_[" + String.valueOf(new Random().nextInt(99999)) + "]");
		try {
			this.connect(srvr);
		} catch (Exception e) {
			l(e.getMessage());
			e.printStackTrace();
		}
		String version = DownloadUtil.saveUrl("https://raw.github.com/CoreModding/CMIRC/master/currentversion").get(new Integer(0));
		for(String channel : this.channels){
			this.joinChannel(channel);
			this.sendMessage(channel, "I am the CoreModding bot created by zeuslightning125!");
			this.sendMessage(channel, "I provide moderator utilities. Do !help for help.");
			this.sendMessage(channel, "My current version is INC_1.0.1.0. The latest is " + version + ".");
			this.sendMessage(channel, "The latest download can be found at: https://raw.github.com/CoreModding/CMIRC/master/releases/LATEST.jar");
		}
		this._version = version;
	}
	
	@Override
	public void onMessage(String _channel, String sender,
            String login, String hostname, String message) {
		String[] args = message.split(" ");
		boolean flaggedv = false;
		boolean flaggedo = false;
		User u = this.getUser(sender, _channel);
		if(u != null){
			flaggedv = u.hasVoice() || u.isOp();
			flaggedo = u.isOp();
			if (message.startsWith("!help")){
				this.sendMessage(sender, "CMBot help:");
				this.sendMessage(sender, "I am a moderating tool bot!");
				this.sendMessage(sender, "Voiced users are people with staff permissions.");
				this.sendMessage(sender, "My current version is INC_1.0.1.0. The latest is " + this._version + ".");
				this.sendMessage(sender, "The latest download can be found at: https://raw.github.com/CoreModding/CMIRC/master/releases/LATEST.jar");
				this.sendMessage(sender, "Commands:");
				this.sendMessage(sender, "!kb username");
				this.sendMessage(sender, "Bans and kicks a player");
				this.sendMessage(sender, "!k username");
				this.sendMessage(sender, "Kicks a player");
				this.sendMessage(sender, "!ub hostname");
				this.sendMessage(sender, "Unbans a player");
				this.sendMessage(sender, "!m");
				this.sendMessage(sender, "Mutes a player");
				this.sendMessage(sender, "!um");
				this.sendMessage(sender, "Unmutes a player");
				this.sendMessage(sender, "!t");
				this.sendMessage(sender, "Displays a test message");
				this.sendMessage(sender, "!v username");
				this.sendMessage(sender, "Voices a player (ops only)");
				this.sendMessage(sender, "!uv");
				this.sendMessage(sender, "Unvoices a player (ops only)");
				this.sendMessage(sender, "Note:");
				this.sendMessage(sender, "Currently only !t works, but we're working on it!");
				this.sendMessage(sender, "Commands besides the listed coming soon.");
				this.sendMessage(sender, "We also accept requests! Let us know on webchat.esper.net #TheHub");
				this.sendMessage(sender, "Credits:");
				this.sendMessage(sender, "Created by CoreModding (coremodding.info), mainly by zeuslightning125");
			}
			if(flaggedv){
				if (message.startsWith("!kb")) {
					User u2 = this.getUser(args[1], _channel);
					if(u2 != null){
						this.ban(_channel, u2.getNick() + "!*@*");
						this.kick(_channel, u2.getNick(), "");
					}
				}
				if (message.startsWith("!k")) {
					User u2 = this.getUser(args[1], _channel);
					if(u2 != null){
						this.sendMessage(_channel, "/msg ChanServ KICK " + this.channels + " " + u2.getNick() + " kicked by " + sender);
					}
				}
				if (message.startsWith("!ub")) {
					User u2 = this.getUser(args[1], _channel);
					if(u2 != null){
						this.sendMessage(_channel, "/msg ChanServ UNBAN " + this.channels + " " + u2.getNick());
					}
				}
				if (message.startsWith("!m")) {
					User u2 = this.getUser(args[1], _channel);
					if(u2 != null){
						this.sendMessage(_channel, "/msg ChanServ QUIET " + this.channels + " " + u2.getNick());
					}
				}
				if (message.startsWith("!um")) {
					User u2 = this.getUser(args[1], _channel);
					if(u2 != null){
						this.sendMessage(_channel, "/msg ChanServ UNQUIET " + this.channels + " " + u2.getNick());
					}
				}
				if(message.startsWith("!t")){
					this.sendMessage(_channel, "test accepted");
				}
				if(flaggedo){
					if (message.startsWith("!v")) {
						User u2 = this.getUser(args[1], _channel);
						if(u2 != null){
							this.sendMessage(_channel, "/msg ChanServ VOICE " + this.channels + " " + u2.getNick());
						}
					}
					if (message.startsWith("!uv")) {
						User u2 = this.getUser(args[1], _channel);
						if(u2 != null){
							this.sendMessage(_channel, "/msg ChanServ UNVOICE " + this.channels + " " + u2.getNick());
						}
					}
				}
			}
			//if(!sender.equals(this.getNick()))
			//	this.sendMessage(_channel, "[DEBUG] " + sender + " has sent the message " + message);
		}
		else{
			this.sendMessage(_channel, "[ERROR] " + sender + " is null?");
		}
	}
	
	/**
	 * @param sender The sender
	 * @param channel The channel to check
	 * @return The user with the name
	 */
	public User getUser(String sender, String channel){
		for(User u : this.getUsers(channel)){
			if(u.getNick().equals(sender)){
				return u;
			}
		}
		return null;
	}
	
	/**
	 * @param msg The message
	 */
	public static void l(String msg){
		System.out.println(msg);
	}
}
