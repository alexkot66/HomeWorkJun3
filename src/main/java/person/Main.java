package person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static Filehandler filehandler = new Filehandler();

    public static void main(String[] args) {

        Person person1 = new Person("Александр", 27);
        Person person2 = new Person("Петр", 20);




        boolean check = writeToFilePerson(person1);
        System.out.println(check);

        Person person3 = readInFile();
        System.out.println(person3);

        check = writeToFilePerson(person2);
        System.out.println(check);

        Person person4 = readInFile();
        System.out.println(person4);

    }

    public static boolean writeToFilePerson(Person person){
        /**
         * Сериализация объекта Person в файл
         */
        try {
            if (filehandler.writeToFileObject(person)) {
                return true;
            }
        } catch (IOException e)   {
            e.printStackTrace();
        }
        return false;
    }

    public static Person readInFile() {
        /**
         * Десериализация объекта Person из файла
         */
        try  {
            return (Person) filehandler.readObjectInFile();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
