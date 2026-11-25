import com.programacion.taller3.embeddings.BusquedaSemanticaMain;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.model.output.Response;

void main() {
    OnnxEmbeddingModel model = new OnnxEmbeddingModel(BusquedaSemanticaMain.PATH_TO_MODEL, BusquedaSemanticaMain.PATH_TO_TOKENIZER, PoolingMode.MEAN);

    var text = "Hola, como estás?";

    Response<Embedding> response = model.embed(text);
    Embedding embedding = response.content();
    System.out.println("Dimension of the embedding: " + embedding.dimension());
    System.out.println("Embedding vector: " + embedding.vectorAsList());
}