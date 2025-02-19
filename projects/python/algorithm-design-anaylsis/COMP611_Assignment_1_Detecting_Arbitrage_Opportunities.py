
import math

# Task 1. Detect Arbitrage with Bellman-Ford Algorithms (Test with arbitrage cycle)
class Graph:
    def __init__(self, vertices, currency_names, conversion_table):
        self.V = vertices
        self.edges = []
        self.currency_names = currency_names  # Currency names for each vertex
        self.conversion_table = conversion_table  # To track actual currency conversion rates

        conversion_table_logscale = [[-math.log(rate) for rate in row] for row in conversion_table]
        
        # Add edges from the log-scale array
        for i in range(len(conversion_table_logscale)):
            for j in range(len(conversion_table_logscale[i])):
        
                self.add_edge(i + 1, j + 1, conversion_table_logscale[i][j])  # Adjust indices to match vertex numbering

    def add_edge(self, v, u, w):
        self.edges.append((v, u, w))

    def find_arbitrage(self):
        # Run Bellman-Ford from each vertex
        for src in range(1, self.V + 1):
            print(f"Checking arbitrage opportunities starting from: {self.currency_names[src-1]}")
            self.bellman_ford(src)

    def bellman_ford(self, src):
        # Initialize distances and predecessors
        d = [float("Inf")] * (self.V + 1)  # Distance array
        predecessor = [-1] * (self.V + 1)  # To track the path

        d[src] = 0

        # Relax edges |V| - 1 times
        for _ in range(self.V - 1):
            for v, u, w in self.edges:
                if d[v] + w < d[u]:
                    d[u] = d[v] + w
                    predecessor[u] = v

        # Check for negative-weight cycles
        for v, u, w in self.edges:
            if d[v] + w < d[u]:
                self.print_cycle(predecessor, u, src)
                return  # Arbitrage found, no need to continue
        
        print("There is no arbitrage opportunity")

    def print_cycle(self, predecessor, start, source):
        # To find the negative-weight cycle path
        cycle = []
        visited = set()
        current = start

        # Follow predecessors to identify the cycle
        while current not in visited:
            visited.add(current)
            current = predecessor[current]

        # Reconstruct the cycle
        cycle_start = current
        current = cycle_start

        # Include source vertex at the beginning and end to show full cycle
        cycle.append(source)
        while True:
            cycle.append(current)
            current = predecessor[current]
            if current == cycle_start:
                cycle.append(current)
                break
        
        cycle.append(source)  # Append source again to close the cycle
        
        # remove the duplicated vertex in the cycle
        i = 0
        while i < len(cycle) - 1:
            if cycle[i] == cycle[i + 1]:
                del cycle[i + 1]  # Remove the duplicate by index
            else:
                i += 1  # Only increment if no removal to avoid skipping elements
        
        # Calculate the total weight of the cycle (log scale)
        cycle_weight = 0
        conversion_rate_product = 1.0  # For calculating the actual conversion rate
        for i in range(len(cycle) - 1):
            from_currency = self.currency_names[cycle[i] - 1]
            to_currency = self.currency_names[cycle[i + 1] - 1]
            weight = next(w for v, u, w in self.edges if v == cycle[i] and u == cycle[i + 1])
            cycle_weight += weight

            # Calculate the actual conversion rate product along the cycle
            conversion_rate_product *= self.conversion_table[cycle[i] - 1][cycle[i + 1] - 1]

        # Print the cycle only if the total weight is negative
        if cycle_weight < 0:
            print("Possible arbitrage cycle detected:")
            for i in range(len(cycle) - 1):
                from_currency = self.currency_names[cycle[i] - 1]
                to_currency = self.currency_names[cycle[i + 1] - 1]
                weight = next(w for v, u, w in self.edges if v == cycle[i] and u == cycle[i + 1])
                print(f"{from_currency} -> {to_currency} (Weight: {weight}, Rate: {self.conversion_table[cycle[i] - 1][cycle[i + 1] - 1]})")
                
            print(f"Total weight of cycle (log scale): {cycle_weight}")
            print(f"Total conversion rate product: {conversion_rate_product}")

            if conversion_rate_product > 1:
                print(f"Arbitrage detected! Conversion rate > 1: {conversion_rate_product}\n")
            else:
                print("No arbitrage based on actual conversion rates (Conversion rate <= 1)\n")
        else:
            print("No negative-weight cycle found\n")
     
import math

print("Task1 - Detect Arbitrage with Bellman-Ford Algorithms (First Test)\n")
# Input Data
test1 = [[1.0, 0.6242247835816354, 0.8394642329594796, 0.9196416454776505, 0.4708903966148208, 0.5584629758593209],
        [1.6019870186221488, 1.0, 1.3448108037986857, 1.4732539778395084, 0.754360302570778, 0.8946504377077266],
        [1.1912359821151182, 0.7435990231304664, 1.0, 1.0955102187445322, 0.5609415840800336, 0.6652611915227097],
        [1.0873800734422072, 0.6787695910154445, 0.9128166792875851, 1.0, 0.512036834054254, 0.607261511704662],
        [2.1236364283257627, 1.3256264898777264, 1.7827168253892953, 1.9529844993417853, 1.0, 1.1859723194060652],
        [1.790629, 1.117755, 1.503169, 1.646737, 0.84319, 1.0]]

currency_names = ["NZD", "USD", "CAD", "AUD", "GBP", "EUR"]

graph_test1 = Graph(6, currency_names, test1)

# Find arbitrage opportunities
graph_test1.find_arbitrage()


# Task 1. Detect Arbitrage with Bellman-Ford Algorithms (Second Test)
print("Task 1. Detect Arbitrage with Bellman-Ford Algorithms (Second Test)")

test2 = [[round(test1[i][j], 4) for j in range(len(test1[i]))] for i in range(len(test1))]

# Loop through the 2D array and print each row
print("Currency Conversion Array (test2):")
for i in range(len(test2)):
    print(test2[i])
    
# Create the Graph with Second Test
graph_test2 = Graph(6, currency_names, test2)


# Find arbitrage opportunities
print("\n")
graph_test2.find_arbitrage()

