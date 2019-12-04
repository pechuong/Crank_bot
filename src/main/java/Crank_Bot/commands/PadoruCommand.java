package Crank_Bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class PadoruCommand extends Command {
	
	public PadoruCommand() {
		this.name = "padoru";
		this.aliases = new String[] {"degenerate"};
		this.help = "a degenerate command that you don't want to use. Rights to use command is limited to daroon";
	}

	@Override
	protected void execute(CommandEvent event) {
		event.reply(event.getMember().getAsMention() + " No.");
	}
	
}
