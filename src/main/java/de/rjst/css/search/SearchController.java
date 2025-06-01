package de.rjst.css.search;

import de.rjst.css.api.model.SearchRequestDto;
import de.rjst.css.api.model.SearchResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchCustomerFunction searchCustomerFunction;


    @PostMapping
    public List<SearchResponse> search(@RequestBody final SearchRequestDto request) {
        return searchCustomerFunction.apply(request);
    }
}
