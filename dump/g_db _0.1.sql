-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Creato il: Feb 11, 2016 alle 17:47
-- Versione del server: 10.1.9-MariaDB
-- Versione PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `g_db`
--

DELIMITER $$
--
-- Procedure
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `errore` (IN `messaggio` VARCHAR(128))  begin
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = messaggio;
  end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `finesequenza` (IN `seq` VARCHAR(20))  begin

        update sequenza set 
        sequenza.fine=( select datafineprevista 
        from attività where attività.nomesequenza=seq 
        order by datafineprevista desc limit 1)
        where sequenza.nome=seq;

    end$$

--
-- Funzioni
--
CREATE DEFINER=`root`@`localhost` FUNCTION `percentuale_Progetto` (`Progetto` VARCHAR(20)) RETURNS DECIMAL(6,1) begin
            declare completate      integer;
            declare tutte           integer;
            declare ris             decimal(6,2);
            
            select count(a.datafine)            into completate from attività a join sequenza s join progetto p on a.nomesequenza=s.nome and s.nomeprogetto=p.nome where p.nome=Progetto;
            select count(a.datafineprevista)    into tutte      from attività a join sequenza s join progetto p on a.nomesequenza=s.nome and s.nomeprogetto=p.nome where p.nome=Progetto;

            set ris=((completate/tutte)*100);

            return ris;
        end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `percentuale_Sequenza` (`nom` VARCHAR(20)) RETURNS DECIMAL(6,1) begin
        
            declare tutte integer;
            declare completate integer;
            declare ris decimal(6,2);


            select count(datafine)          into completate     from  attività where nomesequenza=nom;
            select count(datafineprevista)  into tutte          from  attività where nomesequenza=nom;
            
            
            
            set ris=((completate / tutte)*100);

            return ris;
        end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `attività`
--

CREATE TABLE `attività` (
  `id` int(11) NOT NULL,
  `nomesequenza` varchar(20) NOT NULL,
  `precedenza` int(11) DEFAULT NULL,
  `descrizione` varchar(80) DEFAULT NULL,
  `datainizio` date DEFAULT NULL,
  `datafineprevista` date DEFAULT NULL,
  `datafine` date DEFAULT NULL,
  `costo` decimal(6,2) DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `attività`
--

INSERT INTO `attività` (`id`, `nomesequenza`, `precedenza`, `descrizione`, `datainizio`, `datafineprevista`, `datafine`, `costo`) VALUES
(1, 'Seq1', 2, 'Simulazioni', '2015-09-11', '2016-03-12', NULL, '0.00'),
(2, 'Seq1', NULL, 'Portamozzi Posteriori', '2015-09-11', '2016-02-15', NULL, '0.00'),
(3, 'Seq1', NULL, 'Mozzi Posteriori', '2015-09-11', '2016-02-15', NULL, '0.00'),
(4, 'Seq1', NULL, 'Triangoli Anteriori e Posteriori', '2015-09-11', '2016-02-16', NULL, '0.00'),
(5, 'Seq1', NULL, 'Vari attacchi a telaio', '2015-11-17', '2015-12-07', NULL, '0.00'),
(6, 'Seq1', NULL, 'Rocker in Carbonio', '2015-11-17', '2016-02-09', NULL, '0.00'),
(7, 'Seq1', NULL, 'Barra anti-rollio(BASE)', '2015-11-30', '2016-02-22', NULL, '0.00'),
(8, 'Seq1', NULL, 'Ridimensionamento impianto frenante', '2015-11-16', '2016-02-08', NULL, '0.00'),
(9, 'Seq2', 10, 'Programmazione Taglio Laser', '2015-12-07', '2015-12-25', NULL, '0.00'),
(10, 'Seq2', NULL, 'Simulazione FEM', '2015-11-05', '2015-12-07', NULL, '0.00'),
(11, 'Seq2', NULL, 'Acquisto Tubi', '2015-11-20', '2015-12-10', NULL, '0.00'),
(12, 'Seq2', 11, 'Piega Tubi', '2015-12-10', '2015-12-21', NULL, '0.00'),
(13, 'Seq2', 11, 'Taglio e Saldatura tubi', '2015-12-10', '2015-12-21', NULL, '0.00'),
(14, 'Seq2', NULL, 'Tglio Laser Attacchi vari', '2015-12-21', '2015-12-30', NULL, '0.00'),
(15, 'Seq2', NULL, 'Saldatura Attacchi vari', '2016-01-07', '2016-01-27', NULL, '0.00'),
(16, 'Seq2', NULL, 'Acquisto materiale Sandwich', '2016-01-07', '2016-01-27', NULL, '0.00'),
(17, 'Seq2', NULL, 'Realizzazione pannelli Sandwich', '2016-01-07', '2016-01-27', NULL, '0.00'),
(18, 'Seq3', NULL, 'Flangia coppa con varie soluzioni', '2015-11-05', '2015-11-16', NULL, '0.00'),
(19, 'Seq3', NULL, 'Progettazione e realizzazione dima branco\nprova', '2015-11-05', '2015-11-19', NULL, '0.00'),
(20, 'Seq3', NULL, 'Scegliere termocoppie e sensori di\npressione', '2015-11-05', '2015-11-16', NULL, '0.00'),
(21, 'Seq3', NULL, 'Costruzione boccaglio/venturimetro', '2015-11-05', '2015-11-30', NULL, '0.00'),
(22, 'Seq3', NULL, 'Analisi comportamento motore con olio\nnuovo', '2015-10-05', '2015-10-16', NULL, '0.00'),
(23, 'Seq3', NULL, 'Stadio plenum', '2015-11-05', '2016-02-01', NULL, '0.00'),
(24, 'Seq3', NULL, 'Corpo farfallato slider o barrel', '2015-11-05', '2016-01-07', NULL, '0.00'),
(25, 'Seq3', NULL, 'Contattare Mivv per studio scarico', '2015-11-09', '2015-11-18', NULL, '0.00'),
(26, 'Seq3', NULL, 'Sistemazione banco prova', '2015-11-05', '2015-12-15', NULL, '0.00'),
(27, 'Seq3', NULL, 'Sensore pressione olio', '2015-11-05', '2015-11-16', NULL, '0.00'),
(28, 'Seq3', NULL, 'Rettifica motore', '2015-11-05', '2015-11-25', NULL, '0.00'),
(29, 'Seq3', NULL, 'Ricambi motore', '2015-11-05', '2015-11-25', NULL, '0.00'),
(30, 'Seq3', NULL, 'Simulazione GT-Suite', '2015-10-15', '2016-05-12', NULL, '0.00'),
(31, 'Seq5', NULL, 'Ricablaggio banco prova', '2015-11-07', '2015-12-11', NULL, '0.00'),
(32, 'Seq5', NULL, 'Ricerca nuovo Starter', '2015-11-03', '2015-12-19', NULL, '0.00'),
(33, 'Seq5', NULL, 'Monitoraggio Starter', '2015-12-21', '2015-12-25', NULL, '0.00'),
(34, 'Seq5', NULL, 'Caratterizazzione alternatore e consumi\nelett.p2', '2015-11-09', '2015-11-14', NULL, '0.00'),
(35, 'Seq5', NULL, 'Progettazione alternatore', '2015-11-16', '2015-12-05', NULL, '0.00'),
(36, 'Seq5', NULL, 'Relazione alternatore', '2015-12-17', '2015-12-25', NULL, '0.00'),
(37, 'Seq5', NULL, 'Montaggio e verifica alternatore al\nbanco', '2015-12-28', '2016-01-16', NULL, '0.00'),
(38, 'Seq5', NULL, 'Cablaggio autovettura', '2016-01-11', '2016-02-12', NULL, '0.00'),
(39, 'Seq5', NULL, 'Studio LC e TC', '2015-12-07', '2015-12-25', NULL, '0.00'),
(40, 'Seq5', NULL, 'Realizzazione LC e TC', '2016-02-15', '2016-02-26', NULL, '0.00'),
(41, 'Seq5', NULL, 'Configurazione LC e TC', '2016-02-29', '2016-03-19', NULL, '0.00'),
(42, 'Seq5', NULL, 'Studio sistema acquisizione dati motore', '2015-11-16', '2015-12-18', NULL, '0.00'),
(43, 'Seq5', NULL, 'Implementazione sist. acquisizione dati\nmotore', '2015-12-21', '2016-01-01', NULL, '0.00'),
(44, 'Seq5', NULL, 'Training labView nuovi ragazzi', '2015-11-02', '2015-11-13', NULL, '0.00'),
(45, 'Seq5', NULL, 'Riposizionamento antenna P2', '2015-11-09', '2015-11-20', NULL, '0.00'),
(46, 'Seq5', NULL, 'Configurazione Antennone', '2015-11-23', '2015-12-04', NULL, '0.00'),
(47, 'Seq5', NULL, 'Riconfigurazione Canale P2', '2015-11-09', '2015-11-20', NULL, '0.00'),
(48, 'Seq5', NULL, 'Test P2 con telemetria', '2015-12-07', '2015-12-11', NULL, '0.00'),
(49, 'Seq5', NULL, 'Riprogrammazione Sbrion fuori macchina', '2015-12-14', '2016-01-15', NULL, '0.00'),
(50, 'Seq5', 49, 'Programmazione Sbrio su P3', '2016-01-18', '2016-02-19', NULL, '0.00'),
(51, 'Seq5', NULL, 'Training Solidworks', '2015-11-09', '2015-12-04', NULL, '0.00'),
(52, 'Seq5', 51, 'Progettazione Supporti', '2015-12-07', '2015-12-25', NULL, '0.00'),
(53, 'Seq5', NULL, 'Realizzazione supporti', '2015-12-28', '2016-01-16', NULL, '0.00'),
(54, 'Seq5', NULL, 'Ricerca nuovi sensori automobile', '2015-11-16', '2015-11-30', NULL, '0.00'),
(55, 'Seq5', 54, 'Schemi elettrici P3', '2015-11-30', '2015-12-15', NULL, '0.00'),
(56, 'Seq5', 55, 'Cablaggio al computer', '2015-12-15', '2016-01-11', NULL, '0.00'),
(57, 'Seq5', NULL, 'Studio per cambio', '2015-11-09', '2015-12-14', NULL, '0.00'),
(58, 'Seq5', 57, 'Attuazione per cambio', '2015-12-14', '2015-12-28', NULL, '0.00'),
(59, 'Seq5', 58, 'Progettazione cruscotto', '2015-11-30', '2016-01-04', NULL, '0.00'),
(60, 'Seq5', 59, 'Realizzazione cruscotto', '2016-01-04', '2016-01-16', NULL, '0.00'),
(61, 'Seq5', 60, 'Programmazione cruscotto', '2016-01-16', '2016-02-08', NULL, '0.00'),
(62, 'Seq5', 61, 'Montaggio cruscotto', '2016-02-08', '2016-02-15', NULL, '0.00'),
(63, 'Seq6', NULL, 'Simulazione configurazione muso 2015 e 2016 con\nala.', '2015-10-04', '2015-10-04', NULL, '0.00'),
(64, 'Seq6', NULL, 'Selezione nuovi membri', '2015-10-22', '2015-10-22', '2015-10-24', '50.50'),
(65, 'Seq6', NULL, 'Valutazione piano di lavoro 2016(con i nuovi\nmembri)', '2015-11-09', '2015-11-09', '2015-11-10', '15.65'),
(66, 'Seq6', NULL, 'Apprendimento software ai nuovi', '2015-11-08', '2015-11-22', NULL, '0.00'),
(67, 'Seq6', NULL, 'Inizio progettazione ala inferiore', '2015-11-09', '2015-12-20', NULL, '0.00'),
(68, 'Seq6', NULL, 'Realizzazione ala anteriore', '2016-01-10', '2016-01-28', '2015-10-24', '20.00'),
(69, 'Seq8', NULL, 'DEMO ERRORE', '2016-01-28', NULL, NULL, '0.00'),
(70, 'Seq6', NULL, 'Valutazione mirata ad una riduzione del peso dei componenti\nesistenti', '2016-01-28', '2016-02-08', NULL, '0.00'),
(71, 'Seq3', NULL, 'Sponsorizzazione azienda per tubazioni siliconiche', NULL, NULL, NULL, '0.00'),
(72, 'Seq4', NULL, 'Scelta frizione anti saltellamento', NULL, NULL, NULL, '0.00'),
(73, 'Seq4', NULL, 'Frizione sul cruscotto', NULL, NULL, NULL, '0.00'),
(74, 'Seq4', NULL, 'Controllare regolazione cambio', NULL, NULL, NULL, '0.00'),
(75, 'Seq4', NULL, 'Trattamento interni del cambio', NULL, NULL, NULL, '0.00'),
(82, 'hh', NULL, 'ggggghgh', NULL, NULL, NULL, '0.00'),
(83, 'hh', NULL, 'prova di modifica', '2018-01-10', '2016-02-26', '2016-02-27', '0.00');

--
-- Trigger `attività`
--
DELIMITER $$
CREATE TRIGGER `costo_sequenzaV1` AFTER INSERT ON `attività` FOR EACH ROW begin 
            declare cost    decimal(6,2);
            declare cost_a  decimal(6,2);

            select costo 
                into cost 
                from sequenza 
                where nome=new.nomesequenza;

            set cost_a=new.costo;
            update sequenza 
                set costo=(cost+cost_a) 
                where nome=new.nomesequenza;
            
        end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `costo_sequenzaV2` AFTER UPDATE ON `attività` FOR EACH ROW begin 
            declare cost    decimal(6,2);
            declare cost_a  decimal(6,2);

            select costo 
                into cost 
                from sequenza 
                where nome=new.nomesequenza;
            
            set cost_a=new.costo;
            
            update sequenza 
                set costo=(cost+cost_a) 
                where nome=new.nomesequenza;

        end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `datianagrafici`
--

CREATE TABLE `datianagrafici` (
  `matricola` int(11) NOT NULL,
  `nome` varchar(30) DEFAULT NULL,
  `cognome` varchar(30) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `mail` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `datianagrafici`
--

INSERT INTO `datianagrafici` (`matricola`, `nome`, `cognome`, `telefono`, `mail`) VALUES
(1, 'Nicola', 'Sabino', '3895539050', 'nicola.sabino94@gmail.com'),
(2, 'Filippo', 'Mengarelli', NULL, NULL),
(3, 'Stefano', 'Perpetuini', '3895539050', 'nicola.sabino94@gmail.com'),
(4, 'Eugenio', 'Marchese', NULL, NULL),
(5, 'Alessandro', 'Maurizi', NULL, NULL),
(6, 'Framcesco', 'Codovilli', NULL, NULL),
(7, 'Matteo', 'Farella', NULL, NULL),
(8, 'Mauro', 'Bottiglione', NULL, NULL),
(9, 'Carmine', 'Sborgia', NULL, NULL),
(10, 'Francesco', 'Bosco', NULL, NULL),
(11, 'Francesca', 'Pacella', NULL, NULL),
(12, 'Pierpaolo', 'Salvatore', NULL, NULL),
(13, 'Luca', 'Strozzeri', NULL, NULL),
(14, 'Miky', 'Di Pumpo', NULL, NULL),
(15, 'Niko', 'Pelusi', NULL, NULL),
(16, 'Marco', 'Boni', NULL, NULL),
(17, 'Alessandro', 'Marollo', NULL, NULL),
(18, 'Luca', 'D''Isidoro', NULL, NULL),
(19, 'Denny', 'Roccabella', NULL, NULL),
(20, 'Davide', 'Cao', NULL, NULL),
(21, 'Davide', 'Venditti', NULL, NULL),
(22, 'Giacomo', 'Svampa', NULL, NULL),
(23, 'Daniele', 'Vesprini', NULL, NULL),
(24, 'Ilario', 'Buglioni', NULL, NULL),
(25, 'Michael', 'Minnoni', NULL, NULL),
(26, 'Alessandro', 'Torresi', NULL, NULL),
(27, 'Giorgio', 'Moskwa', NULL, NULL),
(28, 'Giulio', 'Di Nardo', NULL, NULL),
(29, 'Marouane', 'Zarfaoui', NULL, NULL),
(30, 'Alessandro', 'Angioletti', NULL, NULL),
(31, 'Francesco', 'Picconi', NULL, NULL),
(32, 'Antonio', 'Cucco', NULL, NULL),
(33, 'Matteo', 'Angelini', NULL, NULL),
(34, 'Alessio', 'Cupido', NULL, NULL),
(35, 'Nicola', 'Forconi', NULL, NULL),
(36, 'Marco', 'Papa', NULL, NULL),
(37, 'Paolo', 'Ingargiola', NULL, NULL),
(38, 'Alberto', 'Fiorani', NULL, NULL),
(39, 'Samuele', 'Bordi', NULL, NULL),
(40, 'Martino', 'Taffetani', NULL, NULL),
(41, 'Riccardo', 'Tamagnini', NULL, NULL),
(42, 'Dolce', 'Luna', NULL, NULL),
(43, 'Francesco', 'Di Bianco', NULL, NULL),
(44, 'Sofia', 'Santilli', NULL, NULL),
(45, 'Vittorio', 'D''Alleva', NULL, NULL),
(46, 'Lorenzo', 'Mosconi', NULL, NULL),
(47, 'Matteo', 'Agostinone', NULL, NULL),
(48, 'Luca', 'Caproli', NULL, NULL),
(49, 'Alssandro', 'Orazi', NULL, NULL),
(50, 'Andrea', 'Tittii', NULL, NULL),
(51, 'Mattia', 'Utzer', NULL, NULL),
(52, 'Emanuele', 'Ranciaro', NULL, NULL),
(53, 'Stefano', 'Vallese', NULL, NULL),
(54, 'Enrico', 'Moreschi', NULL, NULL),
(55, 'Francesco', 'Querques', NULL, NULL),
(56, 'Luca', 'Girelli', NULL, NULL),
(57, 'Cecilia', 'Scoccia', NULL, NULL),
(58, 'Fabio', 'Lametti', NULL, NULL),
(59, 'Claudio', 'Carbonari', NULL, NULL),
(60, 'Floriano', 'Piersanti', NULL, NULL),
(61, 'Miki', 'Tamburo', NULL, NULL),
(62, 'Enrico', 'Cappanera', NULL, NULL),
(63, 'Laura', 'Filipponi', NULL, NULL),
(64, 'Marco', 'Pandolfi', NULL, NULL),
(65, 'Antony', 'Colasante', NULL, NULL),
(66, 'Chiara', 'Bozza', NULL, NULL),
(67, 'Andrea', 'Tarabù', NULL, NULL),
(68, 'Francesco', 'Romagnoli', NULL, NULL),
(69, 'Gianluca', 'Ciattaglia', NULL, NULL);

--
-- Trigger `datianagrafici`
--
DELIMITER $$
CREATE TRIGGER `eliminazione_datianagafici` BEFORE DELETE ON `datianagrafici` FOR EACH ROW delete from datilavorativi where datilavorativi.matricola=old.matricola
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `inserimento_datianagrafici` AFTER INSERT ON `datianagrafici` FOR EACH ROW insert into datilavorativi(matricola) values (new.matricola)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `datilavorativi`
--

CREATE TABLE `datilavorativi` (
  `matricola` int(11) NOT NULL,
  `ruolo` enum('US','GL','TL') DEFAULT 'US',
  `pwd` varchar(20) DEFAULT '0000'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `datilavorativi`
--

INSERT INTO `datilavorativi` (`matricola`, `ruolo`, `pwd`) VALUES
(1, 'GL', '12222'),
(2, 'TL', '22366'),
(3, 'US', '12222'),
(4, 'US', '0000'),
(5, 'US', '0000'),
(6, 'US', '0000'),
(7, 'US', '0000'),
(8, 'US', '0000'),
(9, 'US', '0000'),
(10, 'US', '0000'),
(11, 'US', '0000'),
(12, 'US', '0000'),
(13, 'US', '0000'),
(14, 'US', '0000'),
(15, 'US', '0000'),
(16, 'US', '0000'),
(17, 'US', '0000'),
(18, 'US', '0000'),
(19, 'US', '0000'),
(20, 'US', '0000'),
(21, 'US', '0000'),
(22, 'US', '0000'),
(23, 'US', '0000'),
(24, 'US', '0000'),
(25, 'US', '0000'),
(26, 'US', '0000'),
(27, 'US', '0000'),
(28, 'US', '0000'),
(29, 'US', '0000'),
(30, 'US', '0000'),
(31, 'US', '0000'),
(32, 'US', '0000'),
(33, 'US', '0000'),
(34, 'US', '0000'),
(35, 'US', '0000'),
(36, 'US', '0000'),
(37, 'US', '0000'),
(38, 'US', '0000'),
(39, 'US', '0000'),
(40, 'US', '0000'),
(41, 'US', '0000'),
(42, 'US', '0000'),
(43, 'US', '0000'),
(44, 'US', '0000'),
(45, 'US', '0000'),
(46, 'US', '0000'),
(47, 'US', '0000'),
(48, 'US', '0000'),
(49, 'US', '0000'),
(50, 'US', '0000'),
(51, 'US', '0000'),
(52, 'US', '0000'),
(53, 'US', '0000'),
(54, 'US', '0000'),
(55, 'US', '0000'),
(56, 'US', '0000'),
(57, 'GL', '0000'),
(58, 'US', '0000'),
(59, 'US', '0000'),
(60, 'US', '0000'),
(61, 'GL', '0000'),
(62, 'US', '0000'),
(63, 'US', '0000'),
(64, 'US', '0000'),
(65, 'US', '0000'),
(66, 'GL', '0000'),
(67, 'US', '0000'),
(68, 'US', '0000'),
(69, 'US', '0000');

-- --------------------------------------------------------

--
-- Struttura della tabella `Fornitore`
--

CREATE TABLE `Fornitore` (
  `nome` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `indirizzo` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefono` varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mail` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dump dei dati per la tabella `Fornitore`
--

INSERT INTO `Fornitore` (`nome`, `indirizzo`, `telefono`, `mail`) VALUES
('azienda', 'via delle driadi 10', '3895539050', 'spa@esempio.it');

-- --------------------------------------------------------

--
-- Struttura della tabella `incarichi`
--

CREATE TABLE `incarichi` (
  `matricola` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `incarichi`
--

INSERT INTO `incarichi` (`matricola`, `id`) VALUES
(2, 65),
(5, 48),
(9, 61);

-- --------------------------------------------------------

--
-- Struttura della tabella `incontro`
--

CREATE TABLE `incontro` (
  `tipo` enum('milestone','checkpoint') NOT NULL DEFAULT 'checkpoint',
  `data` date NOT NULL,
  `ora` time NOT NULL,
  `luogo` varchar(20) NOT NULL,
  `verbale` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `incontro`
--

INSERT INTO `incontro` (`tipo`, `data`, `ora`, `luogo`, `verbale`) VALUES
('milestone', '2016-01-22', '00:00:00', 'Officina', NULL),
('checkpoint', '2016-01-22', '11:13:18', 'Officina', NULL),
('checkpoint', '2016-02-01', '00:00:00', 'AulaStudio', NULL),
('checkpoint', '2016-08-01', '00:00:00', 'polifunzionale', NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `Ordine`
--

CREATE TABLE `Ordine` (
  `id` int(20) NOT NULL,
  `matricola` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prezzo` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dataordine` date NOT NULL,
  `attività` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `approvazione` tinyint(1) DEFAULT NULL,
  `Descrizione` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dump dei dati per la tabella `Ordine`
--

INSERT INTO `Ordine` (`id`, `matricola`, `prezzo`, `dataordine`, `attività`, `approvazione`, `Descrizione`) VALUES
(1, '1', '200', '2016-02-11', '5', NULL, NULL),
(4, '1', '500.35', '2016-02-11', '22', NULL, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `partecipazione`
--

CREATE TABLE `partecipazione` (
  `matricola` int(11) NOT NULL,
  `tipo` enum('milestone','checkpoint') NOT NULL,
  `data` date NOT NULL,
  `ora` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `partecipazione`
--

INSERT INTO `partecipazione` (`matricola`, `tipo`, `data`, `ora`) VALUES
(1, 'milestone', '2016-01-22', '11:11:11'),
(1, 'checkpoint', '2016-01-22', '00:00:00'),
(2, 'milestone', '2016-01-22', '00:00:00'),
(3, 'checkpoint', '2016-01-22', '00:00:00');

-- --------------------------------------------------------

--
-- Struttura della tabella `Prodotto`
--

CREATE TABLE `Prodotto` (
  `Nome` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Costo` int(20) NOT NULL,
  `Azienda` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Descrizione` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `progetto`
--

CREATE TABLE `progetto` (
  `nome` varchar(20) NOT NULL,
  `DeadLine` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `progetto`
--

INSERT INTO `progetto` (`nome`, `DeadLine`) VALUES
('P2', '2016-05-15');

--
-- Trigger `progetto`
--
DELIMITER $$
CREATE TRIGGER `eliminazione_progetto` BEFORE DELETE ON `progetto` FOR EACH ROW delete from sequenza where sequenza.nomeprogetto=old.nome
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `sequenza`
--

CREATE TABLE `sequenza` (
  `nome` varchar(20) NOT NULL,
  `fine` date DEFAULT NULL,
  `nomeprogetto` varchar(20) DEFAULT NULL,
  `costo` decimal(6,2) DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `sequenza`
--

INSERT INTO `sequenza` (`nome`, `fine`, `nomeprogetto`, `costo`) VALUES
('Seq1', '2016-03-12', 'P2', '0.00'),
('Seq2', '2016-01-27', 'P2', '0.00'),
('Seq3', '2016-05-12', 'P2', '0.00'),
('Seq4', NULL, 'P2', '0.00'),
('Seq5', '2016-03-19', 'P2', '0.00'),
('Seq6', '2016-02-08', 'P2', '0.01');

--
-- Trigger `sequenza`
--
DELIMITER $$
CREATE TRIGGER `eliminazione_sequenza` BEFORE DELETE ON `sequenza` FOR EACH ROW delete from attività where attività.nomesequenza=old.nome
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `modifica_fine_sequenza` AFTER UPDATE ON `sequenza` FOR EACH ROW begin
                declare fine_seq date ;
                declare fine_prog date;

                select datafineprevista 
                    into fine_seq 
                    from attività where attività.nomesequenza=new.nome 
                    order by datafineprevista 
                    desc limit 1;

                select p.deadline into fine_prog 
                    from progetto p join sequenza s 
                    on s.nomeprogetto=p.nome and s.nome=new.nome;

                if(fine_seq='NULL')
                    then
                        call errore(concat(new.nome,' ha fine indeterminata!una fine stimata non coerente con la relativa deadline di progetto!'));
                end if;
            
                if(fine_seq>fine_prog)
                    then
                        call errore(concat(new.nome,' ha una fine stimata non coerente con la relativa deadline di progetto!'));
                end if;
            end
$$
DELIMITER ;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `attività`
--
ALTER TABLE `attività`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `datianagrafici`
--
ALTER TABLE `datianagrafici`
  ADD PRIMARY KEY (`matricola`);

--
-- Indici per le tabelle `datilavorativi`
--
ALTER TABLE `datilavorativi`
  ADD PRIMARY KEY (`matricola`);

--
-- Indici per le tabelle `Fornitore`
--
ALTER TABLE `Fornitore`
  ADD PRIMARY KEY (`nome`);

--
-- Indici per le tabelle `incarichi`
--
ALTER TABLE `incarichi`
  ADD PRIMARY KEY (`matricola`,`id`);

--
-- Indici per le tabelle `incontro`
--
ALTER TABLE `incontro`
  ADD PRIMARY KEY (`tipo`,`data`,`luogo`);

--
-- Indici per le tabelle `Ordine`
--
ALTER TABLE `Ordine`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `partecipazione`
--
ALTER TABLE `partecipazione`
  ADD PRIMARY KEY (`matricola`,`tipo`,`data`);

--
-- Indici per le tabelle `progetto`
--
ALTER TABLE `progetto`
  ADD PRIMARY KEY (`nome`);

--
-- Indici per le tabelle `sequenza`
--
ALTER TABLE `sequenza`
  ADD PRIMARY KEY (`nome`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `attività`
--
ALTER TABLE `attività`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;
--
-- AUTO_INCREMENT per la tabella `Ordine`
--
ALTER TABLE `Ordine`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
