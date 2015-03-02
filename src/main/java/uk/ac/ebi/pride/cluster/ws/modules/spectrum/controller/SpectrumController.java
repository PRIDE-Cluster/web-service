package uk.ac.ebi.pride.cluster.ws.modules.spectrum.controller;

import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uk.ac.ebi.pride.spectracluster.repo.dao.cluster.IClusterReadDao;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
@Api(value = "spectrum", description = "retrieve information about spectra", position = 0)
@Controller
@RequestMapping(value = "/spectrum")
public class SpectrumController {

    private static final Logger logger = LoggerFactory.getLogger(SpectrumController.class);

    @Autowired
    IClusterReadDao clusterReaderDao;


}
