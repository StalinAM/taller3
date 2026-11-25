package com.programacion.taller3.embeddings;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BusquedaSemanticaMain {
    public static final Path PATH_TO_MODEL =
            Paths.get("D:\\DEV\\android\\taller3\\02.embeddings\\src\\main\\resources\\modelos\\model.onnx");

    public static final Path PATH_TO_TOKENIZER =
            Paths.get("D:\\DEV\\android\\taller3\\02.embeddings\\src\\main\\resources\\modelos\\tokenizer.json");

    static void main() {
        var documentos = List.of("Java es un lenguaje de programación orientado a objetos",
                "Python es un lenguaje popular en inteligencia artidicial",
                "Los embeddings representan texto con vectores");

        OnnxEmbeddingModel model = new OnnxEmbeddingModel(PATH_TO_MODEL, PATH_TO_TOKENIZER, PoolingMode.MEAN);

        List<Embedding> embeddings = documentos.stream()
                .map(model::embed)
                .map(Response::content)
                .toList();

        var consulta = "programación JAVA";
        var embeddingConsulta = model.embed(consulta).content();

        int index = 0;
        int mejorIndice = 0;
        double mejorScore = -1.0;

        for (var it : embeddings) {
            double score = CosineSimilarity.between(embeddingConsulta, it);
            System.out.printf("Documento %d - Similitud: %.4f\n", index, score);

            if (score > mejorScore) {
                mejorScore = score;
                mejorIndice = index;
            }
        }

        System.out.printf("Mejor resultado: [%d] - %s\n", mejorIndice, documentos.get(mejorIndice));
        System.out.printf("Similitud: %.4f\n", mejorScore);

    }
}

