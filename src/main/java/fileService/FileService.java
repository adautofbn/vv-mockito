package fileService;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;

public class FileService {

    private FileService(){}

    private static FileService fileServiceInstance;
    public static FileService getInstance() {
        if(fileServiceInstance == null) {
            fileServiceInstance = new FileService();
        }
        return fileServiceInstance;
    }

    public Boolean checkFileOnRemoteRepository(String fileName) {
        throw new NotImplementedException();
    }

    public File retrieveFileFromRemote(String fileName) {
        throw new NotImplementedException();
    }
}
