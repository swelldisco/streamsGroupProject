package challenges;


import domain.Student;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentOps {
    //  1. Filter Students by Gender: Write a method to filter a list of students by their gender.

    public static List<Student> filterStudentsByGender(List<Student> students, String gender) {
        return students.stream()
            .filter(s -> s.getGender().equalsIgnoreCase(gender))
            .toList();
    }

    //2. Sort Students by Age: Sort the list of students by their age (based on date of birth).

    public static List<Student> sortStudentsByAge(List<Student> students) {
        return students.stream()
            .sorted((a, b) -> a.getDob().compareTo(b.getDob()))
            .toList();
    }

    //3. Calculate Average Age: Calculate the average age of all students.
    public static double averageStudentAge(List<Student> students) {
        LocalDate today = LocalDate.now();
        students.stream()
            .forEach(a -> {
                int years = Period.between(a.getDob(), today).getYears();
                a.setAge(years);
            });
        return students.stream()
            .map(a -> a.getAge())
            .collect(Collectors.averagingDouble(d -> d.doubleValue()));
    }

    //4. Print Student Names: Print the full names of all students.
    public static void printFullNames(List<Student> students) {
        students.stream()
            .forEach(a -> System.out.println(a.getFirst_name() + " " + a.getLast_name()));
    }

    //5. Group Students by Gender: Group the students by gender.
    public static Map<String, List<Student>> groupStudentsByGender(List<Student> students) {
        return students.stream()
            .collect(Collectors.groupingBy(s -> s.getGender()));
    }

    // 6. Find Maximum Age: Find the maximum age among all students.
    public static int findMaximumAge(List<Student> students) {
        LocalDate today = LocalDate.now();
        students.stream()
            .forEach(s -> {
                int years = Period.between(s.getDob(), today).getYears();
                s.setAge(years);
            });
        Optional<Integer> maxAge = students.stream()
            .map(s -> s.getAge())
            .sorted((b,a) -> a.compareTo(b))
            .findFirst();
        return maxAge.get();
    }

    // 7. Transform to Map: Convert the list of students into a map where the key is the student ID and the value is the student object.
    public static Map<Integer, Student> listToMap(List<Student> students) {
        return students.stream()
            .collect(Collectors.toMap(s -> s.getId(), s -> s));
    }

    // 8. Get Student Emails: Retrieve a list of student emails.
    public static List<String> emailList(List<Student> students) {
        return students.stream()
            .map(s ->s. getEmail())
            .toList();
    }

    // 9. Check if Any Student is Adult: Check if any student is an adult (age 18 or older).
    public static boolean AnyAdultStudents(List<Student> students) {
        LocalDate yearsAgo = LocalDate.now().minusYears(18);
        return students.stream()
            .anyMatch(s -> s.getDob().isBefore(yearsAgo));
    }

    // 10. Count Students by Gender: Count the number of students for each gender.
    public static Map<String, Long> countByGender(List<Student> students) {
        return students.stream()
            .collect(Collectors.groupingBy(s -> s.getGender(), Collectors.counting()));
    }

    // 11. Find Youngest Female Student: Find the youngest female student.
    public static Student youngestFemaleStudent(List<Student> students) {
        Optional<Student> temp = students.stream()
            .filter(s -> s.getGender().equalsIgnoreCase("female"))
            .sorted((a,b) -> a.getDob().compareTo(b.getDob()))
            .findFirst();
        return temp.get();
    }

    // 12. Join Student Names: Join the first names of all students into a single string.
    public static String allTheNames(List<Student> students) {
        StringBuffer sb = new StringBuffer();
        students.stream()
            .map(s -> sb.append(s.getFirst_name() + " "));
        return sb.toString();
    }

    // 13: Calculate Age Sum: Calculate the sum of ages for all students.
    public static int sumAllTheAges(List<Student> students) {
        LocalDate today = LocalDate.now();
        students.stream()
            .forEach(s-> {
                int age = Period.between(s.getDob(), today).getYears();
                s.setAge(age);
            });
        return students.stream()
            .map(s -> s.getAge())
            .collect(Collectors.summingInt(s -> s.intValue()));
    }

    // 14. Check if All Students are Adults: Check if all students are adults (age 18 or older).
    public static boolean areAllStudentsAdults(List<Student> students) {
        LocalDate onceUponATime = LocalDate.now().minusYears(18);
        return students.stream()
            .noneMatch(s -> s.getDob().isAfter(onceUponATime));
    }

    // 15. Find Oldest Student: Find the oldest student.
    public static Student findOldestStudent(List<Student> students) {
        Optional<Student> temp = students.stream()
            .sorted((b,a) -> a.getDob().compareTo(b.getDob()))
            .findFirst();
        return temp.get();
    }

    // 16. Convert to Uppercase: Convert all student first names to uppercase.
    public static List<Student> allNamesToUpperCase(List<Student> students) {
        List<Student> temp = new ArrayList<>();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        students.stream()
            .forEach(s -> {
                temp.add(new Student(s.getId(),s.getFirst_name().toUpperCase(),s.getLast_name(),s.getEmail(),s.getGender(), s.getDob().format(df)));
            });
        return temp;
    }

    // 17. Find Student by ID: Find a student by their ID
    public static Student findStudentById(List<Student> students, int id) {
        Optional<Student> optionalStudent = students.stream()
            .filter(s -> s.getId() == id)
            .findFirst();
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        } else {
            throw new RuntimeException();
        }
    }

    // 18. Compute Age Distribution: Compute the distribution of ages (e.g., count of students for each age).
    public static Map<Integer, Long> ageDistribution(List<Student> students) {
        students.stream()
            .forEach(s -> {
                int age = Period.between(s.getDob(), LocalDate.now()).getYears();
                s.setAge(age);
            });
        return students.stream()
            .sorted((a,b) -> a.getAge() - b.getAge())
            .collect(Collectors.groupingBy(s -> s.getAge(), Collectors.counting()));
    }

    // 19. Group Students by Age: Group the students by their age
    public static Map<Integer, List<Student>> groupStudentsByAge(List<Student> students) {
        students.stream()
            .forEach(s -> {
                int age = Period.between(s.getDob(), LocalDate.now()).getYears();
                s.setAge(age);
            });
        return students.stream()
            .sorted((a,b) -> a.getAge() - b.getAge())
            .collect(Collectors.groupingBy(s -> s.getAge()));
    }

    // 20. Calculate Age Standard Deviation: Calculate the standard deviation of ages for all students.
    public static double getStandardDeviationOfAge(List<Student> students) {
        // far more gradeful solution from Ajmal:
        students.stream()
            .forEach(s -> {
                int age = Period.between(s.getDob(), LocalDate.now()).getYears();
                s.setAge(age);
            });

        double mean = students.stream()
            .mapToDouble(s -> s.getAge())
            .average()
            .orElse(0.0);

        return Math.sqrt(
            students.stream()
                .mapToDouble(s -> Math.pow(s.getAge() - mean, 2))
                .average()
                .orElse(0.0)
        );
        
        // old clunky maybe solution:
        // double[] tempList = students.stream()
        //     .mapToDouble(s -> s.getAge())
        //     .toArray();
            
        // double mean = Arrays.stream(tempList)
        //     .average()
        //     .orElse(0.0);

        // double standardDeviation = 0.00;

        // for (double n : tempList) {
        //     standardDeviation += Math.pow(n - mean, 2);
        // }

        // return Math.sqrt(standardDeviation / length);
    }

}
