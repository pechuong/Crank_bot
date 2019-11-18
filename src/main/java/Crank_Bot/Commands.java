package Crank_Bot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

// For every commands, remember to extend listener adapter
public class Commands extends ListenerAdapter {
	
	/** 
	 * Handles the actions upon receiving a message from a text channel
	 * 
	 * @param event The received event
	 */
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		/* When a message in sent in a text channel */
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		/* check for args */
		StringBuilder command = new StringBuilder();
		/* regex to pull command */
		Pattern pattern = Pattern.compile("^" + Main.prefix + "(+??)");
		Matcher matcher = pattern.matcher(args[0]);
		command.append(matcher.group());
		
		if (!args[0].startsWith(Main.prefix)) {
			event.getChannel().sendMessage("Invalid Command! (Hint: They start with " + Main.prefix + ".)");
		}
		
		if (args[0].equalsIgnoreCase(Main.prefix + "info")) {
			
			String author = event.getMember().getUser().getName();
			String server = event.getGuild().getName();
			
			/* Makes the bot look like it's typing */
			event.getChannel().sendTyping().queue();
			
			/* Embed = pretty card for info */
			EmbedBuilder info = new EmbedBuilder();
			info.setTitle("Crank Bot Info");
			info.setDescription("Surprise Surprise it's info about the bot, who would've thought?");
			//info.addField("Creator", "pechuong", false);
			info.setColor(0xf45642);
		
			info.addField("Server", server, true); 
			info.addField("Author", author, true);
			info.setFooter("Created by " + author, event.getMember().getUser().getAvatarUrl());

			/* info.build() for building embeded card */
			event.getChannel().sendMessage(info.build()).queue();
			info.clear();
		}
	}
	
	
}
