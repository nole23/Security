package agent;

public class Token {

	private String role;
	private String jwt;
	private String username;
	private Csrf csrf;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Csrf getCsrf() {
		return csrf;
	}

	public void setCsrf(Csrf csrf) {
		this.csrf = csrf;
	}

	public static class Csrf {

		private String headerName;
		private String parameterName;
		private String token;

		public String getHeaderName() {
			return headerName;
		}

		public void setHeaderName(String headerName) {
			this.headerName = headerName;
		}

		public String getParameterName() {
			return parameterName;
		}

		public void setParameterName(String parameterName) {
			this.parameterName = parameterName;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

	}
}
