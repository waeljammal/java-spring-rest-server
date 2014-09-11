import com.rs.model.Skill;
import com.rs.repository.SkillRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.AppConfiguration;

import javax.annotation.Resource;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Wael Jammal on 10/09/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfiguration.class })
public class JobRepositoryTest
{
    @Resource
    private SkillRepository repository;

    @Test
    public void JobRepository_Add_Single()
    {
        Skill job = new Skill("Test Skill", "Test Description");

        Skill saved = repository.save(job);

        assertNotNull(saved);
    }
}
