Feature: To add products to the Cart and Validate the Cart and Price

Scenario: Search a product and add the product to the Cart
Given User search for Product " Round Neck Shirt 16 "
When User add the product to the Cart
And user clicks on cart icon
Then User should navigate to Your cart Page
And Added product should be available in Your cart page

Scenario: Navigate to a product and add the product to Cart
Given User navigate to Featured Collection 
And User clicks on the "1" product
When User add the product to the Cart
And user clicks on cart icon
Then User should navigate to Your cart Page
And Added product should be available in Your cart page

Scenario: To increase the Product quantity and validate the price
Given User search for Product " Round Neck Shirt 16 "
When User add the product to the Cart
And user clicks on cart icon
And increase the quantity by "2"
Then User should see the change in the quantity
And Product Total and Subtotal should match

Scenario: To remove the added product and validate the price
Given User search for Product " Round Neck Shirt 16 "
When User add the product to the Cart
And user clicks on cart icon
And user clicks on Remove link
Then Product should be deleted from the Cart

Scenario: To add multiple products and validate the price
Given User navigate to Featured Collection 
And User clicks on the all the products and added to cart
When user clicks on cart icon
Then User should navigate to Your cart Page
And Added product should be available in Your cart page

Scenario: To add multiple products with different sizes
Given User search for Product " Round Neck Shirt 16 "
When User add the product to the Cart
And User select the different sizes from Size drop down
And user clicks on cart icon
Then User should navigate to Your cart Page
And Added product should be available in Your cart page

Scenario: To add multiple products with different colors
Given User navigate to Featured Collection 
And User clicks on the "4" product
When User add the product to the Cart
And User select the different colors from color drop down
And user clicks on cart icon
Then User should navigate to Your cart Page
And Added product should be available in Your cart page




