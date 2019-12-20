package Crank_Bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;

public class UwuCommand extends Command {
	
	public UwuCommand() {
		this.name = "uwu";
	}

	@Override
	protected void execute(CommandEvent event) {
		EmbedBuilder emb = new EmbedBuilder();
		User author = event.getAuthor();
		emb.setAuthor(author.getName());
		emb.setColor(Role.DEFAULT_COLOR_RAW);
		emb.addField("UwU", "uwu " + author.getAsMention(), false);
		emb.setImage("https://i.ytimg.com/vi/YWcrfp_dXKM/maxresdefault.jpg");
		emb.setThumbnail("https://i.pinimg.com/originals/e0/48/0b/e0480b38314ba11894c9064af9c3af88.jpg");
		emb.setTitle("uWu", "https://www.youtube.com/watch?v=6QVgynwxGXc");
		event.reply(emb.build());
	}

}
