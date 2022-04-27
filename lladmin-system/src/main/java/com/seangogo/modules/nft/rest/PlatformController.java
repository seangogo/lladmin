package com.seangogo.modules.nft.rest;

import com.seangogo.modules.nft.domain.Platform;
import com.seangogo.modules.nft.service.PlatformService;
import com.seangogo.modules.nft.service.dto.PlatformQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author xiaojun
* @date 2022-04-27
*/
@RestController
@RequestMapping("api")
public class PlatformController {

    @Autowired
    private PlatformService PlatformService;

    @GetMapping(value = "/Platform")
    @PreAuthorize("hasAnyRole('cjyly','PLATFORM_ALL','PLATFORM_SELECT')")
    public ResponseEntity getPlatforms(PlatformQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(PlatformService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping(value = "/Platform")
    @PreAuthorize("hasAnyRole('cjyly','PLATFORM_ALL','PLATFORM_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Platform resources){
        return new ResponseEntity(PlatformService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping(value = "/Platform")
    @PreAuthorize("hasAnyRole('cjyly','PLATFORM_ALL','PLATFORM_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Platform resources){
        PlatformService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/Platform/{id}")
    @PreAuthorize("hasAnyRole('cjyly','PLATFORM_ALL','PLATFORM_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        PlatformService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}