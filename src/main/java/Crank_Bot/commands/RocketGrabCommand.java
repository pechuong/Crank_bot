package Crank_Bot.commands;

import java.util.ArrayList;
import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
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
		//this.arguments = new Arguments[] {"[member]"};
	}
	
	@Override
	protected void execute(CommandEvent event) {		
		/* Makes sure that a member is in voice channel to use this */
		if (!event.getMember().getVoiceState().inVoiceChannel()) {
			event.reply("YOu NeEd tO bE in a vOiCe ChanNel to uSe This!");
			return;
		}
		
		String[] args = event.getArgs().length() > 0 ? event.getArgs().split("\\s") : null;
		
		/* Get voice channel to drag everyone to */
		VoiceChannel location = event.getMember().getVoiceState().getChannel();
		Guild guild = event.getGuild();
		Member author = event.getMember();

		/* User wants to pull everyone */
		if (args == null) {
			event.getChannel().sendTyping().queue();
			event.reply("okAY " + event.getAuthor().getAsMention() + " i WilL GRab tHem AlL!");
			event.reply("ComMenCiNg rOckeT-grAb On eveRYoNe...");
			pull(event, location, guild);
			return;
		}
		
		List<User> mentions = event.getMessage().getMentionedUsers();
		for (User mentioned : mentions) {
			Member member = guild.getMember(mentioned);
			guild.moveVoiceMember(member, location).queue();
		}
		event.reply("Moving has finished....");
	}
	
	private void pull(CommandEvent event, VoiceChannel location, Guild guild) {
		List<VoiceChannel> channels = new ArrayList<>(guild.getVoiceChannels());
		channels.remove(location);
		
		for (VoiceChannel voice : channels) {
			/* Checks for empty channel */
			if (voice.getMembers() != null) {
				voice.getMembers().stream().forEach(member -> guild.moveVoiceMember(member, location).queue());
			}
			 
		}
	}
	
	private void pull(String[] args) {
		
	}

}
