import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// klasa do weryfikacji imion z pliku
public class NameDictionary {
    private static final String fileName = "imiona.txt";
    private Set<String> names;

    public NameDictionary() {
        try (Stream<String> lines = Files.lines(Path.of(fileName), StandardCharsets.UTF_8)) {
            names = lines.map(String::trim)
                    .map(String::toUpperCase)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toSet());
        } catch (NoSuchFileException e) {
            System.err.println("Nie znaleziono bazy imion: " + fileName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Nieoczekiwany błąd.");
            throw new RuntimeException(e);
        }
    }

    public boolean checkName(String name) {
        return names.contains(name.toUpperCase());
    }
}