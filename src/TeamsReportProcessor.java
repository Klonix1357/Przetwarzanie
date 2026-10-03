public class TeamsReportProcessor extends FileProcessor {
    private ReaderCSV readerCSV;
    private ParticipantsManager manager;
    private NameDictionary dictionary;
    private WriterCSV writer;

    TeamsReportProcessor() {
        readerCSV = new ReaderCSV();
        manager = new ParticipantsManager();
        dictionary = new NameDictionary();
    }

    @Override
    protected void read() {
        manager.addParticipants(readerCSV.read());
    }

    @Override
    protected void validate() {
        manager.validateParticipants(dictionary);
    }

    @Override
    protected void sort() {
        manager.sortParticipantsByLastName();
    }

    @Override
    protected void write() {
        writer = new WriterCSV(manager.getParticipants());
        writer.write();
    }
}