package com.company;

import java.util.*;

public class GaleShapleyAlgorithm extends Algorithm{
    @Override
    public Solution solve(Problem problem) {
        Map<Student,Boolean> studentAssigned = new TreeMap<>(); ///spune daca un student a fost asignat unei scoli sau nu
        for (Student st: problem.getStudentList())
            studentAssigned.put(st,false);
        Map<School, List<Student>> schoolAcceptance = new TreeMap<>(); ///spune lista unei scoli de studenti acceptati
        for (School sc: problem.getSchoolSet())
            schoolAcceptance.put(sc,new ArrayList<>());

        boolean continua = true;
        while(continua) {
            continua = false; //consideram ca am terminat de asignat studentii
            boolean inloops = true;
            for (Student st : problem.getStudentList())
                if(!studentAssigned.get(st) && inloops)
                    for (School scStWantToGo : problem.getStdPrefMap().get(st))
                        if(problem.getSchPrefMap().get(scStWantToGo).contains(st)) {
                            if (scStWantToGo.getCapacity() > schoolAcceptance.get(scStWantToGo).size()) { //daca capacitatea scolii permite adaugarea studentului, il adaugam
                                schoolAcceptance.get(scStWantToGo).add(st);
                                studentAssigned.replace(st, true);
                                continua = true;
                                inloops = false;
                                break;
                            } else { //trebuie sa verificam daca studentul current st, este mai bun decat ultimul din lista scolii scStWantToGo
                                //aflam pozitia studentului curent in lista de preferinte a scolii
                                int indexStCurrent = -1;
                                int i = 0;
                                for (Student iterator : problem.getSchPrefMap().get(scStWantToGo)) {
                                    if (iterator.getName().compareTo(st.getName()) == 0) {
                                        indexStCurrent = i;
                                    }
                                    i++;
                                }
                                //aflam care este ultimul student din lista
                                Student last = new Student("");
                                for (Student iterator : schoolAcceptance.get(scStWantToGo))
                                    last = iterator;
                                //aflam pozitia ultimului student din lista de preferinte a scolii
                                int indexStUltim = 0;
                                for (Student iterator : problem.getSchPrefMap().get(scStWantToGo)) {
                                    if (iterator.getName().compareTo(last.getName()) == 0)
                                        break;
                                    indexStUltim++;
                                }
                                //verificam daca studentul current (cel ce face cererea are indexul mai mic)
                                if (indexStCurrent < indexStUltim) { //daca da, realizam schimbul intre ei
                                    studentAssigned.replace(st, false, true);
                                    studentAssigned.replace(last, true, false);
                                    schoolAcceptance.get(scStWantToGo).remove(indexStUltim - 1);
                                    schoolAcceptance.get(scStWantToGo).add(st);
                                    continua = true;
                                    inloops = false;
                                    break;
                                }
                            }
                        }
            //sortam listele de acceptare dupa preferintele scolilor
            Map<School, List<Student>> schoolAcceptanceSorted = new TreeMap<>();
            for (School schoolIt: problem.getSchoolSet()) {
                    List<Student> listaActualaSt = new ArrayList<>();
                    ///parcurgem lista de preferinte
                for (Student st: problem.getStudentList())
                    if(problem.getSchPrefMap().get(schoolIt).contains(st))
                        if(schoolAcceptance.get(schoolIt).contains(st))
                            listaActualaSt.add(st);
                schoolAcceptanceSorted.put(schoolIt,listaActualaSt);
            }
            schoolAcceptance = schoolAcceptanceSorted;

            //reactualizam lista cu studentii care deja se afla in lista
            Map<Student,Boolean> studentAssignedRestarted = new TreeMap<>();
            for (Student st: problem.getStudentList()) {
                for (School sc : problem.getSchoolSet())
                    if(schoolAcceptance.get(sc).contains(st))
                        studentAssignedRestarted.put(st,true);
                studentAssignedRestarted.putIfAbsent(st, false);
            }
            studentAssigned = studentAssignedRestarted;
        }

        Solution sol = new Solution();
        for (School sc: problem.getSchoolSet()) {
            sol.getPrintSolution().append("Scoala ");
            sol.getPrintSolution().append(sc.getName());
            sol.getPrintSolution().append(" accepta urmatorii studenti:\n");
            for (Student st : schoolAcceptance.get(sc)) {
                sol.getPrintSolution().append("Studentul ");
                sol.getPrintSolution().append(st.getName());
                sol.getPrintSolution().append(".\n");
            }
        }
        return sol;
    }

}
