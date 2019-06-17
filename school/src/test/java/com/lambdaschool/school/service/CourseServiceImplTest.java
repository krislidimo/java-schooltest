package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplicationTests;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplicationTests.class)
class CourseServiceImplTest {

    @Autowired
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findCourseById() {
        assertEquals(1, courseService.findCourseById(1).getCourseid());
    }

    @Test
    void deleteFound() {
        courseService.delete(1);
        assertEquals(2, courseService.findAll().size());
    }

    @org.junit.Test( expected = EntityNotFoundException.class)
    void deleteNotFound() {
        courseService.delete(1000);
        assertEquals(2, courseService.findAll().size());
    }

    @Test
    void save(){
        Course addCourse = new Course("Java Back End", new Instructor("John"));
        courseService.save(addCourse);

        assertNotNull(addCourse);

        Course foundCourse = courseService.findCourseById(addCourse.getCourseid());
        assertEquals(addCourse.getCoursename(), foundCourse.getCoursename());
    }
}