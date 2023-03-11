package edu.libot;

import edu.libot.components.BotCommands;
import edu.libot.config.BotConfig;
import edu.libot.response.BookListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.validation.constraints.NotNull;

@Slf4j
@Component
public class LibraryTelegramBot extends TelegramLongPollingBot implements BotCommands {
    final BotConfig config;

    public LibraryTelegramBot(BotConfig config) {
        this.config = config;
        try {
            this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(@NotNull Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String memberName = update.getMessage().getFrom().getFirstName();

            if (messageText.contains("/start")) {
                /* output buttons */
                startBot(chatId, memberName);
            } else if (messageText.contains("/find")) {
                /* post find query and print data */
                String[] params = messageText.split("/");
                String query = params[params.length - 1];
                findBook(chatId, query);
            } else if (messageText.contains("/all")) {
                /* print all books */
                allBook(chatId);
            } else {
                log.info("Unexpected message");
            }
        }
    }

    private void allBook(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        ResponseEntity<BookListResponse> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/api/v1/book/all", BookListResponse.class
        );
        System.out.println(responseEntity.getBody().getData().toString());
        message.setText(responseEntity.getBody().getData().toString());

        try {
            execute(message);
            log.info("Reply recent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }

    }

    private void findBook(long chatId, String query) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        System.out.println(query);
        ResponseEntity<BookListResponse> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/api/v1/book/find/" + query, BookListResponse.class
        );
        System.out.println(responseEntity.getBody().getData().toString());
        message.setText(responseEntity.getBody().getData().toString());

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void startBot(long chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Hi, " + userName + "! I'm a Telegram bot.'" + "\n" +
                "This is my commands: \n" + LIST_OF_COMMANDS);

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendHelpText(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}