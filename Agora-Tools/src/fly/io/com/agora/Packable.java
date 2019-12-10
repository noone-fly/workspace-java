package fly.io.com.agora;

public interface Packable {
	ByteBuf marshal(ByteBuf out);
}
