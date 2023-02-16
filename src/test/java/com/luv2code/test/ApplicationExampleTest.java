package com.luv2code.test;
import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ApplicationExampleTest {
    private static int count=0;
    @Value("${info.school.name}")
    private String schoolName;

    @Value("${info.app.name}")
    private String appInfo;

    @Value("${info.app.description}")
    private String appDescription;

    @Value("${info.app.version}")
    private String appVersion;

    @Autowired
    CollegeStudent student;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    public  void  beforeEach(){
        count=count+1;
        System.out.println("Testing"+ appInfo + "which is" + appDescription + "version" +
                appVersion+"test method"+count );

        student.setFirstname("ahmed");
        student.setLastname("omar");
        student.setEmailAddress("ahmedomar1997.aoo@gmail.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0,80.00,50.50,91.75)));
        student.setStudentGrades(studentGrades);

    }


    @Test
    void  basicTest(){
    }
    @Test
    @DisplayName("add grades result")
    void  addGradeResult(){
        assertEquals(322.25,studentGrades.addGradeResultsForSingleClass(
                student.getStudentGrades().getMathGradeResults()));
    }

    @Test
    @DisplayName("add grades result NOt Equal")
    void  addGradeResultNOtEqual(){
        assertNotEquals(0,studentGrades.addGradeResultsForSingleClass(
                student.getStudentGrades().getMathGradeResults()));
    }

    @Test
    @DisplayName("is grade greater")
    void  isGradeStudentGrade(){
        assertTrue(studentGrades.isGradeGreater(90,75),
                "failure should be true");
    }

    @Test
    @DisplayName("is grade greater false")
    void  isGradeStudentGradeFalse(){
        assertFalse(studentGrades.isGradeGreater(89,92),
                "failure should be false");
    }
    @Test
    @DisplayName("check null student grade ")
    void  checkNullStudentGrade(){
        assertNotNull(studentGrades.checkNull(student.getStudentGrades().getMathGradeResults()),
                "object should not be null");
    }

    @Test
    @DisplayName("create student without grade init ")
    void  createStudentWithOutGradeInit(){
        CollegeStudent studentTwo=context.getBean("collegeStudent",CollegeStudent.class);
        studentTwo.setFirstname("mohamed");
        studentTwo.setLastname("ali");
        studentTwo.setEmailAddress("ahmedomar1998.aoo@gmail.com");
        assertNotNull(studentTwo.getFirstname());
        assertNotNull(studentTwo.getLastname());
        assertNotNull(studentTwo.getEmailAddress());
        assertNull(studentGrades.checkNull(studentTwo.getStudentGrades()));

    }

    @Test
    @DisplayName("verify  student are prototypes ")
    void  verifyStudentArePrototypes(){
        CollegeStudent studentTwo=context.getBean("collegeStudent",CollegeStudent.class);
                assertNotSame(student,studentTwo);
    }

}
