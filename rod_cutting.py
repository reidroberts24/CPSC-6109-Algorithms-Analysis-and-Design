def memoized_cut_rod_aux(prices, n, revenue):
    if revenue[n] >= 0:  # If already computed, return stored value
        return revenue[n]
    
    if n == 0:
        q = 0  # No revenue for zero-length rod
    else:
        q = float('-inf')  # Initialize max revenue
        for i in range(1, n + 1):  # Try all possible cuts
            q = max(q, prices[i - 1] + memoized_cut_rod_aux(prices, n - i, revenue))
    
    revenue[n] = q  # Store computed max revenue for length n
    return q

def memoized_cut_rod(prices, n):
    revenue = [-1] * (n + 1)  # Initialize memoization array with -1 (not computed)
    return memoized_cut_rod_aux(prices, n, revenue)

# Example Usage:
prices = [1, 5, 7, 10, 11, 18, 18, 21]
n = 8  # Pipe length
max_revenue = memoized_cut_rod(prices, n)
print("Max Revenue (Memoized):", max_revenue)
