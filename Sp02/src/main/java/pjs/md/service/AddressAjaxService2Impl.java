package pjs.md.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pjs.md.domain.Address;
import pjs.md.mapper.AddressAjaxMapper2;

@Service
public class AddressAjaxService2Impl implements AddressAjaxService2 {
	
	@Autowired
	private AddressAjaxMapper2 addressAjaxMapper2;
	@Override
	public List<Address> listS() {
		return addressAjaxMapper2.list();
	}

	@Override
	public void insertS(Address address) {
		addressAjaxMapper2.insert(address);

	}

	@Override
	public void deleteS(long seq) {
		addressAjaxMapper2.delete(seq);

	}

	@Override
	public Address selectBySeqS(long seq) {
		return addressAjaxMapper2.selectBySeq(seq);
	}

	@Override
	public List<Address> selectByNameS(String name) {
		return addressAjaxMapper2.selectByName(name);
	}

}
