package com.heavenhr.interview.model;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class JobApplicationListener extends EmptyInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1380559913542226678L;

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		if (entity instanceof JobApplication) {
			for (int i = 0; i < propertyNames.length; i++) {
				if (currentState[i] == null && previousState[i] == null) {
					continue;
				} else {
					if (!currentState[i].equals(previousState[i])) {
						System.out.println(propertyNames[i] + " was changed from " + previousState[i] + " to "
								+ currentState[i] + " for " + id);
					}
				}

			}
		}

		return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
	}

}
