package Crank_Bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class StaticFieldCommand extends Command {
	
	public StaticFieldCommand() {
		this.name = "static-field";
		this.aliases = new String[]{"silence"};
	}

	@Override
	protected void execute(CommandEvent event) {
		Guild guild = event.getGuild();
		VoiceChannel target = event.getMember().getVoiceState().getChannel();
		AudioManager manager = guild.getAudioManager();
		/* test */
	}

}
