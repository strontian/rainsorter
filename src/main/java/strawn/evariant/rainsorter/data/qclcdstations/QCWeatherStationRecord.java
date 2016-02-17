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
package strawn.evariant.rainsorter.data.qclcdstations;

/**
 *
 * @author davidstrawn
 */
public class QCWeatherStationRecord {
    
    public String wban;
    public double latitude;
    public double longitude;
    
    public QCWeatherStationRecord(String wban, double latitude, double longitude) {
        this.wban = wban;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
}
