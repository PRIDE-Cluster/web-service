package uk.ac.ebi.pride.cluster.ws.modules.library.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import uk.ac.ebi.pride.cluster.ws.modules.library.model.SpectralLibraryRelease;
import uk.ac.ebi.pride.cluster.ws.modules.library.util.RepoSpectralLibraryToWsSpectralLibraryMapper;
import uk.ac.ebi.pride.cluster.ws.modules.library.util.SpectralLibraryDownloadURLGenerator;
import uk.ac.ebi.pride.spectracluster.repo.dao.library.SpectralLibraryReader;
import uk.ac.ebi.pride.spectracluster.repo.model.SpectralLibraryDetail;

import java.util.List;

/**
 * @author Rui Wang
 * @version $Id$
 */
@Api(value = "library", description = "retrieve information about spectral libraries", position = 0)
@Controller
@RequestMapping(value = "/library")
public class SpectralLibraryController {

    private static final Logger logger = LoggerFactory.getLogger(SpectralLibraryController.class);

    @Autowired
    private SpectralLibraryReader spectralLibraryReader;

    @Autowired
    private SpectralLibraryDownloadURLGenerator spectralLibraryDownloadURLGenerator;

    @ApiOperation(value = "returns spectral libraries of the latest release", position = 1, notes = "retrieve spectral libraries of the latest release")
    @RequestMapping(value = "/latest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    SpectralLibraryRelease getLatestSpectralLibraryRelease() {
        logger.info("The latest spectral library release requested");

        List<SpectralLibraryDetail> latestSpectralLibraries = spectralLibraryReader.getLatestSpectralLibraries();

        return RepoSpectralLibraryToWsSpectralLibraryMapper.asSpectralLibraryRelease(latestSpectralLibraries, spectralLibraryDownloadURLGenerator);
    }

//    @ApiOperation(value = "returns PSMs for a given Cluster ID", position = 1, notes = "retrieve PSMs for a given Cluster ID")
//    @RequestMapping(value = "/latest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK) // 200
//    public
//    @ResponseBody
//    SpectralLibraryRelease getLatestSpectralLibraryRelease() {
//        logger.info("Spectral library releases requested");
//
//        //todo: hardcoded spectral libraries at the moment, need to refactor this into a more generic solution
//
//        SpectralLibraryRelease spectralLibraryRelease = new SpectralLibraryRelease();
//
//        spectralLibraryRelease.setVersion("1.0.0");
//        spectralLibraryRelease.setReleaseDate(new Date(System.currentTimeMillis()));
//
//        SpectralLibrary spectralLibrary = new SpectralLibrary();
//        spectralLibrary.setTaxonomyId(9606l);
//        spectralLibrary.setSpeciesScientificName("Homo sapiens (Human)");
//        spectralLibrary.setSpeciesName("Human");
//        spectralLibrary.setNumberOfSpectra(10000);
//        spectralLibrary.setFileSize(10000);
//        DownloadURL downloadURL = new DownloadURL();
//        downloadURL.setProtocol("FTP");
//        downloadURL.setURL("ftp://www.ebi.ac.uk/pride/cluster/download/library-1.msp");
//        spectralLibrary.addDownloadURL(downloadURL);
//        downloadURL = new DownloadURL();
//        downloadURL.setProtocol("ASPERA");
//        downloadURL.setURL("aspera://www.ebi.ac.uk/pride/cluster/download/library-1.msp");
//        spectralLibrary.addDownloadURL(downloadURL);
//
//        spectralLibraryRelease.addSpectralLibrary(spectralLibrary);
//
//        spectralLibrary = new SpectralLibrary();
//        spectralLibrary.setTaxonomyId(10090l);
//        spectralLibrary.setSpeciesScientificName("Mus musculus (Mouse)");
//        spectralLibrary.setSpeciesName("Mouse");
//        spectralLibrary.setNumberOfSpectra(1234567);
//        spectralLibrary.setFileSize(50000);
//        downloadURL = new DownloadURL();
//        downloadURL.setProtocol("FTP");
//        downloadURL.setURL("ftp://www.ebi.ac.uk/pride/cluster/download/library-2.msp");
//        spectralLibrary.addDownloadURL(downloadURL);
//        downloadURL = new DownloadURL();
//        downloadURL.setProtocol("ASPERA");
//        downloadURL.setURL("aspera://www.ebi.ac.uk/pride/cluster/download/library-2.msp");
//        spectralLibrary.addDownloadURL(downloadURL);
//
//        spectralLibraryRelease.addSpectralLibrary(spectralLibrary);
//
//        return spectralLibraryRelease;
//    }
}
