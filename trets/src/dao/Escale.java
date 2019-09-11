package dao;

public class Escale {

    String voyage;
    private String navire;
    private String dateArrivee;
    private String dateDepart;
    private Integer escleunik;
    private String numero;
    private String trafic;

    public Escale() {

    }

    public Escale(String voyage, String navire, String dateArrivee, String dateDepart, String numero, Integer escleunik, String trafic) {

        this.numero = numero;
        this.navire = navire;
        this.dateArrivee = dateArrivee;
        this.dateDepart = dateDepart;
        this.voyage = voyage;
        this.escleunik = escleunik;
        this.trafic = trafic;
    }

    /**
     * Get the value of trafic
     *
     * @return the value of trafic
     */
    public String getTrafic() {
        return trafic;
    }

    /**
     * Set the value of trafic
     *
     * @param string new value of trafic
     */
    public void setTrafic(String string) {
        this.trafic = string;
    }

    public String getNumero() {
        return numero;
    }

    public String getVoyage() {
        return voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNavire() {
        return navire;
    }

    public void setNavire(String navire) {
        this.navire = navire;
    }

    public String getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(String dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Integer getEscleunik() {
        return escleunik;
    }

    public void setEscleunik(Integer escleunik) {
        this.escleunik = escleunik;
    }

}
