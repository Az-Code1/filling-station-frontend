package io.aljavap.fillingStation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.aljavap.fillingStation.entity.Address;
import io.aljavap.fillingStation.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    // Create or save an address
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    // Get all addresses
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    // Get address by ID
    public Optional<Address> getAddressById(Long addressId) {
        return addressRepository.findById(addressId);
    }

    // Update an address
    public Address updateAddress(Long addressId, Address addressDetails) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            address.setStreet(addressDetails.getStreet());
            address.setCity(addressDetails.getCity());
            address.setState(addressDetails.getState());
            return addressRepository.save(address);
        }
        return null; // Or throw an exception
    }

    // Delete an address
    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}
