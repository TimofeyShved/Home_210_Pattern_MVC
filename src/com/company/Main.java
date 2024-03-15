package com.company;

import javax.xml.namespace.QName;

public class Main {

    public static void main(String[] args) {
        // Паттерн (MVC) предпологает работу и запуск через контроллер с разделение реализации
        Controller controller = new Controller();
        controller.execute();
    }
}

// например у нас есть данные студента, возможно на БД или в файле
class Student{
    String name;
    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

// для этого создаём классы для открытия через бд и файл
interface ModelLayer{
    Student getStudent();
}

class DBLayer implements ModelLayer{
    @Override
    public Student getStudent() {
        return new Student("Tom", 28);
    }
}

class FileSystemLayer implements ModelLayer{
    @Override
    public Student getStudent() {
        return new Student("Tom", 28);
    }
}

// так же мы не знаем где будем просматривать данные, в файле или через браузер
// создадим и для них обработчик
interface View {
    void showStudent(Student student);
}

class ConsoleView implements View{
    @Override
    public void showStudent(Student student) {
        System.out.println("Student name: " + student.getName() + " age: " + student.getAge());
    }
}

class HtmlView implements View{
    @Override
    public void showStudent(Student student) {
        System.out.println("<html><body>Student name: " + student.getName() + " age: " + student.getAge() + "</body></html>");
    }
}

// а после укажем контроллер и те параметры через что получим и где будем смотреть данные
class Controller{
    ModelLayer modelLayer = new DBLayer();
    View view = new HtmlView();

    void execute(){
        Student student = modelLayer.getStudent();
        view.showStudent(student);
    }
}