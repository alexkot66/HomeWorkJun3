package person;

import java.io.IOException;
import java.io.Serializable;

public interface Writable {
    boolean writeToFileObject(Serializable object) throws IOException;
    Object readObjectInFile() throws IOException, ClassNotFoundException;
    void setPath(String path);

}
