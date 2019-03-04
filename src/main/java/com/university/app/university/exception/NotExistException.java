package com.university.app.university.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class NotExistException extends AbstractThrowableProblem {

	private static final long serialVersionUID = 1L;

	public NotExistException(String entityName) {
		super(ErrorConstants.ENTITY_NOT_FOUND_TYPE, entityName, Status.BAD_REQUEST);
	}

}
