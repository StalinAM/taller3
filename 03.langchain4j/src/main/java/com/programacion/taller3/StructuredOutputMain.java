package com.programacion.taller3;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

record Persona(String nombre, Integer edad) { }

interface Extractor {
    @UserMessage("Extrae la informacion de contacto del siguiente texto: {{texto}}")
    Persona extraerInformacionPersona (@V("texto") String texto);
}


public class StructuredOutputMain {
    static void main() {

        var chatModel = OpenAiChatModel.builder()
                .baseUrl(AiServiceMain.BASE_URL)
                .modelName(AiServiceMain.MODEL)
                .apiKey(AiServiceMain.API_KEY)
                .logResponses(true)
                .logRequests(true)
                .build();
        var extractor = AiServices.builder(Extractor.class)
                .chatModel(chatModel)
                .build();
        var texto = """
                Hola mi nombre es Juan y tengo 20 años
                """;
        var persona = extractor.extraerInformacionPersona(texto);
        System.out.println(persona);
    }
}
