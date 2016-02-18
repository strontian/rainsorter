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
 * @author David Strawn
 * 
 * This class tests the shapefiles, and the station data
 * The shapefile has records that define a region covered by an MSA
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
                Assert.assertEquals(PORTLAND_FIPS_CODE, feature.getAttribute("CBSAFP").toString());
                //Assert.assertEquals(PORTLAND_FIPS_CODE, feature.getProperty("CBSAFP").getValue().toString());
            }
        }
    }
    /**
     * This makes sure that no station from our entire list of stations fits into more than one region.
     * In other words, we are checking that the regions do not overlap.
     */
    @Test
    public void allStationsBelongToOnlyOneRegion() {
        for(QCWeatherStationRecord station : stations) {
            int featureMatchCount = 0;
            for (SimpleFeature feature : features) {
                if(GeometryTools.isPointInRegion(station.longitude, station.latitude, feature)) {
                    featureMatchCount++;
                    if(featureMatchCount > 1) {
                        //System.out.println("Overmatch Station!:" + station.wban + ", feature:" + feature.getAttribute("CBSAFP").toString());
                    }
                }
            }
            if(featureMatchCount > 1) {
                Assert.fail();
            }
        }
    }
            
}
