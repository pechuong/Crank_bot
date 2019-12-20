package Crank_Bot.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
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
		
		String[] args = event.getArgs().length() > 0 ? event.getArgs().split("\\s") : null;
		
		/* Makes sure that a member is in voice channel to use this */
		if (!event.getMember().getVoiceState().inVoiceChannel()) {
			event.reply("YOu NeEd tO bE in a vOiCe ChanNel to uSe This!");
			return;
		}
		
		
		/* Get voice channel to drag everyone to */
		VoiceChannel location = event.getMember().getVoiceState().getChannel();
		Guild guild = event.getGuild();
		Member author = event.getMember();

		/* args length will still be 1 even w/ no arg so check for it */
		if (args != null) {
			/* Checks for real no arg */
			if (args[0].equalsIgnoreCase("")) {
				event.reply("okAY " + event.getAuthor().getAsMention() + " i WilL GRab tHem AlL!");
				event.getChannel().sendTyping().queue();
				event.reply("ComMenCiNg rOckeT-grAb On eveRYoNe...");
				pull(event, location, guild);
				return;
			} 
			/* TODO: move outside if statement 
			 * 	- There is also a bug here when getting member by tag
			 * 
			 **/
			else if (!guild.getMemberByTag(args[0]).getVoiceState().inVoiceChannel()) {
				event.reply("User isn't online.... unable to grab :-(");
				return;
			}
			//event.reply("GEt ovER hEre");
			//guild.moveVoiceMember(guild.getMemberById(args[1]), location).queue();
		} else {
			pull(event, location, guild);
		}
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
