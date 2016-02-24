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
package strawn.evariant.rainsorter.output;

/**
 * This class aggregates the data we need for the wetness quintile map. It is output as JSON, so no methods are needed.
 */
public class MSAWithQuintileAndCoordinates {
    
    public final double latitude;
    public final double longitude;
    public final String name;
    public final int quintile;
    
    public MSAWithQuintileAndCoordinates(double latitude, double longitude, String name, int quintile) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.quintile = quintile;
    }
    
}
