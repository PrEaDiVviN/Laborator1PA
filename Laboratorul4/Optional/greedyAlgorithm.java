package com.company;

import java.util.Iterator;

public class greedyAlgorithm extends Algorithm {

    private long start; //used for running time
    private long end;
    @Override
    public Solution solve(Problem problem) {
        start = System.currentTimeMillis();
        Solution sol = new Solution();
        /*
        Ideea algoritmului este urmatoarea: se iau scolile la rand(cele cu capacitatea > 0) si se cauta la fiecare pas cea mai mica suma intre indicile preferintelor scolilor
        si indicele preferintelor studentilor. Se repeta acest pas pana cand nu mai exista studenti de repartizat, sau scolile si-au atins capacitatea maxima.
         */

        boolean continua = true;

        while(continua) {
            int schNumber = problem.getSchoolSet().size();
            int stdNumber = problem.getStudentList().size();
            int indiceScoala = -1;
            int indiceStudent = -1;
            int valoareMin = Integer.MAX_VALUE;

            School going = new School("[empty]",0);
            int i = 0;
            for (Iterator<School> it = problem.getSchoolSet().iterator(); it.hasNext(); ) {
             School sch = it.next();
             for (int j = 0; j < stdNumber; j++) {
                 if(problem.getSchPrefMap().get(sch).contains(problem.getStudentList().get(j)) && problem.getStdPrefMap().get(problem.getStudentList().get(j)).contains(sch)) /* verificam ca scoala
                  contine ca preferinta pe student si studentul contine ca preferinta scoala*/
                 {
                     //parcurgem lista de preferinta a scolii pt a afla unde se afla studentul in ea(index)
                     int indexI = 0;
                     for (indexI = 0 ; indexI < problem.getSchPrefMap().get(sch).size(); indexI++)
                         if (problem.getSchPrefMap().get(sch).get(indexI).getName().compareTo(problem.getStudentList().get(j).getName()) == 0)
                             break;
                     //parcurgem lista de preferinta a studentului pt a afla unde se afla scoala in ea(index)
                     int indexJ = 0;
                     for(indexJ = 0; indexJ < problem.getStdPrefMap().get(problem.getStudentList().get(j)).size(); indexJ++)
                         if (problem.getStdPrefMap().get(problem.getStudentList().get(j)).get(indexJ).getName().compareTo(sch.getName()) == 0)
                             break;
                     if (valoareMin > indexI + indexJ) {
                         valoareMin = indexI + indexJ;
                         indiceScoala = i;
                         indiceStudent = j;
                         going = sch;
                     }
                 }

                }
             i++;
            }

            sol.getPrintSolution().append("Studentul ");
            sol.getPrintSolution().append(problem.getStudentList().get(indiceStudent).getName());
            sol.getPrintSolution().append(" va merge la scoala: ");
            sol.getPrintSolution().append(going.getName()); sol.getPrintSolution().append(".\n");
            going.setCapacity(going.getCapacity() - 1);
            // Stergem acel student din mapa tuturor preferintelor scolilor; stergem acea scoala daca are capacitatea 0; stergem acel student

            for (Iterator<School> it = problem.getSchoolSet().iterator(); it.hasNext(); ) {
                School sch = it.next();
                if (problem.getSchPrefMap().get(sch).contains(problem.getStudentList().get(indiceStudent)))
                 problem.getSchPrefMap().get(sch).remove(problem.getStudentList().get(indiceStudent));
            }
            if(going.getCapacity() == 0) {
                for(int index = 0; index < stdNumber ; index ++)
                    if(problem.getStdPrefMap().get(problem.getStudentList().get(index)).contains(going)) {
                        problem.getStdPrefMap().get(problem.getStudentList().get(index)).remove(going);
                    }
                problem.getSchPrefMap().remove(going);
                problem.getSchoolSet().remove(going);
            }
            problem.getStudentList().remove(indiceStudent);
            /* verificam daca capacitatea scolilor = 0 sau mai sunt studenti de trimis */
            continua = false;
            if(problem.getStudentList().size() > 0 && problem.getSchoolSet().stream().filter(e -> e.getCapacity() > 0).count() > 0)
                continua = true;
        }
        end =  System.currentTimeMillis();
        return sol;
    }
    public long getExecutionTime() {
        return end - start;
    }
}
