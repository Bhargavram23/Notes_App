package com.ashokIt.model;



import com.ashokIt.model.Enumerations.EnumStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	@NotBlank(message = "Task title cannot be blank")
	String activityTitle;
	@NotBlank(message = "Task message cannot be blank")
	String activityMessage;
	@NotNull(message = "Select any task status")
	@Enumerated(EnumType.STRING)
	EnumStatus.STATUS status;
	@ManyToOne(fetch = FetchType.EAGER)
	MyUser owner;
}
