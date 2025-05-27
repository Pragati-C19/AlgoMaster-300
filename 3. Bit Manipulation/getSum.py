def getSum(a, b):
    while b != 0:
        # Sum without carry
        temp = a ^ b
        # Carry (shifted left)
        b = (a & b) << 1
        a = temp
    
    return a

print(getSum(1,2))


"""
Time Limit Exceed soo :

def getSum(a, b):
    MASK = 0xFFFFFFFF  # 32 bits mask (handles overflow)
    INT_MAX = 0x7FFFFFFF  # Max positive integer (2^31 - 1)
    
    while b != 0:
        # Calculate sum without carry and carry separately
        a, b = (a ^ b) & MASK, ((a & b) << 1) & MASK

    # Handle negative results (two's complement conversion)
    return a if a <= INT_MAX else ~(a ^ MASK)

    
"""