package com.programacion.taller3;

import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;

import java.time.LocalDateTime;

class MisHerramientas{
    @Tool("Obtiene la fecha y hora actual")
    public String obtenerFechaHora(){
        return LocalDateTime.now().toString();
    }
}

@SystemMessage("Eres un asistente util, utiliza las herramientas disponibles para responder.")
interface  MiAsistente{
    String chat(String message);
}
public class FunctionCallingMain {

    static void main() {

        var chatModel = OpenAiChatModel.builder()
                .baseUrl(AiServiceMain.BASE_URL)
                .modelName(AiServiceMain.MODEL)
                .apiKey(AiServiceMain.API_KEY)
                .logResponses(true)
                .logRequests(true)
                .build();

        var asistente = AiServices.builder(MiAsistente.class)
                .chatModel(chatModel)
                .tools(new MisHerramientas())
                .build();

        var respuesta = asistente.chat("Que hora es?");
        System.out.println(respuesta);

    }
}
