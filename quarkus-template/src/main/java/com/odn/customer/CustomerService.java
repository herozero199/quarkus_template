package com.odn.customer;

import com.odn.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.*;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<Customer> findAll() {
        //int x = 10 /0;
        if (true) {
            throw new ServiceException("vi du neu throw thi se nhu the nao");
        } else {
            throw new RuntimeException("vi du neu throw thi se nhu the nao");
        }
        //return this.customerMapper.toDomainList(customerRepository.findAll().list());
    }

    public Optional<Customer> findById(@NonNull Integer customerId) {
        return customerRepository.findByIdOptional(customerId)
                .map(customerMapper::toDomain);
    }

    @Transactional
    public void save(@Valid Customer customer) {
        log.debug("Saving Customer: {}", customer);
        CustomerEntity entity = customerMapper.toEntity(customer);
        customerRepository.persist(entity);
        customerMapper.updateDomainFromEntity(entity, customer);
    }

    @Transactional
    public void update(@Valid Customer customer) {
        log.debug("Updating Customer: {}", customer);
        if (Objects.isNull(customer.getCustomerId())) {
            throw new ServiceException("Customer does not have a customerId");
        }
        CustomerEntity entity = customerRepository.findByIdOptional(customer.getCustomerId())
                .orElseThrow(() -> new ServiceException("No Customer found for customerId[%s]", customer.getCustomerId()));
        customerMapper.updateEntityFromDomain(customer, entity);
        customerRepository.persist(entity);
        customerMapper.updateDomainFromEntity(entity, customer);
    }

    @Transactional
    public Optional<List<Customer>> search(Customer customer, Integer pageStart, Integer pagesSize) {
        log.debug("Search customer: {}", customer);
        String query = "firstname = :firstname and middlename = :middlename and lastname = :lastname and suffix = :suffix and email = :email and phone = :phone";
        Map<String, Object> params = new HashMap<>();
        params.put("firstname", customer.getFirstName());
        params.put("middlename", customer.getMiddleName());
        params.put("lastname", customer.getLastName());
        params.put("suffix", customer.getSuffix());
        params.put("email", customer.getEmail());
        params.put("phone", customer.getPhone());

        return Optional.of(customerRepository.find(query, params).page(pageStart - 1, pagesSize)
                .stream().map(customerMapper::toDomain).toList());
    }
}