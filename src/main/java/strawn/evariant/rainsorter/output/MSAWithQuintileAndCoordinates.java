/*
 * Copyright (C) 2016 David Strawn.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package strawn.evariant.rainsorter.output;

/**
 * This class aggregates the data we need for the wetness quintile map. It is output as JSON, so no methods are needed.
 */
public class MSAWithQuintileAndCoordinates {
    
    public double latitude;
    public double longitude;
    public String name;
    public int quintile;
    
    public MSAWithQuintileAndCoordinates(double latitude, double longitude, String name, int quintile) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.quintile = quintile;
    }
    
}
