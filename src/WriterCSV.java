import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class WriterCSV {
    private static final String fileName = "5TP_Zadanie-INF04.csv";
    private static final String[] headers = {"Nazwisko", "Imię", "Czas uczestnictwa", "Status zaświadczenia"};
    private static final char separator = ',';
    private List<Participant> participants;

    WriterCSV(List<Participant> participants) {
        this.participants = participants;
    }

    public void write() {
        try (PrintWriter writer = new PrintWriter(fileName)){

            // nagłówek
            StringBuilder header = new StringBuilder();
            for (String element : headers) {
                header.append(element).append(separator);
            }
            header.deleteCharAt(header.length() - 1);
            writer.println(header);

            // dane uczestników
            for (Participant p : participants) {
                String firstName = (p.getFirstName() != null) ? p.getFirstName() : "";
                String lastName = (p.getLastName() != null) ? p.getLastName() : "";
                String duration = (p.getDuration() != null) ? p.getDuration() : "";

                writer.println( lastName + separator +
                                firstName + separator +
                                duration + separator +
                                p.hasCertificate());
            }
            System.out.println("Zapisano przetworzone dane w pliku: " + fileName);

        } catch (IOException e) {
            System.err.println("Nieoczekiwany błąd.");
            throw new RuntimeException(e);
        }
    }
}