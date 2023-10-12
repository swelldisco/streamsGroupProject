package data;

import domain.Student;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FetchData.getCarList().stream()
                .limit(5)
                .forEach(System.out::println);
        System.out.println("++++++++++++++++");
        FetchData.getStudentList().stream()
                .limit(5)
                .forEach(System.out::println);

    }

}
