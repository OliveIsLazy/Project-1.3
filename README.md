# Project-1.3
## Knapsack problem solving

### Context

Container dimensions: 16.5 x 2.5 x 4 --> 33 x 5 x 8 (metres).

##### Parcel A 
- Dimensions: 1 x 1 x 2 --> 2 x 2 x 4 (metres).
- Volume: 16 metres cubed.
- Value: 3
- Value/Volume = 3/16 = 0.1875

##### Parcel B 
- Dimensions: 1 x 1.5 x 2 --> 2 x 3 x 4 (metres).
- Volume: 24 metres cubed.
- Value: 4
- Value/Volume = 4/24 = 1/6 = 0.1666 recurring

##### Parcel C 
- Dimensions: 1.5 x 1.5 x 1.5 --> 3 x 3 x 3 (metres).
- Volume: 27 metres cubed.
- Value: 5
- Value/Volume = 5/25 = 0.1852 (4dp) = 0.18518519

### Goals

1. Make an algorithm that can fit three given parcels (A, B and C) into a given crate with as few spaces possible.

2. Make an algorithm that can maximise the crate's value knowing that A,B and C now have assigned values.
    - Value-Density Ranking: A > C > B = 0.1875 > 0.185 > 0.167

3. Make an algorithm that repeats the second. Replace A, B and C parcels with T, L and P pentominoes also with assigned values.

4. Answer the following questions for both the parcels and the pentominoes:
    1. Can the crate be filled holistically without any gaps?
    2. What is the highest value-density value a crate can have?
  
5. Write a report documenting your progress and conclusion in detail.
