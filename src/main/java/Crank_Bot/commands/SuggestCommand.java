package Crank_Bot.commands;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;

import com.google.common.io.CharSink;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import Crank_Bot.RobotSpeech;

public class SuggestCommand extends Command {
	
	public SuggestCommand() {
		this.name = "suggest";
		this.aliases = new String[]{"sugg"};
		this.help = "Suggest a command (~suggest [name] [what is does]";
	}

	@Override
	protected void execute(CommandEvent event) {
		String[] args = event.getArgs().length() > 0 ? event.getArgs().split("\\s") : null;
		if (args == null) {
			event.reply(RobotSpeech.robotify("Please suggest both a name and what it does(separate these by comma"));
			return;
		}
		try {
			StringBuilder builder = new StringBuilder();
			CharSink sink = Files.asCharSink(Paths.get("src/main/java/Crank_Bot/commands/suggestion.txt").toFile(), Charset.forName("UTF8"), FileWriteMode.APPEND);
			for (String stuff : args) {
				builder.append(stuff + " ");
			}
			sink.write(builder.toString() + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
		event.reply(RobotSpeech.robotify("Noted..."));
	}

}
