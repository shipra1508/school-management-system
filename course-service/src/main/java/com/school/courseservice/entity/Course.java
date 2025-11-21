package com.school.courseservice.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "courses")
public class Course {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String courseCode;

    @Column(nullable = false)
    private String courseName;
    
    @Column(name = "teacher_id")
    private Long teacherId;
    
    
    @ElementCollection
    @CollectionTable(name = "course_student_ids", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "student_id")
    private Set<Long> studentIds = new HashSet<>();

    
	public Course(Long id, String courseCode, String courseName, Long teacherId, Set<Long> studentIds) {
		super();
		this.id = id;
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.teacherId = teacherId;
		this.studentIds = studentIds;
	}


	public Course() {
				
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}


	public Set<Long> getStudentIds() {
		return studentIds;
	}


	public void setStudentIds(Set<Long> studentIds) {
		this.studentIds = studentIds;
	}

}
