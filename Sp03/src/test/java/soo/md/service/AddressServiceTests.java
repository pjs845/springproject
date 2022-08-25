package soo.md.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import soo.md.domain.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AddressServiceTests {
	@Setter(onMethod_ = {@Autowired})
	private AddressService addressService;
	/*
	@Test
	public void testListS(){
		log.info("#addressService: " + addressService);
		List<Address> list = addressService.listS();
		log.info("#addressService list: " + list);
	}*/
	/*
	@Test
	public void testInsertS(){
		Address address = new Address(-1L, "모델2", "서비스2", null);
		addressService.insertS(address);
		log.info("#addressService testInsertS() 수행성공");
	}*/
	@Test
	public void testDeleteS() {
		long seq = 9;
		addressService.deleteS(seq);
		log.info("#addressService testDeleteS() 수행성공");
	}
}
