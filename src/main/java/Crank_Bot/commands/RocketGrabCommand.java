package Crank_Bot.commands;

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
		
		String[] args = event.getArgs().split("\\s+?");
		//event.reply(Integer.toString(args.length));
		//event.reply("Arg 0 is:[" + args[0] + "]");
		if (!event.getMember().getVoiceState().inVoiceChannel()) {
			event.reply("YOu NeEd tO bE in a vOiCe ChanNel to uSe This!");
			return;
		}
		
		VoiceChannel target = event.getMember().getVoiceState().getChannel();
		Guild guild = event.getGuild();
		Member author = event.getMember();
		event.reply("okAY " + event.getAuthor().getAsMention() + " i WilL GRab tHem AlL!");
		/*
		 * event.reply(Integer.toString(args.length));

		if (args.length == 3) {
			if (!guild.getMemberByTag(args[1]).getVoiceState().inVoiceChannel()) {
				event.reply("User isn't online.... unable to grab :-(");
				return;
			}
			event.reply("GEt ovER hEre");
			guild.moveVoiceMember(guild.getMemberById(args[1]), target).queue();
			return;
		}
		*/
		List<VoiceChannel> channels = event.getGuild().getVoiceChannels();
		
		for (VoiceChannel voice : channels) {
			if (voice.getMembers() != null && !voice.equals(target)) {
				for (Member member : voice.getMembers()) {
					guild.moveVoiceMember(member, target).queue();
				}
			}
			 
		}
			
	}

}
