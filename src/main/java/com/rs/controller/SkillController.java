package com.rs.controller;

import com.rs.model.Skill;
import com.rs.model.Skills;
import com.rs.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Convert;

/**
 * Created by Wael Jammal on 10/09/2014.
 */
@RestController
public class SkillController
{
    @Autowired
    private SkillRepository jobRepository;

    @RequestMapping(value = "/allSkills",
                    method = RequestMethod.GET,
                    headers ={"Accept=application/json,application/xml"},
                    produces={"application/json", "application/xml"})
    public Skills getSkills()
    {
        return new Skills((java.util.List<Skill>) jobRepository.findAll());
    }

    @RequestMapping(value = "/skill",
            method = RequestMethod.GET,
            headers ={"Accept=application/json,application/xml"},
            produces={"application/json", "application/xml"})
    public Skill getSkill(@RequestParam(value="id", required=true, defaultValue="0") String id)
    {
        long identifier = Long.parseLong(id);
        return jobRepository.findOne(identifier);
    }
}
