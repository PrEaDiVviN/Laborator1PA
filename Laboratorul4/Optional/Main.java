package com.company;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {

    public static void main(String[] args) {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        var students = IntStream.rangeClosed(0, 3).
                mapToObj(i -> new Student(faker.name().fullName(), 17 + i, (i % 2 == 0 ? Sex.MALE : Sex.FEMALE)))
                .toArray(Student[]::new);
        var schools = IntStream.rangeClosed(0, 2).
                mapToObj(i -> new School(faker.name().fullName(), (i < 1 ? 1 : 2)))
                .toArray(School[]::new);

        List<Student> studentList = new ArrayList<>();
        studentList.addAll(Arrays.asList(students));
        Collections.sort(studentList, Student::compareByName);

        List<School> prefListSch = new ArrayList<>();
        Set<School> schoolSet = new TreeSet<>();
        schoolSet.addAll(Arrays.asList(schools));
        Map<Student, List<School>> stdPrefMap = new HashMap<>();
        prefListSch.add(schools[0]);
        prefListSch.add(schools[1]);
        prefListSch.add(schools[2]);
        stdPrefMap.put(students[0], prefListSch);
        prefListSch = new ArrayList<>();
        prefListSch.add(schools[0]);
        prefListSch.add(schools[1]);
        prefListSch.add(schools[2]);
        stdPrefMap.put(students[1], prefListSch);
        prefListSch = new ArrayList<>();
        prefListSch.add(schools[0]);
        prefListSch.add(schools[1]);
        stdPrefMap.put(students[2], prefListSch);
        prefListSch = new ArrayList<>();
        prefListSch.add(schools[0]);
        prefListSch.add(schools[2]);
        stdPrefMap.put(students[3], prefListSch);

        Map<School, List<Student>> schPrefMap = new TreeMap<>();
        /* TreeMap uses the compareTo method to decide key ordering and key equality. */
        List<Student> prefListStd = new ArrayList<>();
        prefListStd.add(students[3]);
        prefListStd.add(students[0]);
        prefListStd.add(students[1]);
        prefListStd.add(students[2]);
        schPrefMap.put(schools[0], prefListStd);
        prefListStd = new ArrayList<>();
        prefListStd.add(students[0]);
        prefListStd.add(students[2]);
        prefListStd.add(students[1]);
        schPrefMap.put(schools[1], prefListStd);
        prefListStd = new ArrayList<>();
        prefListStd.add(students[0]);
        prefListStd.add(students[1]);
        prefListStd.add(students[3]);
        schPrefMap.put(schools[2], prefListStd);
        /*
        for (Student st : students) {
            System.out.println("Studentul " + st.getName() + " cu varsta" + st.getAge() + " si sexul " + st.getSex() + " prefera urmatoarele scoli: ");
            for (School sc : stdPrefMap.get(st)) {
                System.out.print(sc.getName() + " ");
            }
            System.out.println();
        }
        for (School sc : schools) {
            System.out.println("Scoala " + sc.getName() + " are urmatoarele preferinte: ");
            for (Student st : schPrefMap.get(sc))
                System.out.print(st.getName() + " ");
            System.out.println();
        }
        */
        System.out.println("Afisam studentii care gasesc scolile " + schools[1].getName() + " si " + schools[2].getName() + " acceptabile.----------------------------------------------------------------------------");
        List<School> target = Arrays.asList(schools[1], schools[2]);
        List<Student> resultStudents = studentList.stream().filter(std -> stdPrefMap.get(std).containsAll(target)).collect(Collectors.toList());
        resultStudents.stream().forEach(System.out::println);

        System.out.println("Afisam scolile care gasesc studentul " + students[0].getName() + " drept prioritatea lor de top.----------------------------------------------------------------------------");
        Set<School> resultSchools = schoolSet.stream().filter(sch -> (schPrefMap.get(sch).get(0).toString().compareTo(students[0].toString()) == 0)).collect(Collectors.toSet());
        resultSchools.stream().forEach(System.out::println);

        System.out.println("Afisam solutia pentru problema!----------------------------------------------------------------------------");
        Problem problemSAP = new Problem(studentList,schoolSet,schPrefMap,stdPrefMap);
        Algorithm greedy = new greedyAlgorithm();
        Solution solved = greedy.solve(problemSAP);
        System.out.println(solved.toString());

    }

}
