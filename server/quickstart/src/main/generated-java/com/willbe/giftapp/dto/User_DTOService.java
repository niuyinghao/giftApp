/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/dto/EntityDTOService.java.e.vm
 */
package com.willbe.giftapp.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.willbe.giftapp.domain.Passport;
import com.willbe.giftapp.domain.Role;
import com.willbe.giftapp.domain.User_;
import com.willbe.giftapp.domain.User__;
import com.willbe.giftapp.dto.support.PageRequestByExample;
import com.willbe.giftapp.dto.support.PageResponse;
import com.willbe.giftapp.repository.PassportRepository;
import com.willbe.giftapp.repository.RoleRepository;
import com.willbe.giftapp.repository.User_Repository;

/**
 * A simple DTO Facility for User_.
 */
@Service
public class User_DTOService {

    @Inject
    private User_Repository user_Repository;
    @Inject
    private PassportDTOService passportDTOService;
    @Inject
    private PassportRepository passportRepository;
    @Inject
    private RoleDTOService roleDTOService;
    @Inject
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public User_DTO findOne(Integer id) {
        return toDTO(user_Repository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<User_DTO> complete(String query, int maxResults) {
        List<User_> results = user_Repository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageResponse<User_DTO> findAll(PageRequestByExample<User_DTO> req) {
        Example<User_> example = null;
        User_ user_ = toEntity(req.example);

        if (user_ != null) {
            ExampleMatcher matcher = ExampleMatcher.matching() //
                    .withMatcher(User__.login.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(User__.email.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(User__.firstName.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(User__.lastName.getName(), match -> match.ignoreCase().startsWith());

            example = Example.of(user_, matcher);
        }

        Page<User_> page;
        if (example != null) {
            page = user_Repository.findAll(example, req.toPageable());
        } else {
            page = user_Repository.findAll(req.toPageable());
        }

        List<User_DTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public User_DTO save(User_DTO dto) {
        if (dto == null) {
            return null;
        }

        final User_ user_;

        if (dto.isIdSet()) {
            User_ user_Tmp = user_Repository.findOne(dto.id);
            if (user_Tmp != null) {
                user_ = user_Tmp;
            } else {
                user_ = new User_();
                user_.setId(dto.id);
            }
        } else {
            user_ = new User_();
        }

        user_.setLogin(dto.login);

        user_.setPassword(dto.password);

        user_.setEmail(dto.email);

        user_.setIsEnabled(dto.isEnabled);

        user_.setCivility(dto.civility);

        user_.setCountryCode(dto.countryCode);

        user_.setFirstName(dto.firstName);

        user_.setLastName(dto.lastName);

        user_.setCreationDate(dto.creationDate);

        user_.setCreationAuthor(dto.creationAuthor);

        user_.setLastModificationDate(dto.lastModificationDate);

        user_.setLastModificationAuthor(dto.lastModificationAuthor);

        user_.setVersion(dto.version);

        user_.getRoles().clear();
        if (dto.roles != null) {
            dto.roles.stream().forEach(role -> user_.addRole(roleRepository.findOne(role.id)));
        }

        return toDTO(user_Repository.save(user_));
    }

    /**
     * Converts the passed user_ to a DTO.
     */
    public User_DTO toDTO(User_ user_) {
        return toDTO(user_, 1);
    }

    /**
     * Converts the passed user_ to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param user_
     * @param depth the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *              A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *              xToOne associations will be serialized, etc.
     */
    public User_DTO toDTO(User_ user_, int depth) {
        if (user_ == null) {
            return null;
        }

        User_DTO dto = new User_DTO();

        dto.id = user_.getId();
        dto.login = user_.getLogin();
        dto.password = user_.getPassword();
        dto.email = user_.getEmail();
        dto.isEnabled = user_.getIsEnabled();
        dto.civility = user_.getCivility();
        dto.countryCode = user_.getCountryCode();
        dto.firstName = user_.getFirstName();
        dto.lastName = user_.getLastName();
        dto.creationDate = user_.getCreationDate();
        dto.creationAuthor = user_.getCreationAuthor();
        dto.lastModificationDate = user_.getLastModificationDate();
        dto.lastModificationAuthor = user_.getLastModificationAuthor();
        dto.version = user_.getVersion();
        if (depth-- > 0) {
            dto.passport = passportDTOService.toDTO(user_.getPassport(), depth);
            final int fdepth = depth;
            dto.roles = user_.getRoles().stream().map(role -> roleDTOService.toDTO(role, fdepth)).collect(Collectors.toList());
        }

        return dto;
    }

    /**
     * Converts the passed dto to a User_.
     * Convenient for query by example.
     */
    public User_ toEntity(User_DTO dto) {
        return toEntity(dto, 1);
    }

    /**
     * Converts the passed dto to a User_.
     * Convenient for query by example.
     */
    public User_ toEntity(User_DTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        User_ user_ = new User_();

        user_.setId(dto.id);
        user_.setLogin(dto.login);
        user_.setPassword(dto.password);
        user_.setEmail(dto.email);
        user_.setIsEnabled(dto.isEnabled);
        user_.setCivility(dto.civility);
        user_.setCountryCode(dto.countryCode);
        user_.setFirstName(dto.firstName);
        user_.setLastName(dto.lastName);
        user_.setCreationDate(dto.creationDate);
        user_.setCreationAuthor(dto.creationAuthor);
        user_.setLastModificationDate(dto.lastModificationDate);
        user_.setLastModificationAuthor(dto.lastModificationAuthor);
        user_.setVersion(dto.version);
        if (depth-- > 0) {
            user_.setPassport(passportDTOService.toEntity(dto.passport, depth));
        }

        return user_;
    }
}