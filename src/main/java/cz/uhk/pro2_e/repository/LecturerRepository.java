package cz.uhk.pro2_e.repository;

import cz.uhk.pro2_e.model.Lecturer;
import cz.uhk.pro2_e.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

}
