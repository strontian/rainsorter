/*
 * Copyright 2016 davidstrawn.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package strawn.evariant.rainsorter;

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
 * @author davidstrawn
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
        regions = DataOrganizationMethods.getCBSAToFeaturesMap(MSAShapefileLoader.loadFeatures());
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
