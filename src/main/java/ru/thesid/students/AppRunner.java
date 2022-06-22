package ru.thesid.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import ru.thesid.students.domain.MenuItem;
import ru.thesid.students.domain.Student;
import ru.thesid.students.services.StudentService;
import ru.thesid.students.utils.ScreenUtils;
import ru.thesid.students.validators.StudentValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AppRunner implements CommandLineRunner {

    private Scanner sc = new Scanner(System.in).useDelimiter("\n");

    private final int MIN_INDEX_MENU_ITEM = 0;
    private final int MAX_INDEX_MENU_ITEM = 3;

    @Autowired
    StudentService studentService;

    @Override
    public void run(String... args) {
        showMenu();
        sc.close();
    }

    private void showMenu() {
        ScreenUtils.clear();
        System.out.println("Menu");
        System.out.println("1 Student list");
        System.out.println("2 Add student");
        System.out.println("3 Remove student");
        System.out.println("4 EXIT");

        int selectedIndex = sc.nextInt() - 1;
        if (selectedIndex < MIN_INDEX_MENU_ITEM || selectedIndex > MAX_INDEX_MENU_ITEM) {
            showNoMenuItem();
        }
        ScreenUtils.clear();
        MenuItem selectedMenuItem = MenuItem.values()[selectedIndex];
        switch (selectedMenuItem) {
            case GET_ALL:
                showGetAllStudents();
                break;
            case CREATE:
                showCreateStudent();
                break;
            case REMOVE:
                showRemoveStudent();
                break;
            case BACK:
                ScreenUtils.clear();
                break;
        }
    }

    private void showNoMenuItem() {
        ScreenUtils.printMessage("There is no such item in the menu.");
        sc.next();
        ScreenUtils.clear();
        showMenu();
    }

    private void showGetAllStudents() {
        var students = studentService.getAll();
        if (students.size() == 0) {
            showMessage("No records found.");
            backToMenu();
            return;
        }
        printStudents(students);
        backToMenu();
    }

    private void printStudents(List<Student> students) {
        ScreenUtils.clear();
        for (Student student : students) {
            ScreenUtils.printMessage(String.format("%s %s %s", student.getSurname(), student.getName(), student.getPatronymic()));
        }
        sc.next();
    }

    private void showCreateStudent() {
        Student student = new Student();
        enterFio(student);
        enterGroup(student);
        enterBirthday(student);
        var createdStudent = studentService.create(student);
        if (createdStudent != null) {
            showMessage("User added.");
            backToMenu();
        } else {
            showMessage("Error adding a user.");
            sc.next();
        }
    }

    private void showRemoveStudent() {
        ScreenUtils.printMessage("Enter the student ID to delete:");
        while (!sc.hasNextLong()) {
            System.out.println("Invalid input format. Example 12.");
            sc.next();
            ScreenUtils.clear();
        }
        var studentId = sc.nextLong();
        var student = studentService.remove(studentId);
        if (student != null) {
            showMessage("User deleted.");
            backToMenu();
        } else showMessage("Couldn't find the crawler.");
        sc.next();
        showRemoveStudent();
    }

    private void enterFio(Student student) {
        ScreenUtils.clear();
        ScreenUtils.printMessage("Enter your Last Name, First Name and Patronymic (Example Ivanov Ivan Ivanovich):");
        String fio = sc.next();
        if (!StudentValidator.validateFio(fio)) {
            showMessage("Invalid format. Example Ivanov Ivan Ivanovich.");
            enterFio(student);
        } else {
            var fioVals = fio.split(" ");
            student.setSurname(fioVals[0]);
            student.setName(fioVals[1]);
            student.setPatronymic(fioVals[2]);
            ScreenUtils.clear();
        }
    }

    private void showMessage(String message) {
        ScreenUtils.clear();
        ScreenUtils.printMessage(message);
        sc.next();
    }

    private void enterGroup(Student student) {
        ScreenUtils.clear();
        System.out.println("Enter a group:");
        String group = sc.next();
        if (!StudentValidator.validateGroup(group)) {
            showMessage("Invalid group format. The length of the group should not exceed 4 characters.");
            enterGroup(student);
        } else {
            student.setGroupCode(group);
            ScreenUtils.clear();
        }
    }

    private void enterBirthday(Student student) {
        ScreenUtils.clear();
        ScreenUtils.printMessage("Enter the date of birth (Example 01/05/1995):");
        String birthday = sc.next();
        if (!StudentValidator.validateBirthday(birthday)) {
            showMessage("Invalid date of birth format. Example 01/05/1995.");
            enterBirthday(student);
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        student.setBirthday(LocalDate.parse(birthday, formatter));
    }

    private void backToMenu() {
        ScreenUtils.clear();
        showMenu();
    }
}
