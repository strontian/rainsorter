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
package strawn.evariant.rainsorter.data.msashapefile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;

/**
 *
 * @author davidstrawn
 */
public class MSAShapefileLoader {
    
    public static Map<String, SimpleFeature> getCBSAToFeaturesMap() throws IOException {
        HashMap<String, SimpleFeature> toReturn = new HashMap();
        List<SimpleFeature> features = loadFeatures();
        for (SimpleFeature feature : features) {
            String cbsaId = feature.getProperty("CBSAFP").getValue().toString();
            toReturn.put(cbsaId, feature);
        }
        return toReturn;
    }
    
    public static ArrayList<SimpleFeature> loadFeatures() throws IOException {
        ArrayList<SimpleFeature> toReturn = new ArrayList();
        File file = new File(MSAShapefileFileInfo.LOCATION);
        Map<String, Object> map = new HashMap();
        map.put("url", file.toURI().toURL());
        DataStore dataStore = DataStoreFinder.getDataStore(map);
        String typeName = dataStore.getTypeNames()[0];
        FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
        Filter filter = Filter.INCLUDE; // ECQL.toFilter("BBOX(THE_GEOM, 10,20,30,40)")
        FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(filter);
        FeatureIterator<SimpleFeature> features = collection.features();
        while (features.hasNext()) {
            toReturn.add(features.next());
        }
        features.close();
        return toReturn;
    }
    
}
