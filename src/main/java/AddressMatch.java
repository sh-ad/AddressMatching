import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class AddressMatch {
    public static void main(String[] args) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get("src/main/resources/rawAddresses.txt"))) {
            stream.forEach(s -> System.out.println(preprocess(s)));
        }
    }

    private static String preprocess(String raw){
        // приводим к нижнему регистру
        raw = raw.toLowerCase(Locale.ROOT);

        //TODO вынести в отдельный класс
        //приводим к единому формату терм
        // набор преобразований
        Map<String,String> terms = new HashMap<>();
        terms.put("область", "обл");
        terms.put("город", "г");
        terms.put("улица", "у");
        terms.put("дом", "д");
        terms.put("строение", "стр");
        terms.put("корпус", "к");
        terms.put("квартира", "кв");

        String patternString = "%(" + StringUtils.join(terms.keySet()) + ")%";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(raw);

        StringBuilder sb = new StringBuilder();
        while(matcher.find()) {
            matcher.appendReplacement(sb, terms.get(matcher.group(1)));
        }
        matcher.appendTail(sb);

        return sb.toString();
    }
}
