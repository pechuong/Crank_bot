package Crank_Bot;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class OverdriveCommand extends Command {
	
	public OverdriveCommand() {
		this.name = "Overdrive";
		this.aliases = new String[] {"over"};
	}

	@Override
	protected void execute(CommandEvent event) {
		// get author
		Member member = event.getMember();
		VoiceChannel channel = event.getMember().getVoiceState().getChannel();
		AudioManager manager = event.getGuild().getAudioManager();
		manager.openAudioConnection(channel);
	}
	
}
