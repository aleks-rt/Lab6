package application;

import collection.HumanList;
import com.google.gson.JsonParseException;
import commands.*;
import communication.Request;

import communication.Response;
import org.apache.logging.log4j.spi.LoggerContext;
import parser.Parser;

import java.io.*;
import java.net.BindException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Context {
    public String path;

    public int historySize = 7;

    public HandlerClient handlerClient;
    public HandlerCommands handlerCommands;
    public HumanList humanList;

    public boolean processing;
    public boolean completedProcessing;

    public Logger logger;

    public Context() {

        handlerClient = new HandlerClient();
        handlerCommands = new HandlerCommands(this, historySize);

        logger = LogManager.getLogger(Context.class);

        handlerCommands.setCommand(new CommandAdd())
                       .setCommand(new CommandClear())
                       .setCommand(new CommandHelp())
                       .setCommand(new CommandHistory())
                       .setCommand(new CommandInfo())
                       .setCommand(new CommandPrintDescending())
                       .setCommand(new CommandPrintFieldDescendingCar())
                       .setCommand(new CommandPrintUniqueCar())
                       .setCommand(new CommandRemoveById())
                       .setCommand(new CommandRemoveGreater())
                       .setCommand(new CommandRemoveHead())
                       .setCommand(new CommandShow())
                       .setCommand(new CommandUpdateById());
    }

    public void initialization(String[] args) {
        if(args.length != 2) {
            logger.error("Некорректный ввод аргументов программы! Формат: файл порт");
            System.exit(1);
        }

        int port = 0;
        try {
           port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            logger.error("Некорректный ввод порта!");
            System.exit(1);
        }

        path = args[0];
        try {
            String data = HandlerJsonFiles.readFile(path);
            humanList = Parser.deserialize(data);
            if(humanList == null) {
                humanList = new HumanList();
            }
        } catch (JsonParseException | NullPointerException | IllegalArgumentException e) {
            logger.error("Некорректные данные в json файле!");
            System.exit(0);
        } catch (IOException e) {
            logger.error(e.getMessage());
            System.exit(0);
        }
        logger.info("Коллекция заполнена.");

        try {
            handlerClient.bind(port);
            logger.info("Сервер инициализирован.");
        }
        catch (BindException e) {
            logger.error("Этот порт занят! Выберите другой порт и перезапустите программу.");
            System.exit(0);
        }
        catch (IOException e) {
            logger.error("Запустить сервер не удалось: " + e.getMessage());
            System.exit(0);
        }
    }

    private void execution() {
        logger.info("Работа сервера запущенна.");
        processing = true;
        completedProcessing = false;
        while (processing) {
            try {
                ArrayList<Request> requests = handlerClient.receiveRequests(50);
                if(requests.size() != 0) {
                    ArrayList<Response> responses = new ArrayList<>(requests.size());
                    for (Request request : requests) {
                        responses.add(handlerCommands.executeCommand(request));
                    }
                    handlerClient.sendResponses(responses);
                }
            }
            catch (EOFException e) {
                logger.warn("Не удалось принять запрос, т.к. он слишком объемный и не помещается в буффер.");
            }
            catch (ClassNotFoundException | ClassCastException e) {
                logger.warn("Пришел запрос, который не удалось дессериализовать.");
            }
            catch (IOException e) {
                logger.warn("Произошла ошибка при чтении запроса или отправки ответа: " + e.getMessage());
            }
            catch (Exception e) {
                logger.warn(e.getMessage());
            }
        }
        completedProcessing = true;
    }

    public void run() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                processing = false;
                while (!completedProcessing) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    HandlerJsonFiles.saveFile(path, Parser.serialize(humanList));
                    logger.info("Файл успешно перезаписан.");
                } catch (IOException e) {
                    logger.error("Не удалось записать в файл: " + e.getMessage());
                }
                logger.info("Завершение программы.");
            }
        });
        execution();
    }
}
