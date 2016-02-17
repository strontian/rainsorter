Date Published:

July 1, 2014

Source:

http://factfinder.census.gov/faces/tableservices/jsf/pages/productview.xhtml?pid=PEP_2015_PEPANNRES&src=pt#none

Final File Name:

MSA_Population.csv

Fields:

0. ID1
1. ID2
3. MSA Name
4. Population 2010
5. Population Base(Same as 4., used to calculate growth from 4 to 6)
6. Population 2015

Notes:

Field ID2 is the CBSA code for the MSA

Data Creation Notes:

1. Remove first header from master(downloaded version came with two headers, top one had internal field names, second had display names) to create MSA_Population.csv
2. Run create_msa.sh to create MSA_Population.csv by removing all micro areas from CBSA_Population.csv


