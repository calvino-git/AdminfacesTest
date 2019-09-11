/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(catalog = "", schema = "PPNCARGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Navire.findAll", query = "SELECT n FROM Navire n")
    , @NamedQuery(name = "Navire.findByNacleunik", query = "SELECT n FROM Navire n WHERE n.nacleunik = :nacleunik")
    , @NamedQuery(name = "Navire.findByNumero", query = "SELECT n FROM Navire n WHERE n.numero = :numero")
    , @NamedQuery(name = "Navire.findByRadio", query = "SELECT n FROM Navire n WHERE n.radio = :radio")
    , @NamedQuery(name = "Navire.findByNom", query = "SELECT n FROM Navire n WHERE n.nom = :nom")
    , @NamedQuery(name = "Navire.findByLibelle", query = "SELECT n FROM Navire n WHERE n.libelle = :libelle")
    , @NamedQuery(name = "Navire.findByPavillon", query = "SELECT n FROM Navire n WHERE n.pavillon = :pavillon")
    , @NamedQuery(name = "Navire.findByArmateur", query = "SELECT n FROM Navire n WHERE n.armateur = :armateur")
    , @NamedQuery(name = "Navire.findByAffreteur", query = "SELECT n FROM Navire n WHERE n.affreteur = :affreteur")
    , @NamedQuery(name = "Navire.findByCapitaine", query = "SELECT n FROM Navire n WHERE n.capitaine = :capitaine")
    , @NamedQuery(name = "Navire.findByConstruc", query = "SELECT n FROM Navire n WHERE n.construc = :construc")
    , @NamedQuery(name = "Navire.findByType", query = "SELECT n FROM Navire n WHERE n.type = :type")
    , @NamedQuery(name = "Navire.findByNature", query = "SELECT n FROM Navire n WHERE n.nature = :nature")
    , @NamedQuery(name = "Navire.findByVolume", query = "SELECT n FROM Navire n WHERE n.volume = :volume")
    , @NamedQuery(name = "Navire.findByCapacite", query = "SELECT n FROM Navire n WHERE n.capacite = :capacite")
    , @NamedQuery(name = "Navire.findByChargB", query = "SELECT n FROM Navire n WHERE n.chargB = :chargB")
    , @NamedQuery(name = "Navire.findByChargN", query = "SELECT n FROM Navire n WHERE n.chargN = :chargN")
    , @NamedQuery(name = "Navire.findByTare", query = "SELECT n FROM Navire n WHERE n.tare = :tare")
    , @NamedQuery(name = "Navire.findByConten", query = "SELECT n FROM Navire n WHERE n.conten = :conten")
    , @NamedQuery(name = "Navire.findByLongu", query = "SELECT n FROM Navire n WHERE n.longu = :longu")
    , @NamedQuery(name = "Navire.findByLargeur", query = "SELECT n FROM Navire n WHERE n.largeur = :largeur")
    , @NamedQuery(name = "Navire.findByHauteur", query = "SELECT n FROM Navire n WHERE n.hauteur = :hauteur")
    , @NamedQuery(name = "Navire.findByNombre", query = "SELECT n FROM Navire n WHERE n.nombre = :nombre")
    , @NamedQuery(name = "Navire.findByPortAttac", query = "SELECT n FROM Navire n WHERE n.portAttac = :portAttac")
    , @NamedQuery(name = "Navire.findByPerson", query = "SELECT n FROM Navire n WHERE n.person = :person")
    , @NamedQuery(name = "Navire.findByPropAv", query = "SELECT n FROM Navire n WHERE n.propAv = :propAv")
    , @NamedQuery(name = "Navire.findByPropAr", query = "SELECT n FROM Navire n WHERE n.propAr = :propAr")
    , @NamedQuery(name = "Navire.findByUnite", query = "SELECT n FROM Navire n WHERE n.unite = :unite")
    , @NamedQuery(name = "Navire.findByPassag", query = "SELECT n FROM Navire n WHERE n.passag = :passag")
    , @NamedQuery(name = "Navire.findByQuantite", query = "SELECT n FROM Navire n WHERE n.quantite = :quantite")
    , @NamedQuery(name = "Navire.findByTexte", query = "SELECT n FROM Navire n WHERE n.texte = :texte")
    , @NamedQuery(name = "Navire.findByLDeb", query = "SELECT n FROM Navire n WHERE n.lDeb = :lDeb")
    , @NamedQuery(name = "Navire.findByDesserte", query = "SELECT n FROM Navire n WHERE n.desserte = :desserte")
    , @NamedQuery(name = "Navire.findByNbMot", query = "SELECT n FROM Navire n WHERE n.nbMot = :nbMot")
    , @NamedQuery(name = "Navire.findByNbRem", query = "SELECT n FROM Navire n WHERE n.nbRem = :nbRem")
    , @NamedQuery(name = "Navire.findByLigne", query = "SELECT n FROM Navire n WHERE n.ligne = :ligne")
    , @NamedQuery(name = "Navire.findByTraitSht", query = "SELECT n FROM Navire n WHERE n.traitSht = :traitSht")
    , @NamedQuery(name = "Navire.findByEmplac", query = "SELECT n FROM Navire n WHERE n.emplac = :emplac")
    , @NamedQuery(name = "Navire.findByAffret", query = "SELECT n FROM Navire n WHERE n.affret = :affret")
    , @NamedQuery(name = "Navire.findByCapGrain", query = "SELECT n FROM Navire n WHERE n.capGrain = :capGrain")
    , @NamedQuery(name = "Navire.findByCapBalle", query = "SELECT n FROM Navire n WHERE n.capBalle = :capBalle")
    , @NamedQuery(name = "Navire.findByGrueBab", query = "SELECT n FROM Navire n WHERE n.grueBab = :grueBab")
    , @NamedQuery(name = "Navire.findByGrueTri", query = "SELECT n FROM Navire n WHERE n.grueTri = :grueTri")
    , @NamedQuery(name = "Navire.findByCategorie", query = "SELECT n FROM Navire n WHERE n.categorie = :categorie")
    , @NamedQuery(name = "Navire.findByTLong", query = "SELECT n FROM Navire n WHERE n.tLong = :tLong")
    , @NamedQuery(name = "Navire.findByTLargeur", query = "SELECT n FROM Navire n WHERE n.tLargeur = :tLargeur")
    , @NamedQuery(name = "Navire.findByChateau", query = "SELECT n FROM Navire n WHERE n.chateau = :chateau")
    , @NamedQuery(name = "Navire.findByCellularise", query = "SELECT n FROM Navire n WHERE n.cellularise = :cellularise")
    , @NamedQuery(name = "Navire.findByRampe", query = "SELECT n FROM Navire n WHERE n.rampe = :rampe")
    , @NamedQuery(name = "Navire.findByGestion", query = "SELECT n FROM Navire n WHERE n.gestion = :gestion")
    , @NamedQuery(name = "Navire.findByStatut", query = "SELECT n FROM Navire n WHERE n.statut = :statut")
    , @NamedQuery(name = "Navire.findByCreateur", query = "SELECT n FROM Navire n WHERE n.createur = :createur")
    , @NamedQuery(name = "Navire.findByDCreat", query = "SELECT n FROM Navire n WHERE n.dCreat = :dCreat")
    , @NamedQuery(name = "Navire.findByHCreat", query = "SELECT n FROM Navire n WHERE n.hCreat = :hCreat")
    , @NamedQuery(name = "Navire.findByUtilisat", query = "SELECT n FROM Navire n WHERE n.utilisat = :utilisat")
    , @NamedQuery(name = "Navire.findByDModif", query = "SELECT n FROM Navire n WHERE n.dModif = :dModif")
    , @NamedQuery(name = "Navire.findByHModif", query = "SELECT n FROM Navire n WHERE n.hModif = :hModif")
    , @NamedQuery(name = "Navire.findByPorts", query = "SELECT n FROM Navire n WHERE n.ports = :ports")
    , @NamedQuery(name = "Navire.findByManifold", query = "SELECT n FROM Navire n WHERE n.manifold = :manifold")
    , @NamedQuery(name = "Navire.findByJaugeUms", query = "SELECT n FROM Navire n WHERE n.jaugeUms = :jaugeUms")
    , @NamedQuery(name = "Navire.findByConsignes", query = "SELECT n FROM Navire n WHERE n.consignes = :consignes")
    , @NamedQuery(name = "Navire.findByDateConsignes", query = "SELECT n FROM Navire n WHERE n.dateConsignes = :dateConsignes")
    , @NamedQuery(name = "Navire.findByPuisPropAv", query = "SELECT n FROM Navire n WHERE n.puisPropAv = :puisPropAv")
    , @NamedQuery(name = "Navire.findByPuisPropAr", query = "SELECT n FROM Navire n WHERE n.puisPropAr = :puisPropAr")
    , @NamedQuery(name = "Navire.findByNomCourt", query = "SELECT n FROM Navire n WHERE n.nomCourt = :nomCourt")
    , @NamedQuery(name = "Navire.findByAgent", query = "SELECT n FROM Navire n WHERE n.agent = :agent")
    , @NamedQuery(name = "Navire.findByLongueurPorte", query = "SELECT n FROM Navire n WHERE n.longueurPorte = :longueurPorte")
    , @NamedQuery(name = "Navire.findByNumeroOmi", query = "SELECT n FROM Navire n WHERE n.numeroOmi = :numeroOmi")
    , @NamedQuery(name = "Navire.findByNumeroCertificat", query = "SELECT n FROM Navire n WHERE n.numeroCertificat = :numeroCertificat")
    , @NamedQuery(name = "Navire.findByDateCertificat", query = "SELECT n FROM Navire n WHERE n.dateCertificat = :dateCertificat")
    , @NamedQuery(name = "Navire.findByDatePeremption", query = "SELECT n FROM Navire n WHERE n.datePeremption = :datePeremption")
    , @NamedQuery(name = "Navire.findByNiveauSurete", query = "SELECT n FROM Navire n WHERE n.niveauSurete = :niveauSurete")
    , @NamedQuery(name = "Navire.findByOrganismeCertification", query = "SELECT n FROM Navire n WHERE n.organismeCertification = :organismeCertification")
    , @NamedQuery(name = "Navire.findByPaysCertificat", query = "SELECT n FROM Navire n WHERE n.paysCertificat = :paysCertificat")
    , @NamedQuery(name = "Navire.findByEspaceOuvert", query = "SELECT n FROM Navire n WHERE n.espaceOuvert = :espaceOuvert")
    , @NamedQuery(name = "Navire.findByNombreCale", query = "SELECT n FROM Navire n WHERE n.nombreCale = :nombreCale")
    , @NamedQuery(name = "Navire.findByTonnageDeb", query = "SELECT n FROM Navire n WHERE n.tonnageDeb = :tonnageDeb")
    , @NamedQuery(name = "Navire.findByTonnageEmb", query = "SELECT n FROM Navire n WHERE n.tonnageEmb = :tonnageEmb")
    , @NamedQuery(name = "Navire.findByValideCapitainerie", query = "SELECT n FROM Navire n WHERE n.valideCapitainerie = :valideCapitainerie")
    , @NamedQuery(name = "Navire.findByOkCapitaine", query = "SELECT n FROM Navire n WHERE n.okCapitaine = :okCapitaine")
    , @NamedQuery(name = "Navire.findByDateCapitaine", query = "SELECT n FROM Navire n WHERE n.dateCapitaine = :dateCapitaine")
    , @NamedQuery(name = "Navire.findByHeureCapitaine", query = "SELECT n FROM Navire n WHERE n.heureCapitaine = :heureCapitaine")
    , @NamedQuery(name = "Navire.findByNavirePression", query = "SELECT n FROM Navire n WHERE n.navirePression = :navirePression")
//    , @NamedQuery(name = "Navire.findByDateDebutRelache", query = "SELECT n FROM Navire n WHERE n.dateDebutRelache = :dateDebutRelache")
        
        
//    , @NamedQuery(name = "Navire.findByDateFinRelache", query = "SELECT n FROM Navire n WHERE n.dateFinRelache = :dateFinRelache")
})
public class Navire implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 19, scale = 0)
    private BigDecimal nacleunik;
    @Size(max = 12)
    @Column(length = 12)
    private String numero;
    @Size(max = 12)
    @Column(length = 12)
    private String radio;
    @Size(max = 40)
    @Column(length = 40)
    private String nom;
    @Size(max = 40)
    @Column(length = 40)
    private String libelle;
    @Size(max = 8)
    @Column(length = 8)
    private String pavillon;
    @Size(max = 8)
    @Column(length = 8)
    private String armateur;
    @Size(max = 8)
    @Column(length = 8)
    private String affreteur;
    @Size(max = 25)
    @Column(length = 25)
    private String capitaine;
    private Integer construc;
    @Size(max = 8)
    @Column(length = 8)
    private String type;
    @Size(max = 8)
    @Column(length = 8)
    private String nature;
    private BigInteger volume;
    @Column(precision = 63)
    private Double capacite;
    @Column(name = "CHARG_B", precision = 63)
    private Double chargB;
    @Column(name = "CHARG_N", precision = 63)
    private Double chargN;
    @Column(precision = 19, scale = 6)
    private BigDecimal tare;
    private BigInteger conten;
    @Column(precision = 63)
    private Double longu;
    @Column(precision = 63)
    private Double largeur;
    @Column(precision = 63)
    private Double hauteur;
    private BigInteger nombre;
    @Size(max = 8)
    @Column(name = "PORT_ATTAC", length = 8)
    private String portAttac;
    private BigInteger person;
    @Column(name = "PROP_AV")
    private Short propAv;
    @Column(name = "PROP_AR")
    private Short propAr;
    private BigInteger unite;
    private BigInteger passag;
    private BigInteger quantite;
    @Size(max = 80)
    @Column(length = 80)
    private String texte;
    @Column(name = "L_DEB")
    private BigInteger lDeb;
    @Size(max = 8)
    @Column(length = 8)
    private String desserte;
    @Column(name = "NB_MOT")
    private Short nbMot;
    @Column(name = "NB_REM")
    private Short nbRem;
    @Size(max = 8)
    @Column(length = 8)
    private String ligne;
    @Column(name = "TRAIT_SHT")
    private BigInteger traitSht;
    @Size(max = 8)
    @Column(length = 8)
    private String emplac;
    @Size(max = 8)
    @Column(length = 8)
    private String affret;
    @Column(name = "CAP_GRAIN")
    private Integer capGrain;
    @Column(name = "CAP_BALLE")
    private Integer capBalle;
    @Column(name = "GRUE_BAB")
    private Short grueBab;
    @Column(name = "GRUE_TRI")
    private Short grueTri;
    @Size(max = 2)
    @Column(length = 2)
    private String categorie;
    @Size(max = 8)
    @Column(name = "T_LONG", length = 8)
    private String tLong;
    @Size(max = 8)
    @Column(name = "T_LARGEUR", length = 8)
    private String tLargeur;
    @Column(precision = 63)
    private Double chateau;
    private Short cellularise;
    @Size(max = 8)
    @Column(length = 8)
    private String rampe;
    private BigInteger gestion;
    @Size(max = 8)
    @Column(length = 8)
    private String statut;
    @Size(max = 10)
    @Column(length = 10)
    private String createur;
    @Size(max = 8)
    @Column(name = "D_CREAT", length = 8)
    private String dCreat;
    @Size(max = 4)
    @Column(name = "H_CREAT", length = 4)
    private String hCreat;
    @Size(max = 10)
    @Column(length = 10)
    private String utilisat;
    @Size(max = 8)
    @Column(name = "D_MODIF", length = 8)
    private String dModif;
    @Size(max = 4)
    @Column(name = "H_MODIF", length = 4)
    private String hModif;
    @Size(max = 8)
    @Column(length = 8)
    private String ports;
    @Column(precision = 19, scale = 6)
    private BigDecimal manifold;
    @Column(name = "JAUGE_UMS", precision = 19, scale = 6)
    private BigDecimal jaugeUms;
    private Short consignes;
    @Size(max = 8)
    @Column(name = "DATE_CONSIGNES", length = 8)
    private String dateConsignes;
    @Size(max = 8)
    @Column(name = "PUIS_PROP_AV", length = 8)
    private String puisPropAv;
    @Size(max = 8)
    @Column(name = "PUIS_PROP_AR", length = 8)
    private String puisPropAr;
    @Size(max = 8)
    @Column(name = "NOM_COURT", length = 8)
    private String nomCourt;
    @Size(max = 8)
    @Column(length = 8)
    private String agent;
    @Column(name = "LONGUEUR_PORTE", precision = 19, scale = 6)
    private BigDecimal longueurPorte;
    @Size(max = 8)
    @Column(name = "NUMERO_OMI", length = 8)
    private String numeroOmi;
    @Size(max = 12)
    @Column(name = "NUMERO_CERTIFICAT", length = 12)
    private String numeroCertificat;
    @Size(max = 8)
    @Column(name = "DATE_CERTIFICAT", length = 8)
    private String dateCertificat;
    @Size(max = 8)
    @Column(name = "DATE_PEREMPTION", length = 8)
    private String datePeremption;
    @Size(max = 8)
    @Column(name = "NIVEAU_SURETE", length = 8)
    private String niveauSurete;
    @Size(max = 20)
    @Column(name = "ORGANISME_CERTIFICATION", length = 20)
    private String organismeCertification;
    @Size(max = 8)
    @Column(name = "PAYS_CERTIFICAT", length = 8)
    private String paysCertificat;
    @Column(name = "ESPACE_OUVERT")
    private BigInteger espaceOuvert;
    @Column(name = "NOMBRE_CALE")
    private BigInteger nombreCale;
    @Column(name = "TONNAGE_DEB", precision = 19, scale = 6)
    private BigDecimal tonnageDeb;
    @Column(name = "TONNAGE_EMB", precision = 19, scale = 6)
    private BigDecimal tonnageEmb;
    @Column(name = "VALIDE_CAPITAINERIE")
    private Short valideCapitainerie;
    @Column(name = "OK_CAPITAINE")
    private Short okCapitaine;
    @Size(max = 8)
    @Column(name = "DATE_CAPITAINE", length = 8)
    private String dateCapitaine;
    @Size(max = 4)
    @Column(name = "HEURE_CAPITAINE", length = 4)
    private String heureCapitaine;
    @Column(name = "NAVIRE_PRESSION")
    private Short navirePression;
//    @Size(max = 8)
//    @Column(name = "DATE_DEBUT_RELACHE", length = 8)
//    private String dateDebutRelache;
//    @Size(max = 8)
//    @Column(name = "DATE_FIN_RELACHE", length = 8)
//    private String dateFinRelache;
    @OneToMany(mappedBy = "nacleunik")
    private Collection<Escale> escaleCollection;

    public Navire() {
    }

    public Navire(BigDecimal nacleunik) {
        this.nacleunik = nacleunik;
    }

    public BigDecimal getNacleunik() {
        return nacleunik;
    }

    public void setNacleunik(BigDecimal nacleunik) {
        this.nacleunik = nacleunik;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getPavillon() {
        return pavillon;
    }

    public void setPavillon(String pavillon) {
        this.pavillon = pavillon;
    }

    public String getArmateur() {
        return armateur;
    }

    public void setArmateur(String armateur) {
        this.armateur = armateur;
    }

    public String getAffreteur() {
        return affreteur;
    }

    public void setAffreteur(String affreteur) {
        this.affreteur = affreteur;
    }

    public String getCapitaine() {
        return capitaine;
    }

    public void setCapitaine(String capitaine) {
        this.capitaine = capitaine;
    }

    public Integer getConstruc() {
        return construc;
    }

    public void setConstruc(Integer construc) {
        this.construc = construc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public BigInteger getVolume() {
        return volume;
    }

    public void setVolume(BigInteger volume) {
        this.volume = volume;
    }

    public Double getCapacite() {
        return capacite;
    }

    public void setCapacite(Double capacite) {
        this.capacite = capacite;
    }

    public Double getChargB() {
        return chargB;
    }

    public void setChargB(Double chargB) {
        this.chargB = chargB;
    }

    public Double getChargN() {
        return chargN;
    }

    public void setChargN(Double chargN) {
        this.chargN = chargN;
    }

    public BigDecimal getTare() {
        return tare;
    }

    public void setTare(BigDecimal tare) {
        this.tare = tare;
    }

    public BigInteger getConten() {
        return conten;
    }

    public void setConten(BigInteger conten) {
        this.conten = conten;
    }

    public Double getLongu() {
        return longu;
    }

    public void setLongu(Double longu) {
        this.longu = longu;
    }

    public Double getLargeur() {
        return largeur;
    }

    public void setLargeur(Double largeur) {
        this.largeur = largeur;
    }

    public Double getHauteur() {
        return hauteur;
    }

    public void setHauteur(Double hauteur) {
        this.hauteur = hauteur;
    }

    public BigInteger getNombre() {
        return nombre;
    }

    public void setNombre(BigInteger nombre) {
        this.nombre = nombre;
    }

    public String getPortAttac() {
        return portAttac;
    }

    public void setPortAttac(String portAttac) {
        this.portAttac = portAttac;
    }

    public BigInteger getPerson() {
        return person;
    }

    public void setPerson(BigInteger person) {
        this.person = person;
    }

    public Short getPropAv() {
        return propAv;
    }

    public void setPropAv(Short propAv) {
        this.propAv = propAv;
    }

    public Short getPropAr() {
        return propAr;
    }

    public void setPropAr(Short propAr) {
        this.propAr = propAr;
    }

    public BigInteger getUnite() {
        return unite;
    }

    public void setUnite(BigInteger unite) {
        this.unite = unite;
    }

    public BigInteger getPassag() {
        return passag;
    }

    public void setPassag(BigInteger passag) {
        this.passag = passag;
    }

    public BigInteger getQuantite() {
        return quantite;
    }

    public void setQuantite(BigInteger quantite) {
        this.quantite = quantite;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public BigInteger getLDeb() {
        return lDeb;
    }

    public void setLDeb(BigInteger lDeb) {
        this.lDeb = lDeb;
    }

    public String getDesserte() {
        return desserte;
    }

    public void setDesserte(String desserte) {
        this.desserte = desserte;
    }

    public Short getNbMot() {
        return nbMot;
    }

    public void setNbMot(Short nbMot) {
        this.nbMot = nbMot;
    }

    public Short getNbRem() {
        return nbRem;
    }

    public void setNbRem(Short nbRem) {
        this.nbRem = nbRem;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    public BigInteger getTraitSht() {
        return traitSht;
    }

    public void setTraitSht(BigInteger traitSht) {
        this.traitSht = traitSht;
    }

    public String getEmplac() {
        return emplac;
    }

    public void setEmplac(String emplac) {
        this.emplac = emplac;
    }

    public String getAffret() {
        return affret;
    }

    public void setAffret(String affret) {
        this.affret = affret;
    }

    public Integer getCapGrain() {
        return capGrain;
    }

    public void setCapGrain(Integer capGrain) {
        this.capGrain = capGrain;
    }

    public Integer getCapBalle() {
        return capBalle;
    }

    public void setCapBalle(Integer capBalle) {
        this.capBalle = capBalle;
    }

    public Short getGrueBab() {
        return grueBab;
    }

    public void setGrueBab(Short grueBab) {
        this.grueBab = grueBab;
    }

    public Short getGrueTri() {
        return grueTri;
    }

    public void setGrueTri(Short grueTri) {
        this.grueTri = grueTri;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTLong() {
        return tLong;
    }

    public void setTLong(String tLong) {
        this.tLong = tLong;
    }

    public String getTLargeur() {
        return tLargeur;
    }

    public void setTLargeur(String tLargeur) {
        this.tLargeur = tLargeur;
    }

    public Double getChateau() {
        return chateau;
    }

    public void setChateau(Double chateau) {
        this.chateau = chateau;
    }

    public Short getCellularise() {
        return cellularise;
    }

    public void setCellularise(Short cellularise) {
        this.cellularise = cellularise;
    }

    public String getRampe() {
        return rampe;
    }

    public void setRampe(String rampe) {
        this.rampe = rampe;
    }

    public BigInteger getGestion() {
        return gestion;
    }

    public void setGestion(BigInteger gestion) {
        this.gestion = gestion;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getCreateur() {
        return createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }

    public String getDCreat() {
        return dCreat;
    }

    public void setDCreat(String dCreat) {
        this.dCreat = dCreat;
    }

    public String getHCreat() {
        return hCreat;
    }

    public void setHCreat(String hCreat) {
        this.hCreat = hCreat;
    }

    public String getUtilisat() {
        return utilisat;
    }

    public void setUtilisat(String utilisat) {
        this.utilisat = utilisat;
    }

    public String getDModif() {
        return dModif;
    }

    public void setDModif(String dModif) {
        this.dModif = dModif;
    }

    public String getHModif() {
        return hModif;
    }

    public void setHModif(String hModif) {
        this.hModif = hModif;
    }

    public String getPorts() {
        return ports;
    }

    public void setPorts(String ports) {
        this.ports = ports;
    }

    public BigDecimal getManifold() {
        return manifold;
    }

    public void setManifold(BigDecimal manifold) {
        this.manifold = manifold;
    }

    public BigDecimal getJaugeUms() {
        return jaugeUms;
    }

    public void setJaugeUms(BigDecimal jaugeUms) {
        this.jaugeUms = jaugeUms;
    }

    public Short getConsignes() {
        return consignes;
    }

    public void setConsignes(Short consignes) {
        this.consignes = consignes;
    }

    public String getDateConsignes() {
        return dateConsignes;
    }

    public void setDateConsignes(String dateConsignes) {
        this.dateConsignes = dateConsignes;
    }

    public String getPuisPropAv() {
        return puisPropAv;
    }

    public void setPuisPropAv(String puisPropAv) {
        this.puisPropAv = puisPropAv;
    }

    public String getPuisPropAr() {
        return puisPropAr;
    }

    public void setPuisPropAr(String puisPropAr) {
        this.puisPropAr = puisPropAr;
    }

    public String getNomCourt() {
        return nomCourt;
    }

    public void setNomCourt(String nomCourt) {
        this.nomCourt = nomCourt;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public BigDecimal getLongueurPorte() {
        return longueurPorte;
    }

    public void setLongueurPorte(BigDecimal longueurPorte) {
        this.longueurPorte = longueurPorte;
    }

    public String getNumeroOmi() {
        return numeroOmi;
    }

    public void setNumeroOmi(String numeroOmi) {
        this.numeroOmi = numeroOmi;
    }

    public String getNumeroCertificat() {
        return numeroCertificat;
    }

    public void setNumeroCertificat(String numeroCertificat) {
        this.numeroCertificat = numeroCertificat;
    }

    public String getDateCertificat() {
        return dateCertificat;
    }

    public void setDateCertificat(String dateCertificat) {
        this.dateCertificat = dateCertificat;
    }

    public String getDatePeremption() {
        return datePeremption;
    }

    public void setDatePeremption(String datePeremption) {
        this.datePeremption = datePeremption;
    }

    public String getNiveauSurete() {
        return niveauSurete;
    }

    public void setNiveauSurete(String niveauSurete) {
        this.niveauSurete = niveauSurete;
    }

    public String getOrganismeCertification() {
        return organismeCertification;
    }

    public void setOrganismeCertification(String organismeCertification) {
        this.organismeCertification = organismeCertification;
    }

    public String getPaysCertificat() {
        return paysCertificat;
    }

    public void setPaysCertificat(String paysCertificat) {
        this.paysCertificat = paysCertificat;
    }

    public BigInteger getEspaceOuvert() {
        return espaceOuvert;
    }

    public void setEspaceOuvert(BigInteger espaceOuvert) {
        this.espaceOuvert = espaceOuvert;
    }

    public BigInteger getNombreCale() {
        return nombreCale;
    }

    public void setNombreCale(BigInteger nombreCale) {
        this.nombreCale = nombreCale;
    }

    public BigDecimal getTonnageDeb() {
        return tonnageDeb;
    }

    public void setTonnageDeb(BigDecimal tonnageDeb) {
        this.tonnageDeb = tonnageDeb;
    }

    public BigDecimal getTonnageEmb() {
        return tonnageEmb;
    }

    public void setTonnageEmb(BigDecimal tonnageEmb) {
        this.tonnageEmb = tonnageEmb;
    }

    public Short getValideCapitainerie() {
        return valideCapitainerie;
    }

    public void setValideCapitainerie(Short valideCapitainerie) {
        this.valideCapitainerie = valideCapitainerie;
    }

    public Short getOkCapitaine() {
        return okCapitaine;
    }

    public void setOkCapitaine(Short okCapitaine) {
        this.okCapitaine = okCapitaine;
    }

    public String getDateCapitaine() {
        return dateCapitaine;
    }

    public void setDateCapitaine(String dateCapitaine) {
        this.dateCapitaine = dateCapitaine;
    }

    public String getHeureCapitaine() {
        return heureCapitaine;
    }

    public void setHeureCapitaine(String heureCapitaine) {
        this.heureCapitaine = heureCapitaine;
    }

    public Short getNavirePression() {
        return navirePression;
    }

    public void setNavirePression(Short navirePression) {
        this.navirePression = navirePression;
    }
//
//    public String getDateDebutRelache() {
//        return dateDebutRelache;
//    }
//
//    public void setDateDebutRelache(String dateDebutRelache) {
//        this.dateDebutRelache = dateDebutRelache;
//    }

//    public String getDateFinRelache() {
//        return dateFinRelache;
//    }
//
//    public void setDateFinRelache(String dateFinRelache) {
//        this.dateFinRelache = dateFinRelache;
//    }

    @XmlTransient
    public Collection<Escale> getEscaleCollection() {
        return escaleCollection;
    }

    public void setEscaleCollection(Collection<Escale> escaleCollection) {
        this.escaleCollection = escaleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nacleunik != null ? nacleunik.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Navire)) {
            return false;
        }
        Navire other = (Navire) object;
        if ((this.nacleunik == null && other.nacleunik != null) || (this.nacleunik != null && !this.nacleunik.equals(other.nacleunik))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Navire[ nacleunik=" + nacleunik + " ]";
    }
    
}
