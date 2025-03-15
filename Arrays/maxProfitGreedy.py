def maxProfit(prices):
    profit = 0

    # Loop through prices, start from day 1
    for i in range(1, len(prices)):
        # Capture profit from every upward price movement
        if prices[i] > prices[i - 1]:
            profit += prices[i] - prices[i - 1]

    return profit


prices = [7, 1, 5, 3, 6, 4]
print(maxProfit(prices))  # Output: 7



"""
function maxProfit(prices):
    profit = 0

    for i from 1 to length(prices) - 1:
        if prices[i] > prices[i - 1]:
            profit += prices[i] - prices[i - 1]

    return profit
"""