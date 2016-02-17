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
 *
 * @author davidstrawn
 */
public class MSAWetness {
    
    public String msaName;
    public int msaWetness;
    
    public MSAWetness(String msaName, int msaWetness){
        this.msaName = msaName;
        this.msaWetness = msaWetness;
    }
    
    public String getOutputString() {
        return msaName + ", " + msaWetness;
    }
    
}
