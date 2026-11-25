package com.programacion.taller3.utils;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

@SystemMessage(
"""
Eres un asustente de programacion que responde preguntas de programacion y desarrollo de software
Responde con maximo de 5 lineas
"""
) public interface AsistenteProgramacion {

    String responderPregunta(String pregunta);

    @UserMessage(
            """
            Responde la pregunta {{pregunta}}
            Si no sabes la respuesta utiliza el contexto proporcionado:
            CONTEXTO:
            {{contexto}}
            """
    )
    String consultar(@V("pregunta") String pregunta, @V("contexto") String contexto);
}
