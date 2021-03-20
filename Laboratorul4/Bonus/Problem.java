package com.company;

import java.util.*;

public class Problem {
    private List<Student> studentList;
    private Set<School> schoolSet;
    private Map<School, List<Student>> schPrefMap;
    private Map<Student, List<School>> stdPrefMap;

    public Map<Student, List<School>> getStdPrefMap() {
        return stdPrefMap;
    }

    public void setStdPrefMap(Map<Student, List<School>> stdPrefMap) {
        this.stdPrefMap = stdPrefMap;
    }

    public Map<School, List<Student>> getSchPrefMap() {
        return schPrefMap;
    }

    public void setSchPrefMap(Map<School, List<Student>> schPrefMap) {
        this.schPrefMap =  schPrefMap;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Set<School> getSchoolSet() {
        return schoolSet;
    }

    public void setSchoolSet(Set<School> schoolSet) {
        this.schoolSet = schoolSet;
    }

    public Problem(List<Student> studentList, Set<School> schoolSet, Map<School, List<Student>> schPrefMap, Map<Student, List<School>> stdPrefMap) {
        this.studentList = new ArrayList<>(studentList);
        this.schoolSet = new TreeSet<>(schoolSet);
        this.schPrefMap = new TreeMap<>(schPrefMap);
        this.stdPrefMap = new HashMap<>(stdPrefMap);
    }
    public Problem() {}
}
