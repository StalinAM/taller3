package com.programacion.taller3;

import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;

import java.util.concurrent.atomic.AtomicInteger;

class MyStreamingChatResponseHandler implements StreamingChatResponseHandler {
    AtomicInteger count;
    public MyStreamingChatResponseHandler(AtomicInteger count) {
        this.count = count;
    }

    @Override
    public void onPartialResponse(String partialResponse) {
        System.out.print(partialResponse);
        System.out.println();;
    }

    @Override
    public void onCompleteResponse(ChatResponse completeResponse) {
        System.out.println();
        System.out.println("Streaming completed successfully.");
    }

    @Override
    public void onError(Throwable error) {
        error.printStackTrace();
    }
}


public class ChatStreamingModelMain {

    static void main() throws Exception{
        var chatModel = OpenAiStreamingChatModel.builder()
                .baseUrl(AiServiceMain.BASE_URL)
                .modelName(AiServiceMain.MODEL)
                .apiKey(AiServiceMain.API_KEY)
                .logResponses(true)
                .logRequests(true)
                .build();
        var count = new AtomicInteger(1);
        chatModel.chat("Que es java? ", new MyStreamingChatResponseHandler(count));

        while (count.get() > 8){
            Thread.sleep(108);
        }
    }

}
