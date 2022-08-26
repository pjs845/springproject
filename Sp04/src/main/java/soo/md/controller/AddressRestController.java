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
	public void create(Address address) { //파라미터를 jsObj로 받을 때 
		log.info("#AddressRestController create1() address: " + address);
		service.insertS(address);
	}
	//@GetMapping("create1")일 경우 테스팅
	//http://127.0.0.1:8080/rest_addr/create1?name=가1&addr=나1
	//http://127.0.0.1:8080/rest_addr/create1.json?name=가2&addr=나2
	//http://127.0.0.1:8080/rest_addr/create1.xml?name=가3&addr=나3
	
	@PostMapping("create2")
	public void create2(@RequestBody Address address) { //파라미터를 JSON받을 때 
		log.info("#AddressRestController create2() address: " + address);
		service.insertS(address);
	}
	//http://127.0.0.1:8080/rest_addr/create2  또는 create2.json 또는 create2.xml
	//{"seq":-1, "name":"오유현", "addr":"제주도"}
	//{"name":"이동건", "addr":"인천시"}
	
	
	//(2) Read( select )
	@GetMapping("read")
	public List<Address> read(){
		List<Address> list = service.listS();
		return list;
	}
	//http://127.0.0.1:8080/rest_addr/read 또는 read.xml 
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
	//http://127.0.0.1:8080/rest_addr/read.json?na=홍
	
	
	//아래의 방법도 가능은 하지만.. @GetMapping("read/{seq}")와 공존할 순 없음
	/*@GetMapping("read/{na}") 
	public List<Address> read(@PathVariable String na){
		List<Address> list = service.selectByNameS(na);
		return list;
	}*/
	//http://127.0.0.1:8080/rest_addr/read/홍
	
	//(3) Delete ( delete )
	@DeleteMapping("delete/{seq}")
	public void delete(@PathVariable long seq) {
		service.deleteS(seq);
	}
	//http://127.0.0.1:8080/rest_addr/delete/3
	//http://127.0.0.1:8080/rest_addr/delete/2.json
}


