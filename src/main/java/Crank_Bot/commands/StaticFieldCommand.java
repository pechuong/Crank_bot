package Crank_Bot.commands;

import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class StaticFieldCommand extends Command {
	
	EventWaiter waiter;
	
	public StaticFieldCommand(EventWaiter waiter) {
		this.name = "static-field";
		this.aliases = new String[]{"silence", "static", "shh"};
		this.waiter = waiter;
	}

	@Override
	protected void execute(CommandEvent event) {
		Guild guild = event.getGuild();
		VoiceChannel target = event.getMember().getVoiceState().getChannel();
		List<Member> members = guild.getVoiceChannelById(target.getId()).getMembers();
		for (Member member : members) {
			if (!member.getVoiceState().isMuted()) {
				member.mute(true).queue();
			} else {
				member.mute(false).queue();
			}
		}
	}

}
