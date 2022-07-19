package ms.cripto.generate.service;

import ms.cripto.generate.entity.Block;
import ms.cripto.generate.entity.Blockchain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerateCriptoService {

    private static final Logger log = LoggerFactory.getLogger(GenerateCriptoService.class);

    private Blockchain blockchain;


    public List<Block> generateBlockchain(Integer difficulty, Integer quantity) {
        log.info("CREATING BLOCK SERVICE");
        blockchain = new Blockchain(difficulty);

        int blockNumber = quantity;
        List<Block> chain = blockchain.chain;

        for(int i = 1; i <= blockNumber; i++){
            Block block = blockchain.createBlock("Block" + i);
            Block minedBlock = blockchain.mineBlock(block);
            chain = blockchain.pushBlock(minedBlock);
        }


        return chain;
    }


}
