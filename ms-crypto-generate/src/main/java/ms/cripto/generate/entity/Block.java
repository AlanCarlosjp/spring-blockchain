package ms.cripto.generate.entity;

import ms.cripto.generate.entity.block.Header;
import ms.cripto.generate.entity.block.Payload;

public class Block {

    private Header header;
    private Payload payload;

    public Block(Header header, Payload payload) {
        this.header = header;
        this.payload = payload;
    }

    public Block(Payload payload) {
        this.payload = payload;
    }

    public Header getHeader() {
        return header;
    }

    public Payload getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "Block{" +
                "header=" + header +
                ", payload=" + payload +
                '}';
    }
}
