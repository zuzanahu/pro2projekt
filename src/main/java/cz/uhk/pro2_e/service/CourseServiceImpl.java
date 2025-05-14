package cz.uhk.pro2_e.service;

import cz.uhk.pro2_e.model.Course;
import cz.uhk.pro2_e.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public Course getCourse(long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCourse(long id) {
        courseRepository.deleteById(id);
    }
}
