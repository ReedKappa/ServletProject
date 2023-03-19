package service;


import model.FileStructure;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryService {
    public List<FileStructure> getList(String path) {
        File filee = new File(path);
        if (!filee.exists()){
            filee.mkdir();
        }
        File[] list = new File(path).listFiles();

        if (list == null || list.length == 0)
            return new ArrayList<>();

        return Stream.of(list)
                .map(file -> new FileStructure(
                        file.getName(),
                        file.isDirectory(),
                        file.length(),
                        file.lastModified()
                ))
                .collect(Collectors.toList());
    }
}
