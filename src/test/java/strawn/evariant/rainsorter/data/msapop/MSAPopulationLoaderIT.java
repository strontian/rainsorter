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

import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author davidstrawn
 */
public class MSAPopulationLoaderIT {
    
    static List<MSAPopulationRecord> records;
    
    public static final int EXPECTED_MSA_COUNT = 388;
    
    @BeforeClass
    public static void beforeClass() throws IOException {
        records = MSAPopulationLoader.loadRecordsFromDisk();
    }
    
    /**
     * Make sure we have the correct number of MSAs(388)
     */
    @Test
    public void testMSACount() {
        Assert.assertEquals(EXPECTED_MSA_COUNT, records.size());
    }
    
}
