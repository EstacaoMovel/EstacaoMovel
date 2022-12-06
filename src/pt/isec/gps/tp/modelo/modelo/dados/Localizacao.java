package pt.isec.gps.tp.modelo.dados;

public class Localizacao {

    private int id_localizacao;
    private String latitude;
    private String longitude;

    public Localizacao(int id_localizacao, String latitude, String longitude) {
        this.id_localizacao = id_localizacao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId_localizacao() {
        return id_localizacao;
    }

    public void setId_localizacao(int id_localizacao) {
        this.id_localizacao = id_localizacao;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
