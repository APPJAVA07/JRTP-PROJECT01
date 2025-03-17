package in.dileep.constants;

public class AppConstants {

	// API Response Messages Keys
	public static final String  EMPTY_STR = "";
	public static final String PLAN_SAVE_SUCCESS = "planSaveSucc";
	public static final String PLAN_SAVE_FAIL = "planSavedFail";
	public static final String PLAN_DELETE_SUCCESS = "planDeletedSucc";
	public static final String PLAN_DELETE_FAIL = "planDeletedFail";
	public static final String STATUS_CHANGE_SUCCESS = "statusChangeSuccess";
	public static final String STATUS_CHANGE_FAIL = "statusChangeFail";

	// API Paths
	public static final String  PLANS = "/plans";
	public static final String CATEGORIES = "/categories";
	public static final String STATUS = "/status/{planId}/{status}";
}
