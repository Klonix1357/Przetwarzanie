import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ParticipantsManager {
    private List<Participant> participants;
    private static final Scanner scanner = new Scanner(System.in);

    public ParticipantsManager() {
        participants = new ArrayList<>();
    }

    public void addParticipants(List<String[]> data) {
        for (String[] row : data) {
            createParticipant(row);
        }
        System.out.println("\nSparsowano dane uczestników");
    }

    private void createParticipant(String[] row) {
        Participant participant = new Participant();

        // sprawdzam czy rekord ma dodatkowe etykiety
        if (row[0].contains("(Niezweryfikowany)")) {
            participant.setVerified(false);
            row[0] = row[0].replace("(Niezweryfikowany)", "");
        }
        if (row[0].contains("(Zewnętrzny)")) {
            participant.setExternal(true);
            row[0] = row[0].replace("(Zewnętrzny)", "");
        }
        if (row[0].contains("(Gość)")) {
            participant.setGuest(true);
            row[0] = row[0].replace("(Gość)", "");
        }

        row[0] = row[0].trim(); // usuwam zbędne spacje na końcu
        // rozdzielam nazwę na imię i nazwisko
        if (row[0].contains(" ")) {            // sprawdzamy czy zawiera spację, co oznacza że imię i nazwisko jest oddzielone nią
            participant.setFirstName(row[0].substring(0, row[0].indexOf(' ')));
            participant.setLastName(row[0].substring(row[0].indexOf(' ') + 1));
        }
        else if (!row[0].isEmpty()) {        // jeśli nie to sprawdzamy czy ma jakiekolwiek dane (np. tylko nazwisko)
            if(isFirstName(row[0])) {
                participant.setFirstName(row[0]);
            } else {
                participant.setLastName(row[0]);
            }
        }

        // czas dołączenia, czas opuszczenia, czas udziału
        participant.setJoinTime(row[1]);
        participant.setLeaveTime(row[2]);
        participant.setDuration(row[3]);

        // email jeśli istnieje
        if (!row[4].isBlank()) {
            participant.setEmail(row[4]);
        }

        // id jeśli istnieje
        if (!row[5].isBlank()) {
            participant.setParticipantID(row[5]);
        }

        // rola w spotkaniu
        participant.setRole(row[6]);

        // zaświadczenie
        if (row[3].contains("godz.")) {
            participant.setCertificate(true);
        }

        participants.add(participant);
    }

    // walidacja imienia (i w razie potrzeby zamiana nazwiska i imienia)
    public void validateParticipants(NameDictionary dictionary) {
        for (Participant p : participants) {
            if (p.getFirstName() != null) {
                if (!dictionary.checkName(p.getFirstName())) {
                    if(shouldSwapFirstNameAndLastName(p.getFirstName(), p.getLastName())) {
                        String temp = p.getFirstName();
                        p.setFirstName(p.getLastName());
                        p.setLastName(temp);
                    }
                }
            }
        }
    }

    // sortowanie alfebatyczne według nazwiska
    public void sortParticipantsByLastName() {
        participants.sort(Comparator.comparing(Participant::getLastName, Comparator.nullsLast(String::compareTo)));
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    private static boolean isFirstName(String name) {
        String wybor;
        System.out.println("\nZnaleziono problem brakujących informacji.");
        while (true) {
            System.out.println("Nazwa uczestnika: " + name);
            System.out.println("Jest to imię czy nazwisko?\n");
            System.out.println("1. Imię");
            System.out.println("2. Nazwisko\n");
            System.out.print("Wybierz opcję: ");
            wybor = scanner.nextLine();

            switch (wybor) {
                case "1": // imię
                    return true;
                case "2": // nazwisko
                    return false;
                default:
                    System.out.println("\nNie ma takiej opcji. Spróbuj ponownie.\n");
            }
        }
    }

    private static boolean shouldSwapFirstNameAndLastName(String firstName, String lastName) {
        String wybor;
        System.out.println("\nZnaleziono możliwy problem danych.");
        while (true) {
            System.out.println("Imię uczestnika: " + firstName);
            System.out.println("Nazwisko uczestnika: " + lastName);
            System.out.println("Czy dane uczestnika są prawidłowe i czy zamienić imię z nazwiskiem?\n");
            System.out.println("1. Są prawidłowe, nie zamieniaj ich");
            System.out.println("2. Nie są prawidłowe, zamień je\n");
            System.out.print("Wybierz opcję: ");
            wybor = scanner.nextLine();

            switch (wybor) {
                case "1":
                    return false;
                case "2":
                    return true;
                default:
                    System.out.println("\nNie ma takiej opcji. Spróbuj ponownie.\n");
            }
        }
    }
}