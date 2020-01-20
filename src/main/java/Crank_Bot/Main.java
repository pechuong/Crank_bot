package Crank_Bot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.security.auth.login.LoginException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;

import Crank_Bot.commands.ClearChatCommand;
import Crank_Bot.commands.PadoruCommand;
import Crank_Bot.commands.RocketGrabCommand;
import Crank_Bot.commands.StaticFieldCommand;
import Crank_Bot.commands.UwuCommand;
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
	private static final Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) throws LoginException, IOException {
		logger.info("Hello World");
				
		/*------------------------------------- 
		 * config.txt contains two lines 
		 * - first is bot token
		 * - second is ownerId
		 *------------------------------------*/
        List<String> list = Files.readAllLines(Paths.get("config.txt"));
        String token = list.get(0);
        String ownerId = list.get(1);
        
        // define an eventwaiter, dont forget to add this to the JDABuilder!
        EventWaiter waiter = new EventWaiter();
        
        logger.info("I got this far");
        
        // Create audio player manager
        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
        
        // Make the audio player
        AudioPlayer player = playerManager.createPlayer();
        
        // Have the player listen for events(DJ class)
        DJ dj = new DJ(player);
        player.addListener(dj);
        
        
        /*--------------------------------------------------------- 
         * - define a command client
         *  - set the activity
         *  - set the owner of the bot
         *  - set the emojis for on success, warning, & failure
         *  - set the prefix
         *--------------------------------------------------------*/
        CommandClientBuilder client = new CommandClientBuilder();
        client.setActivity(Activity.watching("my adc die :^)"));
        client.setOwnerId(ownerId);
        client.setEmojis("\uD83D\uDE03", "\uD83D\uDE2E", "\uD83D\uDE26");
        client.setPrefix("~");

        /* Add the commands */
        client.addCommands(
        		new RocketGrabCommand(),
        		new StaticFieldCommand(waiter),
        		new ClearChatCommand(),
        		new PadoruCommand(),
        		new UwuCommand()
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
				.setActivity(Activity.watching("my adc die :-)"))
				.addEventListeners(waiter, client.build())
				.build();
	}
}
