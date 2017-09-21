package com.orasio.example.boot.jwt.filter;


public class AccountCredentials {

    private String username;
    private String password;
    private long tenant = 0L;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getTenant() {
        return tenant;
    }

    public void setTenant(long tenant) {
        this.tenant = tenant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountCredentials that = (AccountCredentials) o;

        if (getTenant() != that.getTenant()) return false;
        if (!getUsername().equals(that.getUsername())) return false;
        return getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + (int) (getTenant() ^ (getTenant() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "AccountCredentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tenant=" + tenant +
                '}';
    }
}