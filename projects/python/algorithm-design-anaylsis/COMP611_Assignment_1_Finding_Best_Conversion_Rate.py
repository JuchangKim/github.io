# Task 2. Finding Best Conversion Rate with Floyd-Warshall Algorithms
import math

def floyd_warshall_with_path(graph):
    n = len(graph)
    
    # Initialize distance and next matrices
    dist = [[float('inf')] * n for _ in range(n)]
    next_node = [[-1] * n for _ in range(n)] # To tracking the path

    for i in range(n):
        for j in range(n):
            if i == j:
                dist[i][j] = 0  # Distance to itself is 0
            elif graph[i][j] != 0:
                dist[i][j] = graph[i][j]
                next_node[i][j] = j  # Initialize next node to j if there is an edge

    # Main loop for the Floyd-Warshall algorithm
    for k in range(n):
        for i in range(n):
            for j in range(n):
                if dist[i][j] > dist[i][k] + dist[k][j]:
                    dist[i][j] = dist[i][k] + dist[k][j]
                    next_node[i][j] = next_node[i][k]  # Update the next node in the path

    return dist, next_node

def get_path(next_node, start, end):
    # Reconstruct the path from start to end using the next_node matrix

    path = [start]
    visited = set()  # To detect cycles

    while start != end:
        if start in visited:  # Cycle detection
            return None  # Cycle detected; no valid path
        visited.add(start)

        start = next_node[start][end]
       
        path.append(start)

    return path

def best_conversion_rate(n, rates, source_index, target_index):
    # Convert rates to weights using logarithms
    weights = [[-math.log(rates[i][j]) for j in range(n)] for i in range(n)]

    # Apply the Floyd-Warshall algorithm with path tracking
    dist, next_node = floyd_warshall_with_path(weights)

    path = get_path(next_node, source_index, target_index)
    
    # Direct conversion rate
    direct_rate = rates[source_index][target_index]
    
    # Compute detoured conversion rate by multiplying rates along the path
    if path:
        rate = 1.0
        sum_of_logs = 0.0  # To store the sum of negative logarithms
        for i in range(len(path) - 1):
            rate *= rates[path[i]][path[i + 1]]
            sum_of_logs += weights[path[i]][path[i + 1]]  # Sum of negative logarithms
    else:
        rate = direct_rate  # Fallback if no detoured_rate path found
        sum_of_logs = weights[source_index][target_index]

    # Determine the best path
    if rate > direct_rate:
        path = get_path(next_node, source_index, target_index)
        path_names = [currencies[i] for i in path] if path else None
    else:
        path_names = None

    return direct_rate, rate, path_names, sum_of_logs, weights, path

# Task 2. Testing To Find Best Conversion Rate with Floyd-Warshall Algorithms (First Test)
print("Task 2. Finding Best Conversion Rate with Floyd-Warshall Algorithms (First Test)\n")

# Example usage
source_currency = 'NZD'  # Currency A
target_currency = 'USD'  # Currency C

# Example array
test1 = [[1.0, 0.6242247835816354, 0.8394642329594796, 0.9196416454776505, 0.4708903966148208, 0.5584629758593209],
        [1.6019870186221488, 1.0, 1.3448108037986857, 1.4732539778395084, 0.754360302570778, 0.8946504377077266],
        [1.1912359821151182, 0.7435990231304664, 1.0, 1.0955102187445322, 0.5609415840800336, 0.6652611915227097],
        [1.0873800734422072, 0.6787695910154445, 0.9128166792875851, 1.0, 0.512036834054254, 0.607261511704662],
        [2.1236364283257627, 1.3256264898777264, 1.7827168253892953, 1.9529844993417853, 1.0, 1.1859723194060652],
        [1.790629, 1.117755, 1.503169, 1.646737, 0.84319, 1.0]]

currencies = ["NZD", "USD", "CAD", "AUD", "GBP", "EUR"]

source_index = currencies.index(source_currency)
target_index = currencies.index(target_currency)

direct_rate, detoured_rate, path_names, sum_of_logs, weights, path = best_conversion_rate(len(test1), test1, source_index, target_index)


if path_names:
    print(f"Direct conversion rate from {source_currency} to {target_currency}: {direct_rate}")
    print(f"Detoured conversion rate from {source_currency} to {target_currency}: {detoured_rate}")
    print(f"Best conversion path via detoured conversion: {' -> '.join(path_names)}")
    print(f"The conversion rate via intermediate currency is better than the direct conversion rate.")
    # Print the negative logarithms for each step in the detoured path
    print("\nNegative logarithms along the detoured path:")
    for i in range(len(path) - 1):
        from_currency = currencies[path[i]]
        to_currency = currencies[path[i + 1]]
        log_value = weights[path[i]][path[i + 1]]
        print(f"{from_currency} -> {to_currency}: {log_value}")
    
    print(f"\nSum of negative logarithms: {sum_of_logs}")
    print(f"\nDirect conversion of logarithm scale: {-math.log(direct_rate)}")
else:
    print(f"Direct conversion rate from {source_currency} to {target_currency}: {direct_rate}")
    print("There is no shortest path via intermediate currencies.")

# Task 2. Testing To Find Best Conversion Rate with Floyd-Warshall Algorithms (Second Test)
print("\nTask 2. Finding Best Conversion Rate with Floyd-Warshall Algorithms (Second Test)\n")
# Currency list for mapping
test2 = [[1.0, 0.6242, 0.8395, 0.9196, 0.4709, 0.5585],
        [1.602, 1.0, 1.3448, 1.4733, 0.7544, 0.8947],
        [1.1912, 0.7436, 1.0, 1.0955, 0.5609, 0.6653],
        [1.0874, 0.6788, 0.9128, 1.0, 0.512, 0.6073],
        [2.1236, 1.3256, 1.7827, 1.953, 1.0, 1.186],
        [1.7906, 1.1178, 1.5032, 1.6467, 0.8432, 1.0]]

currencies = ["NZD", "USD", "CAD", "AUD", "GBP", "EUR"]
source_currency = 'NZD'  # Currency A
target_currency = 'USD'  # Currency B

source_index = currencies.index(source_currency)
target_index = currencies.index(target_currency)

direct_rate, detoured_rate, path_names, sum_of_logs, weights, path = best_conversion_rate(len(test2), test2, source_index, target_index)

if path_names:
    print(f"Direct conversion rate from {source_currency} to {target_currency}: {direct_rate}")
    print(f"Detoured conversion rate from {source_currency} to {target_currency}: {detoured_rate}")
    print(f"Best conversion path via detoured conversion: {' -> '.join(path_names)}")
    print(f"The conversion rate via intermediate currency is better than the direct conversion rate.")
    # Print the negative logarithms for each step in the detoured path
    print("\nNegative logarithms along the detoured path:")
    for i in range(len(path) - 1):
        from_currency = currencies[path[i]]
        to_currency = currencies[path[i + 1]]
        log_value = weights[path[i]][path[i + 1]]
        print(f"{from_currency} -> {to_currency}: {log_value}")
    
    print(f"\nSum of negative logarithms: {sum_of_logs}")
    print(f"\nDirect conversion of logarithm scale: {-math.log(direct_rate)}")
else:
    print(f"Direct conversion rate from {source_currency} to {target_currency}: {direct_rate}")
    print("There is no shortest path via intermediate currencies.")