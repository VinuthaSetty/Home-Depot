# Home-Depot
Story1:
Acceptance Criteria:

-Month should display a small Blurb of each shoe
-Month should display an image each shoe being released
-Each shoe should have a suggested price pricing

1. This script iterates for every month and checks whether price, blurb and image for all the shoes are displayed in each month.
2. As per the requirement - This script only checks for price, blurb and image if they are being displayed. However, the other info like brand, name and release month can be easily added if needed.
3. This script does not validate the price.
4. Print statements are left there for verification.
5. This is tested on Mozilla Firefox and Chrome browser.

Observation:
1. Image is not present for both of the shoes in November.


Story2:
Acceptance Criteria:
-There should be an area to submit email address
-on successful submission of a valid email address user should receive a message Thanks! We will notify you of our new shoes at this email: users email address

1. This script iterates for every month and checks to make sure “Remind me of new shoes” email box is present. And checks to make sure “Thanks! We will notify you of our new shoes at this email: users email address” text is displayed when a valid email is submitted.
2. “Notify me if this shoe is available” email boxes are displayed only for some shoes. This script assumes that this is according to the requirement and therefore does not check, if it is present for every shoe. However, it checks that the confirmation text is displayed when valid email is submitted for these text boxes.
3. This script only check for the positive scenario as per the requirement. It checks for confirmation text when a valid email is submitted.
4. This script checks for the presence of the “Remind me of new shoes” email box in 
	-Home page
	-Month Link (Jan to Dec)
	-All Shoe Link
	-Report an Issue Link
	-Issues Link
	-Problem Definition Link
It does not check for links for specific shoe brands, as this might be redundant.
5. More details like actual and expected message for each email box are logged into theShoeStore.log.

Issues:
1. All of the “Notify me if this shoe is available”– email boxes – do not display proper message when an invalid email is submitted.
2. All of the “Notify me when this shoe is available” email boxes - do not display an error message when you leave email text box empty and click on submit.
3. Both the shoes in November month do not display the images.
4. The drop down list display the brands even though there are no shoes for that brand.
5. Promotional code: when a user enters an invalid promotional code, the error message says “Invalid code format” but does not provide the valid format which is allowed.
6. In IE, in the Home page, the text “Pre-Order your shoes today” is not centered. 

