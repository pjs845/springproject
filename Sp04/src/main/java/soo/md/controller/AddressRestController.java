package soo.md.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.log4j.Log4j;
import soo.md.domain.Address;
import soo.md.service.AddressAjaxService;

@Log4j
@RestController
@RequestMapping("rest_addr")
public class AddressRestController { //CRUD 
	@Autowired
	private AddressAjaxService service;
	
	//(1) Create( insert )
	@PostMapping("create1")
	public void create(Address address) { //�Ķ���͸� jsObj�� ���� �� 
		log.info("#AddressRestController create1() address: " + address);
		service.insertS(address);
	}
	//@GetMapping("create1")�� ��� �׽���
	//http://127.0.0.1:8080/rest_addr/create1?name=��1&addr=��1
	//http://127.0.0.1:8080/rest_addr/create1.json?name=��2&addr=��2
	//http://127.0.0.1:8080/rest_addr/create1.xml?name=��3&addr=��3
	
	@PostMapping("create2")
	public void create2(@RequestBody Address address) { //�Ķ���͸� JSON���� �� 
		log.info("#AddressRestController create2() address: " + address);
		service.insertS(address);
	}
	//http://127.0.0.1:8080/rest_addr/create2  �Ǵ� create2.json �Ǵ� create2.xml
	//{"seq":-1, "name":"������", "addr":"���ֵ�"}
	//{"name":"�̵���", "addr":"��õ��"}
	
	
	//(2) Read( select )
	@GetMapping("read")
	public List<Address> read(){
		List<Address> list = service.listS();
		return list;
	}
	//http://127.0.0.1:8080/rest_addr/read �Ǵ� read.xml 
	//http://127.0.0.1:8080/rest_addr/read.json
	
	@GetMapping("read/{seq}")
	public Address read(@PathVariable long seq){
		Address address = service.selectBySeqS(seq);
		return address;
	}
	//http://127.0.0.1:8080/rest_addr/read/2
	//http://127.0.0.1:8080/rest_addr/read/2.xml
	//http://127.0.0.1:8080/rest_addr/read/2.json
	
	@GetMapping(value="read", params = {"na"})
	public List<Address> read(@RequestParam("na") String na){
		List<Address> list = service.selectByNameS(na);
		return list;
	}
	//http://127.0.0.1:8080/rest_addr/read.json?na=ȫ
	
	
	//�Ʒ��� ����� ������ ������.. @GetMapping("read/{seq}")�� ������ �� ����
	/*@GetMapping("read/{na}") 
	public List<Address> read(@PathVariable String na){
		List<Address> list = service.selectByNameS(na);
		return list;
	}*/
	//http://127.0.0.1:8080/rest_addr/read/ȫ
	
	//(3) Delete ( delete )
	@DeleteMapping("delete/{seq}")
	public void delete(@PathVariable long seq) {
		service.deleteS(seq);
	}
	//http://127.0.0.1:8080/rest_addr/delete/3
	//http://127.0.0.1:8080/rest_addr/delete/2.json
}


