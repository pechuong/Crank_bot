package Crank_Bot.commands;

import java.util.ArrayList;
import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import Crank_Bot.RobotSpeech;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class ClearChatCommand extends Command {

	public ClearChatCommand() {
		this.name = "clear-chat";
		this.aliases = new String[] {"clean"};
		this.help = "Cleans the chat of commands msgs";
	}
	
	@Override
	protected void execute(CommandEvent event) {		
		if (!event.getMember().isOwner()) {
			event.reply(RobotSpeech.robotify("Sorry you are not permitted to perform such actions!"));
			return;
		}
		
		TextChannel channel = event.getTextChannel();
		event.reply(RobotSpeech.robotify("Clearing chat commands & bot messages..."));
		
		List<Message> messages = event.getChannel().getHistory().retrievePast(100).complete();
		ArrayList<Message> unwanted = new ArrayList<>();
		for (Message message : messages) {
			boolean command = message.getContentRaw().matches(event.getClient().getPrefix() + ".*?");
			boolean bot = message.getAuthor().isBot();
			if (command || bot) {
				unwanted.add(message);
			}
		}
		event.getChannel().purgeMessages(unwanted);
		event.reply(RobotSpeech.robotify("Chat has been cleared."));
	}
	
}
