package Crank_Bot.commands;

import java.util.List;
import java.util.Timer;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class StaticFieldCommand extends Command {
	
	EventWaiter waiter;
	
	public StaticFieldCommand() {
		this.name = "static-field";
		this.aliases = new String[]{"silence"};
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
			member.mute(true);
		}
		/*
		try {
			this.wait(3);
		} catch (InterruptedException e) {
			event.reply("UnsIleNciNG comMEmoRaTinG...");
		}
		for (Member member : members) {
			member.mute(false);
		}
		*/
	}

}
