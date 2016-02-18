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
package strawn.evariant.rainsorter.data.msapop;

/**
 * This record represents one row from MSA_Population data. Each record represents an MSA.
 * 
 * @author David Strawn
 */
public class MSAPopulationRecord {
    
    public String msaName;
    public String CBSACode;
    public int population;
    
    public MSAPopulationRecord(String msaName, String CBSAcode, int population) {
        this.msaName = msaName;
        this.CBSACode = CBSAcode;
        this.population = population;
    }
    
}
