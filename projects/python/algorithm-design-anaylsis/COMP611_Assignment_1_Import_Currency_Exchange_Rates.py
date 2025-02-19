# pip install requests
# Python library "requests" is required to be install to get the currency conversion rates.

import requests
import math

def get_exchange_rates(api_key, base_currency='EUR'):
    url = 'https://api.exchangeratesapi.io/v1/2024-08-27'
    params = {
        'access_key': api_key,
        'base': base_currency,
        
        'symbols': 'NZD,USD,CAD,AUD,GBP,EUR'  # Request all available currencies
    }

    response = requests.get(url, params=params)
    data = response.json()

    if response.status_code == 200:
        return data
    else:
        raise Exception(f"Error fetching data: {data.get('error', 'Unknown error')}")

def create_conversion_table(rates):
    currencies = list(rates['rates'].keys())
    conversion_data = []

    for currency_from in currencies:
        row = []
        for currency_to in currencies:
            if currency_from == currency_to:
                row.append(1.0)
            else:
                rate_from = rates['rates'].get(currency_from, 1)
                rate_to = rates['rates'].get(currency_to, 1)
                row.append((rate_to / rate_from))
        conversion_data.append(row)

    return conversion_data

# Execution
api_key = 'a62c4214f8df13af6977b9a35d8f9fbf'  # Replace with your actual API key
try:
    rates = get_exchange_rates(api_key)
    test1 = create_conversion_table(rates)

    # Loop through the 2D array and print each row
    print("Currency Conversion Array for test1:")
    for i in range(len(test1)):
        print(test1[i])
    
    # Create logarithm scale 2D array
    test1_logscale = [[-math.log(rate) for rate in row] for row in test1]
    print("\nNegative Logarithm Scale Array for test1:")
    for i in range(len(test1_logscale)):
        print(test1_logscale[i])
    print("\n")

    
except Exception as e:
    print(e)
