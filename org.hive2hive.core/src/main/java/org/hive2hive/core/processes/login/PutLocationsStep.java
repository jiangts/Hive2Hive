package org.hive2hive.core.processes.login;

import org.hive2hive.core.H2HConstants;
import org.hive2hive.core.exceptions.PutFailedException;
import org.hive2hive.core.model.versioned.Locations;
import org.hive2hive.core.network.data.DataManager;
import org.hive2hive.core.processes.common.base.BasePutProcessStep;
import org.hive2hive.core.processes.context.LoginProcessContext;
import org.hive2hive.processframework.exceptions.InvalidProcessStateException;
import org.hive2hive.processframework.exceptions.ProcessExecutionException;

/**
 * @author Seppi
 */
public class PutLocationsStep extends BasePutProcessStep {

	private final LoginProcessContext context;

	public PutLocationsStep(LoginProcessContext context, DataManager dataManager) {
		super(dataManager);
		this.setName(getClass().getName());
		this.context = context;
	}

	@Override
	protected Void doExecute() throws InvalidProcessStateException, ProcessExecutionException {
		try {
			Locations locations = context.consumeLocations();
			locations.generateVersionKey();
			put(context.consumeUserId(), H2HConstants.USER_LOCATIONS, locations,
					context.consumeUserLocationsProtectionKeys());
		} catch (PutFailedException ex) {
			throw new ProcessExecutionException(this, ex);
		}
		return null;
	}

}