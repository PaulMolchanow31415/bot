package edu.libot.components;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand("/start", "start bot"),
            new BotCommand("/all", "print all books"),
            new BotCommand("/find", "find books"),
            new BotCommand("/help", "bot info")
    );


    String HELP_TEXT = "This bot will help to count the number of messages in the chat. " +
            "The following commands are available to you:\n\n" +
            "/start - starting bot\n" +
            "/all - printing all books\n" +
            "/find/{query} - print all books look like query\n" +
            "/help - help commands";
}
