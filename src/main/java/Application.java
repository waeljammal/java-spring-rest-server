import com.rs.model.Level;
import com.rs.model.Skill;
import com.rs.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Wael Jammal on 10/09/2014.
 */
@ComponentScan
@EnableAutoConfiguration
public class Application
{
    public static void main(String[] args)
    {
        ConfigurableApplicationContext context =SpringApplication.run(Application.class, args);

        SkillRepository repository = context.getBean(SkillRepository.class);

        Skill skill = new Skill("Java", "I have 5 years of Java experience.");
        skill.getLevels().add(new Level("Spring 3"));
        skill.getLevels().add(new Level("Spring 4"));
        skill.getLevels().add(new Level("Hibernate"));
        skill.getLevels().add(new Level("JUnit"));

        repository.save(skill);

        skill = new Skill("Flex", "I have 6+ years of Adobe Flex experience.");
        skill.getLevels().add(new Level("Flex Mobile"));
        skill.getLevels().add(new Level("Air"));
        skill.getLevels().add(new Level("Web Applications"));
        skill.getLevels().add(new Level("LiveCycle"));
        skill.getLevels().add(new Level("Blaze DS"));
        skill.getLevels().add(new Level("Bojinx (My framework)"));
        skill.getLevels().add(new Level("Parsley"));
        skill.getLevels().add(new Level("Robot Legs"));

        repository.save(skill);
    }
}
