package in.dileep.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.dileep.entity.Plan;

public interface PlanRepo  extends JpaRepository<Plan,Integer>{

}
