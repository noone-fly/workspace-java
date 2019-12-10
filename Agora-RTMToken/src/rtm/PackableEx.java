package rtm;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
