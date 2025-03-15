def maxProfit(prices):
    min_price = float('inf')
    max_profit = 0

    for price in prices:
        # Track the lowest price so far
        if price < min_price:
            min_price = price

        # Calculate the profit
        profit = price - min_price
        if profit > max_profit:
            max_profit = profit

    return max_profit


prices = [7, 1, 5, 3, 6, 4]
print("Max Profit:", maxProfit(prices))  # Output: 5



""""
Pseudocode
The key is to track the minimum price so far and calculate profit on each day:

Initialize min_price to a large number (e.g., infinity) and max_profit to 0.
Loop through each price:
If the current price is less than min_price, update min_price.
Otherwise, calculate the profit (price - min_price) and update max_profit if it’s larger.
Return max_profit.
"""