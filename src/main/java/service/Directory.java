package service;


import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Directory {
    public List<FileStructure> getList(String path) {
        return Stream.of(new File(path).listFiles())
                .map(file -> new FileStructure(
                        file.getName(),
                        file.isDirectory(),
                        file.length(),
                        file.lastModified()
                ))
                .collect(Collectors.toList());
    }
}
