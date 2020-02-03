package de.snx.statsapi.mysql;

import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.mysql.jdbc.PreparedStatement;
import de.snx.statsapi.StatsAPI;

public class AsyncHandler {
	
	private ExecutorService executor;

	public AsyncHandler() {
		this.executor = Executors.newCachedThreadPool();
	}

	public ExecutorService getExecutor() {
		return this.executor;
	}

	public void update(final String statement) {
		this.executor.execute(new Runnable() {
			public void run() {
				StatsAPI.getSQLManager().executeUpdate(statement);
			}
		});
	}

	public void update(final PreparedStatement statement) {
		this.executor.execute(new Runnable() {
			public void run() {
				StatsAPI.getSQLManager().executeUpdate(statement);
			}
		});
	}

	public void query(final PreparedStatement statement, final Callback<ResultSet> callback) {
		this.executor.execute(new Runnable() {
			public void run() {
				callback.accept(StatsAPI.getSQLManager().executeQuery(statement));
			}
		});
	}
}