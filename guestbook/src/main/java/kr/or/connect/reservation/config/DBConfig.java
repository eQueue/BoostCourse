package kr.or.connect.reservation.config;

import javax.sql.*;

import org.apache.commons.dbcp2.*;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.*;
import org.springframework.transaction.*;
import org.springframework.transaction.annotation.*;

@Configuration//나 설정파일이야
@EnableTransactionManagement //트랜잭션과 관련된설정 자동으로해줌
public class DBConfig implements TransactionManagementConfigurer {
	private String driverClassName = "com.mysql.cj.jdbc.Driver";

	private String url = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false&characterEncoding=utf8";

	private String username = "connectuser";

	private String password = "connect123!@#";

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManger();
	}

	@Bean
	public PlatformTransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource());
	}
}