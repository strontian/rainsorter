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
package strawn.evariant.rainsorter.data.precipitation;

/**
 * Record representing one row in the QCLCD precipitation data. Each record represents one hour of 
 * precipitation data at the station denoted by the wbanCode
 */
public class PrecipitationRecord {
    
    public String wbanCode;
    public int hour;
    public double inchesOfRain;
    public boolean isTrace;
    public boolean isSuspect;
    
    public PrecipitationRecord(String wbanId, int hour, double inchesOfRain, boolean isTrace, boolean isSuspect) {
        this.wbanCode = wbanId;
        this.hour = hour;
        this.inchesOfRain = inchesOfRain;
        this.isTrace = isTrace;
        this.isSuspect = isSuspect;
    }
    
}
