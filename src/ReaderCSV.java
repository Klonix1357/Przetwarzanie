import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ReaderCSV {
    private static final String fileName = "Zadanie programistyczne pod INF.04 - Raport obecności 17-09-26 (5TP).csv";
    private static final Charset charset = StandardCharsets.UTF_16LE;

    public List<String[]> read() {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(fileName), charset)) {
            String linia = bufferedReader.readLine();

            // szukam drugiej sekcji
            while (linia != null && !(linia.trim().equals("2. Uczestnicy"))) {
                linia = bufferedReader.readLine();
            }
            if (linia == null) {
                System.err.println("W pliku \"" + fileName + "\" nie znaleziono sekcji: \"2. Uczestnicy\"");
                return data;
            }

            // pomijam wiersz z nazwami kolumn
            bufferedReader.readLine();

            while((linia = bufferedReader.readLine()) != null) { // odczyt do końca pliku
                if(linia.isBlank()) { // kiedy linia będzie pusta (akapit pomiędzy sekcjami) to zatrzymuje odczyt
                    break;
                }
                // komórki są odzielone tabulatorami (\t), więc dzielę wiersz według nich
                String[] participantData = linia.split("\t");
                data.add(participantData);
            }
            System.out.println("Odczytano dane z pliku");

        } catch (NoSuchFileException e) {
            System.err.println("Nie znaleziono pliku: " + fileName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Nieoczekiwany błąd.");
            throw new RuntimeException(e);
        }

        return data;
    }
}