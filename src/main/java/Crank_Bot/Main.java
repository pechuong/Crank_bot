package Crank_Bot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.security.auth.login.LoginException;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import Crank_Bot.commands.RocketGrabCommand;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

/**
 * Starts the bot up (Driver).
 * Template was taken from 
 * @see https://github.com/jagrosh/ExampleBot/blob/master/src/main/java/com/jagrosh/examplebot/ExampleBot.java
 * 
 * @author John Grosh (john.a.grosh@gmail.com)
 * @author Peter Chuong (pc9673@gmail.com)
 */

public class Main {
	public static JDA jda;
	public static String prefix = "~";
	
	public static void main(String[] args) throws LoginException, IOException {
		/* 
		 * config.txt contains two lines 
		 * - first is bot token
		 * - second is ownerId
		 */
        List<String> list = Files.readAllLines(Paths.get("config.txt"));
        String token = list.get(0);
        String ownerId = list.get(1);

        // define an eventwaiter, dont forget to add this to the JDABuilder!
        EventWaiter waiter = new EventWaiter();

        // define a command client
        CommandClientBuilder client = new CommandClientBuilder();
        client.setActivity(Activity.watching("my adc die :^)"));

        // sets the owner of the bot
        client.setOwnerId(ownerId);

        // sets emojis used throughout the bot on successes, warnings, and failures
        client.setEmojis("\uD83D\uDE03", "\uD83D\uDE2E", "\uD83D\uDE26");

        // sets the bot prefix
        client.setPrefix("~");

        // adds commands
        client.addCommands(
        		new RocketGrabCommand()
                // command to show information about the bot
        		/*
                new AboutCommand(Color.BLUE, "an example bot",
                        new String[]{"Cool commands","Nice examples","Lots of fun!"},
                        new Permission[]{Permission.ADMINISTRATOR}),

                // command to show a random cat
                new CatCommand(),

                // command to make a random choice
                new ChooseCommand(),
                
                // command to say hello
                new HelloCommand(waiter),

                // command to check bot latency
                new PingCommand(),

                // command to shut off the bot
                new ShutdownCommand());
                */);
		
		jda = new JDABuilder(AccountType.BOT)
				.setToken(token)
				.setStatus(OnlineStatus.IDLE)
				.setActivity(Activity.watching("my adc die :^)"))
				.addEventListeners(waiter, client.build())
				.build();
	}
}
