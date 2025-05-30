package de.rjst.css.logic;

import de.rjst.css.api.model.ElasticResponseDto;
import de.rjst.css.api.model.SearchResponse;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SearchResponseMapper implements Function<ElasticResponseDto, SearchResponse> {

    private final MatchedFieldMapper matchedFieldMapper;

    @Override
    public SearchResponse apply(final ElasticResponseDto elasticResponseDto) {
        var result = new SearchResponse();
        result.setMatchedFields(matchedFieldMapper.apply(elasticResponseDto.getExplanation()));
        result.setEntity(elasticResponseDto.getEntity());
        result.setScore(elasticResponseDto.getScore());
        return result;
    }
}
