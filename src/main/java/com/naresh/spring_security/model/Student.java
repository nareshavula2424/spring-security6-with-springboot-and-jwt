package com.naresh.spring_security.model;

public class Student {
    private Integer studentId;
    private String name;
    private Integer marks;

    public Student(Integer studentId, String name, Integer marks) {
        this.studentId = studentId;
        this.name = name;
        this.marks = marks;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}
