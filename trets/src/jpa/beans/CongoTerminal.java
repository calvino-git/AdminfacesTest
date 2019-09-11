/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.beans;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "CONGO_TERMINAL", catalog = "", schema = "PPNCARGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CongoTerminal.findAll", query = "SELECT c FROM CongoTerminal c")
    , @NamedQuery(name = "CongoTerminal.findById", query = "SELECT c FROM CongoTerminal c WHERE c.id = :id")
    , @NamedQuery(name = "CongoTerminal.findByMois", query = "SELECT c FROM CongoTerminal c WHERE c.mois = :mois")
    , @NamedQuery(name = "CongoTerminal.findByNumCtn", query = "SELECT c FROM CongoTerminal c WHERE c.numCtn = :numCtn")
    , @NamedQuery(name = "CongoTerminal.findByDate", query = "SELECT c FROM CongoTerminal c WHERE c.date = :date")
    , @NamedQuery(name = "CongoTerminal.findByMouvement", query = "SELECT c FROM CongoTerminal c WHERE c.mouvement = :mouvement")
    , @NamedQuery(name = "CongoTerminal.findByTrafic", query = "SELECT c FROM CongoTerminal c WHERE c.trafic = :trafic")
    , @NamedQuery(name = "CongoTerminal.findByVidePlein", query = "SELECT c FROM CongoTerminal c WHERE c.videPlein = :videPlein")
    , @NamedQuery(name = "CongoTerminal.findByIso", query = "SELECT c FROM CongoTerminal c WHERE c.iso = :iso")
    , @NamedQuery(name = "CongoTerminal.findByTare", query = "SELECT c FROM CongoTerminal c WHERE c.tare = :tare")
    , @NamedQuery(name = "CongoTerminal.findByExpCours", query = "SELECT c FROM CongoTerminal c WHERE c.expCours = :expCours")
    , @NamedQuery(name = "CongoTerminal.findByEscale", query = "SELECT c FROM CongoTerminal c WHERE c.escale = :escale")
    , @NamedQuery(name = "CongoTerminal.findByVoyage", query = "SELECT c FROM CongoTerminal c WHERE c.voyage = :voyage")
    , @NamedQuery(name = "CongoTerminal.findByPol", query = "SELECT c FROM CongoTerminal c WHERE c.pol = :pol")
    , @NamedQuery(name = "CongoTerminal.findByPod", query = "SELECT c FROM CongoTerminal c WHERE c.pod = :pod")
    , @NamedQuery(name = "CongoTerminal.findByArmateur", query = "SELECT c FROM CongoTerminal c WHERE c.armateur = :armateur")
    , @NamedQuery(name = "CongoTerminal.findByPoidsBrut", query = "SELECT c FROM CongoTerminal c WHERE c.poidsBrut = :poidsBrut")
    , @NamedQuery(name = "CongoTerminal.findByDateArr", query = "SELECT c FROM CongoTerminal c WHERE c.dateArr = :dateArr")
    , @NamedQuery(name = "CongoTerminal.findByDateDep", query = "SELECT c FROM CongoTerminal c WHERE c.dateDep = :dateDep")})
public class CongoTerminal  implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOIS", nullable = false)
    private BigInteger mois;
    @Size(max = 50)
    @Column(name = "NUM_CTN", length = 50)
    private String numCtn;
    @Size(max = 50)
    @Column(name = "DAT", length = 50)
    private String date;
    @Size(max = 50)
    @Column(name = "MOUVEMENT", length = 50)
    private String mouvement;
    @Size(max = 50)
    @Column(name = "TRAFIC", length = 50)
    private String trafic;
    @Size(max = 50)
    @Column(name = "VIDE_PLEIN", length = 50)
    private String videPlein;
    @Size(max = 50)
    @Column(name = "ISO", length = 50)
    private String iso;
    @Size(max = 50)
    @Column(name = "TARE", length = 50)
    private String tare;
    @Size(max = 50)
    @Column(name = "EXP_COURS", length = 50)
    private String expCours;
    @Size(max = 50)
    @Column(name = "ESCALE", length = 50)
    private String escale;
    @Size(max = 50)
    @Column(name = "VOYAGE", length = 50)
    private String voyage;
    @Size(max = 50)
    @Column(name = "POL", length = 50)
    private String pol;
    @Size(max = 50)
    @Column(name = "POD", length = 50)
    private String pod;
    @Size(max = 50)
    @Column(name = "ARMATEUR", length = 50)
    private String armateur;
    @Size(max = 50)
    @Column(name = "POIDS_BRUT", length = 50)
    private String poidsBrut;
    @Size(max = 50)
    @Column(name = "DATE_ARR", length = 50)
    private String dateArr;
    @Size(max = 50)
    @Column(name = "DATE_DEP", length = 50)
    private String dateDep;

    public CongoTerminal() {
    }

    public CongoTerminal(int id) {
        this.id = id;
    }

    public CongoTerminal(int id, BigInteger mois) {
        this.id = id;
        this.mois = mois;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigInteger getMois() {
        return mois;
    }

    public void setMois(BigInteger mois) {
        this.mois = mois;
    }

    public String getNumCtn() {
        return numCtn;
    }

    public void setNumCtn(String numCtn) {
        this.numCtn = numCtn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMouvement() {
        return mouvement;
    }

    public void setMouvement(String mouvement) {
        this.mouvement = mouvement;
    }

    public String getTrafic() {
        return trafic;
    }

    public void setTrafic(String trafic) {
        this.trafic = trafic;
    }

    public String getVidePlein() {
        return videPlein;
    }

    public void setVidePlein(String videPlein) {
        this.videPlein = videPlein;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getTare() {
        return tare;
    }

    public void setTare(String tare) {
        this.tare = tare;
    }

    public String getExpCours() {
        return expCours;
    }

    public void setExpCours(String expCours) {
        this.expCours = expCours;
    }

    public String getEscale() {
        return escale;
    }

    public void setEscale(String escale) {
        this.escale = escale;
    }

    public String getVoyage() {
        return voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public String getArmateur() {
        return armateur;
    }

    public void setArmateur(String armateur) {
        this.armateur = armateur;
    }

    public String getPoidsBrut() {
        return poidsBrut;
    }

    public void setPoidsBrut(String poidsBrut) {
        this.poidsBrut = poidsBrut;
    }

    public String getDateArr() {
        return dateArr;
    }

    public void setDateArr(String dateArr) {
        this.dateArr = dateArr;
    }

    public String getDateDep() {
        return dateDep;
    }

    public void setDateDep(String dateDep) {
        this.dateDep = dateDep;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof CongoTerminal)) {
//            return false;
//        }
//        CongoTerminal other = (CongoTerminal) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "db.CongoTerminal[ id=" + id + " ]";
    }
    
}
