package uk.ac.ebi.pride.cluster.ws.modules.clustersummary.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uk.ac.ebi.pride.cluster.ws.modules.clustersummary.model.ClusterSummary;
import uk.ac.ebi.pride.cluster.ws.util.RepoClusterToWsClusterMapper;
import uk.ac.ebi.pride.spectracluster.repo.dao.IClusterReadDao;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
@Api(value = "clusterSummary", description = "retrieve summarised information about clusters", position = 0)
@Controller
@RequestMapping(value = "/clusterSummary")
public class ClusterSummaryController {

    private static final Logger logger = LoggerFactory.getLogger(ClusterSummaryController.class);

    @Autowired
    IClusterReadDao clusterReaderDao;

    @ApiOperation(value = "retrieves cluster summary information by cluster ID", position = 1, notes = "retrieve a record of a specific clusterSummary")
    @RequestMapping(value = "/{clusterId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    ClusterSummary getClusterSummary(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Cluster " + clusterId + " summary requested");

        return RepoClusterToWsClusterMapper.asClusterSummary(clusterReaderDao.findCluster(clusterId));
    }

    @ApiOperation(value = "list clusters summaries for given criteria", position = 2, notes = "search functionality")
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    List<ClusterSummary> simpleSearchClusters(
            @ApiParam(value = "search term to query for")
            @RequestParam(value = "q", required = false, defaultValue = "") String term,
            @RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "size", required = true) int size
    ) {

        logger.info("Fetched cluster summaries for term:" + term + " page:" + page + " size:" + size);

        return RepoClusterToWsClusterMapper.asClusterSummaryList(clusterReaderDao.getAllClusters(page, size));
    }

    @ApiOperation(value = "list similar cluster summaries given a list of peaks", position = 3, notes = "additive clustering functionality")
    @RequestMapping(value = "/nearest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    List<ClusterSummary> getSimilarClusters(
            @ApiParam(value = "peak list to compare to")
            @RequestParam(value = "peaks", required = false, defaultValue = "") String peaks
    ) {

        logger.debug("Fetched clusters for peak list: " + peaks);

        return getTestClusterSummaries(5);
    }


    private List<ClusterSummary> getTestClusterSummaries(int n) {
        List<ClusterSummary> res = new LinkedList<ClusterSummary>();

        for (int i=0;i<n;i++) {
            ClusterSummary clusterSummary = new ClusterSummary();
            clusterSummary.setId(i);
            res.add(clusterSummary);
        }

        return res;
    }
}
