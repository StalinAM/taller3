package com.programacion.taller3.embeddings;

import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.nio.file.Paths;

public class SimilitudMain {
    static void main() {
        OnnxEmbeddingModel model = new OnnxEmbeddingModel(BusquedaSemanticaMain.PATH_TO_MODEL, BusquedaSemanticaMain.PATH_TO_TOKENIZER, PoolingMode.MEAN);

        var embedding1 = model.embed("Cual es el area de un circulo?").content();
        var embedding2 = model.embed("Cual es el area de un cuadrado?").content();
        var embedding3 = model.embed("Qué hora es?").content();

        //Calcular la similitud entre los embeddings

        double sim12 = CosineSimilarity.between(embedding1, embedding2);
        double sim13 = CosineSimilarity.between(embedding1, embedding3);
        double sim23 = CosineSimilarity.between(embedding2, embedding3);

        System.out.println("Similitud entre embeddings 1 y 2: " +sim12);
        System.out.println("Similitud entre embeddings 1 y 3: " +sim13);
        System.out.println("Similitud entre embeddings 2 y 3: " +sim23);
    }
}
