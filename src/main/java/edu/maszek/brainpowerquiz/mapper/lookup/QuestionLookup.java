package edu.maszek.brainpowerquiz.mapper.lookup;

import edu.maszek.brainpowerquiz.model.QuestionDTO;
import edu.maszek.brainpowerquiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionLookup {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private QuestionRepository questionRepository;

    public List<QuestionDTO> lookup() {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("theme")
                .localField("_id")
                .foreignField("_id")
                .as("themes");

        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(Criteria.where("_id").is("1")) , lookupOperation);
        List<QuestionDTO> results = mongoTemplate.aggregate(aggregation, "question", QuestionDTO.class).getMappedResults();

        return results;
    }
}
