package batch.processor;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.batch.item.ItemProcessor;

import batch.bo.PersonBo;

/**
 *
 * @author Unicorn
 */
public class PersonItemProcessor implements ItemProcessor<PersonBo, PersonBo> {

    public PersonBo process(final PersonBo person) throws Exception {
        System.out.println("[t] PersonItemProcessor.process()");

        final PersonBo transformedPerson = new PersonBo();

        System.out.println("[t] " + ToStringBuilder.reflectionToString(person, ToStringStyle.MULTI_LINE_STYLE));
        return transformedPerson;
    }

}
