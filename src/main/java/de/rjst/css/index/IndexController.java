package de.rjst.css.index;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/index")
public class IndexController {

    private final IndexerConsumer indexerConsumer;


    @PostMapping
    public void index(@RequestParam final String indexName) {
        indexerConsumer.accept(indexName);
    }

}
