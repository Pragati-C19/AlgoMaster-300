def reverseBits(n):
    res = 0
    for _ in range(32):  # 32 iterations for 32 bits
        res = (res << 1) | (n & 1)  # Shift result and add last bit of n
        n >>= 1  # Shift n to the right by 1 bit
    return res

print(reverseBits("00000010100101000001111010011100"))