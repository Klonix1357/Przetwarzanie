public abstract class FileProcessor {
    public final void process() {
        read();
        validate();
        sort();
        write();
    }
    protected abstract void read();
    protected abstract void validate();
    protected abstract void sort();
    protected abstract void write();
}
