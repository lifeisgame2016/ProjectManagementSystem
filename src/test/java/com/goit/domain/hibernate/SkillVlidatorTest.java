package com.goit.domain.hibernate;

import com.goit.model.Developer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

@RunWith(Parameterized.class)
public class SkillVlidatorTest {

    @Parameterized.Parameters(name = "{index}: n={0}, age={1}")
    public static Collection<Object[]> params() {
        List<Object[]> result = new ArrayList<>();
        result.add(new Object[]{"John", 10});
        result.add(new Object[]{"Jane", 9});
        return result;
    }

    private static Validator validator= Validation.buildDefaultValidatorFactory()
            .getValidator();

   private Developer developer;

   public SkillVlidatorTest(String name, Integer age) {
       Developer developer = new Developer();
       developer.setAge(age);
       developer.setName(name);
       this.developer = developer;
   }

   @Test
   public void test() {
       Set<ConstraintViolation<Developer>> validate =
               validator.validate(developer);
       Assert.assertEquals(1, validate.size());
   }


}
