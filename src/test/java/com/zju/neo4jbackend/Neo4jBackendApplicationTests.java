package com.zju.neo4jbackend;

import com.zju.neo4jbackend.entity.User;
import com.zju.neo4jbackend.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Neo4jBackendApplicationTests {

	@Test
	public void contextLoads() {
	}

	Logger logger = LoggerFactory.getLogger(Neo4jBackendApplicationTests.class);

	@Autowired
	UserRepository userRepository;

	@Test
	public void createUserNode(){
		System.out.println(userRepository);
		User user = new User();
		user.setPassword("xupan");
		user.setUsername("123");
		User save = userRepository.save(user);
		logger.info(save.toString());
		Assert.assertTrue(save!=null);
	}

	@Test
	public void delAll(){
		userRepository.deleteById(new Long(27));

	}

}

