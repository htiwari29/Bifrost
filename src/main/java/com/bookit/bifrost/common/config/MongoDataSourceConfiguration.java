package com.bookit.bifrost.common.config;

import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ConnectionPoolSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = { "com.bookit" })
public class MongoDataSourceConfiguration extends AbstractMongoClientConfiguration {

	public static final Logger log = LoggerFactory.getLogger(MongoDataSourceConfiguration.class);

	private final MongoProperties mongoProperties;

	private final Environment environment;

	public MongoDataSourceConfiguration(MongoProperties mongoProperties, Environment environment) {
		this.mongoProperties = mongoProperties;
		this.environment = environment;
	}

	@Override
	protected String getDatabaseName() {
		return mongoProperties.getDatabase();
	}

	@Override
	public MongoClient mongoClient() {
		return MongoClients.create(
				MongoClientSettings.builder().applyConnectionString(new ConnectionString(mongoProperties.getUri()))
						.applyToConnectionPoolSettings(settings()).build());
	}

	private Block<ConnectionPoolSettings.Builder> settings() {
		// Keep in config file
		// String min =
		// environment.getProperty("threadPool.connectionPool.minimumPoolSize");
		// String max =
		// environment.getProperty("threadPool.connectionPool.maximumPoolSize");

		return builder -> {
			builder.minSize(5);
			builder.maxSize(30);
		};
	}

}
