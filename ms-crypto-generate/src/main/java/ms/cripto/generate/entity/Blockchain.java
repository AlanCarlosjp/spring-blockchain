package ms.cripto.generate.entity;


import ms.cripto.generate.entity.block.Header;
import ms.cripto.generate.entity.block.Payload;
import ms.cripto.generate.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Blockchain {

    private static final Logger log = LoggerFactory.getLogger(Blockchain.class);

    public List<Block> chain = new ArrayList<>();
    private String powPrefix = "0";
    private Integer difficulty = 4;

    public Blockchain(Integer difficulty) {
        createFirstBlock();
        this.difficulty = difficulty;
    }

    public Blockchain() {
    }

    public List<Block> getChain() {
        return chain;
    }

    public String getPowPrefix() {
        return powPrefix;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public Block createFirstBlock() {
        log.info("CREATE FIRST BLOCK");

        //HEADER
        Integer nonce = 0;
        String blockHash = StringUtil.sha256("Alan Coin");

        //PAYLOAD
        Integer sequence = 0;
        Long timeStamp = new Date().getTime();
        Object data = "Alan Coin";
        String previousHash = "";


        Header header = new Header(nonce, blockHash);
        Payload payload = new Payload(sequence, timeStamp, data, previousHash);

        Block block = new Block(header, payload);
        chain.add(block);
        return block;
    }

    private Block lastBlock() {
        log.info("GET LAST BLOCK");
        return this.chain.get(0);
    }

    private List<Block> chain() {
        return this.chain;
    }

    private String getPreviousBlockHash() {
        return lastBlock().getHeader().getBlockHash();
    }

    public Block createBlock(Object data) {

        int sequence = this.lastBlock().getPayload().getSequence() + 1;
        Long timeStamp = new Date().getTime();
        String previousHash = this.getPreviousBlockHash();

        Payload payload = new Payload(sequence, timeStamp, data, previousHash);
        log.info("CREATING BLOCK, payload: " + payload);
        return new Block(payload);
    }

    public Block mineBlock(Block block) {
        log.info("MINING BLOCK: " + block);
        Integer nonce = 0;
        Long startTime = new Date().getTime();
        while (true) {
            String blockHash = StringUtil.sha256(block.toString());
            String proofingHash = StringUtil.sha256(blockHash + nonce);

            if (isHashProofed(proofingHash, this.difficulty, this.powPrefix)) {
                Long endTime = new Date().getTime();
                String shortHash = blockHash.substring(0, 12);
                Long mineTime = (endTime - startTime) / 1000;

                log.info("Mined block ${" + block + "} " +
                        "in ${" + mineTime + "} seconds. Hash: ${" + shortHash + "} " +
                        "(${" + nonce + "} attempts)");

                return new Block(new Header(nonce, blockHash), new Payload(block.getPayload().getSequence(),
                        block.getPayload().getTimestamp(), block.getPayload().getData(),
                        block.getPayload().getPreviousHash()));
            }
            nonce++;
        }
    }

    public boolean verifyBlock(Block block) {
        if (block.getPayload().getPreviousHash() != this.getPreviousBlockHash()) {
            log.error("Invalid block #${block.payload.sequence}: " +
                    "Previous block hash is " +
                    this.getPreviousBlockHash().substring(0, 12) + " not " +
                    block.getPayload().getPreviousHash().substring(0, 12));
            return false;
        }
        if (!isHashProofed(StringUtil.sha256(StringUtil.sha256(
                        block.getPayload().toString() + block.getHeader().getNonce())),
                this.difficulty,
                this.powPrefix
        )) {
            log.info("Invalid block #${block.payload.sequence}: Hash is not proofed, nonce ${" +
                    block.getHeader().getNonce() + "} is not valid");
        }

        return true;
    }

    public List<Block> pushBlock(Block block) {
        if (verifyBlock(block)) this.chain.add(block);
        log.info("Pushed block #${" + block + "}");
        return this.chain;
    }

    private boolean isHashProofed(String proofingHash, Integer difficulty, String powPrefix) {
        String check = powPrefix.repeat(difficulty);
        return proofingHash.startsWith(check);
    }


}
