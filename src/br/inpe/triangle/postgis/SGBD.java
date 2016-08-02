package br.inpe.triangle.postgis;

public enum SGBD {
	POSTGRESQL;
	public String toString() {
		switch (this) {
		case POSTGRESQL:
			return "postgresql";
		default:
			return "UNDEFINED";
		}
	};

	public String getURLConnection(String ip, String database) {
		StringBuilder str = new StringBuilder();
		str.append("jdbc:");
		str.append(toString());
		str.append("://");
		str.append(ip);
		str.append("/");
		str.append(database);
		return str.toString();
	}
}
