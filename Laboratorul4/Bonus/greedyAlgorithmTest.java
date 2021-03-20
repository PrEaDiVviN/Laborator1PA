package com.company;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class greedyAlgorithmTest {
    @Test
    static void testFirst() {
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
        Problem problemSAP = new Problem(studentList, schoolSet, schPrefMap, stdPrefMap);
        Algorithm greedy = new greedyAlgorithm();
        Solution solved = greedy.solve(problemSAP);
        assertEquals("""
                Studentul S2 va merge la scoala: H2.
                Studentul S0 va merge la scoala: H1.
                Studentul S5 va merge la scoala: H0.
                Studentul S6 va merge la scoala: H1.
                Studentul S4 va merge la scoala: H2.
                Studentul S1 va merge la scoala: H3.
                Studentul S3 va merge la scoala: H4.
                """, solved.getPrintSolution().toString());
    }

    @Test
    static void testSpeed() {
        //generam random un numar de 10000 student si 100 de scoli si random preferintele lor astfel incat problema sa poate fi rezolvata (fiecare student sa fie atribuit la o scoala)
        Faker faker = new Faker();
        faker.name().fullName();
        var students = IntStream.rangeClosed(0, 100).
                mapToObj(i -> new Student(faker.name().fullName(), 17 + i, (i % 2 == 0 ? Sex.MALE : Sex.FEMALE)))
                .toArray(Student[]::new);
        var schools = IntStream.rangeClosed(0, 100).
                mapToObj(i -> new School(faker.name().fullName(), 0))//capacitate 0 momenta, va fi schimbata in partea de alegere random
                .toArray(School[]::new);

        List<Student> studentList = new ArrayList<>();
        studentList.addAll(Arrays.asList(students));

        Set<School> schoolSet = new TreeSet<>();
        schoolSet.addAll(Arrays.asList(schools));

        Map<Student, List<School>> stdPrefMap = new HashMap<>();

        //selectam pt fiecare student un numar de scoli si le alegem random care dintre ele ii vor fi asignate
        Random rand = new Random();
        for (Student currentStudent : studentList) {
            boolean[] chosen = new boolean[101];
            int numberSchools = 1 + rand.nextInt(100);
            if (numberSchools > 50) {//atunci eliminam scoli
                numberSchools = 100 - numberSchools;
                for (int i = 0; i <= 100; i++)
                    chosen[i] = true;
                while (numberSchools > 0) {
                    int schoolIndex = rand.nextInt(101);
                    if (chosen[schoolIndex]) {
                        chosen[schoolIndex] = false;
                        numberSchools--;
                    }
                }
            } else {
                while (numberSchools > 0) {
                    int schoolIndex = rand.nextInt(101);
                    if (!chosen[schoolIndex]) {
                        chosen[schoolIndex] = true;
                        numberSchools--;
                    }
                }
            }
            List<School> prefListSch = new ArrayList<>();
            for (int i = 0; i <= 100; i++)
                if (chosen[i])
                    prefListSch.add(schools[i]);
            stdPrefMap.put(currentStudent, prefListSch);
        }
        //selectam pt fiecare scoala un numar de studenti si selectam pt fiecare care va fi asignat
        Map<School, List<Student>> schPrefMap = new TreeMap<>();

        boolean[] ales = new boolean[101];//spune daca studentul a fost asignat cel putin unei scoli
        for (School currentSchool : schoolSet) {
            boolean[] chosen = new boolean[101];
            int numberStudent = 1 + rand.nextInt(101);
            currentSchool.setCapacity(numberStudent);
            if (numberStudent > 50) {//atunci eliminam scoli
                numberStudent = 100 - numberStudent;
                for (int i = 0; i <= 100; i++)
                    chosen[i] = true;
                while (numberStudent > 0) {
                    int studentIndex = rand.nextInt(101);
                    if (chosen[studentIndex]) {
                        chosen[studentIndex] = false;
                        numberStudent--;
                    }
                }
            } else {
                while (numberStudent > 0) {
                    int studentIndex = rand.nextInt(101);
                    if (!chosen[studentIndex]) {
                        chosen[studentIndex] = true;
                        numberStudent--;
                    }
                }
            }
            List<Student> prefListStd = new ArrayList<>();
            for (int i = 0; i <= 100; i++)
                if (chosen[i]) {
                    prefListStd.add(students[i]);
                    ales[i] = true; //marcam faptul ca studentul este dorit de cel putin o scoala
                }
            schPrefMap.put(currentSchool, prefListStd);
        }
        //daca avem studenti care nu au fost asignati la nicio scaoala, atunci ei sunt bagati la toate scolile in lista de preferinte
        for (int i = 0; i <= 100; i++)
            if (!ales[i])
                for (int j = 0; j <= 100; j++)
                    schPrefMap.get(schools[j]).add(students[i]);
        //testam viteza de executie a algoritmului
        Problem problemSAP = new Problem(studentList, schoolSet, schPrefMap, stdPrefMap);
        Algorithm greedy = new greedyAlgorithm();
        Solution solved = greedy.solve(problemSAP);
        assertTrue(((greedyAlgorithm) greedy).getExecutionTime() < 5000);
        System.out.println(solved.getPrintSolution());
    }

    public static void main(String[] args) {
        testFirst();
        testSpeed();
    }

}