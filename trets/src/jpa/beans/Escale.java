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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(catalog = "", schema = "PPNCARGO", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NUMERO"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Escale.findAll", query = "SELECT e FROM Escale e")
    , @NamedQuery(name = "Escale.findByEscleunik", query = "SELECT e FROM Escale e WHERE e.escleunik = :escleunik")
    , @NamedQuery(name = "Escale.findByNumero", query = "SELECT e FROM Escale e WHERE e.numero = :numero")
    , @NamedQuery(name = "Escale.findByAgent", query = "SELECT e FROM Escale e WHERE e.agent = :agent")
    , @NamedQuery(name = "Escale.findByManuten", query = "SELECT e FROM Escale e WHERE e.manuten = :manuten")
    , @NamedQuery(name = "Escale.findByFiliere", query = "SELECT e FROM Escale e WHERE e.filiere = :filiere")
    , @NamedQuery(name = "Escale.findByRadio", query = "SELECT e FROM Escale e WHERE e.radio = :radio")
    , @NamedQuery(name = "Escale.findByService", query = "SELECT e FROM Escale e WHERE e.service = :service")
    , @NamedQuery(name = "Escale.findByIdentite", query = "SELECT e FROM Escale e WHERE e.identite = :identite")
    , @NamedQuery(name = "Escale.findByLigne", query = "SELECT e FROM Escale e WHERE e.ligne = :ligne")
    , @NamedQuery(name = "Escale.findByMoisStat", query = "SELECT e FROM Escale e WHERE e.moisStat = :moisStat")
    , @NamedQuery(name = "Escale.findByCapitaine", query = "SELECT e FROM Escale e WHERE e.capitaine = :capitaine")
    , @NamedQuery(name = "Escale.findByConstruc", query = "SELECT e FROM Escale e WHERE e.construc = :construc")
    , @NamedQuery(name = "Escale.findByObjet", query = "SELECT e FROM Escale e WHERE e.objet = :objet")
    , @NamedQuery(name = "Escale.findByEtat", query = "SELECT e FROM Escale e WHERE e.etat = :etat")
    , @NamedQuery(name = "Escale.findByNombre", query = "SELECT e FROM Escale e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Escale.findByNbAtt", query = "SELECT e FROM Escale e WHERE e.nbAtt = :nbAtt")
    , @NamedQuery(name = "Escale.findBySejour", query = "SELECT e FROM Escale e WHERE e.sejour = :sejour")
    , @NamedQuery(name = "Escale.findByDuree", query = "SELECT e FROM Escale e WHERE e.duree = :duree")
    , @NamedQuery(name = "Escale.findByTravail", query = "SELECT e FROM Escale e WHERE e.travail = :travail")
    , @NamedQuery(name = "Escale.findBySituat", query = "SELECT e FROM Escale e WHERE e.situat = :situat")
    , @NamedQuery(name = "Escale.findByArrivee", query = "SELECT e FROM Escale e WHERE e.arrivee = :arrivee")
    , @NamedQuery(name = "Escale.findByArrH", query = "SELECT e FROM Escale e WHERE e.arrH = :arrH")
    , @NamedQuery(name = "Escale.findByMouill", query = "SELECT e FROM Escale e WHERE e.mouill = :mouill")
    , @NamedQuery(name = "Escale.findByMouillh", query = "SELECT e FROM Escale e WHERE e.mouillh = :mouillh")
    , @NamedQuery(name = "Escale.findByPilEnt", query = "SELECT e FROM Escale e WHERE e.pilEnt = :pilEnt")
    , @NamedQuery(name = "Escale.findByPilEnth", query = "SELECT e FROM Escale e WHERE e.pilEnth = :pilEnth")
    , @NamedQuery(name = "Escale.findByRemE", query = "SELECT e FROM Escale e WHERE e.remE = :remE")
    , @NamedQuery(name = "Escale.findByDebut", query = "SELECT e FROM Escale e WHERE e.debut = :debut")
    , @NamedQuery(name = "Escale.findByDebH", query = "SELECT e FROM Escale e WHERE e.debH = :debH")
    , @NamedQuery(name = "Escale.findByDebOpe", query = "SELECT e FROM Escale e WHERE e.debOpe = :debOpe")
    , @NamedQuery(name = "Escale.findByDebOpeh", query = "SELECT e FROM Escale e WHERE e.debOpeh = :debOpeh")
    , @NamedQuery(name = "Escale.findByAttAr", query = "SELECT e FROM Escale e WHERE e.attAr = :attAr")
    , @NamedQuery(name = "Escale.findByMotAr", query = "SELECT e FROM Escale e WHERE e.motAr = :motAr")
    , @NamedQuery(name = "Escale.findByCondAr", query = "SELECT e FROM Escale e WHERE e.condAr = :condAr")
    , @NamedQuery(name = "Escale.findByPortAttac", query = "SELECT e FROM Escale e WHERE e.portAttac = :portAttac")
    , @NamedQuery(name = "Escale.findByRemS", query = "SELECT e FROM Escale e WHERE e.remS = :remS")
    , @NamedQuery(name = "Escale.findByFinOpe", query = "SELECT e FROM Escale e WHERE e.finOpe = :finOpe")
    , @NamedQuery(name = "Escale.findByFinOpeh", query = "SELECT e FROM Escale e WHERE e.finOpeh = :finOpeh")
    , @NamedQuery(name = "Escale.findByPilSor", query = "SELECT e FROM Escale e WHERE e.pilSor = :pilSor")
    , @NamedQuery(name = "Escale.findByPilSorh", query = "SELECT e FROM Escale e WHERE e.pilSorh = :pilSorh")
    , @NamedQuery(name = "Escale.findByDepart", query = "SELECT e FROM Escale e WHERE e.depart = :depart")
    , @NamedQuery(name = "Escale.findByDepH", query = "SELECT e FROM Escale e WHERE e.depH = :depH")
    , @NamedQuery(name = "Escale.findByFin", query = "SELECT e FROM Escale e WHERE e.fin = :fin")
    , @NamedQuery(name = "Escale.findByFinH", query = "SELECT e FROM Escale e WHERE e.finH = :finH")
    , @NamedQuery(name = "Escale.findByAttDp", query = "SELECT e FROM Escale e WHERE e.attDp = :attDp")
    , @NamedQuery(name = "Escale.findByMotDp", query = "SELECT e FROM Escale e WHERE e.motDp = :motDp")
    , @NamedQuery(name = "Escale.findByCondDp", query = "SELECT e FROM Escale e WHERE e.condDp = :condDp")
    , @NamedQuery(name = "Escale.findByFoBrd", query = "SELECT e FROM Escale e WHERE e.foBrd = :foBrd")
    , @NamedQuery(name = "Escale.findByDoBrd", query = "SELECT e FROM Escale e WHERE e.doBrd = :doBrd")
    , @NamedQuery(name = "Escale.findByFoStg", query = "SELECT e FROM Escale e WHERE e.foStg = :foStg")
    , @NamedQuery(name = "Escale.findByDoStg", query = "SELECT e FROM Escale e WHERE e.doStg = :doStg")
    , @NamedQuery(name = "Escale.findByEau", query = "SELECT e FROM Escale e WHERE e.eau = :eau")
    , @NamedQuery(name = "Escale.findByActivite", query = "SELECT e FROM Escale e WHERE e.activite = :activite")
    , @NamedQuery(name = "Escale.findByPerson", query = "SELECT e FROM Escale e WHERE e.person = :person")
    , @NamedQuery(name = "Escale.findByTraite", query = "SELECT e FROM Escale e WHERE e.traite = :traite")
    , @NamedQuery(name = "Escale.findByQuai", query = "SELECT e FROM Escale e WHERE e.quai = :quai")
    , @NamedQuery(name = "Escale.findByTonnage", query = "SELECT e FROM Escale e WHERE e.tonnage = :tonnage")
    , @NamedQuery(name = "Escale.findByFDate", query = "SELECT e FROM Escale e WHERE e.fDate = :fDate")
    , @NamedQuery(name = "Escale.findByFHeure", query = "SELECT e FROM Escale e WHERE e.fHeure = :fHeure")
    , @NamedQuery(name = "Escale.findByNFact", query = "SELECT e FROM Escale e WHERE e.nFact = :nFact")
    , @NamedQuery(name = "Escale.findByFEche", query = "SELECT e FROM Escale e WHERE e.fEche = :fEche")
    , @NamedQuery(name = "Escale.findByNbrTcs", query = "SELECT e FROM Escale e WHERE e.nbrTcs = :nbrTcs")
    , @NamedQuery(name = "Escale.findByNbrVeh", query = "SELECT e FROM Escale e WHERE e.nbrVeh = :nbrVeh")
    , @NamedQuery(name = "Escale.findByNbrPas", query = "SELECT e FROM Escale e WHERE e.nbrPas = :nbrPas")
    , @NamedQuery(name = "Escale.findByNbrTcst", query = "SELECT e FROM Escale e WHERE e.nbrTcst = :nbrTcst")
    , @NamedQuery(name = "Escale.findByChargN", query = "SELECT e FROM Escale e WHERE e.chargN = :chargN")
    , @NamedQuery(name = "Escale.findByNbrTcsn", query = "SELECT e FROM Escale e WHERE e.nbrTcsn = :nbrTcsn")
    , @NamedQuery(name = "Escale.findByMotif", query = "SELECT e FROM Escale e WHERE e.motif = :motif")
    , @NamedQuery(name = "Escale.findByNbrSht", query = "SELECT e FROM Escale e WHERE e.nbrSht = :nbrSht")
    , @NamedQuery(name = "Escale.findByPort", query = "SELECT e FROM Escale e WHERE e.port = :port")
    , @NamedQuery(name = "Escale.findByRendement", query = "SELECT e FROM Escale e WHERE e.rendement = :rendement")
    , @NamedQuery(name = "Escale.findByAvarie", query = "SELECT e FROM Escale e WHERE e.avarie = :avarie")
    , @NamedQuery(name = "Escale.findByNature", query = "SELECT e FROM Escale e WHERE e.nature = :nature")
    , @NamedQuery(name = "Escale.findByImportance", query = "SELECT e FROM Escale e WHERE e.importance = :importance")
    , @NamedQuery(name = "Escale.findByTopTriton", query = "SELECT e FROM Escale e WHERE e.topTriton = :topTriton")
    , @NamedQuery(name = "Escale.findByTopDanger", query = "SELECT e FROM Escale e WHERE e.topDanger = :topDanger")
    , @NamedQuery(name = "Escale.findByHauteur", query = "SELECT e FROM Escale e WHERE e.hauteur = :hauteur")
    , @NamedQuery(name = "Escale.findByLargeur", query = "SELECT e FROM Escale e WHERE e.largeur = :largeur")
    , @NamedQuery(name = "Escale.findByTopFacAg", query = "SELECT e FROM Escale e WHERE e.topFacAg = :topFacAg")
    , @NamedQuery(name = "Escale.findByTopFacMn", query = "SELECT e FROM Escale e WHERE e.topFacMn = :topFacMn")
    , @NamedQuery(name = "Escale.findByTHoraire", query = "SELECT e FROM Escale e WHERE e.tHoraire = :tHoraire")
    , @NamedQuery(name = "Escale.findByNbPortique", query = "SELECT e FROM Escale e WHERE e.nbPortique = :nbPortique")
    , @NamedQuery(name = "Escale.findByOkAgent", query = "SELECT e FROM Escale e WHERE e.okAgent = :okAgent")
    , @NamedQuery(name = "Escale.findByOkCapitaine", query = "SELECT e FROM Escale e WHERE e.okCapitaine = :okCapitaine")
    , @NamedQuery(name = "Escale.findByOkManutentionnaire", query = "SELECT e FROM Escale e WHERE e.okManutentionnaire = :okManutentionnaire")
    , @NamedQuery(name = "Escale.findByDateAgent", query = "SELECT e FROM Escale e WHERE e.dateAgent = :dateAgent")
    , @NamedQuery(name = "Escale.findByHeureAgent", query = "SELECT e FROM Escale e WHERE e.heureAgent = :heureAgent")
    , @NamedQuery(name = "Escale.findByDateCapitaine", query = "SELECT e FROM Escale e WHERE e.dateCapitaine = :dateCapitaine")
    , @NamedQuery(name = "Escale.findByHeureCapitaine", query = "SELECT e FROM Escale e WHERE e.heureCapitaine = :heureCapitaine")
    , @NamedQuery(name = "Escale.findByDateManutentionnaire", query = "SELECT e FROM Escale e WHERE e.dateManutentionnaire = :dateManutentionnaire")
    , @NamedQuery(name = "Escale.findByHeureManutentionnaire", query = "SELECT e FROM Escale e WHERE e.heureManutentionnaire = :heureManutentionnaire")
    , @NamedQuery(name = "Escale.findByProvenanceI", query = "SELECT e FROM Escale e WHERE e.provenanceI = :provenanceI")
    , @NamedQuery(name = "Escale.findByProvenanceO", query = "SELECT e FROM Escale e WHERE e.provenanceO = :provenanceO")
    , @NamedQuery(name = "Escale.findByDestinationI", query = "SELECT e FROM Escale e WHERE e.destinationI = :destinationI")
    , @NamedQuery(name = "Escale.findByDestinationF", query = "SELECT e FROM Escale e WHERE e.destinationF = :destinationF")
    , @NamedQuery(name = "Escale.findByNavire", query = "SELECT e FROM Escale e WHERE e.navire = :navire")
    , @NamedQuery(name = "Escale.findByDDemande", query = "SELECT e FROM Escale e WHERE e.dDemande = :dDemande")
    , @NamedQuery(name = "Escale.findByHDemande", query = "SELECT e FROM Escale e WHERE e.hDemande = :hDemande")
    , @NamedQuery(name = "Escale.findByReferenc", query = "SELECT e FROM Escale e WHERE e.referenc = :referenc")
    , @NamedQuery(name = "Escale.findByTotPrimeEq1", query = "SELECT e FROM Escale e WHERE e.totPrimeEq1 = :totPrimeEq1")
    , @NamedQuery(name = "Escale.findByTotPrimeEq2", query = "SELECT e FROM Escale e WHERE e.totPrimeEq2 = :totPrimeEq2")
    , @NamedQuery(name = "Escale.findByVoyage", query = "SELECT e FROM Escale e WHERE e.voyage = :voyage")
    , @NamedQuery(name = "Escale.findByNbrHtrb", query = "SELECT e FROM Escale e WHERE e.nbrHtrb = :nbrHtrb")
    , @NamedQuery(name = "Escale.findByNbrTrb", query = "SELECT e FROM Escale e WHERE e.nbrTrb = :nbrTrb")
    , @NamedQuery(name = "Escale.findByNbrNtrb", query = "SELECT e FROM Escale e WHERE e.nbrNtrb = :nbrNtrb")
    , @NamedQuery(name = "Escale.findByHrsNdf", query = "SELECT e FROM Escale e WHERE e.hrsNdf = :hrsNdf")
    , @NamedQuery(name = "Escale.findByHrsNbr", query = "SELECT e FROM Escale e WHERE e.hrsNbr = :hrsNbr")
    , @NamedQuery(name = "Escale.findByGestion", query = "SELECT e FROM Escale e WHERE e.gestion = :gestion")
    , @NamedQuery(name = "Escale.findByStatut", query = "SELECT e FROM Escale e WHERE e.statut = :statut")
    , @NamedQuery(name = "Escale.findByCreateur", query = "SELECT e FROM Escale e WHERE e.createur = :createur")
    , @NamedQuery(name = "Escale.findByDCreat", query = "SELECT e FROM Escale e WHERE e.dCreat = :dCreat")
    , @NamedQuery(name = "Escale.findByHCreat", query = "SELECT e FROM Escale e WHERE e.hCreat = :hCreat")
    , @NamedQuery(name = "Escale.findByUtilisat", query = "SELECT e FROM Escale e WHERE e.utilisat = :utilisat")
    , @NamedQuery(name = "Escale.findByDModif", query = "SELECT e FROM Escale e WHERE e.dModif = :dModif")
    , @NamedQuery(name = "Escale.findByHModif", query = "SELECT e FROM Escale e WHERE e.hModif = :hModif")
    , @NamedQuery(name = "Escale.findByFacleunik", query = "SELECT e FROM Escale e WHERE e.facleunik = :facleunik")
    , @NamedQuery(name = "Escale.findByChargB", query = "SELECT e FROM Escale e WHERE e.chargB = :chargB")
    , @NamedQuery(name = "Escale.findByPavillon", query = "SELECT e FROM Escale e WHERE e.pavillon = :pavillon")
    , @NamedQuery(name = "Escale.findByArmateur", query = "SELECT e FROM Escale e WHERE e.armateur = :armateur")
    , @NamedQuery(name = "Escale.findByJaugeNet", query = "SELECT e FROM Escale e WHERE e.jaugeNet = :jaugeNet")
    , @NamedQuery(name = "Escale.findByRedemU", query = "SELECT e FROM Escale e WHERE e.redemU = :redemU")
    , @NamedQuery(name = "Escale.findByChefPoste", query = "SELECT e FROM Escale e WHERE e.chefPoste = :chefPoste")
    , @NamedQuery(name = "Escale.findByOkFactNav", query = "SELECT e FROM Escale e WHERE e.okFactNav = :okFactNav")
    , @NamedQuery(name = "Escale.findByOkFactEqu", query = "SELECT e FROM Escale e WHERE e.okFactEqu = :okFactEqu")
    , @NamedQuery(name = "Escale.findByTraiteHier", query = "SELECT e FROM Escale e WHERE e.traiteHier = :traiteHier")
    , @NamedQuery(name = "Escale.findByDateStat", query = "SELECT e FROM Escale e WHERE e.dateStat = :dateStat")
    , @NamedQuery(name = "Escale.findByPriorite", query = "SELECT e FROM Escale e WHERE e.priorite = :priorite")
    , @NamedQuery(name = "Escale.findByDatePasseEntree", query = "SELECT e FROM Escale e WHERE e.datePasseEntree = :datePasseEntree")
    , @NamedQuery(name = "Escale.findByHeurePasseEntree", query = "SELECT e FROM Escale e WHERE e.heurePasseEntree = :heurePasseEntree")
    , @NamedQuery(name = "Escale.findByDatePasseSortie", query = "SELECT e FROM Escale e WHERE e.datePasseSortie = :datePasseSortie")
    , @NamedQuery(name = "Escale.findByHeurePasseSortie", query = "SELECT e FROM Escale e WHERE e.heurePasseSortie = :heurePasseSortie")
    , @NamedQuery(name = "Escale.findByDateArriveeRade", query = "SELECT e FROM Escale e WHERE e.dateArriveeRade = :dateArriveeRade")
    , @NamedQuery(name = "Escale.findByHeureArriveeRade", query = "SELECT e FROM Escale e WHERE e.heureArriveeRade = :heureArriveeRade")
    , @NamedQuery(name = "Escale.findByResponsableShipping", query = "SELECT e FROM Escale e WHERE e.responsableShipping = :responsableShipping")
    , @NamedQuery(name = "Escale.findByChefEscale", query = "SELECT e FROM Escale e WHERE e.chefEscale = :chefEscale")
    , @NamedQuery(name = "Escale.findByTelAgent", query = "SELECT e FROM Escale e WHERE e.telAgent = :telAgent")
    , @NamedQuery(name = "Escale.findByTelResponsableShipping", query = "SELECT e FROM Escale e WHERE e.telResponsableShipping = :telResponsableShipping")
    , @NamedQuery(name = "Escale.findByTelChefEscale", query = "SELECT e FROM Escale e WHERE e.telChefEscale = :telChefEscale")
    , @NamedQuery(name = "Escale.findByArriveeRade", query = "SELECT e FROM Escale e WHERE e.arriveeRade = :arriveeRade")
    , @NamedQuery(name = "Escale.findByArrHRade", query = "SELECT e FROM Escale e WHERE e.arrHRade = :arrHRade")
    , @NamedQuery(name = "Escale.findByDepartEffectif", query = "SELECT e FROM Escale e WHERE e.departEffectif = :departEffectif")
    , @NamedQuery(name = "Escale.findByDepHEffectif", query = "SELECT e FROM Escale e WHERE e.depHEffectif = :depHEffectif")
    , @NamedQuery(name = "Escale.findByMouillDepart", query = "SELECT e FROM Escale e WHERE e.mouillDepart = :mouillDepart")
    , @NamedQuery(name = "Escale.findByMouillhDepart", query = "SELECT e FROM Escale e WHERE e.mouillhDepart = :mouillhDepart")
    , @NamedQuery(name = "Escale.findByAgentSortie", query = "SELECT e FROM Escale e WHERE e.agentSortie = :agentSortie")
    , @NamedQuery(name = "Escale.findByTelephoneAgentSortie", query = "SELECT e FROM Escale e WHERE e.telephoneAgentSortie = :telephoneAgentSortie")
    , @NamedQuery(name = "Escale.findByOkApparaux", query = "SELECT e FROM Escale e WHERE e.okApparaux = :okApparaux")
    , @NamedQuery(name = "Escale.findByOkAssurance", query = "SELECT e FROM Escale e WHERE e.okAssurance = :okAssurance")
    , @NamedQuery(name = "Escale.findByOkDeclaration", query = "SELECT e FROM Escale e WHERE e.okDeclaration = :okDeclaration")
    , @NamedQuery(name = "Escale.findByFreeGaz", query = "SELECT e FROM Escale e WHERE e.freeGaz = :freeGaz")
    , @NamedQuery(name = "Escale.findByReparation", query = "SELECT e FROM Escale e WHERE e.reparation = :reparation")
    , @NamedQuery(name = "Escale.findByControle", query = "SELECT e FROM Escale e WHERE e.controle = :controle")
    , @NamedQuery(name = "Escale.findByAvaries", query = "SELECT e FROM Escale e WHERE e.avaries = :avaries")
    , @NamedQuery(name = "Escale.findByManutentionnaireSortie", query = "SELECT e FROM Escale e WHERE e.manutentionnaireSortie = :manutentionnaireSortie")
    , @NamedQuery(name = "Escale.findByDateStatut", query = "SELECT e FROM Escale e WHERE e.dateStatut = :dateStatut")
    , @NamedQuery(name = "Escale.findByHeureStatut", query = "SELECT e FROM Escale e WHERE e.heureStatut = :heureStatut")
    , @NamedQuery(name = "Escale.findByMobileChefEscale", query = "SELECT e FROM Escale e WHERE e.mobileChefEscale = :mobileChefEscale")
    , @NamedQuery(name = "Escale.findByMobileResponsableShipping", query = "SELECT e FROM Escale e WHERE e.mobileResponsableShipping = :mobileResponsableShipping")
    , @NamedQuery(name = "Escale.findByQuai2", query = "SELECT e FROM Escale e WHERE e.quai2 = :quai2")
    , @NamedQuery(name = "Escale.findByQuai3", query = "SELECT e FROM Escale e WHERE e.quai3 = :quai3")
    , @NamedQuery(name = "Escale.findByDateModifEta", query = "SELECT e FROM Escale e WHERE e.dateModifEta = :dateModifEta")
    , @NamedQuery(name = "Escale.findByHeureModifEta", query = "SELECT e FROM Escale e WHERE e.heureModifEta = :heureModifEta")
    , @NamedQuery(name = "Escale.findByDateAppelVigie", query = "SELECT e FROM Escale e WHERE e.dateAppelVigie = :dateAppelVigie")
    , @NamedQuery(name = "Escale.findByHeureAppelVigie", query = "SELECT e FROM Escale e WHERE e.heureAppelVigie = :heureAppelVigie")
    , @NamedQuery(name = "Escale.findByControleurVigie", query = "SELECT e FROM Escale e WHERE e.controleurVigie = :controleurVigie")
    , @NamedQuery(name = "Escale.findBySaisieNavire", query = "SELECT e FROM Escale e WHERE e.saisieNavire = :saisieNavire")
    , @NamedQuery(name = "Escale.findByDateModifEtd", query = "SELECT e FROM Escale e WHERE e.dateModifEtd = :dateModifEtd")
    , @NamedQuery(name = "Escale.findByHeureModifEtd", query = "SELECT e FROM Escale e WHERE e.heureModifEtd = :heureModifEtd")
    , @NamedQuery(name = "Escale.findByBassin", query = "SELECT e FROM Escale e WHERE e.bassin = :bassin")
    , @NamedQuery(name = "Escale.findByDateFeux", query = "SELECT e FROM Escale e WHERE e.dateFeux = :dateFeux")
    , @NamedQuery(name = "Escale.findByHeureFeux", query = "SELECT e FROM Escale e WHERE e.heureFeux = :heureFeux")
    , @NamedQuery(name = "Escale.findByDateFeuxSortie", query = "SELECT e FROM Escale e WHERE e.dateFeuxSortie = :dateFeuxSortie")
    , @NamedQuery(name = "Escale.findByHeureFeuxSortie", query = "SELECT e FROM Escale e WHERE e.heureFeuxSortie = :heureFeuxSortie")
    , @NamedQuery(name = "Escale.findByPosition", query = "SELECT e FROM Escale e WHERE e.position = :position")
    , @NamedQuery(name = "Escale.findByEtaNavire", query = "SELECT e FROM Escale e WHERE e.etaNavire = :etaNavire")
    , @NamedQuery(name = "Escale.findByHEtaNavire", query = "SELECT e FROM Escale e WHERE e.hEtaNavire = :hEtaNavire")
    , @NamedQuery(name = "Escale.findByModifEtaNavire", query = "SELECT e FROM Escale e WHERE e.modifEtaNavire = :modifEtaNavire")
    , @NamedQuery(name = "Escale.findByModifHEtaNavire", query = "SELECT e FROM Escale e WHERE e.modifHEtaNavire = :modifHEtaNavire")
    , @NamedQuery(name = "Escale.findByUtilisatEtaNavire", query = "SELECT e FROM Escale e WHERE e.utilisatEtaNavire = :utilisatEtaNavire")
    , @NamedQuery(name = "Escale.findByTeauArEntree", query = "SELECT e FROM Escale e WHERE e.teauArEntree = :teauArEntree")
    , @NamedQuery(name = "Escale.findByTeauArSortie", query = "SELECT e FROM Escale e WHERE e.teauArSortie = :teauArSortie")
    , @NamedQuery(name = "Escale.findByOkAgentSortie", query = "SELECT e FROM Escale e WHERE e.okAgentSortie = :okAgentSortie")
    , @NamedQuery(name = "Escale.findByOkCapitaineSortie", query = "SELECT e FROM Escale e WHERE e.okCapitaineSortie = :okCapitaineSortie")
    , @NamedQuery(name = "Escale.findByDateAgentSortie", query = "SELECT e FROM Escale e WHERE e.dateAgentSortie = :dateAgentSortie")
    , @NamedQuery(name = "Escale.findByHeureAgentSortie", query = "SELECT e FROM Escale e WHERE e.heureAgentSortie = :heureAgentSortie")
    , @NamedQuery(name = "Escale.findByDateCapitaineSortie", query = "SELECT e FROM Escale e WHERE e.dateCapitaineSortie = :dateCapitaineSortie")
    , @NamedQuery(name = "Escale.findByHeureCapitaineSortie", query = "SELECT e FROM Escale e WHERE e.heureCapitaineSortie = :heureCapitaineSortie")
    , @NamedQuery(name = "Escale.findByEMail", query = "SELECT e FROM Escale e WHERE e.eMail = :eMail")
    , @NamedQuery(name = "Escale.findByLongitudeRade", query = "SELECT e FROM Escale e WHERE e.longitudeRade = :longitudeRade")
    , @NamedQuery(name = "Escale.findByLatitudeRade", query = "SELECT e FROM Escale e WHERE e.latitudeRade = :latitudeRade")
    , @NamedQuery(name = "Escale.findByNumeroVetting", query = "SELECT e FROM Escale e WHERE e.numeroVetting = :numeroVetting")
    , @NamedQuery(name = "Escale.findByTypeManutention", query = "SELECT e FROM Escale e WHERE e.typeManutention = :typeManutention")
    , @NamedQuery(name = "Escale.findByTonnageDeb", query = "SELECT e FROM Escale e WHERE e.tonnageDeb = :tonnageDeb")
    , @NamedQuery(name = "Escale.findByTonnageEmb", query = "SELECT e FROM Escale e WHERE e.tonnageEmb = :tonnageEmb")
    , @NamedQuery(name = "Escale.findByPiloteEntree", query = "SELECT e FROM Escale e WHERE e.piloteEntree = :piloteEntree")
    , @NamedQuery(name = "Escale.findByPiloteSortie", query = "SELECT e FROM Escale e WHERE e.piloteSortie = :piloteSortie")
    , @NamedQuery(name = "Escale.findByDateAppelVigieSortie", query = "SELECT e FROM Escale e WHERE e.dateAppelVigieSortie = :dateAppelVigieSortie")
    , @NamedQuery(name = "Escale.findByHeureAppelVigieSortie", query = "SELECT e FROM Escale e WHERE e.heureAppelVigieSortie = :heureAppelVigieSortie")
    , @NamedQuery(name = "Escale.findByDDebPilote", query = "SELECT e FROM Escale e WHERE e.dDebPilote = :dDebPilote")
    , @NamedQuery(name = "Escale.findByHDebPilote", query = "SELECT e FROM Escale e WHERE e.hDebPilote = :hDebPilote")
    , @NamedQuery(name = "Escale.findByDEmbPilote", query = "SELECT e FROM Escale e WHERE e.dEmbPilote = :dEmbPilote")
    , @NamedQuery(name = "Escale.findByHEmbPilote", query = "SELECT e FROM Escale e WHERE e.hEmbPilote = :hEmbPilote")
    , @NamedQuery(name = "Escale.findByDateProgrammation", query = "SELECT e FROM Escale e WHERE e.dateProgrammation = :dateProgrammation")
    , @NamedQuery(name = "Escale.findByHeureProgrammation", query = "SELECT e FROM Escale e WHERE e.heureProgrammation = :heureProgrammation")
    , @NamedQuery(name = "Escale.findByDureeProgrammation", query = "SELECT e FROM Escale e WHERE e.dureeProgrammation = :dureeProgrammation")
    , @NamedQuery(name = "Escale.findByStatutConferenceEntree", query = "SELECT e FROM Escale e WHERE e.statutConferenceEntree = :statutConferenceEntree")
    , @NamedQuery(name = "Escale.findByDateConferenceEntree", query = "SELECT e FROM Escale e WHERE e.dateConferenceEntree = :dateConferenceEntree")
    , @NamedQuery(name = "Escale.findByHeureConferenceEntree", query = "SELECT e FROM Escale e WHERE e.heureConferenceEntree = :heureConferenceEntree")
    , @NamedQuery(name = "Escale.findByStatutConferenceSortie", query = "SELECT e FROM Escale e WHERE e.statutConferenceSortie = :statutConferenceSortie")
    , @NamedQuery(name = "Escale.findByDateConferenceSortie", query = "SELECT e FROM Escale e WHERE e.dateConferenceSortie = :dateConferenceSortie")
    , @NamedQuery(name = "Escale.findByHeureConferenceSortie", query = "SELECT e FROM Escale e WHERE e.heureConferenceSortie = :heureConferenceSortie")
    , @NamedQuery(name = "Escale.findByTonnageDebDeclare", query = "SELECT e FROM Escale e WHERE e.tonnageDebDeclare = :tonnageDebDeclare")
    , @NamedQuery(name = "Escale.findByTonnageEmbDeclare", query = "SELECT e FROM Escale e WHERE e.tonnageEmbDeclare = :tonnageEmbDeclare")
    , @NamedQuery(name = "Escale.findByNbreBl", query = "SELECT e FROM Escale e WHERE e.nbreBl = :nbreBl")
    , @NamedQuery(name = "Escale.findByTonnageFPilotageImport", query = "SELECT e FROM Escale e WHERE e.tonnageFPilotageImport = :tonnageFPilotageImport")
    , @NamedQuery(name = "Escale.findByTonnageFPilotageExport", query = "SELECT e FROM Escale e WHERE e.tonnageFPilotageExport = :tonnageFPilotageExport")
    , @NamedQuery(name = "Escale.findByTonnageFPilotageTotal", query = "SELECT e FROM Escale e WHERE e.tonnageFPilotageTotal = :tonnageFPilotageTotal")
    , @NamedQuery(name = "Escale.findByTonnageMaxFacture", query = "SELECT e FROM Escale e WHERE e.tonnageMaxFacture = :tonnageMaxFacture")
    , @NamedQuery(name = "Escale.findByNumEscCt", query = "SELECT e FROM Escale e WHERE e.numEscCt = :numEscCt")
    , @NamedQuery(name = "Escale.findByOkDepotManifesteEntree", query = "SELECT e FROM Escale e WHERE e.okDepotManifesteEntree = :okDepotManifesteEntree")
    , @NamedQuery(name = "Escale.findByDateDepotManifesteEntree", query = "SELECT e FROM Escale e WHERE e.dateDepotManifesteEntree = :dateDepotManifesteEntree")
    , @NamedQuery(name = "Escale.findByHeureDepotManifesteEntree", query = "SELECT e FROM Escale e WHERE e.heureDepotManifesteEntree = :heureDepotManifesteEntree")
    , @NamedQuery(name = "Escale.findByOkDepotManifesteSortie", query = "SELECT e FROM Escale e WHERE e.okDepotManifesteSortie = :okDepotManifesteSortie")
    , @NamedQuery(name = "Escale.findByDateDepotManifesteSortie", query = "SELECT e FROM Escale e WHERE e.dateDepotManifesteSortie = :dateDepotManifesteSortie")
    , @NamedQuery(name = "Escale.findByHeureDepotManifesteSortie", query = "SELECT e FROM Escale e WHERE e.heureDepotManifesteSortie = :heureDepotManifesteSortie")
    , @NamedQuery(name = "Escale.findByNombreConteneur20Import", query = "SELECT e FROM Escale e WHERE e.nombreConteneur20Import = :nombreConteneur20Import")
    , @NamedQuery(name = "Escale.findByNombreConteneur40Import", query = "SELECT e FROM Escale e WHERE e.nombreConteneur40Import = :nombreConteneur40Import")
    , @NamedQuery(name = "Escale.findByNombreConteneur20Export", query = "SELECT e FROM Escale e WHERE e.nombreConteneur20Export = :nombreConteneur20Export")
    , @NamedQuery(name = "Escale.findByNombreConteneur40Export", query = "SELECT e FROM Escale e WHERE e.nombreConteneur40Export = :nombreConteneur40Export")
    , @NamedQuery(name = "Escale.findByNombreConteneur20Transbi", query = "SELECT e FROM Escale e WHERE e.nombreConteneur20Transbi = :nombreConteneur20Transbi")
    , @NamedQuery(name = "Escale.findByNombreConteneur40Transbi", query = "SELECT e FROM Escale e WHERE e.nombreConteneur40Transbi = :nombreConteneur40Transbi")
    , @NamedQuery(name = "Escale.findByNombreConteneur20Transbe", query = "SELECT e FROM Escale e WHERE e.nombreConteneur20Transbe = :nombreConteneur20Transbe")
    , @NamedQuery(name = "Escale.findByNombreConteneur40Transbe", query = "SELECT e FROM Escale e WHERE e.nombreConteneur40Transbe = :nombreConteneur40Transbe")
    , @NamedQuery(name = "Escale.findByTopTdr", query = "SELECT e FROM Escale e WHERE e.topTdr = :topTdr")
    , @NamedQuery(name = "Escale.findByTopTdrDate", query = "SELECT e FROM Escale e WHERE e.topTdrDate = :topTdrDate")
    , @NamedQuery(name = "Escale.findByTopTdrHeure", query = "SELECT e FROM Escale e WHERE e.topTdrHeure = :topTdrHeure")
    , @NamedQuery(name = "Escale.findByTopTimesheet", query = "SELECT e FROM Escale e WHERE e.topTimesheet = :topTimesheet")
    , @NamedQuery(name = "Escale.findByTopTimesheetDate", query = "SELECT e FROM Escale e WHERE e.topTimesheetDate = :topTimesheetDate")
    , @NamedQuery(name = "Escale.findByTopTimesheetHeure", query = "SELECT e FROM Escale e WHERE e.topTimesheetHeure = :topTimesheetHeure")
    , @NamedQuery(name = "Escale.findByTopTdrMensuel", query = "SELECT e FROM Escale e WHERE e.topTdrMensuel = :topTdrMensuel")
    , @NamedQuery(name = "Escale.findByTopTdrMensuelDate", query = "SELECT e FROM Escale e WHERE e.topTdrMensuelDate = :topTdrMensuelDate")
    , @NamedQuery(name = "Escale.findByTopTdrMensuelHeure", query = "SELECT e FROM Escale e WHERE e.topTdrMensuelHeure = :topTdrMensuelHeure")
    , @NamedQuery(name = "Escale.findByDateMatrice", query = "SELECT e FROM Escale e WHERE e.dateMatrice = :dateMatrice")
    , @NamedQuery(name = "Escale.findByDateBrouillard", query = "SELECT e FROM Escale e WHERE e.dateBrouillard = :dateBrouillard")
    , @NamedQuery(name = "Escale.findByDateFacture", query = "SELECT e FROM Escale e WHERE e.dateFacture = :dateFacture")
    , @NamedQuery(name = "Escale.findByDateValidationTimesheet", query = "SELECT e FROM Escale e WHERE e.dateValidationTimesheet = :dateValidationTimesheet")
    , @NamedQuery(name = "Escale.findByUtilisatValidationTimesheet", query = "SELECT e FROM Escale e WHERE e.utilisatValidationTimesheet = :utilisatValidationTimesheet")
    , @NamedQuery(name = "Escale.findByUtilisatMatrice", query = "SELECT e FROM Escale e WHERE e.utilisatMatrice = :utilisatMatrice")
    , @NamedQuery(name = "Escale.findByUtilisatBrouillard", query = "SELECT e FROM Escale e WHERE e.utilisatBrouillard = :utilisatBrouillard")
    , @NamedQuery(name = "Escale.findByUtilisatFacture", query = "SELECT e FROM Escale e WHERE e.utilisatFacture = :utilisatFacture")
    , @NamedQuery(name = "Escale.findByDateEscaleInitiale", query = "SELECT e FROM Escale e WHERE e.dateEscaleInitiale = :dateEscaleInitiale")})
public class Escale implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 19, scale = 0)
    private BigDecimal escleunik;
    @Size(max = 12)
    @Column(length = 12)
    private String numero;
    @Size(max = 8)
    @Column(length = 8)
    private String agent;
    @Size(max = 8)
    @Column(length = 8)
    private String manuten;
    @Size(max = 8)
    @Column(length = 8)
    private String filiere;
    @Size(max = 12)
    @Column(length = 12)
    private String radio;
    private Integer service;
    @Size(max = 12)
    @Column(length = 12)
    private String identite;
    @Size(max = 8)
    @Column(length = 8)
    private String ligne;
    @Size(max = 2)
    @Column(name = "MOIS_STAT", length = 2)
    private String moisStat;
    @Size(max = 25)
    @Column(length = 25)
    private String capitaine;
    private Integer construc;
    @Size(max = 8)
    @Column(name = "OBJET_", length = 8)
    private String objet;
    @Size(max = 8)
    @Column(length = 8)
    private String etat;
    private BigInteger nombre;
    @Column(name = "NB_ATT")
    private Short nbAtt;
    private BigInteger sejour;
    private BigInteger duree;
    private BigInteger travail;
    @Size(max = 8)
    @Column(length = 8)
    private String situat;
    @Size(max = 8)
    @Column(length = 8)
    private String arrivee;
    @Size(max = 4)
    @Column(name = "ARR_H", length = 4)
    private String arrH;
    @Size(max = 8)
    @Column(length = 8)
    private String mouill;
    @Size(max = 4)
    @Column(length = 4)
    private String mouillh;
    @Size(max = 8)
    @Column(name = "PIL_ENT", length = 8)
    private String pilEnt;
    @Size(max = 4)
    @Column(name = "PIL_ENTH", length = 4)
    private String pilEnth;
    @Column(name = "REM_E")
    private Short remE;
    @Size(max = 8)
    @Column(length = 8)
    private String debut;
    @Size(max = 4)
    @Column(name = "DEB_H", length = 4)
    private String debH;
    @Size(max = 8)
    @Column(name = "DEB_OPE", length = 8)
    private String debOpe;
    @Size(max = 4)
    @Column(name = "DEB_OPEH", length = 4)
    private String debOpeh;
    @Column(name = "ATT_AR")
    private BigInteger attAr;
    @Size(max = 8)
    @Column(name = "MOT_AR", length = 8)
    private String motAr;
    @Size(max = 8)
    @Column(name = "COND_AR", length = 8)
    private String condAr;
    @Size(max = 8)
    @Column(name = "PORT_ATTAC", length = 8)
    private String portAttac;
    @Column(name = "REM_S")
    private Short remS;
    @Size(max = 8)
    @Column(name = "FIN_OPE", length = 8)
    private String finOpe;
    @Size(max = 4)
    @Column(name = "FIN_OPEH", length = 4)
    private String finOpeh;
    @Size(max = 8)
    @Column(name = "PIL_SOR", length = 8)
    private String pilSor;
    @Size(max = 4)
    @Column(name = "PIL_SORH", length = 4)
    private String pilSorh;
    @Size(max = 8)
    @Column(length = 8)
    private String depart;
    @Size(max = 4)
    @Column(name = "DEP_H", length = 4)
    private String depH;
    @Size(max = 8)
    @Column(name = "FIN_", length = 8)
    private String fin;
    @Size(max = 4)
    @Column(name = "FIN_H", length = 4)
    private String finH;
    @Column(name = "ATT_DP")
    private BigInteger attDp;
    @Size(max = 8)
    @Column(name = "MOT_DP", length = 8)
    private String motDp;
    @Size(max = 8)
    @Column(name = "COND_DP", length = 8)
    private String condDp;
    @Column(name = "FO_BRD", precision = 63)
    private Double foBrd;
    @Column(name = "DO_BRD", precision = 63)
    private Double doBrd;
    @Column(name = "FO_STG", precision = 63)
    private Double foStg;
    @Column(name = "DO_STG", precision = 63)
    private Double doStg;
    @Column(precision = 63)
    private Double eau;
    @Size(max = 8)
    @Column(length = 8)
    private String activite;
    private BigInteger person;
    private BigInteger traite;
    @Size(max = 8)
    @Column(length = 8)
    private String quai;
    @Column(precision = 19, scale = 6)
    private BigDecimal tonnage;
    @Size(max = 8)
    @Column(name = "F_DATE", length = 8)
    private String fDate;
    @Size(max = 4)
    @Column(name = "F_HEURE", length = 4)
    private String fHeure;
    @Size(max = 12)
    @Column(name = "N_FACT", length = 12)
    private String nFact;
    @Size(max = 8)
    @Column(name = "F_ECHE", length = 8)
    private String fEche;
    @Column(name = "NBR_TCS", precision = 126)
    private Double nbrTcs;
    @Column(name = "NBR_VEH", precision = 126)
    private Double nbrVeh;
    @Column(name = "NBR_PAS", precision = 126)
    private Double nbrPas;
    @Column(name = "NBR_TCST", precision = 126)
    private Double nbrTcst;
    @Column(name = "CHARG_N", precision = 19, scale = 6)
    private BigDecimal chargN;
    @Column(name = "NBR_TCSN", precision = 126)
    private Double nbrTcsn;
    @Size(max = 8)
    @Column(length = 8)
    private String motif;
    @Column(name = "NBR_SHT")
    private Integer nbrSht;
    @Size(max = 2)
    @Column(length = 2)
    private String port;
    private BigInteger rendement;
    private Short avarie;
    @Size(max = 8)
    @Column(length = 8)
    private String nature;
    @Size(max = 8)
    @Column(length = 8)
    private String importance;
    @Column(name = "TOP_TRITON")
    private Integer topTriton;
    @Column(name = "TOP_DANGER")
    private Short topDanger;
    @Column(precision = 63)
    private Double hauteur;
    @Column(precision = 63)
    private Double largeur;
    @Column(name = "TOP_FAC_AG")
    private Short topFacAg;
    @Column(name = "TOP_FAC_MN")
    private Short topFacMn;
    @Size(max = 8)
    @Column(name = "T_HORAIRE", length = 8)
    private String tHoraire;
    @Column(name = "NB_PORTIQUE")
    private Short nbPortique;
    @Column(name = "OK_AGENT")
    private Short okAgent;
    @Column(name = "OK_CAPITAINE")
    private Short okCapitaine;
    @Column(name = "OK_MANUTENTIONNAIRE")
    private Short okManutentionnaire;
    @Size(max = 8)
    @Column(name = "DATE_AGENT", length = 8)
    private String dateAgent;
    @Size(max = 4)
    @Column(name = "HEURE_AGENT", length = 4)
    private String heureAgent;
    @Size(max = 8)
    @Column(name = "DATE_CAPITAINE", length = 8)
    private String dateCapitaine;
    @Size(max = 4)
    @Column(name = "HEURE_CAPITAINE", length = 4)
    private String heureCapitaine;
    @Size(max = 8)
    @Column(name = "DATE_MANUTENTIONNAIRE", length = 8)
    private String dateManutentionnaire;
    @Size(max = 4)
    @Column(name = "HEURE_MANUTENTIONNAIRE", length = 4)
    private String heureManutentionnaire;
    @Size(max = 8)
    @Column(name = "PROVENANCE_I", length = 8)
    private String provenanceI;
    @Size(max = 8)
    @Column(name = "PROVENANCE_O", length = 8)
    private String provenanceO;
    @Size(max = 8)
    @Column(name = "DESTINATION_I", length = 8)
    private String destinationI;
    @Size(max = 8)
    @Column(name = "DESTINATION_F", length = 8)
    private String destinationF;
    @Size(max = 40)
    @Column(length = 40)
    private String navire;
    @Size(max = 8)
    @Column(name = "D_DEMANDE", length = 8)
    private String dDemande;
    @Size(max = 4)
    @Column(name = "H_DEMANDE", length = 4)
    private String hDemande;
    @Size(max = 12)
    @Column(length = 12)
    private String referenc;
    @Column(name = "TOT_PRIME_EQ1", precision = 19, scale = 6)
    private BigDecimal totPrimeEq1;
    @Column(name = "TOT_PRIME_EQ2", precision = 19, scale = 6)
    private BigDecimal totPrimeEq2;
    @Size(max = 12)
    @Column(length = 12)
    private String voyage;
    @Column(name = "NBR_HTRB")
    private Integer nbrHtrb;
    @Column(name = "NBR_TRB")
    private Integer nbrTrb;
    @Column(name = "NBR_NTRB")
    private Integer nbrNtrb;
    @Column(name = "HRS_NDF")
    private Integer hrsNdf;
    @Column(name = "HRS_NBR")
    private Integer hrsNbr;
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
    private BigInteger facleunik;
    @Column(name = "CHARG_B", precision = 19, scale = 6)
    private BigDecimal chargB;
    @Size(max = 8)
    @Column(length = 8)
    private String pavillon;
    @Size(max = 8)
    @Column(length = 8)
    private String armateur;
    @Column(name = "JAUGE_NET", precision = 19, scale = 6)
    private BigDecimal jaugeNet;
    @Column(name = "REDEM_U", precision = 19, scale = 6)
    private BigDecimal redemU;
    @Size(max = 8)
    @Column(name = "CHEF_POSTE", length = 8)
    private String chefPoste;
    @Column(name = "OK_FACT_NAV")
    private Short okFactNav;
    @Column(name = "OK_FACT_EQU")
    private Short okFactEqu;
    @Column(name = "TRAITE_HIER", precision = 19, scale = 6)
    private BigDecimal traiteHier;
    @Column(name = "DATE_STAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStat;
    @Size(max = 8)
    @Column(length = 8)
    private String priorite;
    @Size(max = 8)
    @Column(name = "DATE_PASSE_ENTREE", length = 8)
    private String datePasseEntree;
    @Size(max = 4)
    @Column(name = "HEURE_PASSE_ENTREE", length = 4)
    private String heurePasseEntree;
    @Size(max = 8)
    @Column(name = "DATE_PASSE_SORTIE", length = 8)
    private String datePasseSortie;
    @Size(max = 4)
    @Column(name = "HEURE_PASSE_SORTIE", length = 4)
    private String heurePasseSortie;
    @Size(max = 8)
    @Column(name = "DATE_ARRIVEE_RADE", length = 8)
    private String dateArriveeRade;
    @Size(max = 4)
    @Column(name = "HEURE_ARRIVEE_RADE", length = 4)
    private String heureArriveeRade;
    @Size(max = 20)
    @Column(name = "RESPONSABLE_SHIPPING", length = 20)
    private String responsableShipping;
    @Size(max = 20)
    @Column(name = "CHEF_ESCALE", length = 20)
    private String chefEscale;
    @Size(max = 20)
    @Column(name = "TEL_AGENT", length = 20)
    private String telAgent;
    @Size(max = 20)
    @Column(name = "TEL_RESPONSABLE_SHIPPING", length = 20)
    private String telResponsableShipping;
    @Size(max = 20)
    @Column(name = "TEL_CHEF_ESCALE", length = 20)
    private String telChefEscale;
    @Size(max = 8)
    @Column(name = "ARRIVEE_RADE", length = 8)
    private String arriveeRade;
    @Size(max = 4)
    @Column(name = "ARR_H_RADE", length = 4)
    private String arrHRade;
    @Size(max = 8)
    @Column(name = "DEPART_EFFECTIF", length = 8)
    private String departEffectif;
    @Size(max = 4)
    @Column(name = "DEP_H_EFFECTIF", length = 4)
    private String depHEffectif;
    @Size(max = 8)
    @Column(name = "MOUILL_DEPART", length = 8)
    private String mouillDepart;
    @Size(max = 4)
    @Column(name = "MOUILLH_DEPART", length = 4)
    private String mouillhDepart;
    @Size(max = 8)
    @Column(name = "AGENT_SORTIE", length = 8)
    private String agentSortie;
    @Size(max = 20)
    @Column(name = "TELEPHONE_AGENT_SORTIE", length = 20)
    private String telephoneAgentSortie;
    @Column(name = "OK_APPARAUX")
    private Short okApparaux;
    @Column(name = "OK_ASSURANCE")
    private Short okAssurance;
    @Column(name = "OK_DECLARATION")
    private Short okDeclaration;
    @Column(name = "FREE_GAZ")
    private Short freeGaz;
    @Size(max = 20)
    @Column(length = 20)
    private String reparation;
    @Size(max = 20)
    @Column(length = 20)
    private String controle;
    @Size(max = 20)
    @Column(length = 20)
    private String avaries;
    @Size(max = 8)
    @Column(name = "MANUTENTIONNAIRE_SORTIE", length = 8)
    private String manutentionnaireSortie;
    @Size(max = 8)
    @Column(name = "DATE_STATUT", length = 8)
    private String dateStatut;
    @Size(max = 4)
    @Column(name = "HEURE_STATUT", length = 4)
    private String heureStatut;
    @Size(max = 15)
    @Column(name = "MOBILE_CHEF_ESCALE", length = 15)
    private String mobileChefEscale;
    @Size(max = 15)
    @Column(name = "MOBILE_RESPONSABLE_SHIPPING", length = 15)
    private String mobileResponsableShipping;
    @Size(max = 8)
    @Column(length = 8)
    private String quai2;
    @Size(max = 8)
    @Column(length = 8)
    private String quai3;
    @Size(max = 8)
    @Column(name = "DATE_MODIF_ETA", length = 8)
    private String dateModifEta;
    @Size(max = 4)
    @Column(name = "HEURE_MODIF_ETA", length = 4)
    private String heureModifEta;
    @Size(max = 8)
    @Column(name = "DATE_APPEL_VIGIE", length = 8)
    private String dateAppelVigie;
    @Size(max = 4)
    @Column(name = "HEURE_APPEL_VIGIE", length = 4)
    private String heureAppelVigie;
    @Size(max = 10)
    @Column(name = "CONTROLEUR_VIGIE", length = 10)
    private String controleurVigie;
    @Size(max = 20)
    @Column(name = "SAISIE_NAVIRE", length = 20)
    private String saisieNavire;
    @Size(max = 8)
    @Column(name = "DATE_MODIF_ETD", length = 8)
    private String dateModifEtd;
    @Size(max = 4)
    @Column(name = "HEURE_MODIF_ETD", length = 4)
    private String heureModifEtd;
    @Size(max = 8)
    @Column(length = 8)
    private String bassin;
    @Size(max = 8)
    @Column(name = "DATE_FEUX", length = 8)
    private String dateFeux;
    @Size(max = 4)
    @Column(name = "HEURE_FEUX", length = 4)
    private String heureFeux;
    @Size(max = 8)
    @Column(name = "DATE_FEUX_SORTIE", length = 8)
    private String dateFeuxSortie;
    @Size(max = 4)
    @Column(name = "HEURE_FEUX_SORTIE", length = 4)
    private String heureFeuxSortie;
    @Size(max = 8)
    @Column(length = 8)
    private String position;
    @Size(max = 8)
    @Column(name = "ETA_NAVIRE", length = 8)
    private String etaNavire;
    @Size(max = 4)
    @Column(name = "H_ETA_NAVIRE", length = 4)
    private String hEtaNavire;
    @Size(max = 8)
    @Column(name = "MODIF_ETA_NAVIRE", length = 8)
    private String modifEtaNavire;
    @Size(max = 4)
    @Column(name = "MODIF_H_ETA_NAVIRE", length = 4)
    private String modifHEtaNavire;
    @Size(max = 10)
    @Column(name = "UTILISAT_ETA_NAVIRE", length = 10)
    private String utilisatEtaNavire;
    @Column(name = "TEAU_AR_ENTREE", precision = 19, scale = 6)
    private BigDecimal teauArEntree;
    @Column(name = "TEAU_AR_SORTIE", precision = 19, scale = 6)
    private BigDecimal teauArSortie;
    @Column(name = "OK_AGENT_SORTIE")
    private Short okAgentSortie;
    @Column(name = "OK_CAPITAINE_SORTIE")
    private Short okCapitaineSortie;
    @Size(max = 8)
    @Column(name = "DATE_AGENT_SORTIE", length = 8)
    private String dateAgentSortie;
    @Size(max = 4)
    @Column(name = "HEURE_AGENT_SORTIE", length = 4)
    private String heureAgentSortie;
    @Size(max = 8)
    @Column(name = "DATE_CAPITAINE_SORTIE", length = 8)
    private String dateCapitaineSortie;
    @Size(max = 4)
    @Column(name = "HEURE_CAPITAINE_SORTIE", length = 4)
    private String heureCapitaineSortie;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "E_MAIL", length = 50)
    private String eMail;
    @Column(name = "LONGITUDE_RADE", precision = 19, scale = 6)
    private BigDecimal longitudeRade;
    @Column(name = "LATITUDE_RADE", precision = 19, scale = 6)
    private BigDecimal latitudeRade;
    @Size(max = 12)
    @Column(name = "NUMERO_VETTING", length = 12)
    private String numeroVetting;
    @Size(max = 8)
    @Column(name = "TYPE_MANUTENTION", length = 8)
    private String typeManutention;
    @Column(name = "TONNAGE_DEB", precision = 19, scale = 6)
    private BigDecimal tonnageDeb;
    @Column(name = "TONNAGE_EMB", precision = 19, scale = 6)
    private BigDecimal tonnageEmb;
    @Size(max = 8)
    @Column(name = "PILOTE_ENTREE", length = 8)
    private String piloteEntree;
    @Size(max = 8)
    @Column(name = "PILOTE_SORTIE", length = 8)
    private String piloteSortie;
    @Size(max = 8)
    @Column(name = "DATE_APPEL_VIGIE_SORTIE", length = 8)
    private String dateAppelVigieSortie;
    @Size(max = 8)
    @Column(name = "HEURE_APPEL_VIGIE_SORTIE", length = 8)
    private String heureAppelVigieSortie;
    @Size(max = 8)
    @Column(name = "D_DEB_PILOTE", length = 8)
    private String dDebPilote;
    @Size(max = 4)
    @Column(name = "H_DEB_PILOTE", length = 4)
    private String hDebPilote;
    @Size(max = 8)
    @Column(name = "D_EMB_PILOTE", length = 8)
    private String dEmbPilote;
    @Size(max = 4)
    @Column(name = "H_EMB_PILOTE", length = 4)
    private String hEmbPilote;
    @Size(max = 8)
    @Column(name = "DATE_PROGRAMMATION", length = 8)
    private String dateProgrammation;
    @Size(max = 4)
    @Column(name = "HEURE_PROGRAMMATION", length = 4)
    private String heureProgrammation;
    @Column(name = "DUREE_PROGRAMMATION", precision = 19, scale = 6)
    private BigDecimal dureeProgrammation;
    @Size(max = 8)
    @Column(name = "STATUT_CONFERENCE_ENTREE", length = 8)
    private String statutConferenceEntree;
    @Size(max = 8)
    @Column(name = "DATE_CONFERENCE_ENTREE", length = 8)
    private String dateConferenceEntree;
    @Size(max = 4)
    @Column(name = "HEURE_CONFERENCE_ENTREE", length = 4)
    private String heureConferenceEntree;
    @Size(max = 8)
    @Column(name = "STATUT_CONFERENCE_SORTIE", length = 8)
    private String statutConferenceSortie;
    @Size(max = 8)
    @Column(name = "DATE_CONFERENCE_SORTIE", length = 8)
    private String dateConferenceSortie;
    @Size(max = 4)
    @Column(name = "HEURE_CONFERENCE_SORTIE", length = 4)
    private String heureConferenceSortie;
    @Column(name = "TONNAGE_DEB_DECLARE", precision = 19, scale = 6)
    private BigDecimal tonnageDebDeclare;
    @Column(name = "TONNAGE_EMB_DECLARE", precision = 19, scale = 6)
    private BigDecimal tonnageEmbDeclare;
    @Column(name = "NBRE_BL")
    private BigInteger nbreBl;
    @Column(name = "TONNAGE_F_PILOTAGE_IMPORT", precision = 19, scale = 6)
    private BigDecimal tonnageFPilotageImport;
    @Column(name = "TONNAGE_F_PILOTAGE_EXPORT", precision = 19, scale = 6)
    private BigDecimal tonnageFPilotageExport;
    @Column(name = "TONNAGE_F_PILOTAGE_TOTAL", precision = 19, scale = 6)
    private BigDecimal tonnageFPilotageTotal;
    @Column(name = "TONNAGE_MAX_FACTURE", precision = 19, scale = 6)
    private BigDecimal tonnageMaxFacture;
    @Size(max = 12)
    @Column(name = "NUM_ESC_CT", length = 12)
    private String numEscCt;
    @Column(name = "OK_DEPOT_MANIFESTE_ENTREE")
    private Short okDepotManifesteEntree;
    @Size(max = 8)
    @Column(name = "DATE_DEPOT_MANIFESTE_ENTREE", length = 8)
    private String dateDepotManifesteEntree;
    @Size(max = 4)
    @Column(name = "HEURE_DEPOT_MANIFESTE_ENTREE", length = 4)
    private String heureDepotManifesteEntree;
    @Column(name = "OK_DEPOT_MANIFESTE_SORTIE")
    private Short okDepotManifesteSortie;
    @Size(max = 8)
    @Column(name = "DATE_DEPOT_MANIFESTE_SORTIE", length = 8)
    private String dateDepotManifesteSortie;
    @Size(max = 4)
    @Column(name = "HEURE_DEPOT_MANIFESTE_SORTIE", length = 4)
    private String heureDepotManifesteSortie;
    @Column(name = "NOMBRE_CONTENEUR_20_IMPORT")
    private BigInteger nombreConteneur20Import;
    @Column(name = "NOMBRE_CONTENEUR_40_IMPORT")
    private BigInteger nombreConteneur40Import;
    @Column(name = "NOMBRE_CONTENEUR_20_EXPORT")
    private BigInteger nombreConteneur20Export;
    @Column(name = "NOMBRE_CONTENEUR_40_EXPORT")
    private BigInteger nombreConteneur40Export;
    @Column(name = "NOMBRE_CONTENEUR_20_TRANSBI")
    private BigInteger nombreConteneur20Transbi;
    @Column(name = "NOMBRE_CONTENEUR_40_TRANSBI")
    private BigInteger nombreConteneur40Transbi;
    @Column(name = "NOMBRE_CONTENEUR_20_TRANSBE")
    private BigInteger nombreConteneur20Transbe;
    @Column(name = "NOMBRE_CONTENEUR_40_TRANSBE")
    private BigInteger nombreConteneur40Transbe;
    @Column(name = "TOP_TDR")
    private Short topTdr;
    @Size(max = 8)
    @Column(name = "TOP_TDR_DATE", length = 8)
    private String topTdrDate;
    @Size(max = 4)
    @Column(name = "TOP_TDR_HEURE", length = 4)
    private String topTdrHeure;
    @Column(name = "TOP_TIMESHEET")
    private Short topTimesheet;
    @Size(max = 8)
    @Column(name = "TOP_TIMESHEET_DATE", length = 8)
    private String topTimesheetDate;
    @Size(max = 4)
    @Column(name = "TOP_TIMESHEET_HEURE", length = 4)
    private String topTimesheetHeure;
    @Column(name = "TOP_TDR_MENSUEL")
    private Short topTdrMensuel;
    @Size(max = 8)
    @Column(name = "TOP_TDR_MENSUEL_DATE", length = 8)
    private String topTdrMensuelDate;
    @Size(max = 4)
    @Column(name = "TOP_TDR_MENSUEL_HEURE", length = 4)
    private String topTdrMensuelHeure;
    @Size(max = 8)
    @Column(name = "DATE_MATRICE", length = 8)
    private String dateMatrice;
    @Size(max = 8)
    @Column(name = "DATE_BROUILLARD", length = 8)
    private String dateBrouillard;
    @Size(max = 8)
    @Column(name = "DATE_FACTURE", length = 8)
    private String dateFacture;
    @Size(max = 8)
    @Column(name = "DATE_VALIDATION_TIMESHEET", length = 8)
    private String dateValidationTimesheet;
    @Size(max = 10)
    @Column(name = "UTILISAT_VALIDATION_TIMESHEET", length = 10)
    private String utilisatValidationTimesheet;
    @Size(max = 10)
    @Column(name = "UTILISAT_MATRICE", length = 10)
    private String utilisatMatrice;
    @Size(max = 10)
    @Column(name = "UTILISAT_BROUILLARD", length = 10)
    private String utilisatBrouillard;
    @Size(max = 10)
    @Column(name = "UTILISAT_FACTURE", length = 10)
    private String utilisatFacture;
    @Size(max = 8)
    @Column(name = "DATE_ESCALE_INITIALE", length = 8)
    private String dateEscaleInitiale;
    @JoinColumn(name = "NACLEUNIK", referencedColumnName = "NACLEUNIK")
    @ManyToOne
    private Navire nacleunik;
    @OneToMany(mappedBy = "idEscale")
    private Collection<GeneralInfo> generalInfoCollection;

    public Escale() {
    }

    public Escale(BigDecimal escleunik) {
        this.escleunik = escleunik;
    }

    public BigDecimal getEscleunik() {
        return escleunik;
    }

    public void setEscleunik(BigDecimal escleunik) {
        this.escleunik = escleunik;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getManuten() {
        return manuten;
    }

    public void setManuten(String manuten) {
        this.manuten = manuten;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public Integer getService() {
        return service;
    }

    public void setService(Integer service) {
        this.service = service;
    }

    public String getIdentite() {
        return identite;
    }

    public void setIdentite(String identite) {
        this.identite = identite;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    public String getMoisStat() {
        return moisStat;
    }

    public void setMoisStat(String moisStat) {
        this.moisStat = moisStat;
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

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public BigInteger getNombre() {
        return nombre;
    }

    public void setNombre(BigInteger nombre) {
        this.nombre = nombre;
    }

    public Short getNbAtt() {
        return nbAtt;
    }

    public void setNbAtt(Short nbAtt) {
        this.nbAtt = nbAtt;
    }

    public BigInteger getSejour() {
        return sejour;
    }

    public void setSejour(BigInteger sejour) {
        this.sejour = sejour;
    }

    public BigInteger getDuree() {
        return duree;
    }

    public void setDuree(BigInteger duree) {
        this.duree = duree;
    }

    public BigInteger getTravail() {
        return travail;
    }

    public void setTravail(BigInteger travail) {
        this.travail = travail;
    }

    public String getSituat() {
        return situat;
    }

    public void setSituat(String situat) {
        this.situat = situat;
    }

    public String getArrivee() {
        return arrivee;
    }

    public void setArrivee(String arrivee) {
        this.arrivee = arrivee;
    }

    public String getArrH() {
        return arrH;
    }

    public void setArrH(String arrH) {
        this.arrH = arrH;
    }

    public String getMouill() {
        return mouill;
    }

    public void setMouill(String mouill) {
        this.mouill = mouill;
    }

    public String getMouillh() {
        return mouillh;
    }

    public void setMouillh(String mouillh) {
        this.mouillh = mouillh;
    }

    public String getPilEnt() {
        return pilEnt;
    }

    public void setPilEnt(String pilEnt) {
        this.pilEnt = pilEnt;
    }

    public String getPilEnth() {
        return pilEnth;
    }

    public void setPilEnth(String pilEnth) {
        this.pilEnth = pilEnth;
    }

    public Short getRemE() {
        return remE;
    }

    public void setRemE(Short remE) {
        this.remE = remE;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getDebH() {
        return debH;
    }

    public void setDebH(String debH) {
        this.debH = debH;
    }

    public String getDebOpe() {
        return debOpe;
    }

    public void setDebOpe(String debOpe) {
        this.debOpe = debOpe;
    }

    public String getDebOpeh() {
        return debOpeh;
    }

    public void setDebOpeh(String debOpeh) {
        this.debOpeh = debOpeh;
    }

    public BigInteger getAttAr() {
        return attAr;
    }

    public void setAttAr(BigInteger attAr) {
        this.attAr = attAr;
    }

    public String getMotAr() {
        return motAr;
    }

    public void setMotAr(String motAr) {
        this.motAr = motAr;
    }

    public String getCondAr() {
        return condAr;
    }

    public void setCondAr(String condAr) {
        this.condAr = condAr;
    }

    public String getPortAttac() {
        return portAttac;
    }

    public void setPortAttac(String portAttac) {
        this.portAttac = portAttac;
    }

    public Short getRemS() {
        return remS;
    }

    public void setRemS(Short remS) {
        this.remS = remS;
    }

    public String getFinOpe() {
        return finOpe;
    }

    public void setFinOpe(String finOpe) {
        this.finOpe = finOpe;
    }

    public String getFinOpeh() {
        return finOpeh;
    }

    public void setFinOpeh(String finOpeh) {
        this.finOpeh = finOpeh;
    }

    public String getPilSor() {
        return pilSor;
    }

    public void setPilSor(String pilSor) {
        this.pilSor = pilSor;
    }

    public String getPilSorh() {
        return pilSorh;
    }

    public void setPilSorh(String pilSorh) {
        this.pilSorh = pilSorh;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDepH() {
        return depH;
    }

    public void setDepH(String depH) {
        this.depH = depH;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getFinH() {
        return finH;
    }

    public void setFinH(String finH) {
        this.finH = finH;
    }

    public BigInteger getAttDp() {
        return attDp;
    }

    public void setAttDp(BigInteger attDp) {
        this.attDp = attDp;
    }

    public String getMotDp() {
        return motDp;
    }

    public void setMotDp(String motDp) {
        this.motDp = motDp;
    }

    public String getCondDp() {
        return condDp;
    }

    public void setCondDp(String condDp) {
        this.condDp = condDp;
    }

    public Double getFoBrd() {
        return foBrd;
    }

    public void setFoBrd(Double foBrd) {
        this.foBrd = foBrd;
    }

    public Double getDoBrd() {
        return doBrd;
    }

    public void setDoBrd(Double doBrd) {
        this.doBrd = doBrd;
    }

    public Double getFoStg() {
        return foStg;
    }

    public void setFoStg(Double foStg) {
        this.foStg = foStg;
    }

    public Double getDoStg() {
        return doStg;
    }

    public void setDoStg(Double doStg) {
        this.doStg = doStg;
    }

    public Double getEau() {
        return eau;
    }

    public void setEau(Double eau) {
        this.eau = eau;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public BigInteger getPerson() {
        return person;
    }

    public void setPerson(BigInteger person) {
        this.person = person;
    }

    public BigInteger getTraite() {
        return traite;
    }

    public void setTraite(BigInteger traite) {
        this.traite = traite;
    }

    public String getQuai() {
        return quai;
    }

    public void setQuai(String quai) {
        this.quai = quai;
    }

    public BigDecimal getTonnage() {
        return tonnage;
    }

    public void setTonnage(BigDecimal tonnage) {
        this.tonnage = tonnage;
    }

    public String getFDate() {
        return fDate;
    }

    public void setFDate(String fDate) {
        this.fDate = fDate;
    }

    public String getFHeure() {
        return fHeure;
    }

    public void setFHeure(String fHeure) {
        this.fHeure = fHeure;
    }

    public String getNFact() {
        return nFact;
    }

    public void setNFact(String nFact) {
        this.nFact = nFact;
    }

    public String getFEche() {
        return fEche;
    }

    public void setFEche(String fEche) {
        this.fEche = fEche;
    }

    public Double getNbrTcs() {
        return nbrTcs;
    }

    public void setNbrTcs(Double nbrTcs) {
        this.nbrTcs = nbrTcs;
    }

    public Double getNbrVeh() {
        return nbrVeh;
    }

    public void setNbrVeh(Double nbrVeh) {
        this.nbrVeh = nbrVeh;
    }

    public Double getNbrPas() {
        return nbrPas;
    }

    public void setNbrPas(Double nbrPas) {
        this.nbrPas = nbrPas;
    }

    public Double getNbrTcst() {
        return nbrTcst;
    }

    public void setNbrTcst(Double nbrTcst) {
        this.nbrTcst = nbrTcst;
    }

    public BigDecimal getChargN() {
        return chargN;
    }

    public void setChargN(BigDecimal chargN) {
        this.chargN = chargN;
    }

    public Double getNbrTcsn() {
        return nbrTcsn;
    }

    public void setNbrTcsn(Double nbrTcsn) {
        this.nbrTcsn = nbrTcsn;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Integer getNbrSht() {
        return nbrSht;
    }

    public void setNbrSht(Integer nbrSht) {
        this.nbrSht = nbrSht;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public BigInteger getRendement() {
        return rendement;
    }

    public void setRendement(BigInteger rendement) {
        this.rendement = rendement;
    }

    public Short getAvarie() {
        return avarie;
    }

    public void setAvarie(Short avarie) {
        this.avarie = avarie;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public Integer getTopTriton() {
        return topTriton;
    }

    public void setTopTriton(Integer topTriton) {
        this.topTriton = topTriton;
    }

    public Short getTopDanger() {
        return topDanger;
    }

    public void setTopDanger(Short topDanger) {
        this.topDanger = topDanger;
    }

    public Double getHauteur() {
        return hauteur;
    }

    public void setHauteur(Double hauteur) {
        this.hauteur = hauteur;
    }

    public Double getLargeur() {
        return largeur;
    }

    public void setLargeur(Double largeur) {
        this.largeur = largeur;
    }

    public Short getTopFacAg() {
        return topFacAg;
    }

    public void setTopFacAg(Short topFacAg) {
        this.topFacAg = topFacAg;
    }

    public Short getTopFacMn() {
        return topFacMn;
    }

    public void setTopFacMn(Short topFacMn) {
        this.topFacMn = topFacMn;
    }

    public String getTHoraire() {
        return tHoraire;
    }

    public void setTHoraire(String tHoraire) {
        this.tHoraire = tHoraire;
    }

    public Short getNbPortique() {
        return nbPortique;
    }

    public void setNbPortique(Short nbPortique) {
        this.nbPortique = nbPortique;
    }

    public Short getOkAgent() {
        return okAgent;
    }

    public void setOkAgent(Short okAgent) {
        this.okAgent = okAgent;
    }

    public Short getOkCapitaine() {
        return okCapitaine;
    }

    public void setOkCapitaine(Short okCapitaine) {
        this.okCapitaine = okCapitaine;
    }

    public Short getOkManutentionnaire() {
        return okManutentionnaire;
    }

    public void setOkManutentionnaire(Short okManutentionnaire) {
        this.okManutentionnaire = okManutentionnaire;
    }

    public String getDateAgent() {
        return dateAgent;
    }

    public void setDateAgent(String dateAgent) {
        this.dateAgent = dateAgent;
    }

    public String getHeureAgent() {
        return heureAgent;
    }

    public void setHeureAgent(String heureAgent) {
        this.heureAgent = heureAgent;
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

    public String getDateManutentionnaire() {
        return dateManutentionnaire;
    }

    public void setDateManutentionnaire(String dateManutentionnaire) {
        this.dateManutentionnaire = dateManutentionnaire;
    }

    public String getHeureManutentionnaire() {
        return heureManutentionnaire;
    }

    public void setHeureManutentionnaire(String heureManutentionnaire) {
        this.heureManutentionnaire = heureManutentionnaire;
    }

    public String getProvenanceI() {
        return provenanceI;
    }

    public void setProvenanceI(String provenanceI) {
        this.provenanceI = provenanceI;
    }

    public String getProvenanceO() {
        return provenanceO;
    }

    public void setProvenanceO(String provenanceO) {
        this.provenanceO = provenanceO;
    }

    public String getDestinationI() {
        return destinationI;
    }

    public void setDestinationI(String destinationI) {
        this.destinationI = destinationI;
    }

    public String getDestinationF() {
        return destinationF;
    }

    public void setDestinationF(String destinationF) {
        this.destinationF = destinationF;
    }

    public String getNavire() {
        return navire;
    }

    public void setNavire(String navire) {
        this.navire = navire;
    }

    public String getDDemande() {
        return dDemande;
    }

    public void setDDemande(String dDemande) {
        this.dDemande = dDemande;
    }

    public String getHDemande() {
        return hDemande;
    }

    public void setHDemande(String hDemande) {
        this.hDemande = hDemande;
    }

    public String getReferenc() {
        return referenc;
    }

    public void setReferenc(String referenc) {
        this.referenc = referenc;
    }

    public BigDecimal getTotPrimeEq1() {
        return totPrimeEq1;
    }

    public void setTotPrimeEq1(BigDecimal totPrimeEq1) {
        this.totPrimeEq1 = totPrimeEq1;
    }

    public BigDecimal getTotPrimeEq2() {
        return totPrimeEq2;
    }

    public void setTotPrimeEq2(BigDecimal totPrimeEq2) {
        this.totPrimeEq2 = totPrimeEq2;
    }

    public String getVoyage() {
        return voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    public Integer getNbrHtrb() {
        return nbrHtrb;
    }

    public void setNbrHtrb(Integer nbrHtrb) {
        this.nbrHtrb = nbrHtrb;
    }

    public Integer getNbrTrb() {
        return nbrTrb;
    }

    public void setNbrTrb(Integer nbrTrb) {
        this.nbrTrb = nbrTrb;
    }

    public Integer getNbrNtrb() {
        return nbrNtrb;
    }

    public void setNbrNtrb(Integer nbrNtrb) {
        this.nbrNtrb = nbrNtrb;
    }

    public Integer getHrsNdf() {
        return hrsNdf;
    }

    public void setHrsNdf(Integer hrsNdf) {
        this.hrsNdf = hrsNdf;
    }

    public Integer getHrsNbr() {
        return hrsNbr;
    }

    public void setHrsNbr(Integer hrsNbr) {
        this.hrsNbr = hrsNbr;
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

    public BigInteger getFacleunik() {
        return facleunik;
    }

    public void setFacleunik(BigInteger facleunik) {
        this.facleunik = facleunik;
    }

    public BigDecimal getChargB() {
        return chargB;
    }

    public void setChargB(BigDecimal chargB) {
        this.chargB = chargB;
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

    public BigDecimal getJaugeNet() {
        return jaugeNet;
    }

    public void setJaugeNet(BigDecimal jaugeNet) {
        this.jaugeNet = jaugeNet;
    }

    public BigDecimal getRedemU() {
        return redemU;
    }

    public void setRedemU(BigDecimal redemU) {
        this.redemU = redemU;
    }

    public String getChefPoste() {
        return chefPoste;
    }

    public void setChefPoste(String chefPoste) {
        this.chefPoste = chefPoste;
    }

    public Short getOkFactNav() {
        return okFactNav;
    }

    public void setOkFactNav(Short okFactNav) {
        this.okFactNav = okFactNav;
    }

    public Short getOkFactEqu() {
        return okFactEqu;
    }

    public void setOkFactEqu(Short okFactEqu) {
        this.okFactEqu = okFactEqu;
    }

    public BigDecimal getTraiteHier() {
        return traiteHier;
    }

    public void setTraiteHier(BigDecimal traiteHier) {
        this.traiteHier = traiteHier;
    }

    public Date getDateStat() {
        return dateStat;
    }

    public void setDateStat(Date dateStat) {
        this.dateStat = dateStat;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public String getDatePasseEntree() {
        return datePasseEntree;
    }

    public void setDatePasseEntree(String datePasseEntree) {
        this.datePasseEntree = datePasseEntree;
    }

    public String getHeurePasseEntree() {
        return heurePasseEntree;
    }

    public void setHeurePasseEntree(String heurePasseEntree) {
        this.heurePasseEntree = heurePasseEntree;
    }

    public String getDatePasseSortie() {
        return datePasseSortie;
    }

    public void setDatePasseSortie(String datePasseSortie) {
        this.datePasseSortie = datePasseSortie;
    }

    public String getHeurePasseSortie() {
        return heurePasseSortie;
    }

    public void setHeurePasseSortie(String heurePasseSortie) {
        this.heurePasseSortie = heurePasseSortie;
    }

    public String getDateArriveeRade() {
        return dateArriveeRade;
    }

    public void setDateArriveeRade(String dateArriveeRade) {
        this.dateArriveeRade = dateArriveeRade;
    }

    public String getHeureArriveeRade() {
        return heureArriveeRade;
    }

    public void setHeureArriveeRade(String heureArriveeRade) {
        this.heureArriveeRade = heureArriveeRade;
    }

    public String getResponsableShipping() {
        return responsableShipping;
    }

    public void setResponsableShipping(String responsableShipping) {
        this.responsableShipping = responsableShipping;
    }

    public String getChefEscale() {
        return chefEscale;
    }

    public void setChefEscale(String chefEscale) {
        this.chefEscale = chefEscale;
    }

    public String getTelAgent() {
        return telAgent;
    }

    public void setTelAgent(String telAgent) {
        this.telAgent = telAgent;
    }

    public String getTelResponsableShipping() {
        return telResponsableShipping;
    }

    public void setTelResponsableShipping(String telResponsableShipping) {
        this.telResponsableShipping = telResponsableShipping;
    }

    public String getTelChefEscale() {
        return telChefEscale;
    }

    public void setTelChefEscale(String telChefEscale) {
        this.telChefEscale = telChefEscale;
    }

    public String getArriveeRade() {
        return arriveeRade;
    }

    public void setArriveeRade(String arriveeRade) {
        this.arriveeRade = arriveeRade;
    }

    public String getArrHRade() {
        return arrHRade;
    }

    public void setArrHRade(String arrHRade) {
        this.arrHRade = arrHRade;
    }

    public String getDepartEffectif() {
        return departEffectif;
    }

    public void setDepartEffectif(String departEffectif) {
        this.departEffectif = departEffectif;
    }

    public String getDepHEffectif() {
        return depHEffectif;
    }

    public void setDepHEffectif(String depHEffectif) {
        this.depHEffectif = depHEffectif;
    }

    public String getMouillDepart() {
        return mouillDepart;
    }

    public void setMouillDepart(String mouillDepart) {
        this.mouillDepart = mouillDepart;
    }

    public String getMouillhDepart() {
        return mouillhDepart;
    }

    public void setMouillhDepart(String mouillhDepart) {
        this.mouillhDepart = mouillhDepart;
    }

    public String getAgentSortie() {
        return agentSortie;
    }

    public void setAgentSortie(String agentSortie) {
        this.agentSortie = agentSortie;
    }

    public String getTelephoneAgentSortie() {
        return telephoneAgentSortie;
    }

    public void setTelephoneAgentSortie(String telephoneAgentSortie) {
        this.telephoneAgentSortie = telephoneAgentSortie;
    }

    public Short getOkApparaux() {
        return okApparaux;
    }

    public void setOkApparaux(Short okApparaux) {
        this.okApparaux = okApparaux;
    }

    public Short getOkAssurance() {
        return okAssurance;
    }

    public void setOkAssurance(Short okAssurance) {
        this.okAssurance = okAssurance;
    }

    public Short getOkDeclaration() {
        return okDeclaration;
    }

    public void setOkDeclaration(Short okDeclaration) {
        this.okDeclaration = okDeclaration;
    }

    public Short getFreeGaz() {
        return freeGaz;
    }

    public void setFreeGaz(Short freeGaz) {
        this.freeGaz = freeGaz;
    }

    public String getReparation() {
        return reparation;
    }

    public void setReparation(String reparation) {
        this.reparation = reparation;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }

    public String getAvaries() {
        return avaries;
    }

    public void setAvaries(String avaries) {
        this.avaries = avaries;
    }

    public String getManutentionnaireSortie() {
        return manutentionnaireSortie;
    }

    public void setManutentionnaireSortie(String manutentionnaireSortie) {
        this.manutentionnaireSortie = manutentionnaireSortie;
    }

    public String getDateStatut() {
        return dateStatut;
    }

    public void setDateStatut(String dateStatut) {
        this.dateStatut = dateStatut;
    }

    public String getHeureStatut() {
        return heureStatut;
    }

    public void setHeureStatut(String heureStatut) {
        this.heureStatut = heureStatut;
    }

    public String getMobileChefEscale() {
        return mobileChefEscale;
    }

    public void setMobileChefEscale(String mobileChefEscale) {
        this.mobileChefEscale = mobileChefEscale;
    }

    public String getMobileResponsableShipping() {
        return mobileResponsableShipping;
    }

    public void setMobileResponsableShipping(String mobileResponsableShipping) {
        this.mobileResponsableShipping = mobileResponsableShipping;
    }

    public String getQuai2() {
        return quai2;
    }

    public void setQuai2(String quai2) {
        this.quai2 = quai2;
    }

    public String getQuai3() {
        return quai3;
    }

    public void setQuai3(String quai3) {
        this.quai3 = quai3;
    }

    public String getDateModifEta() {
        return dateModifEta;
    }

    public void setDateModifEta(String dateModifEta) {
        this.dateModifEta = dateModifEta;
    }

    public String getHeureModifEta() {
        return heureModifEta;
    }

    public void setHeureModifEta(String heureModifEta) {
        this.heureModifEta = heureModifEta;
    }

    public String getDateAppelVigie() {
        return dateAppelVigie;
    }

    public void setDateAppelVigie(String dateAppelVigie) {
        this.dateAppelVigie = dateAppelVigie;
    }

    public String getHeureAppelVigie() {
        return heureAppelVigie;
    }

    public void setHeureAppelVigie(String heureAppelVigie) {
        this.heureAppelVigie = heureAppelVigie;
    }

    public String getControleurVigie() {
        return controleurVigie;
    }

    public void setControleurVigie(String controleurVigie) {
        this.controleurVigie = controleurVigie;
    }

    public String getSaisieNavire() {
        return saisieNavire;
    }

    public void setSaisieNavire(String saisieNavire) {
        this.saisieNavire = saisieNavire;
    }

    public String getDateModifEtd() {
        return dateModifEtd;
    }

    public void setDateModifEtd(String dateModifEtd) {
        this.dateModifEtd = dateModifEtd;
    }

    public String getHeureModifEtd() {
        return heureModifEtd;
    }

    public void setHeureModifEtd(String heureModifEtd) {
        this.heureModifEtd = heureModifEtd;
    }

    public String getBassin() {
        return bassin;
    }

    public void setBassin(String bassin) {
        this.bassin = bassin;
    }

    public String getDateFeux() {
        return dateFeux;
    }

    public void setDateFeux(String dateFeux) {
        this.dateFeux = dateFeux;
    }

    public String getHeureFeux() {
        return heureFeux;
    }

    public void setHeureFeux(String heureFeux) {
        this.heureFeux = heureFeux;
    }

    public String getDateFeuxSortie() {
        return dateFeuxSortie;
    }

    public void setDateFeuxSortie(String dateFeuxSortie) {
        this.dateFeuxSortie = dateFeuxSortie;
    }

    public String getHeureFeuxSortie() {
        return heureFeuxSortie;
    }

    public void setHeureFeuxSortie(String heureFeuxSortie) {
        this.heureFeuxSortie = heureFeuxSortie;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEtaNavire() {
        return etaNavire;
    }

    public void setEtaNavire(String etaNavire) {
        this.etaNavire = etaNavire;
    }

    public String getHEtaNavire() {
        return hEtaNavire;
    }

    public void setHEtaNavire(String hEtaNavire) {
        this.hEtaNavire = hEtaNavire;
    }

    public String getModifEtaNavire() {
        return modifEtaNavire;
    }

    public void setModifEtaNavire(String modifEtaNavire) {
        this.modifEtaNavire = modifEtaNavire;
    }

    public String getModifHEtaNavire() {
        return modifHEtaNavire;
    }

    public void setModifHEtaNavire(String modifHEtaNavire) {
        this.modifHEtaNavire = modifHEtaNavire;
    }

    public String getUtilisatEtaNavire() {
        return utilisatEtaNavire;
    }

    public void setUtilisatEtaNavire(String utilisatEtaNavire) {
        this.utilisatEtaNavire = utilisatEtaNavire;
    }

    public BigDecimal getTeauArEntree() {
        return teauArEntree;
    }

    public void setTeauArEntree(BigDecimal teauArEntree) {
        this.teauArEntree = teauArEntree;
    }

    public BigDecimal getTeauArSortie() {
        return teauArSortie;
    }

    public void setTeauArSortie(BigDecimal teauArSortie) {
        this.teauArSortie = teauArSortie;
    }

    public Short getOkAgentSortie() {
        return okAgentSortie;
    }

    public void setOkAgentSortie(Short okAgentSortie) {
        this.okAgentSortie = okAgentSortie;
    }

    public Short getOkCapitaineSortie() {
        return okCapitaineSortie;
    }

    public void setOkCapitaineSortie(Short okCapitaineSortie) {
        this.okCapitaineSortie = okCapitaineSortie;
    }

    public String getDateAgentSortie() {
        return dateAgentSortie;
    }

    public void setDateAgentSortie(String dateAgentSortie) {
        this.dateAgentSortie = dateAgentSortie;
    }

    public String getHeureAgentSortie() {
        return heureAgentSortie;
    }

    public void setHeureAgentSortie(String heureAgentSortie) {
        this.heureAgentSortie = heureAgentSortie;
    }

    public String getDateCapitaineSortie() {
        return dateCapitaineSortie;
    }

    public void setDateCapitaineSortie(String dateCapitaineSortie) {
        this.dateCapitaineSortie = dateCapitaineSortie;
    }

    public String getHeureCapitaineSortie() {
        return heureCapitaineSortie;
    }

    public void setHeureCapitaineSortie(String heureCapitaineSortie) {
        this.heureCapitaineSortie = heureCapitaineSortie;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public BigDecimal getLongitudeRade() {
        return longitudeRade;
    }

    public void setLongitudeRade(BigDecimal longitudeRade) {
        this.longitudeRade = longitudeRade;
    }

    public BigDecimal getLatitudeRade() {
        return latitudeRade;
    }

    public void setLatitudeRade(BigDecimal latitudeRade) {
        this.latitudeRade = latitudeRade;
    }

    public String getNumeroVetting() {
        return numeroVetting;
    }

    public void setNumeroVetting(String numeroVetting) {
        this.numeroVetting = numeroVetting;
    }

    public String getTypeManutention() {
        return typeManutention;
    }

    public void setTypeManutention(String typeManutention) {
        this.typeManutention = typeManutention;
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

    public String getPiloteEntree() {
        return piloteEntree;
    }

    public void setPiloteEntree(String piloteEntree) {
        this.piloteEntree = piloteEntree;
    }

    public String getPiloteSortie() {
        return piloteSortie;
    }

    public void setPiloteSortie(String piloteSortie) {
        this.piloteSortie = piloteSortie;
    }

    public String getDateAppelVigieSortie() {
        return dateAppelVigieSortie;
    }

    public void setDateAppelVigieSortie(String dateAppelVigieSortie) {
        this.dateAppelVigieSortie = dateAppelVigieSortie;
    }

    public String getHeureAppelVigieSortie() {
        return heureAppelVigieSortie;
    }

    public void setHeureAppelVigieSortie(String heureAppelVigieSortie) {
        this.heureAppelVigieSortie = heureAppelVigieSortie;
    }

    public String getDDebPilote() {
        return dDebPilote;
    }

    public void setDDebPilote(String dDebPilote) {
        this.dDebPilote = dDebPilote;
    }

    public String getHDebPilote() {
        return hDebPilote;
    }

    public void setHDebPilote(String hDebPilote) {
        this.hDebPilote = hDebPilote;
    }

    public String getDEmbPilote() {
        return dEmbPilote;
    }

    public void setDEmbPilote(String dEmbPilote) {
        this.dEmbPilote = dEmbPilote;
    }

    public String getHEmbPilote() {
        return hEmbPilote;
    }

    public void setHEmbPilote(String hEmbPilote) {
        this.hEmbPilote = hEmbPilote;
    }

    public String getDateProgrammation() {
        return dateProgrammation;
    }

    public void setDateProgrammation(String dateProgrammation) {
        this.dateProgrammation = dateProgrammation;
    }

    public String getHeureProgrammation() {
        return heureProgrammation;
    }

    public void setHeureProgrammation(String heureProgrammation) {
        this.heureProgrammation = heureProgrammation;
    }

    public BigDecimal getDureeProgrammation() {
        return dureeProgrammation;
    }

    public void setDureeProgrammation(BigDecimal dureeProgrammation) {
        this.dureeProgrammation = dureeProgrammation;
    }

    public String getStatutConferenceEntree() {
        return statutConferenceEntree;
    }

    public void setStatutConferenceEntree(String statutConferenceEntree) {
        this.statutConferenceEntree = statutConferenceEntree;
    }

    public String getDateConferenceEntree() {
        return dateConferenceEntree;
    }

    public void setDateConferenceEntree(String dateConferenceEntree) {
        this.dateConferenceEntree = dateConferenceEntree;
    }

    public String getHeureConferenceEntree() {
        return heureConferenceEntree;
    }

    public void setHeureConferenceEntree(String heureConferenceEntree) {
        this.heureConferenceEntree = heureConferenceEntree;
    }

    public String getStatutConferenceSortie() {
        return statutConferenceSortie;
    }

    public void setStatutConferenceSortie(String statutConferenceSortie) {
        this.statutConferenceSortie = statutConferenceSortie;
    }

    public String getDateConferenceSortie() {
        return dateConferenceSortie;
    }

    public void setDateConferenceSortie(String dateConferenceSortie) {
        this.dateConferenceSortie = dateConferenceSortie;
    }

    public String getHeureConferenceSortie() {
        return heureConferenceSortie;
    }

    public void setHeureConferenceSortie(String heureConferenceSortie) {
        this.heureConferenceSortie = heureConferenceSortie;
    }

    public BigDecimal getTonnageDebDeclare() {
        return tonnageDebDeclare;
    }

    public void setTonnageDebDeclare(BigDecimal tonnageDebDeclare) {
        this.tonnageDebDeclare = tonnageDebDeclare;
    }

    public BigDecimal getTonnageEmbDeclare() {
        return tonnageEmbDeclare;
    }

    public void setTonnageEmbDeclare(BigDecimal tonnageEmbDeclare) {
        this.tonnageEmbDeclare = tonnageEmbDeclare;
    }

    public BigInteger getNbreBl() {
        return nbreBl;
    }

    public void setNbreBl(BigInteger nbreBl) {
        this.nbreBl = nbreBl;
    }

    public BigDecimal getTonnageFPilotageImport() {
        return tonnageFPilotageImport;
    }

    public void setTonnageFPilotageImport(BigDecimal tonnageFPilotageImport) {
        this.tonnageFPilotageImport = tonnageFPilotageImport;
    }

    public BigDecimal getTonnageFPilotageExport() {
        return tonnageFPilotageExport;
    }

    public void setTonnageFPilotageExport(BigDecimal tonnageFPilotageExport) {
        this.tonnageFPilotageExport = tonnageFPilotageExport;
    }

    public BigDecimal getTonnageFPilotageTotal() {
        return tonnageFPilotageTotal;
    }

    public void setTonnageFPilotageTotal(BigDecimal tonnageFPilotageTotal) {
        this.tonnageFPilotageTotal = tonnageFPilotageTotal;
    }

    public BigDecimal getTonnageMaxFacture() {
        return tonnageMaxFacture;
    }

    public void setTonnageMaxFacture(BigDecimal tonnageMaxFacture) {
        this.tonnageMaxFacture = tonnageMaxFacture;
    }

    public String getNumEscCt() {
        return numEscCt;
    }

    public void setNumEscCt(String numEscCt) {
        this.numEscCt = numEscCt;
    }

    public Short getOkDepotManifesteEntree() {
        return okDepotManifesteEntree;
    }

    public void setOkDepotManifesteEntree(Short okDepotManifesteEntree) {
        this.okDepotManifesteEntree = okDepotManifesteEntree;
    }

    public String getDateDepotManifesteEntree() {
        return dateDepotManifesteEntree;
    }

    public void setDateDepotManifesteEntree(String dateDepotManifesteEntree) {
        this.dateDepotManifesteEntree = dateDepotManifesteEntree;
    }

    public String getHeureDepotManifesteEntree() {
        return heureDepotManifesteEntree;
    }

    public void setHeureDepotManifesteEntree(String heureDepotManifesteEntree) {
        this.heureDepotManifesteEntree = heureDepotManifesteEntree;
    }

    public Short getOkDepotManifesteSortie() {
        return okDepotManifesteSortie;
    }

    public void setOkDepotManifesteSortie(Short okDepotManifesteSortie) {
        this.okDepotManifesteSortie = okDepotManifesteSortie;
    }

    public String getDateDepotManifesteSortie() {
        return dateDepotManifesteSortie;
    }

    public void setDateDepotManifesteSortie(String dateDepotManifesteSortie) {
        this.dateDepotManifesteSortie = dateDepotManifesteSortie;
    }

    public String getHeureDepotManifesteSortie() {
        return heureDepotManifesteSortie;
    }

    public void setHeureDepotManifesteSortie(String heureDepotManifesteSortie) {
        this.heureDepotManifesteSortie = heureDepotManifesteSortie;
    }

    public BigInteger getNombreConteneur20Import() {
        return nombreConteneur20Import;
    }

    public void setNombreConteneur20Import(BigInteger nombreConteneur20Import) {
        this.nombreConteneur20Import = nombreConteneur20Import;
    }

    public BigInteger getNombreConteneur40Import() {
        return nombreConteneur40Import;
    }

    public void setNombreConteneur40Import(BigInteger nombreConteneur40Import) {
        this.nombreConteneur40Import = nombreConteneur40Import;
    }

    public BigInteger getNombreConteneur20Export() {
        return nombreConteneur20Export;
    }

    public void setNombreConteneur20Export(BigInteger nombreConteneur20Export) {
        this.nombreConteneur20Export = nombreConteneur20Export;
    }

    public BigInteger getNombreConteneur40Export() {
        return nombreConteneur40Export;
    }

    public void setNombreConteneur40Export(BigInteger nombreConteneur40Export) {
        this.nombreConteneur40Export = nombreConteneur40Export;
    }

    public BigInteger getNombreConteneur20Transbi() {
        return nombreConteneur20Transbi;
    }

    public void setNombreConteneur20Transbi(BigInteger nombreConteneur20Transbi) {
        this.nombreConteneur20Transbi = nombreConteneur20Transbi;
    }

    public BigInteger getNombreConteneur40Transbi() {
        return nombreConteneur40Transbi;
    }

    public void setNombreConteneur40Transbi(BigInteger nombreConteneur40Transbi) {
        this.nombreConteneur40Transbi = nombreConteneur40Transbi;
    }

    public BigInteger getNombreConteneur20Transbe() {
        return nombreConteneur20Transbe;
    }

    public void setNombreConteneur20Transbe(BigInteger nombreConteneur20Transbe) {
        this.nombreConteneur20Transbe = nombreConteneur20Transbe;
    }

    public BigInteger getNombreConteneur40Transbe() {
        return nombreConteneur40Transbe;
    }

    public void setNombreConteneur40Transbe(BigInteger nombreConteneur40Transbe) {
        this.nombreConteneur40Transbe = nombreConteneur40Transbe;
    }

    public Short getTopTdr() {
        return topTdr;
    }

    public void setTopTdr(Short topTdr) {
        this.topTdr = topTdr;
    }

    public String getTopTdrDate() {
        return topTdrDate;
    }

    public void setTopTdrDate(String topTdrDate) {
        this.topTdrDate = topTdrDate;
    }

    public String getTopTdrHeure() {
        return topTdrHeure;
    }

    public void setTopTdrHeure(String topTdrHeure) {
        this.topTdrHeure = topTdrHeure;
    }

    public Short getTopTimesheet() {
        return topTimesheet;
    }

    public void setTopTimesheet(Short topTimesheet) {
        this.topTimesheet = topTimesheet;
    }

    public String getTopTimesheetDate() {
        return topTimesheetDate;
    }

    public void setTopTimesheetDate(String topTimesheetDate) {
        this.topTimesheetDate = topTimesheetDate;
    }

    public String getTopTimesheetHeure() {
        return topTimesheetHeure;
    }

    public void setTopTimesheetHeure(String topTimesheetHeure) {
        this.topTimesheetHeure = topTimesheetHeure;
    }

    public Short getTopTdrMensuel() {
        return topTdrMensuel;
    }

    public void setTopTdrMensuel(Short topTdrMensuel) {
        this.topTdrMensuel = topTdrMensuel;
    }

    public String getTopTdrMensuelDate() {
        return topTdrMensuelDate;
    }

    public void setTopTdrMensuelDate(String topTdrMensuelDate) {
        this.topTdrMensuelDate = topTdrMensuelDate;
    }

    public String getTopTdrMensuelHeure() {
        return topTdrMensuelHeure;
    }

    public void setTopTdrMensuelHeure(String topTdrMensuelHeure) {
        this.topTdrMensuelHeure = topTdrMensuelHeure;
    }

    public String getDateMatrice() {
        return dateMatrice;
    }

    public void setDateMatrice(String dateMatrice) {
        this.dateMatrice = dateMatrice;
    }

    public String getDateBrouillard() {
        return dateBrouillard;
    }

    public void setDateBrouillard(String dateBrouillard) {
        this.dateBrouillard = dateBrouillard;
    }

    public String getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(String dateFacture) {
        this.dateFacture = dateFacture;
    }

    public String getDateValidationTimesheet() {
        return dateValidationTimesheet;
    }

    public void setDateValidationTimesheet(String dateValidationTimesheet) {
        this.dateValidationTimesheet = dateValidationTimesheet;
    }

    public String getUtilisatValidationTimesheet() {
        return utilisatValidationTimesheet;
    }

    public void setUtilisatValidationTimesheet(String utilisatValidationTimesheet) {
        this.utilisatValidationTimesheet = utilisatValidationTimesheet;
    }

    public String getUtilisatMatrice() {
        return utilisatMatrice;
    }

    public void setUtilisatMatrice(String utilisatMatrice) {
        this.utilisatMatrice = utilisatMatrice;
    }

    public String getUtilisatBrouillard() {
        return utilisatBrouillard;
    }

    public void setUtilisatBrouillard(String utilisatBrouillard) {
        this.utilisatBrouillard = utilisatBrouillard;
    }

    public String getUtilisatFacture() {
        return utilisatFacture;
    }

    public void setUtilisatFacture(String utilisatFacture) {
        this.utilisatFacture = utilisatFacture;
    }

    public String getDateEscaleInitiale() {
        return dateEscaleInitiale;
    }

    public void setDateEscaleInitiale(String dateEscaleInitiale) {
        this.dateEscaleInitiale = dateEscaleInitiale;
    }

    public Navire getNacleunik() {
        return nacleunik;
    }

    public void setNacleunik(Navire nacleunik) {
        this.nacleunik = nacleunik;
    }

    @XmlTransient
    public Collection<GeneralInfo> getGeneralInfoCollection() {
        return generalInfoCollection;
    }

    public void setGeneralInfoCollection(Collection<GeneralInfo> generalInfoCollection) {
        this.generalInfoCollection = generalInfoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (escleunik != null ? escleunik.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Escale)) {
            return false;
        }
        Escale other = (Escale) object;
        if ((this.escleunik == null && other.escleunik != null) || (this.escleunik != null && !this.escleunik.equals(other.escleunik))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Escale[ escleunik=" + escleunik + " ]";
    }
    
}
