@ui @product
Feature: Product Listing Display
    As a customer browsing products
    I want to see correct product information, layout, and navigation
    So that I can make informed purchasing decisions

    Background:
        Given I am on the product listing page

    @display
    Scenario: Display product information
        Then each product must display a product image
        And each product must display a product name
        # And each product must display a product price with currency
        # And if the product is on sale, a "Sale!" label must be shown

    # @price
    # Scenario: Display discounted price correctly
    #     When a product has a discount
    #     Then the original price must be shown with strikethrough
    #     And the discounted price must be displayed
    #     And the product price must match the product details page

    # @navigation
    # Scenario: Navigation to product details
    #     When I click on the product image
    #     And I click on the product name
    #     And I click on the "View Details" button
    #     Then the product details page for that product must open
    #     And the product name, price, and promotion status must match the listing
