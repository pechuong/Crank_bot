package Crank_Bot.commands;

import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;

public class ClearChat extends Command {

	public ClearChat() {
		this.name = "clear-chat";
		this.aliases = new String[] {"clean"};
		this.help = "Cleans the chat of commands msgs";
	}
	
	@Override
	protected void execute(CommandEvent event) {
		TextChannel channel = event.getTextChannel();
		event.reply("ClEarInG Chat cOmmAnDs & BOt mEssAgeS...");
		
		MessageHistory history = MessageHistory.getHistoryBefore(channel, event.getMessage().getId()).limit(50).complete();
		List<Message> messages = history.getRetrievedHistory();
		for (Message message : messages) {
			boolean command = message.getContentRaw().matches(event.getClient().getPrefix() + ".*?");
			boolean bot = message.getAuthor().isBot();
			//event.reply(message.getContentRaw());
			if (command || bot) {
				message.delete().queue();
			}
		}
		event.reply("ChAt HAs bEeN cLeaRed.");
	}
	
}
