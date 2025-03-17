package in.dileep.service;

import java.util.List;
import java.util.Map;
import in.dileep.entity.Plan;

public interface PlanService {

	public Map<Integer, String> getPlanCategories();

	public boolean upsertPlan(Plan plan);

	public Plan getPlanById(Integer planId);

	public List< Plan> getAllPlans();

	public boolean deletePlanById(Integer planId);

	public boolean planStatusChange(Integer planId, String status);

	 
}
