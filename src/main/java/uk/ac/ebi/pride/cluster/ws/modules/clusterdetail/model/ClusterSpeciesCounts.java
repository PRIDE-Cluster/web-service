package uk.ac.ebi.pride.cluster.ws.modules.clusterdetail.model;

import uk.ac.ebi.pride.cluster.ws.modules.assaysummary.model.SpeciesCount;

import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class ClusterSpeciesCounts {

    private List<SpeciesCount> speciesCounts;

    public List<SpeciesCount> getSpeciesCounts() {

        return speciesCounts;
    }

    public void setSpeciesCounts(List<SpeciesCount> speciesCounts) {
        this.speciesCounts = speciesCounts;
    }
}
