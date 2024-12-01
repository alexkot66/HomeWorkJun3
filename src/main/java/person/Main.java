package person;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
Задание 1: Создайте класс Person с полями name и age. Реализуйте сериализацию и десериализацию этого класса в файл.

Задание 2: Используя JPA, создайте базу данных для хранения объектов класса Person.
Реализуйте методы для добавления, обновления и удаления объектов Person.
 */

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

        try(SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .buildSessionFactory()) {

            // Создание сессии
            Session session = sessionFactory.getCurrentSession();

            // Начало транзации
            session.beginTransaction();

            // Создание объекта
            Person person5 = new Person("Глеб", 22);
            Person person6 = new Person("Коля", 24);
            session.save(person5);
            session.save(person6);
            System.out.println("Object person save successfully");

            // Чтение объекта из базы данных
            Person retrievedPerson = session.get(Person.class, person5.getId());
            System.out.println("Object person retrieved successfully");
            System.out.println("Retrieved person object: " + retrievedPerson);

            // Обновление объекта
            retrievedPerson.setName("Николай");
            retrievedPerson.setAge(40);
            session.update(retrievedPerson);
            System.out.println("Object person update successfully");

            // Удаление данных
            //session.delete(retrievedPerson);
            //System.out.println("Object person delete successfully");

            session.getTransaction().commit();


        }
        catch (Exception e){
            e.printStackTrace();
        }

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
