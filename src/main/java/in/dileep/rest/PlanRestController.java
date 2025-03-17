package in.dileep.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.dileep.constants.AppConstants;
import in.dileep.entity.Plan;
import in.dileep.props.AppProperties;
import in.dileep.service.PlanService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(AppConstants.PLANS)
@RestController
public class PlanRestController {

	private PlanService planService;

	private Map<String, String> messages;

	// @Autowired // Optional
	public PlanRestController(AppProperties appProps, PlanService planService) {

		this.planService = planService;
		this.messages = appProps.getMessages();
		System.out.print(this.messages);
		log.info("Loaded messages: {}", messages);
	}

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {

		Map<Integer, String> categories = planService.getPlanCategories();

		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
		String responseMsg = AppConstants.EMPTY_STR;

		boolean savedPlan = planService.upsertPlan(plan);

		if (savedPlan) {
			responseMsg = messages.get("planSaveSucc"); // ✅ Fetching correct key
		} else {
			responseMsg = messages.get("planSavedFail"); // ✅ Correct key
		}

		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	}

	@GetMapping(AppConstants.PLANS)
	public ResponseEntity<List<Plan>> plans() {
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}

	@GetMapping("/plan/{planId}	")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {
		Plan plan = planService.getPlanById(planId);
		return new ResponseEntity<>(plan, HttpStatus.OK);

	}

	@DeleteMapping("/plan/{planId}	")
	public ResponseEntity<String> DeletePlan(@PathVariable Integer planId) {
		boolean isDeleted = planService.deletePlanById(planId);
		String msg = AppConstants.EMPTY_STR;
		if (isDeleted) {
			msg = messages.get("planDeletedSucc");
		} else {
			msg = messages.get("planDeletedFail");
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping("/status/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, String status) {
		boolean isStatusChanged = planService.planStatusChange(planId, status);
		String msg = "";
		if (isStatusChanged) {
			msg = "Status is changed";
		} else {
			msg = "Status is not changed";
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
