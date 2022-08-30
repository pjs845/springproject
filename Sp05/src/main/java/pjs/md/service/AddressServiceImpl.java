package pjs.md.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pjs.md.domain.Address;
import pjs.md.mapper.AddressMapper;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	public List<Address> listS() {
		return addressMapper.list();
	}

	@Override
	public void insertS(Address address) {
		addressMapper.insert(address);
	}

	@Override
	public void deleteS(long seq) {
		addressMapper.delete(seq);
	}

}
