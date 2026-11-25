package com.programacion.taller3;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;

import java.util.Scanner;

@SystemMessage("Responde una linea maximo 10 palabras")
interface MiChat {
    String chat(String mensaje);
}
public class ChatMemoryMain {

    static void main() {

        var chatModel = OpenAiChatModel.builder()
                .baseUrl(AiServiceMain.BASE_URL)
                .modelName(AiServiceMain.MODEL)
                .apiKey(AiServiceMain.API_KEY)
                .logResponses(true)
                .logRequests(true)
                .build();

        ChatMemory memory = MessageWindowChatMemory.builder()
                .maxMessages(10)
                .build();

        var chat = AiServices.builder(MiChat.class)
                .chatModel(chatModel)
                .chatMemory(memory)
                .build();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Mensaje: ");
            String mensaje = sc.nextLine();

            if (mensaje.equalsIgnoreCase("salir")) {
                System.out.println("Chat finalizado.");
                break;
            }

            String respuesta = chat.chat(mensaje);

            System.out.println("IA: " + respuesta);
        }

        sc.close();
    }
}
