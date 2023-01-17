package es.codeurjc.daw.library;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {

	private static Logger log = LoggerFactory.getLogger(DatabaseConfig.class);

	@Value("${DATABASE_URL:null}")
	private String databaseUrl;

	@Bean
	@ConditionalOnProperty("DATABASE_URL")
	public DataSource dataSource() throws URISyntaxException {

		log.info("Using database configured in DATABASE_URL=", databaseUrl);
		
		HikariConfig config = new HikariConfig();
		
		URI uri = new URI(databaseUrl);
		
		String url = "jdbc:" + new URI("postgresql", null, uri.getHost(), uri.getPort(), uri.getPath(),
				uri.getQuery(), uri.getFragment()).toString();

		String[] userInfoParts = uri.getUserInfo().split(":");

		String username = userInfoParts[0];
		String password = userInfoParts[1];
		
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);
		config.setAutoCommit(false);
		
		return new HikariDataSource(config);
	}
}
