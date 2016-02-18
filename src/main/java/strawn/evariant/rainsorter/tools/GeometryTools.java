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
package strawn.evariant.rainsorter.tools;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.feature.simple.SimpleFeature;

/**
 * Static method class for helping with Geometry used by GeoTools/JTS
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
