package ms.cripto.generate.entity.block;

public class Header {

    private Integer nonce;
    private String blockHash;

    public Header(Integer nonce, String blockHash) {
        this.nonce = nonce;
        this.blockHash = blockHash;
    }

    public Integer getNonce() {
        return nonce;
    }

    public String getBlockHash() {
        return blockHash;
    }

    @Override
    public String toString() {
        return "Header{" +
                "nonce=" + nonce +
                ", blockHash='" + blockHash + '\'' +
                '}';
    }
}
