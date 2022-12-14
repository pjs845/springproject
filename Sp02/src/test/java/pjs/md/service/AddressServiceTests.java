package pjs.md.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.extern.log4j.Log4j;
import pjs.md.domain.Address;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AddressServiceTests {
	@Autowired
	private AddressService addressService;
	
	/*
	@Test
	public void testListS() {
		log.info("#addressMapper: " + addressService);
		List<Address> list = addressService.listS();
		log.info("#addressMapper list: " + list);
	}*/
	/*
	@Test
	public void testInsert() {
		Address address = new Address(-1L, "모델", "서비스", null);
		addressService.insertS(address);
		log.info("#addressMapper testInsertS() 수행성공");
	}
	*/
	@Test
	public void testDelete() {
		long seq = 8;
		addressService.deleteS(seq);
		log.info("#addressMapper testDelete() 수행성공");
	}
}
