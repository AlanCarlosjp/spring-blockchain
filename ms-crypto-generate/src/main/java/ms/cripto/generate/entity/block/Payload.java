package ms.cripto.generate.entity.block;

public class Payload {

    private Integer sequence;
    private Long timestamp;
    private Object data;
    private String previousHash;

    public Payload(Integer sequence, Long timestamp, Object data, String previousHash) {
        this.sequence = sequence;
        this.timestamp = timestamp;
        this.data = data;
        this.previousHash = previousHash;
    }

    public Integer getSequence() {
        return sequence;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "sequence=" + sequence +
                ", timestamp=" + timestamp +
                ", data=" + data +
                ", previousHash='" + previousHash + '\'' +
                '}';
    }

    public String getPreviousHash() {
        return previousHash;
    }
}
