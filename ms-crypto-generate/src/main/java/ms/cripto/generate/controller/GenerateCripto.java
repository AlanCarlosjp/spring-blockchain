package ms.cripto.generate.controller;

import ms.cripto.generate.entity.Block;
import ms.cripto.generate.entity.Blockchain;
import ms.cripto.generate.service.GenerateCriptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/create")
@RestController
public class GenerateCripto {
    private static final Logger log = LoggerFactory.getLogger(GenerateCripto.class);


    private final GenerateCriptoService service;

    public GenerateCripto(GenerateCriptoService service) {
        this.service = service;
    }

    @GetMapping(value = "/{difficulty}/{quantity}")
    public ResponseEntity gerarBlockChain(@PathVariable Integer difficulty,@PathVariable Integer quantity) {
        log.info("difficulty: " + difficulty + "quantity: " + quantity);
        List<Block> blockchain = service.generateBlockchain(difficulty, quantity);

        return ResponseEntity.ok().body(blockchain);
    }
}
