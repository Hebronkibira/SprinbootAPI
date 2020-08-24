package com.comulynx.wallet.rest.api.controller;

import com.comulynx.wallet.rest.api.exception.ResourceNotFoundException;
import com.comulynx.wallet.rest.api.model.Customer;
import com.comulynx.wallet.rest.api.model.Webuser;
import com.comulynx.wallet.rest.api.repository.CustomerRepository;
import com.comulynx.wallet.rest.api.repository.WebuserRepository;
import com.comulynx.wallet.rest.api.util.AppUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(AppUtils.BASE_URL + "/webusers")
public class WebuserController {

    @Autowired
    private WebuserRepository webuserRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/")
    public List<Webuser> getAllWebusers() {
        return webuserRepository.findAll();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Webuser> getWebuserByEmployeeId(@PathVariable(value = "employeeId") String employeeId)
            throws ResourceNotFoundException {
        Webuser webuser = webuserRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Webuser not found for this id : " + employeeId));
        return ResponseEntity.ok().body(webuser);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createWebuser(@RequestBody Webuser webuser) {
        try {
            if (webuser.getUsername() != null && !webuser.getUsername().isEmpty()) {
                return new ResponseEntity<>("Username cannot be empty", HttpStatus.OK);
            }
            if (webuser.getEmail() != null && !webuser.getEmail().isEmpty()) {
                return new ResponseEntity<>("Email cannot be empty :: ", HttpStatus.OK);
            }
            Optional<Customer> exist = customerRepository.findByCustomerId(webuser.getCustomerId());
            if (exist.isPresent()) {
                return new ResponseEntity<>("A Customer Exists with id :: " + webuser.getCustomerId(), HttpStatus.OK);
            }
            Optional<Webuser> user = webuserRepository.findByEmployeeId(webuser.getEmployeeId());
            if (user.isPresent()) {
                return new ResponseEntity<>("A Employee Exists with employee id :: " + webuser.getEmployeeId(), HttpStatus.OK);
            }
            return ResponseEntity.ok().body(webuserRepository.save(webuser));
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Webuser> updateWebuser(@PathVariable(value = "employeeId") String employeeId,
            @RequestBody Webuser webuserDetails) throws ResourceNotFoundException {
        Webuser webuser = webuserRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Webuser not found for this id :: " + employeeId));

        webuser.setEmail(webuserDetails.getEmail());
        webuser.setLastName(webuserDetails.getLastName());
        webuser.setFirstName(webuserDetails.getFirstName());
        final Webuser updatedWebuser = webuserRepository.save(webuser);
        return ResponseEntity.ok(updatedWebuser);
    }

    @DeleteMapping("/{employeeId}")
    public Map<String, Boolean> deleteWebuser(@PathVariable(value = "employeeId") String employeeId)
            throws ResourceNotFoundException {
        Webuser webuser = webuserRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Webuser not found for this id :: " + employeeId));

        webuserRepository.delete(webuser);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
