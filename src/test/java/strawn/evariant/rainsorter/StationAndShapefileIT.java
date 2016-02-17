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
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.opengis.feature.simple.SimpleFeature;
import strawn.evariant.rainsorter.data.msashapefile.MSAShapefileLoader;
import strawn.evariant.rainsorter.data.qclcdstations.QCWeatherStationLoader;
import strawn.evariant.rainsorter.data.qclcdstations.QCWeatherStationRecord;
import strawn.evariant.rainsorter.tools.GeometryTools;

/**
 *
 * @author davidstrawn
 * 
 * This class tests our shapefiles, and our station data.
 * Our shapefile has records that define the region covered by an MSA.
 * Using the latitude and longitude from our station data, we can see if a station is in a particular MSA
 */
public class StationAndShapefileIT {
    
    private static final double PORTLAND_LATITUDE = 45.551;
    private static final double PORTLAND_LONGITUDE = -122.409;
    private static final String PORTLAND_FIPS_CODE = "38900";
    
    private List<SimpleFeature> features;
    private List<QCWeatherStationRecord> stations;
    
    @Before
    public void setup() throws IOException {
        features = MSAShapefileLoader.loadFeatures();
        stations = QCWeatherStationLoader.loadRecordsFromDisk();
    }

    /**
     * The static variables are latitude and longitude of a station and portland
     * The test looks for a region that the coordinates are located in, 
     * and makes sure that its FIPS code is the same as that of Portland-Vancouver-Hillsboro, "38900"
     */
    @Test
    public void portlandIsInPortland() {
        for (SimpleFeature feature : features) {
            if(GeometryTools.isPointInRegion(PORTLAND_LONGITUDE, PORTLAND_LATITUDE, feature)) {
                Assert.assertEquals(PORTLAND_FIPS_CODE, feature.getProperty("CBSAFP").getValue().toString());
            }
        }
    }
    
    @Test
    public void allStationsBelongToOnlyOneRegion() {
        for(QCWeatherStationRecord station : stations) {
            int featureMatchCount = 0;
            for (SimpleFeature feature : features) {
                if(GeometryTools.isPointInRegion(station.longitude, station.latitude, feature)) {
                    featureMatchCount++;
                    if(featureMatchCount > 1) {
                        System.out.println("Overmatch Station!:" + station.wban + ", feature:" + feature.getAttribute("CBSAFP").toString());
                    }
                }
            }
            if(featureMatchCount == 0) {
                System.out.println("Unmatched Station!:" + station.wban);
            }
        }
    }
            
}
