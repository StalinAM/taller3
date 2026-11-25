package com.programacion.taller3;

import com.programacion.taller3.utils.AsistenteProgramacion;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import io.github.cdimascio.dotenv.Dotenv;

public class AiServiceMain {
    final static String BASE_URL= "https://api.groq.com/openai/v1";
    final static String API_KEY= loadApiKey();
    final static String MODEL= "qwen/qwen3.8-27b";

    private static String loadApiKey() {
        var apiKey = Dotenv.configure().ignoreIfMissing().load().get("GROQ_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            apiKey = Dotenv.configure().directory("..").ignoreIfMissing().load().get("GROQ_API_KEY");
        }
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "GROQ_API_KEY is missing. Set it in the environment or in the repository-root .env file.");
        }
        return apiKey;
    }

    static void main() {

        var chatModel = OpenAiChatModel.builder()
                .baseUrl(BASE_URL)
                .modelName(MODEL)
                .apiKey(API_KEY)
                .logResponses(true)
                .logRequests(true)
                .build();
        var asistente = AiServices.create(AsistenteProgramacion.class, chatModel);
        var respuesta = asistente.responderPregunta("Que es java");
        System.out.println(respuesta);

        var respuesta2 = asistente.consultar("Que es python?", "Es necesario para graduarse");
        System.out.println(respuesta2);
    }
}
