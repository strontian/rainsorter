/**
 * Copyright (C) 2016  David Strawn
 * 
 * This file is part of rainsorter.
 *
 * rainsorter is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * rainsorter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public License
 * along with rainsorter.  If not, see http://www.gnu.org/licenses.
 */
package strawn.evariant.rainsorter;

import strawn.evariant.rainsorter.tools.DataOrganizationMethods;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opengis.feature.simple.SimpleFeature;
import strawn.evariant.rainsorter.data.msapop.MSAPopulationLoader;
import strawn.evariant.rainsorter.data.msapop.MSAPopulationRecord;
import strawn.evariant.rainsorter.data.msashapefile.MSAShapefileLoader;

/**
 *
 * @author David Strawn
 */
public class MetropolitanStatisticalAreaIT {
    
    List<MSAPopulationRecord> populationRecords;
    Map<String, SimpleFeature> regions;
    
    public static final int EXPECTED_MSA_COUNT = 388;
    
    /**
     *
     * @throws IOException
     */
    @Before
    public void setup() throws IOException {
        populationRecords = MSAPopulationLoader.loadRecordsFromDisk();
        regions = DataOrganizationMethods.createCBSAToFeaturesMap(MSAShapefileLoader.loadFeatures());
    }
    
    /**
     * Make sure we have the correct number of MSAs(388)
     */
    @Test
    public void testNumberOfMSAs() {
        Assert.assertEquals(EXPECTED_MSA_COUNT, populationRecords.size());
    }
    
    /**
     * Makes sure that for every MSA record we have, there is a region from the shapefile that describes it
     */
    @Test
    public void testEachMSAHasRegion() {
        Set<String> regionKeys = regions.keySet();
        for(MSAPopulationRecord records : populationRecords) {
            if(!regionKeys.contains(records.CBSACode)) {
                Assert.fail();
            }
        }
    }
            
}
