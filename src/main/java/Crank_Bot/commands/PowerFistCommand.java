package Crank_Bot.commands;

import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import Crank_Bot.RobotSpeech;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class PowerFistCommand extends Command {

	public PowerFistCommand() {
		this.name = "power-fist";
		this.aliases = new String[]{"fist", "knockup"};
		this.help = "Knocks up the user(s) up one chat";
	}
	
	@Override
	protected void execute(CommandEvent event) {
		//String[] args = event.getArgs().length() > 0 ? event.getArgs().split("\\s") : null;
		if (!event.getMember().getVoiceState().inVoiceChannel()) {
			event.reply(RobotSpeech.robotify("You need to be in a voice channel to use this!"));
			return;
		}
		List<Member> members = event.getMessage().getMentionedMembers();
		if (members.size() < 1) {
			members = null;
		}
		if (members == null) {
			event.reply(RobotSpeech.robotify("Fisting everyone b/c no one was mentioned..."));
			members = event.getGuild().getMembers();
		}
		Guild guild = event.getGuild();
		List<VoiceChannel> voices = event.getGuild().getVoiceChannels();
		VoiceChannel current = event.getMember().getVoiceState().getChannel();
		// Check if they are online and movable
		
		// Get the voice channel to move to
		VoiceChannel target = voices.get(Math.abs(voices.indexOf(current) - 1 + (voices.size())) % (voices.size()));
		for (Member member : members) {
			if (member.getVoiceState().inVoiceChannel()) {
				guild.moveVoiceMember(member, target).queue();
				event.reply(RobotSpeech.robotify("Get fisted, " + member.getAsMention()));
			}
		}
		event.reply(RobotSpeech.robotify("Finished fisting..."));
	}

}
