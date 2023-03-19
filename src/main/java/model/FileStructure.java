package model;

public class FileStructure {
    private String name;
    private boolean isDirectory;
    private long length;
    private long lastModified;

    public FileStructure(String name, boolean isDirectory, long length, long lastModified) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.length = length;
        this.lastModified = lastModified;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public String getName() {
        return name;
    }

    public long getLength() {
        return length;
    }

    public long getLastModified() {
        return lastModified;
    }
}
