import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.stream.Stream;

public class AddressMatch {
    public static void main(String[] args) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get("src/main/resources/rawAddresses.txt"))) {
            stream.forEach(s -> System.out.println(preprocess(s)));
        }
    }

    private static String preprocess(String raw){
        // нижний регистр
        return raw.toLowerCase(Locale.ROOT);
    }
}
