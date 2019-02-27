# RestWordFinder
Rest API for finding instances of words from a provided dictionary in a provided grid of NxN characters

Requirement assumptions and implementation details:
* The rest API accepts and returns the application/json media type
* The user provides values for N, the dictionary of words to search, and the grid on which to perform the search in a post request
* The value of the N dimensions in the NxN grid is provided in the field 'gridSize'
* The list of words in the search dictionary are provided in the 'dictionary' array
* Words in the provided dictionary and characters in the gridString are case insensitive
* The 'gridString' value lists characters in the NxN grid from left to right, top to bottom
* Spaces provided in the gridString indicate a grid square with no character present
* If the gridString length is less then NxN, trailing grid squares are left blank (as if the gridString had trailing spaces)
* If the gridString length is greater than NxN, an error is returned
* Words in the dictionary cannot contain spaces and throw an error if they do
* Words in the grid can be appear horizontally, vertically, or diagonally, going forward or backwards, up or down
* Words are only counted as present or not present, so once a word is found the algorithm exits that word early
* Words of length greater than gridSize are included in the list of not found words, rather than returning an error
* The values for gridSize, gridString, or dictionary cannot be null (or contain null values) or a validation error is thrown
* The value of gridSize must be a positive integer greater than zero
* Words in the dictionary and gridString only have characters a-zA-Z and space (in the case of the gridString)
* Words in the dictionary can be repeated, but only show up in the found and missing lists in the response once each (and in the casing of the first found result)

Additional notes:
* Authenticate to the service with basic authentication and the credentials username: "Test" and password: "Test@1234"
* Swagger documentation is available at your deployed URL at the /swagger-ui.html path
* Actuator health check is available at your deployed URL at the /actuator/health path
* Security and boilerplate classes do not have testing as they come from modules and imports created from past work and were not copied over due to time
* Granular error handling was omitted, in the interest of time