package Crank_Bot.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;

public class PadoruCommand extends Command {
	
	public PadoruCommand() {
		this.name = "padoru";
		this.aliases = new String[] {"degenerate"};
		this.help = "a degenerate command that you don't want to use. Rights to use command is limited to daroon";
	}

	@Override
	protected void execute(CommandEvent event) {
		EmbedBuilder em = new EmbedBuilder();
		em.setTitle("PaDoRU SAyS...");
		List<String> list;
        try {
			list = Files.readAllLines(Paths.get("src/main/java/Crank_Bot/commands/padoru_messages.txt"));
        } catch (IOException e) {
			e.printStackTrace();
			System.err.println("Padoru has no reply to give user b/c no padoru messages file :-(");
			return;
		}
        list.remove(0);
        int response = (int) (Math.round(Math.random()*(list.size()-1)));
        em.addField(list.get(response), "", false);
        em.addField("aimed at ", event.getMember().getAsMention(), false);
        em.setFooter("~Padooooruuuuu", "https://i.kym-cdn.com/photos/images/masonry/001/568/913/510.png");
		
		event.reply(em.build());
	}
	
}
