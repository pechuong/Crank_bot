package Crank_Bot.commands;

import java.util.ArrayList;
import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import Crank_Bot.RobotSpeech;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;

/**
 * Initiates the rocket grab command.
 * 
 * @author Peter Chuong	(pc9673@gmail.com)
 */
public class RocketGrabCommand extends Command {

	public RocketGrabCommand() {
		this.name = "rocket-grab";
		this.aliases = new String[]{"pull"};
		this.help = "Grabs all members in voice chat to specific channel";
	}
	
	@Override
	protected void execute(CommandEvent event) {		
		/* Makes sure that a member is in voice channel to use this */
		if (!event.getMember().getVoiceState().inVoiceChannel()) {
			event.reply(RobotSpeech.robotify("You need to be in a voice channel to use this!"));
			return;
		}
		String[] args = event.getArgs().length() > 0 ? event.getArgs().split("\\s") : null;
		
		/* Get channel to drag to in the guild */
		VoiceChannel location = event.getMember().getVoiceState().getChannel();
		Guild guild = event.getGuild();

		event.getChannel().sendTyping().queue();
		/* User wants to pull everyone */
		if (args == null) {
			event.reply(RobotSpeech.robotify("Okay " + event.getAuthor().getAsMention() + " I will grab them all!"));
			event.reply(RobotSpeech.robotify("Commencing Rocket-Grab on everyone..."));
			pull(location, guild);
			return;
		}
		event.reply(RobotSpeech.robotify("Okay " + event.getAuthor().getAsMention() + " I will grab the requested members!"));
		pull(event, location, guild);
		event.reply(RobotSpeech.robotify("Moving has finished...."));
	}
	
	private void pull(VoiceChannel location, Guild guild) {
		List<VoiceChannel> channels = new ArrayList<>(guild.getVoiceChannels());
		channels.remove(location);
		for (VoiceChannel voice : channels) {
			/* Checks for empty channel */
			if (voice.getMembers() != null) {
				voice.getMembers().stream().forEach(member -> guild.moveVoiceMember(member, location).queue());
			}
			 
		}
	}
	
	private void pull(CommandEvent event, VoiceChannel location, Guild guild) {
		event.getMessage().getMentionedUsers().stream()
		.forEach(
				person -> guild.moveVoiceMember(
						guild.getMember(person), 
						location).queue()
		);
	}

}
