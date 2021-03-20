package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {

    public static void main(String[] args) {
        testFirst();
        //testSecond();
    }
    static void testFirst() {
        var students = IntStream.rangeClosed(0, 3).
                mapToObj(i -> new Student("S" + i, 17 + i, (i % 2 == 0 ? Sex.MALE : Sex.FEMALE)))
                .toArray(Student[]::new);
        var schools = IntStream.rangeClosed(0, 2).
                mapToObj(i -> new School("H" + i, (i < 1 ? 1 : 2)))
                .toArray(School[]::new);

        List<Student> studentList = new ArrayList<>();
        studentList.addAll(Arrays.asList(students));
        Collections.sort(studentList, Student::compareByName);

        List<School> prefListSch = new ArrayList<>();
        Set<School> schoolSet = new TreeSet<>();
        schoolSet.addAll(Arrays.asList(schools));
        Map<Student, List<School>> stdPrefMap = new TreeMap<>();
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

        System.out.println("Afisam solutia pentru problema!----------------------------------------------------------------------------");
        Problem problemSAP = new Problem(studentList,schoolSet,schPrefMap,stdPrefMap);
        Algorithm galeSharpley = new GaleShapleyAlgorithm();
        Solution solved = galeSharpley.solve(problemSAP);
        System.out.println(solved.toString());
    }

    static void testSecond() {
        var students = IntStream.rangeClosed(0, 6).
                mapToObj(i -> new Student("S" + i, 17 + i, (i % 2 == 0 ? Sex.MALE : Sex.FEMALE)))
                .toArray(Student[]::new);
        var schools = IntStream.rangeClosed(0, 4).
                mapToObj(i -> new School("H" + i, (i < 1 ? 1 : 2)))
                .toArray(School[]::new);

        List<Student> studentList = new ArrayList<>();
        studentList.addAll(Arrays.asList(students));

        Set<School> schoolSet = new TreeSet<>();
        schoolSet.addAll(Arrays.asList(schools));

        Map<Student, List<School>> stdPrefMap = new HashMap<>();
        List<School> prefListSch = new ArrayList<>();
        prefListSch.add(schools[1]);
        prefListSch.add(schools[3]);
        prefListSch.add(schools[4]);
        stdPrefMap.put(students[0], prefListSch);
        prefListSch = new ArrayList<>();
        prefListSch.add(schools[0]);
        prefListSch.add(schools[3]);
        stdPrefMap.put(students[1], prefListSch);
        prefListSch = new ArrayList<>();
        prefListSch.add(schools[2]);
        prefListSch.add(schools[3]);
        stdPrefMap.put(students[2], prefListSch);
        prefListSch = new ArrayList<>();
        prefListSch.add(schools[4]);
        stdPrefMap.put(students[3], prefListSch);
        prefListSch = new ArrayList<>();
        prefListSch.add(schools[0]);
        prefListSch.add(schools[2]);
        prefListSch.add(schools[4]);
        stdPrefMap.put(students[4], prefListSch);
        prefListSch = new ArrayList<>();
        prefListSch.add(schools[3]);
        prefListSch.add(schools[0]);
        prefListSch.add(schools[1]);
        prefListSch.add(schools[2]);
        stdPrefMap.put(students[5], prefListSch);
        prefListSch = new ArrayList<>();
        prefListSch.add(schools[1]);
        prefListSch.add(schools[2]);
        prefListSch.add(schools[3]);
        prefListSch.add(schools[4]);
        stdPrefMap.put(students[6], prefListSch);

        Map<School, List<Student>> schPrefMap = new TreeMap<>();
        /* TreeMap uses the compareTo method to decide key ordering and key equality. */
        List<Student> prefListStd = new ArrayList<>();
        prefListStd.add(students[2]);
        prefListStd.add(students[0]);
        prefListStd.add(students[5]);
        schPrefMap.put(schools[0], prefListStd);
        prefListStd = new ArrayList<>();
        prefListStd.add(students[2]);
        prefListStd.add(students[5]);
        prefListStd.add(students[0]);
        prefListStd.add(students[6]);
        schPrefMap.put(schools[1], prefListStd);
        prefListStd = new ArrayList<>();
        prefListStd.add(students[5]);
        prefListStd.add(students[2]);
        prefListStd.add(students[4]);
        prefListStd.add(students[3]);
        prefListStd.add(students[1]);
        prefListStd.add(students[0]);
        schPrefMap.put(schools[2], prefListStd);
        prefListStd = new ArrayList<>();
        prefListStd.add(students[1]);
        prefListStd.add(students[5]);
        prefListStd.add(students[6]);
        prefListStd.add(students[3]);
        schPrefMap.put(schools[3], prefListStd);
        prefListStd = new ArrayList<>();
        prefListStd.add(students[5]);
        prefListStd.add(students[3]);
        prefListStd.add(students[1]);
        schPrefMap.put(schools[4], prefListStd);
        Problem problemSAP = new Problem(studentList,schoolSet,schPrefMap,stdPrefMap);
        Algorithm galeShapley = new GaleShapleyAlgorithm();
        Solution solved = galeShapley.solve(problemSAP);
        System.out.println(solved.getPrintSolution());
    }
}
