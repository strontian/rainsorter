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
package strawn.evariant.rainsorter.tools;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.feature.simple.SimpleFeature;

/**
 *
 * @author davidstrawn
 */
public class GeometryTools {
    
    /**
     * Takes a coordinate in the form of longitude and latitude, and a SimpleFeature, and determines whether or not
     * the coordinate is enclosed by the region described by the SimpleFeature
     * 
     * @param longitude The longitude of the point
     * @param latitude The latitude of the point
     * @param region SimpleFeature generated from a Shapefile
     * @return true if the coordinates are enclosed by the region, false otherwise
     */
    public static boolean isPointInRegion(double longitude, double latitude, SimpleFeature region) {
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);
        Coordinate coordinate = new Coordinate(longitude, latitude);
        Point point = geometryFactory.createPoint(coordinate);
        return point.within((Geometry)region.getDefaultGeometry());
    }
    
}
