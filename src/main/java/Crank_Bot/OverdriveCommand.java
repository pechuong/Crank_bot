package Crank_Bot;

import java.util.List;

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
		List<VoiceChannel> channels = event.getGuild().getVoiceChannels();
		Member member = event.getMember();
		VoiceChannel channel = member.getVoiceState().getChannel();
		AudioManager manager = event.getGuild().getAudioManager();
		manager.openAudioConnection(channel);
		event.reply(channel.getMembers().toString());
		int index = channels.indexOf(channel);
		int size = channels.size();
		int i = (index - 1 + size) % size;
		event.reply("channel: " + channel);
		while (i != channels.indexOf(channel)) {
			event.reply("Moving to: " + channels.get(i).getName());
			manager.openAudioConnection(channels.get(i));
			event.reply(channels.get(i).getMembers().toString());
			event.reply("in channel: " + channel);
			i = Math.abs((i - 1 + size) % size);
		}
		event.reply("Finished moving");
	}
	
}
