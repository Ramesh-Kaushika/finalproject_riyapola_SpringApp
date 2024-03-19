package lk.project.riyapola.repo;

import lk.project.riyapola.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MaintenanceRepo extends JpaRepository<Maintenance, Integer > {


}
