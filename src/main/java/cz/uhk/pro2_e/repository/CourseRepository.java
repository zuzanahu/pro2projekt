package cz.uhk.pro2_e.repository;

import cz.uhk.pro2_e.model.Course;
import cz.uhk.pro2_e.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
