#### Stock-price prediction

The program handles errors gracefully, and it writes the output in the requested CSV format.

### Parse and predict API

1. API 1: Random Sampling

The first API will sample 10 consecutive data points starting from a random timestamp. It reads the data from a CSV file, selects a random index, and returns the next 10 consecutive rows.

2. API 2: Prediction Logic

The second API will predict the next three stock prices based on the sampled data using the provided logic:

1. The first predicted value (n+1) is the second highest stock price in the 10 data points.
2. The second predicted value (n+2) is half the difference between n and n+1.
3. The third predicted value (n+3) is a quarter of the difference between n+1 and n+2.

### Utils file handler
1. Output File Format

For each file processed, the output will have the following format:

Stock-ID, Timestamp-1, stock price 1
...
Stock-ID, Timestamp-n, stock price n
Stock-ID, Timestamp-n+1, stock price n+1
Stock-ID, Timestamp-n+2, stock price n+2
Stock-ID, Timestamp-n+3, stock price n+3