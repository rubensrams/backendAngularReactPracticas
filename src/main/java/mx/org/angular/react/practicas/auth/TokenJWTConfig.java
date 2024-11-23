package mx.org.angular.react.practicas.auth;

	import javax.crypto.SecretKey;

	import io.jsonwebtoken.Jwts;

	public class TokenJWTConfig {

	    public static final String CONTENT_TYPE = "application/json";
	    public static final String PREFIX_TOKEN = "Bearer ";
	    public static final String HEADER_AUTHORIZATION = "Authorization";
	    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
}
