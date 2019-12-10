package io.agora.accesstoken;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
