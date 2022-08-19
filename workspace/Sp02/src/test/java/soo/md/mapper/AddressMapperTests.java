package soo.md.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import lombok.extern.log4j.Log4j;
import soo.md.domain.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AddressMapperTests {
	@Autowired
	private AddressMapper addressMapper;
	
	/*
	@Test
	public void testList(){
		log.info("#addressMapper: " + addressMapper);
		List<Address> list = addressMapper.list();
		log.info("#addressMapper list: " + list);
	}*/
	/*
	@Test
	public void testInsert(){
		Address address = new Address(-1L, "스프링2", "복잡하지2", null);
		addressMapper.insert(address);
		log.info("#addressMapper testInsert() 수행성공");
	}*/
	
	@Test
	public void testDelete() {
		long seq = 7;
		addressMapper.delete(seq);
		log.info("#addressMapper testDelete() 수행성공");
	}
}
