package Crank_Bot.commands;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import Crank_Bot.Robot;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class ClearChatCommand extends Command {
	
	private static final Logger logger = LogManager.getLogger(ClearChatCommand.class);
	private static int called = 0;

	public ClearChatCommand() {
		this.name = "clear-chat";
		this.aliases = new String[] {"clean"};
		this.help = "Cleans the chat of commands msgs";
	}
	
	@Override
	protected void execute(CommandEvent event) {
		if (!event.getMember().isOwner()) {
			event.reply(Robot.voice("Sorry you are not permitted to perform such actions!"));
			return;
		}
		TextChannel channel = event.getTextChannel();
		event.reply(Robot.voice("Clearing chat commands & bot messages..."));
		
		String[] args = event.getArgs().length() > 0 ? event.getArgs().split("\\s") : null;
		boolean all = args != null ? args.length > 1 : false;
		List<Message> messages = event.getChannel().getHistory().retrievePast(100).complete();
		for (int i = 0; i < called; i++) {
			messages = event.getChannel().getHistory().retrievePast(100).complete();
		}
		called++;
		ArrayList<Message> unwanted = new ArrayList<>();
		for (Message message : messages) {
			boolean command = message.getContentRaw().matches(event.getClient().getPrefix() + ".*?");
			boolean bot = message.getAuthor().isBot();
			if (all || !message.isPinned()) {
				unwanted.add(message);
			}
			else if (command || bot) {
				unwanted.add(message);
			}
		}
		try {
			event.getChannel().purgeMessages(unwanted);
		} catch (IllegalArgumentException e) {
			event.reply(Robot.voice("Error when trying to delete message..."));
		} catch (Exception e) {
			event.reply(Robot.voice("Exception received when trying to delete...."));
		}
		event.reply(Robot.voice("Chat has been cleared."));
	}
	
}
