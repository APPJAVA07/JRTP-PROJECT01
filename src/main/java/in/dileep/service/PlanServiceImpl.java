package in.dileep.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.dileep.entity.Plan;
import in.dileep.entity.PlanCategory;
import in.dileep.repo.PlanCategoryRepo;
import in.dileep.repo.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepo planRepo; // Injecting the plan repository into the plan service
	@Autowired
	private PlanCategoryRepo planCategoryRepo;// Injecting the plan category into the plan service

	@Override
	public Map<Integer, String> getPlanCategories() {
		List<PlanCategory> categories = planCategoryRepo.findAll();
		// Creating a map to store the category ID and name key-value pairs
		Map<Integer, String> categoryMap = new HashMap<>();
		categories.forEach(category -> {
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});
		return categoryMap;
	}

	@Override
	public boolean upsertPlan(Plan plan) {
		Plan savedPlan = planRepo.save(plan);
		// Return true if the plan was successfully saved or updated, else false
		return savedPlan.getPlanId() != null;
	}

	@Override
	public Plan getPlanById(Integer planId) {
		Optional<Plan> getPlanId = planRepo.findById(planId);
		if (getPlanId.isPresent()) {
			return getPlanId.get();
		}
		return null;
	}

	@Override
	public List<Plan> getAllPlans() {
		List<Plan> allPlans = planRepo.findAll();
		return allPlans;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		boolean status = false;
		try {
			planRepo.deleteById(planId);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		Optional<Plan> findById = planRepo.findById(planId);

		if (findById.isPresent()) {
			Plan plan = findById.get();
			plan.setActiveSw(status);
			planRepo.save(plan);
			return true;
		}
		return false;
	}

}
