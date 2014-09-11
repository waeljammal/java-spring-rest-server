package com.rs.controller;

import com.rs.model.Skill;
import com.rs.model.Skills;
import com.rs.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Wael Jammal on 10/09/2014.
 */
@RestController
public class JobController
{
    @Autowired
    private SkillRepository jobRepository;

    @RequestMapping(value = "/allSkills",
                    method = RequestMethod.GET,
                    headers ={"Accept=application/json,application/xml"},
                    produces={"application/json", "application/xml"})
    public Skills getJobs()
    {
        return new Skills((java.util.List<Skill>) jobRepository.findAll());
    }
}
