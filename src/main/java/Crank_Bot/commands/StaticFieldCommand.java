package Crank_Bot.commands;

import java.sql.Time;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import Crank_Bot.RobotSpeech;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class StaticFieldCommand extends Command {
	
	EventWaiter waiter;
	
	public StaticFieldCommand(EventWaiter waiter) {
		this.name = "static-field";
		this.aliases = new String[]{"silence"};
		this.waiter = waiter;
	}

	@Override
	protected void execute(CommandEvent event) {
		Guild guild = event.getGuild();
		VoiceChannel target = event.getMember().getVoiceState().getChannel();
		//AudioManager manager = guild.getAudioManager();
		/* test */
		List<Member> members = event.getGuild().getVoiceChannelById(target.getId()).getMembers();
		//event.reply("tHy ChAnNEl shAlt bE SiLenceD!");
		for (Member member : members) {
			//event.reply(member.toString());
			member.mute(true).queue();
			
		}
		
		/*
		waiter.waitForEvent(MessageReceivedEvent.class,
				(MessageReceivedEvent e) -> e.getAuthor().equals(event.getAuthor())
					&& e.getChannel().equals(event.getChannel())
					&& !e.getMessage().equals(event.getMessage()),
				e -> e.getAuthor().mute(false).queue(),
				500, TimeUnit.MILLISECONDS,
				members.stream()
					.forEach(member -> member.mute(false).queue())
		);
		*/
		
		
		
	}

}
