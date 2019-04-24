package cn.seangogo.modules.quartz.rest;

import cn.seangogo.modules.quartz.domain.QuartzJob;
import cn.seangogo.modules.quartz.domain.QuartzLog;
import cn.seangogo.modules.quartz.service.QuartzJobService;
import cn.seangogo.modules.quartz.service.query.QuartzJobQueryService;
import cn.seangogo.modules.quartz.service.query.QuartzLogQueryService;
import lombok.extern.slf4j.Slf4j;
import cn.seangogo.aop.log.Log;
import cn.seangogo.exception.BadRequestException;
import cn.seangogo.modules.quartz.domain.QuartzJob;
import cn.seangogo.modules.quartz.domain.QuartzLog;
import cn.seangogo.modules.quartz.service.QuartzJobService;
import cn.seangogo.modules.quartz.service.query.QuartzJobQueryService;
import cn.seangogo.modules.quartz.service.query.QuartzLogQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author jie
 * @date 2019-01-07
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class QuartzJobController {

    private static final String ENTITY_NAME = "quartzJob";

    @Autowired
    private QuartzJobService quartzJobService;

    @Autowired
    private QuartzJobQueryService quartzJobQueryService;

    @Autowired
    private QuartzLogQueryService quartzLogQueryService;

    @Log("查询定时任务")
    @GetMapping(value = "/jobs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_SELECT')")
    public ResponseEntity getJobs(QuartzJob resources, Pageable pageable){
        return new ResponseEntity(quartzJobQueryService.queryAll(resources,pageable), HttpStatus.OK);
    }

    /**
     * 查询定时任务日志
     * @param resources
     * @param pageable
     * @return
     */
    @GetMapping(value = "/jobLogs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_SELECT')")
    public ResponseEntity getJobLogs(QuartzLog resources, Pageable pageable){
        return new ResponseEntity(quartzLogQueryService.queryAll(resources,pageable), HttpStatus.OK);
    }

    @Log("新增定时任务")
    @PostMapping(value = "/jobs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_CREATE')")
    public ResponseEntity create(@Validated @RequestBody QuartzJob resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(quartzJobService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改定时任务")
    @PutMapping(value = "/jobs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_EDIT')")
    public ResponseEntity update(@Validated(QuartzJob.Update.class) @RequestBody QuartzJob resources){
        quartzJobService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("更改定时任务状态")
    @PutMapping(value = "/jobs/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_EDIT')")
    public ResponseEntity updateIsPause(@PathVariable Long id){
        quartzJobService.updateIsPause(quartzJobService.findById(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("执行定时任务")
    @PutMapping(value = "/jobs/exec/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_EDIT')")
    public ResponseEntity execution(@PathVariable Long id){
        quartzJobService.execution(quartzJobService.findById(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除定时任务")
    @DeleteMapping(value = "/jobs/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        quartzJobService.delete(quartzJobService.findById(id));
        return new ResponseEntity(HttpStatus.OK);
    }
}
