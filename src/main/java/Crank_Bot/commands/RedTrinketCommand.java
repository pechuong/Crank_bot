package Crank_Bot.commands;

import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.entities.ClientType;
import net.dv8tion.jda.api.entities.Member;

public class RedTrinketCommand extends Command {
	
	public RedTrinketCommand() {
		this.name = "red-trinket";
		this.aliases = new String[]{"pink"};
		this.help = "Tells if the user/users is invis or not";
	}

	@Override
	protected void execute(CommandEvent event) {
		List<Member> members = event.getMessage().getMentionedMembers();
		for (Member m : members) {
			m.getUser().openPrivateChannel();
			event.reply(m.getOnlineStatus(ClientType.DESKTOP).name().equalsIgnoreCase("INVISIBLE") ? "invis" : "no");
		}
	}

}
