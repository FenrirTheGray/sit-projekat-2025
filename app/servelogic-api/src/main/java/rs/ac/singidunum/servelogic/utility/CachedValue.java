package rs.ac.singidunum.servelogic.utility;

public record CachedValue<T>(T data, long expires) {
	public boolean isExpired() {
		return System.currentTimeMillis() > expires;
	}
}
