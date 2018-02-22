/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/rest/EntityResource.java.e.vm
 */
package com.willbe.giftapp.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import com.willbe.giftapp.domain.Passport;
import com.willbe.giftapp.dto.PassportDTO;
import com.willbe.giftapp.dto.PassportDTOService;
import com.willbe.giftapp.dto.support.PageRequestByExample;
import com.willbe.giftapp.dto.support.PageResponse;
import com.willbe.giftapp.repository.PassportRepository;
import com.willbe.giftapp.rest.support.AutoCompleteQuery;

@RestController
@RequestMapping("/api/passports")
public class PassportResource {

    private final Logger log = LoggerFactory.getLogger(PassportResource.class);

    @Inject
    private PassportRepository passportRepository;
    @Inject
    private PassportDTOService passportDTOService;

    /**
     * Create a new Passport.
     */
    @RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PassportDTO> create(@RequestBody PassportDTO passportDTO) throws URISyntaxException {

        log.debug("Create PassportDTO : {}", passportDTO);

        if (passportDTO.isIdSet()) {
            return ResponseEntity.badRequest().header("Failure", "Cannot create Passport with existing ID").body(null);
        }

        PassportDTO result = passportDTOService.save(passportDTO);

        return ResponseEntity.created(new URI("/api/passports/" + result.id)).body(result);
    }

    /**
    * Find by id Passport.
    */
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PassportDTO> findById(@PathVariable Integer id) throws URISyntaxException {

        log.debug("Find by id Passport : {}", id);

        return Optional.ofNullable(passportDTOService.findOne(id)).map(passportDTO -> new ResponseEntity<>(passportDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update Passport.
     */
    @RequestMapping(value = "/", method = PUT, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PassportDTO> update(@RequestBody PassportDTO passportDTO) throws URISyntaxException {

        log.debug("Update PassportDTO : {}", passportDTO);

        if (!passportDTO.isIdSet()) {
            return create(passportDTO);
        }

        PassportDTO result = passportDTOService.save(passportDTO);

        return ResponseEntity.ok().body(result);
    }

    /**
     * Find a Page of Passport using query by example.
     */
    @RequestMapping(value = "/page", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<PassportDTO>> findAll(@RequestBody PageRequestByExample<PassportDTO> prbe) throws URISyntaxException {
        PageResponse<PassportDTO> pageResponse = passportDTOService.findAll(prbe);
        return new ResponseEntity<>(pageResponse, new HttpHeaders(), HttpStatus.OK);
    }

    /**
    * Auto complete support.
    */
    @RequestMapping(value = "/complete", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PassportDTO>> complete(@RequestBody AutoCompleteQuery acq) throws URISyntaxException {

        List<PassportDTO> results = passportDTOService.complete(acq.query, acq.maxResults);

        return new ResponseEntity<>(results, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Delete by id Passport.
     */
    @RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws URISyntaxException {

        log.debug("Delete by id Passport : {}", id);

        try {
            passportRepository.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely org.hibernate.exception.ConstraintViolationException
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}