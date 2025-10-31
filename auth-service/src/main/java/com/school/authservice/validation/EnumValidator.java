package com.school.authservice.validation;

import java.util.Set;

import com.school.authservice.enums.UserRole;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<ValidUserRole, UserRole> {
	private Set<UserRole> acceptedRoles;

	@Override
	public void initialize(ValidUserRole constraint) {
		acceptedRoles = Set.of(constraint.acceptedValues());
	}

	@Override
	public boolean isValid(UserRole value, ConstraintValidatorContext context) {
		return value != null && acceptedRoles.contains(value);
	}
}
