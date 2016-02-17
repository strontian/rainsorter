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

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import java.io.IOException;
import org.geotools.feature.FeatureIterator;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.opengis.feature.simple.SimpleFeature;
import strawn.evariant.rainsorter.data.msaboundaries.MSABoundariesLoader;
import strawn.evariant.rainsorter.tools.GeometryTools;

/**
 *
 * @author davidstrawn
 */
public class MapStationToMsaIT {
    
    private static final double PORTLAND_LATITUDE = 45.551;
    private static final double PORTLAND_LONGITUDE = -122.409;
    private FeatureIterator<SimpleFeature> features;
    private static final String PORTLAND_FIPS_CODE = "38900";
    
    @Before
    public void setup() throws IOException {
        features = MSABoundariesLoader.loadFeatures();
    }
    
    @Test
    public void portlandIsInPortland() {
        while (features.hasNext()) {
            SimpleFeature feature = features.next();
            if(GeometryTools.isPointInRegion(PORTLAND_LONGITUDE, PORTLAND_LATITUDE, feature)) {
                Assert.assertEquals(PORTLAND_FIPS_CODE, feature.getProperty("CBSAFP").getValue().toString());
            }
        }
    }
    
}
