package in.dileep.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="PLAN_CATEGORY")
public class PlanCategory {
	
	@Id
	@Column(name="CATEGORY_ID")
	private Integer categoryId;

	@Column(name="CATEGORY_NAME")
	private String categoryName;

	@Column(name="ACTIVE_SWITCH")
	private String activeSw;

	@Column(name="CREATED_BY")  // Ensure this matches the database column name
	private String createdBy;

	@Column(name="UPDATED_BY")  // Add this annotation if necessary
	private String updatedBy;

	@Column(name="CREATED_DATE",updatable=false)  // Ensure database column names match
	@CreationTimestamp
	private LocalDate createdDate;

	@Column(name="UPDATED_DATE",insertable=false)
	@UpdateTimestamp
	private LocalDate updatedDate;
}
